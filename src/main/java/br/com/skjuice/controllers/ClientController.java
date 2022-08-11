package br.com.skjuice.controllers;

import br.com.skjuice.entities.client.Client;
import br.com.skjuice.entities.client.ClientDTO;
import br.com.skjuice.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){

        ClientDTO clientDTO = clientService.findById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody Client client){

        ClientDTO clientDTO = clientService.registerClient(client);

        return ResponseEntity.ok(clientDTO);
    }

}
