
/*LinearSearch01中代码存在的问题: 我们只能对int类型进行查找，如果我们想查找多种类型？自定义类型？
使用泛型解决。（jdk中的所有容器，map,list等都是泛型类）
 */
//public  class LinearSearchWithGeneric02<E> { //此处不在类的地方声明泛型，因为我们主要使用的是里面的search方法
public class LinearSearchWithGeneric02 {

    private LinearSearchWithGeneric02() {
    }

    public static <E> int search(E[] data, E target) { //使用泛型。 可以根据选择传递任意数据类型

        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {  //细节1：此处需要调用equals方法，而不是==比较。因为传入的可能是某个对象
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] data={24,18,12,9,16,66,32,4}; //细节2：泛型不能是基本数据类型，因此使用下面的对应包装类
        Integer[] data = {24, 18, 12, 9, 16, 66, 32, 4};
        int res = LinearSearchWithGeneric02.search(data, 16);
        System.out.println(res);
        Student[] students = {new Student("Alice"),    //使用自定义类
                              new Student("Bobo"),
                              new Student("frank")};
        Student student = new Student("frank");
        int res2 = LinearSearchWithGeneric02.search(students, student);
        System.out.println(res2);
    }
}
