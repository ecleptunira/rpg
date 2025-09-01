package project.rpg;

public class Game {
    public static void main(String[] args) {
        System.out.println();
        Information.limparTela();
        Hero hero = new Hero("Hero", 30, 150, 1, 0);
        Monster monster = new Monster("Monster", 20, 200, 0, 0);

        int heroWins = 0;
        int monsterWins = 0;
        int fights = 0;
        int turn = 0;

        System.out.println("=================Battle Start!=================");

        boolean replay = true;

        if (replay){
            do{
                turn++;
                System.out.println();
                System.out.println("================Turn " + turn + "================");
                System.out.println("Hero turn:");
                int heroAttack = (int) (Math.random() * 10);
                if (heroAttack <= 0.5) {
                    hero.divineSword(monster);
                } else {
                    hero.attack(monster);
                }
                if (monster.getLife() <= 0) {
                    Information.characterDead(monster);
                    heroWins++; fights++; turn = 0;
                    hero = new Hero("Hero", 30, 150, 1, 0);
                    monster = new Monster("Monster", 20, 200, 0, 0);
                    //Information.pressEnterToContinue();
                }

                System.out.println();
                System.out.println("Monster turn:");
                int monsterAttack = (int) (Math.random() * 10);
                if (monsterAttack <= 5 && monster.getLife() > 0) {
                    monster.stomp(hero);
                } else if (monster.getLife() > 0) {
                    monster.attack(hero);
                }
                if (hero.getLife() <= 0) {
                    Information.characterDead(hero);
                    monsterWins++; fights++; turn = 0;
                    hero = new Hero("Hero", 30, 150, 1, 0);
                    monster = new Monster("Monster", 20, 200, 0, 0);
                    //Information.pressEnterToContinue();
                }
                System.out.println();
            }
            while (fights < 1); //mudar para 5 ou mais para mais lutas
        }
        if (replay){
        System.out.println("==============Battle Over!==============");
        System.out.println("Total fights: " + fights);
        System.out.println(hero.getName() + " wins: " + heroWins);
        System.out.println(monster.getName() + " wins: " + monsterWins);
        System.out.println("Draws: " + (fights - (heroWins + monsterWins)));
        System.out.println("========================================");
        }
    }
}