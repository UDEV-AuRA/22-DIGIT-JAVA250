package com.example.demo.service;

import com.example.demo.entity.Article;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
       createArticle("Les conserves de viande de licorne", 22.98, 9, "https://static.hitek.fr/img/actualite/2016/08/26/41gn6tpvqtl.jpg");
       createArticle("Wenger Couteau suisse géant", 46.39, 2, "https://static.hitek.fr/img/actualite/2016/08/26/61abqa-gt8s-sx522.jpg");
       createArticle("PAPIER TOILETTE DONALD TRUMP", 4.99, 0, "https://static.hitek.fr/img/actualite/2016/08/26/61cb4xnrbol-sx522.jpg");
       createArticle("Grattoir pour Chat en Forme de Platine de DJ", 23.14, 10, "https://static.hitek.fr/img/actualite/2016/08/26/61griray9-l-sx522.jpg");
       createArticle("Jay nothing", 2, 0, "https://static.hitek.fr/img/actualite/2016/08/26/61vu-jqjygl-sy679.jpg");
       createArticle("UN AFFINEUR DE VISAGE", 52, 0, "https://static.hitek.fr/img/actualite/2016/08/26/w_41r-1yapf5l.jpg");
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
