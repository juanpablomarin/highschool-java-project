import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import sun.audio.*;
import javax.swing.*;

public class Interface extends JFrame implements ActionListener {

	private JPanel mainPanel, battlePanel, animatePanel;
	private JLabel enemyName, lblEHP, lblInfo, lblHP, lblName;
	private JButton btnAttack, btnMagic, btnGuard;
	private Font infoFont, nameFont;
	private Enemy boss;
	private Timer idleTimer, attackTimer, delayTimer;
	private ImageIcon[] imgBoss;
	private final int BOSS_X, BOSS_Y;
	private Random rnd;
	private String[] name, spells;
	private int[] stats;
	private int bossNumber, tick, bossDamage;
	private Boolean guard = false, guardImg = false;

	private AudioStream auStream, auStream2, auStream3;
	private AudioPlayer auPlayer;
	private InputStream in, in2, in3;

	public Interface() {

		auPlayer = AudioPlayer.player;

		try {
			in = new FileInputStream(new File("bgm.wav"));
			in2 = new FileInputStream(new File("victory.wav"));
			in3 = new FileInputStream(new File("gameover.wav"));
			auStream = new AudioStream(in);
			auStream2 = new AudioStream(in2);
			auStream3 = new AudioStream(in3);
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		auPlayer.start(auStream);

		tick = 0;

		rnd = new Random();

		idleTimer = new Timer(350, this);
		attackTimer = new Timer(150, this);
		delayTimer = new Timer(3000, this);

		BOSS_X = 100;
		BOSS_Y = 150;

		spells = new String[5];

		spells[0] = "Lightning Storm";
		spells[1] = "Ice Lance";
		spells[2] = "Lava Shock";
		spells[3] = "Windfury";
		spells[4] = "Arcane Missile";

		name = new String[5];
		name[0] = "Behemoth";
		name[1] = "White Dragon";
		name[2] = "Gilgamesh";
		name[3] = "Giant";
		name[4] = "Shinryu";

		imgBoss = new ImageIcon[5];
		imgBoss[0] = new ImageIcon("images\\boss_images\\behemoth.png");
		imgBoss[1] = new ImageIcon("images\\boss_images\\dragon.png");
		imgBoss[2] = new ImageIcon("images\\boss_images\\gilgamesh.png");
		imgBoss[3] = new ImageIcon("images\\boss_images\\giant.png");
		imgBoss[4] = new ImageIcon("images\\boss_images\\shinryu.png");

		bossNumber = rnd.nextInt(5);

		stats = new int[4];

		for (int i = 0; i < stats.length; i++) {
			stats[i] = rnd.nextInt(20) + 7;
		}

		boss = new Enemy(name[bossNumber], stats[0], stats[1], stats[2], stats[3], 5, imgBoss[bossNumber]);

		CharacterSelect.player.setXPos(650);
		CharacterSelect.player.setYPos(BOSS_Y + 90);

		try {
			infoFont = Font.createFont(Font.TRUETYPE_FONT, new File("gameFont.ttf")).deriveFont(21f);
			nameFont = Font.createFont(Font.TRUETYPE_FONT, new File("gameFont.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		}

		catch (IOException | FontFormatException e) {
		}

		mainPanel = new JPanel();

		battlePanel = new JPanel();
		battlePanel.setLayout(null);
		battlePanel.setBackground(Color.black);
		battlePanel.setPreferredSize(new Dimension(800, 175));
		battlePanel.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),
						"BATTLE SCREEN", 0, 0, nameFont, Color.white), BorderFactory.createLineBorder(Color.white, 5)));

		enemyName = new JLabel();
		enemyName.setForeground(Color.white);
		enemyName.setOpaque(false);
		enemyName.setFont(nameFont);
		enemyName.setText(boss.getName());
		enemyName.setBounds(15, battlePanel.getY() + 20, 200, 50);

		lblEHP = new JLabel();
		lblEHP.setForeground(Color.white);
		lblEHP.setOpaque(false);
		lblEHP.setFont(nameFont);
		lblEHP.setText("HP: " + boss.getHealth());
		lblEHP.setBounds(15, battlePanel.getY() + 50, 200, 50);

		lblName = new JLabel(CharacterSelect.player.getName());
		lblName.setForeground(Color.white);
		lblName.setOpaque(false);
		lblName.setFont(nameFont);
		lblName.setBounds(500, battlePanel.getY() + 20, 200, 50);

		lblHP = new JLabel();
		lblHP.setForeground(Color.white);
		lblHP.setOpaque(false);
		lblHP.setText("HP: " + CharacterSelect.player.getHealth());
		lblHP.setFont(nameFont);
		lblHP.setBounds(500, lblName.getY() + 30, 200, 50);

		btnAttack = new JButton("ATTACK");
		btnAttack.setBorderPainted(false);
		btnAttack.setBounds(800 / 2 - 63, battlePanel.getY() + 60, 125, 25);
		btnAttack.setFont(nameFont);
		btnAttack.setBackground(Color.black);
		btnAttack.setForeground(Color.white);
		btnAttack.addActionListener(this);

		btnGuard = new JButton("GUARD");
		btnGuard.setBorderPainted(false);
		btnGuard.setBounds(800 / 2 - 63, btnAttack.getY() + 30, 125, 25);
		btnGuard.setFont(nameFont);
		btnGuard.setBackground(Color.black);
		btnGuard.setForeground(Color.white);
		btnGuard.addActionListener(this);

		btnMagic = new JButton("MAGIC");
		btnMagic.setBorderPainted(false);
		btnMagic.setBounds(800 / 2 - 63, btnGuard.getY() + 30, 125, 25);
		btnMagic.setFont(nameFont);
		btnMagic.setBackground(Color.black);
		btnMagic.setForeground(Color.white);
		btnMagic.addActionListener(this);

		lblInfo = new JLabel();
		lblInfo.setPreferredSize(new Dimension(300, 80));
		lblInfo.setLocation(800 / 2 - 150, 100);
		lblInfo.setFont(infoFont);
		lblInfo.setText("Select your action!");
		lblInfo.setOpaque(true);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBackground(Color.black);
		lblInfo.setForeground(Color.white);
		lblInfo.setBorder(BorderFactory.createLineBorder(Color.white, 5, true));

		battlePanel.add(enemyName);
		battlePanel.add(lblEHP);
		battlePanel.add(lblHP);
		battlePanel.add(lblName);
		battlePanel.add(btnAttack);
		battlePanel.add(btnGuard);
		battlePanel.add(btnMagic);

		animatePanel = new JPanel();
		animatePanel.setLayout(null);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(animatePanel, BorderLayout.CENTER);
		mainPanel.add(lblInfo, BorderLayout.NORTH);
		mainPanel.add(battlePanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		setSize(800, 600);
		setTitle("Interface");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(false);
		setVisible(true);

		idleTimer.start();
	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(new ImageIcon("background\\dungeon2.png").getImage(), 0, animatePanel.getY() + 25,
				animatePanel.getWidth(), animatePanel.getHeight(), animatePanel);

		g2.drawImage(boss.getBossImage().getImage(), BOSS_X, BOSS_Y + 25, boss.getBossImage().getIconWidth(),
				boss.getBossImage().getIconHeight(), animatePanel);

		if (idleTimer.isRunning()) {
			CharacterSelect.player.drawIdle(g2);
		}

		if (attackTimer.isRunning()) {
			CharacterSelect.player.drawAttack(g2);
		}

		if (guardImg == true) {
			CharacterSelect.player.drawStaticImage(g2);
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == idleTimer) {
			Player.index++;

		}

		if (e.getSource() == attackTimer) {
			Player.atkIndex++;

			if (Player.atkIndex == 7) {
				attackTimer.stop();
				Player.atkIndex = 0;
				idleTimer.start();

			}

		}

		if (e.getSource() == delayTimer) {

			if (tick == 1) {

				guardImg = false;
				idleTimer.start();
				btnAttack.setEnabled(true);
				btnGuard.setEnabled(true);
				btnMagic.setEnabled(true);
				delayTimer.stop();
			}

			if (tick == 0) {

				bossDamage = boss.attack();

				if (Enemy.choice == 0) {
					bossDamage = bossDamage - CharacterSelect.player.getDefense();
				} else {
					bossDamage = bossDamage - CharacterSelect.player.getMagicDefense();
				}

				if (bossDamage <= 0) {
					bossDamage = 5;
				}

				if (guard == true) {

					guard = false;

					CharacterSelect.player.setDefense(CharacterSelect.player.getDefense() - 10);
					CharacterSelect.player.setMagicDefense(CharacterSelect.player.getMagicDefense() - 10);
					idleTimer.stop();

				}

				if (CharacterSelect.player.getHealth() - bossDamage <= 0) {
					idleTimer.stop();
					delayTimer.stop();
					attackTimer.stop();

					lblHP.setText("HP: 0");
					lblInfo.setText(CharacterSelect.player.getName() + " has been defeated!");

					auPlayer.stop(auStream);
					auPlayer.start(auStream3);

					JOptionPane.showMessageDialog(null,
							"Defeat! " + CharacterSelect.player.getName() + " has been defeated by " + boss.getName()
									+ "!\n You have failed your quest "
									+ "and were unable to vanquish this evil creature! Please hit OK to finish your journey.",
							"Defeat!", JOptionPane.ERROR_MESSAGE);

					System.exit(0);

				}

				if (Enemy.choice == 0) {
					CharacterSelect.player.setHealth(CharacterSelect.player.getHealth() - bossDamage);
					lblInfo.setText(CharacterSelect.player.getName() + " took " + bossDamage + " damage!");
					lblHP.setText("HP: " + CharacterSelect.player.getHealth());

				}

				if (Enemy.choice == 1) {

					int spellNumber;
					spellNumber = rnd.nextInt(5);
					CharacterSelect.player.setHealth(CharacterSelect.player.getHealth() - bossDamage);
					lblInfo.setText(boss.getName() + "'s " + spells[spellNumber] + " did " + bossDamage + " damage!");
					lblHP.setText("HP: " + CharacterSelect.player.getHealth());
				}

				tick = 1;

			}

		}

		if (e.getSource() == btnAttack) {

			tick = 0;

			idleTimer.stop();
			attackTimer.start();

			int damage = CharacterSelect.player.attack();

			damage = damage - (boss.getDefense() * 10);

			if (damage <= 0) {
				damage = 0;
			}

			boss.setHealth(boss.getHealth() - damage);

			if (Player.crit == true) {
				lblInfo.setText("CRITICAL HIT! " + boss.getName() + " took " + damage + " damage!");
			} else {

				lblInfo.setText(boss.getName() + " took " + damage + " damage!");
			}

			if ((boss.getHealth() - damage) <= 0) {

				idleTimer.stop();
				attackTimer.stop();
				delayTimer.stop();

				auPlayer.stop(auStream);
				auPlayer.start(auStream2);

				lblEHP.setText("HP: 0");

				JOptionPane.showMessageDialog(null,
						"Congratulations " + CharacterSelect.player.getName() + "! You've defeated the "
								+ boss.getName() + "!\n You have completed your quest "
								+ "and vanquished this evil creature! Please hit OK to finish your journey.",
						"Victory!", JOptionPane.INFORMATION_MESSAGE);

				System.exit(0);
			}

			lblEHP.setText("HP: " + boss.getHealth());

			btnAttack.setEnabled(false);
			btnGuard.setEnabled(false);
			btnMagic.setEnabled(false);

			delayTimer.start();

		}

		if (e.getSource() == btnMagic) {
			tick = 0;

			idleTimer.stop();
			attackTimer.start();

			int damage = CharacterSelect.player.magicAttack();
			int spellNumber;

			spellNumber = rnd.nextInt(5);

			damage = damage - (boss.getMagicDefense() * 10);

			if (damage <= 0) {
				damage = 0;
			}

			boss.setHealth(boss.getHealth() - damage);

			if (Player.crit == true) {
				lblInfo.setText(CharacterSelect.player.getName() + "'s Twisting Nether did " + damage + " damage!");
			} else {
				lblInfo.setText(
						CharacterSelect.player.getName() + "'s " + spells[spellNumber] + " did " + damage + " damage!");
			}

			if ((boss.getHealth() - damage) <= 0) {

				idleTimer.stop();
				attackTimer.stop();
				delayTimer.stop();

				auPlayer.stop(auStream);
				auPlayer.start(auStream2);

				lblEHP.setText("HP: 0");

				JOptionPane.showMessageDialog(null,
						"Congratulations " + CharacterSelect.player.getName() + "! You've defeated the "
								+ boss.getName() + "! You have completed your quest "
								+ "and vanquished this evil creature! Please hit OK to finish your journey.",
						"Victory!", JOptionPane.OK_OPTION);

				System.exit(0);
			}

			lblEHP.setText("HP: " + boss.getHealth());

			btnAttack.setEnabled(false);
			btnGuard.setEnabled(false);
			btnMagic.setEnabled(false);

			delayTimer.start();
		}

		if (e.getSource() == btnGuard) {

			guard = true;
			guardImg = true;

			tick = 0;

			lblInfo.setText("You brace yourself for the next attack!");

			CharacterSelect.player.setDefense(CharacterSelect.player.getDefense() + 10);
			CharacterSelect.player.setMagicDefense(CharacterSelect.player.getMagicDefense() + 10);

			idleTimer.stop();

			btnAttack.setEnabled(false);
			btnGuard.setEnabled(false);
			btnMagic.setEnabled(false);

			delayTimer.start();

		}

		repaint();
	}
}
