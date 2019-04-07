package MainPac;

public class Gracz implements Comparable<Gracz>{

    @Override
    public String toString() {
        return
                "Ksywa: " + ksywa + ", Wygrana: " + wygrana + ", Przegrana: " + przegrana + ", Remis: "+ remis;
    }

    public Gracz(String ksywa, String haslo, String wygrana, String przegrana,String remis) {
        this.ksywa = ksywa;
        this.haslo = haslo;
        this.wygrana = wygrana;
        this.przegrana = przegrana;
        this.remis = remis;
    }

    private String ksywa;
    private String haslo;
    private String wygrana;
    private String przegrana;
    private String remis;

    public String getKsywa()
    {
        return ksywa;
    }

    public void setKsywa(String ksywa)
    {
        this.ksywa = ksywa;
    }

    public String getHaslo()
    {
        return haslo;
    }

    public void setHaslo(String haslo)
    {
        this.haslo = haslo;
    }

    public String getWygrana()
    {
        return wygrana;
    }

    public void setWygrana()
    {
        this.wygrana = Integer.toString(Integer.parseInt(wygrana)+1) ;
    }

    public String getPrzegrana()
    {
        return przegrana;
    }

    public void setPrzegrana()
    {
        this.przegrana = Integer.toString(Integer.parseInt(przegrana)+1);
    }

    public String getRemis()
    {
        return remis;
    }

    public void setRemis()
    {
        this.remis = Integer.toString(Integer.parseInt(remis)+1) ;
    }

    //Sortowanie graczy ( Wygrane > Przegrane > Remisy )
    @Override
    public int compareTo(Gracz g)
    {
        if(Integer.parseInt(wygrana) > Integer.parseInt(g.wygrana)) return -1;
        if(Integer.parseInt(wygrana) < Integer.parseInt(g.wygrana)) return 1;
        if(Integer.parseInt(przegrana) < Integer.parseInt(g.przegrana)) return -1;
        if(Integer.parseInt(przegrana) > Integer.parseInt(g.przegrana)) return 1;
        if(Integer.parseInt(remis) < Integer.parseInt(g.remis)) return -1;
        if(Integer.parseInt(remis) > Integer.parseInt(g.remis)) return 1;
        return 0;
    }

}
