package CloudServer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Utility.DBConnection;
import Utility.PacketSend;

public class Logger extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextField ID_TextField;
	public static JTextField UserAction_TextField;
	public static JTextField Time_TextField;
	public static JTextField Location_TextField;
	public static JTextField CheckSum_TextField;
	public static JTextField Signature_TextField;
	public static JTextField ReceivedTime_TextField;
	public static JTextField Response_TextField;
	public JLabel Time_label;
	protected String CurrentTime;
	
	static Calendar now;
	public static int myPort,dataOwnerPort;
	public static String myAddress,myName,time,startTime,dataOwnerAddress;
	public static DBConnection dbc;
	public static Connection con;
	public static Statement st;
	public static ResultSet rs;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logger frame = new Logger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Logger() 
	{
		call();
		setVisible(true);
		setResizable(false);
		setTitle("LOGGER");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\report_user.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 486);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogger = new JLabel("Logger");
		lblLogger.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\C5_Logo_Symbol.png"));
		lblLogger.setForeground(SystemColor.desktop);
		lblLogger.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblLogger.setBounds(213, 11, 194, 81);
		contentPane.add(lblLogger);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tabbedPane.setBounds(10, 102, 583, 351);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Home Page", new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\report_user.png"), panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Entity Identification");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(35, 87, 120, 36);
		panel.add(lblNewLabel);
		
		JLabel lblLogRecoredGeneration = new JLabel("Log Recored Generation");
		lblLogRecoredGeneration.setForeground(SystemColor.desktop);
		lblLogRecoredGeneration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblLogRecoredGeneration.setBounds(144, 20, 313, 36);
		panel.add(lblLogRecoredGeneration);
		
		JLabel lblUserAction = new JLabel("User Action");
		lblUserAction.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUserAction.setBounds(35, 162, 120, 36);
		panel.add(lblUserAction);
		
		JLabel lblUserLocation = new JLabel("Time To Access:");
		lblUserLocation.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblUserLocation.setBounds(35, 246, 120, 36);
		panel.add(lblUserLocation);
		
		JLabel label = new JLabel("User Location :");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label.setBounds(317, 87, 90, 36);
		panel.add(label);
		
		JLabel lblCheckSum = new JLabel("Check Sum");
		lblCheckSum.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCheckSum.setBounds(317, 162, 90, 36);
		panel.add(lblCheckSum);
		
		JLabel lblSignature = new JLabel("Signature ");
		lblSignature.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSignature.setBounds(317, 246, 90, 36);
		panel.add(lblSignature);
		
		ID_TextField = new JTextField();
		ID_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ID_TextField.setBounds(165, 92, 128, 28);
		panel.add(ID_TextField);
		ID_TextField.setColumns(10);
		
		UserAction_TextField = new JTextField();
		UserAction_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		UserAction_TextField.setColumns(10);
		UserAction_TextField.setBounds(165, 167, 128, 28);
		panel.add(UserAction_TextField);
		
		Time_TextField = new JTextField();
		Time_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Time_TextField.setColumns(10);
		Time_TextField.setBounds(165, 246, 128, 28);
		panel.add(Time_TextField);
		
		Location_TextField = new JTextField();
		Location_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Location_TextField.setColumns(10);
		Location_TextField.setBounds(417, 96, 130, 28);
		panel.add(Location_TextField);
		
		CheckSum_TextField = new JTextField();
		CheckSum_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		CheckSum_TextField.setColumns(10);
		CheckSum_TextField.setBounds(417, 171, 130, 28);
		panel.add(CheckSum_TextField);
		
		Signature_TextField = new JTextField();
		Signature_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Signature_TextField.setColumns(10);
		Signature_TextField.setBounds(417, 250, 130, 28);
		panel.add(Signature_TextField);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Auditing Request", new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\weather_06.png"), panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblPushModeAuditing = new JLabel("Push Mode Auditing");
		lblPushModeAuditing.setForeground(SystemColor.desktop);
		lblPushModeAuditing.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblPushModeAuditing.setBounds(140, 31, 273, 47);
		panel_2.add(lblPushModeAuditing);
		
		JLabel lblRequestReceivedTime = new JLabel("Request Received Time:");
		lblRequestReceivedTime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblRequestReceivedTime.setBounds(140, 125, 155, 29);
		panel_2.add(lblRequestReceivedTime);
		
		ReceivedTime_TextField = new JTextField();
		ReceivedTime_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ReceivedTime_TextField.setColumns(10);
		ReceivedTime_TextField.setBounds(292, 125, 128, 28);
		panel_2.add(ReceivedTime_TextField);
		
		JLabel lblReponseSendingTime = new JLabel("Reponse Sending Time:");
		lblReponseSendingTime.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblReponseSendingTime.setBounds(140, 219, 155, 29);
		panel_2.add(lblReponseSendingTime);
		
		Response_TextField = new JTextField();
		Response_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Response_TextField.setColumns(10);
		Response_TextField.setBounds(292, 219, 128, 28);
		panel_2.add(Response_TextField);
		
		Time_label = new JLabel("");
		Time_label.setForeground(SystemColor.desktop);
		Time_label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		Time_label.setBounds(465, 59, 128, 81);
		contentPane.add(Time_label);
		startTime=getDateTime();
		System.out.println(startTime);
		time();
	}
	public void time()
	 {
	 javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener()
	 {
		   public void actionPerformed(ActionEvent e) 
				 {
   		   		  
                    now = Calendar.getInstance();
                    int currenthours = now.get(Calendar.HOUR_OF_DAY);
                    int currentminute = now.get(Calendar.MINUTE);
                    int currentsecond = now.get(Calendar.SECOND);
                    CurrentTime="" + currenthours + ":" + currentminute + ":" + currentsecond;
                    //TimeField.setText("" + currenthours + ":" + currentminute + ":" + currentsecond);
                   // TimeField.setForeground(new Color(0,100,0));
                    Time_label.setText("" + currenthours + ":" + currentminute + ":" + currentsecond);
                    int tt=findTimedifference(startTime,CurrentTime);
                    if(tt==20)
                    {
                    	if(loggerWorkerThread.value==0)
                    	{
                    		startTime=CurrentTime;
                    	}
                    	else
                    	{
                    		getAddress();
                        	PacketSend ss=new PacketSend(dataOwnerAddress, dataOwnerPort);
                        	ss.sendDataOwner("User Log Recored Received",loggerWorkerThread.dataOwnerName,myName);
                        	startTime=CurrentTime;
                    	}
                    }
                    
    				}
	 		}
			  );
	 t.start(); 
	 }
	
	public void call()
	  {
		 myName=JOptionPane.showInputDialog("Enter the Name");
		 String verification=Verifiy();
		 if(verification.equals("No"))
		 {
			 getloggerAddress();
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Please Register the User Details");
			 System.exit(0);
		 }
	  }
	  private void getloggerAddress() 
	  {
		  try 
			{
				defaultconnection();
				 rs=st.executeQuery("SELECT LoggerAddress,LoggerPort FROM loggerdetails WHERE LoggerName='"+myName+"'");
				 if(rs.next())
				 {
					myAddress=rs.getString(1);
					myPort=rs.getInt(2);
					new LoggerProcess(myPort);
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(contentPane, "Verify the data..!");
				 }
				rs.close();
				st.close();
				con.close();
				}
				 catch (Exception e) 
				{
					e.printStackTrace();
				}
	  }

	private String Verifiy() 
	  {
		  String verify="Yes";
		  try 
			{
				defaultconnection();
				 rs=st.executeQuery("SELECT * FROM loggerdetails WHERE LoggerName='"+myName+"'");
				 if(rs.next())
				 {
					verify="No";
				 }
				 
				rs.close();
				st.close();
				con.close();
				}
				 catch (Exception e) 
				{
					e.printStackTrace();
				}
		  return verify;
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
	public void getAddress()
	{
		try 
		{
			defaultconnection();
			 rs=st.executeQuery("SELECT Address,Port FROM dataowner WHERE UserName='"+loggerWorkerThread.dataOwnerName+"'");
			 if(rs.next())
			 {
				dataOwnerAddress=rs.getString(1);
				dataOwnerPort=rs.getInt(2);
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(contentPane, "Verify the data..!");
			 }
			rs.close();
			st.close();
			con.close();
			}
			 catch (Exception e) 
			{
				e.printStackTrace();
			}
	}
protected static int findTimedifference(String senTime, String recTime) {
		
		int arr1[]=putTimeInArray(senTime);
		int arr2[]=putTimeInArray(recTime);
		int secondsDiff=convertTimeToSeconds(arr2)-convertTimeToSeconds(arr1);
		int time=convertSecondsToTime(secondsDiff);
		return time;
	}
	private static int convertSecondsToTime(int secondsDiff) {
		int seconds=(secondsDiff%60);
		int minutes=(secondsDiff/60)%60;
		int hours=(secondsDiff/3600)%48;
		time=hours+":"+minutes+":"+seconds;
		return seconds;
	}
	private static int convertTimeToSeconds(int[] arr1) {
		int hours=  arr1[1];
		   int minutes=arr1[2];
		   int seconds=arr1[3];
		   seconds=seconds+(minutes*60)+(hours*60*60);
		   return seconds;
	}
	private static int[] putTimeInArray(String time) {
		StringTokenizer s1=new StringTokenizer(time,":");
		int t1[]=new int[4];
		int temp=1;

			while(s1.hasMoreTokens())
			{
				t1[temp]=Integer.parseInt(s1.nextToken());
				temp++;
			}
			
		return t1;
	}
	public static String getDateTime() 
	{
    now = Calendar.getInstance();
    int currenthours = now.get(Calendar.HOUR_OF_DAY);
    int currentminute = now.get(Calendar.MINUTE);
    int currentsecond = now.get(Calendar.SECOND);
    String startTime1="" + currenthours + ":" + currentminute + ":" + currentsecond;
    return startTime1;
  }
}
