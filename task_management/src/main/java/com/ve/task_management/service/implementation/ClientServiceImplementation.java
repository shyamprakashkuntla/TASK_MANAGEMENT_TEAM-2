package com.ve.task_management.service.implementation;

import com.ve.task_management.entity.Clients;
import com.ve.task_management.payload.request.ClientRequest;
import com.ve.task_management.payload.response.ClientResponse;
import com.ve.task_management.repository.ClientRepository;
import com.ve.task_management.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;


    @Override
    public List<ClientResponse> getAllClients()
    {
        List<Clients> clientsList = clientRepository.findAllActiveClients();

        return clientsList.stream().map( client ->
                {
                    ClientResponse clientResponse = new ClientResponse();
                    BeanUtils.copyProperties(client,clientResponse);
                    return clientResponse ;
                }
        ).toList();
    }

    @Override
    public ClientResponse getClientById(Integer clientId)
    {
        Clients client = clientRepository.findActiveClientById(clientId)
                .orElseThrow(()->new RuntimeException("client not available of Id: "+clientId));


        ClientResponse clientResponse = new ClientResponse();
        BeanUtils.copyProperties(client,clientResponse);

        return clientResponse;
    }

    @Override
    public ClientResponse createClient(ClientRequest clientRequest)
    {
        Clients clients = new Clients();
        BeanUtils.copyProperties(clientRequest,clients);

        Clients savedClients = clientRepository.save(clients);

        ClientResponse clientResponse = new ClientResponse();
        BeanUtils.copyProperties(savedClients,clientResponse);

        return clientResponse;
    }

    @Override
    public ClientResponse updateClient(Integer clientId, ClientRequest clientRequest)
    {
        Clients existingClients = clientRepository.findActiveClientById(clientId)
                .orElseThrow(()->new RuntimeException("client not found of Id:" + clientId));

        BeanUtils.copyProperties(clientRequest,existingClients);

        Clients updatedClient = clientRepository.save(existingClients);

        ClientResponse response = new ClientResponse();
        BeanUtils.copyProperties(updatedClient,response);
        return response;
    }

    @Override
    public String deleteById(Integer clientId)
    {
        Clients existingClients = clientRepository.findActiveClientById(clientId)
                .orElseThrow(()->new RuntimeException("client not found of id: "+clientId));
        existingClients.setDeleted(true);
        clientRepository.save(existingClients);

        return "client deleted successfully";
    }
}
