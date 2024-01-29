public class DynamicArray {
    private int[] array;

    public DynamicArray() {
        array = new int[0];
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public void append(int value) {
        int[] newArray = new int[array.length + 1];
        newArray[newArray.length - 1] = value;
        array = newArray;
    }

    public void insert(int value, int position) {
        int[] newArray = new int[array.length + 1];
        int j = 0;

        for (int i = 0; i < newArray.length; i++) {
            if (i == position) {
                newArray[i] = value;
            } else {
                newArray[i] = array[j];
                j++;
            }
        }

        array = newArray;
    }

    public void deleteByPosition(int position) {
        if (array.length < 1) {
            throw new IndexOutOfBoundsException("Array is zero length, can't delete");
        }

        int[] newArray = new int[array.length - 1];
        int j = 0;

        for (int i = 0; i < array.length; i++) {
            if (i == position) {
                continue;
            } else {
                newArray[j] = array[i];
            }
            j++;
        }

        array = newArray;
    }

    public void deleteByValue(int value) {
        if (array.length < 1) {
            throw new IndexOutOfBoundsException("Array is zero length, can't delete");
        }

        int numberOfValuesToDelete = 0;
        for (int v : array) {
            if (v == value) {
                numberOfValuesToDelete++;
            }
        }

        int[] newArray = new int[array.length - numberOfValuesToDelete];
        int j = 0;

        //NOTE: ignoring suggestion for enhanced for here, as the index is useful to me in assigning the new array
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                continue;
            } else {
                newArray[j] = array[i];
            }
            j++;
        }

        array = newArray;
    }
}
