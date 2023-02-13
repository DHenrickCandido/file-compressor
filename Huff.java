
import java.util.*;
import java.io.*; 
public class Huff implements Serializable{
    public static void compactar(String arqEntrada) throws Exception{
        
        File arqIn = new File(arqEntrada);
        Scanner reader = new Scanner(arqIn);
        String meuTexto = "";
        while (reader.hasNextLine()) 
            meuTexto = reader.nextLine();
        reader.close();

        TabelaHuff tabela = geraTabela(meuTexto);
        Arvore arvHuff = Huff.geraArvore(tabela);
                
        TabelaBinaria tabConversao = Huff.geraTabelaConversao(arvHuff);
       
        BitSet bs = geraBitSet(geraStrBin(tabConversao,meuTexto));
        
        ArqHuff arq = new ArqHuff(bs,tabConversao);
        FileOutputStream fileOut = new FileOutputStream("./compactado.huff");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(arq);
        out.close();
        fileOut.close();
        System.out.printf("Aquivo compactado salvo em ./compactado.huff");
        
        
    }
    public static void descompactar(String ArqCompactado) throws Exception{
        ArqHuff arq = null;

        FileInputStream fileIn = new FileInputStream(ArqCompactado);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        arq = (ArqHuff) in.readObject();
        in.close();
        fileIn.close();
        FileWriter arqOut = new FileWriter("saida.txt");
        arqOut.write(geraTexto(arq));
        arqOut.close();
        System.out.println("Arquivo descompactado em ./saida.txt");
    }
    
public static TabelaHuff geraTabela(String texto){
    List<RegistroOcorrencia> listaOcorrencia = new ArrayList<RegistroOcorrencia>();
    List<Character> listaCharacter = new ArrayList<Character>();
    // métodos de lista
        for (int i = 0; i < texto.length(); i++)
        {
            
            if (!listaCharacter.contains(texto.charAt(i))) {  // CONMTAINS
                int vezes = 0; 
                RegistroOcorrencia ocor = new RegistroOcorrencia(texto.charAt(i),vezes);
                for (int x = 0; x < texto.length(); x++) {
                    if(texto.charAt(x) == texto.charAt(i)){
                        vezes++;
                        texto.replace(Character.toString(texto.charAt(i)),"");
                    }
                        
                }
                ocor.setOcorrencia(vezes);
                listaOcorrencia.add(ocor);
                listaCharacter.add(texto.charAt(i));
                
            }
        }
      
        Collections.sort(listaOcorrencia);
        
        return new TabelaHuff(listaOcorrencia); 
    }

    private static Arvore geraArvore(TabelaHuff Tab){
        
        for (int h=0; h<Tab.getListaOcorrencia().size(); h++)
            System.out.println(Tab.getListaOcorrencia().get(h).getCaracter() + " " +
                            Tab.getListaOcorrencia().get(h).getOcorrencia());
        
        
        List<No> listaReserva = geraListaNo(Tab.getListaOcorrencia());

        
        while ( listaReserva.size() > 1 ){
            int soma = listaReserva.get(0).getInfo().getOcorrencia() + 
                       listaReserva.get(1).getInfo().getOcorrencia();

            No no = new No(new RegistroOcorrencia('\0',soma));
            no.setEsq(listaReserva.get(0));
            no.setDir(listaReserva.get(1));

            listaReserva.remove(1);
            listaReserva.remove(0);
            listaReserva.add(no);

            Collections.sort(listaReserva);
        }
        Arvore Arv = new Arvore();
        Arv.incluir(listaReserva.get(0));
        return Arv;
    }

    private static List<No> geraListaNo (List<RegistroOcorrencia> lista){
        List<No> Ret = new ArrayList<>();
        while (lista.size() > 0){
            Ret.add(new No(lista.get(0)));
            lista.remove(0);
        }
        return Ret;
    } 
    


    public static void geraCodigo(No Raiz, String codificacao, TabelaBinaria tab){

        if (Raiz==null) return;
        if (Raiz.getInfo().getCaracter()!='\0'){
            tab.addCodigo(Raiz, codificacao);
            geraCodigo(Raiz.getEsq(), "",tab);
        }
        else 
        {
            geraCodigo(Raiz.getEsq(), codificacao+"0", tab);
            geraCodigo(Raiz.getDir(), codificacao+"1", tab);
        }

        
        
    }

    public static String geraStrBin(TabelaBinaria tab, String meuTexto){
        String strBin = "";
        for (int i = 0; i < meuTexto.length();i++){
            for(int x = 0; x < tab.getListaCodigo().size();x++){
                if (tab.getListaCodigo().get(x).getCaracter() == meuTexto.charAt(i)){
                    strBin += tab.getListaCodigo().get(x).getCodigo(); 
                }
            }
        } 
        return strBin; 
        
    }

    public static BitSet geraBitSet(String strBinario){
        BitSet bs = new BitSet();

        for (int i=0; i<strBinario.length(); i++)
            if (strBinario.charAt(i)=='1')
                bs.set(i);

        return bs;        
    }

    public static String geraTexto(ArqHuff arq){
        String bin = "";
        String textoRet = "";
        for (int i = 0; i<arq.getBitSet().length();i++){
            bin += (arq.getBitSet().get(i))?"1":"0";
            for(int x = 0; x<arq.getTabConversao().getListaCodigo().size();x++){
                if (bin.equals(arq.getTabConversao().getListaCodigo().get(x).getCodigo())){
                    textoRet += arq.getTabConversao().getListaCodigo().get(x).getCaracter();
                    bin = "";
                }
            }   
        }
        
        return textoRet;
    }

    private static TabelaBinaria geraTabelaConversao(Arvore A){
        
        List<Codigo> listaCodigo = new ArrayList<Codigo>();
        TabelaBinaria tab = new TabelaBinaria(listaCodigo);
        geraCodigo(A.getRaiz(), "",tab);

        for (int h=0; h<tab.getListaCodigo().size(); h++)
            System.out.println(tab.getListaCodigo().get(h).getCaracter() + " " +
                            tab.getListaCodigo().get(h).getCodigo());

       return tab;
    }
}
