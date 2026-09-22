package dao;

import model.Consulta;
import utill.ConectaPostgre;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private ConectaPostgre db;

    public ConsultaDAO(ConectaPostgre db) {
        this.db = db;
    }


    public boolean inserir(Consulta c) {
        try {

            String dataHoraSQL = c.getData_consulta() + " " + c.getHorario();


            String sql = String.format(

                    "INSERT INTO clinica.consulta (cod_paciente, cod_medico, data_hora_consulta, observacao_paciente) " +

                            "VALUES (%d, %d, '%s', '%s')",


                    c.getCod_paciente(),
                    c.getCod_medico(),
                    dataHoraSQL,
                    c.getObservacao_paciente().replace("'", "''")
            );

            db.stmt.executeUpdate(sql);
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao inserir consulta: " + e.getMessage());
            return false;
        }
    }


    public boolean remover(int id) {
        try {
            String sql = "DELETE FROM clinica.consulta WHERE cod_consulta = " + id;

            return db.stmt.executeUpdate(sql) > 0;

        } catch (Exception e) {
            System.out.println("Erro ao remover consulta: " + e.getMessage());
            return false;
        }
    }


    // JOIN

    public List<String> listarDetalhada() {

        List<String> lista = new ArrayList<>();

        try {
            String sql =
                    "SELECT c.cod_consulta, " +
                            "p.nome_paciente AS paciente, " +
                            "m.nome_medico AS medico, " +
                            "c.data_hora_consulta, " +
                            "c.observacao_paciente " +
                            "FROM clinica.consulta c " +
                            "JOIN clinica.paciente p ON c.cod_paciente = p.cod_paciente " +
                            "JOIN clinica.medico m ON c.cod_medico = m.cod_medico " +
                            "ORDER BY c.cod_consulta";

            db.rs = db.stmt.executeQuery(sql);

            while (db.rs.next()) {
                String dataHoraCompleta = db.rs.getString("data_hora_consulta");




                String[] partes = dataHoraCompleta.split(" ");
                String data = partes[0]; // AAAA-MM-DD
                String hora = partes[1].substring(0, 5);

                String linha = String.format(
                        "ID: %d | Paciente: %s | Médico: %s | Data: %s | Hora: %s | Obs: %s",
                        db.rs.getInt("cod_consulta"),
                        db.rs.getString("paciente"),
                        db.rs.getString("medico"),
                        data,
                        hora,
                        db.rs.getString("observacao_paciente")
                );

                lista.add(linha);
            }

            db.rs.close();

        } catch (Exception e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }

        return lista;
    }


    // VIEW

    public List<String> listarViaView() {
        List<String> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM clinica.vw_consultas_completas ORDER BY cod_consulta";

            db.rs = db.stmt.executeQuery(sql);

            while (db.rs.next()) {

                String dataHoraCompleta = db.rs.getString("data_hora_consulta");
                String[] partes = dataHoraCompleta.split(" ");
                String data = partes[0];
                String hora = partes[1].substring(0, 5);

                String linha = String.format(
                        "ID: %d | Paciente: %s | Médico: %s | Data: %s | Hora: %s | Obs: %s",
                        db.rs.getInt("cod_consulta"),
                        db.rs.getString("nome_paciente"),
                        db.rs.getString("nome_medico"),
                        data,
                        hora,
                        db.rs.getString("observacao_paciente")
                );

                lista.add(linha);
            }
            db.rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar via VIEW: " + e.getMessage());
        }

        return lista;
    }
}


