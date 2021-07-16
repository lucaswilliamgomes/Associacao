package lucasWilliamGomes.associacao.exceptions;

public class ReuniaoJaExistente extends Exception {
    public ReuniaoJaExistente () {
        super("Reunião já existente");
    }
}
