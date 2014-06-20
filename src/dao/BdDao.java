/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import negocio.BD;

/**
 *
 * @author Paulo
 */
public class BdDao {
    Statement stmt;
    
    public BdDao() throws Exception {
        stmt = ConexaoMySQL.obterConexao().createStatement();
    }
    

    public void inserirBD(BD bd) throws ClassNotFoundException, SQLException {
        if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }

        String sql = "INSERT INTO bd (nome, descricao)VALUES (?,?) ";

        PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, bd.getNome());
        statement.setString(2, bd.getDescricao());

        statement.execute();
    }

    public LinkedList consultarBD() throws ClassNotFoundException, SQLException {
        if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }

        LinkedList listaBD = new LinkedList();
        String sql = "SELECT * FROM bd";
        try (PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    BD bd = new BD();

                    bd.setId(resultSet.getInt("id"));
                    bd.setNome(resultSet.getString("nome"));
                    bd.setDescricao(resultSet.getString("descricao"));

                    listaBD.add(bd);
                }
            }
        }

        return listaBD;
    }
    
        public void AlterarBD(BD bd) throws SQLException, ClassNotFoundException{
        
         if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }
        
        String sql = "UPDATE bd SET nome = ?, descricao = ? WHERE id = " + bd.getId();
      
        PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, bd.getNome());
        statement.setString(2, bd.getDescricao());
      
        statement.execute();
    }
        
    public List ConsultarTodosBD() throws SQLException, Exception{
        ResultSet rs;
        List lista = new ArrayList();
        
        // Consulta no banco
        rs = stmt.executeQuery("show databases");
        
        // Transformar RS em List
        while ( rs.next() ) {
           int id = rs.getInt(1);
           String nome = rs.getString("DataBase");          
           BD bd;
            bd = new BD(nome);
           lista.add(bd);            
            
        }
        return lista;

    }
}