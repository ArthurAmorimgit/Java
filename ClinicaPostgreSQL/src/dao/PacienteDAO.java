package dao;

import utill.ConectaPostgre;
import model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private ConectaPostgre banco;

    public PacienteDAO(ConectaPostgre banco) {
        this.banco = banco;
    }

    public boolean inserir(Paciente p) {
        try {
            String nome = p.getNome().replace("'", "''");
            String cpf = p.getCpf().replace("'", "''");
            String data = (p.getDataNascimento() == null ? "NULL" : ("'" + p.getDataNascimento() + "'"));
            String telefone = p.getTelefone() == null ? "" : p.getTelefone().replace("'", "''");
            String endereco = p.getEndereco() == null ? "" : p.getEndereco().replace("'", "''");

            String sql = "INSERT INTO clinica.paciente (nome_paciente, cpf_paciente, data_nascimento_paciente, telefone, endereco) " +
                    "VALUES ('" + nome + "', '" + cpf + "', " + data + ", '" + telefone + "', '" + endereco + "')";
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();
        try {
            String sql = "SELECT cod_paciente, nome_paciente, cpf_paciente, data_nascimento_paciente, telefone, endereco FROM clinica.paciente ORDER BY cod_paciente";
            banco.rs = banco.stmt.executeQuery(sql);
            while (banco.rs.next()) {
                Paciente p = new Paciente();
                p.setCod_paciente(banco.rs.getInt("cod_paciente"));
                p.setNome(banco.rs.getString("nome_paciente"));
                p.setCpf(banco.rs.getString("cpf_paciente"));
                p.setDataNascimento(banco.rs.getString("data_nascimento_paciente"));
                p.setTelefone(banco.rs.getString("telefone"));
                p.setEndereco(banco.rs.getString("endereco"));
                lista.add(p);
            }
            banco.rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean atualizar(Paciente p) {
        try {
            String nome = p.getNome().replace("'", "''");
            String cpf = p.getCpf().replace("'", "''");
            String data = (p.getDataNascimento() == null ? "NULL" : ("'" + p.getDataNascimento() + "'"));
            String telefone = p.getTelefone() == null ? "" : p.getTelefone().replace("'", "''");
            String endereco = p.getEndereco() == null ? "" : p.getEndereco().replace("'", "''");

            String sql = "UPDATE clinica.paciente SET nome_paciente='" + nome + "', cpf_paciente='" + cpf + "', data_nascimento_paciente=" + data +
                    ", telefone='" + telefone + "', endereco='" + endereco + "' WHERE cod_paciente=" + p.getCod_paciente();
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int cod) {
        try {
            String sql = "DELETE FROM clinica.paciente WHERE cod_paciente = " + cod;
            banco.stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Paciente buscaporID(int cod) {
        try {
            String sql = "SELECT cod_paciente, nome_paciente, cpf_paciente, data_nascimento_paciente, telefone, endereco FROM clinica.paciente WHERE cod_paciente = " + cod;
            banco.rs = banco.stmt.executeQuery(sql);
            if (banco.rs.next()) {
                Paciente p = new Paciente();
                p.setCod_paciente(banco.rs.getInt("cod_paciente"));
                p.setNome(banco.rs.getString("nome_paciente"));
                p.setCpf(banco.rs.getString("cpf_paciente"));
                p.setDataNascimento(banco.rs.getString("data_nascimento_paciente"));
                p.setTelefone(banco.rs.getString("telefone"));
                p.setEndereco(banco.rs.getString("endereco"));
                banco.rs.close();
                return p;
            }
            banco.rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // chama function usando stmt
    public int totalConsultas(int idPaciente) {
        try {
            String sql = "SELECT clinica.fn_total_consultas_paciente(" + idPaciente + ")";
            banco.rs = banco.stmt.executeQuery(sql);
            if (banco.rs.next()) {
                int total = banco.rs.getInt(1);
                banco.rs.close();
                return total;
            }
            banco.rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // chama procedure usando stmt
    public boolean atualizarTelefoneProcedure(int idPaciente, String novoTelefone) {
        try {
            String tel = novoTelefone.replace("'", "''");
            String sql = "CALL clinica.sp_atualizar_telefone(" + idPaciente + ", '" + tel + "')";
            banco.stmt.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
