package esam;

import java.math.BigDecimal;

/**
 * Created by zhaobofan on 16/9/2.
 */
public class Math3 {
    public static void main(String args[]){
        //斐波那契数列'
        jiajiajia();
        //decimalJia();

    }
    public static void decimalJia(){
        BigDecimal x=new BigDecimal(1);
        BigDecimal y=new BigDecimal(1);
        BigDecimal tmp;
        for(int i=3;i<100;i++){
            tmp=x;
            x=x.add(y);
            y=tmp;
            System.out.println(tmp);
        }
    }

    public static void jiajiajia(){
        Long num1=1l;
        Long num2=1l;
        Long tmp;
        for(int i=3;i<100;i++){
            tmp=num2;
            num2=num1+num2;
            num1=tmp;
            System.out.println("第"+i+"个斐波那契数列的数字为:"+num2);
        }
    }
}
