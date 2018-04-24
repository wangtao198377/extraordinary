package com.xitao.sort;


//基的选择可能是个问题， 如果选比较大的基 或者是2的幂次方作为基进行位运算 速度应该会更快
//如果基越大  数据的遍历时间可能越长， 在额外数组大小和需要排序的数组数量之间需要找一个平衡
//下面实现一个最简单的16进制位的排序
public class RadicSortMine implements Sort {

    public void sort(int[] arr) {
        //记录最大位数
        int max = 1;
        Node[] extraInt = new Node[16];
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            int num = 1;
            while ((current = current >> 4) > 0) {
                num++;
            }
            if (num > max) {
                max = num;
            }
            Node node = new Node(arr[i]);
            int temp = node.getValue() & ((1 << 4) - 1);
            Node pre = extraInt[temp], pos = extraInt[temp];
            while (pos != null) {
                pre = pos;
                pos = pos.next;
            }
            if (pre == null) {
                extraInt[temp] = node;
            } else {
                pre.next = node;
            }
        }

        for (int i = 2; i <= max; i++) {
            Node[] originalNode = new Node[arr.length];
            int m = 0;
            for (int j = 0; j < extraInt.length; j++) {
                Node current = extraInt[j];
                while (current != null) {
                    originalNode[m] = new Node(current.value);
                    m++;
                    current = current.next;
                }
            }
            extraInt = new Node[16];
            for (int k = 0; k < originalNode.length; k++) {
                Node node = originalNode[k];
                int temp = (node.getValue() & ((1 << (4 * i)) - 1)) >> (4*(i-1));
                Node pre = extraInt[temp], pos = extraInt[temp];
                while (pos  != null) {
                    pre = pos;
                    pos = pos.next;
                }
                if (pre == null) {
                    extraInt[temp] = node;
                } else {
                    pre.next = node;
                }
            }
        }

        int index = 0;
        for (int j = 0; j < extraInt.length; j++) {
            Node current = extraInt[j];
            while (current != null) {
               arr[index] = current.value;
                index++;
                current = current.next;
            }
        }
    }

    class Node {
        private int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        TestUtils.test(new RadicSortMine(), 100000, 100);
    }
}
