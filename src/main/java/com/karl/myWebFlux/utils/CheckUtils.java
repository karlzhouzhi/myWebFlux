package com.karl.myWebFlux.utils;

import com.karl.myWebFlux.exception.CheckException;

import java.util.stream.Stream;

public class CheckUtils {
    private static final String[] INVALID_NAMES = {"user", "admin", "管理员"};

    public static void checkName(String value){
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
                    throw new CheckException("name", value);
        });
    }
}
