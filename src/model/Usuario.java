/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nicolas
 */

public class Usuario {
    public enum NivelPermissao { ADMIN, FUNCIONARIO }

    private int id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String contato;
    private String cpf;
    private String endereco;
    private NivelPermissao nivelPermissao;

    public Usuario(int id, String nomeCompleto, String email, String senha,
                   String contato, String cpf, String endereco, NivelPermissao nivelPermissao) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.contato = contato;
        this.cpf = cpf;
        this.endereco = endereco;
        this.nivelPermissao = nivelPermissao;
    }

    public int getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getContato() { return contato; }
    public String getCpf() { return cpf; }
    public String getEndereco() { return endereco; }
    public NivelPermissao getNivelPermissao() { return nivelPermissao; }

    public void setNomeCompleto(String n) { this.nomeCompleto = n; }
    public void setEmail(String e) { this.email = e; }
    public void setSenha(String s) { this.senha = s; }
    public void setContato(String c) { this.contato = c; }
    public void setCpf(String c) { this.cpf = c; }
    public void setEndereco(String e) { this.endereco = e; }
    public void setNivelPermissao(NivelPermissao n) { this.nivelPermissao = n; }

    @Override
    public String toString() { return nomeCompleto; }
}