package project.rpg.combat;

import project.rpg.characters.Character;
import project.rpg.utils.Information;
import project.rpg.utils.Logger;

public class DamageCalculator {

    public static DamageResult calculateDamage( Character attacker, 
                                                Character defensor, 
                                                Double baseDamageFactor, 
                                                Double variableDamageFactor, 
                                                DamageType damageType){
        //Check list
        Logger.info("================ DAMAGE CALCULATOR ================");
        Logger.info("DAMAGE CALCULATOR -> " + attacker.getName() + " is attacking " + defensor.getName() + " with " + damageType + " damage.");
        //1 - Check if the attack will hit
        boolean willHit = willHit(attacker, defensor);
        if (!willHit) {
            Information.damageMissed(attacker, defensor);
            return DamageResult.missResult(attacker, damageType);
        }

        //2 - calculate the damage
        int attackDamage = damageType == DamageType.PHYSICAL ? attacker.getPhysicalDamage() : attacker.getMagicalDamage();
        int baseDamage = (int) (attackDamage * baseDamageFactor);
        int bonusDamage = (int) (attackDamage * variableDamageFactor);
        int variableDamage = (int) (Math.random() * (bonusDamage + 1));

        double levelDifference = levelDifference(attacker, defensor);
        int damageDealt  = (int) ((baseDamage + variableDamage) * levelDifference);

        // 3 - aplly the defense based on the damage type
        int damageReduction = 0;

        switch (damageType){
            case PHYSICAL:
                damageDealt = applyDefense(damageDealt, defensor.getPhysicalDefense(), defensor.getPercentPhysicalDefense());
                damageReduction = damageDealt;
                break;
            case MAGICAL:
                damageDealt = applyDefense(damageDealt, defensor.getMagicalDefense(), defensor.getPercentMagicDefense());
                damageReduction = damageDealt;
                break;
        }

        // 4 - check if is a critical hit
        int criticalPercentage = attacker.getCriticalChanceAcumulated();
        boolean isCritical = isCritical(attacker);

        if (isCritical) {
            damageDealt = applyCritical(attacker, damageDealt);
        }
        return new DamageResult(willHit, damageType, attackDamage, baseDamage, bonusDamage, variableDamage, levelDifference, damageDealt, damageReduction, criticalPercentage, isCritical);
    }

    /**
     * Determine if the attack will hit or miss based on the attacker's accuracy and defender's evasion
     * @return true if the attack hits, false if it misses, all attacks have a minimum chance of 5% to hit and a maximum chance of 95% to hit.
     */
    public static boolean willHit(Character attacker, Character defender){
        double accuracy = attacker.getAccuracy();
        double evasion = defender.getEvasion();

        double hitChance =  (accuracy / (accuracy + evasion) * 100);
        hitChance = Math.max(5, Math.min(95, hitChance));
        double randomValue = Math.random() * 100;

        boolean willHit = randomValue < hitChance;

        Logger.info("WILLHIT? -> Hit chance: 5 ~ 95% | " + 
        "Atk accuracy ("+attacker.getAccuracy()+")" + " / (Atk accuracy ("+attacker.getAccuracy()+")" + " + Def evasion ("+defender.getEvasion()+") * 100 = " + (int) hitChance +"% < > Random value: " + (int) randomValue + " ? " + (willHit ? "Yes >": "No <"));

        return willHit;
    }

