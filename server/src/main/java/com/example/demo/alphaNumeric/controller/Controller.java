package com.example.demo.alphaNumeric.controller;

import com.example.demo.alphaNumeric.service.CombinationService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.alphaNumeric.domain.CombinationResponse;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {
    private CombinationService combinationService;
    public Controller(CombinationService combinationService) {
        this.combinationService = combinationService;
    }

    /**
     * Controller for route GET /api/combinations/{phoneNumber}>?startIndex={startIndex}&maxResult={maxResult}
     * @param phoneNumber
     * @param startIndex
     * @param maxResult
     * @return
     */
    //Allowed CorssOrigin headers from localhost:4200 so that it can be called from local npm application.
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/combinations/{phoneNumber}")
    public CombinationResponse getAccount(@PathVariable String phoneNumber, @RequestParam Optional<Integer> startIndex,
                                          @RequestParam Optional<Integer> maxResult) {

        return combinationService.alphaNumericCombincations(phoneNumber, startIndex.orElse(0), maxResult.orElse(50));
    }

}
