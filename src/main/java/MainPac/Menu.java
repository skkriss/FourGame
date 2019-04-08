package MainPac;

import java.util.Scanner;

public class Menu
{
    public static void main( String[] args )
    {
        int n = 0;
        Scanner inputSwitch = new Scanner(System.in);

        while (n != 4) {
            System.out.println("\nWybierz:\n"+
                    "1-Zagraj!\n"+
                    "2-Lista graczy!\n"+
                    "3-Lista najlepszych z najlepszych!\n"+
                    "4-Juz nas opuszczasz?\n");


            n = inputSwitch.nextInt();
            switch (n)
            {

                case 1:
                {
                    System.out.println("Witaj w grze ");
                    Scanner input = new Scanner(System.in);

                    int gracz = 1;
                    int column;
                    String message;
                    int row,col;
                    Gra gra = null;

                    System.out.println("Podaj ilosc kolumn");
                    col = input.nextInt();
                    System.out.println("Podaj ilosc rzedow");
                    row = input.nextInt();

                    try
                    {
                        gra = new Gra(row, col,true);
                    }

                    catch(IllegalArgumentException e)
                    {
                        System.out.println("Error "+e.getMessage());
                        break;
                    }

                    while (gra.win == 0)
                    {
                        System.out.println(gra.buildStringBoard() + "\n\nRusza gracz nr : " + gracz + "\nPodaj kolumnę");

                        column = input.nextInt();

                        message = gra.moveGracz(gracz, column);
                        System.out.println(message);

                        if (gra.moveOK == false)
                        {
                            System.out.println("Powtórz ruch");
                        }

                        else
                        {
                            if (gracz == 1)
                            {
                                gracz = 2;
                            }
                            else gracz = 1;
                        }

                    }

                    System.out.println(gra.buildStringBoard());
                    break;
                }

                case 2:
                    {
                    Gra gra = new Gra(6,7,true);
                    gra.gracze = gra.readListGracze(null);
                    gra.printListGracze();
                    break;
                }

                case 3:
                    {
                    Gra gra = new Gra(6,7,true);
                    gra.gracze = gra.readListGracze(null);
                    gra.sortedList();
                    gra.printListGracze();
                    break;
                }
            }
        }
    }
}
