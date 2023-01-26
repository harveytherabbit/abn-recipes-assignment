package com.compilit.recipes.core.exceptions;

public class UnknownUserException extends RuntimeException {
  public UnknownUserException(String username) {
    super("Unknown user: " + username);
  }
}
