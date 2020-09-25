package com.automation.tests.AkbarTests;

import java.util.Random;

public class RandomIntGenerator {
    public static void main(String[] args) {

        Random rand = new Random ();
        String serialNumber = String.format("%04d", rand.nextInt(10000));
        System.out.println(serialNumber);
        System.out.println(serialNumber);

String a = String.format("%04d", rand.nextInt(10000));

        System.out.println(a);
    }
}
