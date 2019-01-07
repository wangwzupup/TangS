package com.baizhi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Objects;

@Document(indexName = "poets",type = "poet")
public class Poet implements Serializable {

    @Id
    private String id;

    @Field(analyzer = "ik_max_word" ,searchAnalyzer = "ik_max_word")
    private String name;

    public Poet() {
    }

    @Override
    public String toString() {
        return "Poet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poet poet = (Poet) o;
        return Objects.equals(id, poet.id) &&
                Objects.equals(name, poet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Poet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Poet(String name) {
        this.name = name;
    }
}
