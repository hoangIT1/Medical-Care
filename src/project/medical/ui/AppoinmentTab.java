package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.HistoryMedicalDAO;

import project.medical.core.Person;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AppoinmentTab extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable todayTable;
	private JTextField tomorrowField;
	private JTable tomTable;
	private HistoryMedicalDAO histDAO;
	private JTextField todayField;
	private List<String> emailToday;
	private List<String> emailTomorrow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppoinmentTab frame = new AppoinmentTab();
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
	public AppoinmentTab() throws Exception {
		setType(Type.UTILITY);
		setTitle("Appointment");

		setBounds(100, 100, 580, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 564, 30);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Send Email to All");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailSender sender;
				try {
					sender = new EmailSender(emailToday, emailTomorrow);
					sender.sendToday();
					sender.sendTomorrow();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 29, 278, 360);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		todayTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(todayTable);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		

		
		todayField = new JTextField();
		todayField.setFont(new Font("Tahoma", Font.BOLD, 12));
		todayField.setHorizontalAlignment(SwingConstants.CENTER);
		todayField.setEditable(false);
		panel_1.add(todayField, BorderLayout.NORTH);
		todayField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(277, 29, 287, 360);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		tomorrowField = new JTextField();
		tomorrowField.setFont(new Font("Tahoma", Font.BOLD, 12));
		tomorrowField.setHorizontalAlignment(SwingConstants.CENTER);
		tomorrowField.setEditable(false);
		panel_2.add(tomorrowField, BorderLayout.NORTH);
		tomorrowField.setColumns(10);
		
		tomTable = new JTable();
		JScrollPane scrollPane_1 = new JScrollPane(tomTable);
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		
		
		histDAO = new HistoryMedicalDAO();
		emailToday = new ArrayList<String>();
		emailTomorrow = new ArrayList<String>();
		List<Person> listPersonToday = histDAO.getPersonToday();
		List<Person> listPersonTomorrow = histDAO.getPersonTomorrow();
		for(Person p: listPersonToday) {
			
			String e = p.getEmail();
			System.out.println(e);
			emailToday.add(e);
		}
		for(Person p: listPersonTomorrow) {
			String e = p.getEmail();
			System.out.println(e);
			emailTomorrow.add(e);
		}
		int numToday = listPersonToday.size();
		int numTom = listPersonTomorrow.size();
		
        
		
		AppointmentTableModel modeltoday = new AppointmentTableModel(listPersonToday);
        AppointmentTableModel modeltomorrow  = new AppointmentTableModel(listPersonTomorrow);
        todayTable.setModel(modeltoday);
		tomTable.setModel(modeltomorrow);
        
		String txt1 = "Today, we have "+ numToday;
        String txt2 = "Tomorrow, we have "+ numTom;
        todayField.setText(txt1);
        tomorrowField.setText(txt2);

	}
}
