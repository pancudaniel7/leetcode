import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

public class GraphTraversal {

    static class EmptyGraphException extends Throwable {
    }

    enum TraversalType {
        DFS,
        BFS
    }

    private void traversal(TraversalType type, int root, Map<Integer, List<Integer>> graph, Consumer<Integer> callback)
          throws EmptyGraphException {
        if (graph == null || graph.isEmpty()) {
            throw new EmptyGraphException();
        }

        Set<Integer> visited = HashSet.newHashSet(graph.size());
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            int current;
            if (Objects.requireNonNull(type) == TraversalType.BFS) {
                current = stack.removeLast();
            } else {
                current = stack.pop();
            }

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            callback.accept(current);
            List<Integer> currentNeighbors = graph.getOrDefault(current, List.of());
            for (Integer currentNeighbor : currentNeighbors) {
                stack.push(currentNeighbor);
            }
        }
    }

    public static void main(String[] args) throws EmptyGraphException {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, List.of(4, 6));
        graph.put(6, List.of(7, 11));
        graph.put(4, List.of(10, 5));
        graph.put(5, List.of(99));

        Consumer<Integer> printCallBack = c -> System.out.printf("Nodes: %d\n", c);

        System.out.println("DFS:");
        new GraphTraversal().traversal(TraversalType.DFS, 1, graph, printCallBack);

        System.out.println("BFS:");
        new GraphTraversal().traversal(TraversalType.BFS, 1, graph, printCallBack);
    }
}

//1
//4 6
//10 5 7 11
//   99