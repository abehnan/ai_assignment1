package q1;

import java.util.ArrayList;
import java.util.Arrays;

class Node {

    private final int[][] grid;
    private final Node parent;
    private final int depth;

    Node(int[][] grid, int depth, Node parent) {
        this.grid = grid;
        this.depth = depth;
        this.parent = parent;
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

    void print() {
        System.out.println(Arrays.deepToString(getGrid()));
    }

    // returns true if the grid state has already been encountered
    static boolean hasBeenVisited(int[][] grid, Node n) {
        return n.getParent() != null && (Arrays.deepEquals(grid, n.getParent().getGrid()) || hasBeenVisited(grid, n.getParent()));
    }

    // generates all possible grid configurations by applying all valid operations
    // order of the returned grids is the order of preference
    ArrayList<int[][]> generatePossibleMoves() {

        ArrayList<int[][]> successors = new ArrayList<>();
        int[][] successor1 = new int[2][3];
        int[][] successor2 = new int[2][3];
        int[][] successor3 = new int[2][3];
        int i, j = 0;
        int temp1, temp2, temp3;

        find_zero_location:
        for(i = 0; i < getGrid().length; i++) {
            for(j = 0; j < getGrid()[i].length; j++) {
                if(getGrid()[i][j] == 0) {
                    break find_zero_location;
                }
            }
        }

        // copy grid to three successors
        for (int k = 0; k < 2; k++) {
            System.arraycopy(getGrid()[k], 0, successor1[k], 0, getGrid()[k].length);
            System.arraycopy(getGrid()[k], 0, successor2[k], 0, getGrid()[k].length);
            System.arraycopy(getGrid()[k], 0, successor3[k], 0, getGrid()[k].length);
        }

        if (i == 0) {
            // if the zero is in [0,0], we can either swap either right or down
            switch(j){
                case 0:
                    // swap right
                    temp1 = getGrid()[i][j+1];
                    successor1[i][j] = temp1;
                    successor1[i][j+1] = 0;

                    // swap down
                    temp2 = getGrid()[i+1][j];
                    successor2[i][j] = temp2;
                    successor2[i+1][j] = 0;

                    // add the successors in the right order
                    if(temp1 < temp2) {
                        successors.add(successor1);
                        successors.add(successor2);
                    }
                    else {
                        successors.add(successor2);
                        successors.add(successor1);
                    }
                    break;
            // if the zero in [0, 1] we can swap either left, right, or down
            case 1:
                // swap right
                temp1 = getGrid()[i][j+1];
                successor1[i][j] = temp1;
                successor1[i][j+1] = 0;

                // swap left
                temp2 = getGrid()[i][j-1];
                successor2[i][j] = temp2;
                successor2[i][j-1] = 0;

                // swap down
                temp3 = getGrid()[i+1][j];
                successor3[i][j] = temp3;
                successor3[i+1][j] = 0;

                // add the successors in the right order
                if (temp1 < temp2 && temp1 < temp3 && temp2 < temp3) {
                    successors.add(successor1);
                    successors.add(successor2);
                    successors.add(successor3);
                }
                else if (temp1 < temp2 && temp1 < temp3 && temp3 < temp2) {
                    successors.add(successor1);
                    successors.add(successor3);
                    successors.add(successor2);
                }
                else if (temp2 < temp1 && temp2 < temp3 && temp1 < temp3) {
                    successors.add(successor2);
                    successors.add(successor1);
                    successors.add(successor3);
                }
                else if (temp2 < temp1 && temp2 < temp3 && temp3 < temp1) {
                    successors.add(successor2);
                    successors.add(successor3);
                    successors.add(successor1);
                }
                else if (temp3 < temp1 && temp3 < temp2 && temp1 < temp2) {
                    successors.add(successor3);
                    successors.add(successor1);
                    successors.add(successor2);
                }
                else if (temp3 < temp1 && temp3 < temp2 && temp2 < temp1) {
                    successors.add(successor3);
                    successors.add(successor2);
                    successors.add(successor1);
                }
                break;
            // if the zero in [0, 2] we can swap either left or down
            default:
                // swap left
                temp1 = getGrid()[i][j-1];
                successor1[i][j] = temp1;
                successor1[i][j-1] = 0;

                // swap down
                temp2= getGrid()[i+1][j];
                successor2[i][j] = temp2;
                successor2[i+1][j] = 0;

                // add the successors in the right order
                if(temp1 < temp2) {
                    successors.add(successor1);
                    successors.add(successor2);
                }
                else {
                    successors.add(successor2);
                    successors.add(successor1);
                }
                break;
            }
        }
        else {
            // if the zero in [1, 0] we can swap either right or up
            switch(j){
                case 0:
                    // swap right
                    temp1 = getGrid()[i][j+1];
                    successor1[i][j] = temp1;
                    successor1[i][j+1] = 0;

                    // swap up
                    temp2 = getGrid()[i-1][j];
                    successor2[i][j] = temp2;
                    successor2[i-1][j] = 0;

                    // add the successors in the right order
                    if(temp1 < temp2) {
                        successors.add(successor1);
                        successors.add(successor2);
                    }
                    else {
                        successors.add(successor2);
                        successors.add(successor1);
                    }
                    break;
                // if the zero in [1, 1] we can swap either left, right or up
                case 1:
                    // swap left
                    temp1 = getGrid()[i][j-1];
                    successor1[i][j] = temp1;
                    successor1[i][j-1] = 0;

                    // swap right
                    temp2 = getGrid()[i][j+1];
                    successor2[i][j] = temp2;
                    successor2[i][j+1] = 0;

                    // swap up
                    temp3 = getGrid()[i-1][j];
                    successor3[i][j] = temp3;
                    successor3[i-1][j] = 0;

                    // add the successors in the right order
                    if (temp1 < temp2 && temp1 < temp3 && temp2 < temp3) {
                        successors.add(successor1);
                        successors.add(successor2);
                        successors.add(successor3);
                    }
                    else if (temp1 < temp2 && temp1 < temp3 && temp3 < temp2) {
                        successors.add(successor1);
                        successors.add(successor3);
                        successors.add(successor2);
                    }
                    else if (temp2 < temp1 && temp2 < temp3 && temp1 < temp3) {
                        successors.add(successor2);
                        successors.add(successor1);
                        successors.add(successor3);
                    }
                    else if (temp2 < temp1 && temp2 < temp3 && temp3 < temp1) {
                        successors.add(successor2);
                        successors.add(successor3);
                        successors.add(successor1);
                    }
                    else if (temp3 < temp1 && temp3 < temp2 && temp1 < temp2) {
                        successors.add(successor3);
                        successors.add(successor1);
                        successors.add(successor2);
                    }
                    else if (temp3 < temp1 && temp3 < temp2 && temp2 < temp1) {
                        successors.add(successor3);
                        successors.add(successor2);
                        successors.add(successor1);
                    }
                    break;
                // if the zero in [1, 2] we can swap either left or up
                default:
                    // swap left
                    temp1 = getGrid()[i][j-1];
                    successor1[i][j] = temp1;
                    successor1[i][j-1] = 0;

                    // swap up
                    temp2 = getGrid()[i-1][j];
                    successor2[i][j] = temp2;
                    successor2[i-1][j] = 0;

                    // add the successors in the right order
                    if(temp1 < temp2) {
                        successors.add(successor1);
                        successors.add(successor2);
                    }
                    else {
                        successors.add(successor2);
                        successors.add(successor1);
                    }
                    break;
            }
        }
        return successors;
    }
}