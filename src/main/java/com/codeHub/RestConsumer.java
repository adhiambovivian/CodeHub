package com.codeHub;


import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;


public class RestConsumer {
    public static void sendSurvey() {

        final String url = "http://api.msurvey.co.ke:8083/v1/surveys/{surveyId}/panel";
        //final String url = "http://api.msurvey.co.ke:8083/v1/surveys";
        PanelS panelS = new PanelS();
        panelS.setPanelId(2);
        panelS.setTarget(6);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        String auth = "bitnami" + ":" + "vivian2020";
        byte[] authentication = auth.getBytes();
        byte[] base64Authentication = Base64Utils.encode(authentication);
        String baseCredential = new String(base64Authentication);
        header.add(HttpHeaders.AUTHORIZATION, "Basic " + baseCredential);
        //header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
       header.setContentType(MediaType.APPLICATION_JSON);
       //#######################//
//        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//        params.add("panelId","200");
//        params.add("target","2");
//
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(params, header);
//        ResponseEntity<String> response = restTemplate.postForEntity( url,entity, String.class,1 );
//        ////ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class, 1);
//        System.out.println("####" + response.toString());
//#############################//
//        String requestJson = "{\"panelId\":2,\"target\":2}";
//        HttpEntity<String> entity = new HttpEntity<String>(requestJson,header);
//        String response = restTemplate.postForObject(url, entity, String.class,2);
//        System.out.println("####" + response.toString());
        ////###############################////
        JSONObject request = new JSONObject();
        request.put("panelId", 2);
        request.put("target", 2);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), header);
        ResponseEntity<String> response = restTemplate
                .exchange(url, HttpMethod.POST, entity, String.class,2);
        System.out.println("####" + response.toString());


    }

}
