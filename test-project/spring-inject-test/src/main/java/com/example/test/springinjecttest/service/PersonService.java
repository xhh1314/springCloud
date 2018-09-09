package com.example.test.springinjecttest.service;

import com.example.test.springinjecttest.annotation.Service;
import com.example.test.springinjecttest.api.PersonApi;
import com.example.test.springinjecttest.bean.Person;

@Service
public class PersonService implements PersonApi {
    @Override
    public Person personDetail(String name) {
        Person person = new Person();
        return person;
    }
}
