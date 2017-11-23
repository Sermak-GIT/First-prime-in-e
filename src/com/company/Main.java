package com.company;


import util.Util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Created by von und zu Professor on 25.07.2016.
 */
public class Main {

    static int primeLength = 10;

    public static void main(String[] args) {

        eulerPiece();
        BigInteger b = new BigInteger("2");
        BigInteger c = b * b + 1;

    }


    static void test() {

        Random r = new Random();
        Date s = new Date();
        for (int i = 0; i < 9000; i++) {
            isPrime(r.nextInt() * 10 + r.nextInt(10) + 0.0);
        }
        Date e = new Date();
        System.out.println(e.getTime() - s.getTime());
    }

    static boolean isPrime(Double i) {
        for (int j = 2; j < Math.sqrt(i) + 1; j++) {
            if (i != j && i % j == 0) {
                return false;
            }
        }
        return true;
    }

    static void eulerPiece() {
        String e = Util.MediaInderfaces.FileInderface.fileRead("H:\\EinesEgidiusnatürlicheUmgebung\\projects\\FirstPrimeIne\\src\\res\\euler.txt")[0];
        assert e != null : "File Error";
        err.println("e: " + e);
        while (e.length() > 9) {
            if(isPrime(Double.parseDouble(e.substring(0, primeLength)))) {
                out.println(Double.parseDouble(e.substring(0, primeLength)));
            }
            e = e.substring(1);
        }

    }

    static BigDecimal e(BigInteger prec) {
        BigDecimal e = new BigDecimal(0.0);
        for (BigInteger i = new BigInteger("1"); i.compareTo(prec) < 0; i = i.add(new BigInteger("1"))) {
            e = e.add(new BigDecimal(1).divide(new BigDecimal(fac(i.intValue()))));
        }
        return e;
    }

    private static double faculty(int i) {
        if(i == 0) { return 1;}
        else return i*faculty(i-1);
    }

    private static double fac(int i) {
        double f = 1;
        for ( ; i > 1; f *= i--);
        return f;
    }

}