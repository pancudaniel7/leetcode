// url: https://leetcode.com/problems/add-two-numbers/

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Size = getSize(l1);
        int l2Size = getSize(l2);

        ListNode head = l1Size > l2Size ? l1 : l2;
        ListNode head2 = l1Size > l2Size ? l2 : l1;

        ListNode headResult = new ListNode(0);
        ListNode resultList = headResult;

        int sum = 0, reminder = 0, digit = 0;
        while (head != null && head2 != null) {

            sum = head.val + head2.val + reminder;
            reminder = sum / 10;
            digit = sum % 10;

            head = head.next;
            head2 = head2.next;

            headResult.val = digit;
            if (reminder == 1 || head != null)
                headResult.next = new ListNode(reminder);
            headResult = headResult.next;
        }

        while (head != null) {
            sum = head.val + headResult.val;
            reminder = sum / 10;
            digit = sum % 10;

            head = head.next;

            headResult.val = digit;
            if (reminder == 1 || head != null)
                headResult.next = new ListNode(reminder);
            headResult = headResult.next;
        }

        return resultList;
    }

    public int getSize(ListNode l1) {
        int size = 0;
        ListNode head = l1;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));

//        ListNode l1 = new ListNode(3, new ListNode(7));
//        ListNode l2 = new ListNode(9, new ListNode(2));

        Solution solution = new Solution();

        ListNode root = solution.addTwoNumbers(l1, l2);
    }
}