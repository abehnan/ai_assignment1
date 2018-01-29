package q3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static double Y(double x) {
        return Math.sin(x * x / 2.0) / (Math.log(x + 4) / Math.log(2.0));
    }

    private static ArrayList<Result> hillClimbing() {

        double startingPoint, delta, x, leftX, rightX, currentValue, leftNeighbour, rightNeighbour;
        int steps;
        ArrayList<Result> results = new ArrayList<>(100);

        for (startingPoint = 0; startingPoint <= 10; startingPoint++) {
            for (delta = 0.01; delta <= 0.1; delta = delta + 0.01) {
                x = startingPoint;
                steps = 0;
                while (true) {
                    currentValue = Y(x);
                    leftX = x - delta;
                    rightX = x + delta;
                    if (leftX < 0.0) leftX = 0.0;
                    if (rightX > 10.0) rightX = 10.0;
                    leftNeighbour = Y(leftX);
                    rightNeighbour = Y(rightX);

                    // debug
//                    System.out.println("currentValue: " + currentValue);
//                    System.out.println("leftNeighbour: " + leftNeighbour);
//                    System.out.println("rightNeighbour: " + rightNeighbour);
//                    System.out.println("step: " + ++steps + "\n");

                    if (currentValue >= leftNeighbour && currentValue >= rightNeighbour) {
                        results.add(new Result(startingPoint, delta, steps, currentValue, x));
                        break;
                    }
                    else if (leftNeighbour > rightNeighbour && leftNeighbour > currentValue)
                        x = leftX;
                    else if (rightNeighbour >= leftNeighbour && rightNeighbour > currentValue)
                        x = rightX;


                    steps++;

                }
            }
        }
        return results;
    }

    private static boolean invalidMove(double a, double b, double temp) {
        double probability = Math.pow(Math.E, -(Math.abs(a-b)/temp));
        Random rng = new Random();
        double x = rng.nextInt();
        return x <= probability;
    }


    private static ArrayList<Result> simulatedAnnealing() {
        double startingPoint, delta = 0.05, x, leftX, rightX, currentValue, leftNeighbour, rightNeighbour;
        int steps;
        ArrayList<Result> results = new ArrayList<>(100);
        double temperature = 10000;
        double alpha = 0.95;

        for (startingPoint = 0; startingPoint <= 10; startingPoint++) {
            x = startingPoint;
            steps = 0;
            while (true) {
                currentValue = Y(x);
                leftX = x - delta;
                rightX = x + delta;
                if (leftX < 0.0) leftX = 0.0;
                if (rightX > 10.0) rightX = 10.0;
                leftNeighbour = Y(leftX);
                rightNeighbour = Y(rightX);

                // debug
//                    System.out.println("currentValue: " + currentValue);
//                    System.out.println("leftNeighbour: " + leftNeighbour);
//                    System.out.println("rightNeighbour: " + rightNeighbour);
//                    System.out.println("step: " + ++steps + "\n");

                if (currentValue >= leftNeighbour && currentValue >= rightNeighbour) {
                    results.add(new Result(startingPoint, delta, steps, currentValue, x));
                    break;
                }
                else if (leftNeighbour > rightNeighbour && leftNeighbour > currentValue) {
                    x = (invalidMove(currentValue, rightNeighbour, temperature)) ? leftX : rightX;
                }
                else if (rightNeighbour >= leftNeighbour && rightNeighbour > currentValue)
                    x = (invalidMove(currentValue, leftNeighbour, temperature)) ? rightX : leftX;

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
                System.out.println("alpha = 0.95\n");
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
