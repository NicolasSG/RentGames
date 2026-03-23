/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nicolas
 */

public class Transacao {
    public enum Tipo { ENTRADA, SAIDA }

    private int id;
    private String descricao;
    private Tipo tipo;
    private double valor;
    private String data;

    public Transacao(int id, String descricao, Tipo tipo, double valor, String data) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public Tipo getTipo() { return tipo; }
    public double getValor() { return valor; }
    public String getData() { return data; }
}