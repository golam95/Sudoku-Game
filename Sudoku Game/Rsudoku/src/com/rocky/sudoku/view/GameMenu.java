package com.rocky.sudoku.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GameMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Dimension PREFERREDSIZE = new Dimension(600, 620);
	static JFrame gameMenu_fr = new JFrame();
	Border border = new LineBorder(Color.decode("#F1DCA9"), 3);
	Font font = new Font("Elephant", Font.BOLD, 15);
	// Button
	private JButton btn_Startgame = new JButton("Start Game");
	private JButton btn_Reg = new JButton("Create Account");
	private JButton btn_Instruction = new JButton("Instruction");
	private JButton btn_About = new JButton("About");
	private JButton btn_Exit = new JButton("Exit");

	// JLebels
	private JLabel lbl_gameTitle = new JLabel(new ImageIcon("image/Capture5.PNG"));
	private JLabel lbl_gameBg = new JLabel(new ImageIcon("image/bg_sudo.jpg"));
	// JPanel
	private JPanel menupanel = new JPanel();

	public GameMenu() {
		try {
			gameMenu_fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameMenu_fr.setMinimumSize(PREFERREDSIZE);
			gameMenu_fr.setPreferredSize(PREFERREDSIZE);
			gameMenu_fr.setResizable(false);
			gameMenu_fr.setLayout(null);
			gameMenu_fr.setLocationRelativeTo(null);
			gameMenu_fr.pack();
			gameMenu_fr.getContentPane().setBackground(Color.decode("#C28C2A"));
			createButtonpanel();
			gameMenu_fr.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createGui" + ex.toString());
		}
	}

	private void createButtonpanel() {
		try {

			lbl_gameBg.setBounds(0, 0, 600, 620);
			gameMenu_fr.add(lbl_gameBg);

			menupanel.setBounds(143, 183, 317, 385);
			menupanel.setLayout(null);
			menupanel.setBackground(Color.decode("#F1DCA9"));
			lbl_gameBg.add(menupanel);

			btn_Startgame.setBounds(65, 50, 180, 45);
			btn_Startgame.setBackground(Color.decode("#843902"));
			btn_Startgame.setForeground(Color.decode("#FFFFFF"));
			btn_Startgame.setBorder(border);
			btn_Startgame.setFont(font);
			menupanel.add(btn_Startgame);
			//
			btn_Reg.setBounds(65, 110, 180, 45);
			btn_Reg.setBackground(Color.decode("#843902"));
			btn_Reg.setForeground(Color.decode("#FFFFFF"));
			btn_Reg.setFont(font);
			btn_Reg.setBorder(border);
			menupanel.add(btn_Reg);

			btn_Instruction.setBounds(65, 170, 180, 45);
			btn_Instruction.setBackground(Color.decode("#843902"));
			btn_Instruction.setForeground(Color.decode("#FFFFFF"));
			btn_Instruction.setBorder(border);
			btn_Instruction.setFont(font);
			menupanel.add(btn_Instruction);
			//

			btn_About.setBounds(65, 230, 180, 45);
			btn_About.setBackground(Color.decode("#843902"));
			btn_About.setForeground(Color.decode("#FFFFFF"));
			btn_About.setBorder(border);
			btn_About.setFont(font);
			menupanel.add(btn_About);
			//
			btn_Exit.setBounds(65, 290, 180, 45);
			btn_Exit.setBackground(Color.decode("#843902"));
			btn_Exit.setForeground(Color.decode("#FFFFFF"));
			btn_Exit.setBorder(border);
			btn_Exit.setFont(font);
			menupanel.add(btn_Exit);
			lbl_gameTitle.setBounds(100, 5, 400, 180);
			lbl_gameBg.add(lbl_gameTitle);

			btn_Startgame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					@SuppressWarnings("unused")
					UserAccesability useraccessability = new UserAccesability();
				}
			});

			btn_Reg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					@SuppressWarnings("unused")
					CreateAccount account = new CreateAccount();

				}
			});

			btn_Instruction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,
							"At first create an account." + "\n" + "Then start your game." + "\n"
									+ "If you don't understand press instruciton button and follow the instruction"
									+ "\n");
				}
			});

			btn_About.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "My name is Tuhin paul." + "\n"
							+ "Studied at Daffodil International Aceademy." + "\n" + "Batch 68th" + "\n");
				}
			});
			btn_Exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = JOptionPane.showConfirmDialog(null, "Do you want to Exit?");
					if (i == 0) {
						visiblefalseMenu();
						dispose();
					}
				}
			});
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error creating button panel" + ex.toString());
		}
	}

	public static void visiblefalseMenu() {
		gameMenu_fr.setVisible(false);
	}

	public static void main(String[] args) {
		// here you can put the selected theme class name in JTattoo
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			@SuppressWarnings("unused")
			GameMenu gamemenu = new GameMenu();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

}
