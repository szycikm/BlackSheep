package stuff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import animals.Antelope;
import animals.Fox;
import animals.HumanTasks;
import animals.Sheep;
import animals.Turtle;
import animals.Wolf;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import plants.Dairy;
import plants.Grass;
import plants.Guarana;
import plants.SosnowskisBorsch;
import plants.WolfBerries;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import species.Organism;
import animals.Human;

public class BestGui
{
	private static final int WORLD_SQUARE_MULTIPLIER = 22;
	private static final int WORLD_SQUARE_SIZE = 20;
	private static final int ANIMAL_START_MAX = 7;
	private static final int PLANT_START_MAX = 2;
	
	private JFrame frmBlackSheepCome;
	private JLabel Lblroundcnt;
	private JPanel panelWorld;
	private JButton btnNextRound;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnSpecial;
	
	private int round = 0;
	private World world;
	private Coordinates worldxy;
	
	private void setHumanControlsEnabled(boolean state)
	{
		btnLeft.setEnabled(state);
		btnRight.setEnabled(state);
		btnUp.setEnabled(state);
		btnDown.setEnabled(state);
		btnSpecial.setEnabled(state);
		btnNextRound.setEnabled(!state);
	}
	
	private void doHumanTask(HumanTasks task)
	{
		Human human = world.getHuman();
		if(human.isTaskLegal(task))
		{
			human.setNextTask(task);
			Turn();
		}
		else
			Logger.writeMessage("Invalid human task");
	}
	
	private void drawWorld()
	{
		panelWorld.removeAll();
		
		for (int y = 0; y < world.getMaxXY().y; y++)
		{
			for(int x = 0; x < world.getMaxXY().x; x++)
			{
				Organism org = world.getOrganismByPosition(new Coordinates(x, y));
				if(world.getOrganismByPosition(new Coordinates(x, y)) != null)
				{
					// organism stands on this field -> draw it as a label
					JLabel stander = new JLabel(String.valueOf(org.draw()));
					stander.setBounds(x*WORLD_SQUARE_MULTIPLIER, y*WORLD_SQUARE_MULTIPLIER, WORLD_SQUARE_SIZE, WORLD_SQUARE_SIZE);
					stander.setHorizontalAlignment(SwingConstants.CENTER);
					panelWorld.add(stander);
				}
				else
				{
					// nothing stands on this field -> draw a button
					JButton plus = new JButton("+");
					plus.setBounds(x*WORLD_SQUARE_MULTIPLIER, y*WORLD_SQUARE_MULTIPLIER, WORLD_SQUARE_SIZE, WORLD_SQUARE_SIZE);
					plus.setBorder(null);
					panelWorld.add(plus);
				}
			}
		}
		
		panelWorld.revalidate();
		panelWorld.repaint();
	}
	
	private void Turn()
	{
		round++;
		Lblroundcnt.setText(String.valueOf(round));
		Logger.writeMessage("======= Round " + round + " =======");
		Logger.writeMessage("Population: " + world.getOrganismCount());

		world.doTurn();
		this.drawWorld();
		
		this.setHumanControlsEnabled(world.isHumanAlive());
		
		Logger.writeMessage("+++++ Round " + round + " ended +++++");
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				// set window style for bonus style points
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				try
				{
					BestGui window = new BestGui();
					window.frmBlackSheepCome.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public BestGui()
	{
		initializeGUI();
		
		worldxy = new Coordinates(0,0);
		
		// get world x and y
		while(worldxy.x == 0)
		{
			try
			{
				String input = (String)JOptionPane.showInputDialog("Enter world width", "10");
				int parseded = Integer.parseInt(input);
				if(parseded > 0)
					worldxy.x = parseded;
				else
					JOptionPane.showMessageDialog(null, "World width must be a positive number");
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Invalid number");
			}
		}
		
		while(worldxy.y == 0)
		{
			try
			{
				String input = (String)JOptionPane.showInputDialog("Enter world height", "10");
				int parseded = Integer.parseInt(input);
				if(parseded > 0)
					worldxy.y = parseded;
				else
					JOptionPane.showMessageDialog(null, "World height must be a positive number");
			}
			catch (NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "Invalid number");
			}
		}
		
		// set global names
		Names.putSpeciesName('W', "Wolf");
		Names.putSpeciesName('S', "Sheep");
		Names.putSpeciesName('F', "Fox");
		Names.putSpeciesName('T', "Turtle");
		Names.putSpeciesName('A', "Antelope");
		Names.putSpeciesName('H', "Human");
		Names.putSpeciesName('G', "Grass");
		Names.putSpeciesName('D', "Dairy");
		Names.putSpeciesName('U', "Guarana");
		Names.putSpeciesName('B', "Wolf Berries");
		Names.putSpeciesName('C', "Sosnowski's Borsch");
		Names.addName("Jake");
		Names.addName("Winston");
		Names.addName("Harry");
		Names.addName("Larry");
		Names.addName("Lenny");
		Names.addName("Johnny");
		Names.addName("Spencer");
		Names.addName("Fred");
		Names.addName("Joey");
		Names.addName("Steve");
		Names.addName("Bob");
		Names.addName("Mascara");
		Names.addName("Mooriela");
		Names.addName("Vicky");
		Names.addName("Christina");
		Names.addName("Vicky");
		Names.addName("Daisy");
		Names.addName("Elizabeth");
		Names.addName("Dolores");
		Names.addName("Esmeralda");
		Names.addName("Matilda");
		Names.addName("Jenny");
		
		// create world
		world = new World(worldxy);
		
		world.addOrganism(new Human(world)); // HUMAN AFTER ALL (actually add him first)
		world.setHumanAlive(true); // yep, he sure seems alive
		
		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, ANIMAL_START_MAX); i++)
			world.addOrganism(new Wolf(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, ANIMAL_START_MAX); i++)
			world.addOrganism(new Sheep(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, ANIMAL_START_MAX); i++)
			world.addOrganism(new Fox(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, ANIMAL_START_MAX); i++)
			world.addOrganism(new Turtle(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, ANIMAL_START_MAX); i++)
			world.addOrganism(new Antelope(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, PLANT_START_MAX); i++)
			world.addOrganism(new Grass(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, PLANT_START_MAX); i++)
			world.addOrganism(new Dairy(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, PLANT_START_MAX); i++)
			world.addOrganism(new Guarana(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, PLANT_START_MAX); i++)
			world.addOrganism(new WolfBerries(world));

