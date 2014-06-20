/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

/**
 *
 * @author Ruan
 */
public class Campo {
    int idCampo;
    int idTab;
   
    String nome;
    String tipo;
    String descricao;

    public Campo(String nome, String tipo, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Campo(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Campo(int idCampo, int idTab, String nome, String tipo, String descricao) {
        this.idCampo = idCampo;
        this.idTab = idTab;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }
    
    
    public Campo(){
    
    }

    public int getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(int idCampo) {
        this.idCampo = idCampo;
    }

    
    
    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
}
