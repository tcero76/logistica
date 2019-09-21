package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTest {
	
	private HttpEntity<?> requestLoggeado;
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void Init() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("username", "llastra");
		map.add("password", "Reaktor6_");
		HttpEntity<?> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> res = this.restTemplate.exchange("/login.action", HttpMethod.POST, request, String.class);
		List<String> cookie = res.getHeaders().get("Set-Cookie"); 
		MultiValueMap<String, String> cookies = new LinkedMultiValueMap<String, String>();
		cookies.add("cookie", cookie.get(0));
		requestLoggeado = new HttpEntity<MultiValueMap<String, String>>(null, cookies);
	}
	
	@Test
	public void testOrec() {
		ResponseEntity<String> resOrec = this.restTemplate.exchange("/orec/", HttpMethod.GET, requestLoggeado, String.class);
        assertThat(resOrec.getBody()).contains("Ingreso de Recepción");
	}

	@Test
	public void testUbicar() {
		ResponseEntity<String> resUbicar = this.restTemplate.exchange("/ubicar/", HttpMethod.GET, requestLoggeado, String.class);
        assertThat(resUbicar.getBody()).contains("Ubicar Ors");
	}

}
