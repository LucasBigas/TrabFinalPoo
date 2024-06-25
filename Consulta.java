package GH;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        String cpfFormatado = formatarCPF(paciente.getCpf());
        return "Consulta{" +
                "paciente=" + paciente.getNome() +
                ", cpf='" + cpfFormatado + '\'' +
                ", medico=" + medico.getNome() +
                ", crm='" + medico.getCrm() + '\'' +
                ", especialidade=" + medico.getEspecialidade().getNomeFormatado() +
                ", dataHora=" + dataHora +
                '}';
    }

    private String formatarCPF(String cpf) {
        return cpf.substring(0, 3) + "." + cpf.substring(4, 7) + "." + cpf.substring(8, 11) + "-" + cpf.substring(12,14);
    }
}
