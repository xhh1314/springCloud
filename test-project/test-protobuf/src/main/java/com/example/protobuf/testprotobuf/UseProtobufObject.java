package com.example.protobuf.testprotobuf;

import com.example.protobuf.testprotobuf.AddressBookProtos.AddressBook;
import com.example.protobuf.testprotobuf.AddressBookProtos.Person;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;

public class UseProtobufObject {



  public void test1() throws InvalidProtocolBufferException {
    AddressBook addressBook = AddressBook.newBuilder().build();
    List<Person> peopleList = addressBook.getPeopleList();

    Person lihao = Person.newBuilder().setEmail("903440799@qq.com").setId(1).setName("lihao")
        .build();
    byte[] bytes = lihao.toByteArray();

    Person person = Person.parseFrom(bytes);

  }

}
