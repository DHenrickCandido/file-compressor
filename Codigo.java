import java.util.*;
import java.io.*;

public class Codigo implements Serializable{
    private char Caracter;
    private String codigo;
    
    public Codigo(char c, String cod){
        this.Caracter = c;
        this.codigo = cod;
    }

    public void setCaracter(char C){ this.Caracter = C;}
    public char getCaracter() { return this.Caracter;}

    public void setCodigo(String cod){ this.codigo = cod;}
    public String getCodigo() { return this.codigo;}

    
    public String toString() {
        return getCaracter() + " [" + getCodigo() + "]";
    }
}
