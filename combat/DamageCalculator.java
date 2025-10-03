package project.rpg.combat;

import project.rpg.characters.Character;
import project.rpg.utils.Information;

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
        double chance = attacker.getCriticalChanceAcumulated();
        if (Math.random() * 100 < chance) {
            // if is a critical hit, reset acumulated chance
            attacker.setCriticalChanceAcumulated(attacker.getCriticalChance());
            return true;
        } else {
            // if is not a critical hit, acumulate the critical chance
            attacker.setCriticalChanceAcumulated((int)chance + attacker.getCriticalChance());
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
        damageDealt = (int) (damageDealt * (1 + (attacker.getCriticalDamage() / 100.0)));
        return damageDealt;
    }


    /**
     * Adjust the damage based on the level difference between attacker and defender
     * - Diference until 5 levels: no adjustment
     * - Each diference level beyond level 5: 10% increase/decrease in damage
     * - cap between 0.5x and 2.0x
     */
    public static double levelDiference(Character attacker, Character defender){
        int levelDifference = attacker.getLevel() - defender.getLevel();
        
        if (Math.abs(levelDifference) <= 5) {
            return 1.0;
        }
        // Cap the level difference at 15
        if (levelDifference > 15) {
            levelDifference = 15;
        } else if (levelDifference < -15) {
            levelDifference = -15;
        }
        //applying a factor of 10% per level difference beyond level 5.
        double factor = 1.0 + ((levelDifference-5) * 0.1);
        
        // Add caps to the factor to avoid extreme values
        if (factor > 2.0) {
            factor = 2.0;
        } else if (factor < 0.5) {
            factor = 0.5;
        }
        return factor;
    }


    /*
     * DEFENSE_SCALE determines the effectiveness of the defense in reducing damage taken.
     * The higher the value, the less effective the defense.
     * Example:
     * Damage = 100 and DEFENSE_SCALE = 10
     * Defense 00 --- 100/(1+00/10) = 100/1   = 100 damage taken
     * Defense 05 --- 100/(1+05/10) = 100/1.5 = 66  damage taken
     * Defense 10 --- 100/(1+10/10) = 100/2   = 50  damage taken
     * Defense 20 --- 100/(1+20/10) = 100/3   = 33  damage taken
     */
    private static final double DEFENSE_SCALE = 10.0;

    /**
     * apply defense on the damage recieved
     * @param damage damage calculated before applying defense
     * @param defense defense of the defensor
     * @return damage after applying defense
     */
    public static int applyDefense(int damage, int defense){
        return (int) (damage / (1 + (defense / DEFENSE_SCALE)));
    }

    /**
     * Determine if the attack will hit or miss based on the attacker's accuracy and defender's evasion
     * @return true if the attack hits, false if it misses
     */
    public static boolean willHit(Character attacker, Character defender){
        double hitChance =  defender.getEvasion() - attacker.getAccuracy();
        if (hitChance < 5 ) hitChance = 5; // Minimum hit chance of 5%
        if (hitChance > 95) hitChance = 95; // Maximum hit chance of 95%
        double randomValue = Math.random() * 100;
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
        // 1 - check if is a miss
        if (willHit(attacker, defensor)) {
            Information.damageMissed(attacker, hability, defensor);
            return;
        }

        // 2 - calculate the variation of the damage
        int baseDamage = (int) (attacker.getPhysicalDamage() * baseDamageFactor);
        int bonusDamage = (int) (attacker.getPhysicalDamage() * bonusDamageFactor);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));
        int damageDealt = (int) ((baseDamage + variableDamage) * levelDiference(attacker, defensor));

        // 3 - aplly the defense based on the damage type
        if (damageType == DamageType.PHYSICAL){
            damageDealt = applyDefense(damageDealt, defensor.getPhysicalDefense());
        } else if (damageType == DamageType.MAGICAL){
            damageDealt = applyDefense(damageDealt, defensor.getMagicDefense());
        }

        // 4 - check if is a critical hit
        if (isCritical(attacker)){
            damageDealt = applyCritical(attacker, damageDealt);
            Information.criticalHit(attacker, hability, damageDealt, defensor);
        }else {
            Information.damageStatus(attacker, hability, damageDealt, defensor);
        }
        // 5 = apply the damage on the defensor and show his health
        defensor.takeDamage(damageDealt);
        Information.showHealth(defensor);
    }
}
