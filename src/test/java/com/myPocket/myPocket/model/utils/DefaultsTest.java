package com.myPocket.myPocket.model.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultsTest {

    @Test
    public void returnsDefaultColor() {

        String color = Defaults.getRandomColor();

        assertTrue(color.startsWith("#"));
        assertTrue(color.length() == 7);
        assertTrue(Arrays.asList(Defaults.getColors()).contains(color));
    }
}
