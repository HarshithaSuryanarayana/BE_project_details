package CloudUser;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import CloudServer.*;

public class Downloading extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JProgressBar Download_ProgressBar;
	public static JLabel Download_Process_Label;
	String message;
	int sleep;
	Thread r;
	public static String downloadStatus="No";
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Downloading frame = new Downloading();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Downloading()
	{
		
		setResizable(false);
		setTitle("JAR File Downloading");
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\IMAGE\\Search Engine\\Hyperlink-Internet-Search-32.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Download_Process_Label = new JLabel("");
		Download_Process_Label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Download_Process_Label.setBounds(10, 55, 392, 29);
		contentPane.add(Download_Process_Label);
		
		Download_ProgressBar = new JProgressBar();
		Download_ProgressBar.setBounds(7, 89, 422, 26);
		contentPane.add(Download_ProgressBar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\gnome_mime_x_directory_smb_server.png"));
		lblNewLabel.setBounds(379, 6, 53, 57);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\gnome_weather_few_clouds.png"));
		label.setBounds(1, 6, 79, 57);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\download.png"));
		label_1.setBounds(184, 6, 61, 46);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				if(downloadStatus.equals("No"))
				{
					JOptionPane.showMessageDialog(null, "Please Wait");
				}
				else
				{
					try
					{
						//	Runtime.getRuntime().exec("java -jar Logger.jar");
							new Logger();
							dispose();
									
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
			}
			
		});
		btnNewButton.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\logout1.png"));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(376, 118, 53, 41);
		contentPane.add(btnNewButton);
		new DownloadingProcess();
		
	}
}
