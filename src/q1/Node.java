package q1;

import java.util.ArrayList;
import java.util.Arrays;

class Node {

    private int[][] grid;
    private Node parent;
    private int depth;

    Node(int[][] grid, int depth, Node parent) {
        this.grid = grid;
        this.depth = depth;
        this.parent = parent;
    }

    void print() {
        System.out.println(Arrays.deepToString(getGrid()));
    }

    // returns true if the grid state has already been encountered
    static boolean hasBeenVisited(int[][] grid, Node n) {
        return n.getParent() != null && (Arrays.deepEquals(grid, n.getParent().getGrid()) || hasBeenVisited(grid, n.getParent()));
    }

    // redo this one
    ArrayList<int[][]> getSuccessors() {

        ArrayList<int[][]> successors = new ArrayList<>();
        int[][] successor1 = new int[2][3];
        int[][] successor2 = new int[2][3];
        int[][] successor3 = new int[2][3];
        int i, j = 0;

        find_zero_location:
        for(i = 0; i < 2; i++) {
            for(j = 0; j < 3; j++) {
                if(getGrid()[i][j] == 0) {
                    break find_zero_location;
                }
            }
        }

        // copy grid to three successors
        for (int k = 0; k < 2; k++) {
            successor1[k] = Arrays.copyOf(getGrid()[k], getGrid()[k].length);
            successor2[k] = Arrays.copyOf(getGrid()[k], getGrid()[k].length);
            successor3[k] = Arrays.copyOf(getGrid()[k], getGrid()[k].length);
        }

        // swapping steps

        // if the zero is in the top
        if(i==0) {
            // if the zero is in top left, we can only move right or down
            if(j==0) {
                int k1, k2;
                // move right
                k1 = getGrid()[i][j+1];
                successor1[i][j] = getGrid()[i][j+1];
                successor1[i][j+1] = 0;

                // move down
                k2 = getGrid()[i+1][j];
                successor2[i][j] = getGrid()[i+1][j];
                successor2[i+1][j] = 0;

                if(k1 < k2) {
                    successors.add(successor1);
                    successors.add(successor2);
                }
                else {
                    successors.add(successor2);
                    successors.add(successor1);
                }
            }
            else if(j==1) {
                int k1, k2, k3;
                // move right
                k1 = getGrid()[i][j+1];
                successor1[i][j] = getGrid()[i][j+1];
                successor1[i][j+1] = 0;

                // move left
                k2 = getGrid()[i][j-1];
                successor2[i][j] = getGrid()[i][j-1];
                successor2[i][j-1] = 0;

                // move down
                k3 = getGrid()[i+1][j];
                successor3[i][j] = getGrid()[i+1][j];
                successor3[i+1][j] = 0;

                if(k1<k2 && k1<k3) {
                    successors.add(successor1);
                    if(k2<k3) {
                        successors.add(successor2);
                        successors.add(successor3);
                    }
                    else {
                        successors.add(successor3);
                        successors.add(successor2);
                    }
                }
                else if(k2<k3 && k2<k1){
                    successors.add(successor2);
                    if(k1<k3) {
                        successors.add(successor1);
                        successors.add(successor3);
                    }
                    else {
                        successors.add(successor3);
                        successors.add(successor1);
                    }
                }
                else { //k3 is min
                    successors.add(successor3);
                    if(k1<k2) {
                        successors.add(successor1);
                        successors.add(successor2);
                    }
                    else {
                        successors.add(successor2);
                        successors.add(successor1);
                    }
                }

            }
            else { // j==2
                int k1, k2;
                // move left
                k1 = getGrid()[i][j-1];
                successor1[i][j] = getGrid()[i][j-1];
                successor1[i][j-1] = 0;

                // move down
                k2 = getGrid()[i+1][j];
                successor2[i][j] = getGrid()[i+1][j];
                successor2[i+1][j] = 0;

                if(k1 < k2) {
                    successors.add(successor1);
                    successors.add(successor2);
                }
                else {
                    successors.add(successor2);
                    successors.add(successor1);
                }
            }
        }
        else { // i ==1
            if(j==0) {
                int k1,k2;
                // move right
                k1 = getGrid()[i][j+1];
                successor1[i][j] = getGrid()[i][j+1];
                successor1[i][j+1] = 0;

                //move up
                k2 = getGrid()[i-1][j];
                successor2[i][j] = getGrid()[i-1][j];
                successor2[i-1][j] = 0;

                if(k1 < k2) {
                    successors.add(successor1);
                    successors.add(successor2);
                }
                else {
                    successors.add(successor2);
                    successors.add(successor1);
                }
            }
            else if(j==1) {
                int k1,k2,k3;
                // move right
                k1 =  getGrid()[i][j+1];
                successor1[i][j] = getGrid()[i][j+1];
                successor1[i][j+1] = 0;
                // move left
                k2 = getGrid()[i][j-1];
                successor2[i][j] = getGrid()[i][j-1];
                successor2[i][j-1] = 0;
                //move up
                k3 = getGrid()[i-1][j];
                successor3[i][j] = getGrid()[i-1][j];
                successor3[i-1][j] = 0;

                if(k1<k2 && k1<k3) {
                    successors.add(successor1);
                    if(k2<k3) {
                        successors.add(successor2);
                        successors.add(successor3);
                    }
                    else {
                        successors.add(successor3);
                        successors.add(successor2);
                    }
                }
                else if(k2<k3 && k2<k1){
                    successors.add(successor2);
                    if(k1<k3) {
                        successors.add(successor1);
                        successors.add(successor3);
                    }
                    else {
                        successors.add(successor3);
                        successors.add(successor1);
                    }
                }
                else { //k3 is min
                    successors.add(successor3);
                    if(k1<k2) {
                        successors.add(successor1);
                        successors.add(successor2);
                    }
                    else {
                        successors.add(successor2);
                        successors.add(successor1);
                    }
                }
            }
            else { //j==2
                int k1,k2;
                // move left
                k1 = getGrid()[i][j-1];
                successor1[i][j] = getGrid()[i][j-1];
                successor1[i][j-1] = 0;

                //move up
                k2 = getGrid()[i-1][j];
                successor2[i][j] = getGrid()[i-1][j];
                successor2[i-1][j] = 0;
                //System.out.println(Arrays.deepToString(newState));

                if(k1 < k2) {
                    successors.add(successor1);
                    successors.add(successor2);
                }
                else {
                    successors.add(successor2);
                    successors.add(successor1);
                }
            }
        }
        return successors;
    }

    int[][] getGrid() {
        return grid;
    }

    q1.Node getParent() {
        return parent;
    }

    int getDepth() {
        return depth;
    }
}
