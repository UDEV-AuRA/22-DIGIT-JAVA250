package com.example.demo.service.mapper;

import com.example.demo.dto.ClientDto;
import com.example.demo.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto clientDto(Client client, int age) {
        return new ClientDto(client.getId(), client.getPrenom(), client.getNom(), client.getDateNaissance(), age);
    }

}
