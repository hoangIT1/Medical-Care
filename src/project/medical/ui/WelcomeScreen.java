package project.medical.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;

import project.medical.DAO.ClinicDAO;
import project.medical.DAO.EventDAO;
import project.medical.DAO.HistoryMedicalDAO;
import project.medical.DAO.KidDAO;
import project.medical.DAO.MomDAO;
import project.medical.DAO.WeightHeightDAO;
import project.medical.core.Clinic;
import project.medical.core.Event;
import project.medical.core.Kid;
import project.medical.core.Mom;

import javax.swing.JToolBar;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.Icon;
import java.awt.FlowLayout;


public class WelcomeScreen {

	private JFrame MainScreen;
	private JTextField kidNameField;
	private KidDAO kidDAO;
	private MomDAO momDAO;
	private EventDAO eventDAO;
	private HistoryMedicalDAO  histDAO;
	private WeightHeightDAO whDAO;
	private ClinicDAO clinicDAO;
	private JTable kidTable;
	private JTable momTable;
	private JTextField momNameField;
	private JButton btnNewButton_15;
	private JTable eventTable;
	private JTextField clinicNameField;
	private JTable clinicTable;


	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen window = new WelcomeScreen();
					window.MainScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	
	public WelcomeScreen() throws Exception {
		kidDAO = new KidDAO();
		momDAO = new MomDAO();
		histDAO = new HistoryMedicalDAO();
		whDAO = new WeightHeightDAO();
		eventDAO = new EventDAO();
		clinicDAO = new ClinicDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		MainScreen = new JFrame();
		MainScreen.getContentPane().setMaximumSize(new Dimension(850, 700));
		//MainScreen.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//MainScreen.setUndecorated(true);
		MainScreen.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		MainScreen.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\eclipse-workspace\\Medical\\Image\\healthcareIcon.png"));
		MainScreen.getContentPane().setBackground(new Color(192, 192, 192));
		MainScreen.setTitle("Health Center");
		MainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainScreen.setBackground(new Color(0, 0, 0));
		MainScreen.setBounds(100, 100, 850, 700);
		MainScreen.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBorder(new CompoundBorder());
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		MainScreen.getContentPane().add(tabbedPane);
		
		JPanel panel_home = new JPanel();
		panel_home.setMaximumSize(new Dimension(650, 650));
		panel_home.setBackground(new Color(0, 191, 255));
		tabbedPane.addTab("HOME", (Icon) null, panel_home, null);
		tabbedPane.setDisabledIconAt(0, null);
		panel_home.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\Medical\\Image\\home.jpg"));
		panel_home.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_kids = new JPanel();
		tabbedPane.addTab("KIDS", null, panel_kids, null);
		panel_kids.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(0, 255, 51));
		panel_kids.add(toolBar, BorderLayout.NORTH);
		// ADD KID
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(0, 255, 51));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateDialog adddialog = new AddUpdateDialog(panel_kids, kidDAO, null, null, null, false, false);
				adddialog.setVisible(true);
					
			}
		});
		toolBar.add(btnNewButton);
		// UPDATE KID
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_1.setBackground(new Color(0, 255, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = kidTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_kids,"Please select a kid","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Kid temp = (Kid) kidTable.getValueAt(row, KidTableModel.OBJECT_COL);
				
				AddUpdateDialog updatedialog = new AddUpdateDialog(panel_kids, kidDAO, temp, null, null, false, true);
				
				updatedialog.setVisible(true);
				
			}		
		});
		toolBar.add(btnNewButton_1);
		// DELETE KID
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_2.setBackground(new Color(0, 255, 51));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = kidTable.getSelectedRow();
					if(row <0) {
						JOptionPane.showMessageDialog(panel_kids,"Please select a kid","Warning",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Kid temp = (Kid) kidTable.getValueAt(row, KidTableModel.OBJECT_COL);
					kidDAO.deleteKid(temp.getID());
					histDAO.deleteHist(temp.getID());
					whDAO.deleteWH(temp.getID());
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_kids,"Deleted","Deleted",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		toolBar.add(btnNewButton_2);
		// SEARCH KID
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_3.setBackground(new Color(0, 255, 51));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name = kidNameField.getText();
				
					List <Kid> kids = null;
					
					if(name != null && name.trim().length() > 0 ) {
						kids = kidDAO.getKidByName(name);
					}
					else {
						kids = kidDAO.getAllKid();
					}
					
                    KidTableModel model = new KidTableModel(kids);
                    kidTable.setModel(model);
                    
	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(panel_kids, "Error: "+ exc, "Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		toolBar.add(btnNewButton_3);
		
		kidNameField = new JTextField();
		toolBar.add(kidNameField);
		kidNameField.setColumns(10);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(new Color(0, 255, 255));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		panel_kids.add(toolBar_1, BorderLayout.WEST);
		
		// APPOINTMENT 1
		JButton btnNewButton_7 = new JButton("Appointment");
		btnNewButton_7.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_7.setBackground(new Color(0, 255, 51));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					AppoinmentTab aptab;
					try {
						aptab = new AppoinmentTab();
						aptab.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				
			}
		});
		btnNewButton_7.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar_1.add(btnNewButton_7);
		
		// VACCINATION KID
		JButton btnNewButton_4 = new JButton("Vaccination");
		btnNewButton_4.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_4.setBackground(new Color(0, 255, 51));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = kidTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_kids,"Please select a kid","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Kid temp = (Kid) kidTable.getValueAt(row, KidTableModel.OBJECT_COL);
				String id = temp.getID();
				VaccinationTab vactab = null;
				try {
					vactab = new VaccinationTab(id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				vactab.setVisible(true);
				
				
				
			}
		});
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setMinimumSize(new Dimension(93, 23));
		btnNewButton_4.setMaximumSize(new Dimension(93, 23));
		toolBar_1.add(btnNewButton_4);
		// INDEX KID
		JButton btnNewButton_6 = new JButton("Index");
		btnNewButton_6.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_6.setBackground(new Color(0, 255, 51));
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setMaximumSize(new Dimension(93, 23));
		btnNewButton_6.setMinimumSize(new Dimension(93, 23));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = kidTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_kids,"Please select a kid","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Kid temp = (Kid) kidTable.getValueAt(row, KidTableModel.OBJECT_COL);
				String idperson = temp.getID();
				IndexTab idextab;
				try {
					idextab = new IndexTab(idperson);
					idextab.setVisible(true);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		toolBar_1.add(btnNewButton_6);
		
		
		kidTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(kidTable);
		panel_kids.add(scrollPane, BorderLayout.CENTER);
		
		
		
		
		JPanel panel_moms = new JPanel();
		panel_moms.setBackground(Color.RED);
		tabbedPane.addTab("MOMS", null, panel_moms, null);
		panel_moms.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setBackground(new Color(0, 255, 51));
		toolBar_2.setForeground(Color.WHITE);
		panel_moms.add(toolBar_2, BorderLayout.NORTH);
		
		// ADD MOM
		JButton btnNewButton_8 = new JButton("Add");
		btnNewButton_8.setBackground(new Color(0, 255, 51));
		btnNewButton_8.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateDialog adddialog = new AddUpdateDialog(panel_moms, null, null, momDAO, null, true, false);
				adddialog.setVisible(true);
				
			}
		});
		toolBar_2.add(btnNewButton_8);
		
		// UPDATE MOM
		JButton btnNewButton_9 = new JButton("Update");
		btnNewButton_9.setBackground(new Color(0, 255, 51));
		btnNewButton_9.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = momTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_kids,"Please select a mom","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Mom temp = (Mom) momTable.getValueAt(row, MomTableModel.OBJECT_COL);
				
				AddUpdateDialog updatedialog = new AddUpdateDialog(panel_kids, null, null, momDAO, temp, true, true);
				
				updatedialog.setVisible(true);
				
				
				
				
			}
		});
		toolBar_2.add(btnNewButton_9);
		// DELETE MOM
		JButton btnNewButton_10 = new JButton("Delete");
		btnNewButton_10.setBackground(new Color(0, 255, 51));
		btnNewButton_10.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = momTable.getSelectedRow();
					if(row <0) {
						JOptionPane.showMessageDialog(panel_moms,"Please select a mom","Warning",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Mom temp = (Mom) momTable.getValueAt(row, MomTableModel.OBJECT_COL);
					momDAO.deleteMom(temp.getID());
					histDAO.deleteHist(temp.getID());
					whDAO.deleteWH(temp.getID());
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_moms,"Deleted","Deleted",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		toolBar_2.add(btnNewButton_10);
		// SEARCH MOM
		JButton btnNewButton_11 = new JButton("Search");
		btnNewButton_11.setBackground(new Color(0, 255, 51));
		btnNewButton_11.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name = momNameField.getText();
				
					List <Mom> moms = null;
					
					if(name != null && name.trim().length() > 0 ) {
						moms = momDAO.getMomByName(name);
					}
					else {
						moms = momDAO.getAllMom();
					}
					
                    MomTableModel model2 = new MomTableModel(moms);
                    momTable.setModel(model2);
                    
	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(panel_kids, "Error: "+ exc, "Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		toolBar_2.add(btnNewButton_11);
		
		momNameField = new JTextField();
		toolBar_2.add(momNameField);
		momNameField.setColumns(10);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setBackground(new Color(0, 255, 255));
		toolBar_3.setOrientation(SwingConstants.VERTICAL);
		panel_moms.add(toolBar_3, BorderLayout.WEST);
		//APPOINTMENT 2
		JButton btnNewButton_13 = new JButton("Appointment");
		btnNewButton_13.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_13.setBackground(new Color(0, 255, 51));
		btnNewButton_13.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppoinmentTab aptab;
				try {
					aptab = new AppoinmentTab();
					aptab.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		toolBar_3.add(btnNewButton_13);
		// VACCINATION MOM
		JButton btnNewButton_14 = new JButton("Vaccination");
		btnNewButton_14.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_14.setBackground(new Color(0, 255, 51));
		btnNewButton_14.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_14.setMinimumSize(new Dimension(93, 23));
		btnNewButton_14.setMaximumSize(new Dimension(93, 23));
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = momTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_moms,"Please select a mom","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Mom temp = (Mom) momTable.getValueAt(row, KidTableModel.OBJECT_COL);
				String id = temp.getID();
				VaccinationTab vactab = null;
				try {
					vactab = new VaccinationTab(id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				vactab.setVisible(true);
				
				
				
			}
		});
		toolBar_3.add(btnNewButton_14);
		// INDEX MOM
		btnNewButton_15 = new JButton("Index");
		btnNewButton_15.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_15.setBackground(new Color(0, 255, 51));
		btnNewButton_15.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_15.setMinimumSize(new Dimension(93, 23));
		btnNewButton_15.setMaximumSize(new Dimension(93, 23));
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = momTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_moms,"Please select a Mom","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Mom temp = (Mom) momTable.getValueAt(row, MomTableModel.OBJECT_COL);
				String idperson = temp.getID();
				IndexTab idextab;
				try {
					idextab = new IndexTab(idperson);
					idextab.setVisible(true);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
					
				
				
			}
		});
		toolBar_3.add(btnNewButton_15);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_moms.add(scrollPane_1, BorderLayout.CENTER);
		
		momTable = new JTable();
		scrollPane_1.setViewportView(momTable);
		
		JPanel panel_clinic = new JPanel();
		tabbedPane.addTab("CLINIC", null, panel_clinic, null);
		panel_clinic.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_4 = new JToolBar();
		toolBar_4.setBackground(new Color(0, 255, 51));
		panel_clinic.add(toolBar_4, BorderLayout.NORTH);
		// ADD CLINIC
		JButton btnNewButton_16 = new JButton("Add");
		btnNewButton_16.setBackground(new Color(0, 255, 51));
		btnNewButton_16.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateClinicDialog diag;
				try {
					diag = new AddUpdateClinicDialog(panel_clinic, null,clinicDAO, false);
					diag.setVisible(true);
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		toolBar_4.add(btnNewButton_16);
		// UPDATE CLINIC
		JButton btnNewButton_17 = new JButton("Update");
		btnNewButton_17.setBackground(new Color(0, 255, 51));
		btnNewButton_17.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = clinicTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_clinic,"Please select a clinic","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Clinic temp = (Clinic) clinicTable.getValueAt(row, ClinicTableModel.OBJECT_COL);
				
				AddUpdateClinicDialog updatedialog;
				try {
					updatedialog = new AddUpdateClinicDialog(panel_clinic, temp, clinicDAO, true);
					updatedialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		toolBar_4.add(btnNewButton_17);
		// DELETE CLINIC
		JButton btnNewButton_18 = new JButton("Delete");
		btnNewButton_18.setBackground(new Color(0, 255, 51));
		btnNewButton_18.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = clinicTable.getSelectedRow();
					if(row <0) {
						JOptionPane.showMessageDialog(panel_moms,"Please select a clinic","Warning",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Clinic temp = (Clinic) clinicTable.getValueAt(row, ClinicTableModel.OBJECT_COL);
					clinicDAO.deleteClinic(temp.getID());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_clinic,"Deleted","Deleted",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		toolBar_4.add(btnNewButton_18);
		// SEARCH CLINIC
		JButton btnNewButton_19 = new JButton("Search");
		btnNewButton_19.setBackground(new Color(0, 255, 51));
		btnNewButton_19.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name = clinicNameField.getText();
				
					List <Clinic> clinics = null;
					
					if(name != null && name.trim().length() > 0 ) {
						clinics = clinicDAO.getClinicByName(name);
					}
					else {
						clinics = clinicDAO.getAllClicnic();
					}
					
                    ClinicTableModel model = new ClinicTableModel(clinics);
                    clinicTable.setModel(model);
                    
	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(panel_clinic, "Error: "+ exc, "Error",JOptionPane.ERROR_MESSAGE);
				}				
				
				
			}
		});
		toolBar_4.add(btnNewButton_19);
		
		clinicNameField = new JTextField();
		toolBar_4.add(clinicNameField);
		clinicNameField.setColumns(10);
		
		JToolBar toolBar_5 = new JToolBar();
		toolBar_5.setBackground(new Color(0, 255, 255));
		panel_clinic.add(toolBar_5, BorderLayout.WEST);
		
		clinicTable = new JTable();
		JScrollPane scrollPane_2 = new JScrollPane(clinicTable);
		panel_clinic.add(scrollPane_2, BorderLayout.CENTER);
		
		

		
		JPanel panel_events = new JPanel();
		tabbedPane.addTab("EVENT", null, panel_events, null);
		panel_events.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_41 = new JToolBar();
		toolBar_41.setBackground(new Color(0, 255, 51));
		panel_events.add(toolBar_41, BorderLayout.NORTH);
		// SHOW EVENT
		JButton btnNewButton_181 = new JButton("Show events");
		btnNewButton_181.setBackground(new Color(0, 255, 51));
		btnNewButton_181.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_181.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					List<Event> events = eventDAO.getAllEvent();			
                    EventTableModel eventmodel = new EventTableModel(events);
                    eventTable.setModel(eventmodel);
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(panel_events, "Error: "+ exc, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnNewButton_5 = new JButton("Statistic");
		btnNewButton_5.setBackground(new Color(0, 255, 51));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrawChart drawer;
				try {
					drawer = new DrawChart();
					drawer.drawingStatisticChart();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_5.setFont(new Font("Arial", Font.BOLD, 11));
		toolBar_41.add(btnNewButton_5);
		toolBar_41.add(btnNewButton_181);
		// CREATE EVENT
		JButton btnNewButton_161 = new JButton("Create New Event");
		btnNewButton_161.setBackground(new Color(0, 255, 51));
		btnNewButton_161.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_161.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEventDialog adddialog;
				try {
					adddialog = new AddEventDialog();
					adddialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		toolBar_41.add(btnNewButton_161);
		// SEND EVENT EMAIL
		JButton btnNewButton_171 = new JButton("Send Email");
		btnNewButton_171.setBackground(new Color(0, 255, 51));
		btnNewButton_171.setForeground(Color.BLACK);
		btnNewButton_171.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_171.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = eventTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_events,"Please select an Event","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				List<String> allEmails = new  ArrayList<String>();
				try {
					List<Kid> allKids = kidDAO.getAllKid();
					List<Mom> allMoms = momDAO.getAllMom();
					for(Kid k: allKids) {
						String s = k.getEmail();
						allEmails.add(s);
					}
					for(Mom p: allMoms) {
						String t  =  p.getEmail();
						allEmails.add(t);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				Event temp = (Event) eventTable.getValueAt(row, EventTableModel.OBJECT_COL);
				EmailSender sender;
				try {
					sender = new EmailSender(null, null);
					sender.sendEvent(temp, allEmails);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		// DELETE EVENT
		JButton btnNewButton_191 = new JButton("Delete Event");
		btnNewButton_191.setBackground(new Color(0, 255, 51));
		btnNewButton_191.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_191.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = eventTable.getSelectedRow();
					if(row <0) {
						JOptionPane.showMessageDialog(panel_events,"Please select an event","Warning",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					Event temp = (Event) eventTable.getValueAt(row, EventTableModel.OBJECT_COL);
					eventDAO.deleteEvent(temp.getName());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(panel_clinic,"Deleted","Deleted",JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});
		toolBar_41.add(btnNewButton_191);
		toolBar_41.add(btnNewButton_171);
		
		JPanel panel = new JPanel();
		panel_events.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		eventTable = new JTable();
		JScrollPane scrollPane_21 = new JScrollPane(eventTable);
		panel.add(scrollPane_21, BorderLayout.CENTER);

		JPanel panel_about = new JPanel();
		tabbedPane.addTab("ABOUT", null, panel_about, null);
		panel_about.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\Medical\\Image\\about.jpg"));
		panel_about.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_about.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_12 = new JButton("User Manual");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String url = "https://www.youtube.com/watch?v=D8l4B7sSS-4&feature=youtu.be&fbclid=IwAR3AhpAbeB6jjtFHEx6HRZJ6ywwCVKxYmP8xOrLenjbKGIsecMPMSAfljDc";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_12.setFont(new Font("Arial", Font.BOLD, 11));
		panel_1.add(btnNewButton_12);
	}
  }

