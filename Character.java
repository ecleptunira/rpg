package project.rpg;

public abstract class Character {
    private String name;
    private int level = 1;
    private int damage;

    private int physicalDefense; // base 1
    private int magicDefense; // base 1

    private int life;
    private int maxLife;

    private int posX;
    private int posY;

    private int criticalChance = 10; //base 10
    private int criticalChanceAcumulated = criticalChance; //base equals to critical chance
    private int criticalDamage = 50; // base 50%

    private double evasion = 50; // base = 50%
    private double accuracy = 15; // base = 5%



    protected Character(String name, int level, int damage, int physicalDefense, int magicDefense, int maxLife, int posX, int posY) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.physicalDefense = physicalDefense;
        this.magicDefense = magicDefense;
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

    public String toString() {
        return "Caracter information: " +
        "\n Name: " + name + " | Level: " + level +
        "\n | Life: " + life + "/" + maxLife +
        "\n | Damage: " + damage +
        "\n | Physical defense: " + physicalDefense +
        "\n | Magic defense: " + magicDefense +
        "\n | Critical Chance: " + criticalChance + "%" +
        "\n | Critical Damage: " + criticalDamage + "%" +
        "\n | Evasion: " + evasion + "%" +
        "\n | Accuracy: " + accuracy + "%" +
        "\n | Position: (" + posX + ", " + posY + ")";
    }

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPhysicalDefense(){
        return physicalDefense;
    }
    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public int getMagicDefense(){
        return magicDefense;
    }
    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }

    public int getMaxLife() {
        return maxLife;
    }
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getCriticalChance() {
        return criticalChance;
    }
    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }

    public int getCriticalChanceAcumulated(){
        return criticalChanceAcumulated;
    }
    public void setCriticalChanceAcumulated(int criticalChanceAcumulated) {
        this.criticalChanceAcumulated = criticalChanceAcumulated;
    }

    public int getCriticalDamage() {
        return criticalDamage;
    }
    public void setCriticalDamage(int criticalDamage) {
        this.criticalDamage = criticalDamage;
    }

    public double getEvasion(){
        return evasion;
    }
    public void setEvasion(double evasion) {
        this.evasion = evasion;
    }

    public double getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

}
