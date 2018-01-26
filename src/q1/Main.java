package q1;


import java.util.*;

class Main {

    private static void bfs(Node start, Node goal){
        Queue<Node> queue = new LinkedList<>();
        Node current_node;
        ArrayList<int[][]> successors;
        queue.add(start);

        while(!queue.isEmpty()) {
            current_node = queue.poll();
            current_node.print();

            if(Arrays.deepEquals(current_node.getGrid(),goal.getGrid()))
                 break;

            // add successors to queue
            successors = current_node.generatePossibleMoves();
            for (int[][] successor : successors) {
                Node n = new Node(successor, current_node.getDepth() + 1, current_node);
                if (current_node.getParent() == null)
                    queue.add(n);
                else if(!Node.hasBeenVisited(n.getGrid(), n))
                    queue.add(n);
            }
        }
    }

    private static void dfs(Node s, Node goal) {

        Stack<Node> stack = new Stack<>();
        Node current_node;
        ArrayList<int[][]> successors;
        stack.push(s);
        int i;
        int steps = 0;
        while(!stack.isEmpty()) {
            current_node = stack.pop();
            current_node.print();

            if(Arrays.deepEquals(current_node.getGrid(), goal.getGrid())) break;

            successors = current_node.generatePossibleMoves();
            // go backwards as we're using a stack
            for(i = successors.size() - 1; i >= 0; i--) {
                Node n = new Node(successors.get(i), current_node.getDepth() + 1, current_node);
                if(current_node.getParent() == null)
                    stack.push(n);
                else if(!Node.hasBeenVisited(n.getGrid(), n))
                    stack.push(n);
            }
            System.out.println("step " + ++steps);
        }
    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[][] start_config = {{1,4,2},{5,3,0}};
        int[][] goal_config = {{0,1,2},{5,4,3}};
        Node start_node = new Node(start_config, 0, null);
        Node goal_node = new Node(goal_config, 0, null);
        int input;

        System.out.println("1. bfs");
        System.out.println("2. dfs");
        System.out.println("Please enter an option: ");
        input = scanner.nextInt();
        if (input == 1)
            bfs(start_node,goal_node);
        else if (input == 2)
            dfs(start_node,goal_node);
        else
            System.out.println("Invalid choice");

    }
}
