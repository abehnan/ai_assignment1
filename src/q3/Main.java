package q3;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static double Y(double x) {
        return Math.sin(x * x / 2.0) / (Math.log(x + 4) / Math.log(2.0));
    }

    private static ArrayList<Result> hillClimbing() {

        double startingPoint, delta, x, leftX, rightX, y, leftY, rightY;
        int steps;
        ArrayList<Result> results = new ArrayList<>(100);

        for (startingPoint = 0; startingPoint <= 10; startingPoint++) {
            for (delta = 0.01; delta <= 0.1; delta = delta + 0.01) {
                x = startingPoint;
                steps = 0;
                while (true) {
                    y = Y(x);
                    leftX = x - delta;
                    rightX = x + delta;
                    if (leftX < 0.0) leftX = 0.0;
                    if (rightX > 10.0) rightX = 10.0;
                    leftY = Y(leftX);
                    rightY = Y(rightX);

//                    if (x == 10.0) {
//                        System.out.println("currentValue: " + y);
//                        System.out.println("leftX: " + leftX);
//                        System.out.println("rightX: " + rightX);
//                        System.out.println("leftY: " + leftY);
//                        System.out.println("rightY: " + rightY);
//
//                    }
                    // debug
//                    System.out.println("currentValue: " + currentValue);
//                    System.out.println("leftNeighbour: " + leftNeighbour);
//                    System.out.println("rightNeighbour: " + rightNeighbour);
//                    System.out.println("step: " + ++steps + "\n");

                    if (y >= leftY && y >= rightY) {
                        results.add(new Result(startingPoint, delta, steps, y, x));
                        break;
                    }
                    else if (leftY > rightY && leftY > y)
                        x = leftX;
                    else if (rightY >= leftY && rightY > y)
                        x = rightX;


                    steps++;

                }
            }
        }
        return results;
    }

    private static boolean invalidMove(double a, double b, double temp) {
        double probability = Math.pow(Math.E, -Math.abs(a-b)/temp);
        double rng = Math.random();

//        System.out.println("a = " + a);
//        System.out.println("b = " + b);
//        System.out.println("temp = " + temp);
//        System.out.println("probability = " + probability);
//        System.out.println("rng = " + rng);


        return probability > rng;
    }


    private static ArrayList<Result> simulatedAnnealing() {
        double startingPoint, x, leftX, rightX, y, leftY, rightY;
        int steps;
        ArrayList<Result> results = new ArrayList<>(100);
        double temperature = 1000;
        double alpha = 0.90;
        double delta = 0.05;

        for (startingPoint = 0.0; startingPoint <= 10.0; startingPoint++) {
            x = startingPoint;
            steps = 0;
            while (true) {


                leftX = x - delta;
                rightX = x + delta;
                if (leftX < 0.0) leftX = 0.0;
                if (rightX > 10.0) rightX = 10.0;
                y = Y(x);
                leftY = Y(leftX);
                rightY = Y(rightX);



                // debug
//                System.out.println("x: " + x);
//                System.out.println("leftX: " + leftX);
//                System.out.println("rightX: " + rightX);
//                System.out.println("y: " + y);
//                System.out.println("leftY: " + leftY);
//                System.out.println("rightY: " + rightY);

                if ((rightY < y) && invalidMove(y, rightY, temperature)) {
                    x = rightX;
//                    System.out.println("invalidMove(y, rightY, temperature)\n");
                }

                else if ((leftY < y) && invalidMove(y, leftY, temperature)) {
                    x = leftX;
//                    System.out.println("invalidMove(y, leftY, temperature)\n");
                }


                else if (y >= leftY && y >= rightY) {
                    results.add(new Result(startingPoint, delta, steps, y, x));
//                    System.out.println("breaking\n");
                    break;
                }
                else if (leftY > rightY && leftY > y) {
//                    System.out.println("x = leftX");
                    x = leftX;
                }

                else if (rightY >= leftY && rightY > y) {
                    System.out.println("x = rightX");
                    x = rightX;
                }


                temperature = alpha * temperature;
                steps++;

            }
        }
        return results;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. hill climbing");
        System.out.println("2. simulated annealing");
        System.out.println("Please enter an option: ");
        int input = scanner.nextInt();
        ArrayList<Result> results = new ArrayList<>(100);
        switch(input){
            case 1:
                results = hillClimbing();
                break;
            case 2:
                results = simulatedAnnealing();
                System.out.println("temp = 10000");
                System.out.println("alpha = 0.99\n");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        for (Result result : results) {
            System.out.println("starting point: " + result.getStartingPoint());
            System.out.println("delta: " + result.getDelta());
            System.out.println("steps: " + result.getSteps());
            System.out.println("y: " + result.getY());
            System.out.println("x: " + result.getX() + "\n");
        }
    }
}
