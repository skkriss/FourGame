**https://travis-ci.com/skkriss/FourGame.svg?branch=master**


**REGUŁY ODDAWANIA PROJEKTU**

Wybieramy jedno z poniższych zadań. Zadania różnią się poziomem trudności i są inaczej punktowane.

Każdy projekt ma być wykonany przy użyciu narzędzia Maven lub Gradle! Projekt nie powinien zawierać pliku jar oraz folderu target lub build.

Obowiązkowo każdy projekt ma być podpięty pod serwis TravisCi

Oddanie projektu będzie odbywać się poprzez utworzenie Issue w repozytorium (szybsza opcja) lub pokazaniu go na zajęciach.

Projekty, w których nie będzie przechodzić polecenie mvn test lub gradle test będą projektami zaliczonymi na 0%

Termin oddania pierwszego projektu to: 08.04.2019 (100%). Następny termin (15.04.2018) będzie terminem na 50%. Wówczas liczy się moment utworzenia Issue (każdy dzień zwłoki obniża wartość procentową o około 10%). 

Ponadto pod ocenę będzie brany styl projektu: jak zapisane są testy i jak sprawdzane są asercje.

Testy powinny wykorzystywać wiele różnych asercji (a nie tylko assertEquals)!

Sprawdzenie projektu na zajęciach będzie polegało na jego obronie: będą to krótkie pytania i ewentualne drobne zmiany w kodzie podane przez prowadzącego!

Prowadzący będzie sprawdzał projekty samodzielnie do dnia 06.04.2019.

Link do GitHub Classroom: https://classroom.github.com/a/v4-4cgKw


------------------------

**Projekt 5 (22 pkt)**

Gra w czwórki. Czwórki to gra dla dwóch osób. Pierwszy gracz wybiera kolor, a następnie obie osoby na zmianę wrzucają kolorowe krążki w kolumny pionowej planszy o wymiarach 7 kolumn x 6 wierszy. Krążki spadają w dół i zajmują najniższe wolne miejsce w danej kolumnie. Celem gry jest umieszczenie czterech krążków w swoim kolorze w pionie, w poziomie lub po przekątnej. Wygrywa osoba, która zrobi to pierwsza. Link do gry jest tutaj: http://www.connectfour.org/

Wymagania do tej gry są następujące:

Plansza składa się z siedmiu kolumn i sześciu wierszy. Początkowo wszystkie pola są puste.
Gracze wrzucają od góry krążki w kolumny. Jeśli kolumna jest pusta, wrzucony krążek spada w dół planszy. Krążki dodane do tej kolumny będą się znajdować nad tymi wcześniej wrzu- conymi.
Gra jest przeznaczona dla dwóch osób. Każdemu graczowi jest przypisany jeden kolor. Jedna osoba używa krążków czerwonych, a druga zielonych. Gracze wykonują ruchy na zmianę i za każdym razem wrzucają jeden krążek.
Program ma informować o zdarzeniach i błędach w grze. Dane wyjściowe powinny przedsta- wiać stan na planszy po każdym ruchu.
Gdy nie można dodać kolejnych krążków. Gra kończy się remisem.
Jeśli gracz dodał krążek i w ten sposób połączył więcej niż trzy krążki w swoim kolorze przylegające do siebie w pionowej linii, wygrywa.
To samo dzieje się, jeśli połączono krążki w linii poziomej.
To samo dzieje się, jeśli połączono krążki po stosie.
Tworzenie list rankingowych graczy: imię, punktacja, ilość wygranych itd
Zakładamy możliwość cofania się ruchów gracza
Pod ocenę będą brane pod uwagę następujące elementy:

(1 pkt) Kompilacja i uruchomienie bezbłędne projektu.
(5 pkt) Uwzględnienie powyższych wymagań.
(7 pkt) Przypadki testowe.
(1 pkt) Użycie różnych asercji.
(1 pkt) Uwzględnienie wyjątków.
(1 pkt) Zastosowanie biblioteki Hamcrest.
(1 pkt) Pokrycie kodu.
(1 pkt) Styl kodu.
(1 pkt) Zastosowanie metodyki TDD.
(1 pkt) Zastosowanie testów parametrycznych.
(1 pkt) Zastosowanie testów parametrycznych przy użyciu plików testowych.
(1 pkt) Użycie biblioteki AssertJ.
Ponadto dodatkowo pod uwagę będą brane następujące elementy:

(2 pkt) Rozszerzenie planszy na 𝑚×𝑛
m
×
n
(2 pkt) Dodanie możliwości zapisu gry do pliku
 Ponadto pod ocenę jest brane również: (Brak tych elementów: -1 pkt za podpunkt od obowiązkowej punktacji zadania!)

Historia projektu w repozytorium.
Ocena opisu commitów
Stan repozytorium (żeby nie był śmietnikiem!!!)
