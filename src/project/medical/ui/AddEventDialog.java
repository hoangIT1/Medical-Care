package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.EventDAO;
import project.medical.core.Event;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddEventDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField dateField;
	private JTextField descriptionField;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private EventDAO eventDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEventDialog dialog = new AddEventDialog();
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
	public AddEventDialog() throws Exception {
		eventDAO = new EventDAO();
		setTitle("Create new event");
		setType(Type.UTILITY);
		setBounds(100, 100, 418, 334);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Name");
			lblNewLabel.setBounds(33, 27, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			nameField = new JTextField();
			nameField.setBounds(132, 24, 154, 20);
			contentPanel.add(nameField);
			nameField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Date");
			lblNewLabel_1.setBounds(33, 74, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			dateField = new JTextField();
			dateField.setBounds(132, 71, 154, 20);
			contentPanel.add(dateField);
			dateField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Description");
			lblNewLabel_2.setBounds(33, 119, 66, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			descriptionField = new JTextField();
			descriptionField.setAlignmentX(Component.RIGHT_ALIGNMENT);
			descriptionField.setHorizontalAlignment(SwingConstants.LEFT);
			descriptionField.setBounds(30, 144, 344, 87);
			contentPanel.add(descriptionField);
			descriptionField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							saveEvent();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
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

	// Saving event
	public void saveEvent() throws ParseException {
		
		
		try {
			String name = nameField.getText();
			String date_S = dateField.getText();
			String description = descriptionField.getText();
			Date date = formatter.parse(date_S);
			Event temp = new Event(name, date, description);
			eventDAO.addEvent(temp);
			JOptionPane.showMessageDialog(AddEventDialog.this, "Added successfully", "Added successfully ", JOptionPane.INFORMATION_MESSAGE);		
			setVisible(false);
			dispose();	
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(AddEventDialog.this,"Error saving: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

}
