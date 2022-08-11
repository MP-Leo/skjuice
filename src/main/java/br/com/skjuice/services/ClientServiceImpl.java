package br.com.skjuice.services;

import br.com.skjuice.entities.client.Client;
import br.com.skjuice.entities.client.ClientDTO;
import br.com.skjuice.exceptions.EntityAlredyExistException;
import br.com.skjuice.exceptions.EntityNotFoundException;
import br.com.skjuice.repositories.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client, ClientDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO findById(Long id) {

        Optional<Client> client = clientRepository.findById(id);

        if(client.isPresent()){
            return modelMapper.map(client.get(), ClientDTO.class);
        }

        throw new EntityNotFoundException(id);

    }

    @Override
    public ClientDTO registerClient(Client clientRequisition) {

        if(clientRepository.findByEmail(clientRequisition.getEmail()).isPresent()){
            throw new EntityAlredyExistException("Email: " + clientRequisition.getEmail() + " already in use");
        }

        Client saved = clientRepository.save(clientRequisition);

        return modelMapper.map(saved, ClientDTO.class);
    }
}