    /**
     * <p>Returns damage multiplier based on attacker/defender level difference
     * higher (levelDiff < -5) => -5% per level beyond 5 (capped at 0.5).
     *
     * <p>This makes strong attackers deal more damage and weak attackers deal less, 
     * while keeping the system balanced and rewarding players for leveling up without making it too punishing for lower-level players.
     * Rules:
     * - If |levelDiff| <= 5  => multiplier = 1.0 (no change).
     * - If attacker is higher (levelDiff > 5) => +10% per level beyond 5 (capped at 2.0).
     * - If defender is higher (levelDiff < -5) => -5% per level beyond 5 (capped at 0.5).
     */
    public static double levelDifference(Character attacker, Character defender){
        int levelDiff = attacker.getLevel() - defender.getLevel();
        double factor = 1.0;
        String log = "LEVELDIFFERENCE -> Attacker: " + attacker.getLevel() + " | Defender: " + defender.getLevel() + " | Factor (x0.5 ~ x2): x";

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

    private static final double DEFENSE_SCALE = 25.0; //Remeber to change getPercentDefense methods.
    /**
     * <p>apply defense on the damage recieved
     * 
     * <p>DEFENSE_SCALE = 25.0
     * <p>DEFENSE_SCALE determines the effectiveness of the defense in reducing damage taken, 
     * the higher the value, the less effective the defense.
     * <p>Example:
     * <p>Damage = 100 and DEFENSE_SCALE = 25.0
     * <p>Defense Formula: Damage Taken = Damage / (1 + (Defense / DEFENSE_SCALE))
     * <p>Defense 000 --- 100 / (1 + (000/25)) = 100/1 = 100 damage taken
     * <p>Defense 025 --- 100 / (1 + (025/25)) = 100/2 = 50  damage taken
     * <p>Defense 050 --- 100 / (1 + (050/25)) = 100/3 = 33  damage taken
     * <p>Defense 075 --- 100 / (1 + (075/25)) = 100/4 = 25  damage taken
     * <p>Defense 100 --- 100 / (1 + (100/25)) = 100/5 = 20  damage taken
     * @param damage damage calculated before applying defense.
     * @param defense defense of the defensor.
     * @param percentDefense percentage of damage reduction based on the defense, used for logging purpose.
     * @return damage after applying defense
     */
    public static int applyDefense(int damage, int defense, int percentDefense){
        // if (defense > 100) defense = 100; //capping defense to 100 //Check if is valid yet
        double damageRecieved = damage / (1 + (defense / DEFENSE_SCALE));
        Logger.info("APPLYDEFENSE -> Damage pre-mitigation: " + damage + 
                    " | Damage reduction: " + percentDefense + "%" + 
                    " | Damage recieved: " + (int)damageRecieved);
        return (int) damageRecieved;
    }

    /**
     * Verify if the attack is a critical hit, 
     * if fail acumulate the critical chance for the next attack
     * @param attacker The character who is attacking
     * @return true if is a critical hit, false otherwise
     */
    public static boolean isCritical(Character attacker){
        double critChance = attacker.getCriticalChanceAcumulated();
        double randomValue =Math.random() * 100;

        Logger.info("ISCRITICAL? -> crit chance: " + (int) critChance + " < > Random value: " + String.format("%.0f", randomValue) + " ? " + (critChance > randomValue ? "> Yes":"< No"));

        if (randomValue < critChance) {
            attacker.setCriticalChanceAcumulated(attacker.getCriticalChance()); // if is a critical hit, reset acumulated chance
            return true;
        } else {
            attacker.setCriticalChanceAcumulated((int)critChance + attacker.getCriticalChance()); // if is not a critical hit, acumulate the critical chance
            return false;
        }
    }

    /**
     * <p>Apply critical damage modificator to the damage dealt
     * @param attacker the character who is attacking
     * @param damageDealt the damage dealt before critical damage modificator
     * @return the damage dealt after critical damage modificator
     */
    public static int applyCritical(Character attacker, int damageDealt){
        int damageDealtBefore = damageDealt;
        damageDealt = (int) (damageDealt * (1 + (attacker.getCriticalDamage() / 100.0)));
        Logger.info("APPLYCRITICAL -> Crit damage: " + attacker.getCriticalDamage() + "%" + 
                    " | Damage before: " + damageDealtBefore + 
                    " | New damage: " + damageDealt);
        return damageDealt;
    }

}
