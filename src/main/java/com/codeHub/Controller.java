package com.codeHub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
@RequestMapping("/v1")
public class Controller {
    @Autowired
    private IncentivesConsumerWorker incentivesConsumerWorker;

    @RequestMapping(path="/download",method = RequestMethod.GET)
public ResponseEntity<Resource> download(String param) throws IOException{
        File file=new File("/home/adhiambo/banking.sav");
        HttpHeaders headers=new HttpHeaders();
        headers.add("Cache-Control","no-cache,no-store,must-revalidate");
        headers.add("Pragma","no-cache");
        headers.add("Expires","0");
        InputStreamResource inputStreamResource=new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(inputStreamResource);
    }

    @RequestMapping(path="/download/file",method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(String param) throws IOException{
        File file=new File("/home/adhiambo/"+param);
        Path path= Paths.get(file.getAbsolutePath());
        ByteArrayResource resource=new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers=new HttpHeaders();
        headers.add("Cache-Control","no-cache,no-store,must-revalidate");
        headers.add("Pragma","no-cache");
        headers.add("Expires","0");
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }



        @RequestMapping(method = RequestMethod.POST, value = "/incentives", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
        public ResponseEntity<String> testIncentives(){
            long startTime = System.currentTimeMillis();
            String response="Failed";
            HttpStatus httpStatus=null;
            try {
                BigDecimal amount = new BigDecimal(10);
                HashMap<String,Object> data=new HashMap<>();
                for(int i=1;i<=20000000;i++) {
                    data.put("amount",amount);
                    data.put("commDomain", "safaricom");
                    data.put("commId","+254729880726");
                    data.put( "countryId","1");
                    data.put("surveyId",2);
                    data.put("participantId", 1);
                    System.out.println("######## "+data.toString());
                    incentivesConsumerWorker.dump(data);
                }
                httpStatus=HttpStatus.OK;
            } catch (Exception e) {
                System.out.println("Failed. Message:" + e.getMessage());
                httpStatus=HttpStatus.INTERNAL_SERVER_ERROR;
            }

            return new ResponseEntity<String>(response,httpStatus);
        }
    }

