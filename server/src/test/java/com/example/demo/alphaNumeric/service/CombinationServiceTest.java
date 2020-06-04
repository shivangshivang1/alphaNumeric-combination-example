package com.example.demo.alphaNumeric.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.alphaNumeric.domain.CombinationResponse;
import com.example.demo.alphaNumeric.service.*;
import com.example.demo.alphaNumeric.util.Utility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CombinationServiceTest {

    @Autowired
    CombinationService combinationServiceImpl;

    /*
        TESTS:
        1. Input should be 7 or 10 digits (Check for < 7, =7, >7 & <10 , =10)
        2. Input should only contain digits
        3. Input should not start with '0'
        4. Validate result for some inputs
            i) All 1's should result in one result only
            ii) Combination of 1's & 0's should result in one result only
            iii) Combination of all 9s shuld result in 5^9 response
            iv) Result length should be permutation of input
        5. Check logic for pagination
         */
    //Check for alphanumeric input should result in invalid input
    @Test
    public void digitCheck1(){
        assertEquals("INVALID_INPUT",combinationServiceImpl.alphaNumericCombincations("abc", 0, 20).getErrorCode()
                );
    }

    //7 alphabetic character check
    @Test
    public void digitCheck2(){
        assertEquals("INVALID_INPUT",combinationServiceImpl.alphaNumericCombincations("abcaaaa", 0, 20).getErrorCode());
    }

    //check for any number < 7 digits (6 digits)
    @Test
    public void digitCheck3(){
        String randomInput = Utility.randomNumberGenerator(6);
        System.out.println("Running test for 6 digit number:" + randomInput);
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations(randomInput, 0 , 20);
        assertEquals("INVALID_INPUT",response.getErrorCode());
        assertNull(response.getResults());
    }

    //check for 8 digits
    @Test
    public void digitCheck4(){
        String randomInput = Utility.randomNumberGenerator(8);
        System.out.println("Running test for 8 digit number:" + randomInput);
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations(randomInput, 0 , 20);
        assertEquals("INVALID_INPUT",response.getErrorCode());
        assertNull(response.getResults());
    }

    //check for 9 digits
    @Test
    public void digitCheck5(){
        String randomInput = Utility.randomNumberGenerator(9);
        System.out.println("Running test for 9 digit number:" + randomInput);
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations(randomInput, 0 , 20);
        assertEquals("INVALID_INPUT",response.getErrorCode());
        assertNull(response.getResults());
    }

    //Check for input starting with 0
    @Test
    public void digitCheck6(){
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations("01234567", 0 , 20);
        assertEquals("INVALID_INPUT",response.getErrorCode());
        assertNull(response.getResults());
    }


    //Tests for random 7 digits input
    @Test
    public void digitCheck7(){
        String randomInput = Utility.randomNumberGenerator(7);
        System.out.println("Running test for 7 digit number:" + randomInput);
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations(randomInput, 0 , 20);
        assertEquals(Utility.calculatePermutation(randomInput),response.getTotalCount());
        assertTrue("Result size should be less than or equal of max size", response.getResults().size() <=20);
        assertEquals(0,response.getStartIndex());
        assertEquals(20, response.getMaxResult());
    }

//Test for random 10 digit input
    @Test
    public void digitCheck8(){
        String randomInput = Utility.randomNumberGenerator(10);
        System.out.println("Running test for 10 digit number:" + randomInput);
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations(randomInput, 0 , 50);
        assertEquals(Utility.calculatePermutation(randomInput),response.getTotalCount());
        assertTrue("Result size should be less than or equal of max size", response.getResults().size() <=50);
        assertEquals(0,response.getStartIndex());
        assertEquals(50, response.getMaxResult());
    }


    //Test for  All 1's digit input
    @Test
    public void digitCheck9(){

        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations("1111111111", 0 , 50);
        assertEquals(Utility.calculatePermutation("1111111111"),response.getTotalCount());
        assertEquals(1, response.getResults().size());
        assertEquals(0,response.getStartIndex());
        assertEquals(50, response.getMaxResult());
    }

    //Test for all 9's
    @Test
    public void digitCheck10(){
        System.out.println("Running test for 9999999999 ");
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations("9999999999", 0 , 50);
        assertEquals(Utility.calculatePermutation("9999999999"),response.getTotalCount());
        assertEquals(50, response.getResults().size());
        assertEquals(0,response.getStartIndex());
        assertEquals(50, response.getMaxResult());
    }

    //Pagination test 1
    @Test
    public void digitCheck11(){
        System.out.println("Running test for 3232322 ");
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations("3232322", 16350 , 50);
        assertEquals(16384,response.getTotalCount());
        assertEquals(34, response.getResults().size());
        assertEquals(16350,response.getStartIndex());
        assertEquals(50, response.getMaxResult());
    }

    //Pagination test 2
    @Test
    public void digitCheck12(){
        System.out.println("Running test for 3213000000 ");
        CombinationResponse response = combinationServiceImpl.alphaNumericCombincations("3213000000", 32 , 50);
        assertEquals(64,response.getTotalCount());
        assertEquals(32, response.getResults().size());
        assertEquals(32,response.getStartIndex());
        assertEquals(50, response.getMaxResult());
    }
}
