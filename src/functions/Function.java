package functions;

import java.io.IOException;
import java.util.*;

import company.Company;
import player.Player;

public class Function {
    Player player = new Player();
    Company company = new Company();

    public int welcome() {
        System.out.println("\n\n\n\t\t\t\t\tWelcome TO\n");
        System.out.println("\t\t\t\t  Monopoly Stock Exchange\n\n");

        return 0;
    }

    public int instructions() {
        System.out.println(" Welcome to Monopoly Stock Exchange Game");
        System.out.println(" Here are the rules of the game\n");
        System.out.println(
                "  1.This is a 3 player  modern stock trading game designed to be hassle-free and fun at every corner.\n ");
        System.out.println(
                "  2.Goal of this game is to become the first player to have more than Rs.20000 and win this ultimate stock market battle.The player will be declared bankrupt once he reaches Rs0.\n");
        System.out.println(
                "  3.This game has 12 blocks that start at 1 which is |GO|. Collect Rs.2000 every time the player passes 1(GO).  Players will have an option to move maximum of 3 blocks at a time.\n ");
        System.out.println(
                "  4.Land on the blocks numbered 2,5,6,8,10,11 to buy the company mentioned on the respective block by purchasing its shares.\n ");
        System.out.println(
                "  5.If a player lands on any of these |Company Block| which is owned by another player, the share value of that company will increase accordingly and the company owner will collect |service rent| from that player. \n");
        System.out.println(
                "  6.Blocks numbered 3,8 are the |Trading blocks|. Here players can trade and transfer their shares.\n ");
        System.out.println(
                "  7.If a player lands on block 4, they are caught in |Shortlisting Fraud|, pay fine of Rs.500 to the bank. \n");
        System.out.println(
                "  8.If a player lands on block 7, they face an |Income Tax Raid|, the share value of all the companies that player own will decrease by 5% along with a fine of Rs.200 to the bank. \n");
        System.out.println("  9.Get lucky with a |Chance Card| every time the player lands on block 12. \n");
        System.out.println(
                " 10.The players will have an option to sell their share anytime during the course of the game.\n");
        return 0;
    }

    public int board() {
         System.out.println("                                                          \t" +"________________________________________________________________                                                                                 ");
         System.out.println("  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \t" +"                                                                                    ");
         System.out.println(" |            |             |             |              |\t" +"          RAJIB                                                     "  );
         System.out.println(" |     1.     |      2.     |      3.     |      4.      |\t" +"    current balance     :- 10000                                                      ");
         System.out.println(" |     GO     |   Amazon    |    Stock    |   Short      |\t" +"    companies owned     :- Amazon , Microsoft                                                          ");
         System.out.println(" |            |             |    Market   |   Listing    |\t" +"    shared owned        :- Amazon-5 , Tesla-10                                                                ");
         System.out.println(" |            |             |             |   Fraud      |\t" +"    net values of shares:- 200 , 400                                                           ");
         System.out.println(" |_ _ _ _ _ _ |_ _ _ _ _ _ _| _ _ _ _ _ _ |_ _ _ _ _ _ _ |\t" +"                                                                 ");
         System.out.println(" |            |                           |              |\t" +"________________________________________________________________                                                            ");
         System.out.println(" |    12.     |                           |      5.      |\t" +"                                                                 ");
         System.out.println(" |   Chance   |                           |    Tesla     |\t" +"          Hitansh                                                               ");
         System.out.println(" |            |                           |              |\t" +"    current balance     :- 10000                                                        ");
         System.out.println(" |_ _ _ _ _ _ |                           | _ _ _ _ _ _ _|\t" +"    companies owned     :- Amazon , Microsoft                                                               ");
         System.out.println(" |            |                           |              |\t" +"    shared owned        :- Amazon-5 , Tesla-10                                                               ");
         System.out.println(" |     11.    |                           |      6.      |\t" +"    net values of shares:- 200 , 400                                                          ");
         System.out.println(" |  Facebook  |                           |   Micorsoft  |\t" +"                                                                   ");
         System.out.println(" |            |                           |              |\t" +"_________________________________________________________________                                                                           ");
         System.out.println(" |_ _ _ _ _ _ | _ _ _ _ _ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ |\t" +"                                                                   ");
         System.out.println(" |            |              |            |              |\t" +"          Dhruv                                                           ");
         System.out.println(" |     10.    |      9.      |     8.     |      7.      |\t" +"    current balance     :- 10000                                                            ");
         System.out.println(" |    ISRO    |    Google    |   Stock    |    Income    |\t" +"    companies owned     :- Amazon , Microsoft                                                           ");
         System.out.println(" |            |              |   Market   |   Tax Raid   |\t" +"    shared owned        :- Amazon-5 , Tesla-10                                                                ");
         System.out.println(" |            |              |            |              |\t" +"    net values of shares:- 200 , 400                                                                            ");
         System.out.println(" |_ _ _ _ _ _ |_ _ _ _ _ _ _ |_ _ _ _ _ _ |_ _ _ _ _ _ _ |\t" +"                                                                             ");
         System.out.println("                                                          \t" +"_________________________________________________________________"     );  
        return 0;
    }

