package com.xitao.sort;

public class RadixSort {
private static void radixSort(int[] array,int d)
{
    int n=1;//代表位数对应的数：1,10,100...
    int k=0;//保存每一位排序后的结果用于下一位的排序输入
    int length=array.length;
    int[][] bucket=new int[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
    int[] order=new int[10];//用于保存每个桶里有多少个数字
    while(n<d)
    {
        for(int num:array) //将数组array里的每个数字放在相应的桶里
        {
            int digit=(num/n)%10;
            bucket[digit][order[digit]]=num;
            order[digit]++;
        }
        for(int i=0;i<10;i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
        {
            if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
            {
                for(int j=0;j<order[i];j++)
                {
                    array[k]=bucket[i][j];
                    k++;
                }
            }
            order[i]=0;//将桶里计数器置0，用于下一次位排序
        }
        n*=10;
        k=0;//将k置0，用于下一轮保存位排序结果
    }
    
}
public static void main(String[] args)
{
    long start = System.currentTimeMillis();
    int[] A=TestUtils.createRandomArray(20,1000);
    radixSort(A, 1000);
    System.out.println(System.currentTimeMillis()-start);


    for(int num:A)
    {
        System.out.println(num);
    }
}
}