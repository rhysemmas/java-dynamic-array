import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    @Test
    @DisplayName("Compare two equal <Integer> dynamic arrays")
    void testEqualsAndHashCodeForInteger() {
        Integer[] intArray = new Integer[]{1, 2, 3};
        DynamicArray<Integer> array = new DynamicArray<>(intArray);
        DynamicArray<Integer> identicalArray = new DynamicArray<>(intArray);

        assertTrue(array.equals(identicalArray));
        assertTrue(array.hashCode() == identicalArray.hashCode());
    }

    @Test
    @DisplayName("Compare two equal <String> dynamic arrays")
    void testEqualsAndHashCodeForString() {
        String[] stringArray = new String[]{"i", "like", "eggs"};
        DynamicArray<String> array = new DynamicArray<>(stringArray);
        DynamicArray<String> identicalArray = new DynamicArray<>(stringArray);

        assertTrue(array.equals(identicalArray));
        assertTrue(array.hashCode() == identicalArray.hashCode());
    }

    @Test
    @DisplayName("Compare two unequal dynamic arrays")
    void testNotEquals() {
        Integer[] intArray = new Integer[]{1, 2, 3};
        String[] stringArray = new String[]{"hi", "hello"};

        DynamicArray<Integer> array = new DynamicArray<>(intArray);
        DynamicArray<String> nonIdenticalArray = new DynamicArray<>(stringArray);

        assertFalse(array.equals(nonIdenticalArray));
        assertFalse(array.hashCode() == nonIdenticalArray.hashCode());
    }

    @Test
    @DisplayName("Append to the dynamic array")
    void testAppend() {
        DynamicArray<Integer> actualArray = new DynamicArray<>();
        DynamicArray<Integer> expectedArray = new DynamicArray<>(new Integer[]{1, 2, null, null, null, null, null, null});

        actualArray.append(1);
        actualArray.append(2);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Append to the dynamic array until it grows")
    void testAppendGrows() {
        DynamicArray<Integer> actualArray = new DynamicArray<>();
        DynamicArray<Integer> expectedArray = new DynamicArray<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});

        for (int i = 0; i < 16; i++) {
            actualArray.append(i + 1);
        }

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Insert into the dynamic array")
    void testInsert() {
        DynamicArray<Integer> actualArray = new DynamicArray<>(new Integer[]{1});
        DynamicArray<Integer> expectedArray = new DynamicArray<>(new Integer[]{1, 10, 2});

        actualArray.insert(2, 1);
        actualArray.insert(10, 1);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Insert past the end of the logical array fails")
    void testInsertPastEndFails() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.insert(2, 1));
    }

    @Test
    @DisplayName("Delete from the dynamic array by position")
    void testDeleteByPosition() {
        DynamicArray<Integer> actualArray = new DynamicArray<>(new Integer[]{1, 2, 3, 4});
        DynamicArray<Integer> expectedArray = new DynamicArray<>(new Integer[]{1, 2, 3, null});

        actualArray.deleteByPosition(3);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Delete from the dynamic array with a partly filled chunk by position")
    void testDeleteByPositionPartChunk() {
        DynamicArray<Integer> actualArray = new DynamicArray<>(new Integer[]{1, 2, 3, 4, null, null, null, null});
        DynamicArray<Integer> expectedArray = new DynamicArray<>(new Integer[]{1, 2, 4, null, null, null, null, null});

        actualArray.deleteByPosition(2);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Delete from the dynamic array with a full chunk by position")
    void testDeleteByPositionFullChunk() {
        DynamicArray<Integer> actualArray = new DynamicArray<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        DynamicArray<Integer> expectedArray = new DynamicArray<>(new Integer[]{1, 2, 3, 5, 6, 7, 8, null});

        actualArray.deleteByPosition(3);

        assertTrue(actualArray.equals(expectedArray));
    }

    @Test
    @DisplayName("Delete from the dynamic array by value")
    void testDeleteByValue() {
        DynamicArray<Integer> actualArray = new DynamicArray<>(new Integer[]{1, 2, 3, 2, 1});
        DynamicArray<Integer> expectedArray = new DynamicArray<>(new Integer[]{1, 3, 1});

        actualArray.deleteByValue(2);

        assertTrue(actualArray.equals(expectedArray));
    }


    @Test
    @DisplayName("Deleting from empty dynamic array fails")
    void testDeleteFromEmptyArrayFails() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>(new Integer[]{});

        assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.deleteByPosition(0));
    }
}