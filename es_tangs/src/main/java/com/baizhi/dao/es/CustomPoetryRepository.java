package com.baizhi.dao.es;

import com.baizhi.entity.Poetry;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

public interface CustomPoetryRepository {

    //声明多域查询
    //public List<Poetry> findByFields(String field1,String field2,String field3);

    //声明作者模糊查询并高亮
    public Map<String, Object> findBytitleLikeWithHighLight(String name,Integer page, Integer rows);
}
