package project.rpg;

public class Monster extends Player {
    private String hability1 = "Stomp";

    public Monster(String name, int level, int damage, int physicalDefense, int magicDefense, int maxLife, int posX, int posY) {
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
            0.85,
            0.45,
            "Attack",
            DamageType.PHYSICAL);
    }

    public void stomp(Character enemy) {
        if (!canAttack(enemy)) {
            Information.outOfRange(this, enemy);
            return;
        }
        DamageCalculator.calculateAndApplyDamage(
            this, 
            enemy, 
            1, 
            0.5, 
            hability1,
            DamageType.PHYSICAL);
    }
}