/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nicolas
 */

public class Jogo {
    private int id;
    private String nome;
    private int ano;
    private String plataforma;
    private double valorAluguel;
    private int diasAluguelPadrao;
    private int quantidadeEstoque;

    public Jogo(int id, String nome, int ano, String plataforma,
                double valorAluguel, int diasAluguelPadrao, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.plataforma = plataforma;
        this.valorAluguel = valorAluguel;
        this.diasAluguelPadrao = diasAluguelPadrao;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getAno() { return ano; }
    public String getPlataforma() { return plataforma; }
    public double getValorAluguel() { return valorAluguel; }
    public int getDiasAluguelPadrao() { return diasAluguelPadrao; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    public void setNome(String nome) { this.nome = nome; }
    public void setAno(int ano) { this.ano = ano; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    public void setValorAluguel(double valorAluguel) { this.valorAluguel = valorAluguel; }
    public void setDiasAluguelPadrao(int d) { this.diasAluguelPadrao = d; }
    public void setQuantidadeEstoque(int q) { this.quantidadeEstoque = q; }

    @Override
    public String toString() { return nome; }
}