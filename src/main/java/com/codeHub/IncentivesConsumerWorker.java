package com.codeHub;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import java.net.URI;
import java.util.*;
import java.security.SecureRandom;


@Component
public class IncentivesConsumerWorker {


    @Autowired
    private Gson gson;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private String username = "safaricom-mt";
    private String password = "wordpass";
    private String destinationNumber = "140";
    private String queue = "incentives";
    private String queueSaf = "safaricom_engine3";


    public void processIncentives(String incentivesString) {
        Incentives incentives = gson.fromJson(incentivesString, Incentives.class);
        IncentiveAccount incentiveAccount = new IncentiveAccount();
        boolean status=false;
//        try {
//            if(incentives.getCommDomain().equalsIgnoreCase("safaricom")) {
//                status = topUpFromLines(incentives, incentiveAccount);
//            }
//            if(!status)
//                topUpFromEtopup(incentives,incentiveAccount);
//        } catch (Exception e) {
//            e.printStackTrace();
//            topUpFromEtopup(incentives,incentiveAccount);
//        }
        System.out.println("###############");

    }

    public boolean topUpFromLines(Incentives incentives, IncentiveAccount incentiveAccount) {
        System.out.println("Using lines");
        boolean status=false;
        String amount = incentives.getAmount().toString();
        String commId = incentives.getCommId();
        SecureRandom random = new SecureRandom();

        String smsSambaza = amount + "#" + commId.replace("+","").replaceAll("254","0");
        String to = destinationNumber;

        HashMap<Long, IncentiveAccount> incentiveAccountHashMap = new HashMap<Long, IncentiveAccount>();
        incentiveAccountHashMap = cacheIncentivesAccount(getIncentiveAccount());

        long randomIncentiveId = Long.valueOf(random.nextInt(incentiveAccountHashMap.size()));
        incentiveAccount = incentiveAccountHashMap.get(2L);
        String from = incentiveAccount.getName();
        System.out.println("********###******* "+incentiveAccount.toString());
        boolean canSend = incentivesCheck(incentiveAccount, incentives.getAmount());
        if (canSend) {
            SambazaParticipant sambazaParticipant = new SambazaParticipant();
            sambazaParticipant.setFrom(from);
            sambazaParticipant.setTo(to);
            sambazaParticipant.setPassword(password);
            sambazaParticipant.setSmsMessage(smsSambaza);
            try {
                //rabbitTemplate.convertAndSend(queueSaf, gson.toJson(sambazaParticipant));
                List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();

                URIBuilder builder = new URIBuilder();
                builder.setScheme("http");
                builder.setHost("23.21.163.195");
                builder.setPort(81);
                builder.setPath("/cgi-bin/sendsms/");

                HttpClient client = new DefaultHttpClient();
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs
                        .add(new BasicNameValuePair("to", to));
                nameValuePairs.add(new BasicNameValuePair("from", from));
                nameValuePairs.add(new BasicNameValuePair("text",
                        smsSambaza));

                builder.setParameters(nameValuePairs).build();
                URI uri=new URI(builder.toString());
                HttpPost httpPost=new HttpPost(uri);
                System.out.println("Builder.tostring:  ********** "+builder.toString()+" GetUri: #########"+httpPost.getURI());

                HttpResponse response=client.execute(httpPost);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent()));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }

