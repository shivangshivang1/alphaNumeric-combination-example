package com.example.demo.alphaNumeric.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utility {

    public static String randomNumberGenerator(int numberOfDigits){
        Random rand = new Random();
        if(numberOfDigits > 0 && numberOfDigits < 10){
            int factor = (int) Math.pow(10, numberOfDigits-1);
            int num = rand.nextInt(9*factor) + factor;
            return String.valueOf(num);
        } else if(numberOfDigits == 10 ){
            long num = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
            return String.valueOf(num);
        } else{
            throw new RuntimeException("Can not generate random number");
        }

    }

    public static long calculatePermutation(String input){
        Map<Character, Integer> digitMap = new HashMap<>();
        digitMap.put('0',1);
        digitMap.put('1',1);
        digitMap.put('2',4);
        digitMap.put('3',4);
        digitMap.put('4',4);
        digitMap.put('5',4);
        digitMap.put('6',4);
        digitMap.put('7',5);
        digitMap.put('8',4);
        digitMap.put('9',5);

         if (input.matches("^[1-9](\\d{6}|\\d{9})$" )){
             long i = 1;
           for(Character c : input.toCharArray()){
               i = i*digitMap.get(c);
           }
           return i;
        } else throw new RuntimeException("Can not calculate permutation");
    }
}
