package com.contoso.javaapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class AdminController {

    Process proc;
    long pid = 0;
    
    @RequestMapping(value="api/admin/connect", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> connect() {
        String response = "{ \"response\": \"Connect - Nothing happen\"}";
        Path localPath = Paths.get("").toAbsolutePath().getParent();
        Path dbconnections = Paths.get(localPath.toString(),"NodeJSApp","tools","dbconnections.js");
        String command = "node " + dbconnections + " www.bing.com 80 1000";
        long currentPid = pid;
        if (currentPid == 0) {
            try {
                Runtime run  = Runtime.getRuntime(); 
                proc = run.exec(command);
                pid = proc.pid();

                response = "{ \"response\": \"Database Connections opened\"}";
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            catch (Exception e) 
            { 
                e.printStackTrace(); 
            }
        } else {
            response = "{ \"response\": \"Database Connections already opened\"}";
            return new ResponseEntity<>(response,HttpStatus.OK);            
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value="api/admin/disconnect", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> disconnect() {
        String response = "{ \"response\": \"Disconnect - Nothing happen\"}";
        if (pid != 0) {
            try {
                proc.destroy();
                pid = 0;
                response = "{ \"response\": \"Database Connections closed\"}";
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            catch (Exception e) 
            { 
                e.printStackTrace(); 
            }            
        } else {
            response = "{ \"response\": \"Database Connections already closed\"}";
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }    
}