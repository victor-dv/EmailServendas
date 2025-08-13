package com.servendas.landingpage.controller;

import com.servendas.landingpage.dto.ContatoDTO;
import com.servendas.landingpage.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contato")
public class ContatoController {
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> receberContato(@RequestBody ContatoDTO contato) {
        if (contato.getEmail() == null || contato.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("E-mail é obrigatório");
        }

        String assunto = "Novo contato da Landing Page Servendas";
        String corpo = String.format("Nome: %s\nE-mail: %s \nTelefone: %s \nCargo: %s \nMensagem: %s",
                contato.getNome(),
                contato.getEmail(),
                contato.getTelefone(),
                contato.getCargo(),
                contato.getMensagem());

        // Envia para você
        emailService.enviarEmail("js813417@gmail.com", assunto, corpo);

        // (Opcional) Envia confirmação para o cliente
        emailService.enviarEmail(contato.getEmail(), "Recebemos seu contato",
                "Olá " + contato.getNome() + ", recebemos sua mensagem e em breve entraremos em contato.");

        return ResponseEntity.ok("Contato enviado com sucesso!");
    }
}
