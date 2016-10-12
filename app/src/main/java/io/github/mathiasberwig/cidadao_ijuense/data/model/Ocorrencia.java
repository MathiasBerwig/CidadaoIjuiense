package io.github.mathiasberwig.cidadao_ijuense.data.model;

import android.net.Uri;

public class Ocorrencia {

    private String tipo;
    private String titulo;
    private String local;
    private String descricao;
    private String nome;
    private String telefone;
    private String email;
    private boolean feedback;
    private Uri imagem;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFeedback() {
        return feedback;
    }

    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Uri getImagem() {
        return imagem;
    }

    public void setImagem(Uri imagem) {
        this.imagem = imagem;
    }
}
