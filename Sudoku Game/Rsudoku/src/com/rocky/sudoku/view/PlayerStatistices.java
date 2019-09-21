package com.rocky.sudoku.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

import com.rocky.sudoku.daoImp.DBConnection;

public class PlayerStatistices extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btn_save;
	Font font = new Font("Elephant", Font.BOLD, 12);
	private JPanel pan_graph = new JPanel();
	private String getName = null;
	private String getTime = null;
	private int wrongAns = 0;
	private String gameScore = null;

	public PlayerStatistices(String name, String time, int wrong, String score) {
		try {
			this.setSize(500, 390);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			getName = name;
			getTime = time;
			wrongAns = wrong;
			gameScore = score;
			JLabel lblcurrentpuzzle = new JLabel();
			JLabel lblaveragetime = new JLabel();
			JLabel lblmistake = new JLabel();

			lblcurrentpuzzle.setBounds(21, 240, 200, 40);
			lblcurrentpuzzle.setFont(font);
			lblcurrentpuzzle.setForeground(Color.decode("#843902"));
			lblcurrentpuzzle.setText("Current  progress :" + gameScore);
			this.add(lblcurrentpuzzle);

			lblaveragetime.setBounds(21, 270, 350, 40);
			lblaveragetime.setFont(font);
			lblaveragetime.setForeground(Color.decode("#843902"));
			lblaveragetime.setText("Average" + getTime);
			this.add(lblaveragetime);

			lblmistake.setBounds(310, 240, 180, 40);
			lblmistake.setFont(font);
			lblmistake.setForeground(Color.decode("#843902"));
			lblmistake.setText("Total mistake :" + wrongAns);
			this.add(lblmistake);

			pan_graph.setBounds(0, 0, 500, 240);
			pan_graph.setLayout(new GridLayout(0, 1));
			pan_graph.setBackground(Color.BLUE);
			this.add(pan_graph);

			btn_save = new JButton("Cancel");
			// txt_Name.setBounds(130, 36, 210, 30);
			btn_save.setBounds(150, 323, 210, 30);
			btn_save.setBackground(Color.decode("#843902"));
			btn_save.setForeground(Color.decode("#FFFFFF"));
			btn_save.setFont(font);
			this.add(btn_save);
			btn_save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeUseraccessbility();
				}
			});
			draw_graph(getName);
			this.getContentPane().setBackground(Color.decode("#F1DCA9"));
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error UserAccesability panel" + ex.toString());
		}
	}

	private void removeUseraccessbility() {
		this.setVisible(false);
	}

	public void draw_graph(String username) {
		try {
			Connection conn = DBConnection.getConnecttion();
			String query = "Select p_level1,p_level3 from progress where p_name='" + username + "'";
			JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, query);
			JFreeChart chartsa = ChartFactory.createBarChart3D("PLayer statistices", "Level", "Overall Progress",
					dataset, PlotOrientation.VERTICAL, false, true, true);
			chartsa.setBackgroundPaint(Color.decode("#F1DCA9"));
			chartsa.getTitle().setPaint(Color.WHITE);
			CategoryPlot plot = chartsa.getCategoryPlot();
			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			plot.setRangeGridlinePaint(Color.WHITE);
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			ChartPanel chartpanel = new ChartPanel(chartsa);
			pan_graph.add(chartpanel);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

}
