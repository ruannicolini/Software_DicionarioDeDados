/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import negocio.Campo;

/**
 *
 * @author Paulo
 */
public class CampoDao {
     public void inserirCampo(Campo campo) throws ClassNotFoundException, SQLException {
        if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }

        String sql = "INSERT INTO campo (nome, tipo, descricao)VALUES (?,?,?) ";

        PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, campo.getNome());
        statement.setString(2, campo.getTipo());
        statement.setString(3, campo.getDescricao());

        statement.execute();
    }

    public LinkedList consultarCampo() throws ClassNotFoundException, SQLException {
        if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }

        LinkedList listaCampo = new LinkedList();
        String sql = "SELECT * FROM bd";
        try (PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Campo campo = new Campo();
 
                    campo.setNome(resultSet.getString("nome"));
                    campo.setTipo(resultSet.getString("tipo"));
                    campo.setDescricao(resultSet.getString("descricao"));

                    listaCampo.add(campo);
                }
            }
        }

        return listaCampo;
    }
    
        public  void AlterarCampo(Campo campo) throws SQLException, ClassNotFoundException{
        
         if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }
        
        String sql = "UPDATE campo SET nome = ?, tipo = ? descricao = ? WHERE id = " + campo.getId();
      
        PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, campo.getNome());
        statement.setString(2, campo.getTipo());
        statement.setString(3, campo.getDescricao());
      
        statement.execute();
    }

}
