/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 1547816
 */
public class ConexaoMySQL {
    static Connection conexao;
    static String servidor = "localhost";  // localhost
    //static String servidor = "172.16.32.30";  // ip da maquina onde esta o servidor
    static String nomeBanco = "dicionario"; // nome do banco
    static String login = "root";
    static String senha = "";
    
    static Connection obterConexao() throws Exception, SQLException {
        
        String url = "jdbc:mysql://" + servidor + ":3306/" + nomeBanco ;
        
        Class.forName("com.mysql.jdbc.Driver");
        conexao = DriverManager.getConnection(url, login, senha);
        return conexao;                
    }
}
