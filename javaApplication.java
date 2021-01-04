import java.io.*;
import java.sql.*;


class insert
{
public static boolean Addtocart(String pname,int price, String tablename,Statement stmt)throws SQLException
{
/*
   Class.forName("oracle.jdbc.driver.OracleDriver");
   //registering type4 driver
   Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","omkar2001");
   Statement stmt=con.createStatement();*/
   String q1="insert into " + tablename + " values ('" + pname + "' , '"+ price +"' )";
   
   int x=stmt.executeUpdate(q1);
   if(x>0)
        return true;
   else
       return false;
}
}

class fetch
{
  public static String fetch_pname(int product,String tablename,Statement stmt)throws IOException, SQLException{
/*
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","omkar2001");
    Statement stmt=con.createStatement();*/
    String q1="select product_Name from " + tablename + " where e_no ='" + product + "'";
    ResultSet y= stmt.executeQuery(q1);
    y.next();
    return y.getString(1);



  }
  
 
   public static int fetch_table(String table_name,Statement stmt)throws IOException {   //Not for Cart
                                                                                
      BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
      //System.out.println("Enter eid where name is to be fetched");
      //int eno=Integer.parseInt(ds.readLine());
      int DisplayRows = 15;
      try
      {/*
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","omkar2001");
        Statement stmt=con.createStatement();*/
        
        String q1="select * from "+ table_name;
        ResultSet x=stmt.executeQuery(q1);
        ResultSetMetaData rsmd = x.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        int i;
        for(i = 1; i <= columnsNumber; i++){
          System.out.print(rsmd.getColumnLabel(i) + " | ");
          
        }
        System.out.println("");
        int count = 0;
        int multiplier = 0;
        int row_counter=0;
        while(x.next())
        {
          row_counter+=1;
          count += 1;
          for(i = 1; i <= columnsNumber; i ++){
            //System.out.print(i + ". ")
            System.out.print(x.getString(i) + " | ");   // for Decorative purpose
          }///////////////////////////////////////////////We can add option "More" /////////////////////////////////////////////////////
          System.out.println("");
          while(true){
            if(count == DisplayRows){
              multiplier += 1;
              System.out.println("To see More products enter -1 else enter the respective product e_no.");
              int choice = Integer.parseInt(ds.readLine());
              if (choice == -1){
                count = 0;
                break;
              }
              else if(choice > 0 && choice <= 15*multiplier){
               return choice;
              }
              else {System.out.println("Invalid Entry!");continue;}
            }
            else{break;}
          }
          
       }
        System.out.println("Enter the number of product you want  ");
        int choice2 = Integer.parseInt(ds.readLine());
        return choice2;
        
     //con.close();
     }
     
        
     catch(Exception e)
     { 
        System.out.println(e);
       
     }
     return -1;    
}

public static int fetch_table_cart(String table_name,Statement stmt)throws IOException{        //Can be Used for CART

   BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
   //System.out.println("Enter eid where name is to be fetched");
   //int eno=Integer.parseInt(ds.readLine());
   int DisplayRows = 15;
   try
   {/*
     Class.forName("oracle.jdbc.driver.OracleDriver");
     Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","omkar2001");
     Statement stmt=con.createStatement();*/
     String q1="select * from "+ table_name;
     ResultSet x=stmt.executeQuery(q1);
     ResultSetMetaData rsmd = x.getMetaData();
     int columnsNumber = rsmd.getColumnCount();
     int i;
     for(i = 1; i <= columnsNumber; i++){
       System.out.print(rsmd.getColumnLabel(i) + " | ");
       
     }
     System.out.println("");
     int price  = 0;
     while(x.next())
     {
      
       for(i = 1; i <= columnsNumber; i ++){
        String temp= x.getString(i);
         System.out.print(temp + " | ");  
         if (i==columnsNumber)
         {
          price = price + Integer.parseInt(temp);


         } // for Decorative purpose
       }///////////////////////////////////////////////We can add option "More" /////////////////////////////////////////////////////
       System.out.println("");
       
    }
    System.out.println("Total price is : " + price);
    return price;

  //con.close();
  }


  catch(Exception e)
  { 
     System.out.println(e);
     return -1;
  }
}

public static String fetch_data(String element_no, String table_name,Statement stmt)throws IOException
   {
      BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
      //System.out.println("Enter eid where name is to be fetched");
      //int eno=Integer.parseInt(ds.readLine());
     
      try
      {/*
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","omkar2001");
        Statement stmt=con.createStatement();*/
        String q1="select * from "+ table_name + " where e_no ='"+ Integer.parseInt(element_no) +"'";
        ResultSet x=stmt.executeQuery(q1);
        ResultSetMetaData rsmd = x.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        int i;

        
        
        if(x.next())
        {
          for(i = 1; i <= columnsNumber; i ++){
            System.out.println(rsmd.getColumnLabel(i) +" :: "+ x.getString(i));
       }  
      }                                                                  
      else
       {
         System.out.println("not found");
       }
       String q2="select price from "+ table_name +" where e_no = " + element_no;
       ResultSet price=stmt.executeQuery(q2);
       if (price.next())
       {
         return  price.getString(1);

       }
      }
     catch(Exception e)
     { 
        System.out.println(e);
     }
      return "-1";
}

  public static boolean loginCheck(String iUserName, String iPassword,Statement stmt){
    try
      {/*
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","omkar2001");
        Statement stmt=con.createStatement();*/
        String q1="select * from userAccounts where username = '" + iUserName + "' and password ='" + iPassword + "'";
        ResultSet x=stmt.executeQuery(q1);
        
        if(x.next())
        {
          return true;
       }                                                                   
      else
       {
         return false;
       }
      
     }
     catch(Exception e)
     { 
        System.out.println(e);
     }
    return false;
  }

}


class javaApplication{
    static String[] iTables = {"Chair", "Wardrobe", "Bed", "Sofa", "DiningTable"};
    
    static boolean showContent(String iUserName,Statement stmt)  throws IOException,SQLException{
       BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
       
       int i;
      //  String[] iTables = javaApplicaton.tables;
        for(i = 0; i < iTables.length; i++){
            System.out.println((i+1) + ". " + iTables[i]);
        }
        
        System.out.println((i + 1) + ". Logout");
        System.out.println((i+2) + " . Viewcart");
        System.out.println("Choose Product type that you wanna see(Enter respective sr. number) / Logout ::");
        int choice = Integer.parseInt(ds.readLine());
        if(choice>0 && choice <= iTables.length){
            int product = fetch.fetch_table(iTables[choice - 1].replaceAll("\\s", ""),stmt);
           while(true){
            System.out.println("1. Buy \n 2. Add to cart");
            int select = Integer.parseInt(ds.readLine());
            int price=  Integer.parseInt(fetch.fetch_data(Integer.toString(product), iTables[choice-1],stmt));
            if (select==1)
            {
               
               while(!Checkpayment(price))
               {
                 System.out.println("Enter correct Value");
               }
               System.out.println("Payment Successful");
               break;

                

            }
            else if (select==2)
            {
                while(!insert.Addtocart(fetch.fetch_pname(product,iTables[choice-1],stmt),price,iUserName,stmt));
                System.out.println("Added to cart successfully");
               break;

            }
            else 
                System.out.println("Enter right number");
           }

            return false;
        }
        else if (choice == iTables.length + 1){ return true;}

        else if (choice == iTables.length+ 2)
        {
            int price = fetch.fetch_table_cart(iUserName,stmt);
            while(!Checkpayment(price))
            {
              System.out.println("Enter correct Value");
            }
            
            System.out.println("Payment Successful");
            Emptycart(iUserName,stmt);

            return false;
        
        }
        else {
            System.out.println("Invalid Input!");
            return false;
        }
    }

    static boolean Checkpayment(int price ) throws IOException
    {
        BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter payable amount");
        int payment=Integer.parseInt(ds.readLine());

        if (price==payment)
        {
            return true;
        }
        else return false;


    }
    
    static void signUp(Statement stmt) throws IOException{
        BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Enter Username::");
            String userName = ds.readLine(); 
            System.out.println("Enter Password::");
            String password = ds.readLine();
            System.out.println("Confirm password::");
            String password2 = ds.readLine();
            if(password.equals(password2)){
                try
                {
                    String q1="insert into userAccounts values('"+userName+"','"+password+"')";
                    int x=stmt.executeUpdate(q1);
                    if(x>0)
                    {
                        System.out.println("SignUp successful");
                    }
                    else
                    {
                        System.out.println("SignUp unsuccessful(Enter another User name)");       
                    }
                    String q = "create table " + userName + "(product_Name Varchar(20), price Varchar(20))";
                   stmt.executeUpdate(q);
                }
                catch(Exception e)
                { 
                    System.out.println(e);
                    continue;
                }
                break;
            }
            else{
                System.out.println("\nPassword didn't match.........\n");
                continue;
            }
        }
    }

    static void userLogIn(Statement stmt) throws IOException,SQLException{
        BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Username::");
        String userName = ds.readLine(); 
        System.out.println("Enter Password::");
        String password = ds.readLine();
        boolean logout = false;
        if(fetch.loginCheck(userName, password,stmt)){               /* Package Involvment */
            while(!logout){                
            logout = javaApplication.showContent(userName,stmt);
            }
        }
        else{System.out.println("User not found");}                            

    }

public static void Emptycart(String username,Statement stmt) throws SQLException
{
  String q0 = "drop table " + username ;
  stmt.executeUpdate(q0);

  String q = "create table " + username + "(product_Name Varchar(20), price Varchar(20))";
  stmt.executeUpdate(q);



}



    public static void main(String []args) throws IOException, ClassNotFoundException
	{
        boolean exit = false;
        BufferedReader ds= new BufferedReader(new InputStreamReader(System.in));
        int choice;
        while(!exit){
            System.out.println("1.log In");
            System.out.println("2.Sign Up");
            System.out.println("3.Exit");
            System.out.println("Choose respective number.::");
            choice = Integer.parseInt(ds.readLine());
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12371159","sql12371159","2hq7cvQ8S1");
            Statement stmt=con.createStatement();
            
            switch(choice){
                case 1:
                {
                    javaApplication.userLogIn(stmt);
                    break;
                }
                case 2:
                {
                    javaApplication.signUp(stmt);
                    break;
                }
                case 3:
                {
                    exit = true;
                }
            }
            con.close();
            }
            catch(SQLException e)
            {
                System.out.println(e);
            }
        }

    }

}
