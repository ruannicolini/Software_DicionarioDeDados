/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paulo
 */
public class DB_conect {

    public static Connection conexao = null;
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DBNAME = "";
    static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
    static final String LOGIN = "";
    static final String SENHA = "";

    /**
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void OpenConnection() throws ClassNotFoundException, SQLException  {
        Class.forName(DRIVER);
        DB_conect.conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
        System.out.println("Conectou");
    }

    public static Connection GetConnection(){
        // System.out.println("entrou");//teste 14/05/14
        // try {   
        return DB_conect.conexao;
        /*  } catch (ClassNotFoundException erro) {
         System.out.println("Driver nao encontrado " + erro.toString());
         return false;
         } catch (SQLException erro) {
         System.out.println("Falha ao conectar" + erro.toString());
         return false;
         }*/
    }

    public static void CloseConnection() throws SQLException {
        //  try {
        DB_conect.conexao.close();
        System.out.println("Desconectou");

        //} catch (SQLException erro) {
        //}
    }
}
