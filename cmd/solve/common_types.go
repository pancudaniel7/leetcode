package solve

// TreeNode is the binary tree node definition used by LeetCode tree problems.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Node is the graph node definition used by LeetCode Clone Graph.
type Node struct {
	Val       int
	Neighbors []*Node
}
