package com.codeHub.models;

import java.sql.Date;
    public class Survey {
        public enum surveyChoiceListing {
            NUMERIC,
            ALPHABETIC;
        }
        public enum surveyObjective {
            AOD,
            CS,
            BASIC;
        }
        public enum statusSurvey {
            ACTIVE,
            DELETED,
            INACTIVE,
            DRAFT;
        }

        private long id;
        private surveyObjective objective;
        private long countryId;
        private long userId;
        private long accountId;
        private Date createDate;
        private String title;
        private String description;
        private Date startDate;
        private String joincode;
        private long maxRespondents;
        private statusSurvey status;
        private boolean retakable;
        private int retakeLimit;
        private long retakeInterval;
        private int defaultIncentiveAmount;
        private Date lastModified;
        private String shortUrl;
        private Date surveyStartTime;
        private long surveyTimeoutInterval;
        private long participantTimeoutInterval;
        private surveyChoiceListing choiceListing;
        private long languageId;
        private boolean numberingQuestion;
        private Date lastActivity;
        private boolean showCommidInDownload;
        private boolean crowdControl;
        private String hashCode;
        private boolean refer;
        private int  referralAmount;
        private int maxReferrals;
        private boolean isTemplate;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public surveyObjective getObjective() {
            return objective;
        }

        public void setObjective(surveyObjective objective) {
            this.objective = objective;
        }

        public long getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public String getJoincode() {
            return joincode;
        }

        public void setJoincode(String joincode) {
            this.joincode = joincode;
        }

        public statusSurvey getStatus() {
            return status;
        }

        public void setStatus(statusSurvey status) {
            this.status = status;
        }

        public boolean isRetakable() {
            return retakable;
        }

        public void setRetakable(boolean retakable) {
            this.retakable = retakable;
        }

        public int getRetakeLimit() {
            return retakeLimit;
        }

        public void setRetakeLimit(int retakeLimit) {
            this.retakeLimit = retakeLimit;
        }

        public long getRetakeInterval() {
            return retakeInterval;
        }

        public void setRetakeInterval(long retakeInterval) {
            this.retakeInterval = retakeInterval;
        }

        public int getDefaultIncentiveAmount() {
            return defaultIncentiveAmount;
        }

        public void setDefaultIncentiveAmount(int defaultIncentiveAmount) {
            this.defaultIncentiveAmount = defaultIncentiveAmount;
        }

        public Date getLastModified() {
            return lastModified;
        }

        public void setLastModified(Date lastModified) {
            this.lastModified = lastModified;
        }

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }

        public Date getSurveyStartTime() {
            return surveyStartTime;
        }

        public void setSurveyStartTime(Date surveyStartTime) {
            this.surveyStartTime = surveyStartTime;
        }

        public long getSurveyTimeoutInterval() {
            return surveyTimeoutInterval;
        }

        public void setSurveyTimeoutInterval(long surveyTimeoutInterval) {
            this.surveyTimeoutInterval = surveyTimeoutInterval;
        }

        public long getParticipantTimeoutInterval() {
            return participantTimeoutInterval;
        }

        public void setParticipantTimeoutInterval(long participantTimeoutInterval) {
            this.participantTimeoutInterval = participantTimeoutInterval;
        }

        public surveyChoiceListing getChoiceListing() {
            return choiceListing;
        }

        public void setChoiceListing(surveyChoiceListing choiceListing) {
            this.choiceListing = choiceListing;
        }

        public long getLanguageId() {
            return languageId;
        }

        public void setLanguageId(long languageId) {
            this.languageId = languageId;
        }

        public boolean isNumberingQuestion() {
            return numberingQuestion;
        }

        public void setNumberingQuestion(boolean numberingQuestion) {
            this.numberingQuestion = numberingQuestion;
        }

        public Date getLastActivity() {
            return lastActivity;
        }

        public void setLastActivity(Date lastActivity) {
            this.lastActivity = lastActivity;
        }

        public boolean isShowCommidInDownload() {
            return showCommidInDownload;
        }

        public void setShowCommidInDownload(boolean showCommidInDownload) {
            this.showCommidInDownload = showCommidInDownload;
        }

        public boolean isCrowdControl() {
            return crowdControl;
        }

        public void setCrowdControl(boolean crowdControl) {
            this.crowdControl = crowdControl;
        }

        public String getHashCode() {
            return hashCode;
        }

        public void setHashCode(String hashCode) {
            this.hashCode = hashCode;
        }

        public boolean isRefer() {
            return refer;
        }

        public void setRefer(boolean refer) {
            this.refer = refer;
        }

        public int getMaxReferrals() {
            return maxReferrals;
        }

        public long getMaxRespondents() {
            return maxRespondents;
        }

        public void setMaxRespondents(long maxRespondents) {
            this.maxRespondents = maxRespondents;
        }

        public int getReferralAmount() {
            return referralAmount;
        }

        public void setReferralAmount(int referralAmount) {
            this.referralAmount = referralAmount;
        }
        public void setMaxReferrals(int maxReferrals) {
            this.maxReferrals = maxReferrals;
        }

        public boolean isTemplate() {
            return isTemplate;
        }

        public void setTemplate(boolean template)
        {
            isTemplate = template;
        }

        public Survey(surveyObjective objective, String title, String description, String joincode, long maxRespondents, statusSurvey status, boolean retakable, int retakeLimit, long retakeInterval, int defaultIncentiveAmount, String shortUrl, long surveyTimeoutInterval, long participantTimeoutInterval, surveyChoiceListing choiceListing, boolean numberingQuestion, String hashCode, boolean refer, int referralAmount, int maxReferrals) {
            this.objective = objective;
            this.title = title;
            this.description = description;
            this.joincode = joincode;
            this.maxRespondents = maxRespondents;
            this.status = status;
            this.retakable = retakable;
            this.retakeLimit = retakeLimit;
            this.retakeInterval = retakeInterval;
            this.defaultIncentiveAmount = defaultIncentiveAmount;
            this.shortUrl = shortUrl;
            this.surveyTimeoutInterval = surveyTimeoutInterval;
            this.participantTimeoutInterval = participantTimeoutInterval;
            this.choiceListing = choiceListing;
            this.numberingQuestion = numberingQuestion;
            this.hashCode = hashCode;
            this.refer = refer;
            this.referralAmount = referralAmount;
            this.maxReferrals = maxReferrals;
        }

        public Survey() {
        }

        @Override
        public String toString() {
            return "Survey{" +
                    "id=" + id +
                    ", objective='" + objective + '\'' +
                    ", countryId=" + countryId +
                    ", userId=" + userId +
                    ", accountId=" + accountId +
                    ", createDate='" + createDate + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", startDate='" + startDate + '\'' +
                    ", joincode='" + joincode + '\'' +
                    ", maxRespondents=" + maxRespondents +
                    ", status='" + status + '\'' +
                    ", retakable=" + retakable +
                    ", retakeLimit=" + retakeLimit +
                    ", retakeInterval=" + retakeInterval +
                    ", defaultIncentiveAmount=" + defaultIncentiveAmount +
                    ", lastModified=" + lastModified +
                    ", shortUrl='" + shortUrl + '\'' +
                    ", surveyStartTime=" + surveyStartTime +
                    ", surveyTimeoutInterval=" + surveyTimeoutInterval +
                    ", participantTimeoutInterval=" + participantTimeoutInterval +
                    ", choiceListing='" + choiceListing + '\'' +
                    ", languageId='" + languageId + '\'' +
                    ", numberingQuestion=" + numberingQuestion +
                    ", lastActivity=" + lastActivity +
                    ", showCommidInDownload=" + showCommidInDownload +
                    ", crowdControl=" + crowdControl +
                    ", hashCode='" + hashCode + '\'' +
                    ", refer=" + refer +
                    ", referralAmount=" + referralAmount +
                    ", maxReferrals=" + maxReferrals +
                    ", isTemplate=" + isTemplate +
                    '}';
        }
    }

