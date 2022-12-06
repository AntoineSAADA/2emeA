public class Executable {
    public static void main(String[] args) {
        Messagerie2 m = new Messagerie2();
        try {
            m.envoyer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Utilisateur c = new Utilisateur(m);
        Utilisateur c2 = new Utilisateur(m);
        Utilisateur c3 = new Utilisateur(m);
        Utilisateur c4 = new Utilisateur(m);
        Utilisateur c5 = new Utilisateur(m);
        Utilisateur c6 = new Utilisateur(m);
        Utilisateur c7 = new Utilisateur(m);
        c.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();


        

    }
    
}
