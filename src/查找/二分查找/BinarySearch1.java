package 查找.二分查找;

import 查找.Search;

public class BinarySearch1<E extends Comparable<E>> extends Search<E> {

    public static int ELEMENT_NOT_FOUND = -1;

    public int search(E[] elements, E target) {
        if (elements == null || elements.length <= 0) {
            throw new IllegalArgumentException();
        }

        return search(elements,
                target,
                0,
                elements.length);
    }

    /**
     * 二分查找
     * <p>
     * 在数组的 [low, high) 区间中查找指定元素
     *
     * @param elements 元素数组
     * @param target   待查找的元素
     * @param low      下界
     * @param high     上界
     *
     * @return 元素位置
     */
    public int search(E[] elements, E target, int low, int high) {
        while (low < high) {
            final int mid = (low + high) >> 1;
            final E midValue = elements[mid];
            final int compareResult = midValue.compareTo(target);
            if (compareResult < 0) {
                low = mid + 1;
            } else if (compareResult > 0) {
                high = mid;
            } else {
                return mid;
            }
        }
        return ELEMENT_NOT_FOUND;
    }
}
