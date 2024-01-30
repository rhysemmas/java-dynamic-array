public class DynamicArray<T> {
    private static final int DEFAULT_ARRAY_BLOCK_SIZE = 8;
    private T[] array;
    private int nextEmptyIndex;

    public DynamicArray() {
        array = (T[]) new Object[DEFAULT_ARRAY_BLOCK_SIZE];
    }

    public DynamicArray(Object[] array) {
        this.array = (T[]) array;
        nextEmptyIndex = array.length;
    }

    @Override
    public boolean equals(Object o) {
        // Check if passed object is this object
        if (o == this) {
            return true;
        }

        // Check if o is an instance of DynamicArray, if it isn't then we can't compare it
        if (!(o instanceof DynamicArray)) {
            return false;
        }

        DynamicArray<T> incomingArray = (DynamicArray<T>) o;

        int i = 0;
        for (T v : incomingArray.array) {
            if (array[i] != v) {
                return false;
            }
            i++;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 5 * hash + array.length;
        hash = 7 * hash + nextEmptyIndex;
        return hash;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[array.length + DEFAULT_ARRAY_BLOCK_SIZE];

        int i = 0;
        for (T element : array) {
            newArray[i] = element;
            i++;
        }

        nextEmptyIndex = array.length;
        array = newArray;
    }

    public void append(T value) {
        if (nextEmptyIndex >= array.length) {
            grow();
        }

        array[nextEmptyIndex] = value;
        nextEmptyIndex++;
    }

    public void insert(T value, int position) {
        if (position > nextEmptyIndex) {
            throw new IndexOutOfBoundsException("Attempting to insert beyond end of array");
        }

        if (nextEmptyIndex >= array.length) {
            grow();
        }

        for (int i = nextEmptyIndex; i > position; i--) {
            array[i] = array[i - 1];
        }

        array[position] = value;
        nextEmptyIndex++;
    }

    public void deleteByPosition(int position) {
        if (array.length < 1) {
            throw new IndexOutOfBoundsException("Array is zero length, can't delete");
        }

        array[position] = null;

        for (int i = position; i < array.length; i++) {
            if (i + 1 == nextEmptyIndex) {
                array[i] = null;
                nextEmptyIndex--;
                break;
            }

            array[i] = array[i + 1];
        }
    }

    public void deleteByValue(Object value) {
        if (array.length < 1) {
            throw new IndexOutOfBoundsException("Array is zero length, can't delete");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                this.deleteByPosition(i);
                i--;
            }
        }
    }
}
