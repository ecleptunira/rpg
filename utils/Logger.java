package project.rpg.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String LOG_FILE = "project/rpg/utils/logs/game_log.txt";
    private static final boolean ENABLE_CONSOLE = false;
    private static final boolean ENABLE_FORMAT = true;
    private static final boolean ENABLE_FILE = true;
    private static final boolean DEBUG = true;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logMessage = "[" + timestamp + "] " + message;

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
    /**
     * Captures and records a printed message to the log file.
     * <p>
     * If {@code ENABLE_CONSOLE} is disabled, the message will also be printed
     * to the console. ANSI color codes are removed before writing to the log file
     * to ensure clean text formatting.
     * </p>
     *
     * <p><b>Recommended use:</b> For capturing standard system outputs that should also
     * be persisted in the log file.</p>
     *
     * @param message The message to be logged.
     */
    public static void capturePrint(String message) {
        if (!ENABLE_CONSOLE){
            System.out.println(message);
        }
        log(message.replaceAll("\\u001B\\[[;\\d]*m", ""));
    }
    /**
     * Writes an informational message to the log.
     * <p>
     * Used for general game events, such as player actions, calculation results,
     * or overall system status updates.
     * </p>
     *
     * <p><b>Example:</b> "INFO: Arthur successfully attacked the Goblin."</p>
     *
     * @param message The informational message to log.
     */
    public static void info(String message) {
        log("INFO: " + message);
    }

    /**
     * Writes a debug message to the log.
     * <p>
     * Intended for internal diagnostics, variable tracking, and step-by-step
     * process visualization during development and testing.
     * </p>
     *
     * <p><b>Note:</b> Should be used only in development mode. Debug logs can be disabled
     * in production builds to improve performance and reduce log clutter.</p>
     *
     * <p><b>Example:</b> "DEBUG: Critical chance = 20%, Rolled = 17 → Critical Hit!"</p>
     *
     * @param message The debug message to log.
     */
    public static void debug(String message) {
        if (DEBUG){
            log("DEBUG: " + message);
        }
    }

    /**
     * Writes an error message to the log.
     * <p>
     * Used to report critical failures, unexpected behaviors, or exceptions.
     * Can be paired with {@code e.printStackTrace()} for detailed diagnostics.
     * </p>
     *
     * <p><b>Example:</b> "ERROR: Failed to apply damage – null value detected."</p>
     *
     * @param message The error message to log.
     */
    public static void error(String message) {
        log("ERROR: " + message);
    }
}