		for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, PLANT_START_MAX); i++)
			world.addOrganism(new SosnowskisBorsch(world));

		
		this.drawWorld();
		
		Logger.writeMessage("Welcome to Black Sheep.");
	}

	private void initializeGUI()
	{
		frmBlackSheepCome = new JFrame();
		frmBlackSheepCome.setTitle("Black Sheep, come on");
		frmBlackSheepCome.setBackground(SystemColor.control);
		frmBlackSheepCome.setBounds(100, 100, 799, 450);
		frmBlackSheepCome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBlackSheepCome.getContentPane().setLayout(new BoxLayout(frmBlackSheepCome.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.3);
		frmBlackSheepCome.getContentPane().add(splitPane);
		
		ScrollLabel logLabel = new ScrollLabel("<html>");
		JScrollPane logScrollPane = new JScrollPane(logLabel);
		splitPane.setRightComponent(logScrollPane);
		Logger.initialize(logLabel, logScrollPane);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 100, 50, 100, 0,};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblInfo = new JLabel("Programowanie Obiektowe projekt 2: Black Sheep. Marcin Szycik 165116");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.insets = new Insets(5, 5, 5, 5);
		gbc_lblInfo.gridwidth = 5;
		gbc_lblInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInfo.gridx = 0;
		gbc_lblInfo.gridy = 0;
		panel.add(lblInfo, gbc_lblInfo);
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelWorld = new JPanel();
		panelWorld.setBackground(Color.WHITE);
		panelWorld.setLayout(null);
		GridBagConstraints gbc_panelWorld = new GridBagConstraints();
		gbc_panelWorld.fill = GridBagConstraints.BOTH;
		gbc_panelWorld.gridwidth = 5;
		gbc_panelWorld.insets = new Insets(0, 0, 5, 0);
		gbc_panelWorld.gridx = 0;
		gbc_panelWorld.gridy = 1;
		panel.add(panelWorld, gbc_panelWorld);
		
		btnNextRound = new JButton("Next round");
		btnNextRound.setEnabled(false);
		GridBagConstraints gbc_btnNextRound = new GridBagConstraints();
		gbc_btnNextRound.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNextRound.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextRound.gridx = 1;
		gbc_btnNextRound.gridy = 2;
		panel.add(btnNextRound, gbc_btnNextRound);
		btnNextRound.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Turn();
			}
		});
		
		btnUp = new JButton("\u2191");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				doHumanTask(HumanTasks.GO_UP);
			}
		});
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnUp.gridx = 2;
		gbc_btnUp.gridy = 2;
		panel.add(btnUp, gbc_btnUp);
		
		btnDown = new JButton("\u2193");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				doHumanTask(HumanTasks.GO_DOWN);
			}
		});
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.insets = new Insets(0, 0, 0, 5);
		gbc_btnDown.gridx = 2;
		gbc_btnDown.gridy = 4;
		panel.add(btnDown, gbc_btnDown);
		
		btnLeft = new JButton("\u2190");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				doHumanTask(HumanTasks.GO_LEFT);
			}
		});
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.anchor = GridBagConstraints.EAST;
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 1;
		gbc_btnLeft.gridy = 3;
		panel.add(btnLeft, gbc_btnLeft);
		
		btnRight = new JButton("\u2192");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				doHumanTask(HumanTasks.GO_RIGHT);
			}
		});
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.anchor = GridBagConstraints.WEST;
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnRight.gridx = 3;
		gbc_btnRight.gridy = 3;
		panel.add(btnRight, gbc_btnRight);
		
		btnSpecial = new JButton("Special ability");
		btnSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				doHumanTask(HumanTasks.DO_SPECIAL);
			}
		});
		GridBagConstraints gbc_btnSpecial = new GridBagConstraints();
		gbc_btnSpecial.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSpecial.insets = new Insets(0, 0, 0, 5);
		gbc_btnSpecial.gridx = 1;
		gbc_btnSpecial.gridy = 4;
		panel.add(btnSpecial, gbc_btnSpecial);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try{
				    PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "\\save.dat", "UTF-8");
				    writer.println(round);
				    writer.println(world.toString());
				    writer.close();
				    Logger.writeMessage("Saved world state");
				}
				catch (IOException e)
				{
					Logger.writeMessage("Cannot save: " + e.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 2;
		panel.add(btnSave, gbc_btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "\\save.dat")));
					round = Integer.parseInt(br.readLine());
					int worldx = Integer.parseInt(br.readLine());
					int worldy = Integer.parseInt(br.readLine());
					world = new World(new Coordinates(worldx, worldy));
					boolean loadHumanAlive = false;
					String line = null;
					while ((line = br.readLine()) != null)
					{
						String[] spliteded = null;
						try
						{
							spliteded = line.split(";", -1);
						}
						catch(Exception ex)
						{
							// something wrong with stupid split
							continue;
						}
						char type = spliteded[0].charAt(0);
						int age = Integer.parseInt(spliteded[1]);
						int strength = Integer.parseInt(spliteded[2]);
						int initiative = Integer.parseInt(spliteded[3]);
						int posx = Integer.parseInt(spliteded[4]);
						int posy = Integer.parseInt(spliteded[5]);
						String name = spliteded[6];
						int countdown = 0;
						try
						{
							countdown = Integer.parseInt(spliteded[7]);
						}
						catch(NumberFormatException ex)
						{
							// nothing to parse
						}

						switch(type)
						{
						case 'W':
							world.addOrganism(new Wolf(world, posx, posy, age, strength, initiative, name));
							break;
						case 'S':
							world.addOrganism(new Sheep(world, posx, posy, age, strength, initiative, name));
							break;
						case 'F':
							world.addOrganism(new Fox(world, posx, posy, age, strength, initiative, name));
							break;
						case 'T':
							world.addOrganism(new Turtle(world, posx, posy, age, strength, initiative, name));
							break;
						case 'A':
							world.addOrganism(new Antelope(world, posx, posy, age, strength, initiative, name));
							break;
						case 'H':
							world.addOrganism(new Human(world, posx, posy, age, strength, initiative, name, countdown));
							loadHumanAlive = true;
							break;
						case 'G':
							world.addOrganism(new Grass(world, posx, posy, age, strength, initiative));
							break;
						case 'D':
							world.addOrganism(new Dairy(world, posx, posy, age, strength, initiative));
							break;
						case 'U':
							world.addOrganism(new Guarana(world, posx, posy, age, strength, initiative));
							break;
						case 'B':
							world.addOrganism(new WolfBerries(world, posx, posy, age, strength, initiative));
							break;
						case 'C':
							world.addOrganism(new SosnowskisBorsch(world, posx, posy, age, strength, initiative));
							break;
						}
					}
					br.close();
					
					drawWorld();
					world.setHumanAlive(loadHumanAlive);
					setHumanControlsEnabled(world.isHumanAlive());
					Logger.writeMessage("Loaded world state");
				}
				catch (IOException e)
				{
					Logger.writeMessage("Cannot load: " + e.getMessage());
				}	
			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoad.gridx = 3;
		gbc_btnLoad.gridy = 4;
		panel.add(btnLoad, gbc_btnLoad);
		
		Lblroundcnt = new JLabel("0");
		GridBagConstraints gbc_Lblroundcnt = new GridBagConstraints();
		gbc_Lblroundcnt.insets = new Insets(0, 0, 5, 5);
		gbc_Lblroundcnt.gridx = 2;
		gbc_Lblroundcnt.gridy = 3;
		panel.add(Lblroundcnt, gbc_Lblroundcnt);
	}
}