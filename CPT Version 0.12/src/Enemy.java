import java.util.Random;
import javax.swing.*;

public class Enemy {

	private int strength, defense, intelligence, magicDefense, health;
	private int width, height, xPos, yPos, damage;
	private ImageIcon imgBoss;
	private String name;
	private Random rnd;
	public static int choice;

	public Enemy() {
		choice = -1;
		name = "";
		xPos = 0;
		yPos = 0;
		strength = 1;
		defense = 1;
		intelligence = 1;
		magicDefense = 1;
		health = 10;
		imgBoss = null;
		width = 0;
		height = 0;
		damage = 0;
		rnd = new Random();
	}

	public Enemy(String n, int str, int def, int inte, int mDef, int hp, ImageIcon img) {
		choice = -1;
		name = n;
		xPos = 0;
		yPos = 0;
		strength = str;
		defense = def;
		intelligence = inte;
		magicDefense = mDef;
		health = hp;
		imgBoss = img;
		width = imgBoss.getIconWidth();
		height = imgBoss.getIconHeight();
		damage = 0;
		rnd = new Random();
	}

	public int attack() {

		choice = rnd.nextInt(2);

		if (choice == 0) {
			if (strength >= 7 && strength <= 22) {
				damage = rnd.nextInt(20) + 10;
			}

			else if (strength >= 23 && strength <= 27) {
				damage = rnd.nextInt(10) + 30;
			}
		} else {

			if (intelligence >= 7 && intelligence <= 22) {
				damage = rnd.nextInt(20) + 10;
			}

			else if (intelligence >= 23 && intelligence <= 27) {
				damage = rnd.nextInt(10) + 30;
			}

		}
		return damage;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setXPos(int x) {
		xPos = x;
	}

	public void setYPos(int y) {
		yPos = y;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setBossImage(Icon icon) {
		imgBoss = (ImageIcon) icon;
	}

	public ImageIcon getBossImage() {
		return imgBoss;
	}

	public void setStrength(int s) {

		strength = s;

	}

	public int getStrength() {
		return strength;
	}

	public void setDefense(int d) {

		defense = d;

	}

	public int getDefense() {
		return defense;
	}

	public void setIntelligence(int i) {

		intelligence = i;

	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setMagicDefense(int md) {

		magicDefense = md;

	}

	public int getMagicDefense() {
		return magicDefense;
	}

	public void setHealth(int h) {

		health = h;

	}

	public int getHealth() {
		return health;
	}

}
