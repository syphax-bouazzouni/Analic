package app.function.intervale;

import java.util.ArrayList;
import java.util.List;

/**
 * Under construction
 *
 * @author Bouazzouni Syphax
 */
public class Interval {
    private List<BaseInterval> listInterval;

    public Interval(String strInterval) {
        int i = 0;
        String str = "";
        this.listInterval = new ArrayList<>();

        if (strInterval != null) {
            if (strInterval.equals("R")) this.listInterval.add(new BaseInterval("]inf;inf["));
            else if (strInterval.equals("R*+")) this.listInterval.add(new BaseInterval("]0;inf["));
            else if (strInterval.equals("R+")) this.listInterval.add(new BaseInterval("[0;inf["));
            else if (strInterval.equals("R*-")) this.listInterval.add(new BaseInterval("]inf;0["));
            else if (strInterval.equals("R-")) this.listInterval.add(new BaseInterval("]inf;0]"));
            else {
                while (i < strInterval.length()) {
                    if (strInterval.charAt(i) == 'u') {
                        listInterval.add(new BaseInterval(str));
                        str = "";
                    } else str += strInterval.charAt(i);
                    i++;
                }
                listInterval.add(new BaseInterval(str));
            }
        }

    }

    @Override
    public String toString() {

        if (this.listInterval.size() == 0) return "Interval = empty";

        String str = "Interval = ";

        for (BaseInterval baseInterval : this.listInterval) {
            str += baseInterval + "u";
        }
        return str;
    }

    public List<BaseInterval> getListInterval() {
        return listInterval;
    }

    public boolean contain(double x) {
        boolean found = false;
        int i = 0;
        while (!found && i < this.listInterval.size()) {
            found = this.listInterval.get(i).contain(x);
            i++;
        }
        return found;
    }

    public void intersection(Interval interval) {
        List<BaseInterval> newListInterval = new ArrayList<>();
        BaseInterval base = null;
        for (BaseInterval baseInterval : this.listInterval) {

            for (BaseInterval baseinterval2 : interval.getListInterval()) {

                base = baseInterval.clone();
                base.insection(baseinterval2);

                if (!base.toString().equals("empty")) newListInterval.add(base);
            }
        }

        this.listInterval.clear();
        this.listInterval = newListInterval;
    }

    public void add(double x) {
        for (BaseInterval base : this.listInterval) {
            base.add(x);
        }
    }
}
