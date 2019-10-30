package com.codve.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author admin
 * @date 2019/10/30 16:32
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Person not found.")
public class MessageNotFoundException extends RuntimeException {
}
