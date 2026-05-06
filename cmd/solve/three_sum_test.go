package solve

import (
	"reflect"
	"sort"
	"testing"
)

func Test_threeSum(t *testing.T) {
	tests := []struct {
		name string
		nums []int
		want [][]int
	}{
		{
			name: "fewer than three numbers",
			nums: []int{1, 2},
			want: [][]int{},
		},
		{
			name: "no triplets",
			nums: []int{1, 2, 3},
			want: [][]int{},
		},
		{
			name: "all zeros returns one unique triplet",
			nums: []int{0, 0, 0, 0},
			want: [][]int{{0, 0, 0}},
		},
		{
			name: "example one",
			nums: []int{-1, 0, 1, 2, -1, -4},
			want: [][]int{{-1, -1, 2}, {-1, 0, 1}},
		},
		{
			name: "duplicate values do not create duplicate triplets",
			nums: []int{-2, 0, 0, 2, 2},
			want: [][]int{{-2, 0, 2}},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := normalizeTriplets(threeSum(tt.nums))
			want := normalizeTriplets(tt.want)
			if !reflect.DeepEqual(got, want) {
				t.Errorf("threeSum() = %v, want %v", got, want)
			}
		})
	}
}

func normalizeTriplets(triplets [][]int) [][]int {
	normalized := make([][]int, len(triplets))
	for i, triplet := range triplets {
		normalized[i] = append([]int(nil), triplet...)
		sort.Ints(normalized[i])
	}

	sort.Slice(normalized, func(i, j int) bool {
		left := normalized[i]
		right := normalized[j]
		for k := 0; k < len(left) && k < len(right); k++ {
			if left[k] != right[k] {
				return left[k] < right[k]
			}
		}
		return len(left) < len(right)
	})

	return normalized
}