                IncentiveQueue incentiveQueue = new IncentiveQueue();
                incentiveQueue.setAmount(incentives.getAmount());
                incentiveQueue.setStatus(true);
                incentiveQueue.setIncentiveAccountId(incentiveAccount.getId());
                incentiveQueue.setSurveyId(incentives.getSurveyId());
                incentiveQueue.setParticipantId(incentives.getParticipantId());
                incentiveQueue.setStatusComment("Sent");
                incentiveQueue.setCountryId(incentiveAccount.getCountryId());
                System.out.println("######" + incentiveQueue.toString());
                insertIncentiveQueue(incentiveQueue);
               System.out.println("Response $$$$$$$ "+response.toString()+"Builder.tostring:  ********** "+builder.toString()+" GetUri: #########"+httpPost.getURI());

            } catch (Exception e) {
                e.printStackTrace();
                status=false;
            }
        }
        return status;
    }

    public void topUpFromEtopup(Incentives incentives,IncentiveAccount incentiveAccount) {
        System.out.println("Using etopup");
        SafaricomEtopup safaricomEtopup = new SafaricomEtopup();
        BigDecimal convertCents = new BigDecimal(100);
        safaricomEtopup.setAmount(incentives.getAmount().multiply(convertCents).toString());//Amount in cents
        safaricomEtopup.setMsisdn2(incentives.getCommId());

        try {
            JAXBContext jc = JAXBContext.newInstance(SafaricomEtopup.class);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(safaricomEtopup, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_XML);
        String etopupUrl="http://23.21.163.195:8081/topup_send";

        IncentiveQueue incentiveQueue = new IncentiveQueue();
        incentiveQueue.setAmount(incentives.getAmount());

        incentiveQueue.setIncentiveAccountId(incentiveAccount.getId());
        incentiveQueue.setSurveyId(incentives.getSurveyId());
        incentiveQueue.setParticipantId(incentives.getParticipantId());

        incentiveQueue.setCountryId(incentiveAccount.getCountryId());

        try {
            SafaricomEtopupResponse etopup = restTemplate.postForObject(etopupUrl,safaricomEtopup, SafaricomEtopupResponse.class);
            if(etopup.getTxnstatus().equalsIgnoreCase("200")){
                incentiveQueue.setStatus(true);
                incentiveQueue.setStatusComment(etopup.getMessage());
                insertIncentiveQueue(incentiveQueue);
            }else{
                System.out.println("Putting back item to queue. Reason: "+etopup.getMessage()+" status: "+etopup.getTxnstatus());
                rabbitTemplate.convertAndSend(queue, gson.toJson(incentives));
            }
        }catch (ResourceAccessException e){
            e.printStackTrace();
            //put back to queue
            System.out.println("Putting back item to queue.");
            rabbitTemplate.convertAndSend(queue, gson.toJson(incentives));
        }
    }

    public boolean incentivesCheck(IncentiveAccount incentiveAccount, BigDecimal amount) {
        boolean canSendIncentives = false;
        BigDecimal maxAmount = new BigDecimal(100);
        BigDecimal dailyLimit = new BigDecimal(10000);
        BigDecimal totalSent=new BigDecimal(totalSentIncentives(incentiveAccount));

        if (incentiveAccount.getBalance().compareTo(amount)==-1||incentiveAccount.getBalance().compareTo(maxAmount)==-1||totalSent.compareTo(dailyLimit) ==1 || totalSent.compareTo(dailyLimit) ==0) {
            canSendIncentives = false;
        } else {
           canSendIncentives = true;
        }
        return canSendIncentives;
    }

    public List<Map<String, Object>> getIncentiveAccount() {
        try {
            return jdbcTemplate.queryForList(
                    "SELECT id,country_id,name,balance,active,create_date FROM incentive_account WHERE active = true AND balance > 0");
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<Long, IncentiveAccount> cacheIncentivesAccount(List<Map<String, Object>> incentiveAccounts) {
        HashMap<Long, IncentiveAccount> incentiveAccountHashMap = new HashMap<Long, IncentiveAccount>();
        for (Map incentiveAccount : incentiveAccounts) {

            IncentiveAccount compensationAccount = new IncentiveAccount();
            compensationAccount.setId((Integer) incentiveAccount.get("id"));
            compensationAccount.setActive((boolean) incentiveAccount.get("active"));
            float amount = (float) incentiveAccount.get("balance");
            BigDecimal balance = new BigDecimal(amount);
            compensationAccount.setBalance((balance));

            compensationAccount.setCountryId(Long.valueOf(incentiveAccount.get("country_id").toString()));
            compensationAccount.setCreateDate((Date) incentiveAccount.get("create_date"));
            compensationAccount.setName(incentiveAccount.get("name").toString());

            incentiveAccountHashMap.put(compensationAccount.getId(), compensationAccount);
        }

        return incentiveAccountHashMap;
    }

    public int updateIncentivesAccountBalance(long incentiveAccountId) {
        int updatedRecords = 0;
        String sqlQuery = String.format("UPDATE incentive_account SET balance=? WHERE id=?");
        updatedRecords = jdbcTemplate.update(sqlQuery, incentiveAccountId);
        return updatedRecords;
    }

    public int insertIncentiveQueue(IncentiveQueue incentiveQueue) {
        int updatedRecords = 0;
        String sqlQuery = String.format("INSERT INTO incentive_queue (incentive_account_id,country_id,participant_id,survey_id,amount,status,status_comment) VALUES (?,?,?,?,?,?,?)");
        try {
            updatedRecords = jdbcTemplate.update(sqlQuery, incentiveQueue.getIncentiveAccountId(), incentiveQueue.getCountryId(), incentiveQueue.getParticipantId(), incentiveQueue.getSurveyId(), incentiveQueue.getAmount(), incentiveQueue.getStatus(), incentiveQueue.getStatusComment());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Updated ******** " + updatedRecords);
        return updatedRecords;


    }

    public int totalSentIncentives(IncentiveAccount incentiveAccount) {
        String sqlQuery = String.format(
                "SELECT COUNT(*) FROM incentive_queue WHERE incentive_account_id=?");
        return jdbcTemplate.queryForObject(sqlQuery, Integer.class, incentiveAccount.getId());
    }

    public void dump(HashMap<String, Object> incentives) {
        String incentivesJson = gson.toJson(incentives);
        try {
            rabbitTemplate.convertAndSend(queue, incentivesJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
