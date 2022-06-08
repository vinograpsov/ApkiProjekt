package com.example.projertapki;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    public static String getExpression(int level){
        ExpressionsMaker maker = new ExpressionsMaker();
        return maker.makeExpression(level);
    }


    public static double solveExpression(String expression){
        StringsCalculator polishNotationCalc = new StringsCalculator();
        double answer = Math.round(polishNotationCalc.processInput(expression) * 1000.0) / 1000.0;
        return answer;
    }

    public static List<Double> valuesToButtons(double right_value){
        List<Double> answer = new ArrayList<>();
        answer.add(right_value);
        for(int i = 0; i < 3;i++) {
            double value = Math.round((Math.random() * (right_value + 20) + right_value - 20) * 1000.0) / 1000.0;
            answer.add(value);
        }
        return answer;
    }
}
