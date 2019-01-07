package com.baizhi.dao.es;

import com.baizhi.entity.Poet;
import com.baizhi.entity.Poetry;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

//@Component
public class CustomPoetryRepositoryImpl implements CustomPoetryRepository {

    @Autowired
    private ElasticsearchTemplate template;

    private Long lla;

    //多域查询
   /* @Override
    public List<Poetry> findByFields(String field1, String field2, String field3) {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSort(SortBuilders.fieldSort(field1).order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort(field2).order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort(field3).order(SortOrder.DESC))
                .build();
        return template.queryForList(query,Poetry.class);
    }*/

    //测试名称模糊查询并且高亮
    @Override
    public Map<String, Object> findBytitleLikeWithHighLight(String name,Integer page, Integer rows) {

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(name,"title","content","poet.name"))
                // .withQuery(QueryBuilders.boolQuery().should(matchQuery("title", name)).should(matchQuery("content", name)))
                .withHighlightFields(new HighlightBuilder.Field[]{
                        new HighlightBuilder.Field("title").preTags("<span style='color:red'>").postTags("</span>"),
                        new HighlightBuilder.Field("content").preTags("<span style='color:red'>").postTags("</span>"),
                        new HighlightBuilder.Field("poet.name").preTags("<span style='color:red'>").postTags("</span>")
                })
               //  .withHighlightFields(new HighlightBuilder.Field("title").preTags("<span style='color:red'>").postTags("</span>"))
                .withPageable(new PageRequest(page-1,rows))
                .build();
        System.out.println(searchQuery.getQuery().toString());

        ArrayList<Poetry> poetries = new ArrayList<>();
        AggregatedPage<Poetry> aggregatedPage = template.queryForPage(searchQuery, Poetry.class, new SearchResultMapper() {
            //结果映射到实体类
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                Poetry poetry = null;

                //检索命中的集合对象
                SearchHits hits = searchResponse.getHits();
                for (SearchHit searchHit : hits) {
                    poetry = new Poetry();
                    poetry.setId(searchHit.getId());

                    Map<String, Object> result = searchHit.getSourceAsMap();
                    poetry.setTitle(result.get("title").toString());
                    poetry.setContent(result.get("content").toString());
                    //System.out.println(result.get("poet.getName()")+"===============================================");

                    //  HashMap<String,String> poet = (HashMap)result.get("poet");
                    //System.out.println(poet+"===============================================");

                    String poet1 = result.get("poet").toString();
                    String namess = poet1.substring(poet1.indexOf("=")+1, poet1.indexOf(","));
                    poetry.setPoet(new Poet(namess));

                    /*Poetry finalPoetry = poetry;
                    Set<String> strings = poet.keySet();
                    System.out.println(strings+"askjdhnawkjld");
                    Set<Map.Entry<String, String>> entries = poet.entrySet();
                    for (Map.Entry<String, String> entry : entries) {

                        String value = entry.getValue();
                        String key = entry.getKey();
                        poetry.setPoet(new Poet(value,key));

                    }*/

                    //将高亮结果封装到title属性中
                    if (searchHit.getHighlightFields().get("content") == null) {
                        poetry.setContent(result.get("content").toString());
                    }else {
                        poetry.setContent(searchHit.getHighlightFields().get("content").getFragments()[0].toString());
                    }

                    if (searchHit.getHighlightFields().get("title") == null) {
                        poetry.setTitle(result.get("title").toString());
                    }else {
                        poetry.setTitle(searchHit.getHighlightFields().get("title").getFragments()[0].toString());
                    }

                    if (searchHit.getHighlightFields().get("poet.name") == null) {
                        poetry.setPoet(new Poet(namess));
                    }else {
                        poetry.setPoet(new Poet(searchHit.getHighlightFields().get("poet.name").getFragments()[0].toString()));
                    }
                    //poetry.setTitle(searchHit.getHighlightFields().get("title").getFragments()[0].toString());
                    //poetry.setContent(searchHit.getHighlightFields().get("content").getFragments()[0].toString());

                    lla = hits.totalHits;

                    poetries.add(poetry);
                }
                for (Poetry poetry1 : poetries) {
                    System.out.println(poetry1);
                }

                return new AggregatedPageImpl<T>((List<T>) poetries);
            }
        });

        long pageCount = aggregatedPage.getTotalPages();
        System.out.println(pageCount+"对比总条数");

        Map<String, Object> map = new HashMap<>();
        map.put("rows",poetries);
        map.put("total",lla);

        System.out.println(lla+"总条数");
        System.out.println(page+"页数");
        System.out.println(rows+"每页展示条数");

        return map;
    }

}
