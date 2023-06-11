package model;


import java.sql.Date;

public class Guest {
    private Long id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String nacionalidade;
    private String telefone;
    private Long idReserva;

    public Guest() {
    }

    public Guest(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Long idReserva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.idReserva = idReserva;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return "Hospede{" + "id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataNascimento=" + dataNascimento + ", nacionalidade=" + nacionalidade + ", telefone=" + telefone + ", idReserva=" + idReserva + '}';
    }

}
