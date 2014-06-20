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
import negocio.Campo;
import negocio.Tabela;

/**
 *
 * @author Ruan
 */
public class CampoDAO {
    Statement stmt;
    public CampoDAO()  throws Exception, SQLException{
        stmt = ConexaoMySQL.obterConexao().createStatement();
    }
    
    // Esse metodo busca o campo no banco local.
    public List BuscarCamposNoBanco(BD bd, Tabela tab)  throws Exception, SQLException{
        stmt = ConexaoMySQL.obterConexao(bd.getNome()).createStatement();
        ResultSet rs;
        List lista = new ArrayList();
        
        // Consulta no banco
        rs = stmt.executeQuery("describe " + tab.getNome());
        
        // Transformar RS em List
        
        while ( rs.next() ) {
           String nome = rs.getString("Field");
           String tipo = rs.getString("Type");
           Campo campo = new Campo(nome, tipo);
          
           campo.setIdTab(tab.getId());
           lista.add(campo);            
        }
        return lista;
        
    }
    
    public void alterar(Campo campo)  throws Exception, SQLException {
        String sql = "UPDATE campo SET nome = ?, descricao = ?, tipo = ? WHERE idCampo = " + campo.getIdCampo();
        
        PreparedStatement pst = ConexaoMySQL.obterConexao().prepareStatement( sql ) ;
        pst.setString(1, campo.getNome() );
        pst.setString(2, campo.getDescricao() );
        pst.setString(3, campo.getTipo() );        
        pst.execute(); 
    }
    
    public int inserir(Campo campo) throws Exception, SQLException{
        String sql = "INSERT INTO campo (nome, tipo, idTabela)VALUES (?,?,?) ";
        
        PreparedStatement pst = ConexaoMySQL.obterConexao().prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS ) ;
        pst.setString(1, campo.getNome() );
        pst.setString(2, campo.getTipo() );
        pst.setInt(3, campo.getIdTab());
        
        pst.execute();
        // Pegar ID gerado pelo campo AUTO NUMERAÇÃO
        ResultSet rs = pst.getGeneratedKeys();
        if ( rs.next() ) {
            campo.setIdCampo(rs.getInt(1) );
        }
        System.out.println("Campo inserido no Dicionário de dados." ); 
        
        return campo.getIdCampo();
    }
    
    public void excluir ( Campo campo ) throws Exception, SQLException {       
        String sql = "DELETE FROM Campo WHERE idCampo = " + campo.getIdCampo();
        stmt.execute(sql);
        
    }
    
    public List listarCampos(Tabela tab) throws Exception, SQLException{
        
        stmt = ConexaoMySQL.obterConexao().createStatement();
        ResultSet rs;
        List lista = new ArrayList();
        
        // Consulta no banco
        rs = stmt.executeQuery("select * from Campo where idTabela = " + tab.getId());
        
        // Transformar RS em List
        
        while ( rs.next() ) {
           int id = rs.getInt("idCampo");
           String nome = rs.getString("nome");
           String descricao = rs.getString("descricao");
           String tipo = rs.getString("tipo");
           int idTab = rs.getInt("idTabela");
           
           Campo campo = new Campo(id, idTab, nome, tipo, descricao);
           lista.add(campo);            
        }
        return lista;
    }
    
}
