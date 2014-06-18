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
import negocio.Tabela;

/**
 *
 * @author Paulo
 */
public class TabelaDao {
    public void inserirTabela(Tabela tabela) throws ClassNotFoundException, SQLException {
        if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }

        String sql = "INSERT INTO tabela (nome, tipo, descricao)VALUES (?,?,?) ";

        PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, tabela.getNome());
        statement.setString(2, tabela.getTipo());
        statement.setString(3, tabela.getDescricao());

        statement.execute();
    }

    public LinkedList consultarTabela() throws ClassNotFoundException, SQLException {
        if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }

        LinkedList listaTabela = new LinkedList();
        String sql = "SELECT * FROM tabela";
        try (PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tabela tabela = new Tabela();
 
                    tabela.setNome(resultSet.getString("nome"));
                    tabela.setTipo(resultSet.getString("tipo"));
                    tabela.setDescricao(resultSet.getString("descricao"));

                    listaTabela.add(tabela);
                }
            }
        }

        return listaTabela;
    }
    
        public void AlterarTabela(Tabela tabela) throws SQLException, ClassNotFoundException{
        
         if (DB_conect.conexao == null) {
            DB_conect.OpenConnection();
        }
        
        String sql = "UPDATE tabela SET nome = ?, tipo = ? descricao = ? WHERE id = " + tabela.getId();
      
        PreparedStatement statement = DB_conect.GetConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, tabela.getNome());
        statement.setString(2, tabela.getTipo());
        statement.setString(3, tabela.getDescricao());
      
        statement.execute();
    }

}
