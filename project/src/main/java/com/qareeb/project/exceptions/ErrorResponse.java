package com.qareeb.project.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
}