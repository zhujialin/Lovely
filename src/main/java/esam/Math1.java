package esam;

/**
 * Created by zhaobofan on 16/9/2.
 */
public class Math1 {
    public static void main(String args[]){
        //99乘法表'
        nineNineMulti();
    }
    public static void nineNineMulti(){
        for(int i=1;i<10;i++){
            for(int j=1;j<=i;j++){
                System.out.print(" "+i+"*"+j+"=" + i * j);
            }
            System.out.print("\n");
        }
    }
}
