package com.baizhi;

import com.baizhi.dao.es.CustomPoetryRepositoryImpl;
import com.baizhi.dao.es.PoetryRepository;
import com.baizhi.entity.Poetry;
import com.baizhi.service.PoetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTangsApplicationTests {

    @Autowired
    private CustomPoetryRepositoryImpl customPoetryRepository;

    @Autowired
    private PoetryRepository repository;

    @Autowired
    private PoetryService poetryService;

    //测试查询所有数据
    @Test
    public void contextLoads() {
        List<Poetry> poetries = poetryService.queryAll();
        poetries.forEach(p -> System.out.println(p));
    }

    //添加诗歌索引
    @Test
    public void testAdd() {
        List<Poetry> poetries = poetryService.queryAll();
        poetries.forEach(a -> System.out.println(a));
        repository.saveAll(poetries);
    }

    //测试查询
    @Test
    public void testfindAll() {
        Iterable<Poetry> all = repository.findAll();
        all.forEach(a -> System.out.println(a));
    }

    //测试模糊查询标题有李白的
    @Test
    public void testfindLike() {
        List<Poetry> aa = repository.findBytitleLike("李白");
        aa.forEach(a -> System.out.println(a));
    }

    //测试模糊查询作者是李白的
    @Test
    public void testfindNameLike() {
        List<Poetry> aa = repository.findByPoet_NameLike("李白");
        aa.forEach(a -> System.out.println(a));
    }

    //测试高亮搜索9552 9576
    @Test
    public void testQuan() {
        Map<String, Object> aaa = customPoetryRepository.findBytitleLikeWithHighLight("李白", 1, 10);
        for (String s : aaa.keySet()) {
            System.out.println(aaa.get("rows"));
            System.out.println("===============================");
        }
    }
}

