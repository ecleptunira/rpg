package project.rpg;

public class Player extends Character{

    public Player(String name, int damage, int life, int posX, int posY) {
        super(name, damage, life, posX, posY);
    }

    @Override
    public void attack(Character enemy) {
        boolean inRange = canAttack(enemy);
        if (!inRange) {
            System.out.println("Out of range");
        } else {
            int missChance = (int) (Math.random() * 10);
            if (missChance <= 3) {
                System.out.println("failed attack");
            } else {
            int baseDamage = (int) (this.getDamage() * 0.75);
            int bonusDamage = (int) (this.getDamage() * 0.25);
            int variableDamage = (int) (Math.random() * (bonusDamage + 1));
            int damageDealt = baseDamage + variableDamage;

            enemy.takeDamage(damageDealt);
            System.out.println(
                this.getName() + " cause " + damageDealt + 
                " of damage on " + enemy.getName() + 
                ", now he has " + enemy.getLife() + " of life.");
            }
        }
    }
}
