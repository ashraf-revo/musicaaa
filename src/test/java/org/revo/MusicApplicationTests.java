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
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MusicApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {
        IntStream.range(0,230).forEach((i) -> System.out.print("1"));
        System.out.println();
        IntStream.range(0,28).forEach((i) -> System.out.print("1"));
    }

}