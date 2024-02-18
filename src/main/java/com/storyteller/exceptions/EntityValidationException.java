package com.storyteller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityValidationException extends RuntimeException {
    private static final long serialVersionUID = -432471719083449101L;
	private final String entityName;
    private final String errorMessage;

}
