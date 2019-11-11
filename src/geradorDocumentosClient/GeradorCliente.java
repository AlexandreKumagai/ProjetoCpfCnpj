package geradorDocumentosClient;

import geradordocumentosserver.GeradorInterface;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeradorCliente {
    
    
    public static List<String> leArquivo() throws FileNotFoundException, IOException{
        FileReader arq = new FileReader("C:/Users/alexa/Documents/Projeto_SD_GeradorCPF/BASEPROJETO.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        List<String> lista = new ArrayList<String>();        
        String linha = lerArq.readLine();
        
        while (linha != null) {
          lista.add(linha.trim());
          linha = lerArq.readLine();   
        }
        arq.close();
        return lista;
    }
    public static void main(String[] args) throws NotBoundException, InterruptedException, IOException{
    	
         List<String> lista = leArquivo() ;
        GeradorInterface remoto = null;
        Registry reg=LocateRegistry.getRegistry("192.168.1.110",1429);
        remoto = (GeradorInterface) reg.lookup("comunicacao");
        
        for (Iterator<String> it = lista.iterator(); it.hasNext();) {
            String documento = it.next();
            if(documento.trim().length()  == 12 )
                System.out.println("CNPJ : " + formatarCNPJ(remoto.executa(documento,2)));
            else
                System.out.println("CPF: " + formatarCPF(remoto.executa(documento,1)));
        }
        
    }

    private static String formatarCNPJ(String s) {
            return s.substring(0, 2) + "." +  s.substring(2, 5) + "." +  s.substring(5, 8) + "/" +  s.substring(8, 12) + "-" +  s.substring(12, 14);
    }

    private static String formatarCPF(String s) {
        return s.substring(0, 3) + "." +  s.substring(3, 6) + "." +  s.substring(6, 9) + "-" +  s.substring(9, 11) ;
    }
}
