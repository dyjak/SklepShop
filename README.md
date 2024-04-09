<a name="br1"></a> 

# SKLEPSHOP



1



<a name="br2"></a> 

Spis treści

[Cel](#br3)[ ](#br3)[aplikacji](#br3)[ ](#br3)[oraz](#br3)[ ](#br3)[ogólny](#br3)[ ](#br3)[opis:](#br3)[..................................................................................................................](#br3)[ ](#br3)[3](#br3)

[Wymagania](#br4)[ ](#br4)[systemowe:](#br4)[..........................................................................................................................](#br4)[ ](#br4)[4](#br4)

[Diagram](#br4)[ ](#br4)[ERD](#br4)[ ](#br4)[(Enꢀty-Relaꢀonship](#br4)[ ](#br4)[Diagram):](#br4)[...........................................................................................](#br4)[ ](#br4)[4](#br4)

[Tabela](#br4)[ ](#br4)[products](#br4)[ ](#br4)[...................................................................................................................................](#br4)[ ](#br4)[4](#br4)

[Tabela](#br5)[ ](#br5)[seꢁlements](#br5)[ ](#br5)[..............................................................................................................................](#br5)[ ](#br5)[5](#br5)

[Instrukcja](#br6)[ ](#br6)[instalacji:](#br6)[.................................................................................................................................](#br6)[ ](#br6)[6](#br6)

[Opis](#br7)[ ](#br7)[Interfejsu](#br7)[ ](#br7)[Użytkownika:](#br7)[...................................................................................................................](#br7)[ ](#br7)[7](#br7)

[Panel](#br7)[ ](#br7)[Produktów:](#br7)[ ](#br7)[................................................................................................................................](#br7)[ ](#br7)[7](#br7)

[Koszyk](#br8)[ ](#br8)[Zakupów:](#br8)[ ](#br8)[.................................................................................................................................](#br8)[ ](#br8)[8](#br8)

[Rozliczenia:](#br8)[ ](#br8)[..........................................................................................................................................](#br8)[ ](#br8)[8](#br8)

[Edycja](#br9)[ ](#br9)[Produktów:](#br9)[...............................................................................................................................](#br9)[ ](#br9)[9](#br9)

[Historia](#br9)[ ](#br9)[Transakcji:](#br9)[...............................................................................................................................](#br9)[ ](#br9)[9](#br9)

[Funkcjonalności:](#br10)[....................................................................................................................................](#br10)[ ](#br10)[10](#br10)

[Przeglądanie](#br10)[ ](#br10)[Produktów:](#br10)[...................................................................................................................](#br10)[ ](#br10)[10](#br10)

[Dodawanie](#br10)[ ](#br10)[Produktów](#br10)[ ](#br10)[do](#br10)[ ](#br10)[Koszyka:](#br10)[ ](#br10)[..................................................................................................](#br10)[ ](#br10)[10](#br10)

[Rozliczenia:](#br10)[ ](#br10)[........................................................................................................................................](#br10)[ ](#br10)[10](#br10)

[Edycja](#br10)[ ](#br10)[Produktów:](#br10)[.............................................................................................................................](#br10)[ ](#br10)[10](#br10)

[Historia](#br10)[ ](#br10)[Transakcji:](#br10)[.............................................................................................................................](#br10)[ ](#br10)[10](#br10)

[Bezpieczeństwo:](#br11)[ ](#br11)[Zabezpieczenia](#br11)[ ](#br11)[i](#br11)[ ](#br11)[Spójność](#br11)[ ](#br11)[Bazy](#br11)[ ](#br11)[Danych](#br11)[.....................................................................](#br11)[ ](#br11)[11](#br11)

[Dokumentacja](#br11)[ ](#br11)[API,](#br11)[ ](#br11)[Opis](#br11)[ ](#br11)[Metod](#br11)[ ](#br11)[i](#br11)[ ](#br11)[Klas:](#br11)[ ](#br11)[..................................................................................................](#br11)[ ](#br11)[11](#br11)

[Products_LoadProducts:](#br11)[....................................................................................................................](#br11)[ ](#br11)[11](#br11)

[Products_Management:](#br11)[....................................................................................................................](#br11)[ ](#br11)[11](#br11)

[Seꢁlements:](#br12)[ ](#br12)[......................................................................................................................................](#br12)[ ](#br12)[12](#br12)

[AddedProduct:](#br12)[...................................................................................................................................](#br12)[ ](#br12)[12](#br12)

[Przydatne](#br13)[ ](#br13)[Informacje:](#br13)[ ](#br13)[...........................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Ograniczenia](#br13)[ ](#br13)[Aplikacji:](#br13)[.......................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Baza](#br13)[ ](#br13)[Danych:](#br13)[..................................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Interfejs](#br13)[ ](#br13)[Użytkownika:](#br13)[ ](#br13)[...................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Zalety](#br13)[ ](#br13)[Aplikacji:](#br13)[..................................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Łatwość](#br13)[ ](#br13)[Używania:](#br13)[ ](#br13)[........................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Elastyczność:](#br13)[..................................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Dokumentacja](#br13)[ ](#br13)[API:](#br13)[ ](#br13)[........................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Zabezpieczenia:](#br13)[ ](#br13)[.............................................................................................................................](#br13)[ ](#br13)[13](#br13)

[Wykorzystanie](#br13)[ ](#br13)[Technologii:](#br13)[ ](#br13)[...........................................................................................................](#br13)[ ](#br13)[13](#br13)

2



<a name="br3"></a> 

Cel aplikacji oraz ogólny opis:

Celem aplikacji jest stworzenie kompleksowego systemu obsługi sklepu, który umożliwia zarządzanie

produktem, przeglądanie oferty, dodawanie produktów do koszyka, a następnie ﬁnalizację zakupu.

Aplikacja ma na celu dostarczenie wygodnego i intuicyjnego interfejsu dla klientów, a także

efektywnych narzędzi administracyjnych dla obsługi produktów.

Aplikacja składa się z kilku kluczowych komponentów, oferując różnorodne funkcjonalności:

EditProductsDB: Klasa umożliwiająca zarządzanie bazą danych produktów. Administratorzy mogą

dodawać nowe produkty, edytować istniejące, a także usuwać nieaktualne pozycje. Dzięki tej klasie,

aktualizacja oferty sklepu staje się łatwa i efektywna.

Products\_LoadProducts: Klasa odpowiedzialna za dynamiczne ładowanie produktów do interfejsu

użytkownika z uwzględnieniem różnych kategorii, opcji sortowania oraz możliwości wyszukiwania.

Użytkownicy mogą łatwo przeglądać ofertę, dostosowując widok do swoich preferencji.

Products\_Management: Klasa zarządzająca koszykiem zakupowym. Użytkownicy mogą dodawać

produkty do koszyka, określać ich ilość, a następnie ﬁnalizować zakupy. Mechanizmy tej klasy

uwzględniają również obliczanie łącznej wartości koszyka.

Seꢁlements: Klasa przechowująca informacje o dokonanych transakcjach. Zapewnia pełny przegląd

historii zakupów, w tym daty, metody płatności, identyﬁkatory produktów i łączną kwotę transakcji.

AddedProduct: Klasa reprezentująca produkty dodane do koszyka. Zawiera informacje takie jak

identyﬁkator produktu, nazwa, cena jednostkowa i ilość.

Product: Klasa deﬁniująca strukturę pojedynczego produktu, zawierająca informacje takie jak

identyﬁkator, nazwa, cena, kategoria itp.

Seꢁlement: Klasa deﬁniująca strukturę rozliczenia transakcji, obejmująca identyﬁkator, produkty,

metodę płatności, datę i łączną kwotę.

App: Główna klasa aplikacji, integrująca wszystkie komponenty i obsługująca interakcję z

użytkownikiem. Wykorzystuje język Java, JavaFX do budowy interfejsu graﬁcznego oraz Hibernate do

komunikacji z bazą danych SQLite.

Aplikacja umożliwia pełne doświadczenie zakupowe, obejmujące przeglądanie, dodawanie produktów

do koszyka, płatność i utrzymanie historii transakcji. Jej przejrzysty interfejs oraz elastyczna struktura

pozwalają dostosować ją do różnych potrzeb sklepów.

3



<a name="br4"></a> 

Wymagania systemowe:

Aby efektywnie korzystać z aplikacji, użytkownikowi zaleca się spełnienie następujących wymagań

systemowych:

•

•

System operacyjny: Aplikacja jest kompatybilna z systemami operacyjnymi z rodziny Windows,

macOS oraz Linux.

Moc obliczeniowa: Aplikacja nie wymaga znaczących zasobów sprzętowych. Zaleca się jednak

posiadanie komputera z wydajnym procesorem i wystarczającą ilością pamięci RAM, aby

zapewnić płynne działanie.

•

•

Dysk twardy: Wolne miejsce na dysku twardym na przechowywanie aplikacji oraz ewentualnej

lokalnej bazy danych.

Rozdzielczość ekranu: Zalecana jest minimalna rozdzielczość ekranu umożliwiająca wygodne

korzystanie z interfejsu graﬁcznego aplikacji (np. 1280x720 pikseli).

Spełnienie powyższych wymagań pozwoli użytkownikowi na pełne korzystanie z funkcji aplikacji,

zapewniając jednocześnie stabilność i wydajność działania.

Diagram ERD (Enꢀty-Relaꢀonship Diagram):

Tabela products

product\_id (int, klucz główny): Unikalny identyﬁkator produktu.

name (varchar): Nazwa produktu.

category (varchar): Kategoria, do której należy produkt.

price (double): Cena produktu.

Cel: Przechowuje informacje o produktach dostępnych w sklepie. Umożliwia skategoryzowanie

produktów oraz określenie ich cen.

4



<a name="br5"></a> 

Tabela seꢁlements

seꢁlement\_id (int, klucz główny): Unikalny identyﬁkator rozliczenia.

products\_ids (varchar): Identyﬁkatory produktów zawartych w danym rozliczeniu.

method (varchar): Metoda płatności (np. karta, gotówka).

date (varchar): Data i czas dokonania rozliczenia.

total\_price (double): Łączna wartość produktów w danym rozliczeniu.

Cel: Umożliwia śledzenie historii rozliczeń dokonywanych w sklepie. Przechowuje informacje o

metodzie płatności, dacie oraz łącznej kwocie danego rozliczenia.

Uzasadnienie wyboru typu danych:

•

•

int (integer): Wykorzystywany do przechowywania liczbowych identyﬁkatorów, zapewniając

unikalność każdego rekordu.

varchar (variable character): Stosowany do przechowywania zmiennych tekstowych danych,

takich jak nazwy, kategorie, metody płatności, daty. Te n typ został również użyty do

przechowywania identyﬁkatorów produktów w tabeli seꢁlements.

double: Wybierany dla przechowywania wartości liczbowych zmiennoprzecinkowych, takich

jak ceny produktów.

•

Cel zbierania danych w tabelach:

products:

•

•

•

Śledzenie dostępnych produktów w sklepie.

Ułatwienie kategoryzacji produktów.

Zapewnienie informacji o cenach produktów.

seꢁlements:

•

•

•

•

Rejestrowanie historii transakcji dokonywanych w sklepie.

Umożliwienie analizy metod płatności preferowanych przez klientów.

Zapewnienie pełnego obrazu ﬁnansowego sklepu.

Te dane są kluczowe dla prawidłowego funkcjonowania aplikacji, umożliwiając zarządzanie

produktami oraz monitorowanie historii rozliczeń w sklepie.

5



<a name="br6"></a> 

Instrukcja instalacji:

1\. Baza Danych:

•

Skonﬁguruj lokalną bazę danych SQLite i utwórz bazę danych o nazwie w formacie \*.db.

2\. Środowisko Java:

•

Upewnij się, że na Twoim systemie jest zainstalowane środowisko wykonawcze Java (JRE)

w wersji 8 lub nowszej.

3\. Biblioteki zewnętrzne:

Projekt wykorzystuje bibliotekę Hibernate do obsługi bazy danych. Pobierz niezbędne pliki

•

JAR z oﬁcjalnej strony Hibernate (hꢁps://hibernate.org/orm/releases/) i dodaj je do

projektu.

4\. Uruchomienie Aplikacji:

Uruchom projekt za pomocą środowiska programistycznego obsługującego JavaFX (np.

IntelliJ IDEA z zainstalowanym pluginem JavaFX) lub za pomocą terminala/linii poleceń.

5\. Konﬁguracja systemu/aplikacji/serwerów:

•

•

Konﬁguracja Bazy Danych: W pliku konﬁguracyjnym "hibernate.cfg.xml", znajdującym się

w pakiecie "resources", dostosuj ustawienia bazy danych, takie jak ścieżka, użytkownik i

hasło.

•

•

•

Hibernate: Skonﬁguruj plik "hibernate.cfg.xml" zgodnie z wymaganiami dostępu do bazy

danych SQLite.

Interfejs Użytkownika: Projekt korzysta z interfejsu użytkownika opartego na JavaFX. W

przypadku konieczności dostosowania interfejsu, edytuj pliki FXML w folderze "resources".

Pliki Źródłowe: Upewnij się, że wszystkie pliki źródłowe, takie jak klasy Java, są prawidłowo

zaimportowane do projektu.

6\. Uruchomienie Aplikacji:

•

Uruchom aplikację, a następnie korzystaj z funkcji zarządzania produktami i rozliczeń w sklepie.

Zastosowanie się do powyższych instrukcji instalacji i konﬁguracji pozwoli na pomyślne wdrożenie

aplikacji oraz korzystanie z jej funkcji.

6



<a name="br7"></a> 

Opis Interfejsu Użytkownika:

Aplikacja "SklepShop" oferuje przejrzysty i intuicyjny interfejs użytkownika, dostosowany do

efektywnego zarządzania produktami, składania zamówień oraz przeglądania historii transakcji. Poniżej

znajdują się szczegółowe informacje na temat poszczególnych komponentów interfejsu:

Panel Produktów:

Panel ten prezentuje listę produktów w postaci kafelków. Każdy kafelek zawiera informacje o produkcie,

takie jak ID, kategoria, nazwa, cena oraz ikona reprezentująca kategorię. Użytkownik może zastosować

ﬁltry kategorii, korzystając z checkboxów. Dodatkowo, istnieje możliwość sortowania produktów

według różnych kryteriów, takich jak nazwa czy cena. Przydatne narzędzie wyszukiwania umożliwia

szybkie odnalezienie konkretnego produktu.

7



<a name="br8"></a> 

Koszyk Zakupów:

Koszyk zakupów wyświetla dodane produkty w formie listy. Dla każdego produktu podano nazwę, ilość,

cenę jednostkową oraz całkowitą cenę. Użytkownik ma możliwość dostosowywania ilości produktów

za pomocą przycisków "+" i "-", a także usuwania produktów z koszyka. Całkowita cena zakupów jest

dynamicznie aktualizowana.

Rozliczenia:

W sekcji rozliczeń użytkownik może ﬁnalizować zakupy, wybierając metodę płatności spośród

dostępnych opcji (karta/gotówka). Po zatwierdzeniu zakupów, system generuje unikalny identyﬁkator

transakcji, a informacje o zakupach są dodawane do historii transakcji. Interaktywny przycisk "Rozlicz"

umożliwia płatność.

8



<a name="br9"></a> 

Edycja Produktów:

Interfejs edycji produktów jest dostępny dla uprawnionych użytkowników. Pozwala na dodawanie

nowych produktów, usuwanie istniejących oraz aktualizację informacji o produktach. Formularz edycji

zawiera pola takie jak nazwa, cena, kategoria.

Historia Transakcji:

Sekcja historii transakcji umożliwia użytkownikowi przeglądanie wszystkich dokonanych zakupów.

Wyświetlane są informacje o dacie zakupu, zakupionych produktach, metodzie płatności oraz

całkowitej kwocie transakcji. Użytkownik ma możliwość śledzenia swoich wcześniejszych zakupów.

Interfejs użytkownika został starannie zaprojektowany z myślą o wygodzie użytkownika, zapewniając

prostą nawigację i intuicyjne korzystanie z funkcji aplikacji "SklepShop".

9



<a name="br10"></a> 

Funkcjonalności:

Przeglądanie Produktów:

Użytkownik może przeglądać listę produktów z podziałem na kategorie. Filtry kategorii, wyszukiwarka

oraz opcje sortowania umożliwiają szybkie odnalezienie interesującego produktu.

•

•

•

•

•

Otwórz aplikację i przejdź do panelu produktów.

Zaznacz interesujące kategorie za pomocą checkboxów.

Wprowadź nazwę produktu lub użyj pustego pola wyszukiwania.

Wybierz preferowany sposób sortowania.

Przeglądaj dostępne produkty.

Dodawanie Produktów do Koszyka:

Użytkownik może dodawać produkty do koszyka zakupów, określając ilość. Dodane produkty są

widoczne w koszyku wraz z aktualną ceną.

•

•

•

Wybierz produkt z panelu produktów.

Wprowadź żądaną ilość i dodaj do koszyka.

Sprawdź zawartość koszyka.

Rozliczenia:

Po zakończeniu zakupów, użytkownik może przejść do sekcji rozliczeń, gdzie wybiera metodę płatności

i ﬁnalizuje zakupy.

•

•

•

•

Przejdź do koszyka zakupów.

Wybierz przycisk "Rozlicz".

Wybierz metodę płatności: karta/kontanty.

Potwierdź zakupy.

Edycja Produktów:

Uprawnieni użytkownicy mogą dodawać nowe produkty, usuwać istniejące oraz aktualizować

informacje o produktach.

•

•

•

•

Przejdź do sekcji edycji produktów.

Wybierz opcję dodawania nowego produktu lub edycji istniejącego.

Wprowadź lub zaktualizuj dane produktu.

Zapisz zmiany.

Historia Transakcji:

Użytkownik może przeglądać historię swoich transakcji, obejmującą datę zakupu, zakupione produkty,

metodę płatności oraz łączną kwotę.

•

•

Przejdź do sekcji historii transakcji.

Przeglądaj informacje o wcześniejszych zakupach.

Diagramy przepływu informacji zostały stworzone, aby zobrazować interakcje pomiędzy różnymi

funkcjonalnościami aplikacji. Ułatwiają one zrozumienie procesów oraz kroków niezbędnych do

wykonania określonych czynności.

10



<a name="br11"></a> 

Bezpieczeństwo: Zabezpieczenia i Spójność Bazy Danych

1\. Autoryzacja i Autentykacja:

•

•

Aplikacja wymaga od użytkowników autoryzacji poprzez unikalne identyﬁkatory i hasła.

Bezpieczne praktyki uwierzytelniania są stosowane w celu ochrony dostępu do panelu

administracyjnego.

2\. Spójność Bazy Danych:

•

Baza danych jest zoptymalizowana, aby utrzymywać spójność danych w przypadku wszelkich

operacji zapisu lub aktualizacji.

•

Transakcje są odpowiednio obsługiwane, a mechanizmy roll-back są dostępne w przypadku

awarii systemu.

3\. Zabezpieczenia przed Wstrzykiwaniem SQL:

•

Wszystkie zapytania do bazy danych są parametryzowane w celu zminimalizowania ryzyka

wstrzykiwania SQL.

•

Używane są przygotowane instrukcje SQL do separacji danych od zapytań.

Dokumentacja API, Opis Metod i Klas:

Products\_LoadProducts:

Metoda loadProducts(loader: FXMLLoader)

Przykład użycia:

// Załaduj produkty i zaktualizuj interfejs użytkownika

Products\_LoadProducts.loadProducts(loader);

Opis: Metoda ta jest używana do dynamicznego ładowania produktów z bazy danych i

aktualizacji interfejsu użytkownika zgodnie z ustawieniami, takimi jak kategorie,

sortowanie i wyszukiwanie.

Metoda loadProductsControls(productsRoot: Parent, productsLoader: FXMLLoader)

Przykład użycia:

// Obsłuż zdarzenia związane z kontrolkami interfejsu użytkownika w sekcji produktów

Products\_LoadProducts.loadProductsControls(productsRoot, productsLoader);

Opis: Ta metoda jest odpowiedzialna za przypisanie obsługi zdarzeń dla kontrolek interfejsu

użytkownika w sekcji produktów, takich jak checkboxy kategorii, pole wyszukiwania czy

radio buꢁony sortowania.

Products\_Management:

Metoda addToCart(product\_i: Product, amount: int, loader: FXMLLoader)

Przykład użycia:

// Dodaj produkt do koszyka i zaktualizuj widok koszyka

Products\_Management.addToCart(productInstance, 2, loader);

11



<a name="br12"></a> 

Opis: Metoda ta dodaje produkt do koszyka z uwzględnieniem określonej ilości i aktualizuje

widok koszyka na interfejsie użytkownika.

Metoda seꢁle(loader: FXMLLoader)

Przykład użycia:

// Finalizuj zakup i zapisz nowy rekord rozliczenia w bazie danych

Products\_Management.seꢁle(loader);

Opis:

Metoda ta służy do ﬁnalizacji zakupu, tworzenia nowego rekordu rozliczenia w bazie

danych oraz czyszczenia zawartości koszyka.

Seꢁlements:

Metoda addSeꢁlement(seꢁlement: Seꢁlement)

Przykład użycia:

// Dodaj nowy rekord rozliczenia do bazy danych

Seꢁlements.addSeꢁlement(seꢁlementInstance);

Opis:Metoda ta dodaje nowy rekord rozliczenia do bazy danych na podstawie

przekazanego obiektu Seꢁlement.

AddedProduct:

Klasa AddedProduct reprezentuje produkt dodany do koszyka z informacjami takimi jak

identyﬁkator, nazwa, cena jednostkowa i ilość.

Przykład użycia:

// Tworzenie instancji AddedProduct i dodanie jej do koszyka

AddedProduct addedProductInstance = new AddedProduct(1, "Mleko", 2.50, 3);

// Dodaj produkt do koszyka

Products\_Management.addToCart(addedProductInstance, 3, loader);

Opis: Klasa AddedProduct służy do przechowywania informacji o produktach dodanych do

koszyka. Przykład pokazuje, jak stworzyć instancję klasy i dodać produkt do koszyka poprzez

odpowiednią metodę z API

API zostało zaprojektowane z myślą o prostocie użycia i elastyczności, umożliwiając programistom

integrację funkcji z istniejącymi modułami aplikacji. Przykłady użycia pokazują, jak skutecznie korzystać

z dostępnych metod i klas w celu obsługi produktów, koszyka oraz ﬁnalizacji zakupów.

12



<a name="br13"></a> 

Przydatne Informacje:

Aplikacja "SklepShop" została zaprojektowana jako system obsługi sklepu internetowego,

umożliwiający zarządzanie produktami, koszykiem zakupowym oraz ﬁnalizację transakcji. Bazuje na

technologii JavaFX i Hibernate, co zapewnia elastyczność i wydajność działania.

Ograniczenia Aplikacji:

Baza Danych:

•

•

Aplikacja obecnie obsługuje tylko bazę danych SQLite.

Nie obsługuje wielu użytkowników jednocześnie.

Interfejs Użytkownika:

•

Projekt interfejsu użytkownika może wymagać dalszego dostosowania do oczekiwań

końcowego użytkownika.

•

Nie został jeszcze zaimplementowany mechanizm autoryzacji i kont użytkowników.

Zalety Aplikacji:

Łatwość Używania:

•

•

Intuicyjny interfejs użytkownika zapewnia łatwość obsługi aplikacji.

Prosta nawigacja po produktach, dodawanie ich do koszyka i ﬁnalizacja zakupów.

Elastyczność:

•

•

Modułowy projekt pozwala na łatwe rozszerzanie funkcjonalności.

Zastosowanie Hibernate ułatwia dostęp do bazy danych i manipulację danymi.

Dokumentacja API:

•

•

Jasna dokumentacja API ułatwia programistom integrację z istniejącymi modułami aplikacji.

Przykłady użycia ułatwiają zrozumienie funkcji dostępnych w API.

Zabezpieczenia:

•

Choć obecnie brakuje pełnego mechanizmu autoryzacji, aplikacja stosuje zabezpieczenia

spójności danych w bazie, co zapewnia integralność informacji.

Wykorzystanie Technologii:

•

JavaFX i Hibernate są wydajnymi i sprawdzonymi technologiami w tworzeniu aplikacji

desktopowych obsługujących bazy danych.

Podsumowując, "SklepShop" to aplikacja zorientowana na prostotę użytkowania i dostosowalność do

przyszłych rozszerzeń. Ograniczenia dotyczą głównie bieżącej funkcjonalności bazy danych i interfejsu

użytkownika.

13


