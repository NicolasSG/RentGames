/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nicolas
 */

public class Cliente {
    private int id;
    private String nomeCompleto;
    private String contato;
    private String cpf;
    private String endereco;

    public Cliente(int id, String nomeCompleto, String contato, String cpf, String endereco) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.contato = contato;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public int getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getContato() { return contato; }
    public String getCpf() { return cpf; }
    public String getEndereco() { return endereco; }

    public void setNomeCompleto(String n) { this.nomeCompleto = n; }
    public void setContato(String c) { this.contato = c; }
    public void setCpf(String c) { this.cpf = c; }
    public void setEndereco(String e) { this.endereco = e; }

    @Override
    public String toString() { return nomeCompleto; }
}