package com.xitao.kuaishou;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *从n个不同的整数中取出m个整数的所有组合打印出来 m<=n
 * 思路：
 * 递归的思想
 * 假设n为10  m为5
 *
 *   m从1增长到5的时候 分析需要做什么操作
 *   能够在n中取m-1个的基础上算出取m个
 *   m=1的时候  从10个里面取出一个有10种方式
 *   会有一个类似的结构的List
 *   Node {
 *       List<Integer> selected;  size 为m
 *       List<Integer> others;   size为n-m
 *   }
 *
 *   如果取m+1个的话，需要对上面的取m个的做什么处理，才能得到。
 *   上述Node会进行分裂，分裂成n-m个node
 *   变为List size为n-m
 *   Node类似于
 *   Node {
 *       List<Integer> selected;  size 为m+1
 *       List<Integer> others;   size为n-m-1
 *   }
 *
 *   优化防范    利用set  参看 SelectMFromSet
 *
 */

public class SelectMFromN {
    private List<Integer> orginLs;
    public SelectMFromN(List<Integer> orginLs) {
        this.orginLs = orginLs;
    }
    public  List<Node> selectMFromN(int m) {
        if(m==1) {
            List<Node> nodes = new ArrayList<>();
            for(int i=0;i<orginLs.size();i++) {
                Node node= new Node();
                List<Integer> selected = new ArrayList<>();
                selected.add(orginLs.get(i));
                node.selected = selected;
                List<Integer> others= new ArrayList<>();
                others.addAll(orginLs.subList(0,i));
                others.addAll(orginLs.subList(i+1,orginLs.size()));
                node.others = others;
                nodes.add(node);
            }
            return nodes;
        } else {
            List<Node> youngNodes =selectMFromN(m-1);
            if(CollectionUtils.isEmpty(youngNodes)) return null;
            List<Node> currentNode = new ArrayList<>();
            for(Node node:youngNodes) {
                List<Integer> selected = node.selected;
                List<Integer> others = node.others;
                for(int i=0;i<others.size();i++) {
                    List newSelected = new ArrayList();
                    newSelected.addAll(selected);
                    newSelected.add(others.get(i));
                    List newOthers = new ArrayList();
                    newOthers.addAll(others.subList(0,i));
                    newOthers.addAll(others.subList(i+1,others.size()));
                    Node tempNode = new Node();
                    tempNode.selected =newSelected;
                    tempNode.others =newOthers;
                    currentNode.add(tempNode);
                }
            }
            return currentNode;
        }
    }

    class Node {
        public List<Integer> selected;
        public List<Integer> others;

        @Override
        public String toString() {
            return "selected:"+selected.toString()+"_"+"others:"+others.toString();
        }



        @Override
        public int hashCode() {
            if(selected != null)
            return selected.hashCode();
            return -1;
        }
    }

    public static void main(String[] args) {
        List<Integer> originalLs = new ArrayList<>();
        for(int i=1;i<3;i++) {
            originalLs.add(i);
        }
        SelectMFromN selectMFromN = new SelectMFromN(originalLs);
        List<Node> ls = selectMFromN.selectMFromN(2);
        System.out.println(ls);
    }
}
