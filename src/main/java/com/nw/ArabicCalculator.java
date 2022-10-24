package com.nw;

class ArabicCalculator extends CalculatorBase {

    public ArabicCalculator(ArithmeticExpression expression) {
        super(expression);
        this.leftOperand = Integer.parseInt(expression.getLeftOperand());
        this.rightOperand = Integer.parseInt(expression.getRightOperand());
    }

    @Override
    protected String getResult() {
        return String.valueOf(result);
    }
}
