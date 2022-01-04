package src;

import java.awt.*;
import java.awt.event.*;  
//import java.awt.datatransfer.*;
import javax.swing.*;
        
public class Main {    
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
        frame.setSize(new Dimension(800, 800));//Wymiary ramki
        frame.setLayout(null);//Layout

        JTable table_booksdata = new JTable();
        table_booksdata.setSize(150,30);
        table_booksdata.setLocation(200,200);
        frame.add(table_booksdata);


        DataBaseConnector.PrintTest();

        //przycisk do odświeżania
        JButton button_reload = new JButton();
        button_reload.setSize(150,30);//rozmiar przycisku 
        button_reload.setLocation(0,0);//Lokalizacja przycisku
        button_reload.setText("Reload");//napis na przycisku
        frame.add(button_reload);//dodawanie przycisku do ramki
        button_login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
            
            }
        });  

        //przycisk do zapisu
        /*
        JButton button_save = new JButton();
        button_save.setSize(150,30);
        button_save.setLocation(0,30);
        button_save.setText("Save");
        frame.add(button_save);
        button_save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
            }
        }); 
        */
        
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
        //comboBox_search.addActionListener(this);
        frame.add(comboBox_search);

        JButton button_search_Id = new JButton();
        button_search_Id.setSize(150,30);
        button_search_Id.setLocation(0,170);
        button_search_Id.setText("Search");
        frame.add(button_search_Id);
        button_search_Id.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
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

        //Order
        JLabel label_order_items = new JLabel("   Sort:");
        label_order_items.setSize(150,30);
        label_order_items.setLocation(50, 250);
        frame.add(label_order_items);

        JComboBox comboBox_order_items = new JComboBox(SearchOptions);
        comboBox_order_items.setSelectedItem(0);
        comboBox_order_items.setSize(150,30);
        comboBox_order_items.setLocation(0,280);
        //comboBox_search.addActionListener(this);
        frame.add(comboBox_order_items);

        String[] SortOptions = {"Ascending", "Descending"};
        JComboBox comboBox_order_updown = new JComboBox(SortOptions);
        comboBox_order_updown.setSelectedItem(0);
        comboBox_order_updown.setSize(150,30);
        comboBox_order_updown.setLocation(0,310);
        //comboBox_search.addActionListener(this);
        frame.add(comboBox_order_updown);

        
        JButton button_sort = new JButton();
        button_sort.setSize(150,30);
        button_sort.setLocation(0,340);
        button_sort.setText("Sort");
        frame.add(button_sort);
        button_sort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
            }
        }); 

        //Add book
        JLabel label_book_add = new JLabel("Add Book:");
        label_book_add.setSize(150,30);
        label_book_add.setLocation(45, 390);
        frame.add(label_book_add);

        JTextArea text_author = new JTextArea(" author");
        text_author.setSize(150,20);
        text_author.setLocation(0, 420);
        frame.add(text_author);

        JTextArea text_title = new JTextArea(" title");
        text_title.setSize(150,20);
        text_title.setLocation(0, 450);
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

        //Borrowmenu



        //Developer options

        JButton button_clear = new JButton();
        button_clear.setSize(200,30);
        button_clear.setLocation(400,0);
        button_clear.setText("BURN IT DOWN");
        button_clear.setBackground(Color.RED);
        button_clear.setForeground(Color.LIGHT_GRAY);
        button_clear.setVisible(false);
        frame.add(button_clear);
        button_clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
            }
        }); 

        JButton button_add_data = new JButton();
        button_add_data.setSize(200,30);
        button_add_data.setLocation(600,0);
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
