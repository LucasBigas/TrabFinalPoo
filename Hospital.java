package GH;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> consultas;

    public Hospital() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.consultas = new ArrayList<>();
    }

    public void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void adicionarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void marcarConsulta(Consulta consulta) {
        consultas.add(consulta);
        consulta.getMedico().adicionarConsulta(consulta);
    }

    public List<Consulta> buscarConsultasPorPaciente(Paciente paciente) {
        List<Consulta> consultasDoPaciente = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(paciente)) {
                consultasDoPaciente.add(consulta);
            }
        }
        return consultasDoPaciente;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void listarPacientes() {
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    public void listarMedicos() {
        for (Medico medico : medicos) {
            System.out.println(medico);
        }
    }

    public void listarConsultas() {
        for (Consulta consulta : consultas) {
            System.out.println(consulta);
        }
    }

    public void listarAgendaMedico(String crm) {
        Medico medico = null;
        for (Medico m : medicos) {
            if (m.getCrm().equals(crm)) {
                medico = m;
                break;
            }
        }
        if (medico != null) {
            medico.listarAgenda();
        } else {
            System.out.println("Médico não encontrado!");
        }
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sistema de Gestão Hospitalar");
            System.out.println("1. Adicionar Paciente");
            System.out.println("2. Adicionar Médico");
            System.out.println("3. Marcar Consulta");
            System.out.println("4. Listar Pacientes");
            System.out.println("5. Listar Médicos");
            System.out.println("6. Listar Consultas");
            System.out.println("7. Listar Agenda do Médico");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Paciente: ");
                    String nomePaciente = scanner.nextLine();
                    System.out.print("Idade do Paciente: ");
                    int idadePaciente = scanner.nextInt();
                    scanner.nextLine();  // Consumir o newline após nextInt()
                    System.out.print("CPF do Paciente (xxx.xxx.xxx-xx): ");
                    String cpfPaciente = scanner.nextLine();

                    if (idadePaciente < 18) {
                        System.out.print("Nome do Responsável: ");
                        String nomeResponsavel = scanner.nextLine();
                        System.out.print("CPF do Responsável (xxx.xxx.xxx-xx): ");
                        String cpfResponsavel = scanner.nextLine();

                        Paciente paciente = new Paciente(nomePaciente, idadePaciente, cpfPaciente, nomeResponsavel, cpfResponsavel);
                        hospital.adicionarPaciente(paciente);
                    } else {
                        Paciente paciente = new Paciente(nomePaciente, idadePaciente, cpfPaciente);
                        hospital.adicionarPaciente(paciente);
                    }
                    break;
                case 2:
                    System.out.print("Nome do Médico: ");
                    String nomeMedico = scanner.nextLine();
                    System.out.println("Especialidades disponíveis:");
                    for (Especialidade esp : Especialidade.values()) {
                        System.out.println(esp.ordinal() + ". " + esp.getNomeFormatado());
                    }
                    System.out.print("Escolha a especialidade (0-3): ");
                    int escolhaEspecialidade = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    if (escolhaEspecialidade < 0 || escolhaEspecialidade >= Especialidade.values().length) {
                        System.out.println("Opção inválida!");
                        break;
                    }
                    Especialidade especialidadeMedico = Especialidade.values()[escolhaEspecialidade];
                    System.out.print("CRM do Médico: ");
                    String crmMedico = scanner.nextLine();
                    Medico medico = new Medico(nomeMedico, especialidadeMedico, crmMedico);
                    hospital.adicionarMedico(medico);
                    break;
                case 3:
                    System.out.print("CPF do Paciente (xxx.xxx.xxx-xx): ");
                    String cpf = scanner.nextLine();
                    Paciente pacienteConsulta = null;
                    for (Paciente p : hospital.pacientes) {
                        if (p.getCpf().equals(cpf)) {
                            pacienteConsulta = p;
                            break;
                        }
                    }
                    if (pacienteConsulta == null) {
                        System.out.println("Paciente não encontrado! Cadastre o paciente.");
                        System.out.print("Nome do Paciente: ");
                        String nome = scanner.nextLine();
                        System.out.print("Idade do Paciente: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        pacienteConsulta = new Paciente(nome, idade, cpf);
                        hospital.adicionarPaciente(pacienteConsulta);
                    }

                    System.out.println("Médicos disponíveis para a consulta:");
                    for (Medico m : hospital.medicos) {
                        System.out.println("CRM: " + m.getCrm() + ", Nome: " + m.getNome() + ", Especialidade: " + m.getEspecialidade().getNomeFormatado());
                    }
                    System.out.print("CRM do Médico: ");
                    String crmConsulta = scanner.nextLine();
                    Medico medicoConsulta = null;
                    for (Medico m : hospital.medicos) {
                        if (m.getCrm().equals(crmConsulta)) {
                            medicoConsulta = m;
                            break;
                        }
                    }
                    if (medicoConsulta == null) {
                        System.out.println("Médico não encontrado!");
                        break;
                    }

                    System.out.print("Data e Hora da Consulta (dd/MM/yyyy HH:mm): ");
                    String dataHoraConsultaStr = scanner.nextLine();
                    LocalDateTime dataHoraConsulta;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        dataHoraConsulta = LocalDateTime.parse(dataHoraConsultaStr, formatter);
                    } catch (Exception e) {
                        System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy HH:mm.");
                        break;
                    }

                    Consulta novaConsulta = new Consulta(pacienteConsulta, medicoConsulta, dataHoraConsulta);
                    hospital.marcarConsulta(novaConsulta);
                    System.out.println("Consulta marcada com sucesso!");
                    break;
                case 4:
                    System.out.println("Lista de Pacientes:");
                    hospital.listarPacientes();
                    break;
                case 5:
                    System.out.println("Lista de Médicos:");
                    hospital.listarMedicos();
                    break;
                case 6:
                    System.out.println("Lista de Consultas:");
                    hospital.listarConsultas();
                    break;
                case 7:
                    System.out.print("CRM do Médico: ");
                    String crmAgenda = scanner.nextLine();
                    hospital.listarAgendaMedico(crmAgenda);
                    break;
                case 8:
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida! Escolha novamente.");
                    break;
            }
        }
    }
}
