/*
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 */
package com.agung.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Agung Pramono
 */
public class PersonDao {
    private Connection koneksi;
    private PreparedStatement insertStatement;
    private PreparedStatement getAllStatement;
    
    private final String insertSQL = "INSERT INTO kontak(nama,alamat,phone)VALUES(?,?,?);";
    private final String getAllSQL = "SELECT * FROM kontak ORDER BY nama;";
    
    
    public void setConnection(Connection c)throws SQLException{
        this.koneksi = c;
        insertStatement = this.koneksi.prepareStatement(insertSQL);
        getAllStatement = this.koneksi.prepareStatement(getAllSQL);
    }
    
    public Person simpan(Person p)throws SQLException{
        insertStatement.setString(1, p.getNama());
        insertStatement.setString(2, p.getAlamat());
        insertStatement.setString(3, p.getPhone());
        int id = insertStatement.executeUpdate();
        p.setId(id);
        return p;
    }
    
    public List<Person>getAll()throws SQLException{
        List<Person>result = new ArrayList<Person>();
        try{
            ResultSet rs = getAllStatement.executeQuery();
            while(rs.next()){
            Person p = KonversiResultSetToPerson(rs); 
            result.add(p);
        }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    private Person KonversiResultSetToPerson(ResultSet rs)throws SQLException {
        Person p = new Person();
        
        p.setId(rs.getInt("id"));
        p.setNama(rs.getString("nama"));
        p.setAlamat(rs.getString("alamat"));
        p.setPhone(rs.getString("phone"));
        return p;
    }
}
