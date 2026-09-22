package model;

public class Medico {
    private Integer cod_medico;
    private String nome;
    private String crm;
    private Integer cod_especialidade;

    public Integer getCod_medico() { return cod_medico; }
    public void setCod_medico(Integer cod_medico) { this.cod_medico = cod_medico; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public Integer getCod_especialidade() { return cod_especialidade; }
    public void setCod_especialidade(Integer cod_especialidade) { this.cod_especialidade = cod_especialidade; }
}
