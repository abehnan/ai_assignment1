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
        ArrayList<Result> results = new ArrayList<>(110);

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

                    // debug
//                    if (x == 10.0) {
//                        System.out.println("currentValue: " + y);
//                        System.out.println("leftX: " + leftX);
//                        System.out.println("rightX: " + rightX);
//                        System.out.println("leftY: " + leftY);
//                        System.out.println("rightY: " + rightY);
//
//                    }
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

        // debug
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
        double delta = 0.05;

        ArrayList<Double> temperatures = new ArrayList<>(4);
        temperatures.add(100.0);
        temperatures.add(1000.0);
        temperatures.add(10000.0);
        temperatures.add(100000.0);
        ArrayList<Double> coolingFactors = new ArrayList<>();
        coolingFactors.add(0.50);
        coolingFactors.add(0.60);
        coolingFactors.add(0.70);
        coolingFactors.add(0.80);
        coolingFactors.add(0.90);
        coolingFactors.add(0.99);

        for (Double temperature : temperatures) {
            for (Double coolingFactor : coolingFactors) {
                double temp = temperature;
                double alpha = coolingFactor;
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

                        if ((rightY < y) && invalidMove(y, rightY, temp))
                            x = rightX;
                        else if ((leftY < y) && invalidMove(y, leftY, temp))
                            x = leftX;
                        else if (y >= leftY && y >= rightY) {
                            results.add(new Result(startingPoint, delta, steps, y, x, alpha, temperature));
                            break;
                        }
                        else if (leftY > rightY && leftY > y)
                            x = leftX;
                        else if (rightY >= leftY && rightY > y)
                            x = rightX;

                        temp = alpha * temp;
                        steps++;

                    }
                }
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
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        System.out.println("startingPoint");
        if (input == 2)
            System.out.println("temperature");
        if (input == 2)
            System.out.println("coolingFactor");
        System.out.println("delta");
        System.out.println("steps");
        System.out.println("x");
        System.out.println("y\n");

        for (Result result : results) {



            System.out.println(result.getStartingPoint());
            if (result.getTemperature() >= 0)
                System.out.println(result.getTemperature());
            if (result.getCoolingFactor() >= 0)
                System.out.println(result.getCoolingFactor());
            System.out.println(result.getDelta());
            System.out.println(result.getSteps());
            System.out.println(result.getY());
            System.out.println(result.getX() + "\n");
        }
    }
}
