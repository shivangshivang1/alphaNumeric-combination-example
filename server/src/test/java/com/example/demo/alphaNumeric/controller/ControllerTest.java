package com.example.demo.alphaNumeric.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import com.example.demo.alphaNumeric.domain.CombinationResponse;
import com.example.demo.alphaNumeric.util.Utility;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ControllerTest extends AbsractTestController {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getCombinationTest1() throws Exception {
        String uri = "/api/combinations/1234567?startIndex=0&maxResult=25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        CombinationResponse response = super.mapFromJson(content, CombinationResponse.class);
        assertTrue(response.getResults().size() == 25);
        assertEquals(Utility.calculatePermutation("1234567"),response.getTotalCount());
        assertTrue("Result size should be less than or equal of max size", response.getResults().size() <=25);
        assertEquals(0,response.getStartIndex());
        assertEquals(25, response.getMaxResult());
    }


    @Test
    public void getCombinationTest2() throws Exception {
        String uri = "/api/combinations/abcd?startIndex=0&maxResult=25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        CombinationResponse combinationResponse = super.mapFromJson(content, CombinationResponse.class);
        assertNull(combinationResponse.getResults());
        assertEquals("INVALID_INPUT",combinationResponse.getErrorCode());
    }


    @Test
    public void getCombinationTest3() throws Exception {
        String uri = "/api/combinations/1234?startIndex=0&maxResult=25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        CombinationResponse combinationResponse = super.mapFromJson(content, CombinationResponse.class);
        assertNull(combinationResponse.getResults());
        assertEquals("INVALID_INPUT",combinationResponse.getErrorCode());
    }

    @Test
    public void getCombinationTest4() throws Exception {
        String uri = "/api/combinations/0123456?startIndex=0&maxResult=25";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        CombinationResponse combinationResponse = super.mapFromJson(content, CombinationResponse.class);
        assertNull(combinationResponse.getResults());
        assertEquals("INVALID_INPUT",combinationResponse.getErrorCode());
    }


    @Test
    public void getCombinationTest5() throws Exception {
        String uri = "/api/combinations/3213000000?startIndex=32&maxResult=50";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        CombinationResponse response = super.mapFromJson(content, CombinationResponse.class);
        assertTrue(response.getResults().size() == 32);
        assertEquals(64,response.getTotalCount());
        assertTrue("Result size should be less than or equal of max size", response.getResults().size() <=50);
        assertEquals(32,response.getStartIndex());
        assertEquals(50, response.getMaxResult());
    }
}
