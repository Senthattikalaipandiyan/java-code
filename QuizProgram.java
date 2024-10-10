import java.util.Scanner;

public class QuizProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] questions = {
            {"What is the capital of France?", "A) Paris", "B) London", "C) Rome", "D) Berlin"},
            {"Which planet is known as the Red Planet?", "A) Earth", "B) Mars", "C) Jupiter", "D) Venus"},
            {"What is the largest mammal in the world?", "A) Elephant", "B) Blue Whale", "C) Great White Shark", "D) Giraffe"},
            {"What is the square root of 64?", "A) 6", "B) 7", "C) 8", "D) 9"},
            {"Who wrote 'Romeo and Juliet'?", "A) Mark Twain", "B) Charles Dickens", "C) William Shakespeare", "D) Jane Austen"}
        };

        char[] answers = {'A', 'B', 'B', 'C', 'C'};
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i][0]);
            for (int j = 1; j < questions[i].length; j++) {
                System.out.println(questions[i][j]);
            }
            System.out.print("Your answer (A/B/C/D): ");
            char userAnswer = scanner.next().toUpperCase().charAt(0);
            
            if (userAnswer == answers[i]) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! The correct answer was " + answers[i] + ".\n");
            }
        }

        System.out.println("Your final score is: " + score + "/" + questions.length);
        scanner.close();
    }
}
