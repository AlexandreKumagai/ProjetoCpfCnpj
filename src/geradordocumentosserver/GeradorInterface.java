package geradordocumentosserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GeradorInterface extends Remote{
    public String executa(String i, int flag) throws RemoteException;
}
