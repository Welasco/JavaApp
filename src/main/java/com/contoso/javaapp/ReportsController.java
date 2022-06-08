package com.contoso.javaapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ReportsController {
    int reportnum = 1;
    int reportCount = Runtime.getRuntime().availableProcessors();
    @RequestMapping(value="api/reports", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> reports() {
        String response = "{ \"response\": \"Report generated in background\"}";

        if (reportCount >= 3) {
            int reportloop = reportCount-2;
            
            for (int report = 1; report <= reportloop; report++) {
                Thread reportT = new Thread(generateChildReport());
                reportT.start();
            }            
        }

        for (int loop = 1; loop < 20; loop++) {
            for (int index = 1; index < 2147483647; index++) {
                reportnum = reportnum * index;
            }
        }      
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private Runnable generateChildReport(){
        Runnable reportr = new Runnable(){
            @Override
            public void run() {
                for (int loop = 1; loop < 10; loop++) {
                    for (int index = 1; index < 2147483647; index++) {
                        reportnum = reportnum * index;
                    }
                }
            }
        };
        return reportr;
    }
}