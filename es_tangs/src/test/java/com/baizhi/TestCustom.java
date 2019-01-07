package com.baizhi;

import com.baizhi.dao.es.CustomPoetryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCustom {

    @Autowired
    private CustomPoetryRepository repository;

    //测试查询诗名并高亮
    @Test
    public void test1(){
        /*List<Poetry> like = repository.findBytitleLikeWithHighLight("李白");
        like.forEach(a -> System.out.println(a));*/
    }
}
