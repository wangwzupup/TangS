package com.baizhi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Objects;

@Document(indexName = "poetrys",type = "poetry")
public class Poetry implements Serializable {

    @Id
    private String id;

    @Field(analyzer = "ik_max_word" ,searchAnalyzer = "ik_max_word")
    private String title;

    @Field(analyzer = "ik_max_word" ,searchAnalyzer = "ik_max_word")
    private String content;

    @Field(analyzer = "ik_max_word" ,searchAnalyzer = "ik_max_word")
    private Poet poet;

    @Override
    public String toString() {
        return "Poetry{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", poet=" + poet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poetry poetry = (Poetry) o;
        return Objects.equals(id, poetry.id) &&
                Objects.equals(title, poetry.title) &&
                Objects.equals(content, poetry.content) &&
                Objects.equals(poet, poetry.poet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, poet);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Poet getPoet() {
        return poet;
    }

    public void setPoet(Poet poet) {
        this.poet = poet;
    }

    public Poetry(String id, String title, String content, Poet poet) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.poet = poet;
    }

    public Poetry() {
    }
}
