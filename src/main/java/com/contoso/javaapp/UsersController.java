package com.contoso.javaapp;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Vector;
import java.util.Random;

@RestController
public class UsersController {
    ArrayList<ArrayList<Integer>> userslist2 = new ArrayList<ArrayList<Integer>>();    
    Vector<int[]> userslist = new Vector<int[]>();

    @RequestMapping(value="api/users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> users() {
        Random rand = new Random();
        String response = "{ \"response\": \"User Added Num: " + (rand.nextInt(150)) + "\"}";
        int userID = 200000 * 1024;
        int[] user = generateid(userID);
        userslist.add(user);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private int[] generateid(int userID){
        int numbers = userID / 8;
        int[] arr = new int[numbers];
        for (int i = 0; i < numbers; i++) {
            arr[i] = i;
        }
        return arr;
    }
}