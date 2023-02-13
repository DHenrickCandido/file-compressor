import java.util.BitSet;

public class AppHuff {
    public static void main(String[] args) throws Exception{
        
        System.out.println("Vamos compactar e descompactar! ");
        System.out.println("Lembre-se: O  arquivo a ser compactado eh o [entrada.txt] e ele deve ficar na mesma pasta do resto dos arquivos! ");
        Huff.compactar("entrada.txt");
        Huff.descompactar("compactado.huff");
        


    
    }
    

    
}
