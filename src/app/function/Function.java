package app.function;

import app.function.intervale.Interval;
import dep.tree.Node;
import dep.tree.Tree;

/**
 * Function is a class that transform a function represented by string
 * to a function represented by a tree
 *
 * @author Bouazzouni syphax
 */
public class Function {
    /**
     * it's the variables of the function ; exemple: x,y,z
     */
    public static final String[] BASE_FUNCTION = {"x", "y", "z"};
    /**
     * the functions that can be used:
     * sin ,cos,tan,ln , e(expontielle) ,sqrt(root),neg(negative)
     */
    public static final String[] COMPLEX_FUNCTION = {"sin", "cos", "tan", "ln", "e", "sqrt", "neg"};
    /**
     * not used yet
     */
    public static final String[] COMPLEX_INTERVALE = {"R", "R", "R", "R*+", "R", "R+"};
    /**
     * the operation that can be used are :
     * +,-,*,/, ^(power(puissance))
     */
    public static final String[] BASE_OPERATION = {"+", "-", "*", "/", "^"};
    /**
     * strFunc is the string of the function
     * exemple : x + ln(sin(x)+2)
     */
    private String strFunc;
    /**
     * it's the tree that contains the function
     */
    private Tree treeFunc;
    /**
     * it's the begin iterval of the function  (not work for the moment)
     */
    private Interval intervalFunc;

    /**
     * Default constucteur create a empty function
     */
    public Function() {
        this.strFunc = "";
        this.treeFunc = new Tree();

    }

    /**
     * The main constructeur
     *
     * @param strFunc : the string that reprsent the fonction which we be transformed to tree
     */
    public Function(String strFunc) {
        //omit space and braket in the begin and end if exist
        this.strFunc = this.formatFonction(strFunc);
        this.treeFunc = new Tree();
        if (!this.strFunc.isEmpty())
            this.functionParse(this.strFunc);  //transform the string to tree
    }

    /**
     * Create with out parse automaticly
     *
     * @param strFunc the string taht represent the tree
     * @param parse   if true we parse automaticly else no parsing
     */
    public Function(String strFunc, Boolean parse) {
        this.strFunc = this.formatFonction(strFunc);
        this.treeFunc = new Tree();
        if (parse) this.functionParse(this.strFunc);
    }

    /**
     * Say's if a string is numeric
     *
     * @param str : the string to test
     * @return true if is numeric else false
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    /**
     * Says if a string is a base function
     * <br>
     * Exemple : - x,y,z is a base function (if we add 'y' and 'z' at the array of base function) <br>
     * - sin(x) is not a base function <br>
     *
     * @param strFunc to string to test
     * @return the index of the base function at the array of base functions
     */
    public static int isBaseFunction(String strFunc) {
        int i = 0, lenght = Function.BASE_FUNCTION.length;
        boolean found = false;
        while (i < lenght && !found) {
            found = Function.BASE_FUNCTION[i].equals(strFunc);
            if (!found) i++;
        }
        if (found) return i;
        else return -1;
    }

    /**
     * Says if a string is a compose function
     * <br>
     * Exemple : - "x","y","z" is not a function <br>
     * - "sin(x)" is a compose function <br>
     * - "cos(x+2)+2" is  not a compose function <br>
     *
     * @param strFunc to string to test
     * @return the index of the complex function at the array of complex functions
     */
    public static int isComposeFunction(String strFunc) {

        if (strFunc == null) return -1;
        else {
            Function f = new Function(strFunc, false);
            // we create f to use getWord
            if (f.getWord(strFunc).length() == strFunc.length()) {
                // if there is only one word
                int i = 0, lenght = Function.COMPLEX_FUNCTION.length;
                boolean found = false;
                /* we search for the compose function
                   in the array of the complex functions and return "i" (it's index) if found
                   else we return -1
                 */
                while (i < lenght && !found) {
                    if (strFunc.length() >= COMPLEX_FUNCTION[i].length()) {
                        found = COMPLEX_FUNCTION[i].equals(strFunc.substring(0, COMPLEX_FUNCTION[i].length()));
                    }
                    if (!found) i++;
                }
                if (found) return i;
                else return -1;
            } else return -1;

        }
    }

    /**
     * Says if a string is a compose function
     * <br>
     * Exemple : - +,-,* is an operation
     *
     * @param op to string to test
     * @return the index of the operation  at the array of oprations
     */
    public static int isOperation(String op) {
        int i = 0, lenght = Function.BASE_OPERATION.length;
        boolean found = false;
        while (i < lenght && !found) {
            found = Function.BASE_OPERATION[i].equals(op);
            if (!found) i++;
        }
        if (found) return i;
        else return -1;
    }

    @Override
    public String toString() {
        return "Function{" +
                "strFunc='" + strFunc + '\'' +
                ", treeFunc \n \t" + treeFunc +
                '}';
    }

    public String getStrFunc() {
        return strFunc;
    }

    public Tree getTreeFunc() {
        return treeFunc;
    }

    /**
     * Omit braket at the begin and end of the string
     *
     * @param strFunc : the string to omit
     * @return the new string with out baraket
     */
    private String omitBraket(String strFunc) {
        if (strFunc != null) {
            if (strFunc.length() >= 2 && strFunc.charAt(0) == '(' && strFunc.charAt(strFunc.length() - 1) == ')') {
                return strFunc.substring(1, strFunc.length() - 1);
            }
            return strFunc;
        }
        return "";
    }

    /**
     * Omit braket and space of a string (use omitBraket)
     *
     * @param strFunc : the string to omit
     * @return the new string with out space and braket
     */
    private String formatFonction(String strFunc) {
        String str = "";
        if (strFunc != null) {
            strFunc = this.omitBraket(strFunc);
            for (int i = 0; i < strFunc.length(); i++) {
                if (strFunc.charAt(i) != ' ') str += strFunc.charAt(i);

            }
            str = str.replaceAll("-\\(", "neg(");
        }
        return str;
    }

    /**
     * Used to read a all the characters before a operation
     * Exemple: - getWord("x+2") = "x";
     * - getWord("sin(x+ln(x))+x") = "sin(x+ln(x))";
     * - getWord("(ln(x)*sin(x))") = "(ln(x)*sin(x))";
     * The word can be : a numeric number, a operation ,
     * a base function (x,y,z) , or a complex function(sin,cos ...)
     *
     * @param strFunc : the string to get from it the word
     * @return the word found
     */
    public String getWord(String strFunc) {
        int i = 0;
        String str = "";
        boolean foundOperation = false;
        int nbBraket = -1; // count (the number of opened braket - the number of colsed braket )
        /*
           we stop if :
                - the string strFunc is finish OR
                - we found an opertaion and didn't found braket before OR
                - the number of braket is equal to zero
         */
        while (i < strFunc.length() && !((foundOperation && nbBraket == -1) || nbBraket == 0)) {

            if (Function.isOperation("" + strFunc.charAt(i)) != -1) {
                if (str.length() == 0 || nbBraket >= 1) str += "" + strFunc.charAt(i);
                foundOperation = true;

            } else {
                str += strFunc.charAt(i);
                if (strFunc.charAt(i) == '(') {
                    if (nbBraket == -1) nbBraket = 1;
                    else nbBraket++;
                } else if (strFunc.charAt(i) == ')') {
                    nbBraket--;
                }
            }
            i++;
        }
        return str;
    }

    /**
     * Give the result of complex function operation
     * <br>
     * Exemple : - index = 3 ,x = 1 : it return ln(1) = 0 <br>
     * - index = 0 ,x = 2 : it return sin(2) <br>
     *
     * @param index : the position of the compose function in the array of compose function
     * @param x     : the parametre of the compose function
     * @return the result of the operation
     */
    private double getComposeFunction(int index, double x) {
        double result;
        switch (index) {
            case 0:
                result = Math.sin(x);
                break;
            case 1:
                result = Math.cos(x);
                break;
            case 2:
                result = Math.tan(x);
                break;
            case 3:
                result = Math.log(x);
                break;
            case 4:
                result = Math.exp(x);
                break;
            case 5:
                result = Math.sqrt(x);
                break;
            case 6:
                result = -x;
                break;
            default:
                result = 0;
        }
        return result;
    }


    /**
     * Give the result of operation between a and b
     * <br>
     * Exemple : - index = 4 : it return a^b : a power b <br>
     * - index = 0 : it return a+b <br>
     *
     * @param index : the position of the operation in the array of operations
     * @param a,b   : the operands
     * @return the result of the operation
     */
    private double getOperation(int index, double a, double b) {
        double result;
        switch (index) {
            case 0:
                result = a + b;
                break;
            case 1:
                result = a - b;
                break;
            case 2:
                result = a * b;
                break;
            case 3:
                if (b != 0) result = a / b;
                else result = 0;
                break;
            case 4:
                result = Math.pow(a, b);
                break;
            default:
                result = 0;
        }
        return result;
    }

