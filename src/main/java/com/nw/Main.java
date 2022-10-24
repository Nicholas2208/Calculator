package com.nw;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final List<Character> operators = Arrays.asList(new Character[]{'+', '-', '*', '/'});

    public static void main( String[] args ) {
        System.out.println("***********************************************************");
        System.out.println("************************КАЛЬКУЛЯТОР************************");
        System.out.println("***********************************************************");
        System.out.println("Чтобы выйти, нажмите q");
        System.out.println("Вызов справки h\n");

        Scanner scanner = new Scanner(System.in);
        String result;

        while (true) {
            System.out.println("Введите выражение: ");
            String inputLine = scanner.nextLine();
            if (inputLine.equals("q")) break;;
            if (inputLine.equals("h")) {
                printHelp();
                continue;
            }

            ArithmeticExpression expression = parseExpression(inputLine);

            if (expression.isRoman) {
                result = new RomanCalculator(expression).calculate();
            } else {
                result = new ArabicCalculator(expression).calculate();
            }

            System.out.println(result);
        }
    }

    public static ArithmeticExpression parseExpression(String input) {
        String raw = input.replace(" ", "");

        if (raw.length() < 3) {
            throw new IllegalArgumentException("Некорректный ввод. Вызов справки: h");
        }

        String[] operands = new String[2];
        Character operator = null;
        boolean roman = false;

        for (Character op : operators) {
            int index = raw.indexOf(op);
            if (index > 0) {
                operator = raw.charAt(index);
            }
        }

        if (operator != null) {
            String strOperator = operator.toString();
            Pattern p = Pattern.compile(strOperator, Pattern.LITERAL);
            operands = p.split(raw);
        } else {
            throw new IllegalArgumentException("Некорректный арифметический оператор.");
        }

        roman = isRomanExpression(operands[0], operands[1]);

        if (!roman) {
            validateOperands(operands);
        }

        return new ArithmeticExpression(operands[0], operator, operands[1], roman);
    }

    private static void validateOperands(String[] vals) {
        if (!isCorrectIntInput(vals[0]) || !isCorrectIntInput(vals[1]) ) {
            throw new IllegalArgumentException("Некорректный ввод. Вывод справки: h.");
        }
    }

    private static boolean isCorrectIntInput(String strNum) {
        if (strNum == null) {
            return false;
        }

        int i = 0;
        try {
            i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        if (i < 1 || i > 10)  return false;

        return true;
    }
    private static boolean isRomanExpression(String leftOperand, String rightOperand) {
        String regex = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

        return leftOperand.matches(regex) && rightOperand.matches(regex);
    }

    private static void printHelp() {
        System.out.println("*************************************************************");
        System.out.println("Калькулятор производит арифметические операции между двумя числами.");
        System.out.println("Операнды - целые числа от 1 до 10 включительно.");
        System.out.println("Числа могут быть арабские или римские.");
        System.out.println("Поддерживаются операции:");
        System.out.println("Сложение (+)");
        System.out.println("Вычитание (-)");
        System.out.println("Умножение (*)");
        System.out.println("Деление (/)\n");
        System.out.println("Примеры: 2 * 3, 6 / 2, 9 - 3, 10 + 10");
        System.out.println("Примеры: V * III, X / II, IX - III, X + XIII\n");
        System.out.println("Для выхода нажмите q");
        System.out.println("Для вывода справки нажмите h");
        System.out.println("*************************************************************");
    }
}
