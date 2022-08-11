package br.com.skjuice.services;

import br.com.skjuice.entities.client.Client;
import br.com.skjuice.entities.client.ClientDTO;

import java.util.List;

public interface ClientService {

    public List<ClientDTO> findAll();

    public ClientDTO findById(Long id);
    public ClientDTO registerClient(Client requisition);

}
