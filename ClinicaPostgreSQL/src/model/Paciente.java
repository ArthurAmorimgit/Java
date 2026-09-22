package model;

public class Paciente {
    private Integer cod_paciente;
    private String nome;
    private String cpf;
    private String dataNascimento; // YYYY-MM-DD
    private String telefone;
    private String endereco;

    public Integer getCod_paciente() { return cod_paciente; }
    public void setCod_paciente(Integer cod_paciente) { this.cod_paciente = cod_paciente; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
