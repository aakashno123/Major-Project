import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ABC {
	
	static{
try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	DATA X,Y,E;
	double B0,B1;
	double SEB0,SEB1;
	static String csvFile,TxtFile;
	StringBuilder points=new StringBuilder();
	/*double XMEAN,YMEAN,COEFFICIENT;
	double[] XARR =new double[4000];
	double[] YARR =new double[4000];
	double mean(double arr[])
	{
		
		return sum(arr)/arr.length;
	}
	double variance(double arr[])
	{
		double MEAN=mean(arr);
		double sd=0;
		for(double i:arr)
			sd+=(MEAN-i)*(MEAN-i);
		return sd/arr.length;
	}
	*/
	/*double coefficient(double Xarr[],double Yarr[])
	{
		double Xmean=mean(Xarr);
		double Ymean=mean(Yarr);
		double a=0,b=0;
		double temp;
		for(int i=0;i<Xarr.length;i++)
		{
			temp=Xarr[i]-Xmean;
			a+=temp*(Yarr[i]-Ymean);
			b+=temp*temp;
		}
		return a/b;
	}*/
	void coefficient()
	{
		double temp,a=0,b=0;
		for(int i=0;i<X.LEN;i++)
		{
			temp=X.ARR[i]-X.MEAN;
			a+=temp*(Y.ARR[i]-Y.MEAN);
			b+=temp*temp;
		}
		B1= a/b;
	}
	
	
	void intercept()
	{
		coefficient();
		B0=Y.MEAN-B1*X.MEAN;
	}
	
	
	void standardDeviation()
	{
		SEB1=E.VARIANCE/(X.VARIANCE*X.LEN);
		SEB0=E.VARIANCE*((X.MEAN*X.MEAN)/(X.VARIANCE*X.LEN)+1/X.LEN);
	}
	
	
	double kam() throws Exception{
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date,date1;
        date1 = myFormat.parse("01-01-2000");
		String xval=GUI.dateGUI.getText();//JOptionPane.showInputDialog(null, "Input date in dd-mm-yyyy format");
		date=myFormat.parse(xval);
        double x=TimeUnit.DAYS.convert(date.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
        return B1*x+B0;
	}
	
	
	
	public static void FUNC()   {
		// TODO Auto-generated method stub
	try{
		
		
		CSVReader obj=new CSVReader();
		double [][]data= GUI.DATAGUI; //obj.read();
		double[] temp=new double[data.length];
		for(int i=0;i<data.length;i++)
		{
			temp[i]=data[i][0];
		}
	
		ABC ob=new ABC();
		ob.X=new DATA(temp);
		for(int i=0;i<data.length;i++)
		{
			temp[i]=data[i][1];
		}
		ob.Y=new DATA(temp);
		ob.intercept();
		for(int i=0;i<ob.X.LEN;i++)
			temp[i]=ob.Y.ARR[i]-ob.B1*ob.X.ARR[i]-ob.B0;
		ob.E=new DATA(temp);
		ob.standardDeviation();
		
		double [] diff=new double[ob.Y.LEN-1];
		double sum=0;
		for(int i=0;i<diff.length;i++){
		diff[i]=ob.Y.ARR[i+1]-ob.Y.ARR[i];
		sum+=diff[i];
	}
		double avgFlu=sum/diff.length;
		System.out.println("avgFlu"+avgFlu);
		
		FileReader fileReader=new FileReader(new File("C:\\Users\\AKASH\\Desktop\\sentdata.txt"));
		BufferedReader br=new BufferedReader(fileReader);
		String line=null;
		StringBuilder sb=new StringBuilder();
		 while((line = br.readLine()) != null) {
                //System.out.println(line);
                sb.append(line);
                //sb.append(System.getProperty("line.separator"));
            }
		br.close();
			double sentiValue=Double.parseDouble(sb.toString());
	//BufferedWriter br=new BufferedWriter(new FileOutputStream(file));
		double yi,xi,yl,xl;
		double xi100,yi100;
		xi=ob.X.ARR[0];
		xl=ob.X.ARR[ob.X.LEN-1];
		yi=ob.B1*xi+ob.B0;
		yl=ob.B1*xl+ob.B0;
		ob.points.append(xi+","+yi+","+xl+","+yl+",");
		
		

		
		
	
			
			System.out.println(ob.X.LEN+" || "+ob.X.SUM+" || "+ob.X.MEAN+" || "+ob.X.VARIANCE);
		System.out.println(ob.Y.LEN+" || "+ob.Y.SUM+" || "+ob.Y.MEAN+" || "+ob.Y.VARIANCE);
		System.out.println(ob.E.LEN+" || "+ob.E.SUM+" || "+ob.E.MEAN+" || "+ob.E.VARIANCE);
		System.out.println(ob.B0+" || "+ob.B1);
		System.out.println(ob.SEB0+" || "+ob.SEB1);
		
	
			//JOptionPane.showMessageDialog(null,ob.kam());
		
		
		DATA X100=new DATA(Arrays.copyOfRange(ob.X.ARR, ob.X.LEN-101, ob.X.LEN-1));
		DATA Y100=new DATA(Arrays.copyOfRange(ob.Y.ARR, ob.Y.LEN-101, ob.Y.LEN-1));
			ob.X=X100;
			ob.Y=Y100;
			ob.intercept();
				File f=new File(System.getProperty("user.home")+"\\Desktop\\intermediate.txt");//fcc.getSelectedFile();//new File("C:\\Users\\AKASH\\Desktop\\abctxt.txt");//
				ABC.TxtFile=f.getAbsolutePath().toString();
				FileOutputStream fos=new FileOutputStream(f);
				xi=ob.X.ARR[0];
				xl=ob.X.ARR[ob.X.LEN-1];
				yi=ob.B1*xi+ob.B0;
				yl=ob.B1*xl+ob.B0;
				ob.points.append(xi+","+yi+","+xl+","+yl);
				//xi100=ob.X.ARR[ob.X.LEN-101];
				//yi100=ob.B1*xi100+ob.B0;
			System.out.println(ob.points);	
				fos.write((ob.points).toString().getBytes());
				
				fos.close();
			
			GUI.predictedValGUI.setText((ob.kam()+sentiValue*avgFlu)+"");
			GUI.curPrice.setText(CurrentPrice.funct(GUI.symbolGUI.getText()));
				//JOptionPane.showMessageDialog(null, ob.kam());
			Process PY=Runtime.getRuntime().exec("cmd.exe  /c start python C:\\Users\\AKASH\\Desktop\\bnew.py "+csvFile+" "+TxtFile);
			
			
		/*ABC obb=new ABC();
		
		obb.XMEAN=
		double []array=new double[10];
		
		for(int i=0;i<10;i++)
			array[i]=i;
		ABC obj=new ABC();
		System.out.println(obj.variance(array));*/
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}



