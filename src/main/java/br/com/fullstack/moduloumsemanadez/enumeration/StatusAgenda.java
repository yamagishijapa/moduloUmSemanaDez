package br.com.fullstack.moduloumsemanadez.enumeration;

public enum StatusAgenda {
    AGENDADO(1,"Agendado"),
    CONFIRMADO(2,"Confirmado"),
    CANCELADO(3,"Cancelado");

    private final Integer valor;
    StatusAgenda(Integer valor, String descricao) {

        this.valor = valor;
    }

    public int getValue() {
        return valor;
    }

    public static StatusAgenda fromValue(int valor) {
        for (StatusAgenda status : StatusAgenda.values()) {
            if (status.getValue() == valor) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + valor);
    }
}
