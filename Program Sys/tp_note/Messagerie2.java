import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Implémente la classe Donner qui contient une liste de Donner 
public class Messagerie2 {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final Object[] items = new Object[10]; 
    int putpr, takepr, count;


    public String envoyer() throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putpr] = "Bonjour";
            if (++putpr == items.length) putpr = 0;
            ++count;
            notEmpty.signal();
            return (String) "Bonjour";
        } finally {
            lock.unlock();
        }
    }

    public String recevoir() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takepr];
            if (++takepr == items.length) takepr = 0;
            --count;
            notFull.signal();
            return (String) x;
        } finally {
            lock.unlock();
        }
    }
}