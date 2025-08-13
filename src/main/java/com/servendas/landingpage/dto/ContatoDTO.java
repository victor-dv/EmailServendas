package com.servendas.landingpage.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ContatoDTO {
    private String nome;
    private String email;
    private String telefone;
    private String cargo;
    private String mensagem;

}
