package q1;

import java.util.*;

public class Main {

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
            successors = current_node.getSuccessors();
            for (int[][] successor : successors) {
                Node n = new Node(successor, current_node.getDepth() + 1, current_node);
                if (current_node.getParent() == null)
                    queue.add(n);
                else if(!Node.hasBeenVisited(n.getGrid(), n))
                    queue.add(n);
            }
        }
    }


    public static void main(String[] args) {

        int[][] start_config = {{1,4,2},{5,3,0}};
        int[][] goal_config = {{0,1,2},{5,4,3}};
        Node start_node = new Node(start_config, 0, null);
        Node goal_node = new Node(goal_config, 0, null);

        //System.out.println(Arrays.deepToString(s.state));
        bfs(start_node,goal_node);
//		dfs(s,goal_state);
//        ids(s, goal_state);
//
//		ArrayList<int[][]> temp = new ArrayList<int[][]>();
//		temp = s.getSuccessor();
//		for(int i=0; i <temp.size(); i++) {
//			System.out.println(temp.get(i).getClass().getSimpleName());
//		}

    }
}
