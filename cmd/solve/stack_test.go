package solve

import (
	"reflect"
	"testing"
)

type stackOperation string

const (
	stackPush stackOperation = "push"
	stackPop  stackOperation = "pop"
	stackPeek stackOperation = "peek"
)

type stackStep[T any] struct {
	name      string
	operation stackOperation
	value     T
	wantValue T
	wantOK    bool
	wantSize  int
	wantEmpty bool
}

func TestNewStackStartsEmpty(t *testing.T) {
	tests := []struct {
		name  string
		stack *Stack[int]
	}{
		{
			name:  "int stack",
			stack: NewStack[int](),
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if tt.stack == nil {
				t.Fatal("expected stack, got nil")
			}
			if got := tt.stack.Size(); got != 0 {
				t.Fatalf("expected size 0, got %d", got)
			}
			if got := tt.stack.IsEmpty(); !got {
				t.Fatalf("expected empty stack, got IsEmpty=%v", got)
			}
		})
	}
}

func TestStackIntOperations(t *testing.T) {
	tests := []struct {
		name  string
		steps []stackStep[int]
	}{
		{
			name: "empty stack returns zero values",
			steps: []stackStep[int]{
				{
					name:      "peek empty",
					operation: stackPeek,
					wantValue: 0,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "pop empty",
					operation: stackPop,
					wantValue: 0,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
			},
		},
		{
			name: "push peek and pop use lifo order",
			steps: []stackStep[int]{
				{
					name:      "push first",
					operation: stackPush,
					value:     1,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "push second",
					operation: stackPush,
					value:     2,
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "push third",
					operation: stackPush,
					value:     3,
					wantSize:  3,
					wantEmpty: false,
				},
				{
					name:      "peek keeps top item",
					operation: stackPeek,
					wantValue: 3,
					wantOK:    true,
					wantSize:  3,
					wantEmpty: false,
				},
				{
					name:      "peek can be repeated",
					operation: stackPeek,
					wantValue: 3,
					wantOK:    true,
					wantSize:  3,
					wantEmpty: false,
				},
				{
					name:      "pop third",
					operation: stackPop,
					wantValue: 3,
					wantOK:    true,
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "pop second",
					operation: stackPop,
					wantValue: 2,
					wantOK:    true,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "pop first",
					operation: stackPop,
					wantValue: 1,
					wantOK:    true,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "pop empty after draining",
					operation: stackPop,
					wantValue: 0,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
			},
		},
		{
			name: "zero value can be stored",
			steps: []stackStep[int]{
				{
					name:      "push zero",
					operation: stackPush,
					value:     0,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "peek zero",
					operation: stackPeek,
					wantValue: 0,
					wantOK:    true,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "pop zero",
					operation: stackPop,
					wantValue: 0,
					wantOK:    true,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "peek empty after zero",
					operation: stackPeek,
					wantValue: 0,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			runStackSteps(t, tt.steps)
		})
	}
}

func TestStackGenericSliceOperations(t *testing.T) {
	tests := []struct {
		name  string
		steps []stackStep[[]int]
	}{
		{
			name: "non comparable values keep lifo order",
			steps: []stackStep[[]int]{
				{
					name:      "push first slice",
					operation: stackPush,
					value:     []int{1, 2},
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "push second slice",
					operation: stackPush,
					value:     []int{3, 4},
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "peek second slice",
					operation: stackPeek,
					wantValue: []int{3, 4},
					wantOK:    true,
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "pop second slice",
					operation: stackPop,
					wantValue: []int{3, 4},
					wantOK:    true,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "pop first slice",
					operation: stackPop,
					wantValue: []int{1, 2},
					wantOK:    true,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "pop empty slice stack",
					operation: stackPop,
					wantValue: nil,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
			},
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			runStackSteps(t, tt.steps)
		})
	}
}

func runStackSteps[T any](t *testing.T, steps []stackStep[T]) {
	t.Helper()

	stack := NewStack[T]()

	for _, step := range steps {
		t.Run(step.name, func(t *testing.T) {
			switch step.operation {
			case stackPush:
				stack.Push(step.value)
			case stackPop:
				got, ok := stack.Pop()
				assertStackResult(t, got, ok, step.wantValue, step.wantOK)
			case stackPeek:
				got, ok := stack.Peek()
				assertStackResult(t, got, ok, step.wantValue, step.wantOK)
			default:
				t.Fatalf("unsupported operation %q", step.operation)
			}

			if got := stack.Size(); got != step.wantSize {
				t.Fatalf("expected size %d, got %d", step.wantSize, got)
			}
			if got := stack.IsEmpty(); got != step.wantEmpty {
				t.Fatalf("expected IsEmpty=%v, got %v", step.wantEmpty, got)
			}
		})
	}
}

func assertStackResult[T any](t *testing.T, gotValue T, gotOK bool, wantValue T, wantOK bool) {
	t.Helper()

	if gotOK != wantOK {
		t.Fatalf("expected ok %v, got %v", wantOK, gotOK)
	}
	if !reflect.DeepEqual(gotValue, wantValue) {
		t.Fatalf("expected value %#v, got %#v", wantValue, gotValue)
	}
}
