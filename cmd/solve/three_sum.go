package solve

import (
	"sort"
)

/*
15. 3Sum

LeetCode: https://leetcode.com/problems/3sum/

Given an integer array nums, return all unique triplets [nums[i], nums[j],
nums[k]] such that i, j, and k are distinct and nums[i] + nums[j] + nums[k] ==
0.

The solution set must not contain duplicate triplets.
*/
func threeSum(nums []int) [][]int {
	sort.Ints(nums)
	results := [][]int{}

	for i := 0; i < len(nums)-2; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		left := i + 1
		right := len(nums) - 1

		for left < right {
			sum := nums[i] + nums[left] + nums[right]
			if sum == 0 {
				results = append(results, []int{nums[i], nums[left], nums[right]})
				left++
				right--

				for left < right && nums[left] == nums[left-1] {
					left++
				}
				for left < right && nums[right] == nums[right+1] {
					right--
				}
			} else if sum < 0 {
				left++
			} else {
				right--
			}
		}
	}

	return results
}
