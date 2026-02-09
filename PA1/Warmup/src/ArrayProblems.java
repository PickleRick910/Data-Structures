public class ArrayProblems {

    // Sort array using a custom implementation of insertion sort
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        for (int i = 1; i < nums.length; i++) {
            int currentElement = nums[i];
            int j = i - 1;

            while (j >= 0 && nums[j] > currentElement) {
                nums[j + 1] = nums[j];
                j--;
            }

            nums[j + 1] = currentElement;
        }

        return nums;
    }

    // Find kth largest element using a modified selection algorithm
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        int targetIndex = nums.length - k;
        int left = 0;
        int right = nums.length - 1;

        while (true) {
            int pivotIndex = partitionArray(nums, left, right);
            if (pivotIndex == targetIndex) {
                return nums[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private static int partitionArray(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }

        swap(nums, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}