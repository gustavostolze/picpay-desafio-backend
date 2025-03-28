package com.gustavostolze.picpay_desafio_backend.authorization.exceptions;

import java.time.LocalDateTime;

public record StandardError(LocalDateTime timestamp, Integer status, String error, String message, String path) {
}
