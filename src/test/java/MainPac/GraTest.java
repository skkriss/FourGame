package MainPac;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraTest 
{
    private Gra gra;
    private int rows,columns;

    @BeforeEach
    void init()
    {
        rows = 6;
        columns = 7;
        gra = new Gra(rows,columns,true);
        gra.gracze.add(new Gracz("Aaaa","pass","1","3","1"));
        gra.gracze.add(new Gracz("Bbbb","haslo","1","1","1"));
    }

        @Test
        void checkInitGameValueInBoardTest()
        {
            assertEquals(0, gra.board[0][0]);
        }
        @Test
        void checkInitGameWinTest()
        {
            assertEquals(0,gra.win);
        }
        @Test
        void checkInitGameLastRunTest()
        {
            assertEquals(-1,gra.lastRun);
        }
        @Test
        void checkInitGameRowsTest()
        {
            assertEquals(rows,gra.rows);
        }
        @Test
        void checkInitGameColumnsTest()
        {
            assertEquals(columns,gra.columns);
        }
        @Test
        void checkInitGameCountAddedTokensTest()
        {
            assertEquals(0,gra.countAddedTokens);
        }
        @Test
        void checkInitGameWrongNumbOfRowsTest()
        {
            assertThrows(IllegalArgumentException.class, () ->
            {
                gra = new Gra(3,6,false);
            },"Should be exceptions IllegalArgument for row < 4");
        }
        @Test
        void checkInitGameWrongNumbOfColumnsTest(){
            assertThrows(IllegalArgumentException.class, () ->
            {
                gra = new Gra(6,3,false);
            },"Should be exceptions IllegalArgument for row < 4");
        }

        /////////////////////////////////////////////////////////////////////
        //Testy przeprowadzane na planszy 6x7

        @Test
        void checkWinVerticalPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][1] =
                    gra.board[4][2] = gra.board[3][1] = gra.board[2][1] = gra.board[1][1] = 1;
            gra.board[5][1] = gra.board[5][2] = gra.board[5][3] = gra.board[3][0] = gra.board[3][2] = 2;
            boolean actual = gra.checkWinVertical(1,1,1);
            assertEquals(true,actual);
        }

        @Test
        void checkWinVerticalPositiveCheckReturn6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][1] =
                    gra.board[4][2] = gra.board[3][1] = gra.board[2][1] = gra.board[1][1] = 1;
            gra.board[5][1] = gra.board[5][2] = gra.board[5][3] = gra.board[3][0] = gra.board[3][2] = 2;
            boolean actual = gra.checkWinVertical(1,1,1);
            assertTrue(actual);
        }

        @Test
        void checkWinVerticalPositiveLeftDownCorner6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][6] = gra.board[4][6] = gra.board[3][6] =
                    gra.board[2][6] = gra.board[3][5] = 2;
            gra.board[5][3] = gra.board[5][4] = gra.board[5][5] = gra.board[4][5]  = 1;
            boolean actual = gra.checkWinVertical(2,2,6);
            assertEquals(true,actual);
        }

        @Test
            //@Disabled("Disabled while function not implemented")
        void checkWinVerticalNonPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][1] =
                    gra.board[4][2] = gra.board[3][1] = gra.board[2][1] = 1;
            gra.board[5][1] = gra.board[5][2] = gra.board[5][3] = gra.board[3][0] = gra.board[3][2] = 2;
            boolean actual = gra.checkWinVertical(1,2,1);
            assertFalse(actual);
        }

        @Test
        void checkInWinHorizontalPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][2]= gra.board[4][1] = gra.board[4][3] =
                    gra.board[5][3] = 1;
            gra.board[3][0] = gra.board[3][1] = gra.board[5][1] = gra.board[5][2] = gra.board[3][3] = 2;
            boolean actual = gra.checkWinHorizontal(1,4,2);
            assertEquals(true,actual);
        }

        @Test
        void checkInWinHorizontalNonPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][2]= gra.board[4][1] = gra.board[4][3] =
                    gra.board[5][3] = gra.board[4][4] = gra.board[4][5] = gra.board[4][6] = 1;
            gra.board[3][0] = gra.board[3][1] = gra.board[5][1] = gra.board[5][2] = gra.board[3][3] = 2;
            boolean actual = gra.checkWinHorizontal(1,4,3);
            assertEquals(true,actual);
        }

        @Test
        void checkWinHorizontalReturnTrue6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][6] = gra.board[4][6] = gra.board[4][4] =
                    gra.board[3][6] = gra.board[3][5] = 1;
            gra.board[5][3] = gra.board[5][4] = gra.board[5][5] = gra.board[4][5] = gra.board[5][2]  = 2;
            boolean actual = gra.checkWinHorizontal(2,5,2);
            assertTrue(actual);
        }

        @Test
        void checkWinHorizontalReturnFalse6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][6] = gra.board[4][6] = gra.board[4][4] =
                    gra.board[2][6] = gra.board[3][5] = gra.board[4][3] = 2;
            gra.board[5][3] = gra.board[5][4] = gra.board[5][5] = gra.board[4][5]  = 1;
            boolean actual = gra.checkWinHorizontal(2,5,3);
            assertFalse(actual);
        }

        @Test
        void checkWinDiagonallyAscPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][2]= gra.board[4][1] = gra.board[4][3] =
                    gra.board[5][3] = gra.board[3][2] = gra.board[2][3] = 1;
            gra.board[3][0] = gra.board[3][1] = gra.board[5][1] = gra.board[5][2] = gra.board[3][3] = 2;
            boolean actual = gra.checkWinDiagonallyAsc(1,3,2);
            assertEquals(true,actual);
        }

        @Test
        void checkWinDiagonallyAscNonPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][2]= gra.board[4][1] = gra.board[4][3] =
                    gra.board[5][3] = gra.board[3][2] = 1;
            gra.board[3][0] = gra.board[3][1] = gra.board[5][1] = gra.board[5][2] = gra.board[3][3] = 2;
            boolean actual = gra.checkWinDiagonallyAsc(1,3,2);
            assertNotSame(true,actual);
        }

        @Test
        void checkWinDiagonallyAscReturnTrue6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][3] = gra.board[4][3]= gra.board[4][4] = gra.board[3][5] =
                    gra.board[3][6] = gra.board[4][6] = gra.board[2][6] = 1;
            gra.board[5][2] = gra.board[5][4] = gra.board[5][5] = gra.board[5][6] = gra.board[4][5] =
                    gra.board[3][3] = gra.board[3][4] = 2;
            boolean actual = gra.checkWinDiagonallyAsc(1,3,5);
            assertTrue(actual);
        }

        @Test
        void checkWinDiagonallyAscReturnFalse6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][3] = gra.board[4][3]= gra.board[4][4] = gra.board[3][5] =
                    gra.board[3][6] = gra.board[4][6] = 1;
            gra.board[5][2] = gra.board[5][4] = gra.board[5][5] = gra.board[5][6] = gra.board[4][5] =
                    gra.board[3][3] = gra.board[3][4] = 2;
            boolean actual = gra.checkWinDiagonallyAsc(1,3,5);
            assertFalse(actual);
        }

        @Test
        void checkWinDiagonallyDescPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[5][2] = gra.board[3][2]=
                    gra.board[4][3]= gra.board[3][3] = gra.board[5][4] = gra.board[4][4] = 1;
            gra.board[3][0] = gra.board[5][1] = gra.board[4][1] = gra.board[3][1] = gra.board[2][1] =
                    gra.board[4][2] = gra.board[2][0] = gra.board[5][3] = 2;
            boolean actual = gra.checkWinDiagonallyDesc(2,2,0);
            assertEquals(true,actual);

        }

        @Test
        void checkWinDiagonallyDescNonPositive6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][0] = gra.board[4][0] = gra.board[5][2] = gra.board[3][2] = gra.board[4][1] =
                    gra.board[4][3] = gra.board[5][4] = gra.board[4][4] = gra.board[5][3]= 1;
            gra.board[3][0] = gra.board[5][1]  = gra.board[3][4] = gra.board[3][1] = gra.board[2][1] =
                    gra.board[4][2] = gra.board[2][0]  = 2;
            boolean actual = gra.checkWinDiagonallyDesc(2,2,0);
            assertNotEquals(true,actual);

        }


        @Test
            //@Disabled("Disabled while function not implemented")
        void checkWinDiagonallyDescReturnTrue6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][3] = gra.board[4][2] = gra.board[4][3]= gra.board[4][4] = gra.board[3][5] =
                    gra.board[3][6] = gra.board[4][6] = 1;
            gra.board[5][2] = gra.board[5][4] = gra.board[5][5] = gra.board[5][6] = gra.board[4][5] =
                    gra.board[3][3] = gra.board[3][4] =gra.board[2][3]=gra.board[2][6]= 2;
            boolean actual = gra.checkWinDiagonallyDesc(2,2,3);
            assertTrue(actual);
        }

        @Test
            //@Disabled("Disabled while function not implemented")
        void checkWinDiagonallyDescReturnFalse6x7Test()
        {
            gra = new Gra(6,7,true);
            gra.board[5][3] = gra.board[4][2] = gra.board[4][3]= gra.board[4][4] = gra.board[3][5] =
                    gra.board[3][6] = gra.board[4][6] = 1;
            gra.board[5][2] = gra.board[5][4] = gra.board[5][5] = gra.board[5][6] = gra.board[4][5] =
                    gra.board[3][3] = gra.board[3][4] =gra.board[2][6]= 2;
            boolean actual = gra.checkWinDiagonallyAsc(1,3,4);
            assertFalse(actual);
        }

        /////////////////////////////////////////////////////////////////////
        //Testy dla dowolnego rozmiaru planszy

        @Test
        void checkgetPositioInColumn_EmptyColumntTest()
        {
            int actual = gra.getPositionInColumn(0);
            assertEquals(rows-1,actual);
        }

        @Test
        void checkgetPositioInColumn_NotEmptyColumntTest()
        {
            gra.board[rows-1][0] = 1; gra.board[rows-2][0] = 2 ;
            int actual = gra.getPositionInColumn(0);
            assertEquals(rows-3,actual);
        }

        @Test
        void checkgetPositioInColumn_NotEmptyColumnt1Test()
        {
            gra.board[rows-1][0] = 1;
            int actual = gra.getPositionInColumn(0);
            assertNotEquals(rows-1,actual);
        }

        @Test
        void checkgetPositioInColumn_FullColumntTest()
        {
            for(int i = rows-1;i>=0;i--)
            {
                gra.board[i][0] = 1;
                gra.board[i][0] = 2;
            }
            int actual = gra.getPositionInColumn(0);
            assertEquals(-1,actual);
        }

        @Test
        void checkgetPositioInColumn_FullColumn1Test()
        {
            for(int i = rows-1;i>=0;i--)
            {
                gra.board[i][0] = 1;
                gra.board[i][0] = 2;
            }
            int actual = gra.getPositionInColumn(0);
            assertNotEquals(0,actual);
        }

        /////////////////////////////////////////////////////////////////////

        @Test
        void checkWinMethod_WinVertical_Test()
        {
            for(int i = rows-1;i>=rows-4;i--)
            {
                gra.board[i][0] = 1;
            }
            String actual = gra.checkWin(1,rows-4,0);
            String expected = "Gracz1 Wygrywa! (vertical)";
            assertEquals(expected,actual);
        }

        @Test
        void checkWinMethod_WinVerticalValueWin_Test()
        {
            for(int i = rows-1;i>=rows-4;i--)
            {
                gra.board[i][0] = 1;
            }
            gra.checkWin(1,rows-4,0);
            assertEquals(1,gra.win);
        }

        @Test
        void checkWinMethod_WinHorizontal_Test()
        {
            for(int i = columns-1;i>=columns-4;i--)
            {
                gra.board[0][i] = 1;
            }
            String actual = gra.checkWin(1,0,columns-3);
            String expected = "Gracz1 Wygrywa! (horizontal)";
            assertEquals(expected,actual);
        }

        @Test
        void checkWinMethod_WinHorizontalValueWin_Test()
        {
            for(int i = columns-1;i>=columns-4;i--)
            {
                gra.board[0][i] = 1;
            }
            gra.checkWin(1,0,columns-3);
            assertEquals(1,gra.win);
        }

        @Test
        void checkWinMethod_WinDiagonallyAsc_Test()
        {
            int col = 0;
            for(int i = rows-1;i>=rows-4;i--,col++)
            {
                gra.board[i][col] = 1;
            }
            String actual = gra.checkWin(1,rows-2,1);
            String expected = "Gracz1 Wygrywa! (diagonallyAsc)";
            assertEquals(expected,actual);
        }

        @Test
        void checkWinMethod_WinDiagonallyAscValueWin_Test()
        {
            int col = 0;
            for(int i = rows-1;i>=rows-4;i--,col++)
            {
                gra.board[i][col] = 1;
            }
            gra.checkWin(1,rows-2,1);
            assertEquals(1,gra.win);
        }

        @Test
        void checkWinMethod_WinDiagonallyDesc_Test()
        {
            int col = columns-1;
            for(int i = rows-1;i>=rows-4;i--,col--)
            {
                gra.board[i][col] = 2;
            }
            gra.checkWin(2,rows-3,columns-3);
            assertEquals(2,gra.win);
        }

        @Test
        void checkWinMethod_WinDiagonallyDescValueWin_Test()
        {
            int col = columns-1;
            for(int i = rows-1;i>=rows-4;i--,col--)
            {
                gra.board[i][col] = 2;
            }
            gra.checkWin(2,rows-3,columns-3);
            assertEquals(2,gra.win);
        }

        @Test
        void checkWinMethod_NobodyWin_Test()
        {
            int col = columns-1;
            for(int i = rows-1;i>=rows-3;i--,col--)
            {
                gra.board[i][col] = 2;
            }
            String actual = gra.checkWin(2,rows-1,columns-1);
            String expected = "Nikt nie wygral :(";
            assertEquals(expected,actual);
        }

        @Test
        void checkWinMethod_NobodyWinValueWin_Test()
        {
            int col = columns-1;
            for(int i = rows-1;i>=rows-3;i--,col--)
            {
                gra.board[i][col] = 2;
            }
            gra.checkWin(2,rows-1,columns-1);
            assertNotEquals(2,gra.win);
        }

        /////////////////////////////////////////////////////////////////////

        @Test
        void BuildStringOfBoard6x7Test()
        {
            gra = new Gra(6,7,false);
            gra.board[5][0] = gra.board[4][0] = gra.board[4][1] =
                    gra.board[4][2] = gra.board[3][1] = gra.board[2][1] = gra.board[1][1] = 1;
            gra.board[5][1] = gra.board[5][2] = gra.board[5][3] = gra.board[3][0] = gra.board[3][2] = 2;
            String expected = "   0  1  2  3  4  5  6 \n" + "0 | || || || || || || |\n"+ "1 | ||R|| || || || || |\n"+
                    "2 | ||R|| || || || || |\n"+ "3 |G||R||G|| || || || |\n"+ "4 |R||R||R|| || || || |\n"+
                    "5 |R||G||G||G|| || || |\n"+ "Gracz1: R, Gracz2: G";
            assertEquals(expected,gra.buildStringBoard());
        }

        @Test
        void BuildStringOfBoard6x7Empty()
        {
            gra = new Gra(6,7,false);
            String expected = "   0  1  2  3  4  5  6 \n" + "0 | || || || || || || |\n"+ "1 | || || || || || || |\n"+
                    "2 | || || || || || || |\n"+ "3 | || || || || || || |\n"+ "4 | || || || || || || |\n"+
                    "5 | || || || || || || |\n"+ "Gracz1: R, Gracz2: G";

            assertEquals(expected,gra.buildStringBoard());

        }

        @Test
        void BuildStringOfBoard8x10Empty()
        {
            gra = new Gra(8,10,false);
            String expected = "   0  1  2  3  4  5  6  7  8  9 \n" + "0 | || || || || || || || || || |\n"+ "1 | || || || || || || || || || |\n"+
                    "2 | || || || || || || || || || |\n"+ "3 | || || || || || || || || || |\n"+ "4 | || || || || || || || || || |\n"+
                    "5 | || || || || || || || || || |\n"+ "6 | || || || || || || || || || |\n"+  "7 | || || || || || || || || || |\n"+"Gracz1: R, Gracz2: G";
            assertEquals(expected,gra.buildStringBoard());
        }

        @Test
        void addToken6x7CheckBoradTest()
        {
            gra = new Gra(6,7,false);
            int[] p1 = { 0, 1, 0 ,1, 1 ,1};
            int[] p2 = { 0, 1, 0, 0, 0, 2};
            for(int i = 0;i<6;i++)
            {
                gra.addToken(1,p1[i]);
                gra.addToken(2,p2[i]);
            }
            int[][] expected = {{2,0,0,0,0,0,0},{2,1,0,0,0,0,0},{2,1,0,0,0,0,0},{1,1,0,0,0,0,0},{2,2,0,0,0,0,0},{1,1,2,0,0,0,0}};
            assertArrayEquals(expected,gra.board);
        }

        @Test
        void addTokenAnySizeTest()
        {
            for(int i = 0;i<rows/2;i++)
            {
                gra.addToken(1,0);
                gra.addToken(2,0);
            }
            int[][] expected = new int[rows][columns];
            int count = 1;
            for(int i = rows-1;i>=0;i--)
            {
                for(int j = columns-1;j>=0;j--)
                {
                    if( j == 0 )
                    {
                        if(count%2 != 0) expected[i][j] = 1;
                        else expected[i][j] = 2;
                        count++;
                    }
                    else
                    {
                        expected[i][j] = 0;
                    }
                }
            }
            if(rows%2 == 1) expected[0][0] = 0;
            assertArrayEquals(expected,gra.board);
        }

        @Test
        void AddTokenCheckReturnValueTest()
        {
            for(int i = 0;i<2;i++)
            {
                gra.addToken(1,0);
            }
            int actual = gra.addToken(1,0);
            assertEquals(rows-3,actual);
        }

        /////////////////////////////////////////////////////////////////////

        @Test
        void moveGraczGiveWrongColumnToLowerTest()
        {
            String actual = gra.moveGracz(1,-1);
            String expected = "Niewlasciwy indeks!";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczGiveWrongColumnToHighTest()
        {
            String actual = gra.moveGracz(1,columns);
            String expected = "Niewlasciwy indeks!";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczSetWinTest()
        {
            gra.win = 1;
            String actual = gra.moveGracz(2,0);
            String expected = "Koniec gry.";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczWrongPlayerTest()
        {
            gra.moveGracz(1,0);
            String actual = gra.moveGracz(1,0);
            String expected = "Twoja kolej.";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczRemisTest()
        {
            gra.countAddedTokens = rows*columns;
            String actual = gra.moveGracz(1,0);
            String expected = "Plansza pelna! Remis!";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczColumnFullTest()
        {
            for(int i = rows-1;i>=0;i--)
            {
                gra.board[i][0] = 1;
            }
            String actual = gra.moveGracz(1,0);
            String expected = "Kolumna pelna.";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczAddTokenCheckFieldsTest()
        {
            gra.moveGracz(1,0);
            assertEquals(1,gra.countAddedTokens,"CountAddedTokens should be 1");
            assertEquals(true,gra.moveOK,"moveOK should be true");
            assertEquals(1,gra.lastRun,"lastRun should be 1");
            assertEquals(0,gra.win,"win should be 0");
        }

        @Test
        void moveGraczChceckInMessageNotNull()
        {
            String actual = gra.moveGracz(1,0);
            assertNotNull(actual);
        }

        @Test
        void moveGraczSimulateWinGracz1Test()
        {
            for(int i = rows-1;i>=rows-3;i--)
            {
                gra.moveGracz(1,0);
                gra.moveGracz(2,2);
            }
            String actual = gra.moveGracz(1,0);
            String expected = "Gracz1 Wygrywa! (vertical)";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczSimulateRemis4x4Test()
        {
            gra = new Gra(4,4,false);
            int[] p1 = { 2, 3, 0 ,1, 2 ,3, 0, 1};
            int[] p2 = { 0, 1, 2, 3, 0, 1, 2, 1};
            for(int i = 0;i<8;i++){
                gra.moveGracz(1,p1[i]);
                gra.moveGracz(2,p2[i]);
            }
            String actual = gra.moveGracz(2,3);
            String expected = "Plansza jest pelna! Remis!";
            assertEquals(expected,actual);
        }

        /////////////////////////////////////////////////////////////////////

        @Test
        void printListPlayerTest()
        {
            String expected = "Ksywa: Aaaa, Wygrana: 1, Przegrana: 3, Remis: 1\n" +
                    "Ksywa: Bbbb, Wygrana: 1, Przegrana: 1, Remis: 1\n";
            String actual = gra.printListGracze();
            assertEquals(expected,actual);
        }

        @Test
        void printListPlayerAfterAddNewPlayerTest()
        {
            gra.gracze.add(new Gracz("Adam","1234","2","1","0"));
            String expected = "Ksywa: Aaaa, Wygrana: 1, Przegrana: 3, Remis: 1\n" +
                    "Ksywa: Bbbb, Wygrana: 1, Przegrana: 1, Remis: 1\n"+
                    "Ksywa: Adam, Wygrana: 2, Przegrana: 1, Remis: 0\n";
            String actual = gra.printListGracze();
            assertEquals(expected,actual);
        }

        @Test
        void sortedListTest()
        {
            gra.sortedList();
            String expected = "Ksywa: Bbbb, Wygrana: 1, Przegrana: 1, Remis: 1\n"+
                    "Ksywa: Aaaa, Wygrana: 1, Przegrana: 3, Remis: 1\n";
            String actual = gra.printListGracze();
            assertEquals(expected,actual);
        }

        @Test
        void sortedListAfterAddPlayerTest()
        {
            gra.gracze.add(new Gracz("Adam","1234","2","1","0"));
            gra.sortedList();
            String expected = "Ksywa: Adam, Wygrana: 2, Przegrana: 1, Remis: 0\n"+
                    "Ksywa: Bbbb, Wygrana: 1, Przegrana: 1, Remis: 1\n"+
                    "Ksywa: Aaaa, Wygrana: 1, Przegrana: 3, Remis: 1\n";
            String actual = gra.printListGracze();
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitListIsEmptyNotInicializeTest()
        {
            gra = new Gra(7,6,false);
            String actual = gra.graczeInit(1,"Mati","pass",true);
            String expected = "Lista graczy pusta!";
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitPlayerExistTest()
        {
            String actual = gra.graczeInit(1,"Aaaa","pass",true);
            String expected = "Gracz1 nie znaleziony";
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitPlayerExistButNotFoundTest()
        {
            String actual = gra.graczeInit(1,"Aaa","pass",true);
            String expected = "Gracz nie znaleziony!";
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitPlayerExistCheckPlayerOnGameTest()
        {
            gra.graczeInit(1,"Aaaa","pass",true);
            assertNotNull(gra.gracz1);
        }

        @Test
        void graczeInitGracz2ExistTest()
        {
            String actual = gra.graczeInit(2,"Aaaa","pass",true);
            String expected = "Gracz2 nie znaleziony";
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitNewPlayerTest()
        {
            String actual = gra.graczeInit(1,"Mati","haselko",false);
            String expected = "Gracz1 stworzony";
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitNewGracz2Test()
        {
            String actual = gra.graczeInit(2,"Mati","haselko",false);
            String expected = "Gracz2 stworzony";
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitNewPlayerButExistYetTest()
        {
            String actual = gra.graczeInit(2,"Aaaa","pass",false);
            String expected = "Gracz juz istnieje!";
            assertEquals(expected,actual);
        }

        @Test
        void graczeInitTwoPlayerAndCheckFieldPlayerSetTest()
        {
            gra.graczeInit(2,"Aaaa","pass",true);
            gra.graczeInit(1,"Mati","haselko",false);
            assertEquals(2,gra.graczeSet);
        }

        @Test
        void graczeInitTwoPlayerAndCompareObjectPlayerTest()
        {
            gra.graczeInit(2,"Aaaa","pass",true);
            gra.graczeInit(1,"Mati","haselko",false);
            assertNotSame(gra.gracz1,gra.gracz2);
        }

        @Test
        void increasStatisticWinGracz1Test()
        {
            gra.gracz1 = gra.gracze.get(0);
            gra.gracz2 = gra.gracze.get(1);
            gra.win = 1;
            gra.increasStatistic();
            assertEquals("2",gra.gracz1.getWygrana());
        }

        @Test
        void increasStatisticWinGracz1CheckWygranaGracz2Test()
        {
            gra.gracz1 = gra.gracze.get(0);
            gra.gracz2 = gra.gracze.get(1);
            gra.win = 1;
            gra.increasStatistic();
            assertNotEquals("2",gra.gracz2.getWygrana());
        }

        @Test
        void increasStatisticWinGracz1CheckPrzegranaGracz2Test()
        {
            gra.gracz1 = gra.gracze.get(0);
            gra.gracz2 = gra.gracze.get(1);
            gra.win = 1;
            gra.increasStatistic();
            assertEquals("2",gra.gracz2.getPrzegrana());
        }

        @Test
        void increasStatisticWinGracz2OneTest()
        {
            gra.gracz1 = gra.gracze.get(0);
            gra.gracz2 = gra.gracze.get(1);
            gra.win = 2;
            gra.increasStatistic();
            assertEquals("2",gra.gracz2.getWygrana());
        }

        @Test
        void increasStatisticRemisTest()
        {
            gra.gracz1 = gra.gracze.get(0);
            gra.gracz2 = gra.gracze.get(1);
            gra.win = 3;
            gra.increasStatistic();
            assertEquals("2",gra.gracz2.getRemis());
        }

        @Test
        void increasStatisticRemis1Test()
        {
            gra.gracz1 = gra.gracze.get(0);
            gra.gracz2 = gra.gracze.get(1);
            gra.win = 3;
            gra.increasStatistic();
            assertEquals("2",gra.gracz1.getRemis());
        }

        /////////////////////////////////////////////////////////////////////

        @Test
        void readListUserFileExitstTest()
        {
            gra = new Gra(7,6,false);
            gra.gracze = gra.readListGracze(null);
            assertNotNull(gra.gracze);
        }

        @Test
        void readListUserFileNotExitstTest()
        {
            gra = new Gra(7,6,false);
            assertThrows(NullPointerException.class, () ->
            {
                gra.gracze = gra.readListGracze("graczey.csv");
            },"File not exist");
        }

        @Test
        void writeListUserTest()
        {
            gra.writeListGracze("testList.csv");
            gra.gracze = gra.readListGracze("testList.csv");
            String actual = gra.gracze.get(0).toString();
            String expected = "Ksywa: Aaaa, Wygrana: 1, Przegrana: 3, Remis: 1";
            assertEquals(expected,actual);
        }

        @Test
        void moveGraczCheckIncreaseStatistic()
        {
            gra.gracz1 = gra.gracze.get(0);
            gra.gracz2 = gra.gracze.get(1);
            gra.fileName = "testList.csv";
            gra.graczeSet = 2;
            for(int i = rows-1;i>=rows-3;i--)
            {
                gra.moveGracz(1,0);
                gra.moveGracz(2,2);

            }
            String actual = gra.moveGracz(1,0);
            assertEquals("2",gra.gracz1.getWygrana(),"Wygrana shuld be 2");
            assertEquals("2",gra.gracz2.getPrzegrana(),"Przegrana shuld be 2");
        }

        @AfterEach
        void tearDownAll()
    {
        gra = null;     
    }
}