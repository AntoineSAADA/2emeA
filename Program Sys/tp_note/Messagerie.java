import java.util.ArrayList;

// Implémente la classe Donner qui contient une liste de Donner 
public class Messagerie {
    ArrayList<String> list ;
    
    public Messagerie(){
        this.list = new ArrayList<>();

    }

    public synchronized void envoyer(){
        // Libère le verrou sur la liste 
        while(list.size() == 10){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add("Bonjour");
        System.out.println("Le message envoyé est : " + list.get(list.size()-1));
        notifyAll();
        
    }

    public synchronized void recevoir(){
        while(list.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Le message reçu est : " + list.get(0));
        list.remove(0);
        notifyAll();
    }
}