package Tools;

public class ArrayTools<E> {

    private void swap(E[] elements, int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
