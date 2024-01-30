public class DynamicArray {
    private static final int DEFAULT_ARRAY_BLOCK_SIZE = 8;
    private int[] array;
    private int nextEmptyIndex;

    public DynamicArray() {
        array = new int[DEFAULT_ARRAY_BLOCK_SIZE];
    }

    public DynamicArray(int[] array) {
        this.array = array;
        nextEmptyIndex = array.length;
    }

    @Override
    public boolean equals(Object o) {
        // Check if passed object is this object
        if (o == this) {
            return true;
        }

        // Check if o is an instance of DynamicArray. If it isn't then we can't compare it
        if (!(o instanceof DynamicArray)) {
            return false;
        }

        DynamicArray incomingArray = (DynamicArray) o;

        // NOTE: can use Arrays.equals() here instead of doing manually
        int i = 0;
        for (int v : incomingArray.array) {
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
        int[] newArray = new int[array.length + DEFAULT_ARRAY_BLOCK_SIZE];

        int i = 0;
        for (int element : array) {
            newArray[i] = element;
            i++;
        }

        nextEmptyIndex = array.length;
        array = newArray;
    }

    public void append(int value) {
        if (nextEmptyIndex >= array.length) {
            grow();
        }

        array[nextEmptyIndex] = value;
        nextEmptyIndex++;
    }

    public void insert(int value, int position) {
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

        array[position] = 0;

        for (int i = position; i < array.length; i++) {
            if (i + 1 == nextEmptyIndex) {
                array[i] = 0;
                nextEmptyIndex--;
                break;
            }

            array[i] = array[i + 1];
        }
    }

    public void deleteByValue(int value) {
        if (array.length < 1) {
            throw new IndexOutOfBoundsException("Array is zero length, can't delete");
        }

        int i = 0;
        for (int v : array) {
            if (v == value) {
                array[i] = 0;
            }
            i++;
        }
    }
}
