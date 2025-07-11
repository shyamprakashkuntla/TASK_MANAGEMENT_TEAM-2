package com.ve.task_management.controller;

import com.ve.task_management.payload.request.ClientRequest;
import com.ve.task_management.payload.response.ApiResponse;
import com.ve.task_management.payload.response.ClientResponse;
import com.ve.task_management.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ClientsController {

    public final ClientService clientService;

    @GetMapping("/getAllClients")
    public ResponseEntity<ApiResponse<List<ClientResponse>>> getAllClients()
    {
        List<ClientResponse> clients = clientService.getAllClients();

        ApiResponse<List<ClientResponse>> response =
                new ApiResponse<>(HttpStatus.OK,"clients fetched successfully",true,clients);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/saveClients")
    public ResponseEntity<ApiResponse<ClientResponse>> createClient(@Valid @RequestBody ClientRequest clientRequest)
    {
        ClientResponse client = clientService.createClient(clientRequest);
        ApiResponse<ClientResponse> response = new ApiResponse<>(HttpStatus.OK,"client created successfully",true,client);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getClientById/{clientId}")
    public ResponseEntity<ApiResponse<ClientResponse>> getClientById(@PathVariable Integer clientId)
    {

        ClientResponse clientResponse = clientService.getClientById(clientId);
        ApiResponse<ClientResponse> response = new ApiResponse<>(HttpStatus.OK,"client fetched successfully",true,clientResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateClient/{clientId}")
    public ResponseEntity<ApiResponse<ClientResponse>> updateClientById(@PathVariable Integer clientId, @Valid @RequestBody ClientRequest clientRequest)
    {
        clientService.updateClient(clientId,clientRequest);
        ApiResponse<ClientResponse> response = new ApiResponse<>(HttpStatus.OK,true,"client updated successfully");

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("/deleteClient/{clientId}")
    public ResponseEntity<ApiResponse<Void>> deleteClientById(@PathVariable Integer clientId)
    {
        clientService.deleteById(clientId);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK,true,"client deleted of id:"+clientId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
