		
package JavaProject;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame {
	public static int MIN_RANGE = 1;
	public static int MAX_RANGE = 100;
	public static int MAX_ATTEMPTS = 10;
    public static int MAX_ROUNDS = 3;
	
	public static void main(String[] args) {
		Random random = new Random();
		Scanner sc= new Scanner(System.in);
		int totalscore = 0;
		
		System.out.println("WELCOME TO NUMBER GUESSING GAME)\n");
		System.out.println("Total Number of Rounds: 3");
		System.out.println("No of Attempts for each round are 6\n");
		
		for(int i=1;i<=MAX_ROUNDS;i++) {
			int number = random.nextInt(MAX_RANGE)+ MIN_RANGE;
			int current_attempts = 0;
			System.out.printf("Current Round: %d: Guess the number between %d and %d in %d attemps.\n",i,MIN_RANGE,MAX_RANGE,MAX_ATTEMPTS);
			
			while(current_attempts < MAX_ATTEMPTS) {
				System.out.println("Enter your Guess: ");
				int guess_number = sc.nextInt();
				current_attempts=current_attempts+1;
				
				if(guess_number == number) {
					int score = MAX_ATTEMPTS - current_attempts;
					totalscore =+ score;
					System.out.printf("Congrats! You successfully guessed the number. Attempts: %d. Round score:%d",current_attempts,score);
					break;
				}
				else if(guess_number<number){
					System.out.printf("The number is greater than that %d. Attempts Left = %d\n",guess_number,MAX_ATTEMPTS-current_attempts);
					
				}
				else if(guess_number>number){
					System.out.printf("The number is less than that %d. Attempts Left = %d\n",guess_number,MAX_ATTEMPTS-current_attempts);
					
				}
				
			}
			if(current_attempts == MAX_ATTEMPTS) {
				System.out.printf("You lost in the Round = %d. Attempts = 0",i);
				System.out.printf("The Random number is %d\n",number);
			}
		}
		System.out.printf("Game Over. Total Score = %d\n", totalscore);
		sc.close();

		
	}

}
