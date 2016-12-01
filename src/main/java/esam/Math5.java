package esam;

/**
 * Created by zhaobofan on 16/9/5.
 * 用100文钱买一百只鸡,其中公鸡，母鸡，小鸡都必须要有，
 * 问公鸡，母鸡，小鸡要买多少只刚好凑足100文钱。
 * 请写一个程序算出来
 */
public class Math5 {
    public static void main(String args[]) {
        xiaoji();
    }

    private static void xiaoji() {
        int x=0,y=0;
        double z=0;
        for(x=1;x<20;x++){
            for(y=1;y<32;y++){
                for(z=1;z<277;z++){
                    if((5*x+3*y+z/3)==100){
                        System.out.println("公鸡:"+x+"母鸡:"+y+"小鸡:"+(int)z);
                    }
                }
            }
        }
    }
}
