package com.nw;

import java.util.Locale;
import java.util.TreeMap;

class RomanCalculator extends CalculatorBase {
    private final static TreeMap map = new TreeMap();

    static{
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
    }

    public RomanCalculator(ArithmeticExpression expression) {
        super(expression);
        this.leftOperand = toArabic(expression.getLeftOperand());
        this.rightOperand = toArabic(expression.getRightOperand());

        if (leftOperand > 10 || rightOperand > 10) {
            throw new IllegalArgumentException("На ввод принимаются числа от нуля до десяти включительно.");
        }
    }

    private int toArabic(String operand) throws IllegalArgumentException {
        String num = operand.toUpperCase(Locale.ENGLISH);
        int res = 0;
        var romanNums= RomanNumbers.getValuesReversed();
        int i = 0;

        while ((num.length() > 0) && (i < romanNums.size())) {
            RomanNumbers n = romanNums.get(i);
            if (num.startsWith(n.name())) {
                res += n.getValue();
                num = num.substring(n.name().length());
            } else {
                i++;
            }
        }

        if (num.length() > 0) {
            throw new IllegalArgumentException("Некорректный ввод.");
        }

        return res;
    }

    private String toRoman(int num) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        var romanNums = RomanNumbers.getValuesReversed();

        for (int i = 0; num > 0 && i < romanNums.size();) {
            RomanNumbers currentNum = romanNums.get(i);
            if (currentNum.getValue() <= num) {
                sb.append(currentNum.name());
                num -= currentNum.getValue();
            } else i++;
        }

        return sb.toString();
    }

    @Override
    protected String getResult() {
        if (result < 1) throw new RuntimeException("Римские числа не могут быть отрицательными!");

        return toRoman(result);
    }
}
