package com.ve.task_management.service;

import com.ve.task_management.payload.request.ClientRequest;
import com.ve.task_management.payload.response.ClientResponse;

import java.util.List;


public interface ClientService {
    List<ClientResponse> getAllClients();
    ClientResponse getClientById(Integer clientId);
    ClientResponse createClient(ClientRequest clientRequest);
    ClientResponse updateClient(Integer clientId,ClientRequest clientRequest);
    String deleteById(Integer clientId);
}
