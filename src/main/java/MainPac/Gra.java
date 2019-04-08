package MainPac;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Gra
{
    int board[][];
    int lastRun;
    int rows;
    int columns;
    int win ;
    int countAddedTokens;
    boolean moveOK = false;

    ArrayList<Gracz> gracze ;
    Gracz gracz1,gracz2;
    int graczeSet = 0;
    String fileName = null;


    public Gra(int row,int column,boolean initListGracze)
    {

        if(row < 4 || column < 4)
        {
            throw new IllegalArgumentException("Liczba kolumn i rzedow musi byc wieksza niz 4!");
        }
        rows = row;
        columns = column;
        board = new int[rows][columns];

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++)
            {
                board[i][j] = 0;
            }
        }
        if(initListGracze)
            gracze = new ArrayList<Gracz>();
        lastRun = -1;
        win = 0;
        countAddedTokens = 0;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //Warunki zwyciestwa ( 4 w pionie, poziomie, po skosie "/" i po skosie "\" )
    /////////////////////////////////////////////////////////////////////////////////////////////
    //Zliczenia beda odbywaly sie na podstawie pozycji rzedow i kolumn ( pozycji na planszy )

    public boolean checkWinVertical(int player,int row,int column)
    {
        int count = 0;

        for(int i=row;i<rows;i++)
        {
            if( board[i][column] == player )
            {
                count ++;
            }
            else
            {
                i=rows;
            }
            if(count == 4) i=rows;
        }
        if( count == 4 )
        {
            return true;
        }

        return false;
    }

    public boolean checkWinHorizontal(int player,int row, int column)
    {
        int count = 0;
        for(int i=column; i<columns; i++)//Sprawdzanie w prawo
        {
            if(board[row][i] == player)
            {
                count++ ;

            }else
            {
                i = columns;
            }
            if(count == 4) i=columns;
        }
        if(count == 4)
        {
            return true;
        }

        else
        {
            for(int i = column-1 ;i>=0;i--)//Sprawdzanie w lewo
            {
                if(board[row][i] == player)
                {
                    count++;
                }
                else
                {
                    i = -1;
                }
                if(count == 4)
                {
                    i = -1;
                    return true;
                }
            }

        }
        return false;
    }

    public boolean checkWinDiagonallyAsc(int player,int row,int column){

        int count = 0;
        int i ;
        int j=column;
        for(i = row;i<rows && j>=0;i++,j--) //Asc, "/"
        {
            if(board[i][j] == player)
            {
                count++;

            }
            else
            {
                i = rows;
            }
            if(count == 4) i = rows;
        }
        if(count == 4)
        {
            return true;
        }
        else
        {
            i = row-1;
            j = column+1;
            for( ;i>=0 && j<columns;i--,j++)
            {
                if(board[i][j] == player)
                {
                    count++;
                }
                else
                {
                    i =-1;
                }
                if(count == 4)
                {
                    i = -1;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinDiagonallyDesc(int player,int row,int column) {
        int count = 0;
        int i ;
        int j=column;
        for(i = row;i<rows && j<columns;i++,j++) //Desc, "\"
        {
            if(board[i][j] == player)
            {
                count++;

            }
            else
            {
                i = rows;
            }
            if(count == 4) i = rows;
        }
        if(count == 4)
        {
            return true;
        }
        else
        {
            i = row-1;
            j = column-1;
            for(; i>=0 && j>=0; i--,j--)
            {
                if(board[i][j] == player)
                {
                    count++;
                }
                else
                {
                    i =-1;
                }
                if(count == 4)
                {
                    i = -1;
                    return  true;
                }
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public int getPositionInColumn(int col)
    {

        for(int i=rows-1;i>=0;i--)
        {
            if( board[i][col] == 0 )
            {
                return i;
            }
        }
        return -1; //Jezeli kolumna pelna
    }

    public String checkWin(int player, int row,int column)
    {
        if(checkWinVertical(player,row,column))
        {
            win = player;
            return "Gracz"+player+" Wygrywa! (vertical)";
        }
        if(checkWinHorizontal(player,row,column))
        {
            win = player;
            return "Gracz"+player+" Wygrywa! (horizontal)";
        }
        if(checkWinDiagonallyAsc(player,row,column))
        {
            win = player;
            return "Gracz"+player+" Wygrywa! (diagonallyAsc)";
        }
        if(checkWinDiagonallyDesc(player,row,column))
        {
            win = player;
            return "Gracz"+player+" Wygrywa! (diagonallyDesc)";
        }
        return "Nikt nie wygral :(";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    //Budowa planszy

    public String buildStringBoard()
    {
        String message = "  ";
        for(int z=0;z<columns;z++) message += " "+z+" ";
        message += "\n";
        for(int i=0;i<rows;i++)
        {
            message += i +" ";
            for (int j = 0; j < columns; j++)
            {
                if(board[i][j] == 0) message += "| |";
                if(board[i][j] == 1) message += "|R|";
                if(board[i][j] == 2) message += "|G|";
            }
            message += "\n";
        }
        message += "Gracz1: R, Gracz2: G";
        return message;
    }

    public int addToken(int player,int col) //Zapisanie zetonu na dole kolumny gracza
    {
        int row = getPositionInColumn(col);
        board[row][col] = player;
        return row;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    public String moveGracz(int player,int col)
    {
        if(col < 0 || col > columns -1)
        {
            moveOK = false; return "Niewlasciwy indeks!";
        }

        if( win == 0 && lastRun != player && board[0][col] == 0 && countAddedTokens < (rows*columns) )
        {
            int row = addToken(player,col);
            countAddedTokens++;
            moveOK = true;
            lastRun = player;
            String message = checkWin(player,row,col);

            if(countAddedTokens >= (rows*columns) && win == 0) //Sprawdzamy czy remis
            {
                win = 3;
                if(graczeSet == 2)
                {
                    increasStatistic();
                    writeListGracze(fileName);
                }
                return "Plansza jest pelna! Remis!";
            }

            if(graczeSet == 2)
            {
                increasStatistic();
                writeListGracze(fileName);
            }
            return message;
        }
        else
        {
            moveOK = false;
            if(win != 0) return "Koniec gry.";
            if(lastRun == player) return  "Twoja kolej.";
            if(countAddedTokens >= (rows*columns)) { win = 3; return "Plansza pelna! Remis!"; }
            if(board[0][col] != 0) return "Kolumna pelna.";
        }
        return null;
    }

    public Gracz serachGracz(String nick,String password)
    {
        for(Gracz player : gracze)
        {
            if(player.getKsywa().equals(nick) && player.getHaslo().equals(password) )
            {
                return player;
            }
        }
        return null;
    }

    public String printListGracze()
    {
        String list= "";
        for (Gracz player : gracze)
        {
            System.out.println(player);
            list+=player+"\n";
        }
        return list;
    }

    public void sortedList()
    {
        Collections.sort(gracze);
    }

    public String graczeInit(int player,String nick,String pass,boolean exist)
    {
        if( gracze == null )
        {
            return "Lista graczy pusta!";
        }

        if(exist == true)
        {
            Gracz playerSerach = serachGracz(nick, pass);

            if (playerSerach != null)
            {
                if(player == 1)
                {
                    gracz1 = playerSerach;
                }
                else {
                    gracz2 = playerSerach;
                }
                graczeSet++;
                return "Gracz"+player+" nie znaleziony";
            }
            else
            {
                return "Gracz nie znaleziony!";
            }
        }
        else
        {
            if(serachGracz(nick,pass) != null)
            {
                return "Gracz juz istnieje!";
            }
            else
            {
                Gracz newPlayer = new Gracz(nick, pass, "0", "0", "0");
                gracze.add(newPlayer);
                if (player == 1)
                {
                    gracz1 = newPlayer;
                }
                else
                {
                    gracz2 = newPlayer;
                }
                graczeSet++;
                return "Gracz"+player+" stworzony";
            }
        }
    }

    public void increasStatistic(){

        if(win != 0){
            if(win == 1)
            {
                gracz1.setWygrana(); gracz2.setPrzegrana();
            }
            if(win == 2)
            {
                gracz2.setWygrana(); gracz1.setPrzegrana();
            }
            if(win == 3)
            {
                gracz2.setRemis(); gracz1.setRemis();
            }

        }
    }

    public ArrayList<Gracz> readListGracze(String fileNameIns)
    {
        ArrayList<Gracz> gracze = new ArrayList<Gracz>();
        File file ;
        if(fileNameIns != null)
        {
            file = new File("src/main/resources/" + fileNameIns);
            fileName = fileNameIns;
        }
        else
            file = new File("src/main/resources/gracze.csv");
        Scanner read = null;
        try
        {
            read = new Scanner(file);
        } catch (FileNotFoundException e)
        {
        }
        //StringTokenizer token;
        while(read.hasNext())
        {
            String[] elements = read.next().split(",");
            gracze.add(new Gracz(elements[0],elements[1],elements[2],elements[3],elements[4]));
        }
        read.close();

        return gracze;
    }

    public void writeListGracze(String fileName)
    {

        File file;
        if(fileName != null)
            file = new File("src/main/resources/"+fileName);
        else
            file = new File("src/main/resources/gracze.csv");
        BufferedWriter save = null;

        try {
            save = new BufferedWriter(new FileWriter(file));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        for (Gracz player : gracze)
        {
            try
            {
                save.write(player.getKsywa()+","+player.getHaslo()+","+player.getWygrana()+","+player.getPrzegrana()+","+player.getRemis()+"\n");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            save.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
