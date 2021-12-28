package 公共类;

public class Person implements Comparable<Person> {

    private int age;

    private String name;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
    }

    public int compareTo(Person o) {
        return this.getAge() - o.getAge();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
