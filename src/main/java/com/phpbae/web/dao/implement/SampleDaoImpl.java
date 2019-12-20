package com.phpbae.web.dao.implement;

import com.phpbae.web.config.DBConnection;
import com.phpbae.web.dao.SampleDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SampleDaoImpl implements SampleDao {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * 간단한 SELECT SQL 을 실행하여 결과를 가지고 온다.
     * TABLE - sample
     * COLUMN - idx(PK) int auto increment / name varchar / code int
     * sample data - 1 / 브라질 / 20
     *
     * @return
     */
    @Override
    public int exampleJDBC() {

        int resultCode = -1;

        connection = DBConnection.getJdbcConnection();

        try {
            preparedStatement = connection.prepareStatement("SELECT code FROM sample WHERE idx = ?");
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultCode = resultSet.getInt("code");
            } else {
                System.out.println("데이터가 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //resultSet Close
                if (resultSet != null) {
                    resultSet.close();
                }

                //preparedStatement Close
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                //connection Close
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return resultCode;
    }
}

/**
 * 문제점.
 * JDBC를 사용하는 경우, 위 메서드와 같이 대량의 소스코드를 기술하여야 한다.(생산성이 너무 좋지 않다.)
 * try ~ catch ~ finally ~ try ~ catch 반본적인 지겨운 코드
 * Connection / PreparedStatement / ResultSet close 를 하지 않으면 DB 리소스 고갈과 메모리 누수의 원인
 * SQLException 은 DB 벤더별로 정의되어 있어서 DB를 바꾸면 소스도 변경해야하며, 이러한 정보를 취득해서 조사를 해야하는 코드를 짜야한다.
 * 결론적으로, 깔끔하지 않은 반복적인 코드의 사용으로 DAO 코드가 더러워지면서 유지보수 및 생산성에 악영향을 미친다.
 */
