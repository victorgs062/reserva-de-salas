package com.academico.api.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo de resposta de erro da API")
public class ErrorResponse {

    @Schema(description = "Mensagem descritiva do erro")
    private String error;

    @Schema(description = "Código HTTP do erro")
    private String status;

    public ErrorResponse(String error, String status) {
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }
}