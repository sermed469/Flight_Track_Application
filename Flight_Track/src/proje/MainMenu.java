package proje;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class MainMenu {

	private JFrame frame;
	private JTextField textField_Capital;
	private JTextField textField_Capital1;
	private JTextField textField_Capital2;
	public String departure;
	public String arrival;
	public String weekday;
	public Date departuretime;
	public Date arrivaltime;
	public String aircraft;
	public int flightnumber;
	public String airline;
	private JTextField textField_FlightNum;
	private JTextField textField_Num;
	private JTextField textField_DepTime;
	private JTextField textField_ArrTime;
	private JTextField textField_Weekday;
	private JTextField textField_Airline;
	private JTextField textField_Aircraft;
	private JTextField textField_FindNum;
	private JTable table;
	private JLabel lblNewLabel_Time;
	int hour;
	int min;
	int timecontrol = 0;
	private JTextField textField_Delaylandtime;
	
	HashMap<Integer,Flight> flights = new HashMap<Integer,Flight>();
	ArrayList<Capital> cities = new ArrayList<Capital>();
	ArrayList<Destination> routes = new ArrayList<Destination>();
	String [] dprtime = new String[100000];
	String [] landtime = new String[100000];
	private JTextField textField_Delaydprtime;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused", "deprecation" })
	private void initialize() {
		
		cities.add(new Capital("Ankara"));
		cities.add(new Capital("New York"));
		cities.add(new Capital("Tokyo"));
		cities.add(new Capital("Berlin"));
		cities.add(new Capital("Sydney")); 
		
		routes.add(new Destination("Ankara", "Berlin"));
		routes.add(new Destination("Ankara", "New York"));
		routes.add(new Destination("Ankara", "Tokyo"));
		routes.add(new Destination("Ankara", "Sydeny"));
		routes.add(new Destination("New York", "Sydney"));
		routes.add(new Destination("New York", "Tokyo"));
		routes.add(new Destination("New York", "Berlin"));
		routes.add(new Destination("Tokyo", "Berlin"));
		routes.add(new Destination("Tokyo", "Sydney"));
		routes.add(new Destination("Berlin", "Sydney"));
		File fileflight = new File("FlightsBeforeLanding.txt");;
		
		try { 
			
			if(!fileflight.exists()) {
				fileflight.createNewFile();
			}
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
		File file = new File("FlightsAfterLanding.txt");
		
		try {
			
			if(!file.exists()) {
				file.createNewFile();
			}
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 1190, 713);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Capitals");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(130, 13, 80, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Destinations");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(491, 13, 111, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnDeleteCapital = new JButton("Delete");
		btnDeleteCapital.setBounds(44, 88, 97, 25);
		frame.getContentPane().add(btnDeleteCapital);
		
		JButton btnAddCapital = new JButton("Add");
		btnAddCapital.setBounds(44, 142, 97, 25);
		frame.getContentPane().add(btnAddCapital);
		
		JComboBox comboBoxCapitals = new JComboBox();
		
		for( Capital city : cities ) {
			
			comboBoxCapitals.addItem(city.getCapital());
		}
		
		comboBoxCapitals.setBounds(202, 89, 116, 22);
		frame.getContentPane().add(comboBoxCapitals);
		  
		textField_Capital = new JTextField();
		textField_Capital.setBounds(202, 143, 116, 22);
		frame.getContentPane().add(textField_Capital);
		textField_Capital.setColumns(10);
		
		JButton btnDeleteDestinations = new JButton("Delete");
		btnDeleteDestinations.setBounds(416, 88, 97, 25);
		frame.getContentPane().add(btnDeleteDestinations);
		
		JButton btnAddDestinations = new JButton("Add");
		btnAddDestinations.setBounds(416, 177, 97, 25);
		frame.getContentPane().add(btnAddDestinations);
		
		JComboBox comboBoxDestinations = new JComboBox();
		comboBoxDestinations.setBounds(567, 89, 150, 22);
		frame.getContentPane().add(comboBoxDestinations);
		
		btnDeleteDestinations.addActionListener(new ActionListener() {//Deleting destinations
			public void actionPerformed(ActionEvent e) {
				
				routes.remove(comboBoxDestinations.getSelectedIndex());
				comboBoxDestinations.removeItem(comboBoxDestinations.getSelectedItem());
				System.out.println(routes.size());
				for ( Destination d : routes ) {
					
					System.out.println(d.getCapital1()+d.getCapital2());
				}
			}
		});
		
		JButton btnDeleteFlight = new JButton("Delete Flight");
		btnDeleteFlight.setBounds(44, 578, 111, 25);
		frame.getContentPane().add(btnDeleteFlight);
		
		JButton btnAddFlight = new JButton("Add Flight");
		btnAddFlight.setBounds(875, 540, 111, 25);
		frame.getContentPane().add(btnAddFlight);
		
		JButton btnFindFlight = new JButton("Find Flight");
		btnFindFlight.setBounds(875, 592, 111, 25);
		frame.getContentPane().add(btnFindFlight);
		for( Destination dest : routes ) {
			comboBoxDestinations.addItem(dest.getCapital1() + "-" + dest.getCapital2());
			
		}
		
		textField_Capital1 = new JTextField();
		textField_Capital1.setBounds(573, 153, 116, 22);
		frame.getContentPane().add(textField_Capital1);
		textField_Capital1.setColumns(10);
		
		textField_Capital2 = new JTextField();
		textField_Capital2.setBounds(573, 207, 116, 22);
		frame.getContentPane().add(textField_Capital2);
		textField_Capital2.setColumns(10);
		
		btnAddDestinations.addActionListener(new ActionListener() {//Adding destinations
			public void actionPerformed(ActionEvent arg0) {
						
				if((textField_Capital1.getText().compareTo("") != 0) && (textField_Capital2.getText().compareTo("") != 0)) {

					comboBoxDestinations.addItem(textField_Capital1.getText() + "-" + textField_Capital2.getText());
					routes.add(new Destination(textField_Capital1.getText(),textField_Capital2.getText()));
					textField_Capital1.setText(null);
					textField_Capital2.setText(null);
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}
						
			}
		});
		
		textField_FlightNum = new JTextField();
		textField_FlightNum.setBounds(190, 604, 116, 22);
		frame.getContentPane().add(textField_FlightNum);
		textField_FlightNum.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Flight number");
		lblNewLabel_2.setBounds(897, 57, 80, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Depurture");
		lblNewLabel_3.setBounds(897, 106, 80, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Arrival");
		lblNewLabel_4.setBounds(897, 160, 56, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Departure time");
		lblNewLabel_5.setBounds(897, 213, 97, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Arrival time");
		lblNewLabel_6.setBounds(897, 273, 97, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Weekdays");
		lblNewLabel_7.setBounds(897, 335, 67, 16);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Airline");
		lblNewLabel_8.setBounds(897, 403, 56, 16);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Aircraft");
		lblNewLabel_9.setBounds(897, 471, 56, 16);
		frame.getContentPane().add(lblNewLabel_9);
		
		textField_Num = new JTextField();
		textField_Num.setBounds(1018, 54, 116, 22);
		frame.getContentPane().add(textField_Num);
		textField_Num.setColumns(10);
		
		JComboBox comboBox_Departure = new JComboBox();
		comboBox_Departure.setBounds(1018, 103, 116, 22);
		frame.getContentPane().add(comboBox_Departure);
		
		for( Capital city : cities ) {
			
			comboBox_Departure.addItem(city.getCapital());
		}
		
		JComboBox comboBox_Arrival = new JComboBox();
		comboBox_Arrival.setBounds(1018, 157, 116, 22);
		frame.getContentPane().add(comboBox_Arrival);
		
		for( Capital city : cities ) {
			
			comboBox_Arrival.addItem(city.getCapital());
		}
		
		textField_DepTime = new JTextField();
		textField_DepTime.setBounds(1018, 210, 116, 22);
		frame.getContentPane().add(textField_DepTime);
		textField_DepTime.setColumns(10);
		
		textField_ArrTime = new JTextField();
		textField_ArrTime.setBounds(1018, 270, 116, 22);
		frame.getContentPane().add(textField_ArrTime);
		textField_ArrTime.setColumns(10);
		
		textField_Weekday = new JTextField();
		textField_Weekday.setBounds(1018, 332, 116, 22);
		frame.getContentPane().add(textField_Weekday);
		textField_Weekday.setColumns(10);
		
		textField_Airline = new JTextField();
		textField_Airline.setBounds(1018, 400, 116, 22);
		frame.getContentPane().add(textField_Airline);
		textField_Airline.setColumns(10);
		
		textField_Aircraft = new JTextField();
		textField_Aircraft.setBounds(1018, 471, 116, 22);
		frame.getContentPane().add(textField_Aircraft);
		textField_Aircraft.setColumns(10);
		
		textField_FindNum = new JTextField();
		textField_FindNum.setBounds(1018, 593, 116, 22);
		frame.getContentPane().add(textField_FindNum);
		textField_FindNum.setColumns(10);

		btnFindFlight.addActionListener(new ActionListener() {//Finding flight
			public void actionPerformed(ActionEvent e) {
				
				if(!textField_FindNum.getText().toString().trim().equals("")) {

					textField_Num.setText(textField_FindNum.getText());
					comboBox_Departure.setSelectedItem(flights.get(Integer.parseInt(textField_Num.getText())).getDeparture());
					comboBox_Arrival.setSelectedItem(flights.get(Integer.parseInt(textField_Num.getText())).getArrival());
					textField_DepTime.setText(flights.get(Integer.parseInt(textField_Num.getText())).getDeparturetime());
					textField_ArrTime.setText(flights.get(Integer.parseInt(textField_Num.getText())).getArrivaltime());
					textField_Weekday.setText(flights.get(Integer.parseInt(textField_Num.getText())).getWeekday());
					textField_Airline.setText(flights.get(Integer.parseInt(textField_Num.getText())).getAirline());
					textField_Aircraft.setText(flights.get(Integer.parseInt(textField_Num.getText())).getAircraft());
					textField_FindNum.setText(null);

				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		}); 

		table = new JTable();
		Object[] columns = {"Flight Number", "Departure", "Arrival", "Departure Time", "Arrival Time", "Weekday", "Airline", "Airraft", "FlightS tatus"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(Color.WHITE);
		table.setBackground(Color.CYAN);
		table.setSelectionBackground(Color.RED);
		table.setGridColor(Color.BLACK);
		table.setSelectionForeground(Color.WHITE);
		table.setFont(new Font("Tohoma", Font.PLAIN, 13));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		JScrollPane pane = new JScrollPane(table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(50, 300, 746, 265);
		frame.getContentPane().add(pane);

		Object[] row = new Object[9];
		//frame.getContentPane().add(table);
	
		JButton btnLanding = new JButton("Landing");
		btnLanding.addActionListener(new ActionListener() {//Giving permission for landing
			public void actionPerformed(ActionEvent e) {
				
				if ((textField_FlightNum.getText().compareTo("") != 0) && (flights.get(Integer.parseInt(textField_FlightNum.getText())).getFlightstatus().compareTo("Wait") == 0)) {
					
					flights.get(Integer.parseInt(textField_FlightNum.getText())).setFlightstatus("Landing");
					textField_FlightNum.setText(null);
					
					for ( Flight f1 : flights.values() ) {
						int i = 0;
						model.removeRow(i);
						i++;
					}
					
					for(Flight f1 : flights.values()) {
						
						row[0] = f1.getFlightnumber();
						row[1] = f1.getDeparture();
						row[2] = f1.getArrival();
						row[3] = f1.getDeparturetime();
						row[4] = f1.getArrivaltime();
						row[5] = f1.getWeekday();
						row[6] = f1.getAirline();
						row[7] = f1.getAircraft();
						row[8] = f1.getFlightstatus();
						model.addRow(row);
					}
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}
			
			}
		});
		btnLanding.setBounds(356, 628, 97, 25);
		frame.getContentPane().add(btnLanding);
		
		JButton btnDelay = new JButton("Delay Flight");
		btnDelay.setBounds(504, 603, 111, 25);
		frame.getContentPane().add(btnDelay);
		
		JButton btnCancelFlight = new JButton("Cancel Flight");
		btnCancelFlight.addActionListener(new ActionListener() {//Canceling flight
			public void actionPerformed(ActionEvent e) {
				
				if ((textField_FlightNum.getText().compareTo("") != 0) && (flights.get(Integer.parseInt(textField_FlightNum.getText())).getFlightstatus().compareTo("did not started") == 0)) {
					
					flights.get(Integer.parseInt(textField_FlightNum.getText())).setFlightstatus("Canceled");
					textField_FlightNum.setText(null);
					
					for ( Flight f1 : flights.values() ) {
						int i = 0;
						model.removeRow(i);
						i++;
					}
					
					for(Flight f1 : flights.values()) {
						
						row[0] = f1.getFlightnumber();
						row[1] = f1.getDeparture();
						row[2] = f1.getArrival();
						row[3] = f1.getDeparturetime();
						row[4] = f1.getArrivaltime();
						row[5] = f1.getWeekday();
						row[6] = f1.getAirline();
						row[7] = f1.getAircraft();
						row[8] = f1.getFlightstatus();
						model.addRow(row);
					}
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}
			
				
			}
		});
		btnCancelFlight.setBounds(44, 628, 109, 25);
		frame.getContentPane().add(btnCancelFlight);
		
		JButton btnUpdateFlight = new JButton("Update Flight");
		btnUpdateFlight.setBounds(1018, 540, 116, 25);
		btnUpdateFlight.addActionListener(new ActionListener() {//Updating flight
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ((comboBox_Departure.getSelectedItem().toString() != comboBox_Arrival.getSelectedItem().toString()) && (!textField_Num.getText().toString().trim().equals(""))) {
					
					for ( Flight f1 : flights.values() ) {
						int i = 0;
						model.removeRow(i);
						i++;
					}
					
					Flight f = new Flight(Integer.parseInt(textField_Num.getText()),comboBox_Departure.getSelectedItem().toString(),comboBox_Arrival.getSelectedItem().toString(),textField_DepTime.getText(),textField_ArrTime.getText(),textField_Weekday.getText(),textField_Airline.getText(),textField_Aircraft.getText());
					flights.put(f.getFlightnumber(), f);
					
					for(Flight f1 : flights.values()) {
						
						row[0] = f1.getFlightnumber();
						row[1] = f1.getDeparture();
						row[2] = f1.getArrival();
						row[3] = f1.getDeparturetime();
						row[4] = f1.getArrivaltime();
						row[5] = f1.getWeekday();
						row[6] = f1.getAirline();
						row[7] = f1.getAircraft();
						row[8] = f1.getFlightstatus();
						model.addRow(row);
					}
					
					textField_Num.setText(null);
					comboBox_Departure.setSelectedItem(null);
					comboBox_Arrival.setSelectedItem(null);
					textField_DepTime.setText(null);
					textField_ArrTime.setText(null);
					textField_Weekday.setText(null);
					textField_Airline.setText(null);
					textField_Aircraft.setText(null);
					
					PrintWriter pw;	
					try {
						pw = new PrintWriter(fileflight);
						for ( Flight f2 : flights.values() ) {							
							pw.println(f2.getFlightnumber()+"   "+f2.getDeparture()+"   "+f2.getArrival()+"   "+f2.getDeparturetime()+"   "+f2.getArrivaltime()+"   "+f2.getWeekday()+"   "+f2.getAirline()+"   "+f2.getAircraft());	
						}
						pw.close();
					} catch (FileNotFoundException a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
					
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		frame.getContentPane().add(btnUpdateFlight);
		
		lblNewLabel_Time = new JLabel("      00.00");
		lblNewLabel_Time.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_Time.setBounds(147, 212, 141, 58);
		frame.getContentPane().add(lblNewLabel_Time);
		
		JButton btnPause = new JButton("PAUSE");
		btnPause.setBounds(44, 245, 97, 25);
		frame.getContentPane().add(btnPause);
		
		JButton btnResume = new JButton("RESUME");
		btnResume.setBounds(293, 211, 97, 25);
		frame.getContentPane().add(btnResume);
	
		JButton btnStop = new JButton("STOP");
		btnStop.setBounds(293, 249, 97, 25);
		frame.getContentPane().add(btnStop);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {//Starting time
			public void actionPerformed(ActionEvent e) {
				
				Thread systime = new Thread()
				{
					public void run() {	
						try {
							for ( hour = 0; hour < 24; hour++ ) {
								for ( min = 0; min < 60; min++ ) {
									
									if ((hour / 10 == 0) && (min / 10 == 0)) {
										
										lblNewLabel_Time.setText("       0" + hour + ".0" + min);
									}
									else if((hour / 10 == 0) && (min / 10 != 0)) {
										
										lblNewLabel_Time.setText("       0" + hour + "." + min);
									}
									else if((hour / 10 != 0) && (min / 10 == 0)){
										
										lblNewLabel_Time.setText("       " + hour + ".0" + min);
									}
									else {
										
										lblNewLabel_Time.setText("       " + hour + "." + min);
									}
									
									sleep(1000);
									
									for ( Flight f : flights.values() ) {
										//Controling whether any flight's arrival time is equals to system time
										if ((lblNewLabel_Time.getText().compareTo("       "+f.getArrivaltime()) == 0) && (f.getFlightstatus().compareTo("Landing") == 0)) {
		
											f.setFlightstatus("Completed");
											for ( Flight f1 : flights.values() ) {
												int i = 0;
												model.removeRow(i);
												i++;
											}
											for(Flight f1 : flights.values()) {//Updating Table
												
												row[0] = f1.getFlightnumber();
												row[1] = f1.getDeparture();
												row[2] = f1.getArrival();
												row[3] = f1.getDeparturetime();
												row[4] = f1.getArrivaltime();
												row[5] = f1.getWeekday();
												row[6] = f1.getAirline();
												row[7] = f1.getAircraft();
												row[8] = f1.getFlightstatus();
												model.addRow(row);
											}
											
											PrintWriter pw;	
											try {
												pw = new PrintWriter(file);
												for ( Flight f2 : flights.values() ) {
													int a = Integer.parseInt(f2.getArrivaltime().replace(".", ""));
													int b = Integer.parseInt(landtime[f2.getFlightnumber()].replace(".", ""));
													int delay = (((a / 100)*60) + (a%100)) - (((b/100)*60) + (b%100));
													pw.println(dprtime[f2.getFlightnumber()]+"    "+landtime[f2.getFlightnumber()]+"   "+f2.getDeparturetime()+"   "+f2.getArrivaltime()+"   "+delay+" minute   "+f2.getWeekday()+"   "+f2.getFlightnumber()+"   "+f2.getAirline()+"   "+f2.getDeparture()+"   "+f2.getArrival());	
												}
												pw.close();
											} catch (FileNotFoundException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}
										//Controling whether any flight's departure time is equals to system time
										if ((lblNewLabel_Time.getText().compareTo("       "+f.getDeparturetime()) == 0 ) && (f.getFlightstatus().compareTo("did not started") == 0)) {
											
											textField_FlightNum.setText(String.valueOf(f.getFlightnumber()));
											f.setFlightstatus("Flight Start");
											for ( Flight f1 : flights.values() ) {
												int i = 0;
												model.removeRow(i);
												i++;
											}
											for(Flight f1 : flights.values()) {//Updating Table
												
												row[0] = f1.getFlightnumber();
												row[1] = f1.getDeparture();
												row[2] = f1.getArrival();
												row[3] = f1.getDeparturetime();
												row[4] = f1.getArrivaltime();
												row[5] = f1.getWeekday();
												row[6] = f1.getAirline();
												row[7] = f1.getAircraft();
												row[8] = f1.getFlightstatus();
												model.addRow(row);
											}
										}
									}
								}
							}
						} 
						catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				
				systime.start();
				
				btnPause.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						systime.suspend();
						btnStart.setEnabled(false);
					}
				});
				
				btnResume.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						systime.resume();
					}
				});
				
				btnStop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						systime.stop();
						lblNewLabel_Time.setText("      00.00");
						btnStart.setEnabled(true);
						for ( Flight f1 : flights.values() ) {
							int i = 0;
							model.removeRow(i);
							i++;
						}
						flights.clear();
					}
				});
				
			}
		});
		btnStart.setBounds(44, 206, 97, 25);
		frame.getContentPane().add(btnStart);
		
		JLabel lblNewLabel_10 = new JLabel("Flight");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(979, 15, 111, 31);
		frame.getContentPane().add(lblNewLabel_10);
		
		textField_Delaylandtime = new JTextField();
		textField_Delaylandtime.setBounds(638, 629, 116, 22);
		frame.getContentPane().add(textField_Delaylandtime);
		textField_Delaylandtime.setColumns(10);
		
		JButton btnWait = new JButton("Wait");
		btnWait.addActionListener(new ActionListener() {//Waiting flight
			public void actionPerformed(ActionEvent e) {
				
				if ((textField_FlightNum.getText().compareTo("") != 0) && (flights.get(Integer.parseInt(textField_FlightNum.getText())).getFlightstatus().compareTo("Flight Start") == 0)) {
					
					flights.get(Integer.parseInt(textField_FlightNum.getText())).setFlightstatus("Wait");
					
					for ( Flight f1 : flights.values() ) {
						int i = 0;
						model.removeRow(i);
						i++;
					}
					
					for(Flight f1 : flights.values()) {//Updating Table
						
						row[0] = f1.getFlightnumber();
						row[1] = f1.getDeparture();
						row[2] = f1.getArrival();
						row[3] = f1.getDeparturetime();
						row[4] = f1.getArrivaltime();
						row[5] = f1.getWeekday();
						row[6] = f1.getAirline();
						row[7] = f1.getAircraft();
						row[8] = f1.getFlightstatus();
						model.addRow(row);
					}
					
					JOptionPane.showMessageDialog(null, "The plane is ready to land.Give permission for landing ");
				}
				else {
					
					JOptionPane.showMessageDialog(null, "error");
				}
			}
		});
		btnWait.setBounds(356, 578, 97, 25);
		frame.getContentPane().add(btnWait);
		
		textField_Delaydprtime = new JTextField();
		textField_Delaydprtime.setBounds(638, 579, 116, 22);
		frame.getContentPane().add(textField_Delaydprtime);
		textField_Delaydprtime.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("     Flight Number");
		lblNewLabel_11.setBounds(192, 582, 111, 16);
		frame.getContentPane().add(lblNewLabel_11);
		
		btnDelay.addActionListener(new ActionListener() {//Delaying flight
			public void actionPerformed(ActionEvent e) {
				
				if((textField_Delaydprtime.getText().compareTo("") != 0) && (textField_FlightNum.getText().compareTo("") != 0) && (flights.get(Integer.parseInt(textField_FlightNum.getText())).getFlightstatus().compareTo("Completed") != 0)) {
					
					flights.get(Integer.parseInt(textField_FlightNum.getText())).setDeparturetime(textField_Delaydprtime.getText());
				}
				if((textField_Delaylandtime.getText().compareTo("") != 0) && (textField_FlightNum.getText().compareTo("") != 0) && (flights.get(Integer.parseInt(textField_FlightNum.getText())).getFlightstatus().compareTo("Completed") != 0)) {
					
					flights.get(Integer.parseInt(textField_FlightNum.getText())).setArrivaltime(textField_Delaylandtime.getText());
				}
				
				for ( Flight f1 : flights.values() ) {
					int i = 0;
					model.removeRow(i);
					i++;
				}
				
				for(Flight f1 : flights.values()) {//Updating Table
					
					row[0] = f1.getFlightnumber();
					row[1] = f1.getDeparture();
					row[2] = f1.getArrival();
					row[3] = f1.getDeparturetime();
					row[4] = f1.getArrivaltime();
					row[5] = f1.getWeekday();
					row[6] = f1.getAirline();
					row[7] = f1.getAircraft();
					row[8] = f1.getFlightstatus();
					model.addRow(row);
				}
			}
		});
		
		
		btnAddFlight.addActionListener(new ActionListener() {//Adding Flight
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if ((comboBox_Departure.getSelectedItem().toString() != comboBox_Arrival.getSelectedItem().toString()) && (!textField_Num.getText().toString().trim().equals(""))) {
					
					for ( Flight f1 : flights.values() ) {
						int i = 0;
						model.removeRow(i);
						i++;
					}
					//Adding new flight to hashmap
					Flight f = new Flight(Integer.parseInt(textField_Num.getText()),comboBox_Departure.getSelectedItem().toString(),comboBox_Arrival.getSelectedItem().toString(),textField_DepTime.getText(),textField_ArrTime.getText(),textField_Weekday.getText(),textField_Airline.getText(),textField_Aircraft.getText());
					flights.put(f.getFlightnumber(), f);
					dprtime[f.getFlightnumber()] = f.getDeparturetime();
					landtime[f.getFlightnumber()] = f.getArrivaltime();
					table.removeAll();
					for(Flight f1 : flights.values()) {//Updating Table
						
						row[0] = f1.getFlightnumber();
						row[1] = f1.getDeparture();
						row[2] = f1.getArrival();
						row[3] = f1.getDeparturetime();
						row[4] = f1.getArrivaltime();
						row[5] = f1.getWeekday();
						row[6] = f1.getAirline();
						row[7] = f1.getAircraft();
						row[8] = f1.getFlightstatus();
						model.addRow(row);
					}
					textField_Num.setText(null);
					comboBox_Departure.setSelectedItem(null);
					comboBox_Arrival.setSelectedItem(null);
					textField_DepTime.setText(null);
					textField_ArrTime.setText(null);
					textField_Weekday.setText(null);
					textField_Airline.setText(null);
					textField_Aircraft.setText(null);
					
					PrintWriter pw;	
					try {//Updating file 
						pw = new PrintWriter(fileflight);
						for ( Flight f2 : flights.values() ) {							
							pw.println(f2.getFlightnumber()+"   "+f2.getDeparture()+"   "+f2.getArrival()+"   "+f2.getDeparturetime()+"   "+f2.getArrivaltime()+"   "+f2.getWeekday()+"   "+f2.getAirline()+"   "+f2.getAircraft());	
						}
						pw.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}
			
			}
		});
		
		btnAddCapital.addActionListener(new ActionListener() {//Adding Capital
			public void actionPerformed(ActionEvent e) {
				
				if(textField_Capital.getText().compareTo("") != 0) {
					
					comboBoxCapitals.addItem(textField_Capital.getText());
					comboBox_Departure.addItem(textField_Capital.getText());
					comboBox_Arrival.addItem(textField_Capital.getText());
					cities.add(new Capital(textField_Capital.getText()));
					textField_Capital.setText(null);
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}	
			}		
		});
	     
		btnDeleteCapital.addActionListener(new ActionListener() {//Deleting Capital
			public void actionPerformed(ActionEvent arg0) {
				
				int i;
				for( i = 0; i < cities.size(); i++ ) {//Removing capital from cities Arraylist 
					if(cities.get(i).getCapital().compareTo(comboBoxCapitals.getSelectedItem().toString()) == 0 ) {
						cities.remove(i);
					}
				}
				
	
				for( i = 0; i < routes.size(); i++ ) {//Removing routes which contain deleting capital from routes Arraylist
					
					if((comboBoxCapitals.getSelectedItem().toString().compareTo(routes.get(i).getCapital1()) == 0) || (comboBoxCapitals.getSelectedItem().toString().compareTo(routes.get(i).getCapital2()) == 0)) {
						routes.remove(i);
						i--;
					}
				}
				
				comboBoxDestinations.removeAllItems();
				
				for( Destination dest : routes ) {
					
					comboBoxDestinations.addItem(dest.getCapital1() + "-" + dest.getCapital2());
				}
				
				comboBoxCapitals.removeItem(comboBoxCapitals.getSelectedItem());
				comboBox_Departure.removeAllItems();
				comboBox_Arrival.removeAllItems();
				for(Capital c : cities) {
					
					comboBox_Departure.addItem(c.getCapital());
					comboBox_Arrival.addItem(c.getCapital());
				}
			}
		});
		
		btnDeleteFlight.addActionListener(new ActionListener() {//Deleting Flight
			public void actionPerformed(ActionEvent arg0) {
				
				int kontrol = 0;
				for(Flight f : flights.values()) {
					
					if( f.getFlightnumber() == Integer.parseInt(textField_FlightNum.getText()) ) {
						
						kontrol = 1;
					}
				}
				
				if ( (!textField_FlightNum.getText().toString().trim().equals("")) && (kontrol == 1) ) {//Updating Table
					
					for ( Flight f1 : flights.values() ) {
						int i = 0;
						model.removeRow(i);
						i++;
					}
					
					flights.remove(Integer.parseInt(textField_FlightNum.getText()));
					textField_FlightNum.setText(null);
					
					for(Flight f1 : flights.values()) {
						
						row[0] = f1.getFlightnumber();
						row[1] = f1.getDeparture();
						row[2] = f1.getArrival();
						row[3] = f1.getDeparturetime();
						row[4] = f1.getArrivaltime();
						row[5] = f1.getWeekday();
						row[6] = f1.getAirline();
						row[7] = f1.getAircraft();
						row[8] = f1.getFlightstatus();
						model.addRow(row);
					}
					
					PrintWriter pw;	
					try {//Updating file
						pw = new PrintWriter(fileflight);

						for ( Flight f2 : flights.values() ) {							
							pw.println(f2.getFlightnumber()+"   "+f2.getDeparture()+"   "+f2.getArrival()+"   "+f2.getDeparturetime()+"   "+f2.getArrivaltime()+"   "+f2.getWeekday()+"   "+f2.getAirline()+"   "+f2.getAircraft());	
						}
						pw.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		
	}
}
