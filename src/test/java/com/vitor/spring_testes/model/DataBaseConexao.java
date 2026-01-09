package com.vitor.spring_testes.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DataBaseConexao {

    static Connection connection;

    @BeforeAll
    static void conexao() throws Exception{
        connection = DriverManager.getConnection("jdbc:h2:mem:teste","sa","");
        connection.createStatement().execute("CREATE TABLE userTest(id INTEGER PRIMARY KEY)");
    }

    @BeforeEach
    void teste() throws Exception{
        connection.createStatement().execute("INSERT INTO userTest (id) VALUES (1)");
    }

    @Test
   // @Disabled
    void pesquisa() throws Exception{
       var result =  connection.createStatement().executeQuery("SELECT * FROM userTest WHERE id = 1");

        Assertions.assertThat(result).isNotNull();


    }

    @AfterAll
    static void close() throws Exception{
        connection.close();
    }

}
