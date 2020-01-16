package com.codeHub.scheduledTasks;

import com.codeHub.IncentiveAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.logging.Logger;

@Component
public class ScheduledTasks {
    private static final Logger logger = Logger.getLogger(ScheduledTasks.class.getName());

    @Autowired
    private DataSource dataSource;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void fetchScheduledTaks(){
        //reminders, surveys,reset incentives, timeout surveys, timeout participants, send re-invites, scheduled surveys
    }

    //@Scheduled(fixedRate = 1000)
    public void resetLineTopupBalance() {
        IncentiveAccount incentiveAccount = new IncentiveAccount();
        try {
            String query = "UPDATE incentive_account SET balance=:balance WHERE type=:type::incentive_account_type";
            SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("balance",0).addValue("type", IncentiveAccount.incentiveAccountType.LINE.toString());
            int status=namedParameterJdbcTemplate.update(query,sqlParameterSource);
            if (status!=0){
                System.out.println("#########Nothing to update on incentive lines balance.");
            }
            else {
                logger.info("********** Reset lines balance ************");
            }

        } catch (Exception e) {
            System.out.println("$$$$$$$$$$$$Failed to update incentives lines balance."+e);

        }

    }

////
//    public void timeoutSurveys(){
//        long currentTimeMillis=System.currentTimeMillis();
//        String query = "SELECT * FROM surveys WHERE status=:survey_status::survey_status";
//        SqlParameterSource sqlParameterSource=new MapSqlParameterSource().addValue("survey_status",0);
//        List<Survey>
//        int status=namedParameterJdbcTemplate.query(query,sqlParameterSource);
//        //get (now-lastactivity) in milli== or >=surveytimeout:
//        //survey.status=off
//
//    }
//    public void timeoutActivePendingParticipants(){
////get now-surcreatedate  == or >= partivipant timeout
//        //insert to sceduled tasks
//    }
//
//    public void sendSurveyReinvites(){
//        //get pending participants
//        //get re-invite msg
//                //update scheduled task
//        //update outgoing
//        //put to queue: telco specific
//
//    }
//
//    public void sendSurveyReminders(){
//
//    }
//
//    public void sendScheduledSurveys(){
//        //get surveys
//        //call sendout api
//        //if 200
//        //update schedule
//        //else
//        //retry else log fatal
//
//
//    }


}
