package it.polimi.ingsw23.gc40.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        Scanner sc = new Scanner(System.in);
        int n;
        do{
            System.out.println("Scegliere modalità di connessione e di interfaccia:");
            System.out.println("1. CLI + RMI");
            System.out.println("2. GUI + RMI");
            System.out.println("3. CLI + Socket");
            System.out.println("4. GUI + Socket");
            System.out.println("Digitare un numero per effettuare la scelta: ");
        do {
            n = 0;
            n = sc.nextInt();

            switch (n) {
                case 1:
                    System.out.println("ok");
                    CLI cli = new CLI(15001);
                    cli.rmi();

                    break;
                case 2:
                    System.out.println("non ancora implementato");
                    //gui.rmi();

                    break;
                case 3:
                    System.out.println("non ancora implementato");
                    //cli.socket();
                    break;
                case 4:
                    System.out.println("non ancora implementato");
                    //gui.socket();
                    break;
                default:
                    System.out.println("Scelta errata, riprova:");
                    break;
            }
        } while (n < 1 || n > 4);

        System.out.println("Sei tornato al menu iniziale, digita 1 per ricominciare, 0 per uscire:");
    }while(sc.nextInt()==1);
    }
}
