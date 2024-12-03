package br.ufrn.imd.web.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class ErrorResponse {

    private int status;

    private HttpStatus httpStatus;

    private String message;

    private String timestamp;

    public ErrorResponse(int status, HttpStatus httpStatus, String message) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = Timestamp.from(Instant.now()).toString();
    }
}
