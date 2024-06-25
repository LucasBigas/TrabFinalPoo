package GH;

public class Paciente {
    private String nome;
    private int idade;
    private String cpf;
    private String nomeResponsavel;
    private String cpfResponsavel;

    public Paciente(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public Paciente(String nome, int idade, String cpf, String nomeResponsavel, String cpfResponsavel) {
        this(nome, idade, cpf);
        this.nomeResponsavel = nomeResponsavel;
        this.cpfResponsavel = cpfResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paciente{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cpf='" + formatarCPF(cpf) + '\'');

        if (idade < 18) {
            sb.append(", nomeResponsavel='" + nomeResponsavel + '\'')
                    .append(", cpfResponsavel='" + formatarCPF(cpfResponsavel) + '\'');
        }

        sb.append('}');
        return sb.toString();
    }

    private String formatarCPF(String cpf) {
        return cpf.substring(0, 3) + "." + cpf.substring(4, 7) + "." + cpf.substring(8, 11) + "-" + cpf.substring(12,14);
    }
}
