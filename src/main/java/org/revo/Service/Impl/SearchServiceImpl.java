package org.revo.Service.Impl;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.revo.Domain.SearchCriteria;
import org.revo.Domain.Song;
import org.revo.Domain.User;
import org.revo.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

/**
 * Created by ashraf on 26/02/17.
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Song> search(SearchCriteria searchCriteria) {
        @SuppressWarnings("unchecked") List<Object[]> data = getFullTextQuery(searchCriteria)
                .setProjection(Song.SearchField())

                .getResultList();
        return data.stream().map(it -> {
            Song song = new Song();
            song.setId(Long.valueOf(String.valueOf(it[0])));
            song.setTitle(String.valueOf(it[1]));
            song.setDescription(String.valueOf(it[2]));
            User user = new User();
            user.setId(Long.valueOf(String.valueOf(it[3])));
            user.setName(String.valueOf(it[4]));
            user.setEmail(String.valueOf(it[5]));
            user.setInfo(String.valueOf(it[6]));
            song.setUser(user);
            return song;
        }).collect(toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Song> searchAndGet(SearchCriteria searchCriteria) {
        return getFullTextQuery(searchCriteria).getResultList();
    }

    public FullTextQuery getFullTextQuery(SearchCriteria searchCriteria) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityManager(entityManager);
        Query query = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Song.class).get()
                .keyword().onFields(Song.SearchField()).matching(searchCriteria.getSearch()).createQuery();
        return fullTextEntityManager.createFullTextQuery(query, Song.class).
                setFirstResult(searchCriteria.getPage().getNumber()).
                setMaxResults(searchCriteria.getPage().getSize());
    }
}