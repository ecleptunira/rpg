package project.rpg.main;

import project.rpg.characters.classes.jobs.*;
import project.rpg.characters.classes.classes.*;
import project.rpg.utils.*;


public class Game {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Information.limparTela();
        Job assasinClass = new AssasinJob();
        Job heroClass = new HeroJob();
        Job monsterClass = new MonsterJob();

        Assasin assasin = new Assasin("Ezio", assasinClass);
        Hero hero = new Hero ("Arthur", heroClass);
        Monster monster = new Monster("goblin", monsterClass);
        
        Information.battleStart();
        hero.useSkill("Basic Attack", monster); 
        hero.useSkill("Basic Attack", monster); 
        hero.setCriticalChanceAcumulated(100);
        hero.useSkill("Basic Attack", monster); 




        // hero.useSkill("Divine Sword", monster);
        
        // monster.useSkill("Stomp", hero);
        // monster.useSkill("Basic Attack", assasin);
        // assasin.useSkill("Backstab", monster);
        // Information.battleOver("DRAW");







        // Information.battleOver(heroWins == monsterWins ? "DRAW" : heroWins > monsterWins ? hero.getName() : monster.getName());
        // System.out.println("Total fights: " + fights);
        // System.out.println(hero.getName() + " wins: " + heroWins);
        // System.out.println(monster.getName() + " wins: " + monsterWins);
        // System.out.println("Draws: " + (fights - (heroWins + monsterWins)));
        // System.out.println("========================================\n\n\n");
        
    }
}