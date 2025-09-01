package project.rpg;

import java.util.Scanner;

public class Information {
    private static final Scanner SC = new Scanner(System.in);
    private static String lineBreak2 = "======================================";
    private static String lineBreak1 = "-------------------";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public static void showCharacterInfo(Character character) {
        System.out.println(character.getName() + " Info:");
        System.out.println(
            "Name: " + character.getName() + 
            " | Damage: " + character.getDamage() + 
            " | Life: " + character.getLife() + 
            " | Position: (" + character.getPosX() + ", " + character.getPosY() + ")");
    }

    public static void limparTela() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Limpa o terminal no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            // Lidar com a exceção, por exemplo, imprimir no console
            System.err.println("Erro ao limpar o terminal: " + e.getMessage());
        }
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        SC.nextLine();
    }

    public static void damageStatus(Character attacker,String hability, int damageDealt, Character defender) {
        System.out.println
            (lineBreak1 + "\n" 
            + attacker.getName() + " used " + hability + " on " + defender.getName() + "!"+
            "\n" + lineBreak1);

        System.out.println(hability + " hit with a power of " + damageDealt + " points! \n" + 
        defender.getName() + " now has " + defender.getLife() + " points of life.");

        System.out.println(lineBreak2);
    }

    public static void damageStatus(Character attacker,int damageDealt, Character defender) {
        damageStatus(attacker, "attack",damageDealt, defender);
    }

    public static void damageMissed(Character attacker, String hability, Character defender) {
        System.out.println
            (lineBreak2 + "\n" 
            + attacker.getName() + " used " + hability + "!"+
            "\n" + lineBreak2);

        System.out.println(hability + " missed! No damage dealt to " + defender.getName() + ".");

        System.out.println(lineBreak2);
    }

    public static void characterDead(Character character) {
        System.out.println(RED + character.getName() + RESET + " has been defeated!");
        System.out.println(lineBreak2);
    }
}
