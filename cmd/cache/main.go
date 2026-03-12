// https://leetcode.com/problems/lru-cache/?envType=problem-list-v2&envId=ssd-ssd1-cache-system-design
package main

type Node struct {
	key   int
	value int
	prev  *Node
	next  *Node
}

type LRUCache struct {
	capacity int
	cache    map[int]*Node
	head     *Node
	tail     *Node
}

func Constructor(capacity int) LRUCache {
	head := &Node{}
	tail := &Node{}
	head.next = tail
	tail.prev = head

	return LRUCache{
		capacity: capacity,
		cache:    make(map[int]*Node),
		head:     head,
		tail:     tail,
	}
}

func (l *LRUCache) Get(key int) int {
	node, ok := l.cache[key]
	if !ok {
		return -1
	}
	l.remove(node)
	l.addToFront(node)

	return node.value
}

func (l *LRUCache) addToFront(node *Node) {
	node.next = l.head.next
	node.prev = l.head
	l.head.next.prev = node
	l.head.next = node
}

func (l *LRUCache) remove(node *Node) {
	node.prev.next = node.next
	node.next.prev = node.prev
}

func (l *LRUCache) Put(key int, value int) {
	if node, ok := l.cache[key]; ok {
		node.value = value
		l.remove(node)
		l.addToFront(node)
		return
	}

	if len(l.cache) == l.capacity {
		lru := l.tail.prev

		lru.prev.next = l.tail
		l.tail.prev = lru.prev

		delete(l.cache, lru.key)
	}

	node := &Node{key: key, value: value}

	node.next = l.head.next
	node.prev = l.head
	l.head.next.prev = node
	l.head.next = node

	l.cache[key] = node
}
