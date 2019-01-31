package com.google.firebase.firebase_admin;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class LiveMonitor {

	private JFrame frame;
    private static final String DATABASE_URL = "https://agroiot-2a4a9.firebaseio.com";
    private static DatabaseReference database;
    XYSeriesCollection tempset = new XYSeriesCollection();
    XYSeries temp_series= new XYSeries("Temperature");
    XYSeriesCollection humset = new XYSeriesCollection();
    XYSeries hum_series = new XYSeries("Humidity");
    XYSeriesCollection luxset = new XYSeriesCollection();
    XYSeries lux_series = new XYSeries("Light Intensity");
	JPanel panel = new JPanel();
    JFreeChart tempchart;
    ChartPanel tempcp;
    JFreeChart humchart;
    ChartPanel humcp;
    JFreeChart luxchart;
    ChartPanel luxcp;
    private JLabel threeLb = new JLabel("•");
    private JLabel twoLb = new JLabel("•");
    private JLabel oneLb = new JLabel("•");
    JToggleButton tglTwo = new JToggleButton("GROW LIGHT");
    JToggleButton tglOne = new JToggleButton("MOTOR");
    JToggleButton tglThree = new JToggleButton("HUM-CONTROLLER");
    
    public  void startListeners() {
    		database.child("ct_test/actuator").addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String key_recv = dataSnapshot.getKey();
                Object i = dataSnapshot.getValue();
                if(key_recv.equals(new String("one"))) {
                	if((i.toString()).equals(new String("1"))) {
                		tglOne.setSelected(true);
                	}
                	else {
                		tglOne.setSelected(false);
                	}
                }
                if(key_recv.equals(new String("two"))) {
                	if((i.toString()).equals(new String("1"))) {
                		tglTwo.setSelected(true);
                	}
                	else {
                		tglTwo.setSelected(false);
                	}
                }
                if(key_recv.equals(new String("three"))) {
                	if((i.toString()).equals(new String("1"))) {
                		tglThree.setSelected(true);
                	}
                	else {
                		tglThree.setSelected(false);
                	}
                }
            }

			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
    		});
        	database.child("ct_test/sensors").addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
				System.out.println(dataSnapshot);
                SensorData recv_data = dataSnapshot.getValue(SensorData.class);
				temp_series.add(recv_data.time_stamp, recv_data.temperature);
				hum_series.add(recv_data.time_stamp, recv_data.humidity);
				lux_series.add(recv_data.time_stamp, recv_data.light);
            }

			public void onCancelled(DatabaseError arg0) {
				// TODO Auto-generated method stub				
			}

			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub
			}
        	});
        	database.child("ct_test/ac_result").addChildEventListener(new ChildEventListener() {
                public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                    String key_recv = dataSnapshot.getKey();
                    Object i = dataSnapshot.getValue();
                    if(key_recv.equals(new String("one"))) {
                    	if((i.toString()).equals(new String("1"))) {
                    		oneLb.setForeground(Color.green);
                    	}
                    	else {
                    		oneLb.setForeground(Color.red);
                    	}
                    }
                    if(key_recv.equals(new String("two"))) {
                    	if((i.toString()).equals(new String("1"))) {
                    		twoLb.setForeground(Color.green);
                    	}
                    	else {
                    		twoLb.setForeground(Color.red);
                    	}
                    }
                    if(key_recv.equals(new String("three"))) {
                    	if((i.toString()).equals(new String("1"))) {
                    		threeLb.setForeground(Color.green);
                    	}
                    	else {
                    		threeLb.setForeground(Color.red);
                    	}
                    }
                }

    			public void onCancelled(DatabaseError arg0) {
    				// TODO Auto-generated method stub				
    			}

    			public void onChildChanged(DataSnapshot dataSnapshot, String arg1) {
    				// TODO Auto-generated method stub
    				//System.out.println(dataSnapshot);
                    String key_recv = dataSnapshot.getKey();
                    Object i = dataSnapshot.getValue();
                    if(key_recv.equals(new String("one"))) {
                    	if((i.toString()).equals(new String("1"))) {
                    		oneLb.setForeground(Color.green);
                    	}
                    	else {
                    		oneLb.setForeground(Color.red);
                    	}
                    }
                    if(key_recv.equals(new String("two"))) {
                    	if((i.toString()).equals(new String("1"))) {
                    		twoLb.setForeground(Color.green);
                    	}
                    	else {
                    		twoLb.setForeground(Color.red);
                    	}
                    }
                    if(key_recv.equals(new String("three"))) {
                    	if((i.toString()).equals(new String("1"))) {
                    		threeLb.setForeground(Color.green);
                    	}
                    	else {
                    		threeLb.setForeground(Color.red);
                    	}
                    }
		}


    			public void onChildMoved(DataSnapshot arg0, String arg1) {
    				// TODO Auto-generated method stub
    			}

    			public void onChildRemoved(DataSnapshot arg0) {
    				// TODO Auto-generated method stub
    			}
            	});
    }
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LiveMonitor window = new LiveMonitor();
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
	public LiveMonitor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1054, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(10, 132, 1018, 371);
		frame.getContentPane().add(panel);
		File key_file = null;
		final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            key_file = fc.getSelectedFile();
        } else {
            
        }
		final CardLayout cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
		JButton btnTemperature = new JButton("Temperature");
		btnTemperature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "temp");
			}
		});
		btnTemperature.setBounds(10, 11, 154, 23);
		frame.getContentPane().add(btnTemperature);
		
		JButton btnHumidy = new JButton("Humidy");
		btnHumidy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                cardLayout.show(panel, "hum");
			}
		});
		btnHumidy.setBounds(10, 50, 154, 23);
		frame.getContentPane().add(btnHumidy);
		
		JButton btnLightIntensity = new JButton("Light Intensity");
		btnLightIntensity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel, "light");
			}
		});
		btnLightIntensity.setBounds(10, 84, 154, 23);
		frame.getContentPane().add(btnLightIntensity);
    	BasicConfigurator.configure();
        try {
        	FileInputStream serviceAccount = new FileInputStream(key_file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Invalid Credential File", "InfoBox: " + "Invalid Credential File", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("ERROR: invalid service account credentials.");
            System.out.println(e.getMessage());
            System.exit(1);
		}
        database = FirebaseDatabase.getInstance().getReference();
        startListeners();

        tempset.addSeries(temp_series);
        tempchart = ChartFactory.createXYLineChart("Temperature vs Time", "Time", "Temperature", tempset, PlotOrientation.VERTICAL, false, false, false);
        tempcp = new ChartPanel(tempchart);
        panel.add(tempcp, "temp");
     
        humset.addSeries(hum_series);
        humchart = ChartFactory.createXYLineChart("Humidity vs Time", "Time", "Humidity", humset, PlotOrientation.VERTICAL, false, false, false);
        humcp = new ChartPanel(humchart);
        panel.add(humcp, "hum");

        luxset.addSeries(lux_series);
        luxchart = ChartFactory.createXYLineChart("Light Intensity vs Time", "Time", "Light Intensity", luxset, PlotOrientation.VERTICAL, false, false, false);
        luxcp = new ChartPanel(luxchart);
        panel.add(luxcp, "light");

        oneLb.setFont(new Font("Tahoma", Font.PLAIN, 73));
        oneLb.setHorizontalAlignment(SwingConstants.CENTER);
        oneLb.setBounds(461, 11, 182, 18);
        frame.getContentPane().add(oneLb);
        
        twoLb.setHorizontalAlignment(SwingConstants.CENTER);
        twoLb.setFont(new Font("Tahoma", Font.PLAIN, 73));
        twoLb.setBounds(653, 11, 182, 18);
        frame.getContentPane().add(twoLb);
        

        threeLb.setHorizontalAlignment(SwingConstants.CENTER);
        threeLb.setFont(new Font("Tahoma", Font.PLAIN, 73));
        threeLb.setBounds(846, 11, 182, 18);
        frame.getContentPane().add(threeLb);
        tglOne.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent ievent) {
        		int state = ievent.getStateChange();
        		if(state == ItemEvent.SELECTED) {
        			DatabaseReference databaseReference = database.child("ct_test").child("actuator");
        			HashMap<String, Object> result = new HashMap<String, Object>();
        			result.put("one", 1);
        			databaseReference.updateChildren(result, null);
        		}
        		else {
        			DatabaseReference databaseReference = database.child("ct_test").child("actuator");
        			HashMap<String, Object> result = new HashMap<String, Object>();
        			result.put("one", 0);
        			databaseReference.updateChildren(result, null);
        		}
        	}
        });
        tglOne.setBounds(493, 31, 133, 90);
        frame.getContentPane().add(tglOne);
        

        tglTwo.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent ievent) {
        		int state = ievent.getStateChange();
        		if(state == ItemEvent.SELECTED) {
        			DatabaseReference databaseReference = database.child("ct_test").child("actuator");
        			HashMap<String, Object> result = new HashMap<String, Object>();
        			result.put("two", 1);
        			databaseReference.updateChildren(result, null);
        		}
        		else {
        			DatabaseReference databaseReference = database.child("ct_test").child("actuator");
        			HashMap<String, Object> result = new HashMap<String, Object>();
        			result.put("two", 0);
        			databaseReference.updateChildren(result, null);
        		}
        	}
        });
        tglTwo.setBounds(680, 31, 133, 90);
        frame.getContentPane().add(tglTwo);
        
        tglThree.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent ievent) {
        		int state = ievent.getStateChange();
        		if(state == ItemEvent.SELECTED) {
        			DatabaseReference databaseReference = database.child("ct_test").child("actuator");
        			HashMap<String, Object> result = new HashMap<String, Object>();
        			result.put("three", 1);
        			databaseReference.updateChildren(result, null);
        		}
        		else {
        			DatabaseReference databaseReference = database.child("ct_test").child("actuator");
        			HashMap<String, Object> result = new HashMap<String, Object>();
        			result.put("three", 0);
        			databaseReference.updateChildren(result, null);
        		}
        	}
        });
        tglThree.setBounds(873, 31, 133, 90);
        frame.getContentPane().add(tglThree);
	}
}
