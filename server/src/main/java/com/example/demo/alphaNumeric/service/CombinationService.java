package com.example.demo.alphaNumeric.service;


import com.example.demo.alphaNumeric.domain.CombinationResponse;

public interface CombinationService {
    public CombinationResponse alphaNumericCombincations(String digits, int startIndex, int maxResult);
}
