package solve

/*
1. Two Sum

LeetCode: https://leetcode.com/problems/two-sum/

Given an array of integers nums and an integer target, return indices of the two
numbers such that they add up to target.

Each input has exactly one solution, and the same element may not be used twice.
Return the answer in any order.
*/
func twoSum(nums []int, target int) []int {
	dict := make(map[int]int)

	for i, num := range nums {
		seek := target - nums[i]
		if idx, ok := dict[seek]; ok {
			return []int{idx, i}
		}
		dict[num] = i
	}
	return nil
}
