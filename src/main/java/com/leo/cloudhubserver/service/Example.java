package com.leo.cloudhubserver.service;

import com.leo.cloudhubserver.model.PersonProto;
import org.springframework.stereotype.Service;

@Service
public class Example {
    public PersonProto.Person getPerson() {
        PersonProto.Person per= PersonProto.Person.newBuilder().setId(1)
                .setName("denis")
                .addPhones("123")
                .addPhones("456")
                .build();
        return per;
    }
}
