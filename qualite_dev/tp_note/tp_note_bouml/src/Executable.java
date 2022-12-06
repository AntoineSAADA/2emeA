import java.util.ArrayList;

public class Executable {

    public static void main(String[] args) {
        ArrayList<Transition> transitions = new ArrayList<Transition>();
        Etat e1 = new Etat(transitions);
        Etat e2 = new Etat(transitions);
        Etat e3 = new Etat(transitions);
        EtatFinal etatFinal = new EtatFinal(transitions);
        transitions.add(new Transition('a', e1, e2));
        transitions.add(new Transition('a', e2, e2));
        transitions.add(new Transition('b', e2, e3));
        transitions.add(new Transition('b', e3, e3));
        transitions.add(new Transition('b', e3, etatFinal));
        Automate automate = new Automate(e1);
        automate.analyseMot("aaabb");
    }
    
}
