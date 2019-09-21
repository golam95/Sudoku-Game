package com.rocky.sudoku.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.rocky.sudoku.daoImp.ManageUserDaoImp;
import com.rocky.sudoku.daoImp.ManageUserProgressDaoImp;
import com.rocky.sudoku.game.Sudoku;

public class UserAccesability extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_save;
	private JButton btn_remove;
	private JTextField txt_Name;
	Font font = new Font("Elephant", Font.BOLD, 12);
	ManageUserDaoImp manageuser = new ManageUserDaoImp();
	ManageUserProgressDaoImp manageprocess = new ManageUserProgressDaoImp();

	public UserAccesability() {

		try {

			this.setSize(400, 200);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			JLabel lbl_Name = new JLabel("Name: ");

			txt_Name = new JTextField();
			btn_save = new JButton("Go");
			lbl_Name.setBounds(50, 36, 180, 30);
			txt_Name.setBounds(130, 36, 210, 30);
			btn_save.setBounds(130, 85, 210, 30);
			btn_save.setBackground(Color.decode("#843902"));
			btn_save.setForeground(Color.decode("#FFFFFF"));

			btn_remove = new JButton("Remove");
			btn_remove.setBounds(130, 125, 210, 30);
			btn_remove.setBackground(Color.decode("#843902"));
			btn_remove.setForeground(Color.decode("#FFFFFF"));

			lbl_Name.setFont(font);
			lbl_Name.setForeground(Color.decode("#9F410C"));
			txt_Name.setFont(font);
			btn_save.setFont(font);
			btn_remove.setFont(font);

			this.add(lbl_Name);
			this.add(txt_Name);
			this.add(btn_save);
			this.add(btn_remove);
			btn_save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String getName = txt_Name.getText().toString();
					if (getName.equals("")) {
						JOptionPane.showMessageDialog(null, "Sorry,Field must not be empty!!");
					} else {

						try {
							if (manageuser.checklogin(getName) == true) {
								@SuppressWarnings("unused")
								Sudoku sudoku = new Sudoku(getName);
								GameMenu.visiblefalseMenu();
								removeUseraccessbility();
							} else {
								JOptionPane.showMessageDialog(null, "Sorry,Wrong Username!!");
							}
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					}

				}
			});

			btn_remove.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String getName = txt_Name.getText().toString();
					if (getName.equals("")) {
						JOptionPane.showMessageDialog(null, "Sorry,Field must not be empty!!");
					} else {

						try {
							manageuser.delete_user(getName);
							manageprocess.delete_user(getName);
							JOptionPane.showMessageDialog(null, "Remove user successfully!!");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}
			});

			this.getContentPane().setBackground(Color.decode("#F1DCA9"));
			this.setVisible(true);

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Error UserAccesability panel" + ex.toString());

		}
	}

	private void removeUseraccessbility() {

		this.setVisible(false);
	}

}
