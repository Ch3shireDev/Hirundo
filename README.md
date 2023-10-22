# Hirundo

Program do wybierania z bazy danych rekordów zdarzeń schwytania ptaków w czasie migracji.

## Motywacja

Uniwersytet Gdański organizuje kilka razy do roku wyjazdy Akcji Bałtyckiej (https://akbalt.ug.edu.pl/), podczas której badacze zbierają dane o wędrówkach ptaków. Każde ze zdarzeń schwytania ptaka jest notowane w bazie danych, ptak jest identyfikowany po numerze obrączki, bądź zakładana jest mu nowa obrączka. Zdarza się, że chwytany jest ten sam ptak w czasie różnych sezonów; sugeruje to, że ptak był w stanie przeżyć wędrówkę i powrót. Hipoteza badawcza mówi, że mierzone cechy takiego ptaka będą w sposób istotny różne od średniej populacji ptaków danego gatunku i płci w danym sezonie. Program ma ułatwiać przeglądanie i wybieranie z bazy danych interesujących nas rekordów powracających ptaków.

## Zamówienia do wersji 1.3

1. Należy policzyć dla D2-D8 parametry ostrości i skośności

    Ostrość (`Pointedness`) = suma D2:D8 / Wing
    Skośność (`Symmetry`) = suma wartości d na prawo od zera - suma wartości d na lewo od zera / suma d2-d8

2. Dla każdej wartości liczbowej (`Weight`, `Wing`, `Tail`, `Pointedness`, `Symmetry`) wyliczyć średnią + odchylenie standardowe + liczbę `n` populacji
    
    Populacja to:
        - wszystkie pierwsze złapania,
        - ptaków w wieku I lub J,
        - z danego roku,
        - danej płci,
        - z odrzuceniem wartości odchylonych o więcej niż 4 odchylenia standardowe.

3. Dla Fat policzyć (dla tak samo określonej populacji):
    - medianę,
    - kwartyl górny,
    - kwartyl dolny.

Tak naprawdę zależy nam na pierwszych złapaniach, nie na wszystkich złapaniach. Dalej powinniśmy posortować złapania, by najpierw były te wcześniejsze.

Płeć określa populację — tj. porównując ptaki z populacją, bierzemy pod uwagę również płeć.