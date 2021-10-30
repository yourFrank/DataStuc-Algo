
//要求:在给定的数组中查找一个元素的位置，找到返回元素下标，否则返回-1
public class LinearSearch01 {

    private LinearSearch01(){} //细节1:不需要对该类创建对象，我们只需要用它的search方法，因此使用private私有化构造函数

    public static int search(int[] data, int target) { //细节2、static声明方法，我们只需要用它的search方法，不需要创建对象（类比jdk中的Math类）

        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i; //找到返回下标元素
            }
        }
        return -1;    //否则返回-1
    }

    public static void main(String[] args) {
        int[] data={24,18,12,9,16,66,32,4};
        int res=LinearSearch01.search(data,16);
        System.out.println(res);
    }
}
