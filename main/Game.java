package project.rpg.main;

import project.rpg.characters.classes.jobs.*;

import java.util.Scanner;

import project.rpg.characters.Player;
import project.rpg.characters.classes.classes.*;
import project.rpg.utils.Information;
import project.rpg.utils.Logger;
import project.rpg.utils.mainAssistant;
import static project.rpg.utils.mainAssistant.waitSeconds;


public class Game {
    public static void main(String[] args) {
        Logger.clearLog();
        Scanner sc = new Scanner(System.in);

        // System.out.println("Bem-vindo ao RPG!");
        // System.out.println("Para iniciar a aventura, é necessário que você escolha a sua classe!");
        // Job job = mainAssistant.chooseJob(sc);
        // System.out.println("Qual será o nome do seu personagem?");
        // Player player = new Player(sc.nextLine(), job);
        Player player = new Player("Legolas", new RangerJob());
        Information.clearScreen();

        Monster monster;
        int wave = 0;
        while(player.isAlive()){
            player.restoreLifeToFull();
            wave++;
            System.out.println("\nWave " + wave);
            waitSeconds(1); // usar depois para ajustar o tempo entre as ações
            System.out.println();
            Logger.capturePrint("1 - Attack | 2 - Character info | 3 - Flee");
            Logger.capturePrint("What do tou want to do? ");
            System.out.print("--- ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                Logger.capturePrint("Invalid input!");
                continue;
            }

            switch (choice){
                case 1:
                    Information.battleStart();
                    monster = mainAssistant.generateEnemy(wave, player);
                    Logger.capturePrint("A " + monster.getName() + " appeared!");
                    Logger.capturePrint("Prepare for battle!");
                    mainAssistant.startBattle(player, monster, sc);
                    continue;

                case 2:
                    Information.showCharacterInfo(player.toString());
                    continue;

                case 3:
                    Logger.capturePrint("You fled from the battle!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
        sc.close();
    }
}