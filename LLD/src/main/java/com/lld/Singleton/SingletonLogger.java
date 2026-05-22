package com.lld.Singleton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SingletonLogger {

    protected enum Level {
            DEBUG,
            INFO,
            WARN,
            ERROR
    }

    private volatile Level logLevel = Level.INFO;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //Singleton pattern - constructor is private
    private SingletonLogger(){
    };

    private static class Holder{
        static final SingletonLogger Instance = new SingletonLogger();
    }

    public static SingletonLogger getInstance(){
        return Holder.Instance;
    }

    public synchronized void setLogLevel(Level level){
           this.logLevel = level;
    }

    public void debug(String message){
        log(message, Level.DEBUG);
    }
    public void info(String message){
        log(message, Level.INFO);
    }
    public void warn(String message){
        log(message, Level.WARN);
    }
    public void error(String message){
        log(message, Level.ERROR);
    }

    private String getDateTime(){
        return formatter.format(LocalDateTime.now());
    }

    private void log(String message, Level level){
        if(logLevel.ordinal() <= level.ordinal()){
                System.out.printf("[%s] [%s] %s\n",getDateTime(), level, message);
        }
    }
}
