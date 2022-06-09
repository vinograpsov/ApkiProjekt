package com.example.projertapki;

import java.util.Arrays;
import java.util.List;

public class ExpressionsMaker {
    private List<Integer> randomValuesForExample(int length){
        List<Integer> vec = Arrays.asList(new Integer[length]);
        for(int i = 0; i < length; i++){
            vec.set(i,(int)(Math.random() * 11) + 1);
        }
        return vec;
    }


    private List<Character> randomSigns(int length){
        List<Character> vec = Arrays.asList(new Character[length]);
        for(int i = 0; i < length; i++){
            int temp = (int)(Math.random() * 4);
            if (temp == 0){
                vec.set(i,'+');
            }
            else if (temp == 1){
                vec.set(i,'-');
            }
            else if (temp == 2){
                vec.set(i,'*');
            }
            else{
                vec.set(i,'/');
            }
        }
        return vec;
    }


    public  String makeExpression(int level){
        String expressinon = "";
        int  numOfExpression;
        List<Character> sights;
        List<Integer> nums;
        switch (level){
            case 1:
//                a + b
                sights =  randomSigns(1);
                nums = randomValuesForExample(2);
                expressinon += nums.get(0) + " " + sights.get(0) + " " + nums.get(1);
                break;
            case 2:
//               a + b + c + d
//               a + b + c
                numOfExpression = (int)(Math.random() * 2);
                switch (numOfExpression){
                    case 0:
                        sights =  randomSigns(2);
                        nums = randomValuesForExample(3);
                        expressinon += nums.get(0) + " " + sights.get(0) + " " + nums.get(1) + " " + sights.get(1) + " "
                                + nums.get(2);
                        break;
                    default:
                        sights =  randomSigns(3);
                        nums = randomValuesForExample(4);
                        expressinon += nums.get(0) + " " + sights.get(0) + " " + nums.get(1) + " " + sights.get(1) + " "
                                + nums.get(2)+ " " + sights.get(2) + " " + nums.get(3);
                        break;
                }
                break;
            case 3:
//                (a  + b) + (b + (b + b)
//                ((a + b + c) + a) a
//                (a  + b) + (b + b)
//                a + (b + (b + b)
//                (a  + b) + (b + (b + b)
                numOfExpression = (int)(Math.random() * 5);
                switch (numOfExpression){
                    case 0:
                        sights =  randomSigns(4);
                        nums = randomValuesForExample(5);
                        expressinon += "(" + " " + nums.get(0) + " " + sights.get(0) + " " + nums.get(1)+ " " + ")" + " "  + sights.get(1) + " "
                                + "(" + " "+ nums.get(2)+ " " + sights.get(2)  + " " + "(" + " "+ nums.get(3) + " " + sights.get(3) + " " + nums.get(4) + " "+ ")" +" "+ ")" ;
                        break;
                    case 1:
                        sights =  randomSigns(4);
                        nums = randomValuesForExample(5);
                        expressinon = "(" +" "+ "(" +" "+nums.get(0) + " "+ sights.get(0) + " "+nums.get(1) + " "+ sights.get(1) + " "+ nums.get(2)+ " " + ")"
                                + " "+ sights.get(3) + " "+ nums.get(2) + " "+ ")" + " "+sights.get(3) + " "+nums.get(4);
                        break;
                    case 2:
                        sights =  randomSigns(3);
                        nums = randomValuesForExample(4);
                        expressinon = "(" + " "+ nums.get(0) + " "+ sights.get(0) + " "+ nums.get(1) + " "+ ")" + " "+sights.get(1)+ " "+
                                "(" + " "+ nums.get(2) + " "+ sights.get(2) + " "+ nums.get(3) + " "+ ")";
                        break;
                    case 3:
                        sights =  randomSigns(3);
                        nums = randomValuesForExample(4);
                        expressinon = nums.get(0) + " " + sights.get(0) + " " + "(" + " "+ nums.get(1) + " "+ sights.get(1)+ " "
                                + "(" + " "+ nums.get(2)+ " "+ sights.get(2) + " "+nums.get(3) + " "+ ")" +" "+ ")";
                        break;
                    default:
                        sights =  randomSigns(4);
                        nums = randomValuesForExample(5);
                        expressinon = "(" + " "+ nums.get(0)+ " "+ sights.get(0) + " "+nums.get(1) + " "+ ")" + " "+ sights.get(1)+ " "
                                + "("+ " " + nums.get(2)+ " "+ sights.get(2)+ " "
                                + "("+ " " +nums.get(3)+ " "+ sights.get(3)+ " " +nums.get(4) + " "+ ")" +" " + ")";
                        break;
                }
                break;
            default:
//                 ((a +a ) +(a + a)) + (a + a)
//                 ((a +a ) + a) + (a + a)
//                 a + ((b + b)+ (a + a))
//                 (a +a ) + (a + a) + (a + a)

                numOfExpression = (int)(Math.random() * 5);
                switch (numOfExpression){
                    case 0:
                        sights =  randomSigns(5);
                        nums = randomValuesForExample(6);
                        expressinon = "(" +" "+ "(" + " "+nums.get(0) + " " + sights.get(0) + " " +nums.get(1) + " "+ ")"
                                + " "+ sights.get(1) + " "+ "(" + " "+nums.get(2) + " " + sights.get(2) + " " +nums.get(3) + " " + ")"+" " + ")"+ " "
                                + sights.get(3)+ " "+ "(" + " "+nums.get(4) + " " + sights.get(4) + " " +nums.get(5)+ " " + ")";
                        break;
                    case 1:
                        sights =  randomSigns(4);
                        nums = randomValuesForExample(5);
                        expressinon = "(" +" "+ "(" + " "+nums.get(0) + " " + sights.get(0) + " " +nums.get(1)+ " " + ")"
                                + " " +sights.get(1) + " "+nums.get(2)+ " " + ")"+ " " +sights.get(2)+ " "
                                + "(" + " "+nums.get(3) + " " + sights.get(3) + " " +nums.get(4)+ " " +")";
                        break;
                    case 2:
                        sights =  randomSigns(4);
                        nums = randomValuesForExample(5);
                        expressinon = nums.get(0) + " " + sights.get(0)+ " "
                                + "(" +" "+ "(" + " "+ nums.get(1) + " "+ sights.get(1) + " " + nums.get(2)+ " "+ ")"+ " "
                                +sights.get(2) + " "+ "("+ " " +nums.get(3) + " "+ sights.get(3) + " " + nums.get(1) + " "+ ")" + " " + ")";
                        break;
                    default:
                        sights =  randomSigns(5);
                        nums = randomValuesForExample(6);
                        expressinon =  "(" + " "+ nums.get(0) + " " + sights.get(0) + " " + nums.get(1) + " "+ ")" + " "+  sights.get(1)+ " "
                                + "("+ " " + nums.get(2) + " " + sights.get(2) + " " + nums.get(3) + " "+ ")" + " "+ sights.get(3)+ " "
                                + "(" + " "+ nums.get(4) + " " +  sights.get(4) + " " + nums.get(5) + " "+ ")";
                        break;
                }
        }
        return expressinon;
    }
}
