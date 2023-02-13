
public class No implements Comparable{
        
     private RegistroOcorrencia Info;
     
     private No Esq, Dir;   // Arvore
     private No Ant, Prox;  // Lista Dupla
     
     public No(RegistroOcorrencia I){
         this.Info = I;
        
         this.Esq = this.Dir = null;
         this.Ant = this.Prox = null;
     }

     public void setInfo(RegistroOcorrencia I){ this.Info = I;}
   
     public void setDir(No D){ this.Dir = D;}
     public void setEsq(No E){ this.Esq = E;}
     public void setAnt(No A){ this.Ant = A;}
     public void setProx(No P){ this.Prox = P;}
        
     public RegistroOcorrencia getInfo() { return this.Info;}
   
     public No  getDir() { return this.Dir;}
     public No  getEsq() { return this.Esq;}
     public No  getAnt() { return this.Ant;}
     public No  getProx() { return this.Prox;}
     public int compareTo(Object outro){
         
         return this.Info.compareTo(((No)outro).getInfo());

     }
     
     public String toString(){
         return ""+getInfo();
     }

}
