package project.rpg.main;
import project.rpg.characters.*;
import project.rpg.utils.*;


public class Game {
    public static void main(String[] args) {
        System.out.println();
        Information.limparTela();
        Hero hero = new Hero("Hero", 1, 30, 4, 2, 150, 1, 0, "Hero");
        Monster monster = new Monster("Monster", 1, 20, 3, 3, 200, 0, 0, "Monster");
        
        int heroWins = 0;
        int monsterWins = 0;
        int fights = 0;
        int turn = 0;
        
        // Hero hero1 = new Hero("Hero", 1, 30, 4, 2, 150, 1, 0, "Hero");
        // Monster monster1 = new Monster("Monster", 1, 20, 3, 3, 1000, 0, 0, "Monster");


        boolean replay = true;

        Information.battleStart();
        if (replay){
            do{
                turn++;
                System.out.println();
                System.out.println("================Turn " + turn + "================");
                System.out.println("Hero turn:");
                if ((Math.random() * 10) <= 0.5) {
                    hero.divineSword(monster);
                } else {
                    hero.attack(monster);
                }
                if (monster.getLife() <= 0) {
                    Information.characterDead(monster);
                    heroWins++; fights++; turn = 0;
                    hero = new Hero("Hero", 1, 30, 4, 2, 150, 1, 0, "Hero");
                    monster = new Monster("Monster", 1, 20, 3, 3, 200, 0, 0, "Monster");
                    continue;
                    //Information.pressEnterToContinue();
                }
                
                System.out.println();
                System.out.println("Monster turn:");
                if ((Math.random() * 10) <= 5) {
                    monster.stomp(hero);
                } else if (monster.getLife() > 0) {
                    monster.attack(hero);
                }
                if (hero.getLife() <= 0) {
                    Information.characterDead(hero);
                    monsterWins++; fights++; turn = 0;
                    hero = new Hero("Hero", 1, 30, 4, 2, 150, 1, 0, "Hero");
                    monster = new Monster("Monster", 1, 20, 3, 3, 200, 0, 0, "Monster");
                    continue;
                    //Information.pressEnterToContinue();
                }
                System.out.println();
                //Information.pressEnterToContinue();
            }
            while (fights < 1); //mudar para 5 ou mais para mais lutas
        }
        if (replay){
            Information.battleOver(heroWins == monsterWins ? "DRAW" : heroWins > monsterWins ? hero.getName() : monster.getName());
            System.out.println("Total fights: " + fights);
            System.out.println(hero.getName() + " wins: " + heroWins);
            System.out.println(monster.getName() + " wins: " + monsterWins);
            System.out.println("Draws: " + (fights - (heroWins + monsterWins)));
            System.out.println("========================================\n\n\n");
        }
    }
}