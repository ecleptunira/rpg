package project.rpg.combat;
import project.rpg.characters.Character;

public class ExperienceCalculator {

    /**
     * Calculates the experience factor based on level difference between attacker and defender
     * @return factor to be applied to experience gain with a variation between 0.5 ~ 2.0
     */
    public static double expFactor(Character attacker, Character defender){
        int levelDifference = defender.getLevel() - attacker.getLevel();
        
        if (levelDifference <= -15) {
            return 0.1;
        }
        if (levelDifference > 5) {
            double factor = 1.0 + (levelDifference - 5) * 0.1;
            return (Math.round(factor *10.0)/10.0);
        }
        if (levelDifference < -5) {
            double factor = 1.0 - (-levelDifference - 5) * 0.1;
            return (Math.round(factor*10.0)/10.0);
        }
        return 1.0;
    }

    /**
     * Calculates the experience gained by the character after defeating the enemy
     * @param attacker Who is gaining experience
     * @param defender Who was defeated
     * @param baseEXP Base experience value of the defeated enemy
     * @return Total experience gained
     */
    public static int calculateExperience(Character attacker, Character defender, int baseEXP){
        double factor = expFactor(attacker, defender);
        int xpGain = (int) (defender.getLevel() * baseEXP * factor);
        return Math.round(xpGain);
    }
}
