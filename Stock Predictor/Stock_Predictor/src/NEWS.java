import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class NEWS {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			String inp=JOptionPane.showInputDialog("Enter the URL :");
			URL url=new URL(inp);
			URLConnection urlCon=url.openConnection();
			InputStream is=urlCon.getInputStream();
			int i;
			StringBuilder sb=new StringBuilder();
			while((i=is.read())!=-1){
				//System.out.print((char)i);
				sb.append((char)i);
				}
			String str=sb.toString();
			StringBuilder sbn=new StringBuilder();
			
			 Pattern pattern = Pattern.compile("");
		        

		        Matcher matcher = pattern.matcher(str);
		        if(matcher.matches()) {
		            sbn.append(matcher.group(1));
		        }
			
			
			System.out.println(sbn);
			byte []b=sbn.toString().getBytes(); 
			JFileChooser fc=new JFileChooser(System.getProperty("user.home") + "\\Desktop");
			
			if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
				File f=fc.getSelectedFile();
				FileOutputStream fos=new FileOutputStream(f);
				fos.write(b);
				
			}
			
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
