package com.example.demo.alphaNumeric.service;


import com.example.demo.alphaNumeric.domain.CombinationResponse;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Service
public class CombinationServiceImpl implements CombinationService{

    /**
     * This method takes generated possible alphaNumeric combination for a given String.
     * A valid input String would be 7 or 10 digit number (not starting with 0).
     *
     * @param digits - String for which AlphaNumeric combination needs to be generated
     * @param startIndex - start index of desired resul
     * @param maxResult - max result (default value : 50)
     * @return
     */
    public CombinationResponse alphaNumericCombincations(String digits, int startIndex, int maxResult){
        //Using linked list as this will be used as Queue as well.
        LinkedList<String> result = new LinkedList<String>();
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

        //Validate the input
        if (!digits.matches("^[1-9](\\d{6}|\\d{9})$" ))
        {
            return new CombinationResponse("INVALID_INPUT", "This is not a valid input");
        }

        //TODO: Caching can be implmented here so that we don't have to generate combinations again for page change events.

        //Create map for possible combination for each input letter based on keyboard
        String[] keys = new String[] {
            "0", "1", "2abc", "3def", "4ghi","5jkl", "6mno", "7pqrs", "8tuv", "9wxyz"
        };
        //initialize value
        result.offer("");

        //For each input character find possible combinations
        for (int i=0; i< digits.length(); i++){

            //Get numeric value for character at position i.
            int x = Character.getNumericValue(digits.charAt(i));
            //Use linked list as a Queue so that data can be retrieved from it until all the results having length less
            //than i can be polled
            while (result.peek().length()==i){
                //Get top element from the queue.
                String temp = result.poll();

                //For each possible combination for keyboard character append it with current value of top element
                // in the queue
                for(char s: keys[x].toCharArray()){
                    //append and push new string to queue
                    result.offer(temp+s);
                }
            }
        }

        //Set the values in the returning object.
        CombinationResponse response = new CombinationResponse();
        response.setStartIndex(startIndex);
        response.setMaxResult(maxResult);
        response.setTotalCount(result.size());
        response.setResults(filterList(result,startIndex,maxResult));
        return response;
    }

    /**
     * This method filters list based on startIndex and Size.
     * @param input
     * @param startIndex
     * @param size
     * @return
     */
    private List<String> filterList(List<String> input, int startIndex, int size) {
        if(input != null && input.size() >= startIndex){
            if(input.size() > startIndex+size){
                return input.subList(startIndex,startIndex+size);
            } else return input.subList(startIndex, input.size());
        }
            return null;
    }
}
