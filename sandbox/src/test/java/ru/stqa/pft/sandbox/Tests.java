package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {

    @Test
    public void testStrings() {
        String s = "Hello world";

        assert s.equals("Hello world");
    }
}
