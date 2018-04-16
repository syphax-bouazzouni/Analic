package app.matrice;

import dep.fraction.Fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Matrix is a class that impelment the most used operation of matrix : <br>
 * - ADD,SUB by using the static method Matrix.operation() <br>
 * - Multiplication by using the static method Matrix.mul() <br>
 * - Diterminant by using  det() <br>
 * - Transpose by using the static method Matrix.transpose() <br>
 * - Comatrice by using  coMat() <br>
 * - Inverse by using Inverse() <br>
 * - Rank by using rank() <br>
 * - Echelonne form by using echelonner() , lowerEchelonner() and echelonnerReduced() <br>
 */
public class Matrix {
    /**
     * the number of line in the main matrix
     */
    private int rowNb;
    /**
     * the number of column in the main matrix
     */
    private int colNb;
    /**
     * a scanner to read from the user
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * the main matrix which is a list of list
     */
    private List<List<Fraction>> mat = new ArrayList<>();

    /**
     * Construc a random(-100~x~100) martix
     *
     * @param row the number of line
     * @param col the number of column
     */
    public Matrix(int row, int col) {
        this.rowNb = row;
        this.colNb = col;
        for (int i = 0; i < row; i++) {
            this.mat.add(new ArrayList());
            for (int j = 0; j < col; j++) {
                this.mat.get(i).add(new Fraction((int) Math.round((Math.random() * 5) - 2.5)));
            }
        }
    }

    /**
     * Create a matrix from the user
     */
    public Matrix() {
        System.out.println("Matrix Init");

        System.out.print("Put the row ");
        this.rowNb = sc.nextInt();
        System.out.print("Put the column ");
        this.colNb = sc.nextInt();

        System.out.println("Put elements of matrix");
        for (int i = 0; i < this.rowNb; i++) {

            this.mat.add(new ArrayList());
            for (int j = 0; j < this.colNb; j++) {
                System.out.print("\t Put elem (" + i + "," + j + ") : ");
                this.mat.get(i).add(new Fraction(sc.nextInt()));
                System.out.println();
            }

        }

        System.out.println(this.toString());


    }

    /**
     * Create a matrix from a other matrix
     *
     * @param mat it's a list of list
     */
    public Matrix(List<List<Fraction>> mat) {
        if (mat == null) {
            this.colNb = 0;
            this.rowNb = 0;
            this.mat = null;
        } else {
            this.colNb = mat.get(0).size();
            this.rowNb = mat.size();
            this.mat = new ArrayList<>();
            for (int i = 0; i < this.rowNb; i++) {
                this.mat.add(new ArrayList());
                for (int j = 0; j < this.colNb; j++) {
                    this.mat.get(i).add(mat.get(i).get(j));
                }
            }
        }

    }

    /**
     * do add (if op=='+') or sub (if op=='-') for 2 matrix
     *
     * @param m1 matrix 1
     * @param m2 matrix 2
     * @param op the type of operation + or -
     * @return matrix = (m1 op m2)
     */
    public static Matrix operation(Matrix m1, Matrix m2, char op) {

        List<List<Fraction>> mAdd = null;
        if (m1 == null || m2 == null) return null;
        else if (!m1.equalsForm(m2)) {
            System.out.println("The 2 matrix haven't the same form");
            return null;
        } else {
            mAdd = new ArrayList<>();
            for (int i = 0; i < m1.getRowNb(); i++) {
                mAdd.add(new ArrayList());
                for (int j = 0; j < m1.getColNb(); j++) {
                    if (op == '+') mAdd.get(i).add(Fraction.add(m1.getElem(i, j), m2.getElem(i, j)));
                    else if (op == '-') mAdd.get(i).add(Fraction.sub(m1.getElem(i, j), m2.getElem(i, j)));
                }
            }
            return new Matrix(mAdd);
        }

    }

    /**
     * it sum the col of matrix 1 with the row of matrix 2
     * (it's used in the multiplication method)
     *
     * @param m1  matrix 1
     * @param m2  matrix 2
     * @param row the row of matrix 1
     * @param col the col of matrix 2
     * @return the sum
     */
    private static Fraction sum(Matrix m1, Matrix m2, int row, int col) {

        Fraction sum = new Fraction(0);
        if (m1 != null) {
            List<Boolean> err = m1.compare(m2, "03");

            if (err.get(0) && err.get(1)) {
                for (int i = 0; i < m1.getColNb(); i++) {
                    sum = Fraction.add(sum, (Fraction.mul(m1.getElem(row, i), m2.getElem(i, col))));
                }
            }
        }
        return sum;
    }

