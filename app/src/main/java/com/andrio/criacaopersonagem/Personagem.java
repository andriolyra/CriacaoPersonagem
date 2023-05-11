package com.andrio.criacaopersonagem;

public class Personagem {

    private int id;
    private String nome, raca, nivel;
    private Classe classe;

    public Personagem() {

    }


    public Personagem(String nome, String nivel, String raca, Classe classe) {
        this.nome = nome;
        this.nivel = nivel;
        this.raca = raca;
        this.classe = classe;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Classe getClasse() {
        return classe;
    }

    public void getClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Personagem: " + nome + "  ----  " +
                "Nível: " + nivel +  "\n" +
                "Raça: " + raca + "  ----  " +
                "Classe: " + classe;
    }

}