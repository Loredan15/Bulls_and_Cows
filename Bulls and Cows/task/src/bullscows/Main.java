package bullscows;

import java.util.*;

public class Main {
    static int length = 0;
    static int letterCount = 0;
    static char firstLetter;
    static char lastLetter;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startGame();

    }

    private static String generateRandom(int length) {
        ArrayList<Character> listNumbers = new ArrayList<Character>();
        Character[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Character[] currentChar = new Character[letterCount];
        String number = "";
        firstLetter = chars[0];
        for (int i = 48; i < 58; i++) {
            listNumbers.add((char) i);
        }
        while (listNumbers.get(0) == 0) {
            Collections.shuffle(listNumbers);
        }

        if (length > 36) {
            System.out.println("Error: can't generate a secret number with a length of " + length
                    + " because there aren't enough unique digits");
        } else if (letterCount > 10) {
            for (int i = 0; i < letterCount-10; i++) {
                currentChar[i] = chars[i];
                lastLetter = chars[i];
                listNumbers.add(currentChar[i]);
            }
        }
        Collections.shuffle(listNumbers);

        for (int i = 0; i < length; i++) {
            number += listNumbers.get(i);
        }

        return number;

//        int place = 1;
//        Random random = new Random();
//        StringBuilder secretCode = new StringBuilder();
//        while (place <= length) {
//            if (place == 1) {
//                int num = random.nextInt(9) + 1;
//                secretCode.append(num);
//                place++;
//            } else {
//                while (true) {
//                    int num = random.nextInt(9);
//                    if (!secretCode.toString().contains("" + num)) {
//                        secretCode.append(num);
//                        place++;
//                        break;
//                    }
//
//                }
//            }
//        }
    }

    private static void startGame() {
        int turn = 1;
        int bull = 0;
        int cow = 0;
        System.out.println("Please, enter the secret code's length:");
        length = scanner.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        letterCount = scanner.nextInt();
        String randomNumber = generateRandom(length);
        boolean isAnswer = false;
        String stars="";
        for (int i = 0; i < length; i++) {
            stars += "*";
        }
        System.out.printf("The secret is prepared: %s (0-9, %s-%s).\n", stars, firstLetter, lastLetter);
        System.out.println("Okay, let's start a game!");
        while (!isAnswer) {
            System.out.printf("Turn %d:\n", turn);
            String guess = scanner.next();
            String[] guessList = guess.split("");
            for (int i = 0; i < guess.length(); i++) {
                if (guess.charAt(i) == randomNumber.charAt(i)) {
                    bull++;
                } else if (randomNumber.contains(guessList[i])) {
                    cow++;
                }
            }
            if (bull == length) {
                System.out.printf("Grade: %d bulls\n", bull);
                System.out.println("Congratulations! You guessed the secret code.");
                isAnswer = true;
            } else if (cow > 0 || bull > 0) {
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

