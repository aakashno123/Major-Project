
public class DATA {
	double ARR[]=new double[5000];
	double SUM;
	double MEAN;
	double VARIANCE;
	int LEN;
	void sum()
	{
		SUM=0;
		for(int i=0;i<ARR.length;i++)
			{
				SUM+=ARR[i];
				if (ARR[i]==0)
				{
					LEN=i;
					break;
				}
			}
			
		
	}
	void mean()
	{
		MEAN=SUM/LEN;
	}
	void variance()
	{
		double sd=0;
		for(double i:ARR)
			{
				if (i==0)
					break;
				sd+=(MEAN-i)*(MEAN-i);
			}
		
		VARIANCE=sd/LEN;
	}
	DATA(double[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			ARR[i]=arr[i];
		}
		//LEN=ARR.length;
		sum();
		mean();
		variance();
	}
	
}
