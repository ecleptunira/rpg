package project.rpg.utils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import project.rpg.characters.classes.classes.*;
import project.rpg.characters.classes.jobs.*;
import project.rpg.characters.classes.jobs.monsters_jobs.*;
import project.rpg.characters.Player;
import project.rpg.characters.skills.*;
import project.rpg.combat.ExperienceCalculator;
import static project.rpg.utils.mainAssistant.waitSeconds;


@SuppressWarnings("unused")
public class mainAssistant {

    public static Job chooseJob(Scanner sc){
        while(true){
            try{
                System.out.println("Qual classe deseja escolher?");
                System.out.println("1 - Heroi | 2 - Assasino | 3 - Arqueiro | 4 - Mago");
                System.out.print("Escolha sua classe: ");
                int choice = Integer.parseInt(sc.nextLine());
                
                switch (choice) {
                    case 1: return new HeroJob ();
                    case 2: return new AssasinJob();
                    case 3: return new RangerJob();
                    case 4: return new MageJob();
                    default: System.out.println("Opção inválida!");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
            }
        }
    }

    public static Monster generateEnemy(int wave, Player p){
        Random rand = new Random();
        int baseLvel = p.getLevel();
        int randFactor = rand.nextInt(5) - 2;
        
        int enemyLevel = Math.max(1, baseLvel + randFactor);
        double randEnemy = Math.random();
        if (wave <= 5 || randEnemy < 0.5){
            Monster monster = new Monster("Goblin", new GoblinJob(), 5);
            monster.forceLevelTo(enemyLevel);
            return monster;
        } else {
            Monster monster = new Monster("High Goblin", new HighGoblinJob(), 7);
            monster.forceLevelTo(enemyLevel);
            return monster;
        }
    }

    public static void startBattle(Player player, Monster monster, Scanner sc){
        while (player.isAlive() && monster.isAlive()){
            int choice;
            System.out.println();
            Logger.capturePrint("1 - Atack | 2 - Enemy stats | 3 - Flee");
            Logger.capturePrint("What you want to do? ");
            System.out.print("--- ");
            monster.setLife(1);
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice){
                case 1:
                    Skill skill = chooseSkill(player, monster, sc);
                    skill.execute(player, monster);
                    break;

                case 2:
                    System.out.println(monster.toString());
                    // Logger.capturePrint("Name: " + monster.getName() + " | Level: " + monster.getLevel() + 
                    // "\n Damage: " + monster.getPhysicalDamage() + 
                    // " | P. defense: " + monster.getPhysicalDefense() + " | M. def: " + monster.getMagicalDefense());
                    // Information.showHealth(monster);
                    continue;

                case 3:
                    double fleeChance = Math.random();
                    if (fleeChance < 0.3){
                        Logger.capturePrint("You fled!");
                        return;
                    } else {
                        Logger.capturePrint("You didn't escape!\n");
                    }
                    break;

                default:
                    System.out.println("Invalid option!");
                    continue;
            }
            waitSeconds(1);
            if (monster.isAlive()){
                double choiceSkill = Math.random();
                List<Skill> monsterSkills = monster.getJob().getSkills();
                if(choiceSkill < 0.5){
                    Skill monsterskill = monsterSkills.get(0);
                    monsterskill.execute(monster, player);
                } else {
                    Skill monsterskill = monsterSkills.get(1);
                    monsterskill.execute(monster, player);
                }
            }
        }

        if (player.isAlive()){
            Information.clearScreen();
            Information.battleOver(player.getName());
            int expGained = ExperienceCalculator.calculateExperience(player, monster, monster.getBaseExp());
            player.gainExperience(expGained);
        } else {
            Information.battleOver(monster.getName());
        }
    }

    public static Skill chooseSkill(Player p, Monster m, Scanner sc){
        List<Skill> skills = p.getJob().getSkills();
        int choice;
        while (true){
            for(int i = 0; i < skills.size(); i++){
                Logger.capturePrint((i+1) + " - " + skills.get(i).getName());
            }
            Logger.capturePrint("Wich hability you will use? ");
            System.out.print("--- ");
            try{
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= skills.size()){
                    return skills.get(choice-1);
                } else {
                    System.out.println("Invalid option!");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid skill!");
            }
        }
    }

    public static void waitSeconds(int seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
