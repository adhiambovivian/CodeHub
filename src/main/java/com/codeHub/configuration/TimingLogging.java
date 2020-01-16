package com.codeHub.configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimingLogging {
    //Implements a singleton logger instance
    private static final TimingLogging instance = new TimingLogging();
    private static File logFile;
    //Retrieve the execution directory. Note that this is whereever this process was launched
    public String logname = "TimingLogger";
    protected String env = System.getProperty("user.dir");

    private TimingLogging() {
        if (instance != null) {
            //Prevent Reflection
            throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
        }
        this.createLogFile();
    }

    public static TimingLogging getInstance() {
        return instance;
    }

    public static TimingLogging getInstance(String withName) {
        instance.logname = withName;
        instance.createLogFile();
        return instance;
    }

    public static void log(String message) {
        try {
            FileWriter out = new FileWriter(TimingLogging.logFile, true);
            out.write(message.toCharArray());
            out.close();
        } catch (IOException e) {
            System.err.println("ERROR: Could not write to log file");
        }
    }

    public void createLogFile() {
        //Determine if a logs directory exists or not.
        File logsFolder = new File(env + '/' + "logs");
        if (!logsFolder.exists()) {
            //Create the directory
            System.err.println("INFO: Creating new logs directory in " + env);
            logsFolder.mkdir();

        }

        //Get the current date and time
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        //Create the name of the file from the path and current time
        logname = logname + '-' + dateFormat.format(cal.getTime()) + ".log";
        TimingLogging.logFile = new File(logsFolder.getName(), logname);
        try {
            if (logFile.createNewFile()) {
                //New file made
                System.err.println("INFO: Creating new log file");
            }
        } catch (IOException e) {
            System.err.println("ERROR: Cannot create log file");
            System.exit(1);
        }
    }
}
