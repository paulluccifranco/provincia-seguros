package com.proyect.provincia;


import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.proyect.provincia.dto.WeatherLocalResponse;
import com.proyect.provincia.dto.WeatherResponse;
import com.proyect.provincia.service.WeatherService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(WireMockExtension.class)
@WireMockTest(httpPort = 8090)
class ProvinciaApplicationTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    WeatherService weatherService;
    @LocalServerPort
    private int port;

    @Test
    @Order(1)
    void when_call_Weather_then_status_is_bad_request() throws Exception {
        ResponseEntity<Void> response = testRestTemplate.exchange(
                String.format("http://localhost:%d/Provincia/weather?city=Lincoln", port),
                HttpMethod.GET,
                null,
                Void.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(2)
    void when_call_Weather_then_status_is_ok() throws Exception {
        stubExternalApiCalls();
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", "1");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<List<WeatherResponse>> response = testRestTemplate.exchange(
                String.format("http://localhost:%d/Provincia/weather?city=Lincoln&country=Argentina", port),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<WeatherResponse>>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertWeatherResponse(response.getBody());
    }

    @Test
    @Order(3)
    void when_recover_weathers_then_found_one() {
        List<WeatherLocalResponse> weathers = weatherService.getPersistedWeather();
        assertEquals(1, weathers.size());
    }

    private void stubExternalApiCalls() {
        stubFor(WireMock.get(urlEqualTo("/locations"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("CityResponse.json")));

        stubFor(WireMock.get(urlEqualTo("/forecasts"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("WeatherResponse.json")));
    }

    private void assertWeatherResponse(List<WeatherResponse> weatherResponses) {
        for(WeatherResponse weatherResponse : weatherResponses) {
            assertEquals("Lincoln", weatherResponse.getCity());
            assertEquals("Buenos Aires", weatherResponse.getProvince());
            assertEquals("Argentina", weatherResponse.getCountry());
            assertEquals("South America", weatherResponse.getRegion());
            assertEquals(new BigDecimal("43.0"), weatherResponse.getDailyForecasts().stream().findFirst().get().getTemperature().getMinimum().getValue());
            assertEquals("Mostly cloudy", weatherResponse.getDailyForecasts().stream().findFirst().get().getDay().getIconPhrase());
        }

    }
}
