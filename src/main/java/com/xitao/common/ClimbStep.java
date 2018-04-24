package com.xitao.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClimbStep {

  public static   ArrayList<ArrayList<Integer>>  findSteps(int n) {
      ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if( n==1) {
            ArrayList<Integer> temp1 = new ArrayList();
            temp1.add(1);
            result.add(temp1);
            return result;
        }
        if(n==2) {
            ArrayList<Integer> temp1 = new ArrayList();
            temp1.add(1);
            temp1.add(1);
            ArrayList<Integer> temp2 = new ArrayList();
            temp2.add(2);
            result.add(temp1);
            result.add(temp2);
            return result;
        }

      ArrayList<ArrayList<Integer>> preLs = findSteps(n-1);
        for(ArrayList<Integer> tempLs: preLs) {
            tempLs.add(1);
        }
      result.addAll(preLs);

      ArrayList<ArrayList<Integer>> preLs2 = findSteps(n-2);
        for(ArrayList<Integer> tempLs: preLs2) {
            tempLs.add(2);
        }
      result.addAll(preLs2);

      return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> result = findSteps(5);
        System.out.println(result);
    }
}
