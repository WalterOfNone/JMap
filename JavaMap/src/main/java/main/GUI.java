package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI implements ActionListener, ItemListener {

	private JFrame frame;
	private JPanel panel;
	private JMenuBar menubar;
	private JMenu scanMenu;
	private Net backEnd;
	private JTextField targetField;
	public GUI() {
		this.backEnd = new Net();
	}

	public void GUInit() {
		// Basic startup options + loading of files
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {

		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
		//opens and created image icons
		ImageIcon logoimg = new ImageIcon("src/main/java/main/jmaplogo.png");
		ImageIcon saveimg = new ImageIcon("src/main/java/main/save.png");
		ImageIcon openimg = new ImageIcon("src/main/java/main/open.png");
		ImageIcon exitimg = new ImageIcon("src/main/java/main/exit.png");
		
		frame = new JFrame("Jmap");
		frame.setIconImage(logoimg.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(500, 600));
		frame.setLayout(new GridBagLayout());
		
		GridBagConstraints frameCon = new GridBagConstraints();
		GridBagConstraints con = new GridBagConstraints();
		// sets up menu bar and items within
		menubar = new JMenuBar();
		
		//scan menu
		scanMenu = new JMenu("Scan");

		JMenuItem openMenu = new JMenuItem("Open Scan", openimg);
		openMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openMenu.addActionListener(this);
		openMenu.setActionCommand("open");
		scanMenu.add(openMenu);

		JMenuItem saveMenu = new JMenuItem("Save Scan", saveimg);
		saveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenu.addActionListener(this);
		saveMenu.setActionCommand("save");
		scanMenu.add(saveMenu);

		JMenuItem exitMenu = new JMenuItem("Exit", exitimg);
		exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		exitMenu.addActionListener(this);
		exitMenu.setActionCommand("exit");
		scanMenu.add(exitMenu);

		menubar.add(scanMenu);
		
		//profile menu
		JMenu profileMenu = new JMenu("Profile"); 
		
		JMenuItem createProfile = new JMenuItem("Create Profile");
		createProfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		createProfile.addActionListener(this);
		createProfile.setActionCommand("createprofile");
		profileMenu.add(createProfile);
		
		JMenuItem editProfile = new JMenuItem("Edit Profile");
		editProfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		editProfile.addActionListener(this);
		editProfile.setActionCommand("editprofile");
		profileMenu.add(editProfile);
		
		menubar.add(profileMenu);
		
		//help menu
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem getHelp = new JMenuItem("Help");
		getHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		getHelp.addActionListener(this);
		getHelp.setActionCommand("help");
		helpMenu.add(getHelp);
		
		menubar.add(helpMenu);
		//Sets created menubar to the default menubar
		frame.setJMenuBar(menubar);
		
		//sets up top command entering components
		JPanel topCom = new JPanel();
		topCom.setLayout(new GridBagLayout());
		
		JLabel targetLabel = new JLabel("Target:");
		con.insets = new Insets(0, 10, 0, 0);
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		topCom.add(targetLabel, con);

		targetField = new JTextField("");
		con.fill = GridBagConstraints.HORIZONTAL;
		con.insets = new Insets(0, 10, 0, 0);
		con.weightx = 0.5;
		con.gridx = 1;
		targetField.addActionListener(this);
		targetField.setActionCommand("strchange");
		topCom.add(targetField, con);

		JLabel profileLabel = new JLabel("Profile:");
		con.fill = GridBagConstraints.HORIZONTAL;
		con.insets = new Insets(0, 10, 0, 0);
		con.weightx = 0;
		con.gridx = 2;
		topCom.add(profileLabel, con);

		String[] profiles = { "profile1", "profile2" };
		JComboBox profileCombo = new JComboBox(profiles);
		con.fill = GridBagConstraints.HORIZONTAL;
		con.weightx = 0.5;
		con.gridx = 3;
		topCom.add(profileCombo, con);

		JButton scanButton = new JButton("Scan");
		con.fill = GridBagConstraints.HORIZONTAL;
		con.weightx = 0;
		con.gridx = 4;
		scanButton.addActionListener(this);
		scanButton.setActionCommand("scan");
		topCom.add(scanButton, con);

		JButton cancelButton = new JButton("Cancel");
		con.fill = GridBagConstraints.HORIZONTAL;
		con.weightx = 0;
		con.gridx = 5;
		topCom.add(cancelButton, con);
		

		
		frameCon.fill = GridBagConstraints.HORIZONTAL;
		frameCon.insets = new Insets(0,0, 0, 0);
		frameCon.weightx = 1;
		frameCon.gridx = 0;
		frameCon.gridy = 0;

		frame.add(topCom, frameCon);
		
		

		


	}

	public void GUIview() {
		frame.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
		case "open":
			break;
		case "save":
			break;
		case "exit":
			System.exit(0);
			break;
		case "scan":
			backEnd.setIp(targetField.getText());
			try {
				backEnd.portScan(1000);
				backEnd.serviceScan();
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
		default:
			System.out.println("Unknown action received");
		}

	}

}
