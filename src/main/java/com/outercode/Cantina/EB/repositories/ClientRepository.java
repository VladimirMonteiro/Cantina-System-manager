package com.outercode.Cantina.EB.repositories;

import com.outercode.Cantina.EB.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
