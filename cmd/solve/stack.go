package solve

type Stack[T any] struct {
	items []T
}

func NewStack[T any]() *Stack[T] {
	return &Stack[T]{
		items: []T{},
	}
}

func (s *Stack[T]) IsEmpty() bool {
	return len(s.items) == 0
}

func (s *Stack[T]) Push(value T) {
	s.items = append(s.items, value)
}

func (s *Stack[T]) Pop() (T, bool) {
	var zero T

	if s.IsEmpty() {
		return zero, false
	}

	index := len(s.items) - 1
	value := s.items[index]
	s.items = s.items[:index]

	return value, true
}

func (s *Stack[T]) Peek() (T, bool) {
	var zero T

	if s.IsEmpty() {
		return zero, false
	}

	return s.items[len(s.items)-1], true
}

func (s *Stack[T]) Size() int {
	return len(s.items)
}
