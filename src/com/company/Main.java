package com.company;
import java.util.*;

class Main {
// gets string an arithmetic expression between two numbers (for exp. V * X) and returns a string with the result of their execution.
    public static String calc(String input) throws Exception {
        String[] Words = input.split(" ");//splitting by spaces
        if (Words.length != 3) {
            throw new Exception ("The format of the mathematical operation does not match the specified one - two operands and one operator (+, -, /, *)");
        }

        String[] RomanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] ArabicNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        Boolean IsArabicFirst = false;
        Boolean IsArabicSecond = false;
        Boolean IsRomanFirst = false;
        Boolean IsRomanSecond = false;
        Integer IntFirst = 0;
        Integer IntSecond = 0;

        //checking if first and second numbers are arabic
        for (String number : ArabicNumbers) {
            if (Words[0].equals(number)) {
                IsArabicFirst = true;
            }
            if (Words[2].equals(number)) {
                IsArabicSecond = true;
            }
        }
        if (IsArabicFirst != IsArabicSecond) {
            throw new Exception ("Different number systems are used at the same time or out of range from 1 to 10");
        }
        //checking if first and second numbers are roman
        for (Integer i = 1; i<=10;i++) {
            if (Words[0].equals(RomanNumbers[i-1])) {
                IsRomanFirst = true;
                IntFirst = i;
            }
            if (Words[2].equals(RomanNumbers[i-1])) {
                IsRomanSecond = true;
                IntSecond = i;
            }
        }
        if (IsRomanFirst != IsRomanSecond) {
            throw new Exception ("Different number systems are used at the same time or out of range from 1 to 10");
        }
        //Arithmetic operations with arabic numbers
        if (IsArabicFirst == true) {
            IntFirst = Integer.parseInt(Words[0]);
            IntSecond = Integer.parseInt(Words[2]);
            if (Words[1].equals("+")) {
                return ConvertToRoman(IntFirst + IntSecond);
            } else if (Words[1].equals("-")) {
                return ConvertToRoman(IntFirst - IntSecond);
            } else if (Words[1].equals("/")) {
                return ConvertToRoman(IntFirst / IntSecond);
            } else if (Words[1].equals("*")) {
                return ConvertToRoman(IntFirst * IntSecond);
            } else {
                throw new Exception ("String is not a mathematical operation");
            }
        } else {

            //Arithmetic operations with roman numbers
            if (IsRomanFirst == true) {
                if (Words[1].equals("+")) {
                    return ConvertToRoman(IntFirst + IntSecond);
                } else if (Words[1].equals("-")) {
                    if (IntFirst - IntSecond <= 0) {
                        throw new Exception ("There are no negative numbers in the roman numeral system");
                    } else{
                        return ConvertToRoman(IntFirst - IntSecond);
                    }
                } else if (Words[1].equals("/")) {
                    return ConvertToRoman(IntFirst / IntSecond);
                } else if (Words[1].equals("*")) {
                    return ConvertToRoman(IntFirst * IntSecond);
                } else {
                    throw new Exception ("String is not a mathematical operation");
                }
            }
        }
        throw new Exception ("Object is not a number");
    }
// gets arabic number (integer not more than 199) and converts it to roman, returns string
    static String ConvertToRoman(int n) {
        Integer[] ArabicValue = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] RomanValue = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String ReturnString = "";
        for(Integer i=0;i<=8;i++) {
            if(ArabicValue[i] <= n) {
                ReturnString += RomanValue[i];
                n = n - ArabicValue[i];
            }
        }
        return ReturnString;
    }

    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(calc(input));
    }
}