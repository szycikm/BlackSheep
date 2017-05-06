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
import animals.HumanTasks;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.Color;

import species.Organism;
import animals.Human;

public class BestGui
{
	private static final int WORLD_SQUARE_MULTIPLIER = 22;
	private static final int WORLD_SQUARE_SIZE = 20;
	
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
	
	private void DrawWorld()
	{
		panelWorld.removeAll();
		
		for (int y = 0; y < world.GetMaxXY().y; y++)
		{
			for(int x = 0; x < world.GetMaxXY().x; x++)
			{
				Organism org = world.GetOrganismByPosition(new Coordinates(x, y));
				if(world.GetOrganismByPosition(new Coordinates(x, y)) != null)
				{
					// organism stands on this field -> draw it as a label
					JLabel stander = new JLabel(String.valueOf(org.Draw()));
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
		Logger.writeMessage("===== Round " + round + " =====");
		Logger.writeMessage("Population: " + world.GetOrganismCount());

		world.DoTurn();
		this.DrawWorld();
		
		this.setHumanControlsEnabled(world.isHumanAlive());
		
		Logger.writeMessage("===== Round " + round + " ended =====");
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
		Names.PutSpeciesName('W', "Wolf");
		Names.PutSpeciesName('S', "Sheep");
		Names.PutSpeciesName('F', "Fox");
		Names.PutSpeciesName('T', "Turtle");
		Names.PutSpeciesName('A', "Antelope");
		Names.PutSpeciesName('H', "Human");
		Names.PutSpeciesName('G', "Grass");
		Names.PutSpeciesName('D', "Dairy");
		Names.PutSpeciesName('U', "Guarana");
		Names.PutSpeciesName('B', "Wolf Berries");
		Names.PutSpeciesName('C', "Sosnowski's Borsch");
		Names.AddName("Jake");
		Names.AddName("Winston");
		Names.AddName("Harry");
		Names.AddName("Larry");
		Names.AddName("Lenny");
		Names.AddName("Johnny");
		Names.AddName("Spencer");
		Names.AddName("Fred");
		Names.AddName("Joey");
		Names.AddName("Steve");
		Names.AddName("Bob");
		Names.AddName("Mascara");
		Names.AddName("Mooriela");
		Names.AddName("Vicky");
		Names.AddName("Christina");
		Names.AddName("Vicky");
		Names.AddName("Daisy");
		Names.AddName("Elizabeth");
		Names.AddName("Dolores");
		Names.AddName("Esmeralda");
		Names.AddName("Matilda");
		Names.AddName("Jenny");
		
		// create world
		world = new World(worldxy);
		
		world.AddOrganism(new Human(world)); // HUMAN AFTER ALL (actually add him first)
		world.setHumanAlive(true); // yep, he sure seems alive
		
		// TODO add all other colorful inhabitants of this world
		
		this.DrawWorld();
		
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
		Logger.Initialize(logLabel, logScrollPane);
		
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
				Human human = world.GetHuman();
				if(human.isTaskLegal(HumanTasks.GO_UP))
				{
					human.setNextTask(HumanTasks.GO_UP);
					Turn();
				}
				else
					Logger.writeMessage("Invalid human task");
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
				Human human = world.GetHuman();
				if(human.isTaskLegal(HumanTasks.GO_DOWN))
				{
					human.setNextTask(HumanTasks.GO_DOWN);
					Turn();
				}
				else
					Logger.writeMessage("Invalid human task");
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
				Human human = world.GetHuman();
				if(human.isTaskLegal(HumanTasks.GO_LEFT))
				{
					human.setNextTask(HumanTasks.GO_LEFT);
					Turn();
				}
				else
					Logger.writeMessage("Invalid human task");
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
				Human human = world.GetHuman();
				if(human.isTaskLegal(HumanTasks.GO_RIGHT))
				{
					human.setNextTask(HumanTasks.GO_RIGHT);
					Turn();
				}
				else
					Logger.writeMessage("Invalid human task");
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
				Human human = world.GetHuman();
				if(human.isTaskLegal(HumanTasks.DO_SPECIAL))
				{
					human.setNextTask(HumanTasks.DO_SPECIAL);
					Turn();
				}
				else
					Logger.writeMessage("Invalid human task");
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
				// TODO save
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
				// TODO load
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