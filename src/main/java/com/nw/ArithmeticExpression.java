package com.nw;

class ArithmeticExpression {
    String leftOperand;
    Character operator;
    String rightOperand;
    boolean isRoman;

    public ArithmeticExpression(String leftOperand, Character operator, String rightOperand, boolean isRoman) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
        this.isRoman = isRoman;
    }

    public String getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(String leftOperand) {
        this.leftOperand = leftOperand;
    }

    public Character getOperator() {
        return operator;
    }

    public void setOperator(Character operator) {
        this.operator = operator;
    }

    public String getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(String rightOperand) {
        this.rightOperand = rightOperand;
    }

    public boolean isRoman() {
        return isRoman;
    }

    public void setRoman(boolean roman) {
        isRoman = roman;
    }

    @Override
    public String toString() {
        return "ArithmeticExpression{" +
                "leftOperand='" + leftOperand + '\'' +
                ", operator=" + operator +
                ", rightOperand='" + rightOperand + '\'' +
                ", isRoman=" + isRoman +
                '}';
    }
}
