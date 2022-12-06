public class Utilisateur extends Thread{
    Messagerie2 m;
    public Utilisateur(Messagerie2 m){
        this.m = m;
    }
    
    public void run(){
        // Utiliser les méthodes de la classe Messagerie et les verrous pour recevoir les messages et en envoyer lorsque la boite n'est pas pleine 
        while(true){
            try {
                Thread.sleep(Math.round(Math.random()*10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Message reçu:"  + m.recevoir());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(Math.round(Math.random()*10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Message envoyé:"  + m.envoyer());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

    }
}
    
    

