import java.util.ArrayList;

// Implémente la classe Donner qui contient une liste de Donner 
public class Donner {
    ArrayList<String> list ;
    
    public Donner(){
        this.list = new ArrayList<>();

    }

    public synchronized void produire(){
        // Libère le verrou sur la liste 
        while(list.size() == 5){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add("produit");
        System.out.println("Le produit à bien été produit");
        notifyAll();
        
    }

    public synchronized void consommer(){
        while(list.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.remove(0);
        System.out.println("Le produit à bien été consommé");
        notifyAll();
    }
}