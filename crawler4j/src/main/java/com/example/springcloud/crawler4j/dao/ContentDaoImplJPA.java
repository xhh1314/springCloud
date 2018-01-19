package com.example.springcloud.crawler4j.dao;

import com.example.springcloud.crawler4j.entity.ContentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDaoImplJPA extends JpaRepository<ContentDO,Integer> {
}
