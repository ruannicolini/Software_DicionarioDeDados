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
import java.util.List;
import negocio.BD;
import negocio.Tabela;


/**
 *
 * @author Ruan
 */
public class TabelaDAO {
    Statement stmt;

    public TabelaDAO() throws Exception, SQLException{
         stmt = ConexaoMySQL.obterConexao().createStatement();
    }
    
    public int inserir(Tabela tab) throws Exception, SQLException{
        String sql = "INSERT INTO tabela (nome, tipo, idBD)VALUES (?,?,?) ";
        
        PreparedStatement pst = ConexaoMySQL.obterConexao().prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS ) ;
        pst.setString(1, tab.getNome() );
        pst.setString(2, tab.getTipo() );
        pst.setInt(3, tab.getIdBanco());
        
        pst.execute();
        // Pegar ID gerado pelo campo AUTO NUMERAÇÃO
        ResultSet rs = pst.getGeneratedKeys();
        if ( rs.next() ) {
            tab.setId( rs.getInt(1) );
        }
        System.out.println("Tabela inserido no Dicionário de dados." ); 
        
        return tab.getId();
    }
    
    public void alterar(Tabela tab)  throws Exception, SQLException {
        String sql = "UPDATE tabela SET nome = ?, descricao = ?, tipo = ? WHERE idTabela = " + tab.getId();
        
        PreparedStatement pst = ConexaoMySQL.obterConexao().prepareStatement( sql ) ;
        pst.setString(1, tab.getNome() );
        pst.setString(2, tab.getDescricao() );
        pst.setString(3, tab.getTipo() );        
        pst.execute(); 
    }
    
    
    public List listarTabelas(BD bd) throws Exception, SQLException{
        
        stmt = ConexaoMySQL.obterConexao().createStatement();
        ResultSet rs;
        List lista = new ArrayList();
        
        // Consulta no banco
        rs = stmt.executeQuery("select * from tabela where idBd = " + bd.getId());
        
        // Transformar RS em List
        
        while ( rs.next() ) {
           int id = rs.getInt("idTabela");
           String nome = rs.getString("nome");
           String descricao = rs.getString("descricao");
           String tipo = rs.getString("tipo");
           int idBd = rs.getInt("idBd");
           
           Tabela tab = new Tabela(id, idBd, nome, tipo, descricao);
           lista.add(tab);            
        }
        return lista;
    }
    
    public void excluir (Tabela tab) throws Exception, SQLException {       
        String sql = "DELETE FROM Tabela WHERE idTabela = " + tab.getId();
        stmt.execute(sql);   
    }
    
}
