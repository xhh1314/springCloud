package com.example.springcloud.crawler4j.dao;

import com.example.springcloud.crawler4j.entity.ContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentDaoImpl {
    @Autowired
    private ContentDaoImplJPA contentDaoImplJPA;

    public ContentDO saveContent(ContentDO contentDO){
       return contentDaoImplJPA.save(contentDO);
    }
}
