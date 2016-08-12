package Utility;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import DataOwner.DataOwner;
/**
 * Summary description for JARMake
 *
 */
public class JARMake extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FileName_TextField;
	public static JTextArea Content_TextArea,EncryptedContent_TextArea;
	Vector<Object> DataList=new Vector<Object>();
	public static String name,message,writeContent;
	public static String JarFilePath;
	public static String path;
	
	public JARMake(String n,String m,String p)
	{
		super();
		name=n;
		message=m;
		path=p;
		setResizable(false);
		initializeComponent();
		this.setVisible(true);
	}

	private void initializeComponent()
	{
		contentPane = (JPanel)this.getContentPane();

		contentPane.setLayout(null);
		
		JLabel label = new JLabel("JAR File");
		label.setIcon(new ImageIcon("D:\\IMAGE\\Amount\\gnome_mime_text_x_java.png"));
		label.setForeground(SystemColor.desktop);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		label.setBounds(141, 11, 171, 41);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("File Name:");
		label_1.setIcon(new ImageIcon("D:\\CLOUD INFORMATION ACCOUNTABILITY\\Image\\gnome-mime-application-x-applix-word.png"));
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		label_1.setBounds(103, 79, 96, 29);
		getContentPane().add(label_1);
		
		FileName_TextField = new JTextField();
		FileName_TextField.setHorizontalAlignment(SwingConstants.CENTER);
		FileName_TextField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		FileName_TextField.setColumns(10);
		FileName_TextField.setBounds(209, 79, 138, 29);
		getContentPane().add(FileName_TextField);
		
		FileName_TextField.setText(name);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 217, 178);
		getContentPane().add(scrollPane);
		
		Content_TextArea = new JTextArea();
		Content_TextArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(Content_TextArea);
		Content_TextArea.append(message);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					String value=Content_TextArea.getText().trim();
					String userkey=FileName_TextField.getText();
					EncryptedContent_TextArea.setText(EncryptionDecryption.encrypt(value, userkey));
					writeContent=EncryptedContent_TextArea.getText().trim();
					DataOwner.encContent=writeContent;
					saveFile(path);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
			
		});
		btnEncrypt.setIcon(new ImageIcon("D:\\IMAGE\\Hacking\\system_run.png"));
		btnEncrypt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEncrypt.setBounds(66, 339, 146, 41);
		getContentPane().add(btnEncrypt);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(234, 132, 217, 178);
		getContentPane().add(scrollPane_1);
		
		EncryptedContent_TextArea = new JTextArea();
		EncryptedContent_TextArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane_1.setViewportView(EncryptedContent_TextArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon("D:\\IMAGE\\Exit\\icon-exit (2).gif"));
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnClose.setBounds(259, 339, 146, 41);
		getContentPane().add(btnClose);

		this.setTitle("JARMake");
		this.setLocation(new Point(152, 100));
		this.setSize(new Dimension(471, 421));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	
	private static void saveFile(String file) 
	{
		
		FileOutputStream out; 
		PrintStream p; 
		try 
		{
			out = new FileOutputStream(file+"\\"+name);

			p = new PrintStream(out);

			p.println(writeContent);

			p.close();
			
			jarMake(file+"\\"+name);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private static void jarMake(String f) 
	{
		try
		{

		String jarName=getNameWithoutExtension(name);
		JarFilePath=path+"\\"+jarName+".jar";
		byte[] buffer = new byte[1024];		

	    FileOutputStream fos = new FileOutputStream(JarFilePath);

    	ZipOutputStream zos = new ZipOutputStream(fos);

    	ZipEntry ze= new ZipEntry(f);

    	zos.putNextEntry(ze);

    	FileInputStream in = new FileInputStream(f);
 
    		int len;

    		while ((len = in.read(buffer)) > 0) 
    		{
    			zos.write(buffer, 0, len);
    		} 

    		in.close();
    		zos.closeEntry(); 
    		zos.close();
		}
		catch (Exception n){n.printStackTrace();}
	}

	private static String getNameWithoutExtension(String str)
	{
		
		String fileWitoutExtension;
		int value=str.lastIndexOf(".");
		fileWitoutExtension=str.substring(0,value);
		return fileWitoutExtension;
	}

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		
	}
}