    /**
     * Do the multiplication of two matrix (m1 and m2)
     *
     * @param m1 matrix 1
     * @param m2 matrix 2
     * @return mul = m1 * m2
     */
    public static Matrix mul(Matrix m1, Matrix m2) {
        Matrix mul = null;
        double sum = 0;
        if (m1 != null) {
            List<Boolean> err = m1.compare(m2, "03"); // see if m2!=null and if col1 == row2

            if (err.get(0) && err.get(1)) {
                mul = new Matrix(m1.getRowNb(), m2.getColNb());

                for (int i = 0; i < mul.getRowNb(); i++) {
                    for (int j = 0; j < mul.getColNb(); j++) {
                        mul.setElem(i, j, Matrix.sum(m1, m2, i, j));
                    }
                }
            } else System.out.println(m1.getError(m2));

        } else System.out.println("Matrix 1 is null");

        return mul;
    }

    /**
     * Do the multiplication of a matrix and a number
     *
     * @param m1 matrix
     * @param a  number (constant)
     * @return mul = m1 a
     */
    public static Matrix mul(Matrix m1, Fraction a) {
        Matrix mul = null;
        if (m1 != null) {
            mul = new Matrix(m1.getRowNb(), m1.getColNb());

            for (int i = 0; i < mul.getRowNb(); i++) {
                for (int j = 0; j < mul.getColNb(); j++) {
                    mul.setElem(i, j, Fraction.mul(m1.getElem(i, j), a));
                }
            }
        } else System.out.println("Matrix 1 is null");

        return mul;
    }

    /**
     * Do the transposer of a matrix (line become column)
     *
     * @param m matrix to transpose
     * @return a matrix
     */
    public static Matrix transpose(Matrix m) {
        if (m == null) {
            return null;
        }
        Matrix newMat = new Matrix(m.getColNb(), m.getRowNb());
        for (int i = 0; i < newMat.getRowNb(); i++) {
            for (int j = 0; j < newMat.getColNb(); j++) {
                newMat.setElem(i, j, m.getElem(j, i));
            }
        }
        return newMat;
    }

    public int getRowNb() {
        return rowNb;
    }

    public void setRowNb(int rowNb) {
        this.rowNb = rowNb;
    }

    public int getColNb() {
        return colNb;
    }

    public void setColNb(int colNb) {
        this.colNb = colNb;
    }

    /**
     * Give an element in the matrix
     *
     * @param x the row
     * @param y the column
     * @return the elem in (x,y)
     */
    public Fraction getElem(int x, int y) {
        return mat.get(x).get(y);
    }

    /**
     * Set an element in the matrix
     *
     * @param x   the row
     * @param y   the column
     * @param val the value to put in (x,y)
     */
    public void setElem(int x, int y, Fraction val) {
        this.mat.get(x).set(y, val.clone());
    }

    /**
     * give the line "x" from the matrix
     *
     * @param x the number of the line (it begin from 0)
     * @return a list of the line "x" in the matrix
     */
    public List<Fraction> getLine(int x) {
        return mat.get(x);
    }

    /**
     * Set a new line "L" in the row "x" of the matrix
     *
     * @param x the row where to set the new list(line)
     * @param l the new line
     */
    public void setLine(int x, List<Fraction> l) {
        mat.set(x, l);
    }

    @Override
    public String toString() {
        String str = "Matrix{ \n";


        for (List l : mat) {
            str += "\t" + l + "\n";
        }

        return str + " }";


    }

    @Override
    /**
     * Duplicate the main matrix
     */
    public Matrix clone() {
        return new Matrix(this.mat);
    }

    /**
     * Says if two matrix are equals in form [(col1=col2) and (row1=row2)]
     *
     * @param m the matrix to compare with
     * @return true or false
     */
    public boolean equalsForm(Matrix m) {
        return (m == null) ? false : ((m.getRowNb() == this.rowNb) && (m.getColNb() == this.colNb));
    }

