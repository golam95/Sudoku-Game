package com.rocky.sudoku.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.rocky.sudoku.daoImp.ManageUserDaoImp;
import com.rocky.sudoku.model.User;

public class CreateAccount extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btn_save;
	private JTextField txt_Name, txt_Age;
	private JComboBox<String> cmb_gender;
	private String array_gender[] = { "Male", "Female" };
	Font font = new Font("Elephant", Font.BOLD, 12);

	ManageUserDaoImp manageuser = new ManageUserDaoImp();

	public CreateAccount() {

		this.createAccount();
	}

	public void createAccount() {
		try {

			this.setSize(400, 230);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			JLabel lbl_Name = new JLabel("Name: ");
			JLabel lbl_Age = new JLabel("Age: ");
			JLabel lbl_Gender = new JLabel("Gender: ");
			txt_Name = new JTextField();
			txt_Age = new JTextField();
			cmb_gender = new JComboBox<String>();
			btn_save = new JButton("Save");

			for (int i = 0; i < array_gender.length; i++) {
				cmb_gender.addItem(array_gender[i]);
			}

			lbl_Name.setBounds(50, 20, 180, 30);
			lbl_Age.setBounds(50, 60, 180, 30);
			lbl_Gender.setBounds(50, 100, 180, 30);
			txt_Name.setBounds(130, 20, 210, 30);
			txt_Age.setBounds(130, 60, 210, 30);
			cmb_gender.setBounds(130, 100, 210, 30);
			btn_save.setBounds(130, 150, 210, 30);
			btn_save.setBackground(Color.decode("#843902"));
			btn_save.setForeground(Color.decode("#FFFFFF"));

			lbl_Name.setFont(font);
			lbl_Age.setFont(font);
			lbl_Gender.setFont(font);

			lbl_Name.setForeground(Color.decode("#9F410C"));
			lbl_Age.setForeground(Color.decode("#9F410C"));
			lbl_Gender.setForeground(Color.decode("#9F410C"));
			cmb_gender.setForeground(Color.decode("#9F410C"));

			txt_Name.setFont(font);
			txt_Age.setFont(font);
			cmb_gender.setFont(font);
			btn_save.setFont(font);

			btn_save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					String name = txt_Name.getText().toString();
					String age = txt_Age.getText().toString();
					String gender = cmb_gender.getSelectedItem().toString();
					String current_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					if (name.equals("") || age.equals("")) {

						JOptionPane.showMessageDialog(null, "Field must not be empty!!");

					} else {

						try {
							if (manageuser.check_playerName(name)) {

								JOptionPane.showMessageDialog(null, "Sorry,Username already existing!!");

							} else {
								try {
									manageuser.add_playerInfo(new User(0, name, age, gender, current_date));
									JOptionPane.showMessageDialog(null, "Please enjoy sudoku game.");
									accountpanelfalse();

								} catch (SQLException e1) {
									e1.printStackTrace();
								}

							}
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				}
			});

			this.add(lbl_Name);
			this.add(lbl_Age);
			this.add(lbl_Gender);
			this.add(txt_Name);
			this.add(txt_Age);
			this.add(cmb_gender);
			this.add(btn_save);
			this.getContentPane().setBackground(Color.decode("#F1DCA9"));
			this.setVisible(true);

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Error createAccount panel" + ex.toString());

		}
	}

	private void accountpanelfalse() {

		this.setVisible(false);

	}

}
