import java.util.*;
import java.io.*;

public class ArqHuff implements Serializable{
    private BitSet bs;
    private TabelaBinaria tabConversao;

    public ArqHuff(BitSet bs, TabelaBinaria tabConversao){
        this.bs = bs;
        this.tabConversao = tabConversao;
    }

    public BitSet getBitSet(){return bs;}
    public TabelaBinaria getTabConversao(){return tabConversao;}

    public void setBitSet(BitSet bs){this.bs = bs;}
    public void setTabConversao(TabelaBinaria tabConversao){this.tabConversao = tabConversao;}

}