package com.contoso.javaapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketingController {
    private Integer marketingglobal = 0;
    @RequestMapping(value="api/marketing", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> marketing() {
        String response = "{\"response\":\"Response OK\"}";
        marketingglobal++;
        Integer marketingmath = marketingglobal%2;
        if (marketingmath == 0) {
            return new ResponseEntity<>(response,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}