package app.function.intervale;


public class BaseInterval {
    private String lowEdge, upEdge;  // the interval [a,b]
    private char typeLow, typeUp;

    public BaseInterval() {  // empty intervale
        this.lowEdge = "";
        this.upEdge = "";
        this.typeUp = ']';
        this.typeLow = '[';
    }

    public BaseInterval(String strIntervale) {
        int i = 1;
        String str = "";
        this.lowEdge = null;
        this.upEdge = null;

        if (strIntervale != null) {
            if (!strIntervale.equals("empty")) {
                this.typeLow = strIntervale.charAt(0);
                while (i < strIntervale.length()) {
                    if (strIntervale.charAt(i) == ';') {
                        this.lowEdge = str;
                        str = "";
                    } else if (strIntervale.charAt(i) == ']' || strIntervale.charAt(i) == '[') {
                        if (this.lowEdge != null) {
                            this.upEdge = str;

                        } else this.lowEdge = this.upEdge = str;
                        this.typeUp = strIntervale.charAt(i);
                    } else str += strIntervale.charAt(i);
                    i++;
                }
            }
        }

    }

    @Override
    public String toString() {

        if (this.isEmpty()) return "empty";
        else if (this.lowEdge.equals(this.upEdge) && isNotInfiniteEdge(this.upEdge))
            return typeLow + " " + this.lowEdge + " " + typeUp;
        else return typeLow + this.lowEdge + ";" + this.upEdge + typeUp;

    }

    public BaseInterval clone() {
        return new BaseInterval(this.toString());
    }

    public String getLowEdge() {
        return lowEdge;
    }

    public String getUpEdge() {
        return upEdge;
    }

    public char getTypeLow() {
        return typeLow;
    }

    public char getTypeUp() {
        return typeUp;
    }

    private boolean isNotInfiniteEdge(String edge) {
        if (edge == null) return true;
        else return !edge.equals("inf");
    }

    private boolean isEmpty() {

        if (this.lowEdge != null && this.upEdge != null) {
            if (isNotInfiniteEdge(this.lowEdge) && isNotInfiniteEdge(this.upEdge)) {
                double a = Double.parseDouble(lowEdge), b = Double.parseDouble(upEdge);
                if (a == b) return (this.typeUp == '[') && (this.typeLow == ']');
                else return a > b;
            } else return false;
        } else return true;
    }

    private boolean compareLowEdge(double x) {
        if (this.isEmpty()) return false;
        else if (this.isNotInfiniteEdge(this.lowEdge)) {
            if (this.typeLow == '[') return x >= Double.parseDouble(this.lowEdge);
            else return x > Double.parseDouble(this.lowEdge);
        } else return true;

    }

    public boolean compareUpEdge(double x) {
        if (this.isEmpty()) return false;
        else if (this.isNotInfiniteEdge(this.upEdge)) {
            if (this.typeUp == ']') return x <= Double.parseDouble(this.upEdge);
            else return x < Double.parseDouble(this.upEdge);
        } else return true;

    }


    public void insection(BaseInterval interval) {
        Double a;

        if (this.isNotInfiniteEdge(interval.getLowEdge()) && !interval.isEmpty()) {
            a = Double.parseDouble(interval.getLowEdge());

            if (this.compareLowEdge(a)) {
                this.lowEdge = "" + a;
                this.typeLow = interval.getTypeLow();
            }
        }

        if (this.isNotInfiniteEdge(interval.getUpEdge()) && !interval.isEmpty()) {
            a = Double.parseDouble(interval.getUpEdge());

            if (this.compareUpEdge(a)) {
                this.upEdge = "" + a;
                this.typeUp = interval.getTypeUp();
            }

        }
    }

    boolean contain(double x) {
        return this.compareLowEdge(x) && this.compareUpEdge(x);
    }

    public void add(double x) {
        if (this.isNotInfiniteEdge(this.lowEdge)) this.lowEdge = "" + (Double.parseDouble(this.lowEdge) + x);
        if (this.isNotInfiniteEdge(this.upEdge)) this.upEdge = "" + (Double.parseDouble(this.upEdge) + x);
    }

    public void mul(double x) {
        if (x > 0) {
            if (this.isNotInfiniteEdge(this.lowEdge)) this.lowEdge = "" + (Double.parseDouble(this.lowEdge) * x);
            if (this.isNotInfiniteEdge(this.upEdge)) this.upEdge = "" + (Double.parseDouble(this.upEdge) + x);
        } else {
            if (this.isNotInfiniteEdge(this.lowEdge)) this.lowEdge = "" + (Double.parseDouble(this.upEdge) * x);
            if (this.isNotInfiniteEdge(this.upEdge)) this.upEdge = "" + (Double.parseDouble(this.lowEdge) * x);
        }
    }

}
