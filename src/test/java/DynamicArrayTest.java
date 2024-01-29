import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DynamicArrayTest {
    @Test
    @DisplayName("Append to the dynamic array")
    void testAppend() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.append(1);

        int[] expectedArray = {1};

        assertArrayEquals(expectedArray, dynamicArray.getArray());
    }

    @Test
    @DisplayName("Insert into the dynamic array")
    void testInsert() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.setArray(new int[]{1});

        dynamicArray.insert(2, 1);
        dynamicArray.insert(10, 1);

        int[] expectedArray = {1, 10, 2};

        assertArrayEquals(expectedArray, dynamicArray.getArray());
    }

    @Test
    @DisplayName("Delete from the dynamic array by position")
        // TODO: test deletion from different positions in array
    void testDeleteByPosition() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.setArray(new int[]{1, 2});

        dynamicArray.deleteByPosition(0);

        int[] expectedArray = {2};

        assertArrayEquals(expectedArray, dynamicArray.getArray());
    }

    @Test
    @DisplayName("Delete from the dynamic array by value")
    void testDeleteByValue() {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.setArray(new int[]{1, 2, 3, 2, 1});

        dynamicArray.deleteByValue(2);

        int[] expectedArray = {1, 3, 1};

        assertArrayEquals(expectedArray, dynamicArray.getArray());
    }


    @Test
    @DisplayName("Deleting from empty dynamic array fails")
    void testDeleteFromEmptyArrayFails() {
        DynamicArray dynamicArray = new DynamicArray();

        assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.deleteByPosition(0));
    }
}