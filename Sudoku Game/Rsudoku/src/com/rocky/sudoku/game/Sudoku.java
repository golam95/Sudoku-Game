
package com.rocky.sudoku.game;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import com.rocky.sudoku.daoImp.ManageUserProgressDaoImp;
import com.rocky.sudoku.model.UserProgress;
import com.rocky.sudoku.view.GameMenu;
import com.rocky.sudoku.view.PlayerStatistices;

import java.awt.Font;

public class Sudoku {

	private JFrame frame;
	boolean isStarted = false;
	int prevBoard[][] = new int[9][9];
	private static String getName = "";
	private static String getname = "";

	private static boolean gamelevelOne;
	private static boolean gamelevelTwo;
	private static boolean gamelevelThree;

	private final Timer timer = new Timer(1000, (ActionListener) null);
	final JLabel timerLabel = new JLabel("Time Left:");
	final JLabel loginLabel = new JLabel();
	final JLabel logout = new JLabel();
	final JButton startButton = new JButton("Start");
	final JButton submitButton = new JButton("Submit");
	final JButton detailButton = new JButton("Statistic");
	final JButton checkButton = new JButton("Check");
	final JButton saveprogressButton = new JButton("Save progress");
	final JTextField grid[][] = new JTextField[9][9];
	private static int correntNumberr = 0;
	private static int wrongNumber = 0;
	private static int checkdigit = 0;
	private static int find = 0;
	private static int difficulty;
	int timeCount = -1;

	ManageUserProgressDaoImp manageprogress = new ManageUserProgressDaoImp();

	// To convert counter into time.
	public String countToTime(int count) {
		String min = Integer.toString(count / 60);
		String sec = Integer.toString(count % 60);
		if (Integer.parseInt(min) == 0)
			min = "0" + min;
		if (Integer.parseInt(sec) / 10 == 0)
			sec = "0" + sec;
		return min + ":" + sec;
	}

