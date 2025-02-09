package com.fitness.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	private int statusCode;
    private String message;
    private LocalDateTime timestamp;

}
