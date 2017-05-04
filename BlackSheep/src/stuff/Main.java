package stuff;

import java.awt.EventQueue;
import javax.swing.JFrame;
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
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.Color;

public class Main
{

	private JFrame frmBlackSheepCome;
	private JLabel Lblroundcnt;
	private Integer round;
	
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
					Main window = new Main();
					window.frmBlackSheepCome.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Main()
	{
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
		
		round = 0;
		initialize();
		Logger.writeMessage("Welcome to Black Sheep.");
	}

	private void initialize()
	{
		frmBlackSheepCome = new JFrame();
		frmBlackSheepCome.setTitle("Black Sheep, come on");
		frmBlackSheepCome.setBackground(SystemColor.control);
		frmBlackSheepCome.setBounds(100, 100, 700, 450);
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
		
		JPanel panelWorld = new JPanel();
		panelWorld.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelWorld = new GridBagConstraints();
		gbc_panelWorld.fill = GridBagConstraints.BOTH;
		gbc_panelWorld.gridwidth = 5;
		gbc_panelWorld.insets = new Insets(0, 0, 5, 0);
		gbc_panelWorld.gridx = 0;
		gbc_panelWorld.gridy = 1;
		panel.add(panelWorld, gbc_panelWorld);
		
		JButton btnNextRound = new JButton("Next round");
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
				round++;
				Lblroundcnt.setText(round.toString());
				Logger.writeMessage("Round " + round);
			}
		});
		
		JButton button = new JButton("\u2191");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 2;
		panel.add(button, gbc_button);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 2;
		panel.add(btnSave, gbc_btnSave);
		
		JButton btnLeft = new JButton("\u2190");
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.anchor = GridBagConstraints.EAST;
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 1;
		gbc_btnLeft.gridy = 3;
		panel.add(btnLeft, gbc_btnLeft);
		
		Lblroundcnt = new JLabel("0");
		GridBagConstraints gbc_Lblroundcnt = new GridBagConstraints();
		gbc_Lblroundcnt.insets = new Insets(0, 0, 5, 5);
		gbc_Lblroundcnt.gridx = 2;
		gbc_Lblroundcnt.gridy = 3;
		panel.add(Lblroundcnt, gbc_Lblroundcnt);
		Lblroundcnt.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnRight = new JButton("\u2192");
		GridBagConstraints gbc_btnRight = new GridBagConstraints();
		gbc_btnRight.anchor = GridBagConstraints.WEST;
		gbc_btnRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnRight.gridx = 3;
		gbc_btnRight.gridy = 3;
		panel.add(btnRight, gbc_btnRight);
		
		JButton btnSpecial = new JButton("Special ability");
		GridBagConstraints gbc_btnSpecial = new GridBagConstraints();
		gbc_btnSpecial.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSpecial.insets = new Insets(0, 0, 0, 5);
		gbc_btnSpecial.gridx = 1;
		gbc_btnSpecial.gridy = 4;
		panel.add(btnSpecial, gbc_btnSpecial);
		
		JButton btnNewButton = new JButton("\u2193");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnLoad = new JButton("Load");
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoad.gridx = 3;
		gbc_btnLoad.gridy = 4;
		panel.add(btnLoad, gbc_btnLoad);
	}
}
