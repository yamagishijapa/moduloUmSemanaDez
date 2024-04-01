package br.com.fullstack.moduloumsemanadez.response.errorValidation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorDetails {
    private String campo;
    private String mensagem;
}
