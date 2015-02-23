/*
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 */
package com.agung.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Main {
    public static void main(String[]args){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDatabaseName("kontak");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        
        PersonService ps = new PersonService();
        ps.setDataSource(dataSource);
        
        Person person = new Person();
        person.setNama("Joko Bodho");
        person.setAlamat("Jakarta");
        person.setPhone("087867665674");
        ps.simpan(person);
        
        List<Person>findAll = ps.cariSemua();
        for(Person persons : findAll){
            System.out.println("ID : "+persons.getId());
            System.out.println("Nama : "+persons.getNama());
            System.out.println("Alamat : "+persons.getAlamat());
            System.out.println("No.Tlp : "+persons.getPhone());
        }
        
        try{
            dataSource.getConnection().close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
