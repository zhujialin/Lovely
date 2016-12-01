package esam;

/**
 * Created by zhaobofan on 16/9/2.
 */
public class Math2 {
    public static void main(String args[]){
        //计算素数个数'
        primeNum();
    }
    public static void primeNum(){
        int count=0;
        for(int i=101;i<200;i+=2){
            boolean prime=true;
            for(int j=2;j<=Math.sqrt(i);j++){
                if(i%j==0){
                    prime=false;
                    break;
                }
            }
            if(prime==true) {
                System.out.println(i + "是素数呀嘿嘿饿");
                count++;
            }
        }
        System.out.println(count+"个素数呀嘿嘿饿");
    }
}
