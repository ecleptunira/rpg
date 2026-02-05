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

        String status = ranger.toString();
        System.out.println(status);
        // mage.gainExperience(322599);
        
        // assasin.useSkill("Basic Attack", goblin);
        ranger.setLife(1);
        ranger.useSkill("Multi Shot", goblin);
        
        // goblin.setMaxLife(5000);
        // goblin.setLife(5000);
        // goblin.setLevel(30);
        // ranger.forceLevelTo(30);
        // ranger.setLife(1);

        status = ranger.toString();
        System.out.println(status);
        
        // hero.useSkill("Fire Ball", goblin);
    }
}