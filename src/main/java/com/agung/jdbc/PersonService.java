/*
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 */
package com.agung.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Personal
 */
public class PersonService {
    private PersonDao personDao;
    private Connection koneksi;
    
    public void setDataSource(DataSource dataSource){
        try{
            koneksi = dataSource.getConnection();
            personDao = new PersonDao();
            personDao.setConnection(koneksi);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public Person simpan(Person person){
        try{
            koneksi.setAutoCommit(false);
            personDao.simpan(person);
            koneksi.commit();
        }
        catch(SQLException ex){
            try {
                koneksi.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace();
            }
        }
        return null;       
    }
    
    public List<Person>cariSemua(){
        try {
            return personDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<Person>();
    }
}
