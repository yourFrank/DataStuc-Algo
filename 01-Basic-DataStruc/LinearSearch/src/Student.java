import java.util.Objects;

//自定义类
public class Student {

    private String name;

    public Student(String name){
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {  //注意这里是要覆盖一个方法，因此传入的参数应该和父类一样都是object，而不是Student
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; //进行必要的判断
        Student student = (Student) o; //强转前需要进行上面类型判断
        return Objects.equals(name.toLowerCase(), student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
