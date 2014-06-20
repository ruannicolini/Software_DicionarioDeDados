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
public class Tabela {
    int id;
    int idBanco;
    String nome;
    String tipo;
    String descricao;

    public Tabela(int id, int idBanco, String nome, String tipo, String descricao) {
        this.id = id;
        this.idBanco = idBanco;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    

    
    
    public Tabela(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    
    
    
    
    public Tabela(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }
    
    
    
    @Override
    public String toString() {
        return nome;
    }
    public Object[] toArray() {
            return new Object[]{this};
    }
}
