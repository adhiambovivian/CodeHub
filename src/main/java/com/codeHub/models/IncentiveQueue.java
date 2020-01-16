package com.codeHub.models;

import java.math.BigDecimal;
import java.util.Date;

public class IncentiveQueue {

        private long id;
        private long incentiveAccountId;
        private long countryId;
        private Participant participant;
        private Survey survey;
        private long surveyId;
        private long participantId;
        private BigDecimal amount;
        private Date createDate;
        private boolean status;
        private String statusComment;
        private Date timestamp;


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getIncentiveAccountId() {
            return incentiveAccountId;
        }

        public void setIncentiveAccountId(long incentiveAccountId) {
            this.incentiveAccountId = incentiveAccountId;
        }

        public long getCountryId() {
            return countryId;
        }

        public void setCountryId(long countryId) {
            this.countryId = countryId;
        }

        public Participant getParticipant() {
            return participant;
        }

        public void setParticipant(Participant participant) {
            this.participant = participant;
        }

        public Survey getSurvey() {
            return survey;
        }

        public void setSurvey(Survey survey) {
            this.survey= survey;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }



    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
            this.status = status;
        }

        public String getStatusComment() {
            return statusComment;
        }

        public void setStatusComment(String statusComment) {
            this.statusComment = statusComment;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
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

    @Override
        public String toString() {
            return "Compensation{" +
                    "id=" + id +
                    ", incentiveAccountId=" + incentiveAccountId +
                    ", countryId=" + countryId +
                    ", participant=" + participant +
                    ", survey=" + survey +
                    ", amount=" + amount +
                    ", createDate=" + createDate +
                    ", status=" + status +
                    ", statusComment='" + statusComment + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

