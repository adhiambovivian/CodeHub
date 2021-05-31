/* Copyright (C)2021  Vivian */
package com.codeHub;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Loggs {

    PatternLayout layout = new PatternLayout();
    private static org.apache.log4j.Logger log = Logger.getLogger(Loggs.class.getName());

    // String pattern="%-7p %d [%t] %c %x - %m%n";
    //    // creates console appender
    //    ConsoleAppender consoleAppender = new ConsoleAppender();
    //        consoleAppender.setLayout(layout);
    //        consoleAppender.activateOptions();
    //
    //    // creates file appender
    //    FileAppender fileAppender = new FileAppender();
    //        fileAppender.setFile("applog3.txt");
    //        fileAppender.setLayout(layout);
    //        fileAppender.activateOptions();
    //
    //
    //    // configures the root logger
    //    Logger rootLogger = Logger.getRootLogger();
    //        rootLogger.setLevel(Level.DEBUG);
    //        rootLogger.addAppender(consoleAppender);
    //        rootLogger.addAppender(fileAppender);
    //
    //    // creates a custom logger and log messages
    //    Logger logger = Logger.getLogger(ProgrammaticLog4jExample.class);
    //        logger.debug("this is a debug log message");
    //        logger.info("this is a information log message");
    //        logger.warn("this is a warning log message");
    public static void loggs() {
        log.info("yess");
    }
}
