package lucasWilliamGomes.associacao.exceptions;

public class ReuniaoNaoExistente extends Exception {
    public ReuniaoNaoExistente () {
        super("Reunião já existente");
    }
}
