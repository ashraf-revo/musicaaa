package org.revo.Service;

import org.revo.Domain.IndexedSong;
import org.revo.Domain.SearchCriteria;
import org.revo.Domain.Song;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ashraf on 22/01/17.
 */
public interface IndexedSongService {
    Page<Song> search(SearchCriteria searchCriteria);

    void save(Song song);

    void deleteAll();

    long count();

    void save(List<IndexedSong> collect);
}