
import java.util.*;

public class TabelaHuff {
    //private No Inicio;
    private char[] letras;

    private List<RegistroOcorrencia> listaOcorrencia = new ArrayList<RegistroOcorrencia>();
    // métodos de lista
    
    public TabelaHuff(List<RegistroOcorrencia> L){
        listaOcorrencia = L;
    }
    public void atualizaTabela(List<RegistroOcorrencia> lista ){
        this.listaOcorrencia = lista;
    }
    public List<RegistroOcorrencia> getListaOcorrencia() {
        return this.listaOcorrencia;
    }

    
}
