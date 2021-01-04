import java.util.Random;
import java.io.*;
import java.sql.*;

public class Creator{
    
 public static void main(String args[]) throws IOException {
        BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(ds.readLine());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            try(Connection con = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12371159","sql12371159","2hq7cvQ8S1"))
            {
                Statement stmt=con.createStatement();
                String[] los = {"Chair", "DiningTable", "Sofa", "Bed", "Wardrobe"};
                int j=0 ,l = los.length;
                while (j<l)
                {
                    String q1 ="create table " + los[j] +"(e_no Varchar(20), product_Name Varchar(20), length Varchar(20), width Varchar(20), height Varchar(20), price Varchar(20));";
                    stmt.executeUpdate(q1);
                    j++;
                }
                j=0;
                while(j < l){
                    for(int i = 0; i < n; i++){
                            String q = "Insert into " + los[j] + " values(" + Integer.toString(i + 1) + "," + "'" + los[j] + "_" + (i + 1) + "'," + random_num(20, 30) + "," + random_num(20,30) + "," + random_num(20, 40) + "," + random_num(350, 1000) + ");";
                            int y = stmt.executeUpdate(q);
                            if(y > 0){
                                System.out.println("Query executed successfully...");
                            }else{
                                System.out.println("Failed to execute the query...");
                            }
                        }

                        j++;
                }
                String Query ="create table userAccounts(userName varchar(20), password varchar(14));";
                stmt.executeUpdate(Query);
            }
                    
        }catch(Exception e){
            System.err.println(e);
            
        }
    }
    

    static String random_num(int min, int max){
        Random rand = new Random();
        int r = rand.nextInt(max);
        if(r < min){
            return Integer.toString(r + min);
        }
        return Integer.toString(r);
    }
}