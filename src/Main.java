package src;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//import java.awt.datatransfer.*;
import javax.swing.*;

public class Main {  
    
    public static int page=1;
    public static int searching_option;
    public static int is_sort_asc;

    public static void Reload(){
        int data_rows =DataBaseConnector.RowCount("booksbase");
        String data_booksbase[][]= DataBaseConnector.GetData("booksbase");
        int y=0;
        for (int i = 0; i < (20*4); i++) {
            if(i%4==0){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[0][y]);
                }
            }
            if(i%4==1){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[1][y]);
                }
            }
            if(i%4==2){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[2][y]);
                }
            }
            if(i%4==3){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[3][y]);
                }
                y++;
                }
            }
    }

    public static void Sort(){
        int data_rows =DataBaseConnector.RowCount("booksbase");
        int y=0;
        String data_booksbase[][];
        if(is_sort_asc==0){
            if(searching_option==0){
                data_booksbase = DataBaseConnector.GetData_DESC_ID("booksbase");
            }else if(searching_option==1){
                data_booksbase = DataBaseConnector.GetData_DESC_Author("booksbase");
            }else{
                data_booksbase = DataBaseConnector.GetData_DESC_Title("booksbase");
            }
        }else{
            if(searching_option==0){
                data_booksbase = DataBaseConnector.GetData_ASC_ID("booksbase");
            }else if(searching_option==1){
                data_booksbase = DataBaseConnector.GetData_ASC_Author("booksbase");
            }else{
                data_booksbase = DataBaseConnector.GetData_ASC_Title("booksbase");
            }
        }
        for (int i = 0; i < (20*4); i++) {
            if(i%4==0){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[0][y]);
                }
            }
            if(i%4==1){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[1][y]);
                }
            }
            if(i%4==2){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[2][y]);
                }
            }
            if(i%4==3){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[3][y]);
                }
                y++;
                }
            }
    }
    
    public static void main(String[] args){

        

        //logowanie 
        JFrame login_frame = new JFrame("Login to SQL Database Manager");
        login_frame.setLocation(700,400);// lokalizacja na ekranie
        login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//co się dzieje jak klikniemy X
        login_frame.setSize(new Dimension(400, 200));//Wymiary ramki
        login_frame.setLayout(null);//Layout
        String[] login_data= new String[]{"admin","1234"};//passy 

        //zaloguj przycisk
        JButton button_login = new JButton();
        button_login.setSize(100,30);//rozmiar przycisku 
        button_login.setLocation(140,100);//Lokalizacja przycisku
        button_login.setText("Login");//napis na przycisku
        login_frame.add(button_login);
        button_login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                DataBaseConnector.login(login_data[0], login_data[1]); 
            }
        });  

        //login
        JTextArea textarea_login = new JTextArea();
        textarea_login.setSize(100,20);
        textarea_login.setLocation(200,10);
        login_frame.add(textarea_login);
        JLabel label_login = new JLabel();
        label_login.setSize(100,20);
        label_login.setText("Login:");
        label_login.setLocation(100,10);
        login_frame.add(label_login);

        //hasło
        JTextArea textarea_password = new JTextArea();
        textarea_password.setSize(100,20);
        textarea_password.setLocation(200,40);
        login_frame.add(textarea_password);
        JLabel label_password = new JLabel();
        label_password.setSize(100,20);
        label_password.setText("Password:");
        label_password.setLocation(100,40);
        login_frame.add(label_password);

        /*
        //pętla logowania
        login_frame.setVisible(true);//Widocznośc ramki
        while(DataBaseConnector.accessgranted==false){
            login_data[0]=textarea_login.getText();
            login_data[1]=textarea_password.getText();
        }
        //*/
        login_frame.setVisible(false);//Widocznośc ramki
        
        //okno główne
        JFrame frame = new JFrame("Books Database Manager");// tworzenie obiektu ramka
        frame.setLocation(600,100);// lokalizacja na ekranie
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//co się dzieje jak klikniemy X
        frame.setSize(new Dimension(777, 700));//Wymiary ramki
        frame.setLayout(null);//Layout


        //gówna tablica z danymi
        int data_rows =DataBaseConnector.RowCount("booksbase");
        String data_booksbase[][]= DataBaseConnector.GetData("booksbase");
        int tablelocationX=160;
        int y=0;
            for (int i = 0; i < (20*4); i++) {
                new MainTableField();
                MainTableField.textFields.get(i).setBackground(Color.WHITE);
                MainTableField.textFields.get(i).setSize(150,30);
                if(i%4==0){
                    MainTableField.textFields.get(i).setLocation(tablelocationX+0,60+(30*y));
                    if(data_rows>y){
                        MainTableField.textFields.get(i).setText(data_booksbase[0][y]);
                    }
                }
                if(i%4==1){
                    MainTableField.textFields.get(i).setLocation(tablelocationX+150,60+(30*y));
                    if(data_rows>y){
                        MainTableField.textFields.get(i).setText(data_booksbase[1][y]);
                    }
                }
                if(i%4==2){
                    MainTableField.textFields.get(i).setLocation(tablelocationX+300,60+(30*y));
                    if(data_rows>y){
                        MainTableField.textFields.get(i).setText(data_booksbase[2][y]);
                    }
                }
                if(i%4==3){
                    MainTableField.textFields.get(i).setLocation(tablelocationX+450,60+(30*y));
                    if(data_rows>y){
                        MainTableField.textFields.get(i).setText(data_booksbase[3][y]);
                    }
                    y++;
                }
                MainTableField.textFields.get(i).setEditable(false);
                frame.add(MainTableField.textFields.get(i));
            }
            
        JTextField column_id = new JTextField();
        JTextField column_author = new JTextField();
        JTextField column_title = new JTextField();
        JTextField column_avalible = new JTextField();    
        column_id.setBackground(Color.WHITE);
        column_author.setBackground(Color.WHITE);
        column_title.setBackground(Color.WHITE);
        column_avalible.setBackground(Color.WHITE);
        column_id.setEditable(false);
        column_author.setEditable(false);
        column_title.setEditable(false);
        column_avalible.setEditable(false);
        column_id.setSize(150,30);
        column_author.setSize(150,30);
        column_title.setSize(150,30);
        column_avalible.setSize(150,30);
        column_id.setLocation(tablelocationX+0,30);
        column_author.setLocation(tablelocationX+150,30);
        column_title.setLocation(tablelocationX+300,30);
        column_avalible.setLocation(tablelocationX+450,30);
        column_id.setText("ID");
        column_author.setText("Author");
        column_title.setText("Title");
        column_avalible.setText("AVAILABLE?");
        frame.add(column_id);
        frame.add(column_author);
        frame.add(column_title);
        frame.add(column_avalible);  

        System.out.println(DataBaseConnector.RowCount("booksbase"));
        //przycisk do odświeżania
        JButton button_reload = new JButton();
        button_reload.setSize(150,30);//rozmiar przycisku 
        button_reload.setLocation(0,0);//Lokalizacja przycisku
        button_reload.setText("Reload");//napis na przycisku
        frame.add(button_reload);//dodawanie przycisku do ramki
        button_reload.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                Reload();
            }
        });  

        //Searching
        JLabel label_search = new JLabel("Search:");
        label_search.setSize(150,30);
        label_search.setLocation(50, 80);
        frame.add(label_search);

        JTextArea text_search = new JTextArea();
        text_search.setSize(150,30);
        text_search.setLocation(0, 110);
        frame.add(text_search);

        String[] SearchOptions = {"by Id", "By Author", "By Title"};
        JComboBox comboBox_search = new JComboBox(SearchOptions);
        comboBox_search.setSelectedItem(0);
        comboBox_search.setSize(150,30);
        comboBox_search.setLocation(0,140);
        frame.add(comboBox_search);

        JButton button_search = new JButton();
        button_search.setSize(150,30);
        button_search.setLocation(0,170);
        button_search.setText("Search");
        frame.add(button_search);
        button_search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //Search();
            }
        });

        JButton button_show_all = new JButton();
        button_show_all.setSize(150,30);
        button_show_all.setLocation(0,200);
        button_show_all.setText("Show all");
        frame.add(button_show_all);
        button_show_all.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
            }
        }); 

        //Sort
        JLabel label_order_items = new JLabel("   Sort:");
        label_order_items.setSize(150,30);
        label_order_items.setLocation(50, 250);
        frame.add(label_order_items);

        JComboBox comboBox_order_items = new JComboBox(SearchOptions);
        comboBox_order_items.setSelectedItem(0);
        comboBox_order_items.setSize(150,30);
        comboBox_order_items.setLocation(0,280);
        comboBox_order_items.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                searching_option = comboBox_order_items.getSelectedIndex();
            }
        }); 
        frame.add(comboBox_order_items);

        String[] SortOptions = {"A > Z", "Z > A"};
        JComboBox comboBox_order_updown = new JComboBox(SortOptions);
        comboBox_order_updown.setSelectedItem(0);
        comboBox_order_updown.setSize(150,30);
        comboBox_order_updown.setLocation(0,310);
        comboBox_order_updown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                is_sort_asc = comboBox_order_updown.getSelectedIndex();
            }
        }); 
        frame.add(comboBox_order_updown);
  
        JButton button_sort = new JButton();
        button_sort.setSize(150,30);
        button_sort.setLocation(0,340);
        button_sort.setText("Sort");
        frame.add(button_sort);
        button_sort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                Sort();
            }
        }); 

        //Add book
        JLabel label_book_add = new JLabel("Add Book:");
        label_book_add.setSize(150,30);
        label_book_add.setLocation(45, 390);
        frame.add(label_book_add);

        JTextArea text_author = new JTextArea("author");
        text_author.setSize(150,20);
        text_author.setLocation(1, 420);
        frame.add(text_author);

        JTextArea text_title = new JTextArea("title");
        text_title.setSize(150,20);
        text_title.setLocation(1, 450);
        frame.add(text_title);

        JButton button_book_add = new JButton();
        button_book_add.setSize(150,30);
        button_book_add.setLocation(0,480);
        button_book_add.setText("Add");
        frame.add(button_book_add);
        button_book_add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                DataBaseConnector.post(text_author.getText(),text_title.getText());
            }
        }); 

        //Delete book
        JLabel label_book_delete = new JLabel("Delete Book:");
        label_book_delete.setSize(150,30);
        label_book_delete.setLocation(30, 530);
        frame.add(label_book_delete);

        JTextArea text_id = new JTextArea(" id");
        text_id.setSize(50,20);
        text_id.setLocation(0, 565);
        frame.add(text_id);

        JButton button_book_delete = new JButton();
        button_book_delete.setSize(100,30);
        button_book_delete.setLocation(50,560);
        button_book_delete.setText("Delete");
        frame.add(button_book_delete);
        button_book_delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
            }
        }); 

        //Developer options
        JButton button_clear = new JButton();
        button_clear.setSize(200,30);
        button_clear.setLocation(160,0);
        button_clear.setText("BURN IT DOWN");
        button_clear.setBackground(Color.RED);
        button_clear.setForeground(Color.LIGHT_GRAY);
        button_clear.setVisible(false);
        frame.add(button_clear);
        button_clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                DataBaseConnector.Purge();
            }
        }); 

        //add data
        JButton button_add_data = new JButton();
        button_add_data.setSize(200,30);
        button_add_data.setLocation(360,0);
        button_add_data.setText("ADD SOME DATA");
        button_add_data.setBackground(Color.RED);
        button_add_data.setForeground(Color.LIGHT_GRAY);
        button_add_data.setVisible(false);
        frame.add(button_add_data);
        button_add_data.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                DataBaseConnector.dev_post();
            }
        }); 

        //dev mode switch
        JButton button_dev = new JButton();
        button_dev.setSize(150,30);
        button_dev.setLocation(0,700);
        button_dev.setText("Developer mode");
        button_dev.setBackground(Color.YELLOW);
        button_dev.setForeground(Color.BLACK);
        frame.add(button_dev);
        button_dev.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                button_clear.setVisible(true);
                button_add_data.setVisible(true);
                button_dev.setVisible(false);
            }
        }); 

        frame.setVisible(true);//Widocznośc ramki
    }
}
