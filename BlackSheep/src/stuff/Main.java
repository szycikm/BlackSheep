package stuff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main
{

	private JFrame frmBlackSheepCome;
	private boolean isWorldHex;
	
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
		initialize();
	}

	private void initialize()
	{
		frmBlackSheepCome = new JFrame();
		frmBlackSheepCome.setTitle("Black Sheep, come on");
		frmBlackSheepCome.setBackground(SystemColor.control);
		frmBlackSheepCome.setBounds(100, 100, 457, 472);
		frmBlackSheepCome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBlackSheepCome.getContentPane().setLayout(new MigLayout("", "[grow][grow][grow]", "[23px][grow][][]"));
		
		JLabel lblInfo = new JLabel("Programowanie Obiektowe projekt 2: Black Sheep. Marcin Szycik 165116");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		frmBlackSheepCome.getContentPane().add(lblInfo, "flowx,cell 0 0 3 1,alignx center");
		
		JPanel panelWorld = new JPanel();
		frmBlackSheepCome.getContentPane().add(panelWorld, "cell 0 1 3 1,grow");
		
		JButton btnNextRound = new JButton("Next round");
		frmBlackSheepCome.getContentPane().add(btnNextRound, "cell 0 2,growx");
		
		JButton btnSave = new JButton("Save");
		frmBlackSheepCome.getContentPane().add(btnSave, "cell 1 2,growx");
		
		final JButton btnSwitchToHex = new JButton("Switch to hex");
		btnSwitchToHex.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				isWorldHex = !isWorldHex;
				btnSwitchToHex.setText("Switch to " + (isWorldHex ? "box" : "hex"));
			}
		});
		frmBlackSheepCome.getContentPane().add(btnSwitchToHex, "cell 2 2,growx");
		
		JButton btnSpecial = new JButton("Special ability");
		frmBlackSheepCome.getContentPane().add(btnSpecial, "cell 0 3,growx");
		
		JButton btnLoad = new JButton("Load");
		frmBlackSheepCome.getContentPane().add(btnLoad, "cell 1 3,growx");
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		frmBlackSheepCome.getContentPane().add(btnExit, "cell 2 3,growx");
		frmBlackSheepCome.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNextRound, btnSave, btnSwitchToHex, btnSpecial, btnLoad, btnExit, frmBlackSheepCome.getContentPane(), lblInfo}));
	}
}
