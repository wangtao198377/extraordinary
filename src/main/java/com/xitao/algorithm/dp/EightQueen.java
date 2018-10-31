package com.xitao.algorithm.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EightQueen {


    public static List<List<Point>> solution() {
        List<List<Point>> result = new ArrayList<>();

        int line0=0;
        for(int i=0;i<8;i++) {
            List<Point> points = new ArrayList<>();
            Point anchor = new Point(i,line0);
            points.add(anchor);
            boolean back=false;
            for(int j=1;j<8 && j>=1;) {
                int jiinit =0;
                if(back) {
                   Point last = points.get(j);
                   if(last.x ==7) {
                       points.remove(j);
                       j--;
                       continue;
                   }
                    jiinit = last.x+1;
                    points.remove(j);
                }
                for(int ji=jiinit;ji<8;ji++) {
                    Point pt = new Point(ji,j);
                    if(isValid(points,pt)) {
                        if(j<7) {
                            points.add(pt);
                            j++;
                            back =false;
                        } else {
                            back =true;
                            j--;
                            List<Point> finalLs = new ArrayList<>(points);
                            finalLs.add(pt);
                            System.out.println(finalLs);
                           // printLs(points);
                            result.add(finalLs);
                        }
                        break;
                    }
                    if(ji==7) {
                        back =true;
                        j--;
                        //printLs(points);

                        break;
                    }
                }

            }
        }

        return result;
    }

    public static boolean isValid(List<Point> origins, Point compare) {
        for(Point origin:origins) {
            if (origin.x == compare.x) return false;
            if (origin.y == compare.y) return false;
            if (origin.x - compare.x == origin.y - compare.y) return false;
            if (origin.x - compare.x == -(origin.y - compare.y)) return false;
        }
        return true;
    }

    @Test
    public  void test() {
        List<List<Point>> lists = EightQueen.solution();
        System.out.println("size::"+lists.size());

        System.out.println(lists);
        for(List<Point> item:lists) {
            System.out.println();
            System.out.println();

            printLs(item);
        }

    }

    private  static void printLs (List<Point> points) {
        System.out.println();
        System.out.println();

        if(points.size() <8) {
            int diff = 8-points.size();
            Point zero = new Point(8,0);
            for(int m=0;m<diff;m++) {
                points.add(zero);
            }
        }

        for(int i=points.size()-1;i>=0;i--) {
            Point pt = points.get(i);
            int x=pt.x;
            for(int j=0;j<x;j++) {
                System.out.print("*  ");

            }
            if(x<8) {
                System.out.print("o  ");
            }
            for(int j=x+1;j<8;j++) {
                System.out.print("*  ");
            }
            System.out.println();

        }
    }
}




class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("y:%s,x:%s",y,x);
    }
}


