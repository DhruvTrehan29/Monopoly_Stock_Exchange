
import java.util.*;
import java.io.IOException;
import company.Company;
import player.Player;

public class App {

    static Player player = new Player();
    static Company company = new Company();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        welcome();

        instructions();

        System.out.print(" Enter the First Player's name:- ");
        String name1 = sc.nextLine();
        player.setPlayerName(name1, 1);

        System.out.print(" Enter the Second Player's name:- ");
        String name2 = sc.nextLine();
        player.setPlayerName(name2, 2);

        System.out.print(" Enter the Third Player's name:- ");
        String name3 = sc.nextLine();
        player.setPlayerName(name3, 3);

        System.out.println("\n Press Enter to start the game... ");

        promptEnterKey();
        board();

        for (int i = 1; i > 0; i++) {
            System.out.println("");
            if (i % 3 == 1) {
                i = 1;
                System.out.println(player.getPlayerName(1) + " press ENTER to roll the dice");

            } else if (i % 3 == 2) {
                i = 2;
                System.out.println(player.getPlayerName(2) + " press ENTER to roll the dice");

            } else {
                i = 3;
                System.out.println(player.getPlayerName(3) + " press ENTER to roll the dice");

                // break;
            }
            promptEnterKey();
            excessBalance(i);

            int die = (int) (3.0 * Math.random()) + 1;
            int currentPosition = player.getPlayerCurrentPosition(i);
            currentPosition = currentPosition + die;
            player.setPlayerCurrentPosition(currentPosition, i);
            resetPostion(player.getPlayerCurrentPosition(i), i);

            System.out.println(" You rolled " + die);
            System.out.println(" Now you are on block " + player.getPlayerCurrentPosition(i));

            switch (currentPosition) {
                case 1: {

                    System.out.println(" You are on |GO|");
                    System.out.println(" You will recieve dollar 2000 when you pass");
                    System.out.println("");
                    System.out.print("Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();
                    System.out.println("");
                    System.out.print("Press ENTER to Continue ...");
                    promptEnterKey();
                    break;
                }
                case 2: {
                    System.out.println(" You have reached | AMAZON |");

                    if (company.getCompanyOwner(2) == 0) {

                        System.out.println(" Cost = $ 6000 ");
                        System.out.println(" Total shares = 20 ");
                        System.out.println(" Share price = $ 200 ");
                        System.out.println(" It will increase by 10% whenever other players arrives on the same");
                        System.out.println(" Service Rent of Amazon is 400");
                        System.out.println(" Do you want to buy or not ? (YES/NO)");

                        String response = sc.next();

                        if (response.equalsIgnoreCase("yes")) {
                            if (player.getPlayerCurrentBal(i) >= 6000) {
                                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 6000, i);
                                player.setPlayerSharesOwned(i, 2, company.getCompanyNoOfShares(2));
                                company.setCompanyOwner(2, i);
                                player.setPlayerCompaniesOwned(i, 1);
                                System.out.println(" Congratulations !!!");
                                System.out.println(" You bought Amazon");
                            } else {
                                System.out.println(" You don't have sufficient balance to buy Amazon");
                                System.out.println(
                                        " Do u want to sell your shares ? Remember , you will have to sell all your shares ,you will not be allowed to sell a particular amount of shares ( Enter yes / no )");

                                String res = sc.next();

                                if (res.equalsIgnoreCase("yes")) {
                                    sell(i);
                                    if (player.getPlayerCurrentBal(i) >= 6000) {
                                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 6000, i);
                                        player.setPlayerSharesOwned(i, 2, company.getCompanyNoOfShares(2));
                                        company.setCompanyOwner(2, i);
                                        player.setPlayerCompaniesOwned(i, 1);
                                        System.out.println(" Congratulations !!!");
                                        System.out.println(" You bought Amazon");
                                        // System.out.println(" Player " + i + "'s current Balance = ");
                                        player.getPlayerCurrentBal(i);
                                    } else {
                                        System.out.println(" You still don't have enough balance");
                                        System.out.println(" Come back soon");
                                    }
                                }
                            }
                        } else {
                            System.out.println(" Continue the game");
                            System.out.println(" Thanks for visiting Amazon");
                        }

                    } else {
                        int owner = company.getCompanyOwner(2);

                        if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(2)) {
                            System.out.println(" Amazon is owned by " + player.getPlayerName(owner));

                            System.out
                                    .println(" You will have to pay $" + company.getCompanyServiceRent(2) + " as rent");
                            player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(2),
                                    i);

                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(2), owner);
                            updateSharePrice(2);

                        } else {
                            sell(i);
                            if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(2)) {
                                System.out.println(" Amazon is owned by " + player.getPlayerName(owner));

                                System.out.println(
                                        " You will have to pay $" + company.getCompanyServiceRent(2) + " as rent");
                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(2), i);

                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(2), owner);
                                updateSharePrice(2);

                            } else {
                                bankRupt(player.getPlayerName(i));
                            }

                        }

                    }
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();
                    
                    break;
                }
                case 3: {
                    stockMarket(i);
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }
                case 4: {
                    fraud(i);
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;

                }
                case 5: {
                    System.out.println(" You have reached | TESLA |");
                    if (company.getCompanyOwner(5) == 0) {

                        System.out.println(" Cost = $ 5500 ");
                        System.out.println(" Total shares = $ 45 ");
                        System.out.println(" Share price = $ 100 ");
                        System.out.println(" It will increase by 10% whenever other players arrives on the same");
                        System.out.println(" Service Rent = $ 450");
                        System.out.println(" Do you want to buy or not ? (YES/NO)");

                        String response = sc.next();

                        if (response.equalsIgnoreCase("yes")) {
                            if (player.getPlayerCurrentBal(i) >= 5500) {
                                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 5500, i);
                                player.setPlayerSharesOwned(i, 5, company.getCompanyNoOfShares(5));
                                company.setCompanyOwner(5, i);
                                player.setPlayerCompaniesOwned(i, 4);
                                System.out.println(" Congratulations !!!");
                                System.out.println(" You bought Tesla");
                                
                            } else {
                                System.out.println(" You don't have sufficient balance to buy Tesla");
                                System.out.println(" Do you want to sell your shares ? Remember , you will have to sell all your shares ,you will not be allowed to sell a particular amount of shares ( Enter yes / no )");

                                String res = sc.next();

                                if (res.equalsIgnoreCase("yes")) {
                                    sell(i);
                                    if (player.getPlayerCurrentBal(i) >= 5500) {
                                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 5500, i);
                                        player.setPlayerSharesOwned(i, 5, company.getCompanyNoOfShares(5));
                                        company.setCompanyOwner(5, i);
                                        player.setPlayerCompaniesOwned(i, 4);
                                        System.out.println(" Congratulations !!!");
                                        System.out.println(" You bought Tesla");
                                        
                                        player.getPlayerCurrentBal(i);
                                    } else {
                                        System.out.println(" You still don't have enough balance");
                                        System.out.println(" Come back soon");
                                    }
                                }
                            }
                        } else {
                            System.out.println(" Continue the game");
                            System.out.println(" Thanks for visiting Tesla");
                        }

                    } else {
                        int owner = company.getCompanyOwner(5);

                        if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(5)) {
                            System.out.println(" Tesla is owned by " + player.getPlayerName(owner));

                            System.out
                                    .println(" You will have to pay $" + company.getCompanyServiceRent(5) + " as rent");
                            player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(5),
                                    i);

                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(5), owner);
                            updateSharePrice(5);

                        } else {
                            sell(i);
                            if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(5)) {
                                System.out.println(" Tesla is owned by " + player.getPlayerName(owner));

                                System.out.println(
                                        " You will have to pay $" + company.getCompanyServiceRent(5) + " as rent");
                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(5), i);

                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(5), owner);
                                updateSharePrice(5);

                            } else {
                                bankRupt(player.getPlayerName(i));
                            }

                        }

                    }
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }

                case 6: {
                    System.out.println(" You have reached | MICROSOFT |");
                    System.out.println("");
                    if (company.getCompanyOwner(6) == 0) {

                        System.out.println(" Cost = $5000");
                        System.out.println(" Total shares = 30 ");
                        System.out.println(" Each share cost = 100 ");
                        System.out.println("will increase 10% whenever other players arrives on the same");
                        System.out.println(" Service Rent = 300");
                        System.out.println(" Do you want to buy or not ? ( YES/NO )");

                        String response = sc.next();

                        if (response.equalsIgnoreCase("yes")) {
                            if (player.getPlayerCurrentBal(i) >= 6000) {
                                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 6000, i);
                                player.setPlayerSharesOwned(i, 6, company.getCompanyNoOfShares(6));
                                company.setCompanyOwner(6, i);
                                player.setPlayerCompaniesOwned(i, 5);
                                System.out.println(" Congratulations !!!");
                                System.out.println(" You have bought Microsoft");

                            } else {
                                System.out.println(" You don't have sufficient balance to buy Microsoft");
                                System.out.println(" Do u want to sell your shares ? Remember , you will have to sell all your shares ,you will not be allowed to sell a particular amount of shares ( Enter yes / no )");

                                String res = sc.next();

                                if (res.equalsIgnoreCase("yes")) {
                                    sell(i);
                                    if (player.getPlayerCurrentBal(i) >= 5000) {
                                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 5000, i);
                                        player.setPlayerSharesOwned(i, 6, company.getCompanyNoOfShares(6));
                                        company.setCompanyOwner(6, i);
                                        player.setPlayerCompaniesOwned(i, 5);
                                        System.out.println(" Congratulations !!!");
                                        System.out.println(" You have bought Microsoft");

                                        player.getPlayerCurrentBal(i);
                                    } else {
                                        System.out.println(" You still don't have enough balance");
                                        System.out.println(" Come back soon");
                                    }
                                }
                            }
                        } else {
                            System.out.println(" Continue the game");
                            System.out.println(" Thanks for visiting Microsoft");
                        }

                    } else {
                        int owner = company.getCompanyOwner(6);

                        if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(6)) {
                            System.out.println(" Microsoft is owned by " + player.getPlayerName(owner));

                            System.out
                                    .println(" You will have to pay $" + company.getCompanyServiceRent(6) + " as rent");
                            player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(6),
                                    i);

                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(6), owner);
                            updateSharePrice(6);

                        } else {
                            sell(i);
                            if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(6)) {
                                System.out.println(" Microsoft is owned by " + player.getPlayerName(owner));

                                System.out.println(
                                        " You will have to pay $" + company.getCompanyServiceRent(6) + " as rent");
                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(6), i);

                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(6), owner);
                                updateSharePrice(6);

                            } else {
                                bankRupt(player.getPlayerName(i));
                            }

                        }

                    }
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }

                case 7: {
                    taxRaid(i);
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }
                case 8: {
                    stockMarket(i);
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;

                }
                case 9: {
                    System.out.println(" You have reached | GOOGLE |");

                    if (company.getCompanyOwner(9) == 0) {

                        System.out.println(" Cost = $10000 ");
                        System.out.println(" Total shares = 10 ");
                        System.out.println(" Each share cost is 1000 " );
                        System.out.println("will increase 5% whenever other players arrives on the same ");
                        System.out.println(" Service Rent = 400");
                        System.out.println(" Do you want to buy or not ? ( YES/NO )");

                        String response = sc.next();

                        if (response.equalsIgnoreCase("yes")) {
                            if (player.getPlayerCurrentBal(i) >= 10000) {
                                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 10000, i);
                                player.setPlayerSharesOwned(i, 9, company.getCompanyNoOfShares(9));
                                company.setCompanyOwner(9, i);
                                player.setPlayerCompaniesOwned(i, 8);
                                System.out.println(" Congratulations !!!");
                                System.out.println(" You have bought Google");

                                player.getPlayerCurrentBal(i);
                            } else {
                                System.out.println(" You don't have sufficient balance to buy Google");
                                System.out.println(
                                        " Do u want to sell your shares ? Remember , you will have to sell all your shares ,you will not be allowed to sell a particular amount of shares ( Enter yes / no )");

                                String res = sc.next();

                                if (res.equalsIgnoreCase("yes")) {
                                    sell(i);
                                    if (player.getPlayerCurrentBal(i) >= 10000) {
                                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 10000, i);
                                        player.setPlayerSharesOwned(i, 9, company.getCompanyNoOfShares(9));
                                        company.setCompanyOwner(9, i);
                                        player.setPlayerCompaniesOwned(i, 8);
                                        System.out.println(" Congratulations !!!");
                                        System.out.println(" You have bought Google");
                                        System.out.println("Player " + i + "'s current Balance = ");
                                        player.getPlayerCurrentBal(i);
                                    } else {
                                        System.out.println(" You still don't have enough balance");
                                        System.out.println(" Come back soon");
                                    }
                                }
                            }
                        } else {
                            System.out.println(" Continue the game");
                            System.out.println(" Thanks for visiting Google");
                        }

                    } else {
                        int owner = company.getCompanyOwner(9);

                        if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(9)) {
                            System.out.println(" Google is owned by " + player.getPlayerName(owner));

                            System.out
                                    .println(" You will have to pay $" + company.getCompanyServiceRent(9) + " as rent");
                            player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(9),
                                    i);

                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(9), owner);
                            updateSharePrice(9);

                        } else {
                            sell(i);
                            if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(9)) {
                                System.out.println(" Google is owned by " + player.getPlayerName(owner));

                                System.out.println(
                                        " You will have to pay $" + company.getCompanyServiceRent(9) + " as rent");
                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(9), i);

                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(9), owner);
                                updateSharePrice(9);

                            } else {
                                bankRupt(player.getPlayerName(i));
                            }

                        }

                    }
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }
                case 10: {
                    System.out.println(" You have reached | ISRO |");
                    if (company.getCompanyOwner(10) == 0) {

                        System.out.println(" Cost = $7000 ");
                        System.out.println(" Total shares = 10 ");
                        System.out.println(" Share price = $500 ");
                        System.out.println(" It will increase 10% whenever other players arrives on the same ");
                        System.out.println(" Service Rent = $500");
                        System.out.println(" Do you want to buy or not ?(YES/NO)");

                        String response = sc.next();

                        if (response.equalsIgnoreCase("yes")) {
                            if (player.getPlayerCurrentBal(i) >= 7000) {
                                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 7000, i);
                                player.setPlayerSharesOwned(i, 10, company.getCompanyNoOfShares(10));
                                company.setCompanyOwner(10, i);
                                player.setPlayerCompaniesOwned(i, 9);
                                System.out.println(" Congratulations !!!");
                                System.out.println(" You bought ISRO");

                                player.getPlayerCurrentBal(i);
                            } else {
                                System.out.println(" You don't have sufficient balance to buy ISRO");
                                System.out.println(
                                        " Do u want to sell your shares ? Remember , you will have to sell all your shares ,you will not be allowed to sell a particular amount of shares ( Enter yes / no )");

                                String res = sc.next();

                                if (res.equalsIgnoreCase("yes")) {
                                    sell(i);
                                    if (player.getPlayerCurrentBal(i) >= 7000) {
                                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 7000, i);
                                        player.setPlayerSharesOwned(i, 10, company.getCompanyNoOfShares(10));
                                        company.setCompanyOwner(10, i);
                                        player.setPlayerCompaniesOwned(i, 9);
                                        System.out.println(" Congratulations !!!");
                                        System.out.println(" You have bought ISRO");

                                        player.getPlayerCurrentBal(i);
                                    } else {
                                        System.out.println(" You still don't have enough balance");
                                        System.out.println(" Come back soon");
                                    }
                                }
                            }
                        } else {
                            System.out.println(" Continue the game");
                            System.out.println(" Thanks for visiting ISRO");
                        }

                    } else {
                        int owner = company.getCompanyOwner(10);

                        if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(10)) {
                            System.out.println(" ISRO is owned by " + player.getPlayerName(owner));

                            System.out.println(
                                    " You will have to pay $" + company.getCompanyServiceRent(10) + " as rent");
                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(10), i);

                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(10), owner);
                            updateSharePrice(10);

                        } else {
                            sell(i);
                            if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(10)) {
                                System.out.println(" ISRO is owned by " + player.getPlayerName(owner));

                                System.out.println(
                                        " You will have to pay $" + company.getCompanyServiceRent(10) + " as rent");
                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(10), i);

                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(10), owner);
                                updateSharePrice(10);

                            } else {
                                bankRupt(player.getPlayerName(i));
                            }

                        }

                    }
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }
                case 11: {
                    System.out.println(" You have reached | Facebook |");
                    if (company.getCompanyOwner(11) == 0) {

                        System.out.println(" Cost = $6500 ");
                        System.out.println(" Total shares = 30");
                        System.out.println(
                                " Each share cost is 200");  
                                System.out.println(" It will increase by 10% whenever other players arrives on the same ");
                        System.out.println(" Service Rent = 600");
                        System.out.println(" Do you want to buy or not ?(YES/NO)");

                        String response = sc.next();

                        if (response.equalsIgnoreCase("yes")) {
                            if (player.getPlayerCurrentBal(i) >= 6500) {
                                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 6500, i);
                                player.setPlayerSharesOwned(i, 11, company.getCompanyNoOfShares(11));
                                company.setCompanyOwner(11, i);
                                player.setPlayerCompaniesOwned(i, 10);
                                System.out.println(" Congratulations !!!");
                                System.out.println(" You have bought Facebook");

                                player.getPlayerCurrentBal(i);
                            } else {
                                System.out.println(" You don't have sufficient balance to buy Facebook");
                                System.out.println(
                                        "Do u want to sell your shares ? Remember , you will have to sell all your shares ,you will not be allowed to sell a particular amount of shares ( Enter yes / no )");

                                String res = sc.next();

                                if (res.equalsIgnoreCase("yes")) {
                                    sell(i);
                                    if (player.getPlayerCurrentBal(i) >= 6500) {
                                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) - 6500, i);
                                        player.setPlayerSharesOwned(i, 11, company.getCompanyNoOfShares(11));
                                        company.setCompanyOwner(11, i);
                                        player.setPlayerCompaniesOwned(i, 11);
                                        System.out.println(" Congratulations !!!");
                                        System.out.println(" You have bought Facebook");

                                        player.getPlayerCurrentBal(i);
                                    } else {
                                        System.out.println(" You still don't have enough balance");
                                        System.out.println(" Come back soon");
                                    }
                                }
                            }
                        } else {
                            System.out.println(" Continue the game");
                            System.out.println(" Thanks for visiting Amazon");
                        }

                    } else {
                        int owner = company.getCompanyOwner(11);

                        if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(11)) {
                            System.out.println(" Facebook is owned by " + player.getPlayerName(owner));

                            System.out.println(
                                    " You will have to pay $" + company.getCompanyServiceRent(11) + " as rent");
                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(11), i);

                            player.setPlayerCurrentBal(
                                    player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(11), owner);
                            updateSharePrice(11);

                        } else {
                            sell(i);
                            if (player.getPlayerCurrentBal(i) >= company.getCompanyServiceRent(11)) {
                                System.out.println(" Facebook is owned by " + player.getPlayerName(owner));

                                System.out.println(
                                        " You will have to pay $" + company.getCompanyServiceRent(11) + " as rent");
                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(i) - company.getCompanyServiceRent(11), i);

                                player.setPlayerCurrentBal(
                                        player.getPlayerCurrentBal(owner) + company.getCompanyServiceRent(11), owner);
                                updateSharePrice(11);

                            } else {
                                bankRupt(player.getPlayerName(i));
                            }

                        }

                    }
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }
                case 12: {
                    chance(i);
                    System.out.println("");
                    System.out.print(" Press ENTER to Continue ...");
                    promptEnterKey();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    board();

                    break;
                }
            }
        }

    }

    static int welcome() {
        System.out.println("\n\n\n\t\t\t\t\tWelcome TO\n");
        System.out.println("\t\t\t\t  Monopoly Stock Exchange\n\n");

        return 0;
    }

    static int instructions() {
        System.out.println(" Welcome to Monopoly Stock Exchange Game");
        System.out.println(" Here are the rules of the game\n");
        System.out.println(
                "  1.This is a 3 player  modern stock trading game designed to be hassle-free and fun at every corner.\n ");
        System.out.println(
                "  2.Goal of this game is to become the first player to have more than Rs.20000 and win this ultimate stock market battle.The player will be declared bankrupt once he reaches Rs0.\n");
        System.out.println(
                "  3.This game has 12 blocks that start at 1 which is |GO|. Collect Rs.2000 every time the player passes 1(GO).  Players will have an option to move maximum of 3 blocks at a time. \n");
        System.out.println(
                "  4.Land on fthe blocks numbered 2,5,6,8,10,11 to buy the company mentioned on the respective block by purchasing its shares. \n");
        System.out.println(
                "  5.If a player lands on any of these |Company Block| which is owned by another player, the share value of that company will increase accordingly and the company owner will collect ???service rent??? from that player.\n ");
        System.out.println(
                "  6.Blocks numbered 3,8 are the |Trading blocks|. Here players can trade and transfer their shares. \n");
        System.out.println(
                "  7.If a player lands on block 4, they are caught in ???Shortlisting Fraud???, pay fine of Rs.500 to the bank. \n");
        System.out.println(
                "  8.If a player lands on block 7, they face an |Income Tax Raid|, the share value of all the companies that player own will decrease by 5% along with a fine of Rs.200 to the bank.\n ");
        System.out.println("  9.Get lucky with a |Chance Card| every time the player lands on block 12. \n");
        System.out.println(
                " 10.The players will have an option to sell their share anytime during the course of the game.\n");
        System.out.println(
                " 11. Please note when a player is out of money and decides to sell his shares, he will have to sell all his shares.\n");

        return 0;
    }

    public static int board() {
        System.out.println("                                                          \t"+ "________________________________________________________________                                                                                 ");
        System.out.println("  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \t"+ "                                                      ");
        System.out.println(" |            |             |             |              |\t"+ "         " + player.getPlayerName(1));
        System.out.println(" |     1.     |      2.     |      3.     |      4.      |\t"+ "    Current Balance     :- " + player.getPlayerCurrentBal(1));
        System.out.println(" |     GO     |   Amazon    |    Stock    |   Short      |\t"+ "    Companies Owned     :- " + companiesOwned(1, 1));
        System.out.println(" |            |             |    Market   |   Listing    |\t"+ "    Shared Owned        :- " + sharesOwned(1));
        System.out.println(" |            |             |             |   Fraud      |\t"+ "    Current Position    :- " + player.getPlayerCurrentPosition(1));
        System.out.println(" |_ _ _ _ _ _ |_ _ _ _ _ _ _| _ _ _ _ _ _ |_ _ _ _ _ _ _ |\t"+ "________________________________________________________________                                                               ");
        System.out.println(" |            |                           |              |\t"+ "                                                                          ");
        System.out.println(" |    12.     |                           |      5.      |\t"+ "         "+ player.getPlayerName(2));
        System.out.println(" |   Chance   |                           |    Tesla     |\t"+ "    Current Balance     :- " + player.getPlayerCurrentBal(2));
        System.out.println(" |            |                           |              |\t"+ "    Companies Owned     :- " + companiesOwned(2, 1));
        System.out.println(" |_ _ _ _ _ _ |                           | _ _ _ _ _ _ _|\t"+ "    Shared Owned        :- " + sharesOwned(2));
        System.out.println(" |            |                           |              |\t"+ "    Current Position    :- " + player.getPlayerCurrentPosition(2));
        System.out.println(" |     11.    |                           |      6.      |\t"+ "_________________________________________________________________                                                             ");
        System.out.println(" |  Facebook  |                           |   Micorsoft  |\t"+ "                                                                   ");
        System.out.println(" |            |                           |              |\t"+ "          "+ player.getPlayerName(3));
        System.out.println(" |_ _ _ _ _ _ | _ _ _ _ _ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ |\t"+ "    Current Balance     :- " + player.getPlayerCurrentBal(3));
        System.out.println(" |            |              |            |              |\t"+ "    Companies Owned     :- " + companiesOwned(3, 1));
        System.out.println(" |     10.    |      9.      |     8.     |      7.      |\t"+ "    Shared Owned        :- " + sharesOwned(3));
        System.out.println(" |    ISRO    |    Google    |   Stock    |    Income    |\t"+ "    Current Position    :- " + player.getPlayerCurrentPosition(3));
        System.out.println(" |            |              |   Market   |   Tax Raid   |\t"+ "_________________________________________________________________                                                                    ");
        System.out.println(" |            |              |            |              |\t"+ "                                                                                ");
        System.out.println(" |_ _ _ _ _ _ |_ _ _ _ _ _ _ |_ _ _ _ _ _ |_ _ _ _ _ _ _ |\t"+ "                                                                             ");

        System.out.println("SHARE PRICE :  Amazon - "+ company.getCompanySharePrice(2)+ ", Tesla - "+ company.getCompanySharePrice(5)+ ", Microsoft - "+ company.getCompanySharePrice(6)+ ", Google - "+ company.getCompanySharePrice(9)+ ", ISRO - "+ company.getCompanySharePrice(10)+ ", Facebook - "+ company.getCompanySharePrice(11) );
        System.out.println("SERVICE RENT : Amazon - "+ company.getCompanyServiceRent(2) + ", Tesla - "+ company.getCompanyServiceRent(5)+ ", Microsoft - "+ company.getCompanyServiceRent(6)+", Google - "+ company.getCompanyServiceRent(9)+", ISRO - "+ company.getCompanyServiceRent(10)+", Facebook - "+ company.getCompanyServiceRent(11) );
        return 0;
    }

    public static int companiesOwned(int p, float percent) {
        boolean[] companies = player.getPlayerCompaniesOwned(p);
        int count = 0;
        if (companies[1] == true) {
            System.out.print("Amazon ");
            company.setCompanySharePrice(company.getCompanySharePrice(2) * percent, 2);
            count++;
        }
        if (companies[4] == true) {
            System.out.print("Tesla ");
            company.setCompanySharePrice(company.getCompanySharePrice(2) * percent, 5);
            count++;
        }
        if (companies[5] == true) {
            System.out.print("Microsoft ");
            company.setCompanySharePrice(company.getCompanySharePrice(2) * percent, 6);
            count++;
        }
        if (companies[8] == true) {
            System.out.print("Google ");
            company.setCompanySharePrice(company.getCompanySharePrice(2) * percent, 9);
            count++;
        }
        if (companies[9] == true) {
            System.out.print("ISRO ");
            company.setCompanySharePrice(company.getCompanySharePrice(2) * percent, 10);
            count++;
        }
        if (companies[10] == true) {
            System.out.print("Facebook ");
            company.setCompanySharePrice(company.getCompanySharePrice(2) * percent, 11);
            count++;
        }
        return count;
    }

    public static int sharesOwned(int p) {
        if (player.getPlayerSharesOwned(p, 2) > 0) {
            System.out.print(" Amazon: " + player.getPlayerSharesOwned(p, 2));
        }
        if (player.getPlayerSharesOwned(p, 5) > 0) {
            System.out.print(" Tesla: " + player.getPlayerSharesOwned(p, 5));
        }
        if (player.getPlayerSharesOwned(p, 6) > 0) {
            System.out.print(" Microsoft: " + player.getPlayerSharesOwned(p, 6));
        }
        if (player.getPlayerSharesOwned(p, 9) > 0) {
            System.out.print(" Google: " + player.getPlayerSharesOwned(p, 9));
        }
        if (player.getPlayerSharesOwned(p, 10) > 0) {
            System.out.print(" ISRO: " + player.getPlayerSharesOwned(p, 10));
        }
        if (player.getPlayerSharesOwned(p, 11) > 0) {
            System.out.print(" Facebook: " + player.getPlayerSharesOwned(p, 11));
        }
        return 0;
    }

    public static String chance(int p) {
        String[] chanceName = { "Hostile takeover attempt - pay lawyer 1000",
                "Your least expensive ticket -pay 5 times share price to bank ",
                "Your most expensive ticket - pay 2 times share price to bank ",
                "Go to trading allowed counter , pass go (nearest trading counter)." };
        Random random = new Random();
        int randomNumber = random.nextInt(chanceName.length);
        String s = chanceName[randomNumber];
        System.out.println(s);

        if (randomNumber == 0) {

            if (player.getPlayerCurrentBal(p) >= 1000) {
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - 1000, p);
            } else {
                sell(p);
                if (player.getPlayerCurrentBal(p) >= 1000) {
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - 1000, p);
                } else {
                    bankRupt(player.getPlayerName(p));
                }
            }

        }
        if (randomNumber == 1) {
            boolean[] companiesOwned = player.getPlayerCompaniesOwned(p);
            int min = 2;
            if (companiesOwned[1] == true) {
                if (company.getCompanyCost(2) < company.getCompanyCost(min - 1)) {
                    min = 2;
                }
            }
            if (companiesOwned[4] == true) {
                if (company.getCompanyCost(5) < company.getCompanyCost(min - 1)) {
                    min = 5;
                }
            }
            if (companiesOwned[5] == true) {
                if (company.getCompanyCost(6) < company.getCompanyCost(min - 1)) {
                    min = 6;
                }
            }
            if (companiesOwned[8] == true) {
                if (company.getCompanyCost(9) < company.getCompanyCost(min - 1)) {
                    min = 9;
                }
            }
            if (companiesOwned[9] == true) {
                if (company.getCompanyCost(10) < company.getCompanyCost(min - 1)) {
                    min = 10;
                }
            }
            if (companiesOwned[10] == true) {
                if (company.getCompanyCost(11) < company.getCompanyCost(min - 1)) {
                    min = 11;
                }
            }
            float fine = company.getCompanySharePrice(min) * 5;
            if (player.getPlayerCurrentBal(p) >= fine) {
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - fine, p);
            } else {
                sell(p);
                if (player.getPlayerCurrentBal(p) >= fine) {
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - fine, p);
                } else {
                    bankRupt(player.getPlayerName(p));
                }

            }
        }

        if (randomNumber == 2) {
            boolean[] companiesOwned = player.getPlayerCompaniesOwned(p);
            int max = 2;
            if (companiesOwned[1] == true) {
                if (company.getCompanyCost(2) > company.getCompanyCost(max - 1)) {
                    max = 2;
                }
            }
            if (companiesOwned[4] == true) {
                if (company.getCompanyCost(5) > company.getCompanyCost(max - 1)) {
                    max = 5;
                }
            }
            if (companiesOwned[5] == true) {
                if (company.getCompanyCost(6) > company.getCompanyCost(max - 1)) {
                    max = 6;
                }
            }
            if (companiesOwned[8] == true) {
                if (company.getCompanyCost(9) > company.getCompanyCost(max - 1)) {
                    max = 9;
                }
            }
            if (companiesOwned[9] == true) {
                if (company.getCompanyCost(10) > company.getCompanyCost(max - 1)) {
                    max = 10;
                }
            }
            if (companiesOwned[10] == true) {
                if (company.getCompanyCost(11) > company.getCompanyCost(max - 1)) {
                    max = 11;
                }
            }
            float fine = company.getCompanySharePrice(max) * 2;
            if (player.getPlayerCurrentBal(p) >= fine) {
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - fine, p);
            } else {
                sell(p);
                if (player.getPlayerCurrentBal(p) >= fine) {
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - fine, p);
                } else {
                    bankRupt(player.getPlayerName(p));
                }

            }
        }

        if (randomNumber == 3) {

            player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) + 2000, p);
            player.setPlayerCurrentPosition(3, p);
        }

        return s;

    }

    static void quit() {
        System.out.println("You Lose the game");
        System.out.println("Better Luck Next Time!");
    }

    static void winner() {
        System.out.println("Congratulations You Have Won The Game");
    }

    static void promptEnterKey() {

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int resetPostion(int currentPosition, int p) { // this function is to repeat the cycle to go to block
                                                                 // 1 after
        // block 12
        if (currentPosition > 12) {
            currentPosition = currentPosition - 12;
            player.setPlayerCurrentPosition(currentPosition, p);
            go(p);
        } else {
            player.setPlayerCurrentPosition(currentPosition, p);
        }
        return 0;
    }

    public static int go(int p) {
        player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) + 2000, p);

        return 0;
    }

    public static void updateSharePrice(int n) {
        //float currentPrice = company.getCompanySharePrice(n);
        
        company.setCompanySharePrice((float) (company.getCompanySharePrice(n)*1.1) , n);
    }

    public static int sell(int i) {
        if (player.getPlayerSharesOwned(i, 2) > 0) {
            if (company.getCompanyOwner(2) != i) {
                int shareNum = player.getPlayerSharesOwned(i, 2);
                int owner = company.getCompanyOwner(2);
                float shareBal = (player.getPlayerSharesOwned(i, 2)) * (company.getCompanySharePrice(2));
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) + shareBal, i);
                player.setPlayerSharesOwned(i, 2, 0);

                if (player.getPlayerCurrentBal(owner) > shareBal) {
                    player.setPlayerSharesOwned(owner, 2, player.getPlayerSharesOwned(owner, 2) + shareNum);
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(owner) - shareBal, owner);
                } else {
                    bankRupt(player.getPlayerName(owner));
                }
            }
        }
        if (player.getPlayerSharesOwned(i, 5) > 0) {
            if (company.getCompanyOwner(5) != i) {
                int shareNum = player.getPlayerSharesOwned(i, 5);
                int owner = company.getCompanyOwner(5);
                float shareBal = (player.getPlayerSharesOwned(i, 5)) * (company.getCompanySharePrice(5));
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) + shareBal, i);
                player.setPlayerSharesOwned(i, 5, 0);

                if (player.getPlayerCurrentBal(owner) > shareBal) {
                    player.setPlayerSharesOwned(owner, 5, player.getPlayerSharesOwned(owner, 5) + shareNum);
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(owner) - shareBal, owner);
                } else {
                    bankRupt(player.getPlayerName(owner));
                }
            }
        }
        if (player.getPlayerSharesOwned(i, 6) > 0) {
            if (company.getCompanyOwner(6) != i) {
                int shareNum = player.getPlayerSharesOwned(i, 6);
                int owner = company.getCompanyOwner(6);
                float shareBal = (player.getPlayerSharesOwned(i, 6)) * (company.getCompanySharePrice(6));
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) + shareBal, i);
                player.setPlayerSharesOwned(i, 6, 0);

                if (player.getPlayerCurrentBal(owner) > shareBal) {
                    player.setPlayerSharesOwned(owner, 6, player.getPlayerSharesOwned(owner, 6) + shareNum);
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(owner) - shareBal, owner);
                } else {
                    bankRupt(player.getPlayerName(owner));
                }
            }
        }
        if (player.getPlayerSharesOwned(i, 9) > 0) {
            if (company.getCompanyOwner(9) != i) {
                int shareNum = player.getPlayerSharesOwned(i, 9);
                int owner = company.getCompanyOwner(9);
                float shareBal = (player.getPlayerSharesOwned(i, 9)) * (company.getCompanySharePrice(9));
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) + shareBal, i);
                player.setPlayerSharesOwned(i, 9, 0);

                if (player.getPlayerCurrentBal(owner) > shareBal) {
                    player.setPlayerSharesOwned(owner, 9, player.getPlayerSharesOwned(owner, 9) + shareNum);
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(owner) - shareBal, owner);
                } else {
                    bankRupt(player.getPlayerName(owner));
                }
            }
        }
        if (player.getPlayerSharesOwned(i, 10) > 0) {
            if (company.getCompanyOwner(10) != i) {
                int shareNum = player.getPlayerSharesOwned(i, 10);
                int owner = company.getCompanyOwner(10);
                float shareBal = (player.getPlayerSharesOwned(i, 10)) * (company.getCompanySharePrice(10));
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) + shareBal, i);
                player.setPlayerSharesOwned(i, 10, 0);

                if (player.getPlayerCurrentBal(owner) > shareBal) {
                    player.setPlayerSharesOwned(owner, 10, player.getPlayerSharesOwned(owner, 10) + shareNum);
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(owner) - shareBal, owner);
                } else {
                    bankRupt(player.getPlayerName(owner));
                }
            }
        }
        if (player.getPlayerSharesOwned(i, 11) > 0) {
            if (company.getCompanyOwner(11) != i) {
                int shareNum = player.getPlayerSharesOwned(i, 11);
                int owner = company.getCompanyOwner(11);
                float shareBal = (player.getPlayerSharesOwned(i, 11)) * (company.getCompanySharePrice(11));
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(i) + shareBal, i);
                player.setPlayerSharesOwned(i, 11, 0);

                if (player.getPlayerCurrentBal(owner) > shareBal) {
                    player.setPlayerSharesOwned(owner, 11, player.getPlayerSharesOwned(owner, 11) + shareNum);
                    player.setPlayerCurrentBal(player.getPlayerCurrentBal(owner) - shareBal, owner);
                } else {
                    bankRupt(player.getPlayerName(owner));
                }
            }
        }

        return 0;
    }

    public static int bankRupt(String gareeb) {
        System.out.println(" We are sorry to inform you that your current balance is 0 .");
        System.out.println(gareeb + " dont even have any stocks to sell .");
        System.out.println(gareeb + " lost the game ");
        System.out.println(" GAME OVER");

        System.exit(0);
        return 0;
    }

    static String mnc;

    static String company(int c) {
        if (c == 1) {
            mnc = "Amazon";
        } else if (c == 4) {
            mnc = "Tesla";
        } else if (c == 5) {
            mnc = "Microsoft";
        } else if (c == 8) {
            mnc = "Google";
        } else if (c == 9) {
            mnc = "ISRO";
        } else if (c == 10) {
            mnc = "Facebook";
        }
        return mnc;
    }

    public static void taxRaid(int p) {
        System.out.println(" Your businesses are being raided by the tax raid agents ");
        System.out.println(" You will have to pay 200 for each company");
        System.out.println(" Share price of your companies will be decreased by 5%");

        int companyCount = companiesOwned(p, (float) 0.95);
        int taxraid = companyCount * 200;
        if (player.getPlayerCurrentBal(p) > taxraid) {

            player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - taxraid, p);
        } else {
            System.out.println(" You will have to sell your shares");
            sell(p);
            if (player.getPlayerCurrentBal(p) > taxraid) {

                player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - taxraid, p);
            } else {
                bankRupt(player.getPlayerName(p));
            }

        }

    }

    public static void fraud(int p) {
        System.out.println(" You arrived on Short Listing Fraud");
        System.out.println(player.getPlayerName(p) + " Pay $500 to the bank");
        System.out.println(" Share price of your companies will be decreased by 20%");
        boolean[] owned = player.getPlayerCompaniesOwned(p);
        int count = 0;
        if(owned[1] ==true){
            count++;
        }
        if(owned[4] ==true){
            count++;
        }
        if(owned[5] ==true){
            count++;
        }
        if(owned[8] ==true){
            count++;
        }
        if(owned[9] ==true){
            count++;
        }
        if(owned[10] ==true){
            count++;
        }
        if(count == 0){
            System.out.println("You have no companies");
        } else{     
            System.out.println(" List of companies owned by " + player.getPlayerName(p) + " - ");
            companiesOwned(p, (float) 0.80);
        }
        
        if (player.getPlayerCurrentBal(p) >= 500) {
            player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - 500, p);

        } else {
            System.out.println(
                    " You don't have enough balance to pay for the short listing fraud \n Press Enter to sell all your shares. ");
            promptEnterKey();
            sell(p);
            if (player.getPlayerCurrentBal(p) >= 500) {
                player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - 500, p);

            } else {
                bankRupt(player.getPlayerName(p));
            }
        }
    }

    public static int excessBalance(int p) {
        if (player.getPlayerCurrentBal(p) >= 20000) {
            System.out.println(" Since your current balance is more than 20000,So you won");
            winner();
        }
        return 0;
    }

    public static int stockMarket(int p) {

        System.out.println(" You have arrived at the Stock Market ");
        System.out.println(" Here you can buy stocks of the companies already owned by other players.");
        int a = 0;

        if (company.getCompanyOwner(2) != 0) {
            a++;
            System.out.println("Do you want to buy shares of Amazon (yes/no)");
            Scanner sc = new Scanner(System.in);
            String response = sc.next();
            if (response.equalsIgnoreCase("yes")) {
                int owner = company.getCompanyOwner(2);
                int sharesForsale = player.getPlayerSharesOwned(owner, 2) - 10;

                System.out.println(" How many shares of Amazon do you want to buy out of " + sharesForsale);
                int buyShare = sc.nextInt();
                if (buyShare > sharesForsale) {
                    System.out.println(buyShare + " shares are not available of Amazon for Sale");

                } else {
                    float sharePrice = buyShare * company.getCompanySharePrice(2);
                    if (player.getPlayerCurrentBal(p) > sharePrice) {
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - sharePrice, p);
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(2) + sharePrice, owner);
                        player.setPlayerSharesOwned(owner, 2, player.getPlayerSharesOwned(owner, 2) - buyShare);
                        player.setPlayerSharesOwned(p, 2, player.getPlayerSharesOwned(p, 2) + buyShare);
                    } else {
                        System.out.println(" You don't have enough balance to buy " + buyShare + " shares of Amazon");
                    }

                }

            }
        }

        if (company.getCompanyOwner(5) != 0) {
            a++;
            System.out.println(" Do you want to buy shares of Tesla (yes/no)");
            Scanner sc = new Scanner(System.in);
            String response = sc.next();
            if (response.equalsIgnoreCase("yes")) {
                int owner = company.getCompanyOwner(5);
                int sharesForsale = player.getPlayerSharesOwned(owner, 2) - 23;
                System.out.println(sharesForsale);

                System.out.println("How many shares of Tesla do you want to buy out of " + sharesForsale);
                int buyShare = sc.nextInt();
                if (buyShare > sharesForsale) {
                    System.out.println(buyShare + " shares are not available of Tesla for Sale");

                } else {
                    float sharePrice = buyShare * company.getCompanySharePrice(5);
                    if (player.getPlayerCurrentBal(p) > sharePrice) {
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - sharePrice, p);
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(2) + sharePrice, owner);
                        player.setPlayerSharesOwned(owner, 5, player.getPlayerSharesOwned(owner, 5) - buyShare);
                        player.setPlayerSharesOwned(p, 5, player.getPlayerSharesOwned(p, 5) + buyShare);
                    } else {
                        System.out.println("You don't have enough balance to buy " + buyShare + " shares of Tesla");
                    }

                }

            }
        }

        if (company.getCompanyOwner(6) != 0) {
            a++;
            System.out.println("Do you want to buy shares of Microsoft (yes/no)");
            Scanner sc = new Scanner(System.in);
            String response = sc.next();
            if (response.equalsIgnoreCase("yes")) {
                int owner = company.getCompanyOwner(6);
                int sharesForsale = player.getPlayerSharesOwned(owner, 2) - 15;

                System.out.println("How many shares of Microsoft do you want to buy out of " + sharesForsale);
                int buyShare = sc.nextInt();
                if (buyShare > sharesForsale) {
                    System.out.println(buyShare + " shares are not available of Microsoft for Sale");

                } else {
                    float sharePrice = buyShare * company.getCompanySharePrice(6);
                    if (player.getPlayerCurrentBal(p) > sharePrice) {
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - sharePrice, p);
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(6) + sharePrice, owner);
                        player.setPlayerSharesOwned(owner, 2, player.getPlayerSharesOwned(owner, 6) - buyShare);
                        player.setPlayerSharesOwned(p, 6, player.getPlayerSharesOwned(p, 6) + buyShare);
                    } else {
                        System.out
                                .println("You don't have enough balance to buy " + buyShare + " shares of Microsoft ");
                    }

                }

            }
        }

        if (company.getCompanyOwner(9) != 0) {
            a++;
            System.out.println("Do you want to buy shares of Google (yes/no)");
            Scanner sc = new Scanner(System.in);
            String response = sc.next();
            if (response.equalsIgnoreCase("yes")) {
                int owner = company.getCompanyOwner(9);
                int sharesForsale = player.getPlayerSharesOwned(owner, 2) - 4;

                System.out.println("How many shares of Google do you want to buy out of " + sharesForsale);
                int buyShare = sc.nextInt();
                if (buyShare > sharesForsale) {
                    System.out.println(buyShare + " shares are not available of Google for Sale");

                } else {
                    float sharePrice = buyShare * company.getCompanySharePrice(2);
                    if (player.getPlayerCurrentBal(p) > sharePrice) {
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - sharePrice, p);
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(9) + sharePrice, owner);
                        player.setPlayerSharesOwned(owner, 9, player.getPlayerSharesOwned(owner, 9) - buyShare);
                        player.setPlayerSharesOwned(p, 9, player.getPlayerSharesOwned(p, 9) + buyShare);
                    } else {
                        System.out.println("You don't have enough balance to buy " + buyShare + " shares of Google");
                    }

                }

            }
        }
        if (company.getCompanyOwner(10) != 0) {
            a++;
            System.out.println("Do you want to buy shares of ISRO (yes/no)");
            Scanner sc = new Scanner(System.in);
            String response = sc.next();
            if (response.equalsIgnoreCase("yes")) {
                int owner = company.getCompanyOwner(10);
                int sharesForsale = player.getPlayerSharesOwned(owner, 2) - 5;

                System.out.println(" How many shares of ISRO do you want to buy out of " + sharesForsale);
                int buyShare = sc.nextInt();
                if (buyShare > sharesForsale) {
                    System.out.println(buyShare + " shares are not available of ISRO for Sale");

                } else {
                    float sharePrice = buyShare * company.getCompanySharePrice(2);
                    if (player.getPlayerCurrentBal(p) > sharePrice) {
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - sharePrice, p);
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(10) + sharePrice, owner);
                        player.setPlayerSharesOwned(owner, 2, player.getPlayerSharesOwned(owner, 10) - buyShare);
                        player.setPlayerSharesOwned(p, 2, player.getPlayerSharesOwned(p, 10) + buyShare);
                    } else {
                        System.out.println(" You don't have enough balance to buy " + buyShare + " shares of ISRO");
                    }

                }

            }
        }
        if (company.getCompanyOwner(11) != 0) {
            a++;
            System.out.println(" Do you want to buy shares of Facebook (yes/no)");
            Scanner sc = new Scanner(System.in);
            String response = sc.next();
            if (response.equalsIgnoreCase("yes")) {
                int owner = company.getCompanyOwner(11);
                int sharesForsale = player.getPlayerSharesOwned(owner, 2) - 15;

                System.out.println(" How many shares of Facebook do you want to buy out of " + sharesForsale);
                int buyShare = sc.nextInt();
                if (buyShare > sharesForsale) {
                    System.out.println(buyShare + " Shares are not available of Facebook for Sale");

                } else {
                    float sharePrice = buyShare * company.getCompanySharePrice(11);
                    if (player.getPlayerCurrentBal(p) > sharePrice) {
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(p) - sharePrice, p);
                        player.setPlayerCurrentBal(player.getPlayerCurrentBal(11) + sharePrice, owner);
                        player.setPlayerSharesOwned(owner, 11, player.getPlayerSharesOwned(owner, 11) - buyShare);
                        player.setPlayerSharesOwned(p, 11, player.getPlayerSharesOwned(p, 11) + buyShare);
                    } else {
                        System.out.println(" You don't have enough balance to buy " + buyShare + " shares of Facebook");
                    }

                }

            }
        } 
        if(a==0) {
            System.out.println(" No companies are sold yet.");
        }

        return 0;
    }
}