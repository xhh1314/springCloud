package com.example.springcloud.crawler4j.dao;

import com.example.springcloud.crawler4j.entity.ContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ContentDaoImpl {
    @Autowired
    private ContentDaoImplJPA contentDaoImplJPA;

    @Transactional
    public ContentDO saveContent(ContentDO contentDO){
       return contentDaoImplJPA.save(contentDO);
    }
}
