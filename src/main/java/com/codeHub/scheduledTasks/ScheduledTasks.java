package com.codeHub.scheduledTasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

}
