package solve

type Queue[T any] struct {
	items []T
}

func NewQueue[T any]() *Queue[T] {
	return &Queue[T]{
		items: []T{},
	}
}

func (q *Queue[T]) IsEmpty() bool {
	return len(q.items) == 0
}

func (q *Queue[T]) Size() int {
	return len(q.items)
}

func (q *Queue[T]) Enqueue(value T) {
	q.items = append(q.items, value)
}

func (q *Queue[T]) Dequeue() (T, bool) {
	var zero T

	if q.IsEmpty() {
		return zero, false
	}

	value := q.items[0]
	q.items = q.items[1:]

	return value, true
}

func (q *Queue[T]) Front() (T, bool) {
	var zero T

	if q.IsEmpty() {
		return zero, false
	}

	return q.items[0], true
}
