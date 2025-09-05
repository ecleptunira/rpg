package project.rpg;

public class Hero extends Player {

    String hability1 = "Divine Sword";

    public Hero(String name, int damage, int maxLife, int posX, int posY) {
        super(name, damage, maxLife, posX, posY);
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
            0.95, 
            0.15, 
            this.getMissChance(), 
            "attack");
    }

    public void divineSword(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            1.0, 
            0.25, 
            this.getMissChance(), 
            hability1);
    }
}