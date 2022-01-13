package com.example.demo.service.impl;

import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.FactureRepository;
import com.example.demo.service.FactureService;
import com.example.demo.service.mapper.ArticleMapper;
import com.example.demo.service.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Service contenant les actions métiers liées aux articles.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<FactureDto> findAll() {
        return factureRepository.findAll().stream().map(f -> {
            FactureDto factureDto = new FactureDto();
            factureDto.setId(f.getId());
            factureDto.setClient(clientMapper.clientDto(f.getClient(), 0));

            List<LigneFactureDto> lignesDtos = new ArrayList<>();
            for (LigneFacture ligneFacture : f.getLigneFactures()) {
                ArticleDto articleDto = articleMapper.articleDto(ligneFacture.getArticle());
                LigneFactureDto ligneFactureDto = new LigneFactureDto(articleDto, ligneFacture.getQuantite());
                lignesDtos.add(ligneFactureDto);
            }
                factureDto.setLigneFactures(lignesDtos);
            return factureDto;
        }).collect(toList());
    }

}
