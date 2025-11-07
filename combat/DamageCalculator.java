package project.rpg.combat;

import project.rpg.characters.Character;
import project.rpg.utils.Information;
import project.rpg.utils.Logger;

/**
 * DamageCalculator is a utility class that provides methods to calculate and aplly
 * damage in combat scenarios.
 * it includes methos for critical hits, evasion, level difference, and damage aplication.
 */

public class DamageCalculator {

    /**
     * Verify if the attack is a critical hit, 
     * if fail acumulate the critical chance for the next attack
     * @param attacker The character who is attacking
     * @return true if is a critical hit, false otherwise
     */
    public static boolean isCritical(Character attacker){
        double critChance = attacker.getCriticalChanceAcumulated();

        if (Math.random() * 100 < critChance) {
            // if is a critical hit, reset acumulated chance
            attacker.setCriticalChanceAcumulated(attacker.getCriticalChance());
            Logger.info("ISCRITICAL? -> Yes, Crit chance: " + critChance);
            return true;
        } else {
            // if is not a critical hit, acumulate the critical chance
            Logger.info("ISCRITICAL? -> No, Crit chance: " + critChance);
            attacker.setCriticalChanceAcumulated((int)critChance + attacker.getCriticalChance());
            return false;
        }
    }


    /**
     * Apply critical damage modificator to the damage dealt
     * @param attacker the character who is attacking
     * @param damageDealt the damage dealt before critical damage modificator
     * @return the damage dealt after critical damage modificator
     */
    public static int applyCritical(Character attacker, int damageDealt){
        int damageDealtBefore = damageDealt;
        damageDealt = (int) (damageDealt * (1 + (attacker.getCriticalDamage() / 100.0)));
        Logger.info("APPLYCRITICAL -> Attacker: " + attacker.getName() + " | Crit damage: " + attacker.getCriticalDamage() + "%" + 
                    " | Damage before: " + damageDealtBefore + " | New damage: " + damageDealt);
        return damageDealt;
    }


    /**
     * Returns damage multiplier based on attacker/defender level difference.
     *
     * Rules:
     * - If |levelDiff| <= 5  => multiplier = 1.0 (no change)
     * - If attacker is higher (levelDiff > 5) => +10% per level beyond 5 (capped at 2.0)
     * - If defender is higher (levelDiff < -5) => -5% per level beyond 5 (capped at 0.5)
     *
     * This makes strong attackers scale faster (up to 2x) while being less punishing
     * when defender is higher (down to 0.5x), matching your examples.
     */
    public static double levelDifference(Character attacker, Character defender){
        int levelDiff = attacker.getLevel() - defender.getLevel();
        double factor = 1.0;
        String log = "LEVELDIFFERENCE -> Attacker: " + attacker.getLevel() + " | Defender: " + defender.getLevel() + " | Factor(x0.5 ~ x2): x";

        // within ±5 levels -> no change
        if (Math.abs(levelDiff) <= 5) {
            Logger.info(log + factor);
            return 1.0;
        }

        // attacker is stronger: +10% per level beyond 5
        if (levelDiff > 5) {
            factor = 1.0 + (levelDiff - 5) * 0.10; // +0.1 per extra level
        } 
        // defender is stronger: -5% per level beyond 5
        else { // levelDiff < -5
            factor = 1.0 + (levelDiff + 5) * 0.05; // levelDiff is negative, (levelDiff+5) negative => reduces
            // equivalent: factor = 1.0 - (abs(levelDiff) - 5) * 0.05
        }

        // clamp to sensible bounds: [0.5, 2.0]
        if (factor < 0.5) factor = 0.5;
        if (factor > 2.0) factor = 2.0;
        Logger.info(log + factor);

        return factor;
    }
    

    /*
     * DEFENSE_SCALE determines the effectiveness of the defense in reducing damage taken.
     * The higher the value, the less effective the defense.
     * Example:
     * Damage = 100 and DEFENSE_SCALE = 25.0
     * Defense Formula: Damage Taken = Damage / (1 + (Defense / DEFENSE_SCALE))
     * Defense 00  --- 100 / (1 + (00 /25)) = 100/1 = 100 damage taken
     * Defense 25  --- 100 / (1 + (25 /25)) = 100/2 = 50  damage taken
     * Defense 50  --- 100 / (1 + (50 /25)) = 100/3 = 33  damage taken
     * Defense 75  --- 100 / (1 + (75 /25)) = 100/4 = 25  damage taken
     * Defense 100 --- 100 / (1 + (100/25)) = 100/5 = 20  damage taken
     */
    private static final double DEFENSE_SCALE = 25.0; //Remeber to change getPercentDefense methods.

    /**
     * apply defense on the damage recieved
     * @param damage damage calculated before applying defense
     * @param defense defense of the defensor with a cap of 100
     * @return damage after applying defense with a minimum cap of 20% of the original damage
     */
    public static int applyDefense(int damage, int defense, int percentDefense){
        if (defense > 100) defense = 100; //capping defense to 100
        double damageRecieved = damage / (1 + (defense / DEFENSE_SCALE));
        Logger.info("APPLYDEFENSE -> Damage pre-mitigation: " + damage + " | Damage reduction: " + percentDefense + "% | Damage recieved: " + (int)damageRecieved);
        return (int) damageRecieved;
    }

