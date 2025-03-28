package com.gustavostolze.picpay_desafio_backend.authorization;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class AuthorizationService {
	private final RestClient restClient;

	public AuthorizationService(RestClient.Builder builder) {
		this.restClient = builder
				.baseUrl("https://util.devi.tools/api/v2/authorize")
				.build();
	}

	public void authorize() {
		var result = restClient
				.get()
				.retrieve().onStatus(HttpStatusCode::isError, (request, response) -> {
					System.out.println(response.getStatusCode() + " " + response.getStatusText());
				})
				.toEntity(Authorization.class);

		if (result.getStatusCode().isError() && !Objects.requireNonNull(result.getBody()).isAuthorize()) {
			throw new UnauthorizeException("Unauthorized transaction!! " + result.getBody());
		}
	}
}
