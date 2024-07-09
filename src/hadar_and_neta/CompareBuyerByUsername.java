package hadar_and_neta;

import java.util.Comparator;

public class CompareBuyerByUsername implements Comparator<Buyer> {

    public int compare(Buyer b1, Buyer b2){
        return b1.getUser().getName().compareTo(b2.getUser().getName());
    }
}
