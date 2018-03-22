
/**
 * Import the required classes
 */
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.*;

/**
 * This is a player class with a wide variety of stats that will be used for
 * calculating attack daamges, and will also be used to draw images
 */

public class Player{

	/**
	 * Declare the fields
	 */
	private int strength, defense, intelligence, magicDefense, health, gender, damage;
	private int width, height, xPos, yPos;
	private ImageIcon imgPortrait;
	private String name;
	public static final int MALE = 0;
	public static final int FEMALE = 1;
	private ImageIcon[] maleIdle, femaleIdle, maleAttack, femaleAttack;
	public static int index, atkIndex;
	public static boolean crit;
	private Random rnd;

	/**
	 * No arg constructor that assigns default values to 
	 */
	public Player() {
		crit = false;
		name = "Danam";
		damage = 0;
		rnd = new Random();
		index = 0;
		atkIndex = 0;
		xPos = 0;
		yPos = 0;
		strength = 1;
		defense = 1;
		intelligence = 1;
		magicDefense = 1;
		health = 100;
		imgPortrait = null;
		loadImages();
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

	public void setPortrait(Icon icon) {
		imgPortrait = (ImageIcon) icon;
	}

	public ImageIcon getPortrait() {
		return imgPortrait;
	}

	public void drawStaticImage(Graphics2D g2) {

		if (gender == MALE) {
			g2.drawImage(maleIdle[0].getImage(), xPos, yPos, width, height, null);
		} else {
			g2.drawImage(femaleIdle[0].getImage(), xPos, yPos, width, height, null);
		}

	}

	public void setStrength(int s) {

		strength = s;

	}

	public int magicAttack() {

		int critChance = rnd.nextInt(20) + 1;
		crit = false;

		if (intelligence > 0 && intelligence <= 5) {
			damage = rnd.nextInt(61) + 40;

			if (damage > 40 && damage <= 49) {

				damage = 0;
			}
		} else if (intelligence > 0 && intelligence <= 5) {
			damage = rnd.nextInt(50) + 125;
		} else if (intelligence > 5 && intelligence <= 10) {
			damage = rnd.nextInt(51) + 200;
		} else if (intelligence > 10 && intelligence <= 15) {
			damage = rnd.nextInt(51) + 250;
		} else if (intelligence > 15 && intelligence <= 20) {
			damage = rnd.nextInt(51) + 325;
		} else if (intelligence > 20 && intelligence <= 25) {
			damage = rnd.nextInt(101) + 450;
		}

		if (critChance == 20) {
			crit = true;
			return damage * 2;

		} else {
			return damage;
		}
	}

	public int attack() {

		int critChance = rnd.nextInt(20) + 1;
		crit = false;

		if (strength > 0 && strength <= 5) {
			damage = rnd.nextInt(61) + 40;

			if (damage > 40 && damage <= 49) {

				damage = 0;
			}
		} else if (strength > 0 && strength <= 5) {
			damage = rnd.nextInt(50) + 125;
		} else if (strength > 5 && strength <= 10) {
			damage = rnd.nextInt(51) + 200;
		} else if (strength > 10 && strength <= 15) {
			damage = rnd.nextInt(51) + 250;
		} else if (strength > 15 && strength <= 20) {
			damage = rnd.nextInt(51) + 325;
		} else if (strength > 20 && strength <= 25) {
			damage = rnd.nextInt(101) + 450;
		}

		if (critChance == 20) {
			crit = true;
			return damage * 2;

		} else {
			return damage;
		}
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

	public void setGender(int i) {

		if (i == MALE) {
			gender = MALE;
		}
		if (i == FEMALE) {
			gender = FEMALE;
		}

	}

	public int getGender() {

		return gender;

	}

	private void loadImages() {

		maleAttack = new ImageIcon[8];
		for (int i = 0; i < maleAttack.length; i++) {
			maleAttack[i] = new ImageIcon("images\\male_attack\\attack" + (i + 1) + ".png");
		}

		femaleAttack = new ImageIcon[8];
		for (int i = 0; i < femaleAttack.length; i++) {
			femaleAttack[i] = new ImageIcon("images\\female_attack\\attack" + (i + 1) + ".png");
		}

		maleIdle = new ImageIcon[4];
		for (int i = 0; i < maleIdle.length; i++) {
			maleIdle[i] = new ImageIcon("images\\idle_male_1\\idle" + (i + 1) + ".png");
		}
		femaleIdle = new ImageIcon[4];
		for (int i = 0; i < femaleIdle.length; i++) {
			femaleIdle[i] = new ImageIcon("images\\idle_female_1\\idle" + (i + 1) + ".png");
		}

	}

	public void drawAttack(Graphics2D g2) {

		if (gender == FEMALE) {
			width = 50;
			height = 100;
			if (atkIndex >= 8) {
				atkIndex = 0;
			}

			g2.drawImage(femaleAttack[atkIndex].getImage(), xPos, yPos, width, height, null);
		}

		if (gender == MALE) {

			width = 50;
			height = 100;
			if (atkIndex >= 8) {
				atkIndex = 0;
			}
			g2.drawImage(maleAttack[atkIndex].getImage(), xPos, yPos, width, height, null);

		}

	}

	public void drawIdle(Graphics2D g2) {

		if (gender == FEMALE) {
			width = 50;
			height = 100;
			if (index >= 4) {
				index = 0;
			}
			g2.drawImage(femaleIdle[index].getImage(), xPos, yPos, width, height, null);
		}

		if (gender == MALE) {

			width = 50;
			height = 100;
			if (index >= 4) {
				index = 0;
			}
			g2.drawImage(maleIdle[index].getImage(), xPos, yPos, width, height, null);

		}

	}

}