    /**
     * Determine if the attack will hit or miss based on the attacker's accuracy and defender's evasion
     * @return true if the attack hits, false if it misses, all attacks have a minimum chance of 5% to hit and a maximum chance of 95% to hit.
     */
    public static boolean willHit(Character attacker, Character defender){
        double hitChance =  defender.getEvasion() - attacker.getAccuracy();
        double randomValue = Math.random() * 100;

        Logger.info("WILLHIT? -> (Defender evasion: " + defender.getEvasion() + " - Attacker accuracy: " + attacker.getAccuracy() + 
                    ") = " + hitChance + " | Hit chance: 5 ~ 95%" + " | Random value: " + String.format("%.0f", randomValue) + "%");

        if (hitChance < 5 ) hitChance = 5; // Minimum hit chance of 5%
        if (hitChance > 95) hitChance = 95; // Maximum hit chance of 95%
        return  randomValue < hitChance;
    }

    /**
     * calculate and apply damage from attacker to defensor, considering:
     * - If the attack hits or misses
     * - Base damage and bonus damage factors
     * - Level difference between attacker and defensor
     * - Defense of the defensor based on damage type
     * - If is a critical hit
     * @param attacker character who is attacking
     * @param defensor character who is defending
     * @param baseDamageFactor minimum damage factor
     * @param bonusDamageFactor maximum damage factor
     * @param hability name of the hability ysed
     * @param damageType type of damage
     */
    public static void calculateAndApplyDamage( Character attacker, Character defensor, 
                                                double baseDamageFactor, double bonusDamageFactor, 
                                                String hability, DamageType damageType) {
        // preliminary checks
        // 0 - check if the attacker is in range
        Logger.log("BEGGIN CALCULATE AND APLLY DAMAGE");
        boolean canAttack = attacker.canAttack(defensor);
        if(!canAttack) {
            Information.outOfRange(attacker.getName(), defensor.getName());
            return;
        }
        // 1 - check if is a miss
        if (willHit(attacker, defensor)) {
            Information.damageMissed(attacker, hability, defensor);
            return;
        }

        // 2 - calculate the variation of the damage
        int baseDamage = (int) (attacker.getPhysicalDamage() * baseDamageFactor); // CORRIGIR PARA INCLUIR MAGIC DAMAGE
        int bonusDamage = (int) (attacker.getPhysicalDamage() * bonusDamageFactor); // CORRIGIR PARA INCLUIR MAGIC DAMAGE
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        double levelDifference = levelDifference(attacker, defensor);
        int damageDealt  = (int) ((baseDamage + variableDamage) * levelDifference);
        
        // 3 - aplly the defense based on the damage type
        String typeOfDamage = "Unknown";
        int damageReduction = 0;
        if (damageType == DamageType.PHYSICAL){
            damageDealt = applyDefense(damageDealt, defensor.getPhysicalDefense(), defensor.getPercentPhysicalDefense());
            damageReduction = damageDealt;
            typeOfDamage = "Physical";
        } else if (damageType == DamageType.MAGICAL){
            damageDealt = applyDefense(damageDealt, defensor.getMagicDefense(), defensor.getPercentMagicDefense());
            damageReduction = damageDealt;
            typeOfDamage = "Magical";
        }
        // 4 - check if is a critical hit
        boolean isCritical = isCritical(attacker);
        if (isCritical){
            damageDealt = applyCritical(attacker, damageDealt);
            Information.criticalHit(attacker, hability, damageDealt, defensor);
        }else {
            Information.damageStatus(attacker, hability, damageDealt, defensor);
        }

        // 5 = apply the damage on the defensor and show his health
        defensor.takeDamage(damageDealt);
        Information.showHealth(defensor);
        if (!defensor.isAlive()) {
            Information.characterDead(defensor.getName());
        }

        Logger.debug("================ DAMAGE CALCULATION ================");
        Logger.debug("Attacker: " + attacker.getName() + " (Level " + attacker.getLevel() + ") - " + 
                    "Defender: " + defensor.getName() + " (Level " + defensor.getLevel() + ")");
        Logger.debug("Damage Type: " + typeOfDamage + " | Base Damage " + baseDamage + " + Variable Damage (Random): " + variableDamage);
        Logger.debug("Critical damage: " + attacker.getCriticalDamage() + "% | Critical Chance: " + 
                    (attacker.getCriticalChanceAcumulated() > attacker.getCriticalChance() ? attacker.getCriticalChanceAcumulated() : attacker.getCriticalChance()) + "%");
        Logger.debug("Damage pre-mitigation: " + (baseDamage + variableDamage));
        Logger.debug("Level Difference Multiplier: " + String.format("%.1f", levelDifference) + " | Critical Hit: " + (isCritical ? "Yes" : "No"));
        Logger.debug(typeOfDamage + " defense used " +  (damageType == DamageType.PHYSICAL ? defensor.getPhysicalDefense() : defensor.getMagicDefense())
                    + " | Damage reduction scale: " + 
                    (damageType == DamageType.PHYSICAL ? defensor.getPercentPhysicalDefense() : defensor.getPercentMagicDefense()) + "%");
        Logger.debug("Calculation steps: Damage Dealt = base + variable = "+ (baseDamage+variableDamage) +" * levelDiff -> " + ((baseDamage + variableDamage)*levelDifference) + 
                    " -> apply defense -> " + damageReduction + " -> apply critical -> " +
                    (isCritical ? "x" + (1 + (attacker.getCriticalDamage() / 100.0)) : "no critical"));
        Logger.debug("Final Damage: " + damageDealt);
    }
}
