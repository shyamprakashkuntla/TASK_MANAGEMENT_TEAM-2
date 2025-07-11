package com.ve.task_management.repository;

import com.ve.task_management.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Clients,Integer> {

    @Query("SELECT c from Clients c where c.deleted=false")
    List<Clients> findAllActiveClients();

    @Query("SELECT c from Clients c where c.clientId=:clientId and c.deleted=false")
    Optional<Clients> findActiveClientById(Integer clientId);
}
