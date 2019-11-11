package geradordocumentosserver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GeradorDocumentosServer extends UnicastRemoteObject implements GeradorInterface {


    public GeradorDocumentosServer() throws RemoteException{
    }

    @Override
    public String executa(String doc , int flag) throws RemoteException {
        
            System.out.println("recebeu  :" +  doc);     
        
            return new Gerador(flag).getGerador().gerar(doc);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            
            Registry r = LocateRegistry.createRegistry(1429);          
            GeradorDocumentosServer servidor = new GeradorDocumentosServer();
            r.rebind("comunicacao", servidor);
            System.out.println("Servidor pronto "+r.lookup("comunicacao"));     
            
        }catch(NotBoundException | RemoteException e){
           
            e.printStackTrace();
        
        }
    }
    
}
