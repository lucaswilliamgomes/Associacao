package lucasWilliamGomes.associacao.exceptions;

public class TaxaJaExistente extends Exception {
    public TaxaJaExistente () {
        super("Taxa já existente");
    }
}