    public String chance() {
        String[] chanceName = { "Hostile takeover attempt - pay lawyer 1000",
                "Your least expensive ticket -pay 5 times share price to bank and inc",
                "Your most expensive ticket - pay 2 times share price to bak and inc",
                "Go to trading allowed counter , pass go (nearest trading counter)." };
        Random random = new Random();
        int randomNumber = random.nextInt(chanceName.length);
        String s = chanceName[randomNumber];
        System.out.println(s);
        return s;
    }

    public void quit() {
        System.out.println("You Lose the game");
        System.out.println("Better Luck Next Time!");
    }

    public void winner() {
        System.out.println("Congratulations You Have Won The Game");
    }

    public void promptEnterKey() {
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//     public void demo(){
//             System.out.println("Press any key..");
//             Scanner sc = new Scanner(System.in);
//             String a = sc.nextLine();
//     }

    /*
     * public void cls(){ try{ new ProcessBuilder("cmd", "/c",
     * "cls").inheritIO().start().waitFor(); } catch(Exception E){
     * System.out.println(E); } }
     */

     public int dice(int p){
        int die = (int)(3.0 * Math.random()) + 1;
        int currentPosition = player.getPlayerCurrentPosition(p);
        currentPosition = currentPosition + die;
        player.setPlayerCurrentPosition(currentPosition, p);
        resetPostion(player.getPlayerCurrentPosition(p), p);
        // System.out.println("Now you are on "+player.getPlayerCurrentPosition(p));
        return die;
    }
    public int resetPostion(int currentPosition, int p){   // this function is to repeat the cycle to go to block 1 after block 12
        if(currentPosition > 12){
            currentPosition = currentPosition - 12;
            player.setPlayerCurrentPosition(currentPosition, p);
            go(p);
        } else{
            player.setPlayerCurrentPosition(currentPosition, p);
        }
        return 0;
     }
     public int go(int p){
         player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) + 2000, p);
        //  System.out.println("Your current balance is "+ player.getPlayerCurrentBal(p));
        return 0;
    }

    public void updateServiceRent(int n){
        float currentRent = company.getCompanyServiceRent(n);  
        currentRent = (float) (currentRent + 0.1*currentRent);
        company.setCompanyServiceRent(currentRent, n);
    }

    /*
    public int bankRupt(){ 
         System.out.println(" We are sorry to inform you that you don't have sufficient balance in your account.");
         System.out.println(" You dont even have any stocks to sell .");
         System.out.println(" You lost the game ");
            // isme abhi uss player ka naam add krna ha jo bankrupt ho rha ha
        return 0;
    }
    */
}