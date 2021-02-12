package battleship;

import java.util.Scanner;

public class BattleShip {

    public static void main(String[] args) {
        int[][] player1 = new int[10][10];
        int[][] player2 = new int[10][10];
        ShipPlacement placer = new ShipPlacement();
        ShipPlacement placer2 = new ShipPlacement();
        System.out.println("Welcome to Battleship");
        Scanner scan = new Scanner(System.in);
        System.out.print("would you like to hear the rules? (y/n): ");
        String str = scan.next();
        boolean d = str.equalsIgnoreCase("Y") || str.equalsIgnoreCase("N") || str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n");
        while (!d) {
            System.out.print("Error enter again: ");
            str = scan.next();
            d = str.equalsIgnoreCase("Y") || str.equalsIgnoreCase("N") || str.equalsIgnoreCase("y") || str.equalsIgnoreCase("n");
        }
        if (str.equals("y") || str.equals("Y")) {
            System.out.println("Players = 2\nGoal = Sink all of the opponent's ships\nSetup = Each player sets their board with ships\nRules = Each player takes turns taking shots at their opponent's ships");
        }
        System.out.println("**Player one**\nChoose your ships");
        player1 = placer.choosing(player1);
        System.out.println("**Player two**\nChoose your ships");
        player2 = placer.choosing(player2);
        while (placer.battlefield(player1,player2) == 0) {
            System.out.println("Player one Fire!");
            placer.display(player1);
            placer.fire(player2);
            System.out.println("Player two Fire!");
            placer.fire(player1);
            placer.display(player2);
        }
        if(placer.battlefield(player1, player2) == 1){
        System.out.println("CONGRATULATIONS PLAYER ONE\n **YOU HAVE WON**");
        }
        else if(placer.battlefield(player1, player2) == 2){
        System.out.println("CONGRATULATIONS PLAYER TWO\n **YOU HAVE WON**");
        }
        else{
            System.out.println("Unknown Error");
        }
    }
}