package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'insérer des données dans l'application.
 */
@Service
@Transactional
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

    private EntityManager entityManager;

    public InitData(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertTestData();
    }

    private void insertTestData() {
        Article a1 = createArticle("Les conserves de viande de licorne", 22.98, 9, "https://static.hitek.fr/img/actualite/2016/08/26/41gn6tpvqtl.jpg");
        Article a2 = createArticle("Wenger Couteau suisse géant", 46.39, 2, "https://static.hitek.fr/img/actualite/2016/08/26/61abqa-gt8s-sx522.jpg");
        Article a3 = createArticle("PAPIER TOILETTE DONALD TRUMP", 4.99, 0, "https://static.hitek.fr/img/actualite/2016/08/26/61cb4xnrbol-sx522.jpg");
        Article a4 = createArticle("Grattoir pour Chat en Forme de Platine de DJ", 23.14, 10, "https://static.hitek.fr/img/actualite/2016/08/26/61griray9-l-sx522.jpg");
        Article a5 = createArticle("Jay nothing", 2, 0, "https://static.hitek.fr/img/actualite/2016/08/26/61vu-jqjygl-sy679.jpg");
        Article a6 = createArticle("UN AFFINEUR DE VISAGE", 52, 0, "https://static.hitek.fr/img/actualite/2016/08/26/w_41r-1yapf5l.jpg");

        Client c1 = createClient("John", "Doe", LocalDate.of(2000, 2, 18));
        Client c2 = createClient("Keanu", "Reeves", LocalDate.of(1999, 1, 2));

        createFacture(c1, Pair.of(a1, 4), Pair.of(a2, 1));
        createFacture(c1, Pair.of(a2, 1));
        createFacture(c2, Pair.of(a6, 1), Pair.of(a5, 2), Pair.of(a3, 9));
        createFacture(c1, Pair.of(a6, 5));

    }

    private void createFacture(Client c, Pair<Article, Integer>... articles) {
        Facture f = new Facture();
        f.setClient(c);
        entityManager.persist(f);
        for (Pair<Article, Integer> article : articles) {
            LigneFacture ligneFacture = new LigneFacture();
            ligneFacture.setFacture(f);
            ligneFacture.setArticle(article.getFirst());
            ligneFacture.setQuantite(article.getSecond());
            entityManager.persist(ligneFacture);
        }
    }

    private Client createClient(String prenom, String nom, LocalDate dateNaissance) {
        Client c1 = new Client();
        c1.setPrenom(prenom);
        c1.setNom(nom);
        c1.setDateNaissance(dateNaissance);
        entityManager.persist(c1);
        return c1;
    }

    private Article createArticle(String libelle, double prix, int stock, String imageUrl) {
        Article a1 = new Article();
        a1.setLibelle(libelle);
        a1.setPrix(prix);
        a1.setStock(stock);
        a1.setImageUrl(imageUrl);
        entityManager.persist(a1);
        return a1;
    }

}
