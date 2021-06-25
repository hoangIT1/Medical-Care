package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.WeightHeightDAO;
import project.medical.core.WeightHeight;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddIndexDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField DateField;
	private JTextField HeightField;
	private JTextField WeightField;
	private String personID;
	private WeightHeightDAO  whDAO;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddIndexDialog dialog = new AddIndexDialog();
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
	public AddIndexDialog(String thepersonID) throws Exception {
		this();
		this.personID = thepersonID;
		whDAO = new WeightHeightDAO();
	}
	public AddIndexDialog() {
		setTitle("Add New Index");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Date");
			lblNewLabel.setBounds(49, 30, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			DateField = new JTextField();
			DateField.setBounds(153, 27, 157, 20);
			contentPanel.add(DateField);
			DateField.setColumns(10);
		}
		
		HeightField = new JTextField();
		HeightField.setBounds(153, 69, 66, 20);
		contentPanel.add(HeightField);
		HeightField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Height");
		lblNewLabel_1.setBounds(49, 75, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Weight");
		lblNewLabel_2.setBounds(49, 129, 46, 14);
		contentPanel.add(lblNewLabel_2);
		
		WeightField = new JTextField();
		WeightField.setBounds(153, 126, 66, 20);
		contentPanel.add(WeightField);
		WeightField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							saveIndex();
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
	// Save index
	public void saveIndex() throws ParseException {

		
		
		try {
			String weight_S = WeightField.getText();
			String height_S = HeightField.getText();
			String date_S = DateField.getText();
			
			int weight = Integer.parseInt(weight_S);		
			int height = Integer.parseInt(height_S);
			Date date = formatter.parse(date_S);
			WeightHeight temp = new WeightHeight(height, weight, date);
			whDAO.addWeightHeight(temp, personID);
			JOptionPane.showMessageDialog(AddIndexDialog.this, "Added successfully", "Added successfully ", JOptionPane.INFORMATION_MESSAGE);		
			setVisible(false);
			dispose();	
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(AddIndexDialog.this,"Error saving: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
}
