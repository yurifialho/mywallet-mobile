package br.com.secoti.mywallet.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "lancamento_table")
public class Lancamento {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private Date data;

    private Float valor;

    private String descricao;

    private int categoria;

    public Lancamento(Date data, Float valor, String descricao, int categoria) {
        this.data = data;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Float getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCategoria() {
        return categoria;
    }
}