package model;

public class Consulta {

    private int id_consulta;
    private int cod_paciente;
    private int cod_medico;
    private String data_consulta;
    private String horario;
    private String observacao_paciente;

    public Consulta() {}

    public Consulta(int cod_paciente, int cod_medico, String data_consulta, String horario, String observacao_paciente) {
        this.cod_paciente = cod_paciente;
        this.cod_medico = cod_medico;
        this.data_consulta = data_consulta;
        this.horario = horario;
        this.observacao_paciente = observacao_paciente;
    }

    public int getId_consulta() { return id_consulta; }
    public void setId_consulta(int id_consulta) { this.id_consulta = id_consulta; }

    public int getCod_paciente() { return cod_paciente; }
    public void setCod_paciente(int cod_paciente) { this.cod_paciente = cod_paciente; }

    public int getCod_medico() { return cod_medico; }
    public void setCod_medico(int cod_medico) { this.cod_medico = cod_medico; }

    public String getData_consulta() { return data_consulta; }
    public void setData_consulta(String data_consulta) { this.data_consulta = data_consulta; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getObservacao_paciente() { return observacao_paciente; }
    public void setObservacao_paciente(String observacao_paciente) { this.observacao_paciente = observacao_paciente; }
}
