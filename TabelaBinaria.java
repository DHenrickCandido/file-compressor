import java.util.*;
import java.io.*;
public class TabelaBinaria implements Serializable {
    //private No Inicio;
    private char[] letras;

    private List<Codigo> listaCodigo = new ArrayList<Codigo>();
    // métodos de lista
    
    public TabelaBinaria(List<Codigo> L){
        listaCodigo = L;
    }
    public void atualizaTabela(List<Codigo> lista ){
        this.listaCodigo = lista;
    }
    public List<Codigo> getListaCodigo() {
        return this.listaCodigo;
    }
    public void addCodigo(No no, String cod){
        Codigo code = new Codigo(no.getInfo().getCaracter(),cod);
        this.listaCodigo.add(code);
    }
    
}

