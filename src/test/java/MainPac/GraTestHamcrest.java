package MainPac;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GraTestHamcrest
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

    ////////////////////////////////////////////////////////////////////
    // Dla każdego rozmiaru

    @Test
    void checkWinVerticalTest()
    {
        for(int i = rows-1;i>=rows-4;i--)
        {
            gra.board[i][columns-1] = 1;
        }
        boolean actual = gra.checkWinVertical(1,rows-4,columns-1);
        assertThat(actual,is(equalTo(true)));
    }

    @Test
    void checkWinHorizontalTest()
    {
        for(int i = 0;i<4;i++)
        {
            gra.board[0][i] = 1;
        }
        boolean actual = gra.checkWinHorizontal(1,0,columns-4);
        assertThat(actual,is(equalTo(true)));
    }

    @Test
    void checkWinDiagonallyAscTest()
    {
        int col = columns-1;
        for(int i = 0;i<4;i++,col--)
        {
            gra.board[i][col] = 1;
        }
        boolean actual = gra.checkWinDiagonallyAsc(1,0,columns-1);
        assertThat(actual,equalTo(true));
    }

    @Test
    void checkWinDiagonallyDescTest()
    {
        int col = 0;
        for(int i = 0;i<4;i++,col++)
        {
            gra.board[i][col] = 1;
        }
        boolean actual = gra.checkWinDiagonallyDesc(1,2,2);
        assertThat(actual,is(true));
    }

    ////////////////////////////////////////////////////////////////////

    @Test
    void checkSizeOfListGraczTest()
    {
        gra.gracze.add(new Gracz("Mati","koko","0","0","0"));
        assertThat(gra.gracze,hasSize(3));
    }

    @Test
    void initTestCheckIfListIncludeNewGraczTest()
    {
        gra.graczeInit(1,"MArek","Pass",false);
        assertThat(gra.gracze,hasSize(3));
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
        assertThat(actual,anyOf(containsString("Gracz1")));
    }

    @Test
    void checkWinMethod_WinDiagonallyAscCheckStartAndEndString_Test()
    {
        int col = 0;
        for(int i = rows-1;i>=rows-4;i--,col++)
        {
            gra.board[i][col] = 1;
        }
        String actual = gra.checkWin(1,rows-2,1);
        assertThat(actual,allOf(startsWith("Gracz1"),endsWith("Asc)")));
    }

    @Test
    void checkWinMethod_WinHorizontal_Test()
    {
        for(int i = columns-1;i>=columns-4;i--)
        {
            gra.board[0][i] = 1;
        }
        String actual = gra.checkWin(1,0,columns-3);
        assertThat(actual,endsWith("(horizontal)"));
    }

    @Test
    void checkWinMethod_WinHorizontalValueWin_Test()
    {
        for(int i = columns-1;i>=columns-4;i--)
        {
            gra.board[0][i] = 1;
        }
        String actual = gra.checkWin(1,0,columns-3);
        assertThat(actual,startsWith("Gracz1"));
    }

    @Test
    void searchGraczCheckReturnVaule()
    {
        Gracz actual = gra.serachGracz("Aaaa","pass");
        assertThat(actual,notNullValue());
    }

    @Test
    void searchGraczChecClassVaule()
    {
        Gracz actual = gra.serachGracz("Aaaa","pass");
        assertThat(actual,isA(Gracz.class));
    }

    @Test
    void searchGraczCeckReturnVaule1()
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
        gra.moveGracz(1,0);
        int actual = Integer.parseInt(gra.gracz1.getWygrana());
        assertThat(actual,greaterThan(1));
    }

    @AfterEach
    void tearDownAll()
    {
        gra = null;
    }
}
