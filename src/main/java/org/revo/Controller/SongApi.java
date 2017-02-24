package org.revo.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.revo.Domain.*;
import org.revo.Service.*;
import org.revo.Util.Util;
import org.revo.Util.ViewDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * Created by ashraf on 18/01/17.
 */
@RestController
@RequestMapping(Role.Paths.SONG_PATH)
public class SongApi {
    @Autowired
    private SongService songService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ViewService viewService;
    @Autowired
    private CachedUserService cachedUserService;

    @GetMapping
    @JsonView(ViewDetails.CustomSong.class)
    public ResponseEntity<List<Song>> songs() {
        return ResponseEntity.ok(Util.buildLikes(songService.findAll(), cachedUserService.likesByCurrentUser()));
    }

    @PostMapping(value = "search")
    public ResponseEntity<Page<Song>> search(@RequestBody SearchCriteria searchCriteria) {
        return null;
    }

    @PostMapping
    @JsonView(ViewDetails.CustomSong.class)
    public ResponseEntity<Song> song(@ModelAttribute Song song) {
        return ResponseEntity.ok(songService.save(song));
    }

    @PostMapping("like")
    @JsonView(ViewDetails.CustomLike.class)
    public ResponseEntity<Like> like(@Validated @RequestBody Like like) {
        return ResponseEntity.ok(likeService.like(like));
    }

    @PostMapping("unlike")
    public ResponseEntity<Void> unlike(@RequestBody Like like) {
        likeService.unLike(like);
        return ResponseEntity.ok().build();
    }

    @PostMapping("view")
    @JsonView(ViewDetails.CustomView.class)
    public ResponseEntity view(@RequestBody @Validated View view) {
        viewService.view(view);
        return ResponseEntity.ok().build();
    }


    @Autowired
    UserService userService;

    @GetMapping("fuck/{parallelism}/{from}/{end}")
    public void fuck(@PathVariable("parallelism") Integer parallelism, @PathVariable("from") Integer from, @PathVariable("end") Integer end) {
        Flux.range(from, end)
                .parallel(parallelism).runOn(Schedulers.parallel())
                .map(String::valueOf).map(it -> new Song("1111111111111111111111111111", "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"))
                .map(it -> songService.save(it)).subscribe(it -> {
            likeService.like(new Like(it));
            viewService.view(new View(it));
        });

    }

}