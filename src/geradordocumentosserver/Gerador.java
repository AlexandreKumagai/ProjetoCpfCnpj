package geradordocumentosserver;

public class Gerador {
    
    private GeradorDigito gerador;
    
    Gerador(Integer codigo){
        gerador = new RelacaoDocumentos().getInstance(codigo);
    }
   
    public GeradorDigito getGerador(){
        return this.gerador;
    }
}
