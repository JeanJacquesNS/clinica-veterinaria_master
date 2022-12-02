/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.VO;

import java.util.Date;

/**
 *
 * @author 
 */
public class Exame {
    
    private int idExame;
    private String nomeAnimal;
    private String exameDescricao;
    private String dataExame;
    private String veterinarioExame;

    public Exame() {
    }

    public int getIdExame() {
        return idExame;
    }

    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getExameDescricao() {
        return exameDescricao;
    }

    public void setExameDescricao(String exameDescricao) {
        this.exameDescricao = exameDescricao;
    }

    public String getDataExame() {
        return dataExame;
    }

    public void setDataExame(String dataExame) {
        this.dataExame = dataExame;
    }

    public String getVeterinarioExame() {
        return veterinarioExame;
    }

    public void setVeterinarioExame(String veterinarioExame) {
        this.veterinarioExame = veterinarioExame;
    }

    
}
