public class Consomateur extends Thread{
    Donner d;
    public Consomateur(Donner d){
        this.d = d;
    }
    public void run(){
        while(true){
            try {
                
                Thread.sleep((int)(Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            d.consommer();
        }
    }
    
    
}
