/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import java.awt.AWTEventMulticaster;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author utkus
 */
public class JavaProject {
static User user1 = new User();
    static int chck = 1;
    ArrayList<Product> profuctList = new ArrayList<Product>();
    ResultSetMetaData mymeta = null;
    ResultSet res = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args ) {
        JavaProject ms = new JavaProject(); 
        ArrayList<Product> testList =  ms.loadListWithDB();
        System.out.println(testList.size());
        for (int i = 0; i < testList.size(); i++) {
         
            System.out.println(testList.get(i).getProductName());  
        } 
        try{
             new Loginpanel().setVisible(true); 
        }catch(Exception e){
               
        } 
     //   ms.StockUpdtae("Aspirin", 50);
   //       ms.getSpecificRecord("Aspirin");
   //     ms.insertNewRecordToDatabase(6,"as",4,"fd",8);
    }
    
    public void updateImageId(Product adressObj){
        System.out.println("updateteyim");
        System.out.println(adressObj.getImageId()); // obje null döndürüyo????????????????????????????????
        System.out.println(adressObj.getProductName());
        try{
             Connection con = DBconnection.connectdb();
             Statement st = con.createStatement();
             try (PreparedStatement stmt = con.prepareStatement(
                    "UPDATE medictable SET IMAGE = ? WHERE name = ?")) {
                         System.out.println("set edicem");   
                         stmt.setString(1, adressObj.getImageId());
                         stmt.setString(2, adressObj.getProductName());

                         stmt.executeUpdate();
                
            }
        }catch (SQLException ex) {
            System.out.println("set edemicem");
        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteProduct(String name){
        
    try {
        Connection con = DBconnection.connectdb();
        Statement st;
        st = con.createStatement();
   //     st.executeUpdate(
   //     "DELETE FROM medictable WHERE ID = (?)"); 
   //     int id = 6;
        String query = "DELETE FROM medictable WHERE NAME = (?)";
        PreparedStatement add = con.prepareStatement(query);
        add.setString(1, name);
        add.executeUpdate();
        System.out.println("Product Succesfully deleted");
    } catch (SQLException ex) {
        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }
    
    // Search function
    public ArrayList<Product> getSpecificRecord(String data){ 
        ArrayList<Product> limitedProList = new ArrayList<Product>();
    try {
        Connection con = DBconnection.connectdb();
        Statement st = con.createStatement();  
        String query = "SELECT * FROM medictable WHERE NAME ='" + data + "' ";
        st.executeQuery(query);
        ResultSet rs = st.getResultSet();
        if(rs.next()){
            Product pro = new Product(rs.getInt("id") ,rs.getString("name"), rs.getString("type"), rs.getInt("price"), rs.getInt("stock") );
            limitedProList.add(pro);
        }
        return limitedProList;
    } catch (SQLException ex) {
        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    
    public void insertNewRecordToDatabase(int id, String name, int stock, String type, int price){
    try {
        Connection con = DBconnection.connectdb();
        Statement st = con.createStatement();
        String query = "INSERT INTO medictable(id, name, stock, type, price) VALUES(?,?,?,?,?)";
        PreparedStatement add = con.prepareStatement(query);
        
        add.setInt(1, id);
        add.setString(2, name);
        add.setInt(3, stock);
        add.setString(4, type);
        add.setInt(5, price);
        add.executeUpdate();
//        mymeta = res.getMetaData();
        System.out.println("User Successfuly created");
    
    } catch (SQLException ex) {
        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    // return number of rows in database
    public int returnRowCount(){
    try {
        Connection con = DBconnection.connectdb();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from medictable");
        int rowNumber = 0;
        while(rs.next()){
            rowNumber++;
            
        }
        return rowNumber;
    } catch (SQLException ex) {
        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
    }
    return NULL;
    }
    
    // load datas in row from database
    public ArrayList<Product> loadListWithDB(){
        ArrayList<Product> productList = new ArrayList<Product>();
        try {
            Connection con = DBconnection.connectdb();
            PreparedStatement ps = null;                      
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from medictable");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
      
       //     System.out.println("Row Number == " + returnRowCount());
            rs = st.executeQuery("select * from medictable");
            while(rs.next())
            {
                     Product movie1 = new Product(rs.getInt("id") ,rs.getString("name"), rs.getString("type"), rs.getInt("price"), rs.getInt("stock"), rs.getString("IMAGE") );
                     productList.add(movie1);
  
      }
        } catch (SQLException ex) {
            Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return productList; 
    } 
    
  /*  public ArrayList<Product> updateListWithDB(ArrayList<Product> array){
        
    } */
    
    public void StockUpdtate(Product pro ){
        
        try {
             
            Connection con = DBconnection.connectdb();
            Statement st = con.createStatement();
            
            
            try (PreparedStatement checkAccountExists = con.prepareStatement(
                    "SELECT STOCK FROM medictable WHERE NAME = ?")) {
                checkAccountExists.setString(1, pro.getProductName());
                try (ResultSet RS = checkAccountExists.executeQuery()){
                    if (RS.next()) {
                    
                    
                         try (PreparedStatement stmt = con.prepareStatement(
                         "UPDATE medictable SET STOCK = ? WHERE NAME = ?")){ 

                         stmt.setInt(1, pro.getStock());
                         stmt.setString(2, pro.getProductName());

                         stmt.executeUpdate();
                         JOptionPane.showMessageDialog(null, "Success! New Stock is "+pro.getStock());
                         }
                    }
                }
            }   
        } catch (SQLException ex) {
        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
    
    
    
   public void UpdateAll(int id,String name, String type, int price, int stock){
       try {
            ArrayList<Product> productList = new ArrayList<Product>();
            Connection con = DBconnection.connectdb();
            Statement st = con.createStatement();
            
            
            try (PreparedStatement checkAccountExists = con.prepareStatement(
                    "SELECT * FROM medictable WHERE ID = ?")) {
                checkAccountExists.setInt(1, id);
                try (ResultSet RS = checkAccountExists.executeQuery()){
                    if (RS.next()) {
                       
                    
                         try (PreparedStatement stmt = con.prepareStatement(
                         "UPDATE medictable SET STOCK = ?, NAME = ?, TYPE = ?, PRICE = ? WHERE ID = ?")){ 
                         stmt.setInt(1, stock);
                         stmt.setString(2, name);
                         stmt.setString(3, type);
                         stmt.setInt(4, price);
                         stmt.setInt(5, id);

                         stmt.executeUpdate();
                         JOptionPane.showMessageDialog(null, "Success! Product Updated! ");
                         } 
                         
                         
                    }
                }
            }
            con.close();
            st.close();
            
        } catch (SQLException ex) {
        Logger.getLogger(JavaProject.class.getName()).log(Level.SEVERE, null, ex);
        }
   } 
    
    
    Product pro = new Product();
    public Product buyItem(int selected, int decValue){
      ArrayList<Product>  newAr = loadListWithDB();
      
   //     for (int i = 0; i < newAr.size(); i++) {
    //        if(newAr.get(selected).equals(newAr.get(i))){
               if(newAr.get(selected).getStock() >= decValue ){
                   pro.setStock(newAr.get(selected).getStock()- decValue);
                   pro.setProductName(newAr.get(selected).getProductName());
                   StockUpdtate(pro);
      //             break;
               }else{
                   System.out.println("ssss");
                   JOptionPane.showMessageDialog(null, "Stock can not supply selected value");
               } 
               
     //       }
            
//        }
        
        System.out.println("stock = " + newAr.get(selected).getStock());
      return pro;
    }
    
    
    

   
}
