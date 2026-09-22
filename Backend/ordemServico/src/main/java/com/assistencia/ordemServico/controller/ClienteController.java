package com.assistencia.ordemServico.controller;

import com.assistencia.ordemServico.service.ClienteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController (ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Criar cliente

    //Listar todos os clientes

    //Buscar cliente por Id

    //Editar cliente

    //Deletar Cliente

}
