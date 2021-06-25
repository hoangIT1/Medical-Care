package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.ClinicDAO;
import project.medical.core.Clinic;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUpdateClinicDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel thisPanel;
	private ClinicDAO clinicDAO;
	private Clinic prevClinic;
	private boolean updateMode;
	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField typeField;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUpdateClinicDialog dialog = new AddUpdateClinicDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public AddUpdateClinicDialog(JPanel thePanel, Clinic thePrevClinic, ClinicDAO theClinicDAO, boolean theUpdateMode) throws Exception {
		this();
		thisPanel = thePanel;
		prevClinic = thePrevClinic;
		clinicDAO = theClinicDAO;
		updateMode = theUpdateMode;
		
		if (updateMode) {
			setTitle("Update");
			populateGui(thePrevClinic);
		}
	}
	
	private void populateGui(Clinic thePrevClinic) {
		idField.setText(thePrevClinic.getID());
		nameField.setText(thePrevClinic.getClinicName());
		addressField.setText(thePrevClinic.getAddress());
		phoneField.setText(thePrevClinic.getPhoneNum());
		emailField.setText(thePrevClinic.getEmail());
		typeField.setText(thePrevClinic.getType());
	}
	
	public AddUpdateClinicDialog() throws Exception {
		
		setBounds(100, 100, 450, 331);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Clinic Name:");
			lblNewLabel.setBounds(new Rectangle(39, 54, 72, 14));
			contentPanel.add(lblNewLabel);
		}
		{
			nameField = new JTextField();
			nameField.setBounds(new Rectangle(132, 54, 253, 20));
			contentPanel.add(nameField);
			nameField.setColumns(10);
		}
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(new Rectangle(142, 10, 253, 20));
		addressField.setBounds(132, 94, 253, 20);
		contentPanel.add(addressField);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(new Rectangle(37, 13, 72, 14));
		lblAddress.setBounds(39, 94, 72, 14);
		contentPanel.add(lblAddress);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(new Rectangle(142, 10, 253, 20));
		phoneField.setBounds(132, 138, 253, 20);
		contentPanel.add(phoneField);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(new Rectangle(37, 13, 72, 14));
		lblPhoneNumber.setBounds(39, 138, 83, 14);
		contentPanel.add(lblPhoneNumber);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(new Rectangle(142, 10, 253, 20));
		emailField.setBounds(132, 182, 253, 20);
		contentPanel.add(emailField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setBounds(new Rectangle(37, 13, 72, 14));
		lblNewLabel_1_1.setBounds(39, 182, 72, 14);
		contentPanel.add(lblNewLabel_1_1);
		
		typeField = new JTextField();
		typeField.setColumns(10);
		typeField.setBounds(new Rectangle(142, 10, 253, 20));
		typeField.setBounds(132, 228, 253, 20);
		contentPanel.add(typeField);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Type:");
		lblNewLabel_1_1_1.setBounds(new Rectangle(37, 13, 72, 14));
		lblNewLabel_1_1_1.setBounds(39, 228, 72, 14);
		contentPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblClinicId = new JLabel("Clinic ID:");
		lblClinicId.setBounds(new Rectangle(39, 54, 72, 14));
		lblClinicId.setBounds(39, 11, 72, 14);
		contentPanel.add(lblClinicId);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(new Rectangle(132, 54, 253, 20));
		idField.setBounds(132, 11, 253, 20);
		contentPanel.add(idField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveClinic();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	// Saveclinic
	protected void saveClinic() {
		
		String id = idField.getText();
		String clinicName = nameField.getText();
		String address = addressField.getText();
		String phoneNum = phoneField.getText();
		String email = emailField.getText();
		String type = typeField.getText();
		
		Clinic temp = null;
		if(updateMode) {
		
			temp = prevClinic;
			temp.setClinicName(clinicName);
			temp.setAddress(address);
			temp.setPhoneNum(phoneNum);
			temp.setEmail(email);
			temp.setType(type);
			temp.setID(id);
		}
		else {
			temp = new Clinic(id, clinicName, address, phoneNum, email, type);
		}
		try {
			if (updateMode) {
				clinicDAO.updateclinic(temp);
				JOptionPane.showMessageDialog(thisPanel, "Updated successfully","Updated successfully ", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				clinicDAO.addClinic(temp);
				JOptionPane.showMessageDialog(thisPanel, "Added successfully", "Added successfully ", JOptionPane.INFORMATION_MESSAGE);
			}
			
			setVisible(false);
			dispose();	
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(thisPanel,"Error saving: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
	
}
