package com.ty.model;

import java.util.Objects;

public class Student implements Comparable<Student>{//实现comparable接口

    private String name;
    private int score;

    public Student(String name,int score){
        this.name=name;
        this.score=score;
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
    public int compareTo(Student o) {
        return this.score-o.score; //当前元素小于other,则返回负数
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
