package MainPac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("AssertJ Tests.")
public class GraAssertJTest
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
    void checkWinOnInitBoardTest()
    {
        assertThat(gra.win).isEqualTo(0);
    }

    @Test
    void checkInitGameWrongNumbOfRowsTest()
    {
        assertThatIllegalArgumentException().isThrownBy(() ->
        {
            gra = new Gra(3,6,false);
        });
    }

    @Test
    void checkInitGameWrongNumbOfColumnTest()
    {
        Throwable thrown = catchThrowable(()->
        {
            gra = new Gra(6,3,false);
        });
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testException()
    {
        assertThatIllegalArgumentException().isThrownBy(() ->
        {
            gra = new Gra(3,6,false);
        })
                .withMessage("%s", "Liczba kolumn i rzedow musi byc wieksza niz 4!")
                .withMessageContaining("kolumn");
    }

    ////////////////////////////////////////////////////////////////////
    // Dla każdego rozmiaru
    @Test
    void checkWinVerticalTest()
    {
        for(int i = rows-1;i>=rows-3;i--)
        {
            gra.board[i][columns-1] = 1;
        }
        boolean actual = gra.checkWinVertical(1,rows-3,columns-1);
        assertThat(actual).isFalse();
    }

    @Test
    void checkWinHorizontalTest()
    {
        for(int i = 0;i<4;i++)
        {
            gra.board[0][i] = 1;
        }
        boolean actual = gra.checkWinHorizontal(1,0,columns-4);
        assertThat(actual).isTrue();
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
        assertThat(actual).isEqualTo(true);
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
        assertThat(actual).isTrue();
    }

    ////////////////////////////////////////////////////////////////////

    @Test
    void checkAddedPlayersTest()
    {
        assertThat(gra.gracze).extracting("ksywa", "haslo")
                .contains(tuple("Aaaa","pass"),
                        tuple("Bbbb","haslo"));
    }

    @Test
    void checkAddNewPlayerAndCheckTest()
    {
        gra.graczeInit(1,"Marko","silne",false);
        assertThat(gra.gracze).extracting("ksywa", "haslo")
                .contains(tuple("Aaaa","pass"),
                        tuple("Bbbb","haslo"),
                        tuple("Marko","silne"));
    }

    @Test
    void checkSizeOfListPlayersTest()
    {
        gra.gracze.add(new Gracz("Mati","koko","0","0","0"));
        assertThat(gra.gracze).hasSize(3);
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
        assertThat(actual).startsWith("Gracz1")
                .endsWith("Asc)");
    }
    ////////////////////////////////////////////////////////////////////

    @Test
    void movePlayerSimulateTie4x4Test()
    {
        gra = new Gra(4,4,true);
        gra.gracze.add(new Gracz("Aaaa","pass","1","3","1"));
        gra.gracze.add(new Gracz("Bbbb","haslo","1","1","1"));
        gra.gracz1 = gra.gracze.get(0);
        gra.gracz2 = gra.gracze.get(1);
        gra.graczeSet = 2;
        gra.fileName = "testList.csv";

        int[] p1 = { 2, 3, 0 ,1, 2 ,3, 0, 1};
        int[] p2 = { 0, 1, 2, 3, 0, 1, 2, 1};
        for(int i = 0;i<8;i++)
        {
            gra.moveGracz(1,p1[i]);
            gra.moveGracz(2,p2[i]);
        }
        String actual = gra.moveGracz(2,3);
        String expected = "Plansza jest pelna! Remis!";
        assertThat(actual).isEqualTo(expected);
    }
}
