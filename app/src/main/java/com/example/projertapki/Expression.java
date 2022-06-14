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

    public static ArrayList<Double> valuesToButtons(double right_value){
        ArrayList<Double> answer = new ArrayList<>();
        answer.add(right_value);
        for(int i = 0; i < 3;i++) {
            double value = Math.round((Math.random() * (right_value + 20) + right_value - 20) * 10.0) / 10.0;
            if((right_value * 10) % 10 == 0){
                value -= ((value * 10) % 10) * 0.1;
                value = Math.round(value*1.0)/1.0;
            }
            answer.add(value);
        }
        return answer;
    }
}
