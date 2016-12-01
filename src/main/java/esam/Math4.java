package esam;

/**
 * Created by zhaobofan on 16/9/2.
 */
public class Math4 {
    public static void main(String args[]){
        //冒泡排序,从大到小
        int needSortArray[] ={24,6,1,5,3454,54325,5464,21324,345,45,54632234,3453};
        maopao(needSortArray);
    }
    public static void maopao(int[] x){
        int len=x.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++) {
                if (x[j]>x[i]) {
                    int tmp=x[j];
                    x[j]=x[i];
                    x[i]=tmp;
                }
            }
        }
        for(int k=0;k<len;k++){
            System.out.println(x[k]);
        }
    }
}
