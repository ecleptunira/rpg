package project.rpg;

import java.util.Scanner;

public class Information {
    private static final Scanner SC = new Scanner(System.in);

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
}
