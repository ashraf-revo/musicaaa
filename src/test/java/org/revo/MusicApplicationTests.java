package org.revo;

import org.hibernate.search.jpa.FullTextQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.revo.Domain.Page;
import org.revo.Domain.SearchCriteria;
import org.revo.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@ActiveProfiles("custom")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MusicApplicationTests {
    @Autowired
    private SearchService searchService;

    @Test
    public void contextLoads() throws IOException, InterruptedException {
        SearchCriteria searchCriteria = new SearchCriteria("Band Of Bees - This Town Lyrics", new Page(0, 100));

        @SuppressWarnings("unchecked") List<Object[]> d = searchService.getFullTextQuery(searchCriteria)
                .setProjection(FullTextQuery.SCORE,
                        "id")
                .getResultList();

        for (Object[] objects : d) {
            for (Object object : objects) {
                System.out.print(object+" ");
            }
            System.out.println();
        }

    }
}