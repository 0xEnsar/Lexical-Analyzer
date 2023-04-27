import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class App {
    public static boolean isDigit(char c) {
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8'
                || c == '9') {
            return true;
        }
        return false;
    }

    public static boolean isHexadecimal(char c) {
        if (c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F' || c == 'a' || c == 'b' || c == 'c'
                || c == 'd' || c == 'e' || c == 'f') {
            return true;
        }
        return false;
    }

    public static boolean isBinary(char c) {
        if (c == '0' || c == '1') {
            return true;
        }
        return false;
    }

    public static boolean isLetter(char c) {
        if (c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f' || c == 'g' || c == 'h' || c == 'i'
                || c == 'j' || c == 'k' || c == 'l' || c == 'm' || c == 'n' || c == 'o' || c == 'p' || c == 'q'
                || c == 'r' || c == 's' || c == 't' || c == 'u' || c == 'v' || c == 'w' || c == 'x' || c == 'y'
                || c == 'z') {
            return true;
        } else
            return false;
    }

    public static char isBracket(char c) {
        if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}') {
            return c;
        }
        return 0;
    }

    public static boolean isOperator(char c) {
        if (c == '*' || c == '!' || c == '/' || c == ':' || c == '<' || c == '=' || c == '>' || c == '?') {
            return true;
        } else
            return false;
    }

    public static boolean isDigitOperator(char c) {
        if (c == '.' || c == '+' || c == '-') {
            return true;
        } else
            return false;
    }

    public static boolean isIdentifierForLetter(String st) {
        if (st.length() != 0) {
            for (int i = 1; i <= st.length() - 1; i++) {
                if (isLetter(st.charAt(i)) || isDigit(st.charAt(i)) || isDigitOperator(st.charAt(i))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public static boolean isIdentifierForOperator(String st) {
        if (st.length() != 0) {
            for (int i = 1; i <= st.length() - 1; i++) {
                if (isLetter(st.charAt(i)) || isDigit(st.charAt(i)) || isDigitOperator(st.charAt(i))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public static void printHashMap(HashMap<String, String> t, int maxRow, int maxColumn, FileWriter output)
            throws IOException {
        // print with key
        for (int i = 1; i < maxRow; i++) {
            for (int j = 1; j <= maxColumn; j++) {
                String value = t.get("" + i + ":" + j);
                if (value == null || value == "") {
                    continue;
                }
                if (value.contains("ERROR")) {
                    System.out.println(value);
                    output.write(value);
                    return;
                }
                System.out.println(value + " " + i + ":" + j + " ");
                try {
                    output.write(value + " " + i + ":" + j + " \n");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void floatingPointNumberChecker(String st, HashMap<String, String> tOut, int maxRow, int maxCol,
            int i, int j) {
        int beforeDot = st.indexOf('.') - 1;
        int afterDot = st.indexOf('.') + 1;
        boolean isDigitBeforeDot = false;
        if (beforeDot > 0) {

            for (int m = 1; m <= beforeDot; m++) {
                if (isDigit(st.charAt(m))) {
                    isDigitBeforeDot = true;
                } else {
                    isDigitBeforeDot = false;
                    break;
                }
            }
        }
        if (isDigitBeforeDot || beforeDot <= 0) {
            // if it contains e
            if (st.contains("e") && !st.contains("E")) {
                int beforee = st.indexOf('e') - 1;
                int aftere = st.indexOf('e') + 1;
                boolean isDigitAfterDot = false;
                boolean isDigitAftere = false;
                // check wheter after dot is digit or not
                for (int m = afterDot; m <= beforee; m++) {
                    if (isDigit(st.charAt(m))) {
                        isDigitAfterDot = true;
                    } else {
                        isDigitAfterDot = false;
                        break;
                    }
                }
                // if it contains e and after dot is digit
                if (isDigitAfterDot) {
                    boolean isThereDigitAftere = true;
                    if (aftere > st.length() - 1) {
                        isThereDigitAftere = false;
                    }

                    if ((isThereDigitAftere)) {
                        // check wheter after e is digit or not
                        if (st.charAt(aftere) == '+' || st.charAt(aftere) == '-') {
                            aftere++;
                        }
                        for (int n = aftere; n <= st.length() - 1; n++) {
                            if (isDigit(st.charAt(n))) {
                                isDigitAftere = true;
                            } else {
                                isDigitAftere = false;
                                break;
                            }
                        }
                        if (isDigitAftere) {
                            tOut.put("" + i + ":" + j, "NUMBER");
                        } else {
                            tOut.put("" + i + ":" + j,
                                    "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                        }
                    } else {
                        tOut.put("" + i + ":" + j,
                                "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                    }
                } else {
                    tOut.put("" + i + ":" + j,
                            "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                }
            }
            // if it contains E
            else if (st.contains("E") && !st.contains("e")) {
                int beforeE = st.indexOf('E') - 1;
                int afterE = st.indexOf('E') + 1;
                boolean isDigitAfterDot = false;
                boolean isDigitAfterE = false;
                // check wheter after dot is digit or not
                for (int m = afterDot; m <= beforeE; m++) {
                    if (isDigit(st.charAt(m))) {
                        isDigitAfterDot = true;
                    } else {
                        isDigitAfterDot = false;
                        break;
                    }
                }
                // if it contains E and after dot is digit
                if (isDigitAfterDot) {
                    boolean isThereDigitAfterE = true;
                    if (afterE > st.length() - 1) {
                        isThereDigitAfterE = false;
                    }
                    if (isThereDigitAfterE) {
                        // check wheter after E is digit or not
                        if (st.charAt(afterE) == '+' || st.charAt(afterE) == '-') {
                            afterE++;
                        }
                        for (int n = afterE; n <= st.length() - 1; n++) {
                            if (isDigit(st.charAt(n))) {
                                isDigitAfterE = true;
                            } else {
                                isDigitAfterE = false;
                                break;
                            }
                        }
                    }
                    if (isDigitAfterE) {
                        tOut.put("" + i + ":" + j, "NUMBER");
                    } else {
                        tOut.put("" + i + ":" + j,
                                "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                    }
                } else if (isDigitAfterE == false) {
                    tOut.put("" + i + ":" + j,
                            "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                }
            }
            // if it doesn't contain e or E
            else if (!st.contains("e") && !st.contains("E")) {
                boolean isDigitAfterDot = false;
                for (int m = afterDot; m <= st.length() - 1; m++) {
                    if (isDigit(st.charAt(m))) {
                        isDigitAfterDot = true;
                    } else {
                        isDigitAfterDot = false;
                        break;
                    }
                }
                if (isDigitAfterDot) {
                    tOut.put("" + i + ":" + j, "NUMBER");
                } else if (isDigitAfterDot == false) {
                    tOut.put("" + i + ":" + j,
                            "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                }
            }
        } else {
            tOut.put("" + i + ":" + j,
                    "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner inputFileReader = new Scanner(System.in);
        System.out.println("Enter the name of the input file (input.txt): ");
        String inputFileName = inputFileReader.next();
        File input = new File("./" + inputFileName);
        Scanner inputReader = new Scanner(input);

        FileWriter output = new FileWriter("./output.txt");

        ArrayList<String> lines = new ArrayList<String>();
        int lineN = 0;
        String curretString = "";
        HashMap<String, String> t = new HashMap<String, String>();
        HashMap<String, String> tOut = new HashMap<String, String>();
        char currentChar = ' ';

        while (inputReader.hasNextLine()) {
            lineN++;
            lines.add(inputReader.nextLine());
        }

        int maxColumn = 0;
        int maxRow = lineN + 1;

        boolean isDoubleQuote = false;
        boolean isSingleQuote = false;

        for (int i = 0; i < lineN; i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (j >= maxColumn) {
                    maxColumn = j + 2;
                }

                currentChar = lines.get(i).charAt(j);

                if (isBracket(currentChar) != 0) {
                    if (t.get((1 + i) + ":" + (j - curretString.length() + 1)) == null) {
                        t.put((1 + i) + ":" + (j - curretString.length() + 1), curretString);
                        curretString = "";
                    }
                    t.put((1 + i) + ":" + (j + 1), "" + currentChar);
                }

                else if (currentChar == ' ' && isDoubleQuote == false) {
                    t.put((1 + i) + ":" + (j - curretString.length() + 1), curretString);
                    curretString = "";
                }

                else if (currentChar == '"') {
                    isDoubleQuote = !isDoubleQuote;
                    if (isDoubleQuote == false) {
                        curretString += currentChar;
                        t.put((1 + i) + ":" + (j - curretString.length() + 2), curretString);
                        curretString = "";
                    } else {
                        curretString += currentChar;
                    }
                }

                else if (currentChar == '\\') {
                    char nextChar = lines.get(i).charAt(j + 1);
                    j++;
                    curretString += nextChar + "\\";
                }

                else if (currentChar == '\'') {
                    isSingleQuote = !isSingleQuote;
                    if (isSingleQuote == false) {
                        curretString += currentChar;
                        t.put((1 + i) + ":" + (j - curretString.length() + 2), curretString);
                        curretString = "";
                    } else {
                        curretString += currentChar;
                    }
                }

                else {
                    curretString += currentChar;
                }
            }
        }

        for (int i = 1; i < maxRow; i++) {
            for (int j = 1; j <= maxColumn; j++) {

                if (t.get("" + i + ":" + j) == null || t.get("" + i + ":" + j) == "") {
                    continue;
                }

                String st = t.get("" + i + ":" + j);

                if (st.charAt(0) == '-' || st.charAt(0) == '+') {
                    // - veya + den sonra boşluk kısmı
                    if (st.length() == 1) {
                        tOut.put("" + i + ":" + j, "IDENTIFIER");
                    }
                    // - veya + den sonra digit gelmesi durumu
                    else if (st.length() >= 2 && !st.contains(".")) {
                        if (st.contains("e") && !st.contains("E")) {
                            int beforee = st.indexOf('e') - 1;
                            int aftere = st.indexOf('e') + 1;
                            boolean isDigitAftere = false;
                            boolean isDigitBeforee = false;

                            for (int m = 1; m <= beforee; m++) {
                                if (isDigit(st.charAt(m))) {
                                    isDigitBeforee = true;
                                } else {
                                    isDigitBeforee = false;
                                    break;
                                }
                            }
                            boolean isThereDigitAftere = true;
                            if (aftere >= st.length()) {
                                isThereDigitAftere = false;
                            }
                            if (isThereDigitAftere) {
                                if (st.charAt(aftere) == '-' || st.charAt(aftere) == '+') {
                                    aftere++;
                                }
                                for (int m = aftere; m < st.length(); m++) {
                                    if (isDigit(st.charAt(m))) {
                                        isDigitAftere = true;
                                    } else {
                                        isDigitAftere = false;
                                        break;
                                    }
                                }
                            }

                            if (isDigitAftere == true && isDigitBeforee == true) {
                                tOut.put("" + i + ":" + j, "NUMBER");
                            } else {
                                tOut.put("" + i + ":" + j,
                                        "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                            }

                        } else if (st.contains("E") && !st.contains("e")) {
                            boolean isDigitAfterE = false;
                            boolean isDigitBeforeE = false;
                            int beforeE = st.indexOf('E') - 1;
                            int afterE = st.indexOf('E') + 1;

                            for (int m = 1; m <= beforeE; m++) {
                                if (isDigit(st.charAt(m))) {
                                    isDigitBeforeE = true;
                                } else {
                                    isDigitBeforeE = false;
                                    break;
                                }
                            }
                            boolean isThereDigitAfterE = true;
                            if (afterE >= st.length()) {
                                isThereDigitAfterE = false;
                            }
                            if (isThereDigitAfterE) {
                                if (st.charAt(afterE) == '-' || st.charAt(afterE) == '+') {
                                    afterE++;
                                }
                                for (int m = afterE; m < st.length(); m++) {
                                    if (isDigit(st.charAt(m))) {
                                        isDigitAfterE = true;
                                    } else {
                                        isDigitAfterE = false;
                                        break;
                                    }
                                }
                            }

                            if (isDigitAfterE == true && isDigitBeforeE == true) {
                                tOut.put("" + i + ":" + j, "NUMBER");
                            } else {
                                tOut.put("" + i + ":" + j,
                                        "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                            }
                        } else if (!st.contains("e") && !st.contains("E")) {
                            boolean isDigit = true;
                            for (int k = 1; k < st.length(); k++) {
                                if (!isDigit(st.charAt(k))) {
                                    isDigit = false;
                                    break;
                                }
                            }
                            if (isDigit) {
                                tOut.put("" + i + ":" + j, "NUMBER");
                            } else {
                                tOut.put("" + i + ":" + j,
                                        "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                            }
                        } else {
                            tOut.put("" + i + ":" + j,
                                    "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                        }

                    } else if (st.length() >= 2 && st.contains(".")) {
                        floatingPointNumberChecker(st, tOut, maxRow, maxColumn, i, j);
                    }

                    // nokta ile başlama dururmumuz
                } else if (st.charAt(0) == '.') {
                    if (st.length() == 1) {
                        tOut.put("" + i + ":" + j, "IDENTIFIER");
                    } else if (st.length() >= 2) {
                        floatingPointNumberChecker(st, tOut, maxRow, maxColumn, i, j);
                    }

                } else if (isDigit(st.charAt(0))) {
                    boolean isDecimal = true;
                    boolean isHexadecimal = true;
                    boolean isBinary = true;
                    boolean isFloat = false;

                    // check if the input string is a floating point number
                    if (st.length() >= 2 && st.contains(".")) {
                        floatingPointNumberChecker(st, tOut, maxRow, maxColumn, i, j);
                        isFloat = true;
                    }

                    // Check if the input string is a decimal number
                    for (int k = 1; k < st.length(); k++) {
                        if (!isDigit(st.charAt(k))) {
                            isDecimal = false;
                            break;
                        }
                    }

                    // Check if the input string is a hexadecimal number
                    if (st.length() >= 2 && st.charAt(0) == '0' && (st.charAt(1) == 'x')) {
                        if (st.charAt(1) == 'x' && st.length() == 2) {
                            isHexadecimal = false;
                        }
                        for (int k = 2; k < st.length(); k++) {
                            if (!isHexadecimal(st.charAt(k))) {
                                isHexadecimal = false;
                                break;
                            }
                        }
                    } else {
                        isHexadecimal = false;
                    }

                    // Check if the input string is a binary number
                    if (st.length() >= 2 && st.charAt(0) == '0' && (st.charAt(1) == 'b')) {
                        if (st.charAt(1) == 'b' && st.length() == 2) {
                            isBinary = false;
                        }
                        for (int k = 2; k < st.length(); k++) {
                            if (!isBinary(st.charAt(k))) {
                                isBinary = false;
                                break;
                            }
                        }
                    } else {
                        isBinary = false;
                    }

                    // Output the token based on the type of input string
                    if (isDecimal) {
                        tOut.put("" + i + ":" + j, "NUMBER");
                    } else if (isHexadecimal) {
                        tOut.put("" + i + ":" + j, "NUMBER");
                    } else if (isBinary) {
                        tOut.put("" + i + ":" + j, "NUMBER");
                    } else if (isFloat) {
                        // do nothing
                    } else {
                        tOut.put("" + i + ":" + j, "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                    }

                } else if (isLetter(st.charAt(0))) {
                    if (st.equalsIgnoreCase("true") || st.equalsIgnoreCase("false")) {
                        tOut.put("" + i + ":" + j, "BOOLEAN");
                    } else if (st.equalsIgnoreCase("define")) {
                        tOut.put("" + i + ":" + j, "DEFINE");
                    } else if (st.equalsIgnoreCase("let")) {
                        tOut.put("" + i + ":" + j, "LET");
                    } else if (st.equalsIgnoreCase("cond")) {
                        tOut.put("" + i + ":" + j, "COND");
                    } else if (st.equalsIgnoreCase("if")) {
                        tOut.put("" + i + ":" + j, "IF");
                    } else if (st.equalsIgnoreCase("begin")) {
                        tOut.put("" + i + ":" + j, "BEGIN");
                    } else if (st.length() >= 2 && isIdentifierForLetter(st)) {
                        tOut.put("" + i + ":" + j, "IDENTIFIER");
                    } else if (st.length() == 1 && isLetter(st.charAt(0))) {
                        tOut.put("" + i + ":" + j, "IDENTIFIER");
                    } else {
                        tOut.put("" + i + ":" + j, "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                    }
                } else if (st.charAt(0) == '\'') {
                    if (st.length() == 4) {
                        if (st.charAt(0) == '\'') {
                            if (st.charAt(1) == '\''){
                                if (st.charAt(2) == '\\') {
                                    if (st.charAt(3) == '\'') {
                                        tOut.put("" + i + ":" + j, "CHAR");
                                    }
                                }
                            }
                            else if (st.charAt(1) == '\\') {
                                if (st.charAt(2) == '\\') {
                                    if (st.charAt(3) == '\'') {
                                        tOut.put("" + i + ":" + j, "CHAR");
                                    }
                                }
                            }

                        }

                    } else if (st.length() == 3 && st.charAt(0) == '\'' && st.charAt(2) == '\'') {
                        tOut.put("" + i + ":" + j, "CHAR");
                    } else {
                        tOut.put("" + i + ":" + j, "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                    }
                } else if (st.charAt(0) == '\"') {
                    if ((st.length() == 4 && st.charAt(0) == '\"' && st.charAt(1) == '\\' && st.charAt(2) == '\"'
                            && st.charAt(3) == '\"')
                            || (st.length() == 4 && st.charAt(0) == '\"' && st.charAt(1) == '\\' && st.charAt(2) == '\\'
                                    && st.charAt(3) == '\"')) {
                        tOut.put("" + i + ":" + j, "STRING");
                    } else if (st.charAt(0) == '\"' && st.charAt(st.length() - 1) == '\"') {
                        tOut.put("" + i + ":" + j, "STRING");
                    } else {
                        tOut.put("" + i + ":" + j, "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                    }
                } else if (isBracket(st.charAt(0)) != 0) {
                    char c = isBracket(st.charAt(0));
                    if (c == '(') {
                        tOut.put("" + i + ":" + j, "LEFTPAR");
                    } else if (c == ')') {
                        tOut.put("" + i + ":" + j, "RIGHTPAR");
                    } else if (c == '[') {
                        tOut.put("" + i + ":" + j, "LEFTSQUAREB");
                    } else if (c == ']') {
                        tOut.put("" + i + ":" + j, "RIGHTSQUAREB");
                    } else if (c == '{') {
                        tOut.put("" + i + ":" + j, "LEFTCURLYB");
                    } else if (c == '}') {
                        tOut.put("" + i + ":" + j, "RIGHTCURLYB");
                    }
                } else if (isOperator(st.charAt(0))) {
                    if (isOperator(st.charAt(0)) && st.length() == 1) {
                        tOut.put("" + i + ":" + j, "IDENTIFIER");
                    } else if (st.length() >= 2 && isIdentifierForOperator(st)) {
                        tOut.put("" + i + ":" + j, "IDENTIFIER");
                    } else {
                        tOut.put("" + i + ":" + j, "LEXICAL ERROR [" + i + ":" + j + "]: Invalid token '" + st + "'");
                    }
                }
            }
        }

        printHashMap(tOut, maxRow, maxColumn, output);

        inputReader.close();
        inputFileReader.close();
        output.close();
    }

}