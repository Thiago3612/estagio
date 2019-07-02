package DAO;

import java.util.List;

public class Correntista {
    private String conta;
    private String senha;
    private String nome;
    private String perfil;
    private float saldo;
    private List<Transacao> transacao;
    

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransacao() {
        return transacao;
    }

    public void setTransacao(List<Transacao> transacao) {
        this.transacao = transacao;
    }
    
}
