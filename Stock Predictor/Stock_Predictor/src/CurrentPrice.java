import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;


public class CurrentPrice {

	
	
	
	public static String funct(String sym) {
		String ans="";
		try {
			String link="https://finance.yahoo.com/quote/";
			String inp=link+sym;//JOptionPane.showInputDialog("Enter the Symbol :");
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
			
			String n=str.substring(str.indexOf("regularMarketPrice\":{\"raw\"") + 26, str.indexOf("regularMarketVolume"));
			//ans=n.substring(n.length()-10, n.length()-4); 
			int index=n.length()-5;
			//System.out.println(n.charAt(index));
			while((n.charAt(index)>='0' && n.charAt(index)<='9')|| n.charAt(index)=='.' || n.charAt(index)==',')
					{
						if(n.charAt(index)!=',')
							ans=n.charAt(index)+ans;
						index--;
					}
			//ans=n.charAt(index)+ans;
			//System.out.println(ans);
			//System.out.println(n.length());
		/*	byte []b=str.getBytes(); 
			JFileChooser fc=new JFileChooser(System.getProperty("user.home") + "\\Desktop");
			
			if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
				File f=fc.getSelectedFile();
				FileOutputStream fos=new FileOutputStream(f);
				fos.write(b);
			}
			*/
			
			is.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}
	
	
	
	
	
	
	
	
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String link="https://finance.yahoo.com/quote/";
			String inp=link+JOptionPane.showInputDialog("Enter the URL :");
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
			String ans="";
			String n=str.substring(str.indexOf("regularMarketPrice\":{\"raw\"") + 26, str.indexOf("regularMarketVolume"));
			//ans=n.substring(n.length()-10, n.length()-4); 
			int index=n.length()-5;
			//System.out.println(n.charAt(index));
			while((n.charAt(index)>='0' && n.charAt(index)<='9')|| n.charAt(index)=='.' || n.charAt(index)==',')
					{
						if(n.charAt(index)!=',')
							ans=n.charAt(index)+ans;
						index--;
					}
			//ans=n.charAt(index)+ans;
			System.out.println(ans);
			//System.out.println(n.length());
		/*	byte []b=str.getBytes(); 
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
		
	}*/

}
