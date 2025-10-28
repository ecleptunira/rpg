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
        Monster monster = new Monster("Goblin", monsterClass);
        
        Information.battleStart();
        assasin.setCriticalChance(100);
        assasin.setCriticalChanceAcumulated(100);

        monster.setMaxLife(1000);
        monster.setLife(1000);
        
        monster.setPhysicalDefense(0);
        monster.setLevel(1);

        
        
        
        // monster.useSkill("Basic Attack", assasin);
        // assasin.useSkill("Backstab", monster);
        // hero.useSkill("Divine Sword", monster);
        // monster.useSkill("Stomp", hero);


    }
}