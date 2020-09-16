package ru.stqa.pft.sandbox;

import org.testng.Assert;

public class MyFirstProgram {

	public static void main(String[] args) {

		String s = "Hello world!";

		Assert.assertEquals(s, "Hello world");

	}
}