package src;

import java.awt.*;
import java.awt.event.*;  
//import java.awt.datatransfer.*;
import javax.swing.*;
        
public class Main {

    //login savecheck
    private static boolean login_guard=true;
    public static void access_granted(){
        login_guard= false;
    }
    
    public static void main(String[] args){

        //logowanie 
        JFrame login_frame = new JFrame("Login to SQL Database Manager");
        login_frame.setLocation(700,400);// lokalizacja na ekranie
        login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//co się dzieje jak klikniemy X
        login_frame.setSize(new Dimension(400, 200));//Wymiary ramki
        login_frame.setLayout(null);//Layout
        String[] login_data= new String[]{"admin","1234"};//passy 
        String[] input_data= new String[]{" "," "};

        //zaloguj przycisk
        JButton button_login = new JButton();
        button_login.setSize(100,30);//rozmiar przycisku 
        button_login.setLocation(140,100);//Lokalizacja przycisku
        button_login.setText("Login");//napis na przycisku
        login_frame.add(button_login);
        button_login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                if(input_data[0].equals(login_data[0])&&input_data[1].equals(login_data[1])){
                    access_granted();
                }
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

        //pętla logowania
        login_frame.setVisible(true);//Widocznośc ramki
        while(login_guard){
            input_data[0]=textarea_login.getText();
            input_data[1]=textarea_password.getText();
        }
        login_frame.setVisible(false);//Widocznośc ramki

        //okno główne
        JFrame frame = new JFrame("SQL Database Manager");// tworzenie obiektu ramka
        frame.setLocation(500,300);// lokalizacja na ekranie
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//co się dzieje jak klikniemy X
        frame.setSize(new Dimension(800, 400));//Wymiary ramki
        frame.setLayout(null);//Layout

        //przycisk do otwierania 
        JButton button_extract = new JButton();
        button_extract.setSize(100,30);//rozmiar przycisku 
        button_extract.setLocation(0,0);//Lokalizacja przycisku
        button_extract.setText("Load");//napis na przycisku
        frame.add(button_extract);//dodawanie przycisku do ramki
        button_login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
            }
        });  

        //przycisk do zapisu
        JButton button_save = new JButton();
        button_save.setSize(100,30);
        button_save.setLocation(100,0);
        button_save.setText("Save");
        frame.add(button_save);
        button_save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ 
                //
            }
        });  

        frame.setVisible(true);//Widocznośc ramki
    }
}