    /**
     * Says if a matrix is a square
     *
     * @return true or false
     */
    public boolean isSquare() {
        if (this.colNb != this.rowNb) {
            System.out.println("Matrix isn't a square");
            return false;
        }
        return true;
    }

    /**
     * compare two matrix
     *
     * @param m the matrix to compare with the main matrix
     * @return a list of boolean errors thar are :
     * 0 - matrix null
     * 1 - Col1 == Col2
     * 2 - Row1 == Row2
     * 3 - Col1 == Row2
     * 4 - Row1 == Col2
     */
    public List<Boolean> compare(Matrix m) {
        List<Boolean> tabEror = new ArrayList<Boolean>();
        for (int i = 0; i < 5; i++) {
            tabEror.add(false);
        }

        tabEror.set(0, m != null);
        tabEror.set(1, this.colNb == m.getColNb());
        tabEror.set(2, this.rowNb == m.getRowNb());
        tabEror.set(3, this.colNb == m.getRowNb());
        tabEror.set(4, this.rowNb == m.getColNb());

        return tabEror;
    }

    /**
     * the same that the first compare
     * the change here is that we can specify which error to check with "numErr"
     * <p>
     * Exemple : compare(m1,"12") it will check if (Col1 == Col2 and Row1 == Row2 )
     *
     * @param m      the matrix to compare with the main matrix
     * @param numErr it's a string wich specify wich error to check from 0 to 4
     * @return a list of boolean
     */
    public List<Boolean> compare(Matrix m, String numErr) {
        List<Boolean> tabEror = new ArrayList<Boolean>();


        if (numErr.contains("0")) tabEror.add(m != null);
        if (numErr.contains("1")) tabEror.add(this.colNb == m.getColNb());
        if (numErr.contains("2")) tabEror.add(this.rowNb == m.getRowNb());
        if (numErr.contains("3")) tabEror.add(this.colNb == m.getRowNb());
        if (numErr.contains("4")) tabEror.add(this.rowNb == m.getColNb());


        return tabEror;
    }

    /**
     * Use method compare  to print errors
     *
     * @param m the matrix to compare with the main matrix
     * @return a string of errors
     */
    public String getError(Matrix m) {
        List<String> stringErr = new ArrayList<String>();
        String[] tabErr = new String[5];
        String str = "";
        tabErr[0] = "The Matrix is null";
        tabErr[1] = "The col of matrix 1 is diffrent of col matrix 2 ";
        tabErr[2] = "The row of matrix 1 is diffrent of row matrix 2 ";
        tabErr[3] = "The col of matrix 1 is diffrent of row matrix 2 ";
        tabErr[4] = "The row of matrix 1 is diffrent of col matrix 2 ";

        int i = 0;

        for (Boolean b : this.compare(m)) {

            if (!b) str += "\t - " + tabErr[i] + " \n";

            i++;
        }

        return str;
    }

    /**
     * Give the mineur of matrix : omit row iOrg and omit column jOrg
     *
     * @param iOrg the line to omit
     * @param jOrg the column to omit
     * @return a matrix with out iOrg and jOrg
     */
    public Matrix minMat(int iOrg, int jOrg) {

        Matrix newMat = null;
        int h = 0, l = 0;

        if (iOrg == -1) newMat = new Matrix((this.rowNb), (this.colNb - 1)); // we don't omit line
        else if (jOrg == -1) newMat = new Matrix((this.rowNb - 1), (this.colNb)); // we don't omit column
        else newMat = new Matrix((this.rowNb - 1), (this.colNb - 1)); // we omit column and line

        for (int i = 0; i < this.rowNb; i++) {
            if (i != iOrg) {
                for (int j = 0; j < this.colNb; j++) {
                    if (j != jOrg && h < newMat.getRowNb() && l < newMat.getColNb()) {
                        newMat.setElem(h, l, this.getElem(i, j));
                        l++;
                    }

                    if (l == newMat.getColNb()) {
                        l = 0;
                        h++;
                    }

                }

            }

        }
        return newMat;
    }

