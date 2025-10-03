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
        System.out.println("╔===========================================╗");
        System.out.println("║               BATTLE START!               ║");
        System.out.println("╚===========================================╝");
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
        System.out.println("╔" + "═".repeat(totalLength - 2) + "╗");
        System.out.println(winnerPhrase);
        System.out.println("╚" + "═".repeat(totalLength - 2) + "╝");
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
        System.out.println(
            lineBreak1 + "\n" +
            RED + ">>> CRITICAL HIT! <<<" + RESET + "\n" +
            YELLOW + attacker.getName() + RESET + " used " 
            + PURPLE + hability + RESET + " on " 
            + BLUE + defender.getName() + RESET + "!\n"
        );

        System.out.println(
            PURPLE + hability + RESET + " unleashed a " 
            + RED + "CRITICAL power of " + damageDealt + RESET + " points!"
        );
        System.out.println(lineBreak1);
        }

    /**
     * Display the damage status after an normal attack
     * @param attacker character who is attacking
     * @param hability name of the hability used
     * @param damageDealt amount of the damage dealt
     * @param defender character who is defending
     */
    public static void damageStatus(Character attacker,String hability, int damageDealt, Character defender) {
        System.out.println(
            lineBreak1 + "\n" +
            YELLOW + attacker.getName() + RESET + " used " 
            + PURPLE + hability + RESET + " on " 
            + BLUE + defender.getName() + RESET + "!\n"
        );
        System.out.println(
            PURPLE + hability + RESET + " hit with a power of " 
            + GREEN + damageDealt + RESET + " points!"
        );
        System.out.println(lineBreak1);
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
        System.out.println
        (lineBreak1 + "\n" 
        + YELLOW + attacker.getName() + RESET + " used " 
        + PURPLE + hability + RESET + "!"+
        "\n" + lineBreak1);
        
        System.out.println(PURPLE + hability + RESET + 
        " missed! No damage dealt to " 
        + BLUE + defender.getName() + RESET + ".");
        
        System.out.println(lineBreak1);
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
    public static void outOfRange(Character attacker, Character defender) {
        System.out.println(RED + "Out of range!" + RESET + 
        " " + attacker.getName() + " cannot reach " + defender.getName() + ".");
        System.out.println(lineBreak2);
    }

    /**
     * Display a message when a character is defeated
     * @param character character who has been defeated
     */
    public static void characterDead(Character character) {
        System.out.println("\n" + RED + character.getName() + RESET + 
        " has been "+ RED +"defeated!" + RESET);
        System.out.println(lineBreak2);
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
        System.out.println(YELLOW + character.getName() + RESET + " Health: " + healthBar + " "+ character.getLife() + "/" + character.getMaxLife() + " HP");
    }
}
