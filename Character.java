package project.rpg;

public abstract class Character {
    private String name;
    private int damage;

    private int life;
    private int maxLife;

    private int posX;
    private int posY;

    private int criticalChance = 10;
    private int criticalChanceAculative = 10;
    private int critalDamage = 50;

    private double missChance = 0.3;

    protected Character(String name, int damage, int maxLife, int posX, int posY) {
        this.name = name;
        this.damage = damage;
        this.maxLife = maxLife;
        this.life = maxLife;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean canAttack(Character enemy) {
        int deltaX = Math.abs(this.posX - enemy.posX);
        int deltaY = Math.abs(this.posY - enemy.posY);
        return (deltaX < 2 && deltaY == 0) || (deltaX == 0 && deltaY < 2);
    }

    public void takeDamage(int damage) {
        this.life -= damage;
        if (this.life < 0) {
            this.life = 0;
        }
    }

    public abstract void attack(Character enemy);

    public void move (Direction direction) {
        if (direction == Direction.UP) {
            this.posY += 1;
        } else if (direction == Direction.DOWN) {
            this.posY -= 1;
        } else if (direction == Direction.LEFT) {
            this.posX -= 1;
        } else if (direction == Direction.RIGHT) {
            this.posX += 1;
        }
    }

    public int getDamage() {
        return damage;
    }
    public int getLife() {
        return life;
    }
    public String getName() {
        return name;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public int getMaxLife() {
        return maxLife;
    }
    public int getCriticalChance() {
        return criticalChance;
    }
    public int getCriticalChanceAcumulative(){
        return criticalChanceAculative;
    }
    public int getCritalDamage() {
        return critalDamage;
    }

    public double getMissChance(){
        return missChance;
    }
    public void setCriticalChanceAculative(int criticalChanceAculative) {
        this.criticalChanceAculative = criticalChanceAculative;
    }

    public String toString() {
        return "Caracter information: " +
        "\n Name: " + name +
        "\n | Damage: " + damage +
        "\n | Life: " + life +
        "\n | Position: (" + posX + ", " + posY + ")";
    }
}
