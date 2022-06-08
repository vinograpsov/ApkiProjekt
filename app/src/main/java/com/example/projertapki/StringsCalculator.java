package com.example.projertapki;

import java.util.Stack;

public class StringsCalculator {
    private Stack<Character> operatorStack;
    private Stack<Double> valueStack;
    private boolean error;

    public StringsCalculator() {
        operatorStack = new Stack<Character>();
        valueStack = new Stack<Double>();
        error = false;
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private int getPrecedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        }
        if (ch == '*' || ch == '/') {
            return 2;
        }
        return 0;
    }

    private void processOperator(char t) {
        double a, b;
        if (valueStack.empty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            b = valueStack.peek();
            valueStack.pop();
        }
        if (valueStack.empty()) {
            System.out.println("Expression error.");
            error = true;
            return;
        } else {
            a = valueStack.peek();
            valueStack.pop();
        }
        double r = 0;
        if (t == '+') {
            r = a + b;
        } else if (t == '-') {
            r = a - b;
        } else if (t == '*') {
            r = a * b;
        } else if(t == '/') {
            r = a / b;
        } else {
            System.out.println("Operator error.");
            error = true;
        }
        valueStack.push(r);
    }

    public double processInput(String input) {
        String[] tokens = input.split(" ");

        for (int n = 0; n < tokens.length; n++) {
            String nextToken = tokens[n];
            char ch = nextToken.charAt(0);
            if (ch >= '0' && ch <= '9') {
                double value = Double.parseDouble(nextToken);
                valueStack.push(value);
            } else if (isOperator(ch)) {
                if (operatorStack.empty() || getPrecedence(ch) > getPrecedence(operatorStack.peek())) {
                    operatorStack.push(ch);
                } else {
                    while (!operatorStack.empty() && getPrecedence(ch) <= getPrecedence(operatorStack.peek())) {
                        char toProcess = operatorStack.peek();
                        operatorStack.pop();
                        processOperator(toProcess);
                    }
                    operatorStack.push(ch);
                }
            } else if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
                    char toProcess = operatorStack.peek();
                    operatorStack.pop();
                    processOperator(toProcess);
                }
                if (!operatorStack.empty() && operatorStack.peek() == '(') {
                    operatorStack.pop();
                } else {
                    System.out.println("Error: unbalanced parenthesis.");
                    error = true;
                }
            }

        }

        while (!operatorStack.empty() && isOperator(operatorStack.peek())) {
            char toProcess = operatorStack.peek();
            operatorStack.pop();
            processOperator(toProcess);
        }

        double result = valueStack.peek();
        if (error == false) {
            valueStack.pop();
            if (!operatorStack.empty() || !valueStack.empty()) {
                System.out.println("Expression error.");
            } else {
                System.out.println("The result is " + result);
            }
        }
        return result;
    }
}
