package com.myPocket.myPocket.model.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class ValidationTest {

    @Test
    public void returnsProperPaterns() {
        Pattern loginPattern = Validation.getLoginPattern();
        Pattern emailPattern = Validation.getEmailPattern();
        Pattern passwordPattern = Validation.getPasswordPattern();

        assertNotNull(loginPattern);
        assertNotNull(emailPattern);
        assertNotNull(passwordPattern);
    }

    @Test
    public void checksLoginCorrectly() {
        Pattern loginPattern = Validation.getLoginPattern();

        String[] validMatches = new String[]{
            "adamadam",
            "ADAMADAM",
            "ADAM_ADAM",
            "ADAM-ADAM",
            "ADAM+ADAM",
            "ADAM12345",
            "123456789",
            "123-456",
            "123_456",
            "123+456"
        };

        String[] invalidMatches = new String[]{
            "adam",
            "1234",
            "adam@",
            "adam%",
            "adam.%"
        };

        for (String validMatch : validMatches) {
            assertTrue(loginPattern.matcher(validMatch).matches(), "Login '" + validMatch + "' should be valid");
        }

        for (String invalidMatch : invalidMatches) {
            assertFalse(loginPattern.matcher(invalidMatch).matches(), "Login '" + invalidMatch + "' should be invalid");
        }
    }

    @Test
    public void checksEmailCorrectly() {
        Pattern emailPattern = Validation.getEmailPattern();

        String[] validMatches = new String[]{
            "abc@def.com",
            "123@132.123",
            "abc_def@ghi_jkl.com",
            "abc-def@ghi-jkl.com",
            "abc+def@ghi+jkl.com",
            "ABC@DEF.COM",
            "abcdefg@com.com"
        };

        String[] invalidMatches = new String[]{
            "abc@def.com@",
            "abc@def@com",
            "abc@def$com",
            "abc@def%com",
            "abc%def.com",
            "abc&def.com",
            "abc$def.com",
            "abcdefghijk",
            "abcdefghijk@"
        };

        for (String validMatch : validMatches) {
            assertTrue(emailPattern.matcher(validMatch).matches(), "Email '" + validMatch + "' should be valid");
        }

        for (String invalidMatch : invalidMatches) {
            assertFalse(emailPattern.matcher(invalidMatch).matches(), "Email '" + invalidMatch + "' should be invalid");
        }
    }

    @Test
    public void checksPasswordCorrectly() {
        Pattern passwordPattern = Validation.getPasswordPattern();

        String[] validMatches = new String[]{
                "Ab123456",
                "12somethingA",
                "1somethingA",
                "adamAdam1234",
                "AdAm1234AdAm"
        };

        String[] invalidMatches = new String[]{
                "123456789",
                "abcdefghijk",
                "abcdefg1234",
                "ABCDEFG1234",
                "@&$12somethingA",
                "@&$1somethingA",
                "AbCdEfGhIjK",
                "AbCdEfGhIjK",
                "AbCdEfGh@%&$",
                "abcdefgh@%&$",
                "ABCDEFGH@%&$",
                "123456798@%&$"
        };

        for (String validMatch : validMatches) {
            assertTrue(passwordPattern.matcher(validMatch).matches(), "Password '" + validMatch + "' should be valid");
        }

        for (String invalidMatch : invalidMatches) {
            assertFalse(passwordPattern.matcher(invalidMatch).matches(), "Password '" + invalidMatch + "' should be invalid");
        }
    }

}
