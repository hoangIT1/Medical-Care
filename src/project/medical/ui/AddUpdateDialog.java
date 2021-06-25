package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.KidDAO;
import project.medical.DAO.MomDAO;
import project.medical.core.Kid;
import project.medical.core.Mom;
import project.medical.core.Person;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddUpdateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel thisPanel;
	private MomDAO momDAO;
    private KidDAO kidDAO;
    private Mom prevMom ;
    private Kid prevKid ;
    private boolean momtab;
    private boolean updateMode;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private JTextField idField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField dateOfBirthField;
    private JTextField genderField = null;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField parentNameField = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddUpdateDialog dialog = new AddUpdateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddUpdateDialog(JPanel thePanel,KidDAO theKidDAO, Kid thePrevKid, MomDAO theMomDAO, Mom thePrevMom,
			boolean theMomtab,  boolean theUpdateMode) {
    	this();
    	thisPanel = thePanel;
		prevKid = thePrevKid;
		kidDAO = theKidDAO;
		prevMom = thePrevMom;
		momDAO = theMomDAO;
		momtab = theMomtab;
    	updateMode= theUpdateMode;
    	setTitle("Add");
    	if (updateMode) {
    		setTitle("Update");
    		if (!momtab) populateGui(thePrevKid);
    		else populateGui(thePrevMom);
    	}
    	if(momtab) {
    		genderField.setEditable(false);
    		parentNameField.setEditable(false);
    	}
	}
	private void populateGui(Person thepreviousPerson) {
		idField.setText(thepreviousPerson.getID());
		lastNameField.setText(thepreviousPerson.getLastName());
		firstNameField.setText(thepreviousPerson.getFirstName());
		emailField.setText(thepreviousPerson.getEmail());
		dateOfBirthField.setText(formatter.format(thepreviousPerson.getDateOfBirth()));
		phoneField.setText(thepreviousPerson.getPhoneNum());
		addressField.setText(thepreviousPerson.getAddress());
		if (thepreviousPerson instanceof Kid) {
			genderField.setText(((Kid) thepreviousPerson).getGender());
			parentNameField.setText(thepreviousPerson.getFirstName());
		}
		idField.setEditable(false);
	
	}

	public AddUpdateDialog() {
		
		setBounds(100, 100, 443, 364);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 14, 46, 14);
		contentPanel.add(lblNewLabel);
		
		idField = new JTextField();
		idField.setBounds(100, 8, 312, 20);
		contentPanel.add(idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setBounds(10, 39, 80, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setBounds(10, 67, 80, 14);
		contentPanel.add(lblNewLabel_2);
		
		lastNameField = new JTextField();
		lastNameField.setBounds(100, 33, 312, 20);
		contentPanel.add(lastNameField);
		lastNameField.setColumns(10);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		firstNameField.setBounds(100, 64, 312, 20);
		contentPanel.add(firstNameField);
		
		JLabel lblNewLabel_3 = new JLabel("DateOfBirth");
		lblNewLabel_3.setBounds(10, 98, 80, 14);
		contentPanel.add(lblNewLabel_3);
		
		dateOfBirthField = new JTextField();
		dateOfBirthField.setColumns(10);
		dateOfBirthField.setBounds(100, 95, 129, 20);
		contentPanel.add(dateOfBirthField);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setBounds(250, 98, 55, 14);
		contentPanel.add(lblNewLabel_4);
		
		genderField = new JTextField();
		genderField.setColumns(10);
		genderField.setBounds(315, 95, 97, 20);
		contentPanel.add(genderField);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setBounds(10, 137, 80, 14);
		contentPanel.add(lblNewLabel_5);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(100, 134, 129, 20);
		contentPanel.add(addressField);
		
		JLabel lblNewLabel_6 = new JLabel("Phone");
		lblNewLabel_6.setBounds(250, 137, 55, 14);
		contentPanel.add(lblNewLabel_6);
		
		phoneField = new JTextField();
		phoneField.setBounds(315, 134, 97, 20);
		contentPanel.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setBounds(10, 178, 84, 14);
		contentPanel.add(lblNewLabel_7);
		
		emailField = new JTextField();
		emailField.setBounds(100, 175, 312, 20);
		contentPanel.add(emailField);
		emailField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("ParentName");
		lblNewLabel_8.setBounds(10, 203, 84, 14);
		contentPanel.add(lblNewLabel_8);
		
		parentNameField = new JTextField();
		parentNameField.setBounds(100, 200, 312, 20);
		contentPanel.add(parentNameField);
		parentNameField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (momtab) saveMom();
						else saveKid();
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

	protected void saveMom() {
		String id = idField.getText();
		String last_name = lastNameField.getText();
		String first_name = firstNameField.getText();
		String date_of_birth = dateOfBirthField.getText();
		String email = emailField.getText();
		String address = addressField.getText();
		String phone = phoneField.getText();
		Mom temp = null;
		if(id.length() == 0){
			JOptionPane.showMessageDialog(thisPanel, "Please enter ID");
		}
		else{
		try {
			if(updateMode) {
			
				temp = prevMom;
				temp.setLastName(last_name);
				temp.setFirstName(first_name);
				temp.setDateOfBirth(formatter.parse(date_of_birth));
				temp.setEmail(email);
				temp.setPhoneNum(phone);
				temp.setAddress(address);
			}
			else {
				temp = new Mom(id, last_name, first_name, formatter.parse(date_of_birth), address, email, phone);
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			if (updateMode) {
				momDAO.updateMom(temp);
				JOptionPane.showMessageDialog(thisPanel, "Updated successfully","Updated successfully ", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				momDAO.addMom(temp);
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
	protected void saveKid()  {
		String id = idField.getText();
		String last_name = lastNameField.getText();
		String first_name = firstNameField.getText();
		String date_of_birth = dateOfBirthField.getText();
		String email = emailField.getText();
		String address = addressField.getText();
		String phone = phoneField.getText();
		String parent_name = parentNameField.getText();
		String gender = genderField.getText();
		Kid temp = null;
		if (id.length()==0) {
			JOptionPane.showMessageDialog(thisPanel, "Please enter ID");
		} else {
		try {
			if(updateMode) {
			
				temp = prevKid;
				temp.setLastName(last_name);
				temp.setFirstName(first_name);
				temp.setDateOfBirth(formatter.parse(date_of_birth));
				temp.setEmail(email);
				temp.setPhoneNum(phone);
				temp.setAddress(address);
				temp.setGender(gender);
				temp.setParentName(parent_name);
			}
			else {
				temp = new Kid(id, last_name, first_name, formatter.parse(date_of_birth), address, email, phone, gender, parent_name);
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			if (updateMode) {
				kidDAO.updateKid(temp);
				JOptionPane.showMessageDialog(thisPanel, "Updated successfully","Updated successfully ", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				kidDAO.addKid(temp);
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
}
