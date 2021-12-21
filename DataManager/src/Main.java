package src;

import java.awt.*;
import java.awt.event.*;  
import java.awt.datatransfer.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args){

        JFrame frame = new JFrame("SQL Manager");// tworzenie obiektu ramka
        frame.setLocation(500,300);// lokalizacja na ekranie
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//co się dzieje jak klikniemy X
        frame.setSize(new Dimension(800, 400));//Wymiary ramki
        frame.setLayout(null);//Layout
        frame.setVisible(true);//Widocznośc ramki

        // przycisk do otwierania 
        JButton button_extract = new JButton();
        button_extract.setSize(100,30);//rozmiar przycisku 
        button_extract.setLocation(0,0);//Lokalizacja przycisku
        button_extract.setText("Open");//napis na przycisku
        frame.add(button_extract);//dodawanie przycisku do ramki

        JButton button_save = new JButton();
        button_save.setSize(100,30);
        button_save.setLocation(100,0);
        button_save.setText("Save");
        frame.add(button_save);
        System.out.println("Hello, World!");



    }
}
