package com.baizhi.dao.es;

import com.baizhi.entity.Poetry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PoetryRepository extends ElasticsearchRepository<Poetry,String> {

    //模糊查询标题有李白的
    public List<Poetry> findBytitleLike(String aaa);

    //测试作者是李白的
    public List<Poetry> findByPoet_NameLike(String aaa);

    //测试内容有李白的
    public List<Poetry> findBycontentLike(String aaa);
}
