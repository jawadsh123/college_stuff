/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ddb_project;
import static ddb_project.Places.rs;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author Jawad
 */
public class ListPlaces {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   int[] id = new int[10];
   int count=0;
   
   public ListPlaces(){
      prepareGUI();
   }
   public static void main(String[] args){
      ListPlaces  swingControlDemo = new ListPlaces();      
      swingControlDemo.showListDemo();
      
   }
   
   private static Connection connect() throws ClassNotFoundException{
        Class.forName ("com.mysql.jdbc.Driver");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/kairos", "root", "");
            System.out.println("Connection Established!");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DDB_project.class.getName()).log(Level.SEVERE, null, ex);
            return conn;
        }
    }
   
   void prepareGUI(){
      mainFrame = new JFrame("Java Swing ");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 2));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
//      controlPanel.setLayout(new GridLayout(3,1));
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   void showListDemo(){                                       
      headerLabel.setText("Places"); 

      Connection conn = null;
        try {
            conn = connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Places.class.getName()).log(Level.SEVERE, null, ex);
        }

        DefaultListModel data = new DefaultListModel();;
        
        if (conn != null){

            java.sql.Statement query = null;
            try {
                query = conn.createStatement();
                rs = query.executeQuery("Select * from location");
            } catch (SQLException ex) {
                Logger.getLogger(Login_Form.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                
                while(rs.next()){   
                    id[count] = rs.getInt("l_id");
                    count++;
                    String city = rs.getString("l_city");
                    String country = rs.getString("l_country");
                    int id = rs.getInt("l_id");
                    String listElement = city + ", " + country;
                    System.out.println(listElement);
                    id-=1;
                    data.addElement(listElement);
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Places.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

      final JList fruitList = new JList(data);
      fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      fruitList.setSelectedIndex(0);
      fruitList.setVisibleRowCount(10);

      JScrollPane placeScrollPanel = new JScrollPane(fruitList);    
      placeScrollPanel.setSize(400, 400);
      
      JButton showButton = new JButton("Show");

      showButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
            String data = "";
            if (fruitList.getSelectedIndex() != -1) {                     
               data = "Fruits Selected: " + fruitList.getSelectedValue(); 
               statusLabel.setText(data);
               int set = fruitList.getSelectedIndex();
                System.out.println(id[set]);
               mainFrame.setVisible(false);
               new CurrentWeather(id[set]).setVisible(true);
               
            }
            
            statusLabel.setText(data);
         }
      }); 
      controlPanel.add(placeScrollPanel);  
      controlPanel.add(showButton);
	  
      mainFrame.setVisible(true);             
   }

    
}
