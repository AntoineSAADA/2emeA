public class Executable {
    public static void main(String[] args) {
        Donner d = new Donner();
        Producteur p = new Producteur(d);
        Producteur p1 = new Producteur(d);
        Producteur p2 = new Producteur(d);
        Producteur p3 = new Producteur(d);
        Consomateur c = new Consomateur(d);
        Consomateur c2 = new Consomateur(d);
        Consomateur c3 = new Consomateur(d);
        p.start();
        c.start();
        c2.start();
        c3.start();
        p1.start();
        p2.start();
        p3.start();
        

    }
    
}
