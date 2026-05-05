package solve

import "testing"

func Test_twoSum(t *testing.T) {
	tests := []struct {
		name   string
		nums   []int
		target int
		want   []int
	}{
		{
			name:   "example one",
			nums:   []int{2, 7, 11, 15},
			target: 9,
			want:   []int{0, 1},
		},
		{
			name:   "example two",
			nums:   []int{3, 2, 4},
			target: 6,
			want:   []int{1, 2},
		},
		{
			name:   "same value used from different indices",
			nums:   []int{3, 3},
			target: 6,
			want:   []int{0, 1},
		},
		{
			name:   "negative number pair",
			nums:   []int{-3, 4, 3, 90},
			target: 0,
			want:   []int{0, 2},
		},
		{
			name:   "zero pair",
			nums:   []int{0, 4, 3, 0},
			target: 0,
			want:   []int{0, 3},
		},
		{
			name:   "negative target",
			nums:   []int{-1, -2, -3, -4, -5},
			target: -8,
			want:   []int{2, 4},
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got := twoSum(tt.nums, tt.target)
			if !sameIndexPair(got, tt.want) {
				t.Errorf("twoSum() = %v, want %v", got, tt.want)
			}
		})
	}
}

func sameIndexPair(got, want []int) bool {
	if len(got) != len(want) {
		return false
	}
	if len(got) != 2 {
		return len(got) == 0
	}

	return got[0] == want[0] && got[1] == want[1] ||
		got[0] == want[1] && got[1] == want[0]
}
