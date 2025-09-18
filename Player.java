package project.rpg;

public class Player extends Character{
    
    public Player(String name, int level, int damage, int physicalDefense, int magicDefense, int maxLife, int posX, int posY) {
        super(name, level, damage, physicalDefense, magicDefense, maxLife, posX, posY);
    }

    @Override
    public void attack(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            0.75, 
            0.25, 
            "attack",
            DamageType.PHYSICAL);
    }
}
