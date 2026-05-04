package solve

import (
	"reflect"
	"testing"
)

type queueOperation string

const (
	queueEnqueue queueOperation = "enqueue"
	queueDequeue queueOperation = "dequeue"
	queueFront   queueOperation = "front"
)

type queueStep[T any] struct {
	name      string
	operation queueOperation
	value     T
	wantValue T
	wantOK    bool
	wantSize  int
	wantEmpty bool
}

func TestNewQueueStartsEmpty(t *testing.T) {
	tests := []struct {
		name  string
		queue *Queue[int]
	}{
		{
			name:  "int queue",
			queue: NewQueue[int](),
		},
	}

	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			if tt.queue == nil {
				t.Fatal("expected queue, got nil")
			}
			if got := tt.queue.Size(); got != 0 {
				t.Fatalf("expected size 0, got %d", got)
			}
			if got := tt.queue.IsEmpty(); !got {
				t.Fatalf("expected empty queue, got IsEmpty=%v", got)
			}
		})
	}
}

func TestQueueIntOperations(t *testing.T) {
	tests := []struct {
		name  string
		steps []queueStep[int]
	}{
		{
			name: "empty queue returns zero values",
			steps: []queueStep[int]{
				{
					name:      "front empty",
					operation: queueFront,
					wantValue: 0,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "dequeue empty",
					operation: queueDequeue,
					wantValue: 0,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
			},
		},
		{
			name: "enqueue front and dequeue use fifo order",
			steps: []queueStep[int]{
				{
					name:      "enqueue first",
					operation: queueEnqueue,
					value:     1,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "enqueue second",
					operation: queueEnqueue,
					value:     2,
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "enqueue third",
					operation: queueEnqueue,
					value:     3,
					wantSize:  3,
					wantEmpty: false,
				},
				{
					name:      "front keeps first item",
					operation: queueFront,
					wantValue: 1,
					wantOK:    true,
					wantSize:  3,
					wantEmpty: false,
				},
				{
					name:      "front can be repeated",
					operation: queueFront,
					wantValue: 1,
					wantOK:    true,
					wantSize:  3,
					wantEmpty: false,
				},
				{
					name:      "dequeue first",
					operation: queueDequeue,
					wantValue: 1,
					wantOK:    true,
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "dequeue second",
					operation: queueDequeue,
					wantValue: 2,
					wantOK:    true,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "front after dequeues",
					operation: queueFront,
					wantValue: 3,
					wantOK:    true,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "dequeue third",
					operation: queueDequeue,
					wantValue: 3,
					wantOK:    true,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "dequeue empty after draining",
					operation: queueDequeue,
					wantValue: 0,
					wantOK:    false,
					wantSize:  0,
					wantEmpty: true,
				},
			},
		},
		{
			name: "zero value can be stored",
			steps: []queueStep[int]{
				{
					name:      "enqueue zero",
					operation: queueEnqueue,
					value:     0,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "front zero",
					operation: queueFront,
					wantValue: 0,
					wantOK:    true,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "dequeue zero",
					operation: queueDequeue,
					wantValue: 0,
					wantOK:    true,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "front empty after zero",
					operation: queueFront,
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
			runQueueSteps(t, tt.steps)
		})
	}
}

func TestQueueGenericSliceOperations(t *testing.T) {
	tests := []struct {
		name  string
		steps []queueStep[[]int]
	}{
		{
			name: "non comparable values keep fifo order",
			steps: []queueStep[[]int]{
				{
					name:      "enqueue first slice",
					operation: queueEnqueue,
					value:     []int{1, 2},
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "enqueue second slice",
					operation: queueEnqueue,
					value:     []int{3, 4},
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "front first slice",
					operation: queueFront,
					wantValue: []int{1, 2},
					wantOK:    true,
					wantSize:  2,
					wantEmpty: false,
				},
				{
					name:      "dequeue first slice",
					operation: queueDequeue,
					wantValue: []int{1, 2},
					wantOK:    true,
					wantSize:  1,
					wantEmpty: false,
				},
				{
					name:      "dequeue second slice",
					operation: queueDequeue,
					wantValue: []int{3, 4},
					wantOK:    true,
					wantSize:  0,
					wantEmpty: true,
				},
				{
					name:      "dequeue empty slice queue",
					operation: queueDequeue,
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
			runQueueSteps(t, tt.steps)
		})
	}
}

func runQueueSteps[T any](t *testing.T, steps []queueStep[T]) {
	t.Helper()

	queue := NewQueue[T]()

	for _, step := range steps {
		t.Run(step.name, func(t *testing.T) {
			switch step.operation {
			case queueEnqueue:
				queue.Enqueue(step.value)
			case queueDequeue:
				got, ok := queue.Dequeue()
				assertQueueResult(t, got, ok, step.wantValue, step.wantOK)
			case queueFront:
				got, ok := queue.Front()
				assertQueueResult(t, got, ok, step.wantValue, step.wantOK)
			default:
				t.Fatalf("unsupported operation %q", step.operation)
			}

			if got := queue.Size(); got != step.wantSize {
				t.Fatalf("expected size %d, got %d", step.wantSize, got)
			}
			if got := queue.IsEmpty(); got != step.wantEmpty {
				t.Fatalf("expected IsEmpty=%v, got %v", step.wantEmpty, got)
			}
		})
	}
}

func assertQueueResult[T any](t *testing.T, gotValue T, gotOK bool, wantValue T, wantOK bool) {
	t.Helper()

	if gotOK != wantOK {
		t.Fatalf("expected ok %v, got %v", wantOK, gotOK)
	}
	if !reflect.DeepEqual(gotValue, wantValue) {
		t.Fatalf("expected value %#v, got %#v", wantValue, gotValue)
	}
}
