import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicArrayTest {
    @Test
    @DisplayName("Compare two equal dynamic arrays")
    void testEqualsAndHashCode() {
        int[] intArray = new int[]{1, 2, 3};
        DynamicArray array = new DynamicArray(intArray);
        DynamicArray identicalArray = new DynamicArray(intArray);

        assertTrue(array.equals(identicalArray));
        assertTrue(array.hashCode() == identicalArray.hashCode());
    }

    @Test
    @DisplayName("Append to the dynamic array")
    void testAppend() {
        DynamicArray actualArray = new DynamicArray();
        DynamicArray expectedArray = new DynamicArray(new int[]{1, 2, 0, 0, 0, 0, 0, 0});

        actualArray.append(1);
        actualArray.append(2);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Append to the dynamic array until it grows")
    void testAppendGrows() {
        DynamicArray actualArray = new DynamicArray();
        DynamicArray expectedArray = new DynamicArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});

        for (int i = 0; i < 16; i++) {
            actualArray.append(i + 1);
        }

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Insert into the dynamic array")
    void testInsert() {
        DynamicArray actualArray = new DynamicArray(new int[]{1});
        DynamicArray expectedArray = new DynamicArray(new int[]{1, 10, 2});

        actualArray.insert(2, 1);
        actualArray.insert(10, 1);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Delete from the dynamic array by position")
        // TODO: test deletion from different positions in array
    void testDeleteByPosition() {
        DynamicArray actualArray = new DynamicArray(new int[]{1, 2});
        DynamicArray expectedArray = new DynamicArray(new int[]{2});

        actualArray.deleteByPosition(0);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Delete from the dynamic array by value")
    void testDeleteByValue() {
        DynamicArray actualArray = new DynamicArray(new int[]{1, 2, 3, 2, 1});
        DynamicArray expectedArray = new DynamicArray(new int[]{1, 3, 1});

        actualArray.deleteByValue(2);

        assertTrue(actualArray.equals(expectedArray));
    }


    @Test
    @DisplayName("Deleting from empty dynamic array fails")
    void testDeleteFromEmptyArrayFails() {
        DynamicArray dynamicArray = new DynamicArray();

        assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.deleteByPosition(0));
    }
}