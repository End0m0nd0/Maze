# Opis projektu

Aplikacja jest graficznym programem desktopowym napisanym w języku **Java** z wykorzystaniem biblioteki **Swing**, którego celem jest wizualne generowanie oraz rozwiązywanie labiryntów. Program tworzy losowy labirynt, rozmieszcza w nim specjalne punkty (oznaczone czerwonymi kółkami), a następnie wyznacza najkrótszą możliwą ścieżkę prowadzącą od pola startowego do wyjścia, przechodzącą po kolei przez wszystkie te punkty.


## Funkcje

- Generowanie labiryntu za pomocą algorytmu **DFS (Depth-First Search)**
- Rozwiązywanie labiryntu za pomocą algorytmu **BFS (Breadth-First Search)**
- Losowe rozmieszczanie specjalnych **punktów** w labiryncie
- Znalezienie najkrótszej ścieżki od startu (0,0) do końca (prawy dolny róg), przechodząc **w ustalonej kolejności przez wszystkie punkty**
- Graficzne wyświetlanie labiryntu i ścieżki w oknie aplikacji

## Jak uruchomić projekt z konsoli (CMD)

1. Otwórz konsolę (CMD) w katalogu głównym projektu (tam, gdzie znajduje się folder `src`).
2. Przejdź do folderu `src`:

   ```
   cd src
   ```

3. Skompiluj wszystkie pliki `.java`:

   ```
   javac *.java
   ```

4. Uruchom program, podając dwa argumenty:

   ```
   java Main <rozmiar_labiryntu> <liczba_punktów>
   ```

   Przykład:

   ```
   java Main 10 5
   ```

   To utworzy labirynt o rozmiarze 10×10 i rozmieści 5 specjalnych punktów.

5. Aplikacja otworzy okno graficzne z wygenerowanym labiryntem oraz wizualizacją ścieżki przechodzącej przez wszystkie punkty.

## Zrzuty ekranu

<p align="center">
  <img src="https://i.imgur.com/c4oGIni.png" alt="Widok 1" style="height: 325px; object-fit: cover; object-position: top; margin: 5px; border: 1px solid #ccc;"/>
  <img src="https://i.imgur.com/szBK7Yl.png" alt="Widok 2" style="height: 325px; object-fit: cover; object-position: top; margin: 5px; border: 1px solid #ccc;"/>
  <img src="https://i.imgur.com/gDuCMmc.png" alt="Widok 3" style="height: 325px; object-fit: cover; object-position: top; margin: 5px; border: 1px solid #ccc;"/>
</p>

## Wymagania

- Zainstalowane **Java Development Kit (JDK)**
- Komendy `javac` i `java` dostępne w systemie (można sprawdzić: `javac -version`)
- System operacyjny: Windows, Linux lub macOS

## Autor

Jędrzej Nowakowski
