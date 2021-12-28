package Other;

import 公共类.Person;
import Tools.tools.Asserts;

public class Main {
    public static void main(String[] args) {
        MyArrayListMy<Integer> arrayList = new MyArrayListMy();

        {
            for (int i = 1; i <= 10; i++) {
                arrayList.add(i * 10);
            }
            System.out.println(arrayList);
        }

        {
            arrayList.set(2, 31);
            System.out.println(arrayList);
            Asserts.test(arrayList.get(2) == 31);
            Asserts.test(arrayList.size() == 10);
        }

        {
            int elementRemoved = arrayList.remove(3);
            Asserts.test(elementRemoved == 40);
            Asserts.test(arrayList.size() == 9);
            Asserts.test(arrayList.get(3) == 50);
        }

        {
            for (int i = 0; i < 20; i++) {
                arrayList.add(i * 10);
            }
            System.out.println(arrayList);
        }

        {
            MyArrayListMy<Person> personArrayList = new MyArrayListMy<Person>();
            for (int i = 0; i < 20; i++) {
                Person p = new Person(new String("name" + i), i + 1);
                personArrayList.add(p);
            }
            System.out.println(personArrayList);
        }

    }

    // static void ArrayListTest() {
    //     Other.MyArrayListMy arrayListInteger = new Other.MyArrayListMy();
    //
    //     for (int i = 1; i <= 20; i++) {
    //         arrayListInteger.add(i * 10);
    //     }
    //     System.out.println(arrayListInteger);
    //
    //     arrayListInteger.set(2, 31);
    //     Asserts.test(arrayListInteger.get(2) == 31);
    //     Asserts.test(arrayListInteger.size() == 20);
    //
    //     arrayListInteger.add(arrayListInteger.size(), 210);
    //     Asserts.test(arrayListInteger.get(arrayListInteger.size() - 1) == 210);
    //     Asserts.test(arrayListInteger.size() == 21);
    //
    //     arrayListInteger.add(1, 15);
    //     Asserts.test(arrayListInteger.get(1) == 15);
    //     Asserts.test(arrayListInteger.size() == 22);
    //
    //     Asserts.test(arrayListInteger.remove(1) == 15);
    //     Asserts.test(arrayListInteger.size() == 21);
    //     System.gc();
    //
    //     Asserts.test(arrayListInteger.get(2) == 31);
    //     Asserts.test(arrayListInteger.size() == 21);
    //
    //     Other.MyArrayListMy<Person> arrayListPerson = new Other.MyArrayListMy<>();
    //     arrayListPerson.add(new Person("Jack", 28));
    //     arrayListPerson.add(new Person("Rose", 30));
    //     arrayListPerson.add(new Person("Tom", 10));
    //     System.out.println(arrayListPerson);
    //
    //     System.out.println(arrayListPerson.remove(1));
    //     System.out.println(arrayListPerson);
    //
    //     arrayListPerson.add(1, null);
    //     System.out.println(arrayListPerson);
    // }
}
