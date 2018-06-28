import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;

public class CSVReader {

	    double [][] read() {
    	String csvFile="";
    	//System.out.println("CFBFHSFJV");
    	JFileChooser fc=new JFileChooser(System.getProperty("user.home")+"\\Desktop");
    	
    	if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
    		csvFile=fc.getSelectedFile().getAbsolutePath();
    		ABC.csvFile=csvFile;
    		GUI.csvIndicate.setText(csvFile);
    	}
       
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int i=-1;
        //double date;
        Date date;
        String[] d;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        String inputString1 = "01-01-2000";
        //Date date1 = myFormat.parse(inputString1);
        //String inputString2 = "27 04 1997";
        double data[][]=new double[5000][2];
        try {

        	Date date1 = myFormat.parse(inputString1);
        	br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                if(i!=-1)
                {
                //d=country[0].split("-");
                //date=Double.parseDouble(d[0])*1000000+Double.parseDouble(d[1])*10000+Double.parseDouble(d[2]);
                	if(country[0].charAt(4)=='-')
    				{
    					//System.out.print(country[0]);
    					d=country[0].split("-");
    					country[0]=d[2]+"-"+d[1]+"-"+d[0];
    					//System.out.println(" || "+country[0]);
    				}
    				else if(country[0].charAt(4)=='/')
    				{
    					//System.out.print(country[0]);
    					d=country[0].split("/");
    					country[0]=d[2]+"-"+d[1]+"-"+d[0];
    					//System.out.println(" || "+country[0]);
    				}
    				else if(country[0].charAt(2)=='/')
    				{
    					//System.out.print(country[0]);
    					d=country[0].split("/");
    					country[0]=d[0]+"-"+d[1]+"-"+d[2];
    					//System.out.println(" || "+country[0]);
    				}
    				if(country[1].equals("null"))
    					continue;
                    
                date=myFormat.parse(country[0]);
                data[i][0]=TimeUnit.DAYS.convert(date.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
                data[i][1]=Double.parseDouble(country[1]);
                //System.out.println(data[i][0]+" || "+data[i][1]);
                }
                i++;
                //System.out.println("Date ="+country[0] + " open =" + country[1] + "high ="+ country[2]+" low ="+country[3]);

            }
            /*for(int j=0;j<i;j++){
            	System.out.println(data[j][3]);
            }*/
            
            //System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

}