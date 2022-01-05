package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseConnector {
    //login
    public static String user="root";
    public static String password="1234";
    public static Boolean accessgranted=false;
    public static void login(String user, String password){
        try{
            DataBaseConnector.user=user;
            DataBaseConnector.password=password;
            getConnection();
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public static Connection getConnection() throws Exception{
        try{
            String url = "jdbc:mysql://localhost:3306/booksdatabase";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
            accessgranted=true;
            return conn;

        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void createTable(){
        try{
            Connection conn = getConnection();
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS booksbase(id int NOT NULL AUTO_INCREMENT, Author varchar(255), Title varchar(255), PRIMARY KEY(id))");//Note the added ')' after 'id)'
            create.executeUpdate();

        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("Function complete");
        }
    }
    public static void dev_post(){
        try{
            post("George Orwell","1984r");
            post("George Orwell","Folwark Zwierzęcy");
            post("Hans Christian Andersen","Królowa Sniegu");
            post("Hans Christian Andersen","Calineczka");
            post("Hans Christian Andersen","Nowe Szaty Cesarza");
            post("Hans Christian Andersen","Brzydkie Kaczątko");
            post("Adam Mickiewicz","Pan Tadeusz");
            post("C.S. Lewis","Opowieści z Narnii");
            post("Timothy Zahn","Dynastia Thrawna");
            post("Timothy Zahn","Thrawn");
            post("Timothy Zahn","Starcraft");
            post("Aldous Huxley","Nowy Wspaniały Świat");
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("Dev Data added");
        }
    }
    
    public static void post(String author, String title){
        final String var1 = author;
        final String var2 = title;
        try{
            Connection con = getConnection();
            PreparedStatement posted = con.prepareStatement("INSERT INTO booksbase (Author, Title) VALUES ('"+var1+"','"+var2+"')");
            posted.executeUpdate();
            //PreparedStatement deleteduplicates = con.prepareStatement("delete t1 FROM booksbase t1 INNER JOIN booksbase t2 WHERE t1.id < t2.id AND t1.Author = t2.Author AND t1.Title = t2.Title");
            //deleteduplicates.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("Insert Completed");
        }
    }
    public static ArrayList<String> get() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM booksbase");
            //PreparedStatement statement = con.prepareStatement("SELECT * FROM booksbase LIMIT 3");
            //PreparedStatement statement = con.prepareStatement("SELECT * FROM booksbase ORDER BY Author ASC");
            //PreparedStatement statement = con.prepareStatement("SELECT * FROM booksbase, booksbase2 WHERE booksbase.id = booksbase2.id");
            //PreparedStatement statement = con.prepareStatement("SELECT * FROM booksbase ORDER BY Author DESC");
            //String searchingAuthor="Andrzej Sapkowski";
            //PreparedStatement statement = con.prepareStatement("SELECT * FROM booksbase WHERE Author = '"+searchingAuthor+"'");
            ResultSet result = statement.executeQuery();
        
            ArrayList<String> array = new ArrayList<String>();
            while(result.next()){
                System.out.print(result.getString("Id"));
                System.out.print(" ");
                System.out.print(result.getString("Author"));
                System.out.print(" ");
                System.out.println(result.getString("Title"));

                array.add(result.getString("Title"));
            }
            System.out.println(">>---All records have been selected---<<");
            return array;

        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static void PrintTest(){
        try{
            get();
        }catch(Exception e){
            System.out.println("No i co pajacu");
        }finally{
            System.out.println("dziaa");
        }
    }

}
