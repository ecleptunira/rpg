package project.rpg.utils;

import project.rpg.characters.Character;
import java.util.Scanner;

/**
 * Utility class for handling game messages, battle logs,
 * colored outputs, and UI-like information in the console.
 * 
 * This class does not perform any calculations.
 * It only displays formatted information to the player.
 */
public class Information {
    private static final Scanner SC = new Scanner(System.in);
    private static String lineBreak2 = "======================================";
    private static String lineBreak1 = "--------------------------------------";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";    // Death and missed
    public static final String GREEN = "\u001B[32m";  // Health and damage
    public static final String CYAN = "\u001B[36m";   // Life
    public static final String BLUE = "\u001B[34m";   // Defender
    public static final String YELLOW = "\u001B[33m"; // Attacker
    public static final String PURPLE = "\u001B[35m"; // Hability


    /**
     * Display the start message for a battle
     */
    public static void battleStart() {
        Logger.capturePrint
            ("╔===========================================╗\n" + 
             "║               BATTLE START!               ║\n" +
             "╚===========================================╝");
    }

    /**
     * Display the end message for a battle
     * @param winnerName name of the winner or "DRAW" if has no winner
     */
    public static void battleOver(String winnerName) {
        String winnerNameUpper = winnerName.toUpperCase();
        String winnerPhrase = "║        " + winnerNameUpper + " WON THE BATTLE! " + "        ║";
        if (winnerNameUpper.equals("DRAW")) {
            winnerPhrase = "║               IT'S A DRAW!                ║";
        }
        int totalLength = winnerPhrase.length();
        Logger.capturePrint(
            "╔" + "═".repeat(totalLength - 2) + "╗\n" + 
            winnerPhrase + "\n" + 
            "╚" + "═".repeat(totalLength - 2) + "╝");
    }

    /**
     * Clear the console screen on windows systems
     */
    public static void limparTela() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            System.err.println("Erro ao limpar o terminal: " + e.getMessage());
        }
    }

    /**
     * Pause the game until user press enter
     */
    public static void pressEnterToContinue() {
        System.out.println("\nPress" + YELLOW + " Enter " + RESET + "to continue...");
        SC.nextLine();
    }

    /**
     * Display a message when a critical hit occurs
     * @param attacker character who is attacking
     * @param hability name of the hability used
     * @param damageDealt amount of the damage dealt
     * @param defender character who is defending
     */
    public static void criticalHit(Character attacker, String hability, int damageDealt, Character defender) {
        Logger.capturePrint(
            lineBreak1 + "\n" +

            RED + ">>> CRITICAL HIT! <<<" + RESET + "\n" 

            + YELLOW + attacker.getName() + RESET + " used " 
            + PURPLE + hability + RESET + " on " 
            + BLUE + defender.getName() + RESET + "!\n"

            + PURPLE + hability + RESET + " unleashed a " 
            + RED + "CRITICAL power of " + damageDealt + RESET + " points!\n" + 

            lineBreak1);
    }

    /**
     * Display the damage status after an normal attack
     * @param attacker character who is attacking
     * @param hability name of the hability used
     * @param damageDealt amount of the damage dealt
     * @param defender character who is defending
     */
    public static void damageStatus(Character attacker,String hability, int damageDealt, Character defender) {
        Logger.capturePrint(
            lineBreak1 + "\n" +

            YELLOW + attacker.getName() + RESET + " used " 
            + PURPLE + hability + RESET + " on " 
            + BLUE + defender.getName() + RESET + "!\n" 

            + PURPLE + hability + RESET + " hit with a power of " 
            + GREEN + damageDealt + RESET + " points!\n" +
            lineBreak1
        );
    }

    /**
     * Overloaded method to display the damage status when no hability name is provided
     */
    public static void damageStatus(Character attacker,int damageDealt, Character defender) {
        damageStatus(attacker, "attack",damageDealt, defender);
    }

    /**
     * Display a message when an attack misses
     */
    public static void damageMissed(Character attacker, String hability, Character defender) {
        Logger.capturePrint
        (lineBreak1 + "\n" 

        + YELLOW + attacker.getName() + RESET + " used " 
        + PURPLE + hability + RESET + "!"+

        "\n" + lineBreak1 + "\n" + 

        PURPLE + hability + RESET + 
        " missed! No damage dealt to " 
        + BLUE + defender.getName() + RESET + ".\n" + 

        lineBreak1
        );
    }

    /**
     * Overloaded method to display the damage missed when no hability name is provided
     */
    public static void damageMissed(Character attacker, Character defender) {
        damageMissed(attacker, "attack", defender);
    }

    /**
     * Display a message when an attack is out of range
     */
    public static void outOfRange(String attacker, String defender) {
        Logger.capturePrint(
            lineBreak2 + "\n" +
            
            RED + "Out of range! " + RESET + attacker + " cannot reach " + defender + ".\n" +

            lineBreak2
            );
    }

    /**
     * Display a message when a character is defeated
     * @param character character who has been defeated
     */
    public static void characterDead(String dead) {
        Logger.capturePrint(
            lineBreak2 + "\n" +
            RED + dead + RESET + 
            " has been "+ RED +"defeated!" + RESET + "\n" +
            lineBreak2);
    }

    /**
     * Display character healt bars in the console
     * @param character character health bar to be shown
     */
    public static void showHealth(Character character){
        int totalBars = 20;
        int filledBars = (int) Math.round(((double) character.getLife() / character.getMaxLife()) * totalBars);
        int emptyBars = totalBars - filledBars;
        String healthBar = "[" + GREEN + "█".repeat(filledBars) + RED + "-".repeat(emptyBars) + RESET + "]";
        Logger.capturePrint(YELLOW + character.getName() + RESET + " Health: " + healthBar + " "+ character.getLife() + "/" + character.getMaxLife() + " HP");
    }
}
