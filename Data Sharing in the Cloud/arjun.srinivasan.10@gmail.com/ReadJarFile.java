package Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class ReadJarFile 
{

	public ReadJarFile(String destination) 
	{
		   File sourceFileOrDir = new File("D:\\CLOUD INFORMATION ACCOUNTABILITY\\JAR");
	       File destDir = new File(destination);
	       if (sourceFileOrDir.isFile()) 
	       {
	           try 
	           {
				copyJarFile(new JarFile(sourceFileOrDir), destDir);
	           } 
	           catch (IOException e)
	           {				
				e.printStackTrace();
			   }
	       } 
	       else if (sourceFileOrDir.isDirectory()) 
	       {
	           File[] files = sourceFileOrDir.listFiles(new FilenameFilter() {
	               public boolean accept(File dir, String name) {
	                   return name.endsWith(".jar");
	               }
	           });
	           for (File f : files) 
	           {
	           try 
	           {
					copyJarFile(new JarFile(f), destDir);
				}
	           catch (IOException e)
	           {
	        	   e.printStackTrace();
				}
	           }
	       }
	   }
	 
	   public static void copyJarFile(JarFile jarFile, File destDir) throws IOException 
	   {
	       String fileName = jarFile.getName();
	       String fileNameLastPart = fileName.substring(fileName.lastIndexOf(File.separator));
	       File destFile = new File(destDir, fileNameLastPart);
	 
	       JarOutputStream jos = new JarOutputStream(new FileOutputStream(destFile));
	       Enumeration<JarEntry> entries = jarFile.entries();
	 
	       while (entries.hasMoreElements()) 
	       {
	           JarEntry entry = entries.nextElement();
	           InputStream is = jarFile.getInputStream(entry);
	           jos.putNextEntry(new JarEntry(entry.getName()));
	           byte[] buffer = new byte[4096];
	           int bytesRead = 0;
	           while ((bytesRead = is.read(buffer)) != -1) {
	               jos.write(buffer, 0, bytesRead);
	           }
	           is.close();
	           jos.flush();
	           jos.closeEntry();
	       }
	       jos.close();
	   }
	}
