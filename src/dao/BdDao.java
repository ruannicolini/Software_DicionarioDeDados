/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import negocio.BD;
import negocio.Tabela;

/**
 *
 * @author Ruan
 */
public class BdDAO {
    Statement stmt;
    public BdDAO() throws Exception, SQLException{
        stmt = ConexaoMySQL.obterConexao().createStatement();
    }

    public List listarTodosBancos() throws Exception, SQLException{
        
        ResultSet rs;
        List lista = new ArrayList();
        
        // Consulta no banco
        rs = stmt.executeQuery("show databases");
        
        // Transformar RS em List
        
        while ( rs.next() ) {
           String nome = rs.getString("DataBase");
           BD bd = new BD(nome);
           lista.add(bd);            
        }
        return lista;
    }
    
    public List listarBancosCadastrados() throws Exception, SQLException{
        
        ResultSet rs;
        List lista = new ArrayList();
        
        // Consulta no banco
        rs = stmt.executeQuery("select * from BD");
        
        // Transformar RS em List
        
        while ( rs.next() ) {
           Integer id = rs.getInt("idBd");
           String nome = rs.getString("Nome");
           String descricao = rs.getString("Descricao");
           BD bd = new BD(id, nome, descricao);
           lista.add(bd);            
        }
        return lista;
    }
    
    public List listarTabelas(String nomeBD) throws Exception, SQLException{
        
        stmt = ConexaoMySQL.obterConexao(nomeBD).createStatement();
        ResultSet rs;
        List lista = new ArrayList();
        
        // Consulta no banco
        rs = stmt.executeQuery("show table status");
        
        // Transformar RS em List
        
        while ( rs.next() ) {
           String nome = rs.getString("Name");
           String tipo = rs.getString("Engine");
           Tabela tab = new Tabela(nome, tipo);
           lista.add(tab);            
        }
        return lista;
    }
    
    public int inserir(BD bd) throws Exception, SQLException{
        String sql = "INSERT INTO BD (nome, descricao)VALUES (?,?) ";
        
        PreparedStatement pst = ConexaoMySQL.obterConexao().prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS ) ;
        pst.setString(1, bd.getNome() );
        pst.setString(2, bd.getDescricao() );
        
        pst.execute();
        // Pegar ID gerado pelo campo AUTO NUMERAÇÃO
        ResultSet rs = pst.getGeneratedKeys();
        if ( rs.next() ) {
            bd.setId( rs.getInt(1) );
        }
        System.out.println("Banco inserido para Dicionário de dados." ); 
        
        return bd.getId();
    }
    
    public void excluir (BD bd) throws Exception, SQLException {       
        String sql = "DELETE FROM BD WHERE idBd = " + bd.getId();
        stmt.execute(sql);   
    }
    
}
