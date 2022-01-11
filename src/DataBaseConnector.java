package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseConnector {
    public static String user;
    public static String password;
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
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS booksbase(id int NOT NULL AUTO_INCREMENT, Author varchar(255), Title varchar(255), User varchar(255), PRIMARY KEY(id))");
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
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("Insert Completed");
        }
    }
    public static void update(String user, int id, boolean remove){
        if (remove==false){
            try{
                Connection con = getConnection();
                PreparedStatement posted = con.prepareStatement("UPDATE booksbase SET User = '"+user+"' WHERE id ="+id+"");
                posted.executeUpdate();
            }catch(Exception e){
                System.out.println(e);
            }
            finally{
                System.out.println("Data Updated added");
            }
        }else{
            try{
                Connection con = getConnection();
                PreparedStatement posted = con.prepareStatement("UPDATE booksbase SET User = null WHERE id ="+id+"");
                posted.executeUpdate();
            }catch(Exception e){
                System.out.println(e);
            }
            finally{
                System.out.println("Data Updated removed");
            }  
        }

    }
    public static int RowCount(String pickedtable){
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table);
            ResultSet result = statement.executeQuery();//wykonaj
            int i=0;
            while(result.next()){
                i++;
            }
            return i;

        }catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }
    public static String[][] GetData(String pickedtable){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] GetData_ASC_ID(String pickedtable){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" ORDER BY Id ASC");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] GetData_DESC_ID(String pickedtable){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" ORDER BY Id DESC");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] GetData_ASC_Author(String pickedtable){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" ORDER BY Author ASC");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] GetData_DESC_Author(String pickedtable){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" ORDER BY Author DESC");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] GetData_ASC_Title(String pickedtable){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" ORDER BY Title ASC");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] GetData_DESC_Title(String pickedtable){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" ORDER BY Title DESC");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static void Purge(){
        try{
            Connection con = getConnection();
            PreparedStatement kill = con.prepareStatement("drop table booksbase");
            kill.executeUpdate();
            createTable();
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("nothing left");
        }
    }
    public static void DeleteRow(String table, int id){
        try{
            Connection con = getConnection();
            PreparedStatement kill = con.prepareStatement("delete from "+table+" where Id="+id+" ORDER BY Id DESC LIMIT 1");
            kill.executeUpdate();
            createTable();
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("row removed");
        }
    }
    public static String[][] Search_ID(String pickedtable, String value){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" where id="+value);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] Search_Author(String pickedtable, String value){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" where author='"+value+"'");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static String[][] Search_Title(String pickedtable, String value){
        String[][] Data= new String[4][RowCount(pickedtable)];
        int i=0;
        try{
            final String table= pickedtable;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM "+table+" where title='"+value+"'");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Data[0][i]=result.getString("Id");
                Data[1][i]=result.getString("Author");
                Data[2][i]=result.getString("Title");
                Data[3][i]=result.getString("User");
                i++;
            }
            return Data;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
