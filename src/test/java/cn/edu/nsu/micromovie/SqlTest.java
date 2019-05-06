package cn.edu.nsu.micromovie;

import cn.edu.nsu.micromovie.Filter.MovieFilter;
import cn.edu.nsu.micromovie.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlTest {
    @Autowired
    private MovieService movieService;

    @Test
    public void movieServiceTest(){
        MovieFilter filter = new MovieFilter();
        filter.setTime(2018);
        movieService.selectByFilter(filter);
    }
}
