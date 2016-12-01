package com.demo.service.impl;

import com.demo.service.SearchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhaobofan on 16/9/14.
 */
@Service
public class SearchServiceImpl implements SearchService{
    private static final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Override
    public List searchInBaidu(String word) {
        String url="http://www.baidu.com/s?pn=1&wd=饿了么";
        try {
            Document document = Jsoup.connect(url).get();
            log.info(document.outerHtml());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String args[]){
        SearchServiceImpl searchService=new SearchServiceImpl();
        searchService.searchInBaidu("a");
    }
}
