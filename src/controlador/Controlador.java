/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.BdDAO;
import dao.CampoDAO;
import dao.TabelaDAO;
import java.awt.Component;
import java.util.List;
import java.sql.SQLException;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import negocio.BD;
import negocio.Campo;
import negocio.Tabela;

/**
 *
 * @author Ruan
 */
public class Controlador {
    BdDAO bdDAO;
    TabelaDAO tabDAO;
    CampoDAO campoDAO;
    List<Tabela> tabelasSelecionadas;
    public Controlador()  throws Exception, SQLException{
        bdDAO = new BdDAO();
        tabDAO = new TabelaDAO();
        campoDAO = new CampoDAO();
    }
    
    public void excluirBDdoBancoDicionario(BD bd) throws Exception, SQLException  { 
        List<Campo> listaCampo;
        List<Tabela> listaTab = tabDAO.listarTabelas(bd);
        for (int i = 0; i < listaTab.size(); i++){
            listaCampo = campoDAO.listarCampos(listaTab.get(i));
            for (int j = 0; j < listaCampo.size(); j++){
                campoDAO.excluir(listaCampo.get(j));
            }
            tabDAO.excluir(listaTab.get(i));
        }
        bdDAO.excluir(bd);
    }
            
    public void carregarComboBD(JComboBox combo) throws Exception, SQLException  {  
        List lista = null;
        lista = bdDAO.listarTodosBancos();
        combo.setModel( new DefaultComboBoxModel( lista.toArray() ) );
    }

    public void carregarComboBDdoBancoDicionario(JComboBox combo) throws Exception, SQLException  {  
        List lista = null;
        lista = bdDAO.listarBancosCadastrados();
        if(lista != null){
            combo.setModel( new DefaultComboBoxModel( lista.toArray() ) );
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum Dicionário de Dados Cadastrado Ainda. ");            
        }
    }
    
    public void carregarComboTabela(JComboBox comboTab,JComboBox comboCampo, BD bd, JTextField nomeCampo, JTextField tipoCampo, JTextArea descricaoCampo, JTextField nomeTabela, JTextField tipoTabela, JTextArea descricaoTabela) throws Exception, SQLException  {  
        List<Tabela> lista = null;
        lista = tabDAO.listarTabelas(bd);
        comboTab.setModel( new DefaultComboBoxModel( lista.toArray() ) );
        
        comboTab.setSelectedItem(lista.get(0).getNome());
        Tabela tab = (Tabela)comboTab.getSelectedItem();
        nomeTabela.setText(tab.getNome());
        tipoTabela.setText(tab.getTipo());
        descricaoTabela.setText(tab.getDescricao());
        
        
        carregarComboCampos(comboCampo, nomeCampo, tipoCampo, descricaoCampo, (Tabela)lista.get(0));
    }
    
    public void carregarComboCampos(JComboBox combo, JTextField nome, JTextField tipo, JTextArea descricao, Tabela tab) throws Exception, SQLException  {  
        List<Campo> lista = null;
        lista = campoDAO.listarCampos(tab);
        combo.setModel( new DefaultComboBoxModel( lista.toArray() ) );
        
        
        Campo campo = (Campo) combo.getSelectedItem();
        nome.setText(campo.getNome());
        tipo.setText(campo.getTipo());
        descricao.setText(campo.getDescricao());
    }
    
    
    public void alterarTabela(Tabela tab) throws Exception, SQLException  {
        tabDAO.alterar(tab);
    }
    
    public void alterarCampo(Campo campo) throws Exception, SQLException  {
        campoDAO.alterar(campo);
    }
    
    public void consultarTabelas(JTable tabela,String nomeBanco) throws Exception, SQLException  {      
        
        List lista = null;
        lista = bdDAO.listarTabelas(nomeBanco);
        
        Tabela tab;
        ((DefaultTableModel) tabela.getModel()).setRowCount(0);
        Iterator<Tabela> ite = lista.iterator();
        
        while ( ite.hasNext() ) {
                    tab = ite.next();
                    ((DefaultTableModel) tabela.getModel()).addRow( tab.toArray() );         
        }
    }
    public int CriarBD(BD bd, JTable jtbTabelasSelecionadas)  throws Exception, SQLException  {
        List<Campo> CamposDaTabela;
        Tabela tab;
        int qtnLinha = jtbTabelasSelecionadas.getModel().getRowCount();
        
        int id = bdDAO.inserir(bd);
        bd.setId(id);
        
        for (int i = 0; i < qtnLinha; i++) {           
             tab = (Tabela) jtbTabelasSelecionadas.getValueAt(i, 0);  
             tab.setIdBanco(bd.getId());
             int idTab = tabDAO.inserir(tab);
             tab.setId(idTab);
             //tabelasSelecionadas.add(tab);
             CamposDaTabela = campoDAO.BuscarCamposNoBanco(bd, tab);
             
             for(int x = 0; (x < (CamposDaTabela.size())); x++){
                 campoDAO.inserir(CamposDaTabela.get(x));
             }
        }

        return id;
    }
//    public int inserirTabela(Tabela tab){
//        return 0;
//    }
    public List getListaBancosCadastrados() throws Exception, SQLException{
        List<BD> lista = bdDAO.listarBancosCadastrados();
        return lista;
    }
    
}
