package stuff;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Main
{

	private JFrame frmBlackSheepCome;
	private boolean isWorldHex;
	private JLabel Lblroundcnt;
	private int round;
	private ScrollLabel logLabel;
	private JScrollPane logScrollPane;
	
	public void writeMessage(String msg)
	{
		logLabel.setText(logLabel.getText() + msg + "<br />");
		logScrollPane.getVerticalScrollBar().setValue(logScrollPane.getVerticalScrollBar().getMaximum());
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
		
		isWorldHex = false;
		round = 0;
		initialize();
		writeMessage("Welcome to Black Sheep.");
	}

	private void initialize()
	{
		frmBlackSheepCome = new JFrame();
		frmBlackSheepCome.setTitle("Black Sheep, come on");
		frmBlackSheepCome.setBackground(SystemColor.control);
		frmBlackSheepCome.setBounds(100, 100, 700, 450);
		frmBlackSheepCome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{150, 150, 150, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_NORMAL};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frmBlackSheepCome.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblInfo = new JLabel("Programowanie Obiektowe projekt 2: Black Sheep. Marcin Szycik 165116");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInfo.insets = new Insets(5, 5, 5, 5);
		gbc_lblInfo.gridwidth = 3;
		gbc_lblInfo.gridx = 0;
		gbc_lblInfo.gridy = 0;
		frmBlackSheepCome.getContentPane().add(lblInfo, gbc_lblInfo);
		
		Lblroundcnt = new JLabel("Round 0");
		Lblroundcnt.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_Lblroundcnt = new GridBagConstraints();
		gbc_Lblroundcnt.fill = GridBagConstraints.HORIZONTAL;
		gbc_Lblroundcnt.insets = new Insets(5, 5, 5, 0);
		gbc_Lblroundcnt.gridx = 3;
		gbc_Lblroundcnt.gridy = 0;
		frmBlackSheepCome.getContentPane().add(Lblroundcnt, gbc_Lblroundcnt);
		
		JPanel panelWorld = new JPanel();
		GridBagConstraints gbc_panelWorld = new GridBagConstraints();
		gbc_panelWorld.fill = GridBagConstraints.BOTH;
		gbc_panelWorld.insets = new Insets(0, 0, 5, 5);
		gbc_panelWorld.gridwidth = 3;
		gbc_panelWorld.gridx = 0;
		gbc_panelWorld.gridy = 1;
		frmBlackSheepCome.getContentPane().add(panelWorld, gbc_panelWorld);
		
		JButton btnNextRound = new JButton("Next round");
		btnNextRound.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				round++;
				Lblroundcnt.setText("Round " + round);
				writeMessage("Round " + round);
			}
		});
		GridBagConstraints gbc_btnNextRound = new GridBagConstraints();
		gbc_btnNextRound.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNextRound.anchor = GridBagConstraints.NORTH;
		gbc_btnNextRound.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextRound.gridx = 0;
		gbc_btnNextRound.gridy = 2;
		frmBlackSheepCome.getContentPane().add(btnNextRound, gbc_btnNextRound);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.anchor = GridBagConstraints.NORTH;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 2;
		frmBlackSheepCome.getContentPane().add(btnSave, gbc_btnSave);
		
		final JButton btnSwitchToHex = new JButton("Switch to hex");
		btnSwitchToHex.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				isWorldHex = !isWorldHex;
				btnSwitchToHex.setText("Switch to " + (isWorldHex ? "box" : "hex"));
			}
		});
		GridBagConstraints gbc_btnSwitchToHex = new GridBagConstraints();
		gbc_btnSwitchToHex.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSwitchToHex.anchor = GridBagConstraints.NORTH;
		gbc_btnSwitchToHex.insets = new Insets(0, 0, 5, 5);
		gbc_btnSwitchToHex.gridx = 2;
		gbc_btnSwitchToHex.gridy = 2;
		frmBlackSheepCome.getContentPane().add(btnSwitchToHex, gbc_btnSwitchToHex);
		
		logLabel = new ScrollLabel("<html>");
		logLabel.setBackground(SystemColor.text);
		logScrollPane = new JScrollPane(logLabel);
		GridBagConstraints gbc_logScrollPane = new GridBagConstraints();
		gbc_logScrollPane.gridheight = 3;
		gbc_logScrollPane.fill = GridBagConstraints.BOTH;
		gbc_logScrollPane.gridx = 3;
		gbc_logScrollPane.gridy = 1;
		frmBlackSheepCome.getContentPane().add(logScrollPane, gbc_logScrollPane);
		
		JButton btnSpecial = new JButton("Special ability");
		GridBagConstraints gbc_btnSpecial = new GridBagConstraints();
		gbc_btnSpecial.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSpecial.anchor = GridBagConstraints.NORTH;
		gbc_btnSpecial.insets = new Insets(0, 0, 0, 5);
		gbc_btnSpecial.gridx = 0;
		gbc_btnSpecial.gridy = 3;
		frmBlackSheepCome.getContentPane().add(btnSpecial, gbc_btnSpecial);
		
		JButton btnLoad = new JButton("Load");
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.anchor = GridBagConstraints.NORTH;
		gbc_btnLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoad.gridx = 1;
		gbc_btnLoad.gridy = 3;
		frmBlackSheepCome.getContentPane().add(btnLoad, gbc_btnLoad);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExit.anchor = GridBagConstraints.NORTH;
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 3;
		frmBlackSheepCome.getContentPane().add(btnExit, gbc_btnExit);
		
		frmBlackSheepCome.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNextRound, btnSave, frmBlackSheepCome.getContentPane(), lblInfo}));
	}
}
