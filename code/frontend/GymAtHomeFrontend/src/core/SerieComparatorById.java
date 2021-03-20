package core;

import java.util.Comparator;

public class SerieComparatorById implements Comparator<Serie> {
    @Override
    public int compare(Serie s1, Serie s2) {
        return Integer.compare(s1.ID, s2.ID);
    }
}
