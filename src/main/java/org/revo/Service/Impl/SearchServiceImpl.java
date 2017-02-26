package org.revo.Service.Impl;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.revo.Domain.SearchCriteria;
import org.revo.Domain.Song;
import org.revo.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

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
        @SuppressWarnings("unchecked") List<Object[]> data = getFullTextQuery(entityManager, searchCriteria, true).getResultList();
        return data.stream().map(it -> {
            Song song = new Song();
            song.setId(Long.valueOf(String.valueOf(it[0])));
            return song;
        }).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Song> searchAndGet(SearchCriteria searchCriteria) {
        return getFullTextQuery(entityManager, searchCriteria, false).getResultList();
    }


    private static FullTextQuery getFullTextQuery(EntityManager entityManager, SearchCriteria searchCriteria, boolean projection) {
        FullTextEntityManager fullTextEntityManager = getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Song.class).get();
        Query query = queryBuilder.keyword().onFields(Song.SearchField()).matching(searchCriteria.getSearch()).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Song.class).
                setFirstResult(searchCriteria.getPage().getNumber()).
                setMaxResults(searchCriteria.getPage().getSize());
        if (projection) fullTextQuery.setProjection(Song.SearchField());
        return fullTextQuery;
    }
}