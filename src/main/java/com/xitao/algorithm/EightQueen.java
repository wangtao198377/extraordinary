package com.xitao.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EightQueen {
    /**
     * 先写一下伪代码
     *  总体思路就是两个循环
     *  一共8行
     *  i代表行
     *  while(0<=x<=8)
     *  因为从0开始
     *  j从0到8
     *
     *  确定了点i j  之后
     *  所有在水平线和垂直线上的点的坐标是
     *  1. x=i;
     *  2. y=j;
     *  3. y=-x+(i-j)
     *  4. y=x+i-j
     *
     *  实际上就是过i  j 点的两条线段
     *  当然 x，y为整数   并且  0<=(x,y)<=8
     */

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
    private boolean ifAtLine(List<Point> origins, Point compare) {
        int compareX = compare.x;
        int compareY = compare.y;
        for(Point origin:origins) {
            int originX = origin.x;
            int originY = origin.y;
            if (compareX == originX) return true;
            if (compareY == originY) return true;
            if (compareY == -compareX + originX + originY) return true;
            if (compareY == compareX  -originX + originY) return true;
        }
        return false;
    }

    @Test
    public void  getSolution() {
        List<List<Point>> lists = new ArrayList<>();
        int liney =0;
        for(int i=0;i<8;i++) {
            List<Point> pointList = new ArrayList<>();
            Point origin = new Point(i,liney);
            pointList.add(origin);
            boolean loop = false;
            for (int j=1;j<8;j++) {
                if(j<1) break;
                int init=0;
                if(loop) {
                    Point last = pointList.get(j);
                    init = last.x+1;
                    pointList.remove(j);
                }
                boolean hasPoint = false;
                for(int ji=init;ji<8;ji++) {
                    Point compare = new Point(ji,j);
                    if(!ifAtLine(pointList,compare)){
                        pointList.add(compare);
                        loop = false;
                        hasPoint = true;
                        break;
                    }
                }
                if(!hasPoint) {
                    j=j-2;
                    loop = true;
                }

            }
            lists.add(pointList);
        }
        for(List<Point> item:lists) {
            if(item.size() == 8)
               System.out.println(item);
        }
    }

    private void printGraph(List<Point> points) {

    }
}
