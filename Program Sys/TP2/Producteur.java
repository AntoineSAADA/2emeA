
public class Producteur extends Thread{
    Donner d;
    public Producteur(Donner d){
        this.d = d;
    }

    public void run(){
        while(true){
            try {
                
                Thread.sleep((int)(Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            d.produire();
        }
    }

  
    

}