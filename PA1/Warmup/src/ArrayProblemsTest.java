import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ArrayProblemsTest {

    @Test
    void testSortArray() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ArrayProblems.sortArray(new int[]{5, 2, 3, 1, 4}));
        assertArrayEquals(new int[]{1, 1, 2, 2, 3}, ArrayProblems.sortArray(new int[]{3, 2, 1, 2, 1}));
        assertArrayEquals(new int[]{1}, ArrayProblems.sortArray(new int[]{1}));
        assertArrayEquals(new int[]{}, ArrayProblems.sortArray(new int[]{}));
        assertNull(ArrayProblems.sortArray(null));
    }

    @Test
    void testFindKthLargest() {
        assertEquals(5, ArrayProblems.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        assertEquals(4, ArrayProblems.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        assertEquals(1, ArrayProblems.findKthLargest(new int[]{1}, 1));
        assertEquals(3, ArrayProblems.findKthLargest(new int[]{3,3,3,3,3}, 1));
    }

    @Test
    void testFindKthLargestExceptions() {
        assertThrows(IllegalArgumentException.class, () -> ArrayProblems.findKthLargest(new int[]{1,2,3}, 0));
        assertThrows(IllegalArgumentException.class, () -> ArrayProblems.findKthLargest(new int[]{1,2,3}, 4));
        assertThrows(IllegalArgumentException.class, () -> ArrayProblems.findKthLargest(new int[]{}, 1));
        assertThrows(IllegalArgumentException.class, () -> ArrayProblems.findKthLargest(null, 1));
    }
}