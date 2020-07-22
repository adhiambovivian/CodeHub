package com.codeHub.service;

import sun.misc.BASE64Decoder;

import java.util.Base64;

public class CryptographyService {
    public static void crytopgraphyCmd(){
        base64encoding("Such a very sunny day today.");
        encodeUrl("");
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

    public static void encodeUrl(String url){
        Base64.Encoder encoder=Base64.getUrlEncoder();
        String encodedUrl=encoder.encodeToString("https://stackoverflow.com/tour".getBytes());
        System.out.println("Encoded url: "+encodedUrl);
        //decode
        Base64.Decoder decoder=Base64.getUrlDecoder();
        String decodedUrl=new String(decoder.decode(encodedUrl));
        System.out.println("Decoded string: "+decodedUrl);
    }
}
