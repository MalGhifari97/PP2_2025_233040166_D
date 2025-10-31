/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.praktikumpemrograman2;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ramaa
 */
public class TugasModul5 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                // Label utama di atas
                JLabel label = new JLabel("Klik salah satu tombol di bawah ini!", JLabel.CENTER);

                // Buat tombol
                JButton btnNorth = new JButton("NORTH");
                JButton btnSouth = new JButton("SOUTH");
                JButton btnWest = new JButton("WEST");
                JButton btnEast = new JButton("EAST");
                JButton btnCenter = new JButton("CENTER");

                // Tambahkan aksi ke setiap tombol
                btnNorth.addActionListener(e -> label.setText("Tombol NORTH diklik!"));
                btnSouth.addActionListener(e -> label.setText("Tombol SOUTH diklik!"));
                btnWest.addActionListener(e -> label.setText("Tombol WEST diklik!"));
                btnEast.addActionListener(e -> label.setText("Tombol EAST diklik!"));
                btnCenter.addActionListener(e -> label.setText("Tombol CENTER diklik!"));

                // Tambahkan ke frame sesuai posisinya
                frame.add(label, BorderLayout.NORTH);
                frame.add(btnSouth, BorderLayout.SOUTH);
                frame.add(btnWest, BorderLayout.WEST);
                frame.add(btnEast, BorderLayout.EAST);
                frame.add(btnCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}

