package lucasWilliamGomes.associacao.exceptions;

public class FrequenciaJaRegistrada extends Exception {
    public FrequenciaJaRegistrada () {
        super("Frequência já registrada");
    }
}
