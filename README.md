# Hirundo

Program do wybierania z bazy danych rekordów zdarzeń schwytania ptaków w czasie migracji.

## Motywacja

Uniwersytet Gdański organizuje kilka razy do roku wyjazdy Akcji Bałtyckiej (https://akbalt.ug.edu.pl/), podczas której badacze zbierają dane o wędrówkach ptaków. Każde ze zdarzeń schwytania ptaka jest notowane w bazie danych, ptak jest identyfikowany po numerze obrączki, bądź zakładana jest mu nowa obrączka. Zdarza się, że chwytany jest ten sam ptak w czasie różnych sezonów - sugeruje to, że ptak był w stanie przeżyć wędrówkę i powrót. Hipoteza badawcza mówi, że mierzone cechy takiego ptaka będą w sposób istotny różne od średniej populacji ptaków danego gatunku i płci w danym sezonie. Program ma ułatwiać przeglądanie i wybieranie z bazy danych interesujących nas rekordów powracających ptaków.

## Wymagania funkcjonalne

1. [ ] Stworzyć moduł do pobierania danych z bazy danych Access
2. [ ] Stworzyć moduł do wysyłania zapytania do bazy danych według wybranych kryteriów
3. [ ] Stworzyć moduł wyliczający dane statystyczne dla wybranego zbioru rekordów
4. [ ] Stworzyć moduł do zapisu danych w formacie .csv
5. [ ] Stworzyć widok główny programu. W widoku powinny się znajdować:
    1. [ ] Panel wyboru bazy danych
    2. [ ] Panel ustawień kryteriów wyszukiwania
    3. [ ] Guzik do wysłania zapytania
    4. [ ] Pasek postępu pobierania danych
    5. [ ] Panel informujący o ilości pobranych rekordów
    6. [ ] Panel zapisu danych w wybranej formie do wybranej lokalizacji