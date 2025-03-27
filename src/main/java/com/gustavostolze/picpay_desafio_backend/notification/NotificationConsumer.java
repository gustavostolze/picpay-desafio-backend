package com.gustavostolze.picpay_desafio_backend.notification;

import com.gustavostolze.picpay_desafio_backend.transaction.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class NotificationConsumer {
	private final RestClient restClient;
	
	public NotificationConsumer(RestClient.Builder builder) {
		this.restClient = builder
				.baseUrl("https://util.devi.tools/api/v1/notify")
				.build();
	}


	@KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
	public void receiveNotification(Transaction transaction) {
		var response = restClient.get().retrieve().toEntity(Notification.class);

		if (response.getStatusCode().isError() &&
				!Objects.requireNonNull(response.getBody()).notificationSendedSuccessfully()) {
			throw new NotificationException("Error in notifying service! The notification will be soon delivered!");
		}
		
		// send notification simulation

		System.out.println("A new transaction: " + transaction);
	}
}
