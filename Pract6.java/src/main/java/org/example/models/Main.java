package org.example.models;

import models.CaptainModel;
import models.EmployeeModel;
import models.Log;
import models.PrisonerModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.*;


public class Main {
//    static Log mainLog;
//    static {
//        try{
//            mainLog = new Log("MainLogger.log");
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private static final Logger log = Logger.getLogger(Main.class.getName());

    static ArrayList<ArrayList<Object>> employees = new ArrayList<>(); //1 - Employee number, 2 - Employee access level
    static ArrayList<ArrayList<Object>> heretics = new ArrayList<>(); //1 - heretic id, 2 - faith percentage
    static ArrayList<ArrayList<Object>> captainOrders = new ArrayList<>(); //1 - directive number, 2 - directive type(1 - terminate, 2 - read sermon to, 3 - clean), 3 - directive parameters, 4 - directive text
    static PrisonerModel prisoner;

    static {
        try {
            prisoner = new PrisonerModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static CaptainModel captain;

    static {
        try {
            captain = new CaptainModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static EmployeeModel employee;

    static {
        try {
            employee = new EmployeeModel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler("Main.log", true);
        log.addHandler(fileHandler);
        log.setLevel(Level.INFO);
        SimpleFormatter formatter = new SimpleFormatter(){
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";
            @Override
            public synchronized  String format(LogRecord lr){
                return String.format(
                        format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getName(),
                        lr.getMessage()
                );
            }
        };
        fileHandler.setFormatter(formatter);


        log.info("Программа успешно запущена");

        captain.RaiseEmployee(employees);

        System.out.println(captainOrders);
    }
}