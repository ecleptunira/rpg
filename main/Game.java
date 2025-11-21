package project.rpg.main;

import project.rpg.characters.classes.jobs.*;
import project.rpg.characters.classes.jobs.monstersjobs.GoblinJob;
import project.rpg.characters.classes.classes.*;
import project.rpg.utils.Information;
import project.rpg.utils.Logger;
import project.rpg.combat.ExperienceCalculator;


@SuppressWarnings("unused")
public class Game {
    public static void main(String[] args) {
        Information.limparTela();
        Job assasinClass = new AssasinJob();
        Job heroClass = new HeroJob();
        Job goblinClass = new GoblinJob();

        Assasin assasin = new Assasin("Ezio", assasinClass);
        Hero hero = new Hero ("Arthur", heroClass);
        Monster goblin = new Monster("Goblin", goblinClass, 5);
        
        Information.battleStart();
        goblin.setLevel(50);
        goblin.setLife(1);
        assasin.setLife(1);

        String status = assasin.toString();
        System.out.println(status);

        assasin.useSkill("Basic Attack", goblin);

        status = assasin.toString();
        System.out.println(status);
        

        // hero.useSkill("Fire Ball", monster);
        // hero.useSkill("Basic Attack", monster);

        // String a = monster.toString();
        // Logger.capturePrint(a);
        

        // hero.useSkill("Basic Attack", monster);
        // ExperienceCalculator.expFactor(hero, monster);

        // monster.useSkill("Basic Attack", assasin);
        // assasin.useSkill("Backstab", monster);
        // hero.useSkill("Divine Sword", monster);
        // monster.useSkill("Stomp", hero);


    }
}