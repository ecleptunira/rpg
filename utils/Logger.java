package project.rpg.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "project/rpg/utils/logs/game_log.txt";
    private static final boolean ENABLE_CONSOLE = false;
    private static final boolean ENABLE_FORMAT = false;
    private static final boolean ENABLE_FILE = true;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logMessage = "[" + timestamp + "] \n" + message;

        if (ENABLE_CONSOLE) {
            System.out.println(message);
        }

        if (ENABLE_FILE) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                if(ENABLE_FORMAT){
                    writer.println(logMessage);
                } else{
                    writer.println(message);
                }

            } catch (IOException e) {
                System.err.println("Error writing log: " + e.getMessage());
            }
        }
    }
    
    public static void capturePrint(String message) {
        System.out.println(message);
        log(message.replaceAll("\\u001B\\[[;\\d]*m", ""));
    }

    public static void info(String message) {
        log("INFO: " + message);
    }

    public static void debug(String message) {
        log("DEBUG: " + message);
    }

    public static void error(String message) {
        log("ERROR: " + message);
    }
}