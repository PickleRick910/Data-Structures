public class LinkedListProblems {

    /**
     * Removes adjacent duplicates from a sorted linked list.
     * Time complexity: O(n), where n is the number of nodes in the list.
     * Space complexity: O(1), as we only use a constant amount of extra space.
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // Edge case: if the list is empty or has only one node, no duplicates to remove
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;

        // Traverse the list
        while (current != null && current.next != null) {
            // If the current node's value is the same as the next node's value
            if (current.val == current.next.val) {
                // Skip the next node by updating the next pointer
                current.next = current.next.next;
            } else {
                // Move to the next node
                current = current.next;
            }
        }

        return head;
    }

    /**
     * Reverses a singly linked list.
     * Time complexity: O(n), where n is the number of nodes in the list.
     * Space complexity: O(1), as we only use a constant amount of extra space.
     */
    public static ListNode reverseList(ListNode head) {
        // Edge case: if the list is empty or has only one node, no need to reverse
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        // Traverse the list and reverse the links
        while (current != null) {
            // Store the next node
            next = current.next;

            // Reverse the link
            current.next = prev;

            // Move prev and current one step forward
            prev = current;
            current = next;
        }

        // The new head is the last node we processed
        return prev;
    }
}