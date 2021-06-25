package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.WeightHeightDAO;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;


public class IndexTab extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DrawChart myDrawer;
	private String personID;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private WeightHeightDAO whDAO;
	private JButton btnNewButton_3;
	private JPanel panel_1;
	private JTextField bmiField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField advicesField;
	private JTextField stateField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndexTab frame = new IndexTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public IndexTab(String thepersonID) throws Exception {
		this();
		this.personID = thepersonID;
		whDAO = new WeightHeightDAO();
		myDrawer = new DrawChart(personID);
	}
	
	
	public IndexTab() {
		setType(Type.UTILITY);
		setTitle("Index");
		setBounds(100, 100, 420, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnNewButton = new JButton("Height Chart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDrawer.drawingHeightChart();
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnNewButton_3 = new JButton("Add");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddIndexDialog addialog;
				try {
					addialog = new AddIndexDialog(personID);
					addialog.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnNewButton_3);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Weight Chart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDrawer.drawingWeightChart();
			}
		});
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Advices");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cur_bmi = 0;
				try {
					cur_bmi = whDAO.getBmiByID(personID);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				String state = null, advices = null;
				
				if (cur_bmi == 0) {
				} else if (cur_bmi < 16) {
					state = "Servere Thinness";
					advices = "You should do excercises and eat healthy";
				} else if (cur_bmi < 17) {
					state = "Moderate Thinness";
					advices = "You should do  excercises and eat healthy";
				} else if (cur_bmi < 18.5) {
					state = "Mild Thinness";
					advices = "You should do  excercises and eat healthy";
				} else if (cur_bmi < 25) {
					state = "Normal";
					advices = "OK. Keep eating healthy";
				} else if (cur_bmi < 30) {
					state = "Overweight";
					advices = "You should do more excercises and start eating diet food";
				} else if (cur_bmi < 35) {
					state = "Obese class I";
					advices = "You should do more excercises and start eating diet food";
				} else if (cur_bmi < 40 ) {
					state = "Obese class II";
					advices = "You should do more excercises and start eating diet food";
				} else if (cur_bmi > 40) {
					state = "Obese class III";
					advices = "You should do more excercises and start eating diet food";
				}
				
				bmiField.setText(Double.toString(cur_bmi));
				stateField.setText(state);
				advicesField.setText(advices);
				
			}
		});
		panel.add(btnNewButton_2);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		bmiField = new JTextField();
		bmiField.setEditable(false);
		bmiField.setBounds(130, 65, 139, 20);
		panel_1.add(bmiField);
		bmiField.setColumns(10);
		
		lblNewLabel = new JLabel("Click Advices to show details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 394, 14);
		panel_1.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("BMI index");
		lblNewLabel_1.setBounds(33, 68, 66, 14);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Details");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 118, 394, 14);
		panel_1.add(lblNewLabel_2);
		
		advicesField = new JTextField();
		advicesField.setHorizontalAlignment(SwingConstants.CENTER);
		advicesField.setEditable(false);
		advicesField.setBounds(10, 223, 374, 38);
		panel_1.add(advicesField);
		advicesField.setColumns(10);
		
		stateField = new JTextField();
		stateField.setEditable(false);
		stateField.setHorizontalAlignment(SwingConstants.CENTER);
		stateField.setBounds(10, 164, 374, 38);
		panel_1.add(stateField);
		stateField.setColumns(10);
	}
}