	// Event handler when game is over.
	public void gameOver() {

		timerLabel.setVisible(false);
		timer.stop();
		prevBoard = SudokuSolver.solve(prevBoard);
		boolean isFine = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j].getText().equals("")) {
					isFine = false;
					break;
				} else if (Integer.parseInt(grid[i][j].getText()) != prevBoard[i][j]) {
					isFine = false;
					break;
				}
			}
		}
		if (isFine && isStarted)
			JOptionPane.showMessageDialog(null, "You Won.");
		else
			JOptionPane.showMessageDialog(null, "You Lose.");
		isStarted = false;
		startButton.setText("Start");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j].setText("");
				grid[i][j].setEditable(false);
			}
		}
	}

	public Sudoku(String name) {
		getName = name;
		initialize(getName);
	}

	private void initialize(String getName) {
		detailButton.setEnabled(false);
		checkButton.setEnabled(false);
		saveprogressButton.setEnabled(false);
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timeCount--;
				timerLabel.setText("Time Left- " + countToTime(timeCount));
				if (timeCount == 0)
					gameOver();
			}
		});
		getname = getName;
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 620);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		////////////////////////////////// GRID START
		////////////////////////////////// /////////////////////////////////////////////////////////////

		int h = 12, w = 120, hi = 39, wi = 37;

		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0 && i != 0)
				w += 13;

			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0 && j != 0)
					h += 11;

				grid[i][j] = new JTextField();
				grid[i][j].setColumns(10);
				grid[i][j].setBounds(h, w, 38, 37);
				grid[i][j].setBackground(Color.decode("#B56E2C"));
				frame.getContentPane().add(grid[i][j]);

				h += hi;

			}
			h = 12;
			w += wi;

		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j].setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 22));
				grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				grid[i][j].setEditable(false);
			}
		}
		////////////////////////////////// GRID END
		////////////////////////////////// /////////////////////////////////////////////////////////////

		submitButton.setVisible(false);
		timerLabel.setVisible(false);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkButton.setEnabled(false);
				gameOver();

			}
		});
		submitButton.setFont(new Font("Calibri Light", Font.BOLD, 18));
		submitButton.setBounds(200, 515, 155, 37);
		submitButton.setBackground(Color.decode("#843902"));
		submitButton.setForeground(Color.decode("#FFFFFF"));
		frame.getContentPane().add(submitButton);

		// difficultyLabel.setBounds(435, 70, 155, 24);

		JLabel difficultyLabel = new JLabel("Select Levels:");
		difficultyLabel.setFont(new Font("Calibri Light", Font.BOLD, 16));
		difficultyLabel.setBounds(435, 310, 155, 24);
		difficultyLabel.setForeground(Color.decode("#843507"));
		frame.getContentPane().add(difficultyLabel);

		timerLabel.setFont(new Font("Calibri Light", Font.BOLD, 22));
		timerLabel.setBounds(110, 70, 210, 27);
		timerLabel.setForeground(Color.decode("#843507"));
		frame.getContentPane().add(timerLabel);

		loginLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		loginLabel.setFont(new Font("Calibri Light", Font.BOLD, 16));
		loginLabel.setBounds(10, 15, 276, 16);
		loginLabel.setText("Name: " + getName);
		loginLabel.setForeground(Color.decode("#843507"));
		frame.getContentPane().add(loginLabel);

		logout.setFont(new Font("Calibri Light", Font.BOLD, 16));
		logout.setBounds(465, 13, 276, 16);
		logout.setText("#Logout");
		logout.setForeground(Color.decode("#843507"));
		frame.getContentPane().add(logout);
		logout.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				gamevisiblefalse();
			}
		});

		final JRadioButton easyButton = new JRadioButton("Easy");
		easyButton.setFont(new Font("Calibri Light", Font.BOLD, 13));
		easyButton.setForeground(Color.decode("#843507"));
		easyButton.setBounds(435, 350, 127, 25);
		easyButton.setBackground(Color.decode("#F1DCA9"));
		frame.getContentPane().add(easyButton);

		final JRadioButton mediumButton = new JRadioButton("Medium");
		mediumButton.setFont(new Font("Calibri Light", Font.BOLD, 13));
		mediumButton.setBounds(435, 380, 127, 25);
		mediumButton.setForeground(Color.decode("#843507"));
		mediumButton.setBackground(Color.decode("#F1DCA9"));
		frame.getContentPane().add(mediumButton);

		final JRadioButton hardButton = new JRadioButton("Hard");
		hardButton.setFont(new Font("Calibri Light", Font.BOLD, 13));
		hardButton.setBounds(435, 420, 127, 25);
		hardButton.setBackground(Color.decode("#F1DCA9"));
		hardButton.setForeground(Color.decode("#843507"));
		frame.getContentPane().add(hardButton);

		ButtonGroup bg = new ButtonGroup();
		bg.add(easyButton);
		bg.add(mediumButton);
		bg.add(hardButton);
		bg.setSelected(mediumButton.getModel(), true);

		startButton.setBounds(20, 515, 155, 37);

		startButton.setBackground(Color.decode("#843902"));
		startButton.setForeground(Color.decode("#FFFFFF"));
		frame.getContentPane().add(startButton);
		startButton.setFont(new Font("Calibri Light", Font.BOLD, 18));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				gamelevelOne = false;
				gamelevelTwo = false;
				gamelevelThree = false;
				checkdigit = 0;
				wrongNumber = 0;
				correntNumberr = 0;
				checkButton.setEnabled(true);

				if (isStarted) {
					checkButton.setEnabled(false);
					detailButton.setEnabled(false);
					saveprogressButton.setEnabled(false);
					isStarted = false;
					prevBoard = SudokuSolver.solve(prevBoard);
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							grid[i][j].setEditable(false);
							grid[i][j].setText(Integer.toString(prevBoard[i][j]));
							grid[i][j].setBackground(Color.decode("#E4A249"));

						}
					}
					startButton.setText("Start");
					timer.stop();
					timerLabel.setVisible(false);
					submitButton.setVisible(false);

					System.out.println("First");

				} else {

					difficulty = 1; // Default is medium.
					if (easyButton.isSelected()) {
						difficulty = 0;
						gamelevelOne = true;
					} else if (hardButton.isSelected()) {
						difficulty = 2;
						gamelevelTwo = true;
					} else {
						difficulty = 1;
						gamelevelThree = true;
					}

					if (difficulty == 0)
						timeCount = 600;
					else if (difficulty == 1)
						timeCount = 360;
					else
						timeCount = 180;

					int board[][] = new int[9][9];
					do {
						board = SudokuGenerator.generate(difficulty);
					} while (board[0][0] == -1);
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							prevBoard[i][j] = board[i][j];

						}
					}
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							if (board[i][j] != 0) {
								grid[i][j].setText(Integer.toString(board[i][j]));
								grid[i][j].setBackground(Color.decode("#B56E2C"));
								grid[i][j].setForeground(Color.decode("#FFFFFF"));
								checkdigit++;
							} else {
								grid[i][j].setText("");
								grid[i][j].setEditable(true);
								grid[i][j].setBackground(Color.decode("#E4A249"));
								grid[i][j].setForeground(Color.decode("#FFFFFF"));
							}

						}
					}
					submitButton.setVisible(true);
					startButton.setText("solution");
					timerLabel.setVisible(true);
					timer.start();
					isStarted = true;

					System.out.println("finally gap is done= " + checkdigit);
					System.out.println("second");
				}
			}
		});

		detailButton.setFont(new Font("Calibri Light", Font.BOLD, 18));
		detailButton.setBounds(415, 180, 155, 37);
		detailButton.setBackground(Color.decode("#843902"));
		detailButton.setForeground(Color.decode("#FFFFFF"));
		frame.getContentPane().add(detailButton);
		detailButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String getTime = timerLabel.getText().toString();
				int storescore = 0;
				storescore = (correntNumberr - checkdigit);
				System.out.println("correntNumberr= " + correntNumberr);
				System.out.println("Check digit= " + checkdigit);
				System.out.println("check score= " + storescore);
				String score = "0%";
				if (storescore <= 10 && storescore == 0) {
					score = "30%";
				} else if (storescore >= 11 && storescore <= 20) {
					score = "50%";
				} else if (storescore >= 21 && storescore <= 30) {
					score = "80%";
				} else if (storescore >= 31 && storescore <= 50) {
					score = "100%";
				}
				@SuppressWarnings("unused")
				PlayerStatistices player = new PlayerStatistices(getname, getTime, wrongNumber, score);
			}
		});

		saveprogressButton.setFont(new Font("Calibri Light", Font.BOLD, 18));
		saveprogressButton.setBounds(415, 240, 155, 37);
		saveprogressButton.setBackground(Color.decode("#843902"));
		saveprogressButton.setForeground(Color.decode("#FFFFFF"));
		frame.getContentPane().add(saveprogressButton);
		saveprogressButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					checkPlayerprogress(getname);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		checkButton.setFont(new Font("Calibri Light", Font.BOLD, 18));
		checkButton.setBounds(410, 120, 155, 37);
		checkButton.setBackground(Color.decode("#843902"));
		checkButton.setForeground(Color.decode("#FFFFFF"));
		frame.getContentPane().add(checkButton);
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				detailButton.setEnabled(true);
				saveprogressButton.setEnabled(true);
				correntNumberr = 0;
				wrongNumber = 0;

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (grid[i][j].getText().equals("")) {
							find++;
						} else {
							if (Integer.parseInt(grid[i][j].getText()) == prevBoard[i][j]) {
								correntNumberr++;
								// grid[i][j].setBackground(Color.GREEN);
							} else {
								wrongNumber++;
								grid[i][j].setBackground(Color.red);

							}
						}

					}

					if (find == 1) {
						find = 0;
						System.out.println("find is error= " + find);
						break;
					}

				}

			}
		});
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.decode("#F1DCA9"));
	}

	private void checkPlayerprogress(String getname) throws SQLException {
		int storescore = (correntNumberr - checkdigit);
		// int storescore = 4;
		if (manageprogress.check_playerName(getname) == true) {
			if (gamelevelOne == true) {
				manageprogress.updateprogressLevelOne(new UserProgress(getname, Integer.toString(storescore)));
				JOptionPane.showMessageDialog(null, "game progress add succesfully!!");
			} else if (gamelevelTwo == true) {
				manageprogress.updateprogressLevelTwo(new UserProgress(getname, Integer.toString(storescore)));
				JOptionPane.showMessageDialog(null, "game progress add succesfully!!");
			} else if (gamelevelThree == true) {
				manageprogress.updateprogressLevelThree(new UserProgress(getname, Integer.toString(storescore)));
				JOptionPane.showMessageDialog(null, "game progress add succesfully!!");
			}
		} else {
			manageprogress.add_userprogress(new UserProgress(0, getname, "0", "0", "0"));
			JOptionPane.showMessageDialog(null, "game progress add succesfully!!");
		}
	}

	private void gamevisiblefalse() {
		frame.setVisible(false);
		GameMenu gamemenue = new GameMenu();
	}
}
