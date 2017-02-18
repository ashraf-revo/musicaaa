package org.revo.Service.Impl;

//import org.elasticsearch.common.unit.Fuzziness;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.MultiMatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.revo.Domain.IndexedSong;
import org.revo.Domain.IndexedSong;
import org.revo.Domain.SearchCriteria;
import org.revo.Domain.Song;
import org.revo.Repository.IndexedSongRepository;
import org.revo.Service.IndexedSongService;
import org.revo.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

//import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * Created by ashraf on 22/01/17.
 */
@Service
public class IndexedSongServiceImpl implements IndexedSongService {
//    @Autowired
    private IndexedSongRepository indexedsongRepository;
    @Autowired
    private SongService songService;
//    @Autowired
//    private ElasticsearchOperations operations;

    @Override
    public Page<Song> search(SearchCriteria searchCriteria) {
//        QueryBuilder builder = multiMatchQuery(searchCriteria.getSearch()).field("title").field("description")
//                .operator(MatchQueryBuilder.Operator.AND).fuzziness(Fuzziness.ONE)
//                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
//        SearchQuery query = new NativeSearchQueryBuilder().withQuery(builder).build();
//        query.setPageable(searchCriteria.getPage().getPageRequest());
//        Page<IndexedSong> indexedSongList = operations.queryForPage(query, IndexedSong.class);

/*
        List<Song> find = songService.find(indexedSongList.getContent().stream().map(IndexedSong::getId).collect(toList()))
                .stream().map(it -> {
                    it.setLikes(new ArrayList<>());
                    it.setViews(new ArrayList<>());
                    it.getUser().setLikes(new ArrayList<>());
                    it.getUser().setViews(new ArrayList<>());
                    it.getUser().setSongs(new ArrayList<>());
                    return it;

                }).collect(toList());
*/


        return null;
//                new PageImpl<>(find, searchCriteria.getPage().getPageRequest(), indexedSongList.getTotalElements());

    }

    @Override
    public void save(Song song) {
//        indexedsongRepository.save(IndexedSong.indexedSong(song));
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void save(List<IndexedSong> collect) {
        collect.forEach(new Consumer<IndexedSong>() {
            @Override
            public void accept(IndexedSong indexedSong) {
//                indexedsongRepository.save(indexedSong);
            }
        });
    }
}