package solve

/*
146. LRU Cache

LeetCode: https://leetcode.com/problems/lru-cache/

Design a data structure that follows the constraints of a Least Recently Used
cache.

Implement the LRUCache class:

Constructor initializes the cache with a positive capacity.
Get returns the value of the key if it exists, otherwise returns -1.
Put updates the value of the key if it exists, or inserts the key-value pair.
When the cache exceeds capacity, invalidate the least recently used item.

Both get and put should run in O(1) average time complexity.
*/
type LRUCache struct {
}

func Constructor(capacity int) LRUCache {
	return LRUCache{}
}

func (this *LRUCache) Get(key int) int {
	return -1
}

func (this *LRUCache) Put(key int, value int) {
}
