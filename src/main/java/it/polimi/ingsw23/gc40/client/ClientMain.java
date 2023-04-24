package it.polimi.ingsw23.gc40.client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Scegliere modalità di connessione e di interfaccia:");
        System.out.println("1. CLI + RMI");
        System.out.println("2. GUI + RMI");
        System.out.println("3. CLI + Socket");
        System.out.println("4. GUI + Socket");
        System.out.println("Digitare un numero per effettuare la scelta: ");
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            n = 0;
            n = sc.nextInt();

            switch (n) {
                case 1:
                    System.out.println("ok");
                    ClientMainRMI c = new ClientMainRMI(15001);
                    c.main(null);


                    //e mo?
                    break;
                case 2:
                    System.out.println("non ancora implementato");

                    break;
                case 3:
                    System.out.println("non ancora implementato");

                    break;
                case 4:
                    System.out.println("non ancora implementato");
                    break;
                default:
                    System.out.println("Scelta errata");
                    break;
            }
        }while(n<1 || n>4);

        System.out.println("sono fuori");
    }
}
