package src;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//import java.awt.datatransfer.*;
import javax.swing.*;

public class Main {  
    
    public static int sort_option;
    public static int search_option;
    public static int is_sort_asc;
    public static String searching_word;

    //odświeżenie tabeli
    public static void Reload(String booksbase[][], int datarows){
        int data_rows = datarows;
        String data_booksbase[][]= booksbase;
        int y=0;
        for (int i = 0; i < (20*4); i++) {
            if(i%4==0){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[0][y]);
                }else{
                    MainTableField.textFields.get(i).setText(" ");  
                }
            }
            if(i%4==1){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[1][y]);
                }else{
                    MainTableField.textFields.get(i).setText(" ");  
                }
            }
            if(i%4==2){
                if(data_rows>y){
                    MainTableField.textFields.get(i).setText(data_booksbase[2][y]);
                }else{
                    MainTableField.textFields.get(i).setText(" ");  
                }
            }
            if(i%4==3){
                if(data_rows>y){
                    if (data_booksbase[3][y]==null){
                        MainTableField.textFields.get(i).setText(" ");
                    }else{
                        MainTableField.textFields.get(i).setText(data_booksbase[3][y]+" have it");
                    }
                }
                y++;
                }
            }
    }

    //funkcja układa informacje według wybranych opcji
    public static void Sort(){
        String data_booksbase[][];//tablica dwuwymiarowa zawierająca dane z bazy danych
        if(is_sort_asc==0){
            if(sort_option==0){
                data_booksbase = DataBaseConnector.GetData_DESC_ID("booksbase");
            }else if(sort_option==1){
                data_booksbase = DataBaseConnector.GetData_DESC_Author("booksbase");
            }else{
                data_booksbase = DataBaseConnector.GetData_DESC_Title("booksbase");
            }
        }else{
            if(sort_option==0){
                data_booksbase = DataBaseConnector.GetData_ASC_ID("booksbase");
            }else if(sort_option==1){
                data_booksbase = DataBaseConnector.GetData_ASC_Author("booksbase");
            }else{
                data_booksbase = DataBaseConnector.GetData_ASC_Title("booksbase");
            }
        }
        Reload(data_booksbase,DataBaseConnector.RowCount("booksbase"));
    }


    public static void Search(){
        String data_booksbase[][];
        if(search_option==0){
            data_booksbase = DataBaseConnector.Search_ID("booksbase", searching_word );
        }else if(search_option==1){
            data_booksbase = DataBaseConnector.Search_Author("booksbase", searching_word );
        }else{
            data_booksbase = DataBaseConnector.Search_Title("booksbase", searching_word );
        }
        Reload(data_booksbase,DataBaseConnector.RowCount("booksbase"));
    }

    public static void main(String[] args){
        //logowanie 
        JFrame login_frame = new JFrame("Login to SQL Database Manager");
        login_frame.setLocation(700,400);
        login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_frame.setSize(new Dimension(400, 200));
        login_frame.setLayout(null);
        String[] login_data= new String[]{"admin","1234"};//passy 

        //zaloguj przycisk
        JButton button_login = new JButton();
        button_login.setSize(100,30);
        button_login.setLocation(140,100);
        button_login.setText("Login");
        login_frame.add(button_login);
        button_login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                DataBaseConnector.login(login_data[0], login_data[1]); 
            }
        });  

        //okno logowania
        JTextArea textarea_login = new JTextArea();// pole do wpisywania loginu
        textarea_login.setSize(100,20);//wymiary
        textarea_login.setLocation(200,10);//lokalizacja
        login_frame.add(textarea_login);//dodaj do ramki logownaia
        
        JLabel label_login = new JLabel();//label z informacją
        label_login.setSize(100,20);//wymiary
        label_login.setText("Login:");// informacja
        label_login.setLocation(100,10);//lokalizacja
        login_frame.add(label_login);//dodaj do ramki logowania

        //hasło (pole textowe i label)
        JTextArea textarea_password = new JTextArea();
        textarea_password.setSize(100,20);
        textarea_password.setLocation(200,40);
        login_frame.add(textarea_password);
        JLabel label_password = new JLabel();
        label_password.setSize(100,20);
        label_password.setText("Password:");
        label_password.setLocation(100,40);
        login_frame.add(label_password);

        //*
        //pętla logowania
        //ma na celu zablokować dalsze uruchomienie programu, 
        //tak długo jak długo wpisane hasło nie jest poprawne 
        login_frame.setVisible(true);//Widocznośc ramki
        while(DataBaseConnector.accessgranted==false){
            login_data[0]=textarea_login.getText();
            login_data[1]=textarea_password.getText();
        }
        //*/
        login_frame.setVisible(false);//wyłącznie Widoczności ramki logowania
        
        //okno główne
        JFrame frame = new JFrame("Books Database Manager");// tworzenie obiektu ramka
        frame.setLocation(600,100);// lokalizacja na ekranie
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//co się dzieje jak klikniemy X
        frame.setSize(new Dimension(777, 700));//Wymiary ramki
        frame.setLayout(null);//Layout


        //główna tablica z danymi
        int data_rows =DataBaseConnector.RowCount("booksbase");//ilość rzędów
        String data_booksbase[][]= DataBaseConnector.GetData("booksbase");//tablica z danymi z bazy danych
        int tablelocationX=160;//lokalizacja tabeli
        int y=0;
            //pierwsza inicjalizacja tabeli zaraz po jej utworzeniu
            for (int i = 0; i < (20*4); i++) {
                new MainTableField();
                MainTableField.textFields.get(i).setBackground(Color.WHITE);//kolor tła komórki
                MainTableField.textFields.get(i).setSize(150,30);//wymiar jednej komórki

                if(i%4==0){//pierwsza kolumna
                    MainTableField.textFields.get(i).setLocation(tablelocationX+0,60+(30*y));
                    if(data_rows>y){//if zapobiega błędowi wynikającemu z ograniczonej długości tabeli
                        MainTableField.textFields.get(i).setText(data_booksbase[0][y]);
                    }else{
                        MainTableField.textFields.get(i).setText(" ");  
                    }
                }
                if(i%4==1){//druga kolumna
                    MainTableField.textFields.get(i).setLocation(tablelocationX+150,60+(30*y));
                    if(data_rows>y){//if zapobiega błędowi wynikającemu z ograniczonej długości tabeli
                        MainTableField.textFields.get(i).setText(data_booksbase[1][y]);
                    }else{
                        MainTableField.textFields.get(i).setText(" ");  
                    }
                }
                if(i%4==2){//trzecia kolumna
                    MainTableField.textFields.get(i).setLocation(tablelocationX+300,60+(30*y));
                    if(data_rows>y){//if zapobiega błędowi wynikającemu z ograniczonej długości tabeli
                        MainTableField.textFields.get(i).setText(data_booksbase[2][y]);
                    }else{
                        MainTableField.textFields.get(i).setText(" ");  
                    }
                    
                }
                if(i%4==3){//czwarta kolumna
                    MainTableField.textFields.get(i).setLocation(tablelocationX+450,60+(30*y));
                    if(data_rows>y){//if zapobiega błędowi wynikającemu z ograniczonej długości tabeli
                        String temp = data_booksbase[3][y];
                        if (temp==null){
                            MainTableField.textFields.get(i).setText(" ");
                        }else{
                        //w przypadku w którym książka jest pożyczona udzielana jest informacja na temat tego kto ją pożyczył
                            MainTableField.textFields.get(i).setText(data_booksbase[3][y]+" have it");
                        }
                    }else{
                        MainTableField.textFields.get(i).setText(" ");  
                    }
                    y++;
                }
                MainTableField.textFields.get(i).setEditable(false);//wyłącznie edycji danych w komórce z poziomu użykownika
                frame.add(MainTableField.textFields.get(i));//dodanie komórki do okna
            }
        
        //tworzenie nagłówków do tabeli
        JTextField column_id = new JTextField();
        JTextField column_author = new JTextField();
        JTextField column_title = new JTextField();
        JTextField column_avalible = new JTextField();    
        //kolor tła nagłówków
        column_id.setBackground(Color.LIGHT_GRAY);
        column_author.setBackground(Color.LIGHT_GRAY);
        column_title.setBackground(Color.LIGHT_GRAY);
        column_avalible.setBackground(Color.LIGHT_GRAY);
        //wyłącznie edycji
        column_id.setEditable(false);
        column_author.setEditable(false);
        column_title.setEditable(false);
        column_avalible.setEditable(false);
        //wymiar komórki
        column_id.setSize(150,30);
        column_author.setSize(150,30);
        column_title.setSize(150,30);
        column_avalible.setSize(150,30);
        //lokalizacja w ramce
        column_id.setLocation(tablelocationX+0,30);
        column_author.setLocation(tablelocationX+150,30);
        column_title.setLocation(tablelocationX+300,30);
        column_avalible.setLocation(tablelocationX+450,30);
        //wyświetlana informacja
        column_id.setText("ID");
        column_author.setText("Author");
        column_title.setText("Title");
        column_avalible.setText("Avalibility data");
        //dodaj do ramki
        frame.add(column_id);
        frame.add(column_author);
        frame.add(column_title);
        frame.add(column_avalible);  

        //przycisk do odświeżania
        JButton button_reload = new JButton();
        button_reload.setSize(150,30);
        button_reload.setLocation(0,0);
        button_reload.setText("Reload");
        frame.add(button_reload);
        button_reload.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                Reload(DataBaseConnector.GetData("booksbase"),DataBaseConnector.RowCount("booksbase"));
            }
        });  

        //wyszukiwanie
        JLabel label_search = new JLabel("Search:");
        label_search.setSize(150,30);
        label_search.setLocation(10, 30);
        frame.add(label_search);

        JTextArea text_search = new JTextArea();
        text_search.setSize(150,20);
        text_search.setLocation(1, 60);
        frame.add(text_search);

        String[] SearchOptions = {"by Id", "By Author", "By Title"};
        JComboBox comboBox_search = new JComboBox(SearchOptions);
        comboBox_search.setSelectedItem(0);
        comboBox_search.setSize(150,30);
        comboBox_search.setLocation(0,90);
        comboBox_search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                search_option = comboBox_search.getSelectedIndex();
            }
        }); 
        frame.add(comboBox_search);

        JButton button_search = new JButton();
        button_search.setSize(150,30);
        button_search.setLocation(0,120);
        button_search.setText("Search");
        frame.add(button_search);
        button_search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                searching_word = text_search.getText();
                Search();
            }
        });

        //Sortowanie
        JLabel label_order_items = new JLabel("Sort:");
        label_order_items.setSize(150,30);
        label_order_items.setLocation(10, 150);
        frame.add(label_order_items);

        String OrderOptions[] =SearchOptions;//alias
        JComboBox comboBox_order_items = new JComboBox(OrderOptions);
        comboBox_order_items.setSelectedItem(0);
        comboBox_order_items.setSize(150,30);
        comboBox_order_items.setLocation(0,180);
        comboBox_order_items.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                sort_option = comboBox_order_items.getSelectedIndex();
            }
        }); 
        frame.add(comboBox_order_items);

        String[] SortOptions = {"Z > A", "A > Z"};
        JComboBox comboBox_order_updown = new JComboBox(SortOptions);
        comboBox_order_updown.setSelectedItem(0);
        comboBox_order_updown.setSize(150,30);
        comboBox_order_updown.setLocation(0,210);
        comboBox_order_updown.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                is_sort_asc = comboBox_order_updown.getSelectedIndex();
            }
        }); 
        frame.add(comboBox_order_updown);
  
        JButton button_sort = new JButton();
        button_sort.setSize(150,30);
        button_sort.setLocation(0,240);
        button_sort.setText("Sort");
        frame.add(button_sort);
        button_sort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                Sort();
            }
        }); 

        //Dodaj książkę 
        JLabel label_book_add = new JLabel("Add Book:");
        label_book_add.setSize(150,30);
        label_book_add.setLocation(10, 270);
        frame.add(label_book_add);

        JTextArea text_author = new JTextArea("author");
        text_author.setSize(150,20);
        text_author.setLocation(1, 300);
        frame.add(text_author);

        JTextArea text_title = new JTextArea("title");
        text_title.setSize(150,20);
        text_title.setLocation(1, 330);
        frame.add(text_title);

        JButton button_book_add = new JButton();
        button_book_add.setSize(150,30);
        button_book_add.setLocation(0,360);
        button_book_add.setText("Add");
        frame.add(button_book_add);
        button_book_add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                DataBaseConnector.post(text_author.getText(),text_title.getText());
            }
        }); 

        //Usuń ksiązkę
        JLabel label_book_delete = new JLabel("Delete Book:");
        label_book_delete.setSize(150,30);
        label_book_delete.setLocation(10, 390);
        frame.add(label_book_delete);

        JTextArea text_id = new JTextArea("id");
        text_id.setSize(49,20);
        text_id.setLocation(1, 425);
        frame.add(text_id);

        JButton button_book_delete = new JButton();
        button_book_delete.setSize(100,30);
        button_book_delete.setLocation(50,420);
        button_book_delete.setText("Delete");
        frame.add(button_book_delete);
        button_book_delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                DataBaseConnector.DeleteRow("booksbase", Integer.parseInt(text_id.getText()) );
            }
        }); 

        //Opcje developera 
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

        //Dodaj informacje 
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

        //przycisk włączający tryb developera
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


        
        JFrame b_frame = new JFrame("Borrow");
        b_frame.setLocation(100,300);
        b_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b_frame.setSize(new Dimension(235, 200));
        b_frame.setLayout(null);

        JLabel label_book_id = new JLabel("Book id:");
        label_book_id.setSize(150,30);
        label_book_id.setLocation(20, 0);
        b_frame.add(label_book_id);

        JLabel label_book_borrower = new JLabel("Borrower name:");
        label_book_borrower.setSize(150,30);
        label_book_borrower.setLocation(10, 60);
        b_frame.add(label_book_borrower);

        JTextArea text_id_bbook = new JTextArea("id");
        text_id_bbook.setSize(40,20);
        text_id_bbook.setLocation(11, 30);
        b_frame.add(text_id_bbook);

        JTextArea text_borrower = new JTextArea("Borrower");
        text_borrower.setSize(100,20);
        text_borrower.setLocation(11, 90);
        b_frame.add(text_borrower);

        JButton button_borrow = new JButton();
        button_borrow.setSize(100,30);
        button_borrow.setLocation(10,120);
        button_borrow.setText("Borrow");
        b_frame.add(button_borrow);
        button_borrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DataBaseConnector.update(text_borrower.getText(), Integer.parseInt(text_id_bbook.getText()), false);
            }
        });

        JButton button_return = new JButton();
        button_return.setSize(100,30);
        button_return.setLocation(110,120);
        button_return.setText("Return");
        b_frame.add(button_return);
        button_return.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                DataBaseConnector.update(text_borrower.getText(), Integer.parseInt(text_id_bbook.getText()), true);
            }
        });

        JButton borrowbook_menu = new JButton();
        borrowbook_menu.setSize(150,30);
        borrowbook_menu.setLocation(0,480);
        borrowbook_menu.setText("Borrow");
        frame.add(borrowbook_menu);
        borrowbook_menu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(b_frame.isVisible()){
                    b_frame.setVisible(false);
                }else{
                    b_frame.setVisible(true);
                }
            }
        }); 
        frame.setVisible(true);
    }
}
