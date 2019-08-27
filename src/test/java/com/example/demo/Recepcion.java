package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = LogisticaApplication.class)
public class Recepcion {
	
	public Recepcion() {
	}

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
	@Test
	public void recepcionActiva() throws Exception {
        assertThat(this.restTemplate
        		.withBasicAuth("tcero", "Reaktor6_")
        		.getForObject("http://localhost:"+ port +"/orec/", String.class))
    		.contains("Ingreso de Recepción");
	}
	

}
