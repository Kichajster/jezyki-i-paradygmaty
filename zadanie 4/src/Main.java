abstract class Character {

    int hp;
    int mp;
    int ad;
    int def;

    public Character(int hp, int mp, int ad, int def) {
        this.hp = hp;
        this.mp = mp;
        this.ad = ad;
        this.def = def;
    }

    public void type() {
        System.out.println("Class: not definied.");
    }

    public void skill() {
        System.out.println("Skill: not definied.");
    }

    public void showCharacter() {
        type();
        System.out.println("HP: " + hp);
        System.out.println("MP: " + mp);
        System.out.println("AD: " + ad);
        System.out.println("DEF: " + def);
    }
}

class Mage extends Character {
    public Mage(int hp, int mp, int ad, int def) {
        super(hp, mp, ad, def);
    }

    @Override
    public void showCharacter() {
        type();
        System.out.println("HP: " + hp);
        System.out.println("MP: " + mp);
        System.out.println("AD: " + ad);
        System.out.println("DEF: " + def);
        skill();
        System.out.println();
    }
}

final class FireMage extends Mage {
    public FireMage(int hp, int mp, int ad, int def) {
        super(hp, mp, ad, def);
    }

    @Override
    public void type() {
        System.out.println("Class: Fire Mage");
    }

    public void skill() {
        System.out.println("Skill: Fireball");
    }
}

final class IceMage extends Mage {
    public IceMage(int hp, int mp, int ad, int def) {
        super(hp, mp, ad, def);
    }

    @Override
    public void type() {
        System.out.println("Class: Ice Mage");
    }

    public void skill() {
        System.out.println("Skill: Frostbolt");
    }
}

class Warrior extends Character {
    String weapon;

    public Warrior(int hp, int mp, int ad, int def, String weapon) {
        super(hp, mp, ad, def);
        this.weapon = weapon;
    }

    public void attack() {
        System.out.println("Wields a: " + weapon);
    }

    @Override
    public void showCharacter() {
        type();
        System.out.println("HP: " + hp);
        System.out.println("MP: " + mp);
        System.out.println("AD: " + ad);
        System.out.println("DEF: " + def);
        attack();
        skill();
        System.out.println();
    }
}


final class Fighter extends Warrior {
    public Fighter(int hp, int mp, int ad, int def, String weapon) {
        super(hp, mp, ad, def, weapon);
    }

    @Override
    public void type() {
        System.out.println("Class: Fighter");
    }

    public void skill() {
        System.out.println("Skill: Rage");
    }
}

final class Tank extends Warrior {
    public Tank(int hp, int mp, int ad, int def, String weapon) {
        super(hp, mp, ad, def, weapon);
    }

    @Override
    public void type() {
        System.out.println("Class: Tank");
    }

    public void skill() {
        System.out.println("Skill: Protection");
    }
}


public class Main {
    public static void main(String[] args) {
        Character fireMage = new FireMage(50, 200, 50, 20);
        Character iceMage = new IceMage(50, 300, 50, 10);
        Character tank = new Tank(100, 100, 75, 75, "Longsword");
        Character fighter = new Fighter(120, 800, 100, 50, "Battleaxe");

        fireMage.showCharacter();
        iceMage.showCharacter();
        tank.showCharacter();
        fighter.showCharacter();

        System.out.println("Czy zmienna 'fighter' jest rozszerzeniem klasy 'Mage'?" );
        System.out.println(fighter instanceof Mage);
        System.out.println("Czy zmienna 'fighter' jest rozszerzeniem klasy 'Warrior'?" );
        System.out.println(fighter instanceof Warrior);
        System.out.println("Czy zmienna 'tank' jest rozszerzeniem klasy 'Character'?");
        System.out.println(tank instanceof Character);
        System.out.println("Czy zmienna 'iceMage' jest rozszerzeniem klasy 'Warrior?");
        System.out.println(iceMage instanceof Warrior);

    }
}
