package com.youngzy.stackskills.dp.p13chainresponsibility;

import java.io.IOException;

public class ChainResponsibility {
    public static void methodOne(int randomNumber)
        throws IOException, NullPointerException {
        if (randomNumber == 1) {
            throw new IOException("IO Exception in method one");
        } else if (randomNumber == 2) {
            throw new NullPointerException("Null Pointer in method one");
        }
    }

    public static void methodTwo(int randomNumber)
            throws NullPointerException {

        try {
            methodOne(randomNumber);
        } catch (IOException e) {
            System.out.print("IO Exception in method two");
        }
    }

    public static void methodThree(int randomNumber) {

        try {
            methodTwo(randomNumber);
        } catch (NullPointerException e) {
            System.out.print("Null Pointer Exception in method Three");
        }
    }

    public static void main(String[] args) {
        int randomNumber = (int) Math.ceil(Math.random() * 10);
        System.out.println(randomNumber);
        methodThree(randomNumber);
    }
}
