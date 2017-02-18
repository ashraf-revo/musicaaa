package org.revo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.revo.Domain.Like;
import org.revo.Domain.View;
import org.revo.Repository.LikeRepository;
import org.revo.Repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Consumer;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MusicApplicationTests {
    @Autowired
    ViewRepository likeRepository;

    @Test
    public void contextLoads() throws InterruptedException {
        List<View> likesByUser_id = likeRepository.readByUser_Id(2L);
        print(likesByUser_id);
    }

    <T extends View> void print(List<T> tList) {

        tList.forEach(t -> System.out.println(t.getId()));
    }

}