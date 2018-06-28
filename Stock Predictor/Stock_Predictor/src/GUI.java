import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1644903275869182803L;
	
	
	static{
		try {
					
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	
	
	
	private JPanel contentPane;
	public static JTextField symbolGUI;
	public static JTextField dateGUI;
	public static JTextArea textArea;
	public static JLabel curPrice;
	public static JButton btnOpenCsvFile;
	public static JLabel predictedValGUI;
	public static double [][]DATAGUI;
	public static JLabel csvIndicate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\AKASH\\Desktop\\icon.png"));
		setResizable(false);
		setTitle("Stock Predictor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnOpenCsvFile = new JButton("Open CSV File");
		btnOpenCsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CSVReader obj=new CSVReader();
				DATAGUI=obj.read();
			}
		});
		btnOpenCsvFile.setBounds(105, 42, 115, 25);
		contentPane.add(btnOpenCsvFile);
		
		JLabel lblEnetrTheSymbol = new JLabel("Enetr the Symbol :");
		lblEnetrTheSymbol.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblEnetrTheSymbol.setBounds(10, 145, 125, 18);
		contentPane.add(lblEnetrTheSymbol);
		
		symbolGUI = new JTextField();
		symbolGUI.setToolTipText("Symbol of the company.");
		symbolGUI.setBounds(219, 145, 86, 20);
		contentPane.add(symbolGUI);
		symbolGUI.setColumns(10);
		
		JLabel lblEnetrThe = new JLabel("Enetr the date to be predicted :");
		lblEnetrThe.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblEnetrThe.setBounds(10, 197, 210, 14);
		contentPane.add(lblEnetrThe);
		
		dateGUI = new JTextField();
		dateGUI.setToolTipText("DD-MM-YYYY");
		dateGUI.setBounds(219, 195, 86, 20);
		contentPane.add(dateGUI);
		
		dateGUI.setColumns(10);
		
		JLabel lblThePredictedValue = new JLabel("The predicted value is :");
		lblThePredictedValue.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblThePredictedValue.setBounds(10, 249, 159, 14);
		contentPane.add(lblThePredictedValue);
		
		predictedValGUI = new JLabel("");
		predictedValGUI.setBounds(170, 249, 125, 23);
		contentPane.add(predictedValGUI);
		
		JButton btnCalculate = new JButton("CALCULATE");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Process PY=Runtime.getRuntime().exec("cmd.exe  /c start python C:\\Users\\AKASH\\Desktop\\news.py");
					//Thread.sleep(15000);
					FileReader fileReader=new FileReader(new File("C:\\Users\\AKASH\\Desktop\\sentiment.txt"));
					BufferedReader br=new BufferedReader(fileReader);
					String line=null;
					StringBuilder sb=new StringBuilder();
					 while((line = br.readLine()) != null) {
			                //System.out.println(line);
			                sb.append(line);
			                sb.append(System.getProperty("line.separator"));
			            }
					br.close();
					textArea.setText(sb.toString());
					
					ABC.FUNC();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnCalculate.setBounds(115, 359, 99, 25);
		contentPane.add(btnCalculate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(330, 13, 652, 552);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblCurrentPriceIs = new JLabel("Current Price is :");
		lblCurrentPriceIs.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblCurrentPriceIs.setBounds(10, 299, 112, 18);
		contentPane.add(lblCurrentPriceIs);
		
		curPrice = new JLabel("");
		curPrice.setBounds(170, 299, 86, 16);
		contentPane.add(curPrice);
		
		csvIndicate = new JLabel("");
		csvIndicate.setFont(new Font("Georgia", Font.PLAIN, 15));
		csvIndicate.setBounds(12, 93, 293, 16);
		contentPane.add(csvIndicate);
	}
}
