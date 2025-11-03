/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.praktikumpemrograman2;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
 
            public void run() {
                JFrame frame = new JFrame("Jendela dan tombol");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new FlowLayout());
                
                JLabel label  = new JLabel ("Ini adalah Jabel."); 
                JButton button =  new JButton ("Klik Saya");
                
                button.addActionListener(e ->{
                    label.setText("Tombol berhasill di klik");
                });
                
                    frame.add(label);
                    frame.add(button);
                    frame.setVisible(true);
            }
        });
    }
}

