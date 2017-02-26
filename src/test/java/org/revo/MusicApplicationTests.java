package org.revo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@ActiveProfiles("custom")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MusicApplicationTests {
    @Test
    public void contextLoads() throws IOException, InterruptedException {


    }
}