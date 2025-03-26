package com.gustavostolze.picpay_desafio_backend.authorization;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizationService {
	private final RestClient restClient;

	public AuthorizationService(RestClient.Builder builder) {
		this.restClient = builder
				.baseUrl("https://util.devi.tools/api/v2/authorize")
				.build();
	}

	public void authorize() {
		var response = restClient
				.get()
				.retrieve()
				.toEntity(Authorization.class);
		
		if (response.getStatusCode().isError() && !response.getBody().isAuthorize()) {
			throw new UnauthorizeException("This transaction is unauthorized!");
		}
	}
}