    /**
     * Give the determinant of a matrix
     *
     * @return a double
     */
    public Fraction det() {
        Fraction det = new Fraction(0);
        if ((this.isSquare()) && (this.rowNb > 2)) {
            for (int i = 0; i < this.colNb; i++) {
                det = Fraction.add(
                        det,
                        Fraction.mul(this.getElem(0, i), this.minMat(0, i).det()).mul((int) Math.pow(-1, i))
                );

            }
        } else if (this.rowNb == 2 && this.colNb == 2) {
            det = Fraction.sub(
                    Fraction.mul(this.getElem(0, 0), this.getElem(1, 1)),
                    Fraction.mul(this.getElem(0, 1), this.getElem(1, 0))
            );
        } else if (this.rowNb == 1 && this.colNb == 1) det = this.getElem(0, 0);

        return det;
    }

    /**
     * Give  the coMatrix of a matrix
     *
     * @return a matrix
     */
    public Matrix coMat() {

        Matrix newMat = null;
        if (this.isSquare()) {
            newMat = new Matrix(this.rowNb, this.colNb);
            for (int i = 0; i < this.rowNb; i++) {
                for (int j = 0; j < this.colNb; j++) {
                    newMat.setElem(i, j, this.minMat(i, j).det().mul((int) Math.pow(-1, i + j)));
                }
            }
        }

        return newMat;
    }

    /**
     * Give the inverse of a matrix
     * it's use the methods coMat(),det(),mul(),transpose().
     *
     * @return a matrix
     */
    public Matrix inverse() {
        Fraction det = this.det();
        if (!det.isNul()) return Matrix.mul(Matrix.transpose(this.coMat()), Fraction.div(new Fraction(1), det));
        else {
            System.out.println("Imposible to inverse (det=0)");
            return null;
        }
    }


    /**
     * search for a pivot (first line with out zero ) in a matrix from column col
     *
     * @param col the column where to search the pivot
     * @return a (int) the line of the pivot
     */
    private int findPivot(int col) {
        int rowPivot = col;
        Fraction elem = new Fraction(0);
        if (col >= this.colNb || col >= this.rowNb) return -1;

        while (elem.isNul() && rowPivot < this.rowNb) {
            elem = this.getElem(rowPivot, col);
            if (elem.isNul()) rowPivot++;
        }

        return rowPivot;
    }

    /**
     * switch between two line's
     *
     * @param rowA the first line  (line 2 become line 1)
     * @param rowB the second line (line 1 become line 2)
     */
    private void switchLine(int rowA, int rowB) {
        List a = this.getLine(rowA);
        List b = this.getLine(rowB);

        this.setLine(rowA, b);
        this.setLine(rowB, a);

    }

    /**
     * it exchange elem(i,j) with elem(MaxRow-i-1,MinRow-j-1)
     * (it's used for the methods lowerEchelonner() and echelonnerReduced() )
     *
     * @return a matrix
     */
    public Matrix reflect() {
        Matrix newMat = this.clone();

        for (int i = 0; i < newMat.getRowNb(); i++) {
            for (int j = 0; j < newMat.colNb; j++) {
                newMat.setElem(i, j, this.getElem(this.rowNb - 1 - i, this.colNb - 1 - j));
            }
        }
        return newMat;
    }

    /**
     * Give the echelonne form  of a matrix
     *
     * @return a matrix (the echelonne form)
     */
    public Matrix echelonner() {
        Matrix newMat = this.clone();  // we clone the original matrice
        List pivot;
        Fraction cof;

        for (int i = 0; i < Math.min(this.colNb, this.rowNb); i++) {

            int pivotRow = newMat.findPivot(i); // we search for a pivot in column "i"

            if (pivotRow == (this.rowNb)) {
                // if we didn't find a pivot in this column we pass to the next column
                //System.out.println("pivot didn't found in col: "+i);
            } else if (pivotRow != -1) {
                // else if  we find a pivot in this collumn
                //System.out.println(pivotRow);
                pivot = newMat.getLine(pivotRow); // we get the line that will be the pivot
                newMat.switchLine(pivotRow, i); // we switch the rowPivot to the current line (wich is the current column)

                // we omit for each line the pivot
                for (int j = i + 1; j < this.rowNb; j++) {
                    cof = Fraction.div(newMat.getElem(j, i), (Fraction) pivot.get(i));
                    //System.out.println(newMat);
                    //System.out.println("cof = "+cof);
                    for (int l = 0; l < this.colNb; l++) {

                        newMat.setElem(j, l, Fraction.sub(
                                newMat.getElem(j, l),
                                Fraction.mul(cof, (Fraction) pivot.get(l)))
                        );
                    }
                }
            }


        }
        return newMat;
    }

