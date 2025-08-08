package com.spring.resturant.code;

import java.util.UUID;

public class Generator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
