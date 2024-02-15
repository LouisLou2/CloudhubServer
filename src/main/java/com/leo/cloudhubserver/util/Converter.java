package com.leo.cloudhubserver.util;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Converter {
    //@Mapping(source = "username", target = "username")
    //@Mapping(source = "id", target = "id", qualifiedByName = "Stringify")
    //@Mapping(source = "password", target = "password")
    //@Mapping(source = "email", target = "email")
    //@Mapping(source = "phone", target = "phone", qualifiedByName = "Stringify")
    //@Mapping(source = "capacity", target = "capacity", qualifiedByName = "Stringify")
    //@Mapping(source = "used", target = "used", qualifiedByName = "Stringify")
    //@Mapping(source = "lastLogin", target = "lastLogin", qualifiedByName = "Stringify")
    //@Mapping(source = "status", target = "status", qualifiedByName = "Stringify")
    //@Mapping(source = "gender", target = "gender", qualifiedByName = "Stringify")
    //Hashtable<String, String> userToMap(User user);
    //
    //@Named("Stringify")
    //default String Stringiff(Object object) {
    //    return String.valueOf(object);
    //}
    //default Map<String, String> createMap() {
    //    return new HashMap<>();
    //}
}