    /**
     * Give the lower echelonne form of a matrix
     *
     * @return a matrix (the lower echelonner form)
     */
    public Matrix lowerEchelonner() {
        return (this.reflect().echelonner().reflect());

    }

    /**
     * Give the reduced echelonner form of a matrix
     *
     * @return a matrix ( the reduced echelonner form )
     */
    public Matrix echelonnerReduced() {
        return (this.echelonner().reflect().echelonner().reflect()
        );
    }

    /**
     * Give the rank of a matrix
     * it's use the echelonne form to find the rank
     *
     * @return rank an integer which the number of line deffirent of zero in echelonne form
     */
    public int rank() {
        Matrix newMat = this.echelonner();
        int rank = newMat.getRowNb(); //the rank in max is the raw of the matrix
        int i;
        boolean lineIsNull = true;
        // we will see the number of  line in the echelon form  that are all zero
        while (lineIsNull && rank > 0) {
            i = 0;
            while (i < newMat.getColNb() && lineIsNull) {
                lineIsNull = (lineIsNull && (newMat.getElem(rank - 1, i).isNul()));
                i++;
            }

            //System.out.println("line: "+(rank-1)+"isNull: "+lineIsNull);

            if (lineIsNull) rank--;
        }
        return rank; // rank = the Max row of matrix - number of line that are all zero;

    }

    /*
          don't use rankRecursive method

    public int rankRecursive(){

        if(this.det()!= 0) return this.colNb;
        else if (this.isSquare() && this.rowNb == 1) return 0; // if matrix has one element
        else{
            int  rang = -1 ,cycle=0;
            boolean direction = false; // if direction = false go from left to right
                               // else if direction = true go from top to bottom

            Matrix minMat ;
            boolean foundRang = false;
            int nbSquare = 0;

            if(!this.isSquare()) {
                if(this.colNb>this.rowNb){
                    nbSquare =  this.colNb - this.rowNb + 1;
                    direction = false; //  left to right
                }else {
                    nbSquare =  this.rowNb - this.colNb + 1;
                    direction = true; // top to bottom
                }
            } else nbSquare = 1;

            /*
                we have 2 cycles :
                    - cycle 1 : get one square by one in the main matrix and test it's determinant .
                    - cycle 2 : it mean that we didn't found a no null determinant in all the squares
                    of the main matrix , so we re-get one suare by one and do a ricursive call to each square

             *//*
            while (!foundRang && cycle<2){
                for (int i = 0; (i < nbSquare) && !foundRang ; i++) { // number of square matrix in the main matrix
                    minMat =this.clone();
                    if(nbSquare>1){
                        // we omit "i" columns or lines in the begining of the main matrix
                        for (int j = 0; j <i; j++) {
                            if(direction) minMat = minMat.minMat(0,-1);
                            else minMat = minMat.minMat(-1,0);
                        }

                        //we omit "(nbSuare-1) - i" columns or lines at the end of the main matrix
                        for (int j = 0; j < ((nbSquare-1)-i) ; j++) { // number of column to omit
                            if(direction) minMat = minMat.minMat(minMat.getRowNb()-1,-1);
                            else minMat = minMat.minMat(-1,minMat.getColNb()-1);
                        }

                    }else { // the main matrix is a square
                        /* we omit first last line and last column it gives square 1
                           then we omit last line and first column it gives square 2
                           then we omit first line and last column it gives square 3
                           then we omit first line and first column it gives square 4
                        *//*
                        for (int j = 0; j <this.colNb; j+=this.colNb-1) {
                            for (int k = 0; k <this.colNb ; k+=this.colNb-1) {
                                minMat = this.minMat(this.colNb-1-j,this.colNb-1-k);
                            }
                        }
                    }

                    if(cycle == 0){
                        if(minMat.det()!=0) {
                            System.out.println(minMat);
                            rang = minMat.getColNb();
                            foundRang = true;
                        }
                    }else if(cycle == 1){
                        rang = minMat.rankRecursive();
                        foundRang = (rang == Math.min(minMat.getColNb(),minMat.getRowNb())-1);
                    }
                }
                cycle++;
            }
            return  rang;
        }
    }*/
}
