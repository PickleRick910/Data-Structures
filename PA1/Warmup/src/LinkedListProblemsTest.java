import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LinkedListProblemsTest {

    @Test
    void testDeleteDuplicates() {
        assertArrayEquals(new int[]{1, 2, 3}, listToArray(LinkedListProblems.deleteDuplicates(createList(1, 1, 2, 3, 3))));
        assertArrayEquals(new int[]{1, 2, 3}, listToArray(LinkedListProblems.deleteDuplicates(createList(1, 2, 3))));
        assertArrayEquals(new int[]{1}, listToArray(LinkedListProblems.deleteDuplicates(createList(1, 1, 1))));
        assertNull(LinkedListProblems.deleteDuplicates(null));
    }

    @Test
    void testReverseList() {
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, listToArray(LinkedListProblems.reverseList(createList(1, 2, 3, 4, 5))));
        assertArrayEquals(new int[]{1}, listToArray(LinkedListProblems.reverseList(createList(1))));
        assertNull(LinkedListProblems.reverseList(null));
    }

    private ListNode createList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    private int[] listToArray(ListNode head) {
        if (head == null) return null;
        ListNode current = head;
        int length = 0;
        while (current != null) {
            length++;
            current = current.next;
        }
        int[] result = new int[length];
        current = head;
        for (int i = 0; i < length; i++) {
            result[i] = current.val;
            current = current.next;
        }
        return result;
    }
}