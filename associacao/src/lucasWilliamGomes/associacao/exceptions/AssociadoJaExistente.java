package lucasWilliamGomes.associacao.exceptions;

public class AssociadoJaExistente extends Exception {
    public AssociadoJaExistente () {
        super("Associado já cadastrado");
    }
}
