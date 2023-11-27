package com.example.databaseconnectivity;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.DataSource;

public class PostgreSQLConnection {

    public static DataSource createDataSource(String host, int port, String dbName, String username, String password) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(host); // PostgreSQL sunucu adresi
        dataSource.setPortNumber(port); // PostgreSQL port numarası
        dataSource.setDatabaseName(dbName); // Veritabanı adı
        dataSource.setUser(username); // Kullanıcı adı
        dataSource.setPassword(password); // Şifre

        return dataSource;
    }

}
