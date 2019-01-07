package com.baizhi.controller;

import com.baizhi.dao.es.CustomPoetryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/sou")
public class PoetryController {
    @Autowired
    private CustomPoetryRepositoryImpl customPoetryRepository;

    @RequestMapping("/app")
    @ResponseBody
    public Map<String, Object> app(String querys,Integer page, Integer rows){
        Map<String, Object> map = customPoetryRepository.findBytitleLikeWithHighLight(querys, page, rows);
        return map;
    }
}
