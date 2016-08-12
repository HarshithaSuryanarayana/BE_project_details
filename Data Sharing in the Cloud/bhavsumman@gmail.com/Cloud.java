package CloudServer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Utility.DBConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cloud extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Address_TextField;
	private JTextField Port_TextField;
	public static String address,port;
	public static DBConnection dbc;
	public static Connection con;
	public static Statement st;
	public static ResultSet rs;
	String a="localhost";
	String p="9999";
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					Cloud frame = new Cloud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cloud() 
	{
		setTitle("CLOUD");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\weather-rainbow-only.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 379);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCiaCloudInformation = new JLabel("CIA Cloud Information Accountability");
		lblCiaCloudInformation.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\C5_Logo_Symbol.png"));
		lblCiaCloudInformation.setForeground(SystemColor.desktop);
		lblCiaCloudInformation.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblCiaCloudInformation.setBounds(116, 28, 575, 81);
		contentPane.add(lblCiaCloudInformation);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\11505709-one-computer-server-with-a-cloud-shape-concept-of-remote-data-storage-3d-render (1).jpg"));
		lblNewLabel.setBounds(549, 133, 181, 198);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\11505709-one-computer-server-with-a-cloud-shape-concept-of-remote-data-storage-3d-render (1).jpg"));
		label.setBounds(21, 133, 181, 198);
		contentPane.add(label);
		
		JLabel lblServerName = new JLabel("Server Address:");
		lblServerName.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\id-card.png"));
		lblServerName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblServerName.setBounds(204, 133, 148, 41);
		contentPane.add(lblServerName);
		
		Address_TextField = new JTextField();
		Address_TextField.setHorizontalAlignment(SwingConstants.CENTER);
		Address_TextField.setForeground(Color.BLACK);
		Address_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Address_TextField.setColumns(10);
		Address_TextField.setBackground(Color.WHITE);
		Address_TextField.setBounds(362, 142, 150, 29);
		contentPane.add(Address_TextField);
		Address_TextField.setText(a);
		
		JLabel lblServerPort = new JLabel("Server Port:");
		lblServerPort.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\emailmnb.png"));
		lblServerPort.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblServerPort.setBounds(204, 199, 148, 41);
		contentPane.add(lblServerPort);
		
		JButton On_Button = new JButton("");
		On_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				address=Address_TextField.getText().trim();
				if(address.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Address");
				}
				else
				{
					port=Port_TextField.getText().trim();
					if(port.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please Enter the Port");
					}
					else
					{
						saveinfo();
					}
				}
			}
		});
		On_Button.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\free-for-job.png"));
		On_Button.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		On_Button.setBounds(313, 283, 128, 48);
		contentPane.add(On_Button);
		
		Port_TextField = new JTextField();
		Port_TextField.setHorizontalAlignment(SwingConstants.CENTER);
		Port_TextField.setForeground(Color.BLACK);
		Port_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Port_TextField.setColumns(10);
		Port_TextField.setBackground(Color.WHITE);
		Port_TextField.setBounds(362, 208, 150, 29);
		contentPane.add(Port_TextField);
		Port_TextField.setText(p);
	}

	protected void saveinfo() 
	{
		try 
		{
			String name="admin";
			 defaultconnection();
	 		 int i=st.executeUpdate("UPDATE serverdetails set ServerAddress='"+address+"',ServerPort='"+port+"'WHERE ServerName='"+name+"'");
	 		 if(i==1)
	 		 {	 			 
	 			JOptionPane.showMessageDialog(null,"Updated Successfully ","VERIFIED !",JOptionPane.INFORMATION_MESSAGE);
	 			CloudServer frame = new CloudServer(port);
				frame.setVisible(true);
				dispose();
	 		 }
	 		 else
	 		 {
	 			JOptionPane.showMessageDialog(null,"Update Failed","WARNING !",JOptionPane.WARNING_MESSAGE);
	 		 }
	 		 st.close();
	 		 con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void defaultconnection()
	{
		try
		{
			dbc=new DBConnection();
            con=dbc.getConnection();
	     	st=con.createStatement();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
