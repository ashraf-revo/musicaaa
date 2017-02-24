package org.revo;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.revo.Domain.Product;
import org.revo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MusicApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void contextLoads() throws InterruptedException {
//        init();

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);


        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Product.class).get();

        org.apache.lucene.search.Query query = qb
                .keyword()
                .onFields("name")
                .matching("Skyball")
                .createQuery();


        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);


        @SuppressWarnings("unchecked") List<Product> result = fullTextQuery
                .getResultList();


        result.forEach(System.out::println);

    }

    public void init() {
        List<String> skyfall = Arrays.asList(
                "Make You Feel My Love",
                "Melt My Heart to Stone",
                "Right as Rain",
                "River Lea",
                "Rolling in the Deep",
                "Send My Love (To Your New Lover)",
                "Set Fire to the Rain",
                "Skyfall",
                "Someone Like You",
                "That's It, I Quit, I'm Moving On",
                "Turning Tables",
                "When We Were Young");


        List<Product> collect = IntStream.range(0, skyfall.size()).boxed().
                map(it -> Product.builder().name(skyfall.get(it)).url("http://ex.com/" + it).build())
                .collect(toList());
        productRepository.save(collect);

    }
}