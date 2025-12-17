/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2.modul10;

/**
 *
 * @author Mal_Ghifari97
 */

import id.ac.unpas.pp2.modul10.MahasiswaController;
import id.ac.unpas.pp2.modul10.Mahasiswa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MahasiswaView extends JFrame {
    private JTextField txtNama, txtNIM, txtJurusan, txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    private JTable tableMahasiswa;
    private DefaultTableModel model;
    private MahasiswaController controller;

    public MahasiswaView() {
        controller = new MahasiswaController();
        
        setTitle("Mahasiswa");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:")); 
        txtNama = new JTextField(); 
        panelForm.add(txtNama);
        
        panelForm.add(new JLabel("NIM:")); 
        txtNIM = new JTextField(); 
        panelForm.add(txtNIM);
        
        panelForm.add(new JLabel("Jurusan:")); 
        txtJurusan = new JTextField(); 
        panelForm.add(txtJurusan);

        panelForm.add(new JLabel("Cari Nama:"));
        JPanel pnlCari = new JPanel(new BorderLayout());
        txtCari = new JTextField(); 
        btnCari = new JButton("Cari");
        pnlCari.add(txtCari, BorderLayout.CENTER); 
        pnlCari.add(btnCari, BorderLayout.EAST);
        panelForm.add(pnlCari);

        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan"); 
        btnEdit = new JButton("Edit"); 
        btnHapus = new JButton("Hapus"); 
        btnClear = new JButton("Clear");
        panelTombol.add(btnSimpan); 
        panelTombol.add(btnEdit); 
        panelTombol.add(btnHapus); 
        panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        
        this.add(panelAtas, BorderLayout.NORTH);

        model = new DefaultTableModel(new Object[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        this.add(scrollPane, BorderLayout.CENTER);

        btnSimpan.addActionListener(e -> actionSimpan());
        btnCari.addActionListener(e -> refreshTable(true));
        btnHapus.addActionListener(e -> actionHapus());
        btnEdit.addActionListener(e -> actionEdit());
        btnClear.addActionListener(e -> kosongkanForm());
        
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                if (row != -1) {
                    txtNama.setText(model.getValueAt(row, 1).toString());
                    txtNIM.setText(model.getValueAt(row, 2).toString());
                    txtJurusan.setText(model.getValueAt(row, 3).toString());
                }
            }
        });

        refreshTable(false);
    }

    private void refreshTable(boolean isSearch) {
        model.setRowCount(0);
        try {
            List<Mahasiswa> list = isSearch ? controller.search(txtCari.getText()) : controller.getAll();
            int no = 1;
            for (Mahasiswa m : list) {
                model.addRow(new Object[]{no++, m.getNama(), m.getNim(), m.getJurusan()});
            }
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); 
        }
    }

    private void actionSimpan() {
        if (txtNama.getText().isEmpty() || txtNIM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
            return;
        }
        try {
            if (controller.isNimExists(txtNIM.getText())) {
                JOptionPane.showMessageDialog(this, "NIM sudah terdaftar!");
                return;
            }
            controller.insert(new Mahasiswa(txtNama.getText(), txtNIM.getText(), txtJurusan.getText()));
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            refreshTable(false); 
            kosongkanForm();
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Gagal simpan: " + e.getMessage()); 
        }
    }

    private void actionEdit() {
        try {
            controller.update(new Mahasiswa(txtNama.getText(), txtNIM.getText(), txtJurusan.getText()));
            JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
            refreshTable(false);
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Gagal edit: " + e.getMessage()); 
        }
    }

    private void actionHapus() {
        try {
            int confirm = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                controller.delete(txtNIM.getText());
                refreshTable(false); 
                kosongkanForm();
            }
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Gagal hapus: " + e.getMessage()); 
        }
    }

    private void kosongkanForm() {
        txtNama.setText(""); 
        txtNIM.setText(""); 
        txtJurusan.setText(""); 
        txtCari.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MahasiswaView().setVisible(true);
        });
    }
}