    /**
     * Transform a string to a binary tree
     * <br>
     * Exemple :  - x+2 : it gives root = "+" , left son = "x" ,right son ="2" <br>
     * - sin(x) : it gives root = "sin" , left son = null , right son = "x" <br>
     * <br>
     * Rule's :
     * -  braket are not obligatory but use it's to express priority
     *
     * @param strFuncOrign the function to transform exemple(x+2)
     * @return the result tree
     */
    public Tree functionParse(String strFuncOrign) {
        Tree resultTree = null;
        /* a string strFuncOrigin can be 3 things :
                - A numeric value
                - A base function ("x","y"..)
                - A compose function ("sin(x)",cos("x+2"),..)
                - OR an operation between them
        */

        if (Function.isNumeric(strFuncOrign) || Function.isBaseFunction(strFuncOrign) != -1) {
            resultTree = new Tree(strFuncOrign);

        } else if (Function.isComposeFunction(strFuncOrign) != -1) {
            String s = COMPLEX_FUNCTION[Function.isComposeFunction(strFuncOrign)];
            // s is the the compose function (sin,cos,ln,e,..)
            resultTree = new Tree(s);
            resultTree.addRightTree(this.functionParse(strFuncOrign.substring(s.length() + 1, strFuncOrign.length() - 1)));

            if (strFuncOrign.equals(this.strFunc)) this.treeFunc.setRoot(resultTree.getRoot());

        } else if (strFuncOrign != null) {
            String word1 = "", word2 = "", word3 = "";
            int nbWord, nb = 3;
            int i = 0;
            // we read first 3 words  then until the end of strFunc we read 2 words
            while (i < strFuncOrign.length()) {
                nbWord = 0;
                while (i < strFuncOrign.length() && nbWord < nb) {
                    if (nbWord == 0) {
                        word1 = this.getWord(strFuncOrign.substring(i));
                        i += word1.length();
                        word1 = this.omitBraket(word1);

                    } else if (nbWord == 1) {
                        word2 = this.getWord(strFuncOrign.substring(i));
                        i += word2.length();
                        word2 = this.omitBraket(word2);
                    } else if (nbWord == 2) {
                        word3 = this.getWord(strFuncOrign.substring(i));
                        i += word3.length();
                        word3 = this.omitBraket(word3);
                    }

                    nbWord++;
                }

                if (nb == 3) {
                    resultTree = new Tree(word2); // root is the operation
                    resultTree.addLeftTree(this.functionParse(word1)); // a ricursive call for word 1
                    resultTree.addRightTree(this.functionParse(word3));// a ricursive call for word 2
                    nb = 2; // then we read only 2 word

                } else if (nb == 2) {
                    Tree t = new Tree();
                    t.setRoot(resultTree.getRoot()); // save the 3 words tree that we created first
                    resultTree = new Tree(word1); // the new root it's an operation
                    resultTree.addLeftTree(t);
                    resultTree.addRightTree(this.functionParse(word2));

                }
            }

        } else return null;
        // we write in this.treeFunc only at the end
        if (strFuncOrign.equals(this.strFunc)) treeFunc.setRoot(resultTree.getRoot());
        return resultTree;
    }

    /**
     * Calculate Function(x)(not use directly this)
     * <br>
     * Exemple of use :
     * Function f = new Funtion("x^2");
     * f.calc(2,f.getRoot()) retuns 4
     *
     * @param x    :the prameter
     * @param root :the root of the function
     * @return the result
     */
    public double calc(double x, Node root) {

        if (root == null) return 0;
        else {
            String nodeValue = root.getValue();
            double resultA = 0, resultB = 0;
            int index;
            //if it's numeric parse the string  to a double
            if (Function.isNumeric(nodeValue)) return Double.parseDouble(nodeValue);
                //if it's "x" we return x ;
            else if ((Function.isBaseFunction(nodeValue)) != -1) return x;
            else if ((index = Function.isComposeFunction(nodeValue)) != -1) {
                return this.getComposeFunction(index, this.calc(x, root.getRightSon()));

            } else if ((index = Function.isOperation(nodeValue)) != -1) {
                if (root.getLeftSon() != null) {
                    resultA = this.calc(x, root.getLeftSon());
                }
                if (root.getRightSon() != null) {
                    resultB = this.calc(x, root.getRightSon());
                }
                return this.getOperation(index, resultA, resultB);

            } else return 0;
        }

    }

    /**
     * Calculate Function(x)(use directly this)
     * <br>
     * Exemple of use :
     * Function f = new Funtion("x^2");
     * f.calc(2) retuns 4
     *
     * @param x :the prameter
     * @return the result
     */
    public double calc(double x) {
        return this.calc(x, this.treeFunc.getRoot());
    }
}
