package com.example.demo.service.export;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class ClientExportCVSService {

    @Autowired
    private ClientRepository clientRepository;

    public void export(PrintWriter writer) {
        writer.println("Nom;Prénom;Age");
        for (Client client : clientRepository.findAll()) {
            LocalDate dateNaissance = client.getDateNaissance();
            int age = LocalDate.now().getYear() - dateNaissance.getYear();
            writer.println(client.getNom() + ";" + client.getPrenom() + ";" + age);
        }
    }

}
