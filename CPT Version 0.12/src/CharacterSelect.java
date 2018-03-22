//AUSTIN JONES AND JUAN MARIN
//Import Required Packages

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CharacterSelect extends JPanel implements ActionListener {

	// Declare Global variables
	ImageIcon[] portraits;
	JTextField txtName;
	JLabel lblPortrait, lblPoints;
	JButton btnCreate, btnLoad;
	JComboBox<String> cboChar;
	JLabel[] stats;
	JComboBox[] cboStats;
	int totalStats;
	JFrame frame;

	public static Player player;

	public static void main(String[] args) {
		// start program with a Character selection screen
		new CharacterSelect();
	}

	public CharacterSelect() {

		// make totalStats equal to 0
		totalStats = 0;
		

		// create a new instance of a player
		player = new Player();

		// set background of the JPanel to black
		this.setBackground(Color.black);

		// create a new array of image icons used as portraits
		portraits = new ImageIcon[4];
		portraits[0] = new ImageIcon("images\\portraits\\player1Male.png");
		portraits[1] = new ImageIcon("images\\portraits\\player2Male.png");
		portraits[2] = new ImageIcon("images\\portraits\\player3Female.png");
		portraits[3] = new ImageIcon("images\\portraits\\player4Female.png");

		// create an array of string stat names
		String[] statNames = { "HP", "STR", "DEF", "INT", "M.DEF" };

		// intialize 5 spots int the stats array
		stats = new JLabel[5];

		// for loop to intialize each jlabel in the stats array, set their
		// foreground color to white and set the text to corresponding stat
		// names
		for (int i = 0; i < stats.length; i++) {

			stats[i] = new JLabel();
			stats[i].setForeground(Color.white);
			stats[i].setText(statNames[i]);
		}

		// create an array of 5 JComboboxes
		cboStats = new JComboBox[5];

		// add 5 items to the first jcombobox in the array, which contains
		// determines the health value of the player
		cboStats[0] = new JComboBox<Integer>();
		cboStats[0].setPreferredSize(new Dimension(50, 20));
		cboStats[0].addItem(100 + "(1)");
		cboStats[0].addItem(200);
		cboStats[0].addItem(300);
		cboStats[0].addItem(400);
		cboStats[0].addItem(500);
		cboStats[0].addActionListener(this);

		// for the rest of the jcomboboxes, intialize a new jcombobox and set
		// their propertires
		// its properties
		for (int i = 1; i < cboStats.length; i++) {
			cboStats[i] = new JComboBox<Integer>();
			cboStats[i].setPreferredSize(new Dimension(50, 20));
			for (int j = 1; j <= 25; j++) {
				cboStats[i].addItem(j);
			}
			cboStats[i].addActionListener(this);

		}

		// create a new jlabel for the title and set its properties
		JLabel lblTitle = new JLabel("CREATE YOUR CHARACTER:");
		lblTitle.setFont(new Font("Courier New", Font.BOLD, 23));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.white);

		// create a new jlabel for choose your name and set its properties
		JLabel lblN = new JLabel("Choose your Name:");
		lblN.setPreferredSize(new Dimension(150, 25));
		lblN.setForeground(Color.white);

		// create a new jlabel to tell the player they have 30 points to assign
		// and set its properties
		lblPoints = new JLabel("YOU HAVE 30 POINTS TO ASSIGN!");
		lblPoints.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblPoints.setForeground(Color.white);
		lblPoints.setHorizontalAlignment(SwingConstants.RIGHT);

		// create a new jtextfield for the player to enter their name and set
		// its properties and set a default name within the text box
		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(150, 25));
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setPreferredSize(new Dimension(150, 25));
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setText("Danam");
		txtName.requestFocus();
		txtName.selectAll();

		// create a new jlabel prompting the user to choose their character and
		// set its properties
		JLabel lblC = new JLabel("Choose your Character:");
		lblC.setPreferredSize(new Dimension(150, 25));
		lblC.setForeground(Color.white);

		// create a new jcombobox for the gender the player wants and add items
		cboChar = new JComboBox<String>();
		cboChar.setPreferredSize(new Dimension(150, 25));
		cboChar.addItem("Male 1");
		cboChar.addItem("Male 2");
		cboChar.addItem("Female 1");
		cboChar.addItem("Female 2");
		cboChar.addActionListener(this);

		// create a jlabel that says your current stats and set its foreground
		// to white
		JLabel lblStats = new JLabel("Your Current Stats:");
		lblStats.setForeground(Color.white);

		// create a new jlabel for the player label and set its properties
		lblPortrait = new JLabel();
		lblPortrait.setPreferredSize(new Dimension(portraits[1].getIconWidth(), portraits[0].getIconHeight()));
		lblPortrait.setBorder(BorderFactory.createLineBorder(Color.black));
		lblPortrait.setIcon(portraits[0]);

		// create a new jbutton and set its properties
		btnCreate = new JButton("CREATE");
		btnCreate.setPreferredSize(new Dimension(80, 30));
		btnCreate.addActionListener(this);

		// create a new jbutton and set its properties
		btnLoad = new JButton("LOAD");
		btnLoad.setPreferredSize(new Dimension(80, 30));
		btnLoad.addActionListener(this);

		// create a new jpanel that will hold the combo box stats and jlabels
		// corresponding to those components
		JPanel statPanel = new JPanel();
		statPanel.setBackground(Color.black);
		statPanel.setLayout(new GridLayout(5, 2));
		statPanel.setAlignmentY(CENTER_ALIGNMENT);
		for (int i = 0; i < stats.length; i++) {
			statPanel.add(stats[i]);
			statPanel.add(cboStats[i]);
		}

		// create a new JPanel that sets the layout to a gridbaglayout, sets the
		// background color to white create a new gridbagconstraints then add
		// all the components needed onto this panel
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		pane.setBackground(Color.black);
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets(10, 5, 10, 5);
		gc.gridwidth = 2;
		gc.gridx = 0;
		gc.gridy = 0;
		pane.add(lblTitle, gc);
		gc.gridwidth = 1;
		gc.gridy = 1;
		pane.add(lblN, gc);
		gc.gridx = 1;
		pane.add(txtName, gc);
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = 0;
		gc.gridy = 2;
		pane.add(lblC, gc);
		gc.gridx = 1;
		pane.add(cboChar, gc);
		gc.gridx = 0;
		gc.gridy = 3;
		pane.add(lblStats, gc);
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 4;
		pane.add(lblPortrait, gc);
		gc.gridx = 0;
		pane.add(statPanel, gc);
		gc.anchor = GridBagConstraints.EAST;
		gc.gridx = 0;
		gc.gridy = 5;
		pane.add(lblPoints, gc);
		gc.anchor = GridBagConstraints.SOUTHEAST;
		gc.gridx = 0;
		gc.gridy = 6;
		pane.add(btnCreate, gc);
		gc.anchor = GridBagConstraints.SOUTHWEST;
		gc.gridx = 1;
		pane.add(btnLoad, gc);

		// add the previous pane to the jlabel then create a new jframe which
		// uses this as its content pane and then set the properties of the
		// jframe
		this.add(pane);
		frame = new JFrame();
		frame.setContentPane(this);
		frame.setTitle("CHARACTER SELECTION");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		// if the trigger action was cboChar, get the selected index, then set
		// the icon of lblportrait to a corresponding image
		if (e.getSource() == cboChar) {
			if (cboChar.getSelectedIndex() == 0) {
				lblPortrait.setIcon(portraits[0]);
				player.setGender(Player.MALE);
			} else if (cboChar.getSelectedIndex() == 1) {
				lblPortrait.setIcon(portraits[1]);
				player.setGender(Player.MALE);
			} else if (cboChar.getSelectedIndex() == 2) {
				lblPortrait.setIcon(portraits[2]);
				player.setGender(Player.FEMALE);
			} else {
				lblPortrait.setIcon(portraits[3]);
				player.setGender(Player.FEMALE);
			}

			// else if it is ANY of the cboStats combo boxes, set the matching
			// stat of
			// the player to the corresponding value within each combo box
		} else if (e.getSource() == cboStats[1]) {
			player.setStrength(cboStats[1].getSelectedIndex() + 1);
		} else if (e.getSource() == cboStats[2]) {
			player.setDefense(cboStats[2].getSelectedIndex() + 1);
		} else if (e.getSource() == cboStats[3]) {
			player.setIntelligence(cboStats[3].getSelectedIndex() + 1);
		} else if (e.getSource() == cboStats[4]) {
			player.setMagicDefense(cboStats[4].getSelectedIndex() + 1);
		} else if (e.getSource() == cboStats[0]) {
			player.setHealth((cboStats[0].getSelectedIndex() + 1) * 100);

			// else if it is the create button
		} else if (e.getSource() == btnCreate) {

			// create a new boolean
			boolean create = false;

			// set total stats to 0
			totalStats = 0;

			// calculate the player's total ASSIGNED stats(not including 1)
			totalStats = player.getStrength() - 1 + player.getDefense() - 1 + player.getIntelligence() - 1
					+ player.getMagicDefense() - 1 + cboStats[0].getSelectedIndex();

			// if their total assigned points is greater than 30, prompt the
			// user to reassign their stats
			if (totalStats > 30) {
				JOptionPane.showMessageDialog(null, "Your total assigned stat points is " + totalStats
						+ "! Please make sure this number is" + " less than or equivalent to 30!");
				// else if it is less than 30, prompt the user that the game
				// will be made difficult because of their current amount of
				// assigned player stats and ask if they wish to continue with
				// this
			} else if (totalStats < 30) {
				int result = JOptionPane.showConfirmDialog(null,
						"Your total assigned stat points is " + totalStats + "! Leaving it this way"
								+ " will make the game more difficult. Are you sure you wish to continue?",
						"Character Selection", JOptionPane.YES_NO_OPTION);

				// if the player answers yes, create is made to true, else
				// create is set to f alse
				if (result == JOptionPane.YES_OPTION) {
					create = true;
				} else {
					create = false;
				}
			}
			// otherwise, if there are no complications with the player's
			// assigned stat points, set create to true
			else {
				create = true;
			}

			// create is equal to true
			if (create == true) {

				// set the name and portrait of the player
				player.setName(txtName.getText());
				player.setPortrait(lblPortrait.getIcon());

				// create a new image
				ImageIcon img = new ImageIcon("images\\sword.png");

				// prompt the player if they wish to start their adventure and
				// output what their current stats are one more before
				// continuing onto the game
				int result = JOptionPane.showConfirmDialog(null,
						"Are you ready to start your adventure " + player.getName() + "? Your current stats are:\n"
								+ "Health: " + player.getHealth() + "\nStrength: " + player.getStrength()
								+ "\nDefense: " + player.getDefense() + "\nIntelligence: " + player.getIntelligence()
								+ "\nMagic Defense: " + player.getMagicDefense(),
						"Begin your Adventure", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, img);

				// if the user hits YES, open up the interface program
				if (result == JOptionPane.YES_OPTION) {

					new Interface();

					// create a new file named character.txt
					File f = new File("character.txt");

					try {
						// declare and initialize a new buffered writer to the
						// file name character.txt
						BufferedWriter out = new BufferedWriter(new FileWriter(f));

						// write the player's name then a new line, then ALL the
						// stats and portrait number of the player all
						// comma-delimited
						out.write(player.getName());
						out.newLine();
						out.write(Integer.toString(player.getHealth()));
						out.write(",");
						out.write(Integer.toString(player.getStrength()));
						out.write(",");
						out.write(Integer.toString(player.getDefense()));
						out.write(",");
						out.write(Integer.toString(player.getIntelligence()));
						out.write(",");
						out.write(Integer.toString(player.getMagicDefense()));
						out.write(",");
						out.write(Integer.toString(cboChar.getSelectedIndex()));

						// close the writer
						out.close();

					} catch (IOException e1) {

						// if an error occurs, print a message
						System.out.println(e1.getMessage());

					}

					// dispose of this frame
					frame.dispose();

				} else {
				}

			}

			// if the user wishes to load a character
		} else if (e.getSource() == btnLoad) {

			// create a new jfilechooser object that starts in the files
			// directory and add a new text file filter
			JFileChooser fc = new JFileChooser("...");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
			fc.addChoosableFileFilter(filter);

			// show an openDialog
			int result = fc.showOpenDialog(this);

			// if they choose a file
			if (result == JFileChooser.APPROVE_OPTION) {
				try {

					// create a new buffered reader for the chosen file
					BufferedReader in = new BufferedReader(new FileReader(new File(fc.getSelectedFile().toString())));

					// declare a string and then an array of strings
					String line;
					String[] data;

					// read the first line
					line = in.readLine();

					// set the name of txtName to the read line
					txtName.setText(line);

					// read the next line
					line = in.readLine();

					// split this line with the regex ','
					data = line.split(",");

					// set the selected index of cboStats and cboChar to a
					// certain index read from the comma delimited file
					cboStats[0].setSelectedIndex((Integer.parseInt(data[0]) / 100) - 1);
					cboStats[1].setSelectedIndex(Integer.parseInt(data[1]) - 1);
					cboStats[2].setSelectedIndex(Integer.parseInt(data[2]) - 1);
					cboStats[3].setSelectedIndex(Integer.parseInt(data[3]) - 1);
					cboStats[4].setSelectedIndex(Integer.parseInt(data[4]) - 1);
					cboChar.setSelectedIndex(Integer.parseInt(data[5]));

					//close the reader
					in.close();

				} catch (IOException er) {

				}
			}
			//if the user hits else do nothing
		} else {
		}

	}
/**
 * End of this program
 */
}
