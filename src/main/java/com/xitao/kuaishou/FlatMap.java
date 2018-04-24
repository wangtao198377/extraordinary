package com.xitao.kuaishou;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 问题大概如下所述：
 * 场景是Json类型的数据，例如：
 *
 *

 {
 aa:bb;
 "employees":
 { "firstName":"John" , "lastName":"Doe" },
 }
 转变为扁平的Map结构
 如下：
 {
 aa:bb,
 employees.firstName:John,
 employees.lastName:Doe,
 }

 用循环压栈来避免递归也太搞笑了点吧，手工递归就不是递归了？递归化循环是对算法的优化，比如计算Fibonacci 数列的值，F（n）=F（n-1)+F(n-2);F(0)=1;F(1)=1;
 要算F(100),你可以从F（100）递归往下算，也可以从F（2）开始循环往上算。递归化循环是要通过算法的优化避免压栈操作，而从栈底向上循环得到结果。
 栈底不可预见的时候，递归是无法有效地化为循环的。

 作者：高木端
 链接：https://www.zhihu.com/question/20418254/answer/15081000
 来源：知乎
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 有限递归都可以转换为迭代
 但是貌似这个问题是个无限递归 因为永远不知道有多少层Map 也就是不知道终止条件==>貌似是错的

 判断条件就是一个list等于空  把需要递归的东西放在list里面  一直循环调用

 文件夹的遍历
 爬台阶问题

 甚至到现在为止没有解决的

 从n个不同的数中取出m个数的所有组合，都可以通过此方式来解决。
 */

public class FlatMap {

    //递归实现
    public static void convert(HashMap<String,Object> originalMap, String preKey, HashMap<String,String> resultMap) {

        Set<Map.Entry<String,Object>>  entries =   originalMap.entrySet();
        for(Map.Entry<String,Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String currentKey = key;
            if(!StringUtils.isEmpty(preKey)) {
                currentKey = preKey +"."+currentKey;
            }

            if(value instanceof  String) {

                resultMap.put(currentKey,(String)value);
            } else {

                convert((HashMap)value,currentKey,resultMap);
            }
        }
    }


    //非递归实现
    public static void convert2(HashMap<String,Object> originalMap, HashMap<String,String> resultMap) {

        List<Node> nodes = getNodes(originalMap, resultMap,null);

        while(!CollectionUtils.isEmpty(nodes)) {
            List<Node> tempNodes = new ArrayList<>();
            for(Node node : nodes) {
                HashMap mapValue = node.map;
                String prefixKey = node.prefixKey;
                List<Node> subNodes = getNodes(mapValue,resultMap,prefixKey );
                tempNodes.addAll(subNodes);
            }
            nodes =tempNodes;
        }
    }

    private static List<Node> getNodes(HashMap<String, Object> originalMap, HashMap<String, String> resultMap,String prefixKey) {
        Set<Map.Entry<String,Object>> entries =   originalMap.entrySet();
        List<Node> nodes = new ArrayList<>();
        for(Map.Entry<String,Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String currentKey = key;
            if(!StringUtils.isEmpty(prefixKey)) {
                currentKey = prefixKey+"."+currentKey;
            }

            if(value instanceof  String) {
                resultMap.put(currentKey,(String)value);
            } else {
                Node node = new Node();
                node.prefixKey = currentKey;
                node.map = (HashMap)value;
                nodes.add(node);
            }
        }
        return nodes;
    }

    static class Node {
        public String prefixKey;
        public HashMap<String,Object> map;
    }


    public static void main(String[] args) {
        HashMap<String,String> resultMap = new HashMap<>();

        HashMap<String,Object> originalMap = new HashMap<>();
        HashMap<String,String> secondMap = new HashMap<>();

        originalMap.put("aa","bb");
        originalMap.put("employees",secondMap);
        secondMap.put("firstName","John");
        secondMap.put("lastName","Doe");
        convert(originalMap,null,resultMap);
        System.out.println(resultMap);
        HashMap<String,String> resultMap2 = new HashMap<>();
        convert2(originalMap,resultMap2);
        System.out.println(resultMap2);

    }


}
