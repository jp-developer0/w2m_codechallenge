package org.dynamics360.org.ecomapp;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.dynamics360.org.ecomapp.dtos.NaveDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NaveManagementTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Should return a nave when data is saved")
    void testReadNave_Found() {
        ResponseEntity<String> response = restTemplate.getForEntity("/naves/90000", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(90000);
    }

    @Test
    @DisplayName("Should return a 404 when nave is not found")
    void testReadNave_NotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/naves/12345699", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Should show all naves")
    void testFindAllnaves() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/naves?number=0&size=10", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).contains(70000,80000,90000);
    }

}
