package com.myPocket.myPocket.model.utils;

import lombok.Data;
import lombok.Getter;

import java.util.regex.Pattern;


public class Validation {

    @Getter
    private static final Pattern loginPattern = Pattern.compile("^[a-zA-Z0-9.+_-]{5,50}$");
    @Getter
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.+_-]{5,50}$");
    @Getter
    private static final Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$");

    @Getter
    private static final Validation INSTANCE = new Validation();

    private Validation() {
    };
}
