package project.rpg;

public class Hero extends Player {

    public Hero(String name, int damage, int life, int posX, int posY) {
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
            int baseDamage = (int) (this.getDamage() * 0.95);
            int bonusDamage = (int) (this.getDamage() * 0.15);
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

    public void divineSword(Character enemy) {
        boolean inRange = canAttack(enemy);
        if (!inRange) {
            System.out.println("Out of range");
        } else {
            int missChance = (int) (Math.random() * 10);
            if (missChance <= 3) {
                System.out.println("failed attack");
            } else {
            int baseDamage = (int) (this.getDamage() * 0.95);
            int bonusDamage = (int) (this.getDamage() * 0.15);
            int variableDamage = (int) (Math.random() * (bonusDamage + 1));
            int damageDealt = baseDamage + variableDamage;

            enemy.takeDamage(damageDealt);
            System.out.println(this.getName() + " used Divine Sword!");
            System.out.println("Divine sword hit with a power of " + damageDealt + " points! \n" + 
            enemy.getName() + " now has " + enemy.getLife() + " points of life.");
            }
        }
    }

}
