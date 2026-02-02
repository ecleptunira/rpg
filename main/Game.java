package project.rpg.main;

import project.rpg.characters.classes.jobs.*;
import project.rpg.characters.classes.jobs.monstersjobs.*;
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
        Job rangerClass = new RangerJob();
        Job mageClass = new MageJob();

        Mage mage = new Mage("Gandalf", mageClass);
        Ranger ranger = new Ranger("Legolas", rangerClass);
        Assasin assasin = new Assasin("Ezio", assasinClass);
        Hero hero = new Hero ("Arthur", heroClass);
        Monster goblin = new Monster("Goblin", goblinClass, 5);
        
        Information.battleStart();
        // goblin.setLevel(1);

        mage.setLife(1);
        String status = mage.toString();
        System.out.println(status);
        
        mage.gainExperience(322599);
        // assasin.gainExperience(700);
        // assasin.forceLevelTo(5);
        status = mage.toString();
        System.out.println(status);

        // hero.useSkill("Fire Ball", goblin);
        // hero.useSkill("Basic Attack", goblin);

        // hero.useSkill("Basic Attack", monster);
        // ExperienceCalculator.expFactor(hero, monster);

        // monster.useSkill("Basic Attack", assasin);
        // assasin.useSkill("Backstab", monster);
        // hero.useSkill("Divine Sword", monster);
        // monster.useSkill("Stomp", hero);


    }
}