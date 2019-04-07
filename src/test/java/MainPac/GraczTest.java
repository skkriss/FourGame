package MainPac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GraczTest 
{
    Gracz gracz;

    @BeforeEach
    void init()
    {
        gracz = new Gracz("Patryk","nowezycie","2","1","1");
    }

    @Test
    void playerToStringTest()
    {

        String actual = gracz.toString();

        String expected = "Ksywa: Patryk, Wygrana: 2, Przegrana: 1, Remis: 1";

        assertEquals(expected,actual);
    }

    @Test
    void increaseWinsGracz()
    {

        gracz.setWygrana();

        assertEquals("3",gracz.getWygrana());
    }

    @Test
    void increaseLoseGracz()
    {

        gracz.setPrzegrana();

        assertEquals("2",gracz.getPrzegrana());
    }

    @Test
    void increaseTieGracz()
    {

        gracz.setRemis();

        assertEquals("2",gracz.getRemis());
    }

    @Test
    void setAndGetNick()
    {
        gracz.setKsywa("Patrys");

        assertEquals("Patrys",gracz.getKsywa());
    }

    @Test
    void setAndGetPassword()
    {
        gracz.setHaslo("nowe");

        assertEquals("nowe",gracz.getHaslo());
    }

    @Test
    void compareToSecondGraczMoreWinsTest()
    {
        Gracz p1 = new Gracz("A","B","2","1","0");
        Gracz p2 = new Gracz("B","A","4","1","0");

        int actual = p1.compareTo(p2);

        assertEquals(1,actual);
    }
    @Test
    void compareToFirstGraczMoreWinsTest()
    {
        Gracz p1 = new Gracz("A","B","4","1","0");
        Gracz p2 = new Gracz("B","A","2","1","0");

        int actual = p1.compareTo(p2);

        assertEquals(-1,actual);
    }

    @Test
    void compareToSameWinsFirstGraczLessLostTest()
    {
        Gracz p1 = new Gracz("A","B","2","1","0");
        Gracz p2 = new Gracz("B","A","2","2","0");

        int actual = p1.compareTo(p2);

        assertEquals(-1,actual);
    }

    @Test
    void compareToSameWinsSecondGraczLessLostTest()
    {
        Gracz p1 = new Gracz("A","B","2","3","0");
        Gracz p2 = new Gracz("B","A","2","2","0");

        int actual = p1.compareTo(p2);

        assertEquals(1,actual);
    }

    @Test
    void compareToSameWinsAndLostsFirstGraczLessTieTest()
    {
        Gracz p1 = new Gracz("A","B","2","2","0");
        Gracz p2 = new Gracz("B","A","2","2","3");

        int actual = p1.compareTo(p2);

        assertEquals(-1,actual);
    }

    @Test
    void compareToSameWinsAndLostsSecondGraczLessTieTest()
    {
        Gracz p1 = new Gracz("A","B","2","2","5");
        Gracz p2 = new Gracz("B","A","2","2","3");

        int actual = p1.compareTo(p2);

        assertEquals(1,actual);
    }

    @Test
    void compareToAllSameTest()
    {
        Gracz p1 = new Gracz("A","B","2","2","5");
        Gracz p2 = new Gracz("B","A","2","2","5");

        int actual = p1.compareTo(p2);

        assertEquals(0,actual);
    }
}