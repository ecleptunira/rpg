package project.rpg.combat;
import project.rpg.characters.Character;

public class ExperienceCalculator {

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

    @SuppressWarnings("unused")
    public static int calculateExperience(Character attacker, Character defender){
        double factor = expFactor(attacker, defender);
        
        return 1;
    }
}
