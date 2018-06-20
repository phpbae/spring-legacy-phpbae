package com.phpbae.web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    final static String DB_URI = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
    final static String USER = "root";
    final static String PASSWORD = "1234";

    public static Connection getJdbcConnection() {

        /**
         * 1. DB connection Driver 클래스 로딩.
         * 2. DriverManager 를 이용하여, 커넥션 생성(생성 시, URI / USER / PASSWORD 정보를 넘겨준다)
         */

        Connection connection = null;

        try {
            //Class loading
            Class.forName(DRIVER_NAME);
            //Get Connection
            connection = DriverManager.getConnection(DB_URI, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println(connection);
            return connection;
        }
    }

}
