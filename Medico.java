package GH;

import java.util.ArrayList;
import java.util.List;

public class Medico {
    private String nome;
    private Especialidade especialidade;
    private String crm;
    private List<Consulta> agenda;

    public Medico(String nome, Especialidade especialidade, String crm) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.agenda = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public List<Consulta> getAgenda() {
        return agenda;
    }

    public void adicionarConsulta(Consulta consulta) {
        agenda.add(consulta);
    }

    public void listarAgenda() {
        for (Consulta consulta : agenda) {
            System.out.println(consulta);
        }
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nome='" + nome + '\'' +
                ", especialidade=" + especialidade.getNomeFormatado() +
                ", crm='" + crm + '\'' +
                '}';
    }
}
