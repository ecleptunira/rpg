package project.rpg.characters;

// import project.rpg.utils.Direction;
import project.rpg.utils.Information;
import project.rpg.characters.classes.jobs.Job;
import project.rpg.characters.skills.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Character {
    private List<Skill> skills = new ArrayList<>();

    private String name;
    private int level = 0;
    private int experience = 0;
    private int experienceToNextLevel = 10;
    
    private int life = 100;
    private int maxLife = life;

    private int physicalDamage = 1;
    private int magicalDamage = 1;

    private int physicalDefense = 0;
    private int magicDefense = 0;

    private int criticalChance = 0;
    private int criticalChanceAcumulated = 0;
    private int criticalDamage = 50;

    private int evasion = 0;
    private int accuracy = 0;

    protected Character(String name, Job choosenClass) {
        this.name = name;
        choosenClass.applyClassStats(this);
    }

    protected Character(String name){
        this.name = name;
    }

    public void takeDamage(int damage) {
        this.life -= damage;
        if (this.life < 0) this.life = 0;
    }

    public int heal(int amount){
        int before = this.life;
        this.life = Math.min(this.life + amount, this.maxLife);
        return (this.life - before);
    }

    public void restoreLifeToFull(){
        this.life = this.maxLife;
    }

    public void gainExperience(int amount){
        this.experience += amount;
        Information.gainExperience(this, amount);
        Information.showExperience(this);
        while (this.experience >= this.experienceToNextLevel){
            levelUp();
        }
    }

    public void levelUp(){
        this.level ++;
        this.experience -= this.experienceToNextLevel ;
        this.experienceToNextLevel = (int) Math.round(this.experienceToNextLevel * 1.25);
        Information.levelUp(this);
        Information.showExperience(this);
    }

    public void forceLevelTo(int targetLevel){
        if (targetLevel < this.level){
            return;
        }

        while (this.level < targetLevel){
            this.level ++;
            this.experienceToNextLevel = (int) Math.round(this.experienceToNextLevel * 1.25);
        }
    }

    public boolean isAlive(){
        return this.life > 0;
    }

    public String toString() {
        return "Caracter information: " +
        "\n Name: " + name + " | Level: " + level +
        "\n | Life: " + life + "/" + maxLife +
        "\n | Physical Damage: " + physicalDamage +
        "\n | Magic Damage: " + magicalDamage + 
        "\n | Physical defense: " + physicalDefense +
        "\n | Magic defense: " + magicDefense +
        "\n | Critical Chance: " + criticalChance + "%" +
        "\n | Critical Damage: " + criticalDamage + "%" +
        "\n | Evasion: " + evasion + "%" +
        "\n | Accuracy: " + accuracy + "%" +
        "\n | Skills: " + skills.stream().map(Skill::getName).collect(Collectors.joining(", "));
    }

    public List<Skill> getSkills() {
        return skills;
    }
    public void addSkills(Skill skill){
        skills.add(skill);
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

    public double getExperience(){
        return experience;
    }
    public void setExperience(int experience){
        this.experience = experience;
    }

    public double getExperienceToNextLevel(){
        return experienceToNextLevel;
    }
    public void setExperienceToNextLevel(int experienceToNextLevel){
        this.experienceToNextLevel = experienceToNextLevel;
    }

    public int getPhysicalDamage() {
        return physicalDamage;
    }
    public void setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
    }

    public int getMagicalDamage() {
        return magicalDamage;
    }
    public void setMagicalDamage(int magicalDamage) {
        this.magicalDamage = magicalDamage;
    }

    public int getPercentPhysicalDefense(){
        return (int) Math.abs((100 / (1 + (this.physicalDefense / 25.0)))-100);
    }
    public int getPhysicalDefense(){
        return physicalDefense;
    }
    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public int getPercentMagicDefense(){
        return (int) Math.abs((100 / (1 + (this.magicDefense / 25.0)))-100);
    }
    public int getMagicalDefense(){
        return magicDefense;
    }
    public void setMagicalDefense(int magicDefense) {
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

    public int getEvasion(){
        return evasion;
    }
    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

}
