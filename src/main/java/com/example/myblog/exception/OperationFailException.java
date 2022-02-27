package com.example.myblog.exception;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationFailException extends RuntimeException {
    private Integer Code;
    private String Msg;
}
