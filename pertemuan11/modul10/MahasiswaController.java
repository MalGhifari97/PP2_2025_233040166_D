/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2.modul10;

/**
 *
 * @author Mal_Ghifari97
 */

import id.ac.unpas.pp2.modul10.KoneksiDB;
import id.ac.unpas.pp2.modul10.Mahasiswa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaController {
    public boolean isNimExists(String nim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM mahasiswa WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nim);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        }
        return false;
    }

    public void insert(Mahasiswa m) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        try (Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, m.getNama());
            pst.setString(2, m.getNim());
            pst.setString(3, m.getJurusan());
            pst.execute();
        }
    }

    public List<Mahasiswa> getAll() throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa";
        try (Connection conn = KoneksiDB.configDB();
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(sql)) {
            while (res.next()) {
                list.add(new Mahasiswa(res.getString("nama"), res.getString("nim"), res.getString("jurusan")));
            }
        }
        return list;
    }

    public List<Mahasiswa> search(String keyword) throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        try (Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                list.add(new Mahasiswa(res.getString("nama"), res.getString("nim"), res.getString("jurusan")));
            }
        }
        return list;
    }

    public void update(Mahasiswa m) throws SQLException {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, m.getNama());
            pst.setString(2, m.getJurusan());
            pst.setString(3, m.getNim());
            pst.executeUpdate();
        }
    }

    public void delete(String nim) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try (Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nim);
            pst.execute();
        }
    }
}
