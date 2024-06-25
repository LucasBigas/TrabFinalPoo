package GH;

public enum Especialidade {
    CARDIOLOGIA("Cardiologia"),
    PEDIATRIA("Pediatria"),
    ORTOPEDIA("Ortopedia"),
    GINECOLOGIA("Ginecologia");

    private final String nomeFormatado;

    Especialidade(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }
}
