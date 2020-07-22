package com.codeHub.service;

import java.util.Base64;

public class CryptographyService {
    public static void crytopgraphyCmd(){
        base64encoding("Such a very sunny day today.");
    }

    public static void base64encoding(String rawString){
        Base64.Encoder encoder=Base64.getEncoder();
        String encodedString=encoder.encodeToString(rawString.getBytes());
        System.out.println("Encoded string: "+encodedString);
        //Get decoder
        Base64.Decoder decoder=Base64.getDecoder();
        String decodedStr=new String(decoder.decode(encodedString));
        System.out.println("Decoded string: "+decodedStr);

    }
}
