package com.codeHub;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//import javax.annotation.PostConstruct;
//import javax.sql.DataSource;

public class Incentives {
    private BigDecimal amount;
    private String commDomain;
    private String commId;
    private long countryId;
    private long surveyId;
    private long participantId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCommDomain() {
        return commDomain;
    }

    public void setCommDomain(String commDomain) {
        this.commDomain = commDomain;
    }

    public String getCommId() {
        return commId;
    }

    public void setCommId(String commId) {
        this.commId = commId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(long participantId) {
        this.participantId = participantId;
    }

    public Incentives(BigDecimal amount, String commDomain, String commId, long countryId, long surveyId, long participantId) {
        this.amount = amount;
        this.commDomain = commDomain;
        this.commId = commId;
        this.countryId = countryId;
        this.surveyId = surveyId;
        this.participantId = participantId;
    }

    @Override
    public String toString() {
        return "Incentives{" +
                "amount=" + amount +
                ", commDomain='" + commDomain + '\'' +
                ", commId='" + commId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", surveyId=" + surveyId +
                ", participantId=" + participantId +
                '}';
    }

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Autowired
//    private DataSource dataSource;
//    @PostConstruct
//    private void postConstruct() {
//        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }
//    private static  final Logger logger=Logger.getLogger(Incentives.class.getName());
//
//    public List<IncentiveQueue> getIncentiveQueue(int limit, String status) {
//        try {
//            return jdbcTemplate.query(
//                    "SELECT incentive_queue.id,incentive_queue.incentive_account_id,incentive_queue.country_id,incentive_queue.participant_id," +
//                            "participant.comm_id,participant.comm_domain,incentive.queue.survey_id,incentive_queue.amount,incentive_queue.status " +
//                            "FROM incentive_queue INNER JOIN participant ON incentive_queue.participant_id = participant.id WHERE incentive_queue.status = ? LIMIT ?",
//                    (rs, rowNum) -> {
//
//                        IncentiveQueue incentiveQueue = new IncentiveQueue();
//                        incentiveQueue.setId(rs.getLong("id"));
//                        incentiveQueue.setIncentiveAccountId(rs.getLong("incentive_account_id"));
//                        incentiveQueue.setCountryId(rs.getLong("country_id"));
//                        Survey survey=new Survey();
//                        survey.setId(rs.getLong("survey_id"));
//                        incentiveQueue.setSurvey(survey);
//                        incentiveQueue.setAmount(rs.getBigDecimal("amount"));
//                        incentiveQueue.setStatus(rs.getBoolean("status"));
//                        Participant participant=new Participant();
//                        participant.setCommId(rs.getString("comm_id"));
//                        participant.setCommDomain(rs.getString("comm_domain"));
//                        incentiveQueue.setParticipant(participant);
//
//
//                        return incentiveQueue;
//                    }, status,limit);
//        } catch (EmptyResultDataAccessException e) {
//            logger.log(Level.SEVERE,"Failed to fetch account by username", e);
//            return null;
//        }
//    }


}
