package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class ShipPlacement {

    Scanner scan = new Scanner(System.in);

    public int[][] choosing(int[][] player) {
        display(player);
        // Ship Sizes: Carrier = 5, Battleship = 4, Cruiser = 3, Submarine = 3, Destroyer = 2
        int[] check = new int[5];
        Arrays.fill(check, 1);
        while (check[0] != 0 || check[1] != 0 || check[2] != 0 || check[3] != 0 || check[4] != 0) {
            System.out.println("1.Carrier 2.Battleship 3.Cruiser 4.Submarine 5.Destroyer");
            System.out.print("Please Pick a ship to place: ");
            int choose = scan.nextInt();
            if (choose == 1 && check[0] != 0) {
                placement(5, 1, player);
                check[0] = 0;
            } else if (choose == 2 && check[1] != 0) {
                placement(4, 2, player);
                check[1] = 0;
            } else if (choose == 3 && check[2] != 0) {
                placement(3, 3, player);
                check[2] = 0;
            } else if (choose == 4 && check[3] != 0) {
                placement(3, 4, player);
                check[3] = 0;
            } else if (choose == 5 && check[4] != 0) {
                placement(2, 5, player);
                check[4] = 0;
            } else {
                System.out.println("Error that has already been placed");
            }
        }
        System.out.println("Player has finished placement");
        return player;
    }

    public void placement(int shipsize, int shiptype, int[][] player) {
        System.out.println("1.North 2.South 3.East 4.West");
        System.out.print("pick direction to place: ");
        int dir = scan.nextInt();
        while (dir > 4 || dir < 1) {
            System.out.print("Error enter a number 1-4: ");
            dir = scan.nextInt();
        }
        System.out.println("pick coordinates to place");
        System.out.print("X: ");
        int x = coord();
        System.out.print("Y: ");
        int y = coord();
        if (dir == 1) {
            while (y - shipsize < 0) {
                System.out.print("Error the y position is out of bounds: ");
                y = coord();
            }
            //validate
            for (int i = 1; i < shipsize + 1; i++) {
                while (player[y - i][x - 1] != 0) {
                    System.out.println("Error overlaping ships");
                    System.out.print("X: ");
                    x = coord();
                    System.out.print("Y: ");
                    y = coord();
                }
            }
            //process
            for (int i = 1; i < shipsize + 1; i++) {
                player[y - i][x - 1] = shiptype;
            }
        }
        if (dir == 2) {
            while (y + shipsize > 11) {
                System.out.print("Error the y position is out of bounds: ");
                y = coord();
            }
            for (int i = -1; i < shipsize - 1; i++) {
                while (player[y + i][x - 1] != 0) {
                    System.out.println("Error overlaping ships");
                    System.out.print("X: ");
                    x = coord();
                    System.out.print("Y: ");
                    y = coord();
                }
            }
            for (int i = -1; i < shipsize - 1; i++) {
                player[y + i][x - 1] = shiptype;
            }
        }
        if (dir == 3) {
            while (x + shipsize > 11) {
                System.out.print("Error the x position is out of bounds: ");
                x = coord();
            }
            for (int i = -1; i < shipsize - 1; i++) {
                while (player[y - 1][x + i] != 0) {
                    System.out.println("Error overlaping ships");
                    System.out.print("X: ");
                    x = coord();
                    System.out.print("Y: ");
                    y = coord();
                }
            }
            for (int i = -1; i < shipsize - 1; i++) {
                player[y - 1][x + i] = shiptype;

            }
        }
        if (dir == 4) {
            while (x - shipsize < 0) {
                System.out.print("Error the x position is out of bounds: ");
                x = coord();
            }
            for (int i = 1; i < shipsize + 1; i++) {
                while (player[y - 1][x - i] != 0) {
                    System.out.println("Error overlaping ships");
                    System.out.print("X: ");
                    x = coord();
                    System.out.print("Y: ");
                    y = coord();
                }
            }
            for (int i = 1; i < shipsize + 1; i++) {
                player[y - 1][x - i] = shiptype;
            }
        }
        display(player);
    }

    public void display(int[][] player) {
        int row = 10;
        int column = 10;
        int count = 1;
        int display = 2;
        for (int x = 0; x < 11; x++) {
            System.out.print(x + "  ");
        }
        System.out.println("");
        System.out.print("1  ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(player[i][j] + "  ");
                if (count == 10) {
                    System.out.println("");
                    count = 0;
                    if (display < 10) {
                        System.out.print(display + "  ");
                    }
                    if (display == 10) {
                        System.out.print(display + " ");
                    }
                }
                count++;
            }
            display++;
        }
    }

    public int coord() {
        int cord = scan.nextInt();
        while (cord > 11 || cord < 1) {
            System.out.print("Error enter a coordinate 1-10: ");
            cord = scan.nextInt();
        }
        return cord;
    }

    public int[][] fire(int[][] player) {
        System.out.print("X: ");
        int x = coord()-1;
        System.out.print("Y: ");
        int y = coord()-1;
        if (player[x][y] < 6 && player[x][y] > 0) {
            System.out.println("HIT!!");
            player[x][y] = 9;
        } else {
            System.out.println("Miss");
            player[x][y] = 7;
        }
        return player;
    }

    public int battlefield(int[][] player1, int[][] player2) {
        int p1 = 0;
        int p2 = 0;
        int n = player1.length;
        int x = 17;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (x == player1[i][j]) {
                    p1++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (x == player2[i][j]) {
                    p2++;
                }
            }
        }
        if (p1 == x) {
            return 1;
        } else if (p1 == x) {
            return 2;
        } else {
            return 0;
        }

    }
}
