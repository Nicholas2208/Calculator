package com.nw;

abstract class CalculatorBase {
    protected ArithmeticExpression expression;
    protected int result;
    protected int leftOperand;
    protected int rightOperand;

    public CalculatorBase(ArithmeticExpression expression) {
        this.expression = expression;
        this.result = 0;
    }

    public String calculate() {
        switch (expression.getOperator()) {
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
            case '*':
                result = leftOperand * rightOperand;
                break;
            case '/':
                result = leftOperand / rightOperand;
                break;
            default:
                throw new IllegalArgumentException("Некорректный арифметический оператор.");
        }

        return getResult();
    }

    abstract protected String getResult();
}
