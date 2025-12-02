package view;

import utill.ConectaPostgre;
import dao.*;
import model.*;

import java.util.List;
import java.util.Scanner;

public class ClinicaMain {
    private final ConectaPostgre db;
    private final PacienteDAO pacienteDAO;
    private final MedicoDAO medicoDAO;
    private final EspecialidadeDAO especialidadeDAO;
    private final ConvenioDAO convenioDAO;
    private final RecepcionistaDAO recepcionistaDAO;
    private final ConsultaDAO consultaDAO;
    private final Scanner sc = new Scanner(System.in);

    public ClinicaMain(){
        db = new ConectaPostgre();

        db.Conectar("jdbc:postgresql://localhost:5432/ClinicaMedica","postgres", "tttt4020");

        pacienteDAO = new PacienteDAO(db);
        medicoDAO = new MedicoDAO(db);
        especialidadeDAO = new EspecialidadeDAO(db);
        convenioDAO = new ConvenioDAO(db);
        recepcionistaDAO = new RecepcionistaDAO(db);
        consultaDAO = new ConsultaDAO(db);
    }

    public void menuPrincipal(){
        int op;

        do {
            System.out.println("\n---Clinica---");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Medicos");
            System.out.println("3 - Consultas");
            System.out.println("4 - Especialidade");
            System.out.println("5 - Convenio");
            System.out.println("6 - Recepcionista");
            System.out.println("0 - Sair");
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 1 -> menuPaciente();
                case 2 -> menuMedico();
                case 3 -> menuConsulta();
                case 4 -> menuEspecialidade();
                case 5 -> menuConvenio();
                case 6 -> menuRecepcionista();
                case  0 -> {
                    db.Desconectar();
                    System.out.println("Conexão encerrada");
                }
                default -> System.out.println("Opeção invaluda");
                }
            }while (op != 0);
        }

        public void menuPaciente(){
        int op;

        do {
            System.out.println("\n-- PACIENTE --");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar por id");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Listar todos");
            System.out.println("6 - Total consultas (function)");
            System.out.println("7 - Atualizar telefone (procedure)");
            System.out.println("0 - Voltar");
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 1 ->{
                    Paciente p = new Paciente();
                    System.out.println("Nome: ");p.setNome(sc.nextLine());
                    System.out.println("CPF: ");p.setCpf(sc.nextLine());
                    System.out.println("Nascimento ");p.setDataNascimento(sc.nextLine());
                    System.out.println("Telefone");p.setTelefone(sc.nextLine());
                    System.out.println("Endereço: ");p.setEndereco(sc.nextLine());
                    System.out.println(pacienteDAO.inserir(p) ? "Paciente inserido" : "erro");
                }
                case 2 -> {
                    System.out.println("ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Paciente p = pacienteDAO.buscaporID(id);
                    if (p != null) System.out.println("Paciente: " + p.getNome() + " | CPF: " + p.getCpf());
                    else System.out.println("Não encontrado.");
                }
                case 3 ->{
                    System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                    Paciente p = pacienteDAO.buscaporID(id);
                    if (p == null) { System.out.println("Não encontrado"); break; }
                    System.out.print("Nome ("+p.getNome()+"): "); String n = sc.nextLine(); if(!n.isEmpty()) p.setNome(n);
                    System.out.print("CPF ("+p.getCpf()+"): "); String c = sc.nextLine(); if(!c.isEmpty()) p.setCpf(c);
                    System.out.print("Nascimento ("+p.getDataNascimento()+"): "); String d = sc.nextLine(); if(!d.isEmpty()) p.setDataNascimento(d);
                    System.out.print("Telefone ("+p.getTelefone()+"): "); String t = sc.nextLine(); if(!t.isEmpty()) p.setTelefone(t);
                    System.out.print("Endereço ("+p.getEndereco()+"): "); String e = sc.nextLine(); if(!e.isEmpty()) p.setEndereco(e);
                    System.out.println(pacienteDAO.atualizar(p) ? "Atualizado." : "Erro.");
                }
                case 4 ->{
                    System.out.println("ID: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.println(pacienteDAO.excluir(id) ? "Excluido" : "erro");
                }
                case 5 ->{
                    List<Paciente> lista = pacienteDAO.listar();
                    lista.forEach(px -> System.out.println(px.getNome()));
                }
                case 6 ->{
                    System.out.println("ID paciente ");int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Totoal consultas: " + pacienteDAO.totalConsultas(id));
                }
                case 7 ->{
                    System.out.println("ID paciente: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Novo telefone "); String f = sc.nextLine();
                    System.out.println(pacienteDAO.atualizarTelefoneProcedure(id, f) ? "Telefone atualizado" : "Erro");
                }
                case 0 ->{

                }
                default -> System.out.println("Invalido");
            }
        }while (op != 0);
    }
    private void menuMedico() {
        int op;
        do {
            System.out.println("\n-- MÉDICO --");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Consultar por id");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Listar todos");
            System.out.println("6 - Listar médicos com especialidade (JOIN)");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> {
                    Medico m = new Medico();
                    System.out.print("Nome: "); m.setNome(sc.nextLine());
                    System.out.print("CRM: "); m.setCrm(sc.nextLine());
                    System.out.print("cod_especialidade (id): "); String esp = sc.nextLine();
                    m.setCod_especialidade(esp.isEmpty() ? null : Integer.parseInt(esp));
                    System.out.println(medicoDAO.inserir(m) ? "Inserido." : "Erro.");
                }
                case 2 -> {
                    System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                    Medico m = medicoDAO.buscaporID(id);
                    if (m != null) System.out.println("Médico: " + m.getNome() + " | CRM: " + m.getCrm());
                    else System.out.println("Não encontrado.");
                }
                case 3 -> {
                    System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                    Medico m = medicoDAO.buscaporID(id);
                    if (m == null) { System.out.println("Não encontrado"); break; }
                    System.out.print("Nome ("+m.getNome()+"): "); String n = sc.nextLine(); if(!n.isEmpty()) m.setNome(n);
                    System.out.print("CRM ("+m.getCrm()+"): "); String crm = sc.nextLine(); if(!crm.isEmpty()) m.setCrm(crm);
                    System.out.print("cod_especialidade ("+m.getCod_especialidade()+"): "); String esp2 = sc.nextLine(); if(!esp2.isEmpty()) m.setCod_especialidade(Integer.parseInt(esp2));
                    System.out.println(medicoDAO.atualizar(m) ? "Atualizado." : "Erro.");
                }
                case 4 -> {
                    System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.println(medicoDAO.excluir(id) ? "Excluído." : "Erro.");
                }
                case 5 -> {
                    List<Medico> lista = medicoDAO.listarTodos();
                    lista.forEach(mx -> System.out.println(mx.getCod_medico()+" - "+mx.getNome()+" - CRM:"+mx.getCrm()+" - Esp:"+mx.getCod_especialidade()));
                }
                case 6 -> {
                    List<String> list = medicoDAO.listarMedicosComEspecialidade();
                    list.forEach(System.out::println);
                }
                case 0 -> {}
                default -> System.out.println("Inválido.");
            }
        } while (op != 0);
    }
    private void menuConsulta() {
        int op;

        do {
            System.out.println("\n-- CONSULTA --");
            System.out.println("1 - Agendar consulta");
            System.out.println("2 - Excluir consulta");
            System.out.println("3 - Listar consultas detalhadas (JOIN)");
            System.out.println("4 - Listar consultas via VIEW");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {


                case 1 -> {
                    Consulta c = new Consulta();

                    System.out.print("cod_paciente: ");
                    c.setCod_paciente(Integer.parseInt(sc.nextLine()));

                    System.out.print("cod_medico: ");
                    c.setCod_medico(Integer.parseInt(sc.nextLine()));

                    System.out.println("Data da consulta (Formato AAAA-BB-CC");
                    c.setData_consulta(sc.nextLine());

                    System.out.println("Horario da COnsulta( FORMATO AA:BB");
                    c.setHorario(sc.nextLine());

                    System.out.print("Observação: ");
                    c.setObservacao_paciente(sc.nextLine());

                    boolean ok = consultaDAO.inserir(c);
                    System.out.println(ok ? "Consulta agendada." : "Erro ao agendar.");
                }


                case 2 -> {
                    System.out.print("ID da consulta: ");
                    int id = Integer.parseInt(sc.nextLine());

                    boolean ok = consultaDAO.remover(id);
                    System.out.println(ok ? "Consulta removida." : "Erro ao remover.");
                }


                case 3 -> {
                    System.out.println("\n--- LISTA DETALHADA DE CONSULTAS ---");
                    var lista = consultaDAO.listarDetalhada();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma consulta encontrada.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }


                case 4 -> {
                    System.out.println("\n--- CONSULTAS (VIEW consultas_view) ---");
                    var lista = consultaDAO.listarViaView();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma consulta encontrada.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }

                case 0 -> {
                    // voltar ao menu principal
                }

                default -> System.out.println("Opção inválida.");
            }

        } while (op != 0);
    }
    private void menuEspecialidade() {
        int op;
        do {
            System.out.println("\n-- ESPECIALIDADE --");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> {
                    Especialidade e = new Especialidade();
                    System.out.print("Nome: "); e.setNome_especialidade(sc.nextLine());
                    System.out.print("Descricao: "); e.setDescricao_especialidade(sc.nextLine());
                    System.out.println(especialidadeDAO.inserir(e) ? "Inserido." : "Erro.");
                }
                case 2 -> {
                    List<Especialidade> lista = especialidadeDAO.listarTodos();
                    lista.forEach(x -> System.out.println(x.getCod_especialidade()+" - "+x.getNome_especialidade()));
                }
                case 0 -> {}
                default -> System.out.println("Inválido.");
            }
        } while (op != 0);
    }

    private void menuConvenio() {
        int op;
        do {
            System.out.println("\n-- CONVÊNIO --");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> {
                    Convenio c = new Convenio();
                    System.out.print("Nome: "); c.setNome_convenio(sc.nextLine());
                    System.out.print("Cobertura: "); c.setCobertura_convenio(sc.nextLine());
                    System.out.print("Telefone: "); c.setTelefone_convenio(sc.nextLine());
                    System.out.println(convenioDAO.inserir(c) ? "Inserido." : "Erro.");
                }
                case 2 -> {
                    List<Convenio> lista = convenioDAO.listarTodos();
                    lista.forEach(x -> System.out.println(x.getCod_convenio()+" - "+x.getNome_convenio()));
                }
                case 0 -> {}
                default -> System.out.println("Inválido.");
            }
        } while (op != 0);
    }
    private void menuRecepcionista() {
        int op;
        do {
            System.out.println("\n-- RECEPCIONISTA --");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1 -> {
                    Recepcionista r = new Recepcionista();
                    System.out.print("Nome: "); r.setNome_recepcionista(sc.nextLine());
                    System.out.print("CPF: "); r.setCpf(sc.nextLine());
                    System.out.println(recepcionistaDAO.inserir(r) ? "Inserido." : "Erro.");
                }
                case 2 -> {
                    List<Recepcionista> lista = recepcionistaDAO.listarTodos();
                    lista.forEach(x -> System.out.println(x.getCod_recepcionista()+" - "+x.getNome_recepcionista()));
                }
                case 0 -> {}
                default -> System.out.println("Inválido.");
            }
        } while (op != 0);
    }

    public static void main(String[] args) {
        new ClinicaMain().menuPrincipal();
    }

}
