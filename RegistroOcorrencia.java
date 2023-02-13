
public class RegistroOcorrencia implements Comparable {
    private char Caracter;
    private int Ocorrencia;
    
    public RegistroOcorrencia(char c, int o){
        this.Caracter = c;
        this.Ocorrencia = o;
    }
    public RegistroOcorrencia(int o){
        this('\0',o);   // "barra zero"
    }
    public int compareOcorrencia(int ocorrencia) {
        if (this.Ocorrencia > ocorrencia)
            return 1;
        if (this.Ocorrencia < ocorrencia)
            return -1;
        return 0;
    } 
    public void setCaracter(char C){ this.Caracter = C;}
    public char getCaracter() { return this.Caracter;}

    public void setOcorrencia(int O){ this.Ocorrencia = O;}
    public int getOcorrencia() { return this.Ocorrencia;}
    public int compareTo(Object outro){

        return compareOcorrencia(((RegistroOcorrencia)outro).getOcorrencia());
    }
    
    public String toString() {
        return getCaracter() + " [" + getOcorrencia() + "]";
    }
}
