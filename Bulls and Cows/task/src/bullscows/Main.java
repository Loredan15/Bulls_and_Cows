package bullscows;

import java.util.*;

public class Main {
    static int length = 0;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        startGame();
        
    }

    private static String generateRandom(int length) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String number = "";
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        while (list.get(0) == 0) {
            Collections.shuffle(list);
        }

        if (length > 10) {
            System.out.println("Error: can't generate a secret number with a length of " + length
                    + " because there aren't enough unique digits");
        } else {
            for (int i = 0; i < length; i++) {
                number += list.get(i);
            }
        }
        return number;
    }

    private static void startGame(){
        int turn = 1;
        int bull = 0;
        int cow = 0;
        System.out.println("Please, enter the secret code's length:");
        length = scanner.nextInt();
        String randomNumber = generateRandom(length);
        boolean isAnswer = false;
        System.out.println("Okay, let's start a game!");
        while (!isAnswer){
            System.out.printf("Turn %d:\n", turn);
            String guess = scanner.next();
            String[] guessList = guess.split("");
            for (int i = 0; i < guess.length(); i++) {
                if(guess.charAt(i) == randomNumber.charAt(i)) {
                    bull++;
                } else if (randomNumber.contains(guessList[i])) {
                    cow++;
                }
            }
            if (bull == length){
                System.out.printf("Grade: %d bulls\n", bull);
                System.out.println("Congratulations! You guessed the secret code.");
                isAnswer = true;
            }else if(cow > 0 || bull > 0) {
                System.out.print("Grade: " + bull + " bulls(s) and " + cow + " cow(s).\n");
            } else {
                System.out.print("Grade: None.\n");
            }
            turn++;
            bull = 0;
            cow = 0;
        }

    }
}

