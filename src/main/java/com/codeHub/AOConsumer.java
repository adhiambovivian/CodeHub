package com.codeHub;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AOConsumer {
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

    public void processAO(String messageString) {
        System.out.println("Received " + messageString);
        try {
            SurveySendOutObject messageObject = gson.fromJson(messageString, SurveySendOutObject.class);
            String commId = messageObject.getCommId();
            String commDomain = messageObject.getCommDomain();
            long respondentId = messageObject.getRespondentId();
            long surveyId = messageObject.getSurveyId();
            String joincode = messageObject.getJoincode();
            String surveyNumber = messageObject.getSurveyNumber();
            long accountId = messageObject.getAccountId();
            long creatorId = messageObject.getCreatorId();
            long audienceId = messageObject.getAudienceId();
            insert(messageObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(SurveySendOutObject surveySendOutObject) {

        java.sql.Timestamp currentTime = new java.sql.Timestamp(new java.util.Date().getTime());
        String queryString = String.format("INSERT INTO survey_progress(survey_id,participant_id,country_id,status,status_comment,sms_number,referred_by,number_attempted,current_question_id) VALUES (?,?,?,?::survey_progress_status,?,?,?,?,?)");
        jdbcTemplate.update(queryString, surveySendOutObject.getSurveyId(), surveySendOutObject.getRespondentId(), 1, "ACTIVE", "Participant got survey", surveySendOutObject.getSurveyNumber(), 1, 0, 1);


    }


}
