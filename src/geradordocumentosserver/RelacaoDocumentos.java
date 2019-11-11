package geradordocumentosserver;

import java.util.HashMap;
import java.util.Map;

public class RelacaoDocumentos {
    
    private static Map<Integer,GeradorDigito> mapper = new HashMap<Integer, GeradorDigito>();
    
    static{
        mapper.put(1,new GeradorCpf());
        mapper.put(2, new GeradorCnpj());
    }
    
    public GeradorDigito getInstance(Integer cod){
        return mapper.get(cod);
    }
}
