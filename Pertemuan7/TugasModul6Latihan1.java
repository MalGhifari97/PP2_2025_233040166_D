/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pp2;

/**
 *
 * @author ramaa
 */
import javax.swing.*;
import java.awt.*;

/**
 * Kalkulator - hanya layout (BorderLayout + GridLayout)
 * 16 tombol: 10 angka (0-9), 4 operator (+ - * /), ditambah '=' dan 'C' (total 16).
 */
public class TugasModul6Latihan1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Kalkulator - Layout Saja");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(320, 420);
            frame.setLocationRelativeTo(null);

            // Layar (JTextField) di NORTH
            JTextField display = new JTextField();
            display.setHorizontalAlignment(JTextField.RIGHT);
            display.setEditable(false);
            display.setFont(display.getFont().deriveFont(24f));
            frame.add(display, BorderLayout.NORTH);

            // Panel tombol: Grid 4x4 di CENTER
            JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Susunan tombol (baris demi baris)
            String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+"
            };

            for (String text : buttons) {
                JButton b = new JButton(text);
                b.setFont(b.getFont().deriveFont(18f));
                // karena ini hanya layout, tidak menambahkan ActionListener untuk operasi
                buttonPanel.add(b);
            }

            frame.add(buttonPanel, BorderLayout.CENTER);

            // Opsional: status bar kecil di SOUTH
            JLabel status = new JLabel("Tugas Modul 6 Latihan 1 )");
            status.setHorizontalAlignment(SwingConstants.CENTER);
            status.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            frame.add(status, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}

