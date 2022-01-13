package com.example.demo.service.impl;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ClientDto;
import com.example.demo.entity.Client;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.ClientService;
import com.example.demo.service.mapper.ArticleMapper;
import com.example.demo.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Service contenant les actions métiers liées aux articles.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream()
                .map(client -> {
                    int age = LocalDate.now().getYear() - client.getDateNaissance().getYear();
                    ClientDto clientDto = clientMapper.clientDto(client, age);
                    return clientDto;
                })
                .collect(toList());
        // c'est équivalent
        //List<ClientDto> list = new ArrayList<>();
        //for (Client client : clientRepository.findAll()) {
        //    int age = LocalDate.now().getYear() - client.getDateNaissance().getYear();
        //    ClientDto clientDto = clientMapper.clientDto(client, age);
        //    list.add(clientDto);
        //}
        //return list;
    }

}
