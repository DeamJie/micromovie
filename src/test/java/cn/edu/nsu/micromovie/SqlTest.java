package cn.edu.nsu.micromovie;

import cn.edu.nsu.micromovie.dao.*;
import cn.edu.nsu.micromovie.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlTest {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private CollectionMapper collectionMapper;



    @Test
    public void getAllMovie(){
        Movie movie = new Movie();
        movie.setCountry("11");
        movie.setDownload("aaa");
        movie.setImg("aaa");
        movie.setLabelid(1);
        movie.setName("aaa");
        movie.setTranslationname("aaa");
        movie.setTime(2016);
        movie.setScore(5.5);
        movie.setIntro("1111");
        movieMapper.insertMovie(movie);
//        System.out.println(movieMapper.selectById(1).getName());
//        System.out.println(movieMapper.selectByTime(2016).get(0).getName());
//        System.out.println(movieMapper.selectByLabel(1).get(0).getName());
//        System.out.println(movieMapper.selectByName("aaa").get(0).getName());
    }
    @Test
    public void labelTest(){
        Label label = new Label();
        label.setName("11111");
        labelMapper.insertLabel(label);
        System.out.println(labelMapper.selectAllLabel().size());
        System.out.println(labelMapper.selectLableByID(1).getName());
    }

    @Test
    public void evaluationTest(){
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluation("111");
        evaluation.setMovieid(1);
        evaluation.setUserid(1);
        evaluationMapper.insertEvaluation(evaluation);
        System.out.println(evaluationMapper.selectByMovieId(1).get(0).getEvaluation());
        System.out.println(evaluationMapper.selectByUserId(1).get(0).getEvaluation());
    }

    @Test
    public void userTest(){
        User user = new User();
        user.setSalt("111");
        user.setPhonenumber(11111L);
        user.setPassworld("11111");
        user.setName("aaa");
        user.setMail("dfsdf");
        user.setIntroduction("aaaa");
        user.setHeadportrait("1111");
        userMapper.insertUser(user);
        System.out.println(userMapper.selectByMail("dfsdf"));
    }

    @Test
    public void collectionTest(){
        Collection collection = new Collection();
        collection.setUserid(1);
        collection.setMovieid(1);
        collectionMapper.insertCollection(collection);
        System.out.println(collectionMapper.selectByUserId(1).get(0).getUserid());
    }

    @Test
    public void adminTest(){
        Admin admin = new Admin();
        admin.setName("111");
        admin.setPassworld("11");
        admin.setSalt("1");
        adminMapper.insertAdmin(admin);
        System.out.println(adminMapper.selectByName("111").getName());
    }
}
