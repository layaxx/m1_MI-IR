# Assignment 3, group 11

## Task 1

### Task 1.2.b

Beim ZigZag Encoding werden alle Zahlen vor der VByte Komprimierung auf positive Zahlen gemappt.

### Task 1.3.b

VarIntGB ist auf modernen CPUs effizienter ausführbar als VByte, da durch die Sammlung der Meta Daten bei der Dekomprimierung weniger Vergleiche ausgeführt werden (bekannte Länge, also keine Suche nach Continuation Bits) und auch weniger Veränderungen an den Daten durchgeührt werden müssen (Bytes können komplett kopiert werden, ohne Continuation Bit zu entfernen).

# Task 2

### 2.1

Ab 35:38: In diesem Abschnitt ging es um explainable AI und Transparenz. In diesem Aspekt ist es bei deep-learning Verfahren immer noch schwer nachzuvollziehen, warum bestimmte Entscheidungen getroffen wurden (37:30). Dieses Problem ist laut den Experten allerdings lösbar (39:17). Ein Lösungsansatz wurde vorgeschlagen: Hier könnten verschiedene Informationsmodelle (z.B.: Keywords, Vectors, Knowledge Graphs) ineinander übersetzt werden, wodurch diese Modelle mithilfe des Kontexts der anderen Modelle einfacher interpretiert werden können (37:50). Es wird aber auch gewarnt, dass zu viel Transparenz es erlauben könnte das System zu auszunutzen (41:43).

Ab 47:10: Aus den Query Logs kann das System wichtige/häufig benutzte Begriffe herauslesen und verstehen, dass es sich um domänenspezifische Begriffe handelt, wodurch diese besser interpretiert werden können (47:10).  Außerdem kann mit Hilfe der Query Logs ein Programm auf Rechtschreibfehlererkennung und -verbesserung trainiert werden, da eine Anfrage mit Rechtschreibfehler öfters von den Nutzern verbessert wird. Dadurch kann das Programm diese Korrektur lernen (48:15) und anderen Nutzern vorschlagen.

Ab 1:43:37: Nutzer, die etwas suchen, haben oft unterschiedliche Bestreben, die anders behandelt werden sollten (1:43:57).  Um dies besser zu behandeln, könnten in der Zukunft verschiedene Systeme (wie normale Suchmaschinen und Chatbots) in ein System zusammengefasst werden (1:46:35). Die Suchmaschinen werden dadurch ein immer wichtiger Bestandteil im Umgang mit digitalen Informationen (1:46:24).

### 2.2

## Task 3

### 3.1.1

- Documents
  Ein Dokument ist eine Menge von Daten, die etwas beschreiben. Es besteht aus Feldern, die bestimmte Informationen enthalten.
- Fields/Field Classes
  Jedes Dokument kann aus mehreren Feldern bestehen, die zur Speicherung von einzelner Informationen verwendet werden. Jedes Feld hat einen Feldtyp, der den Typ oder das Format der in diesem Feld gespeicherten Daten angibt.
- Schema Design
  Diese Felder und ihre Typen werden im Schema gespeichert
- Collection
  Eine Kollektion sind ein oder mehrere Dokumente, die in einem einzigen logischen Index mit einer einzigen Konfiguration und einem einzigen Schema zusammengefasst sind.

### 3.1.2

- "Standard Query Parser"
  Der Standard Query Parser, auch als Lucene Parser bekannt, ist der Standard Parser von Solr. Er ermöglicht eine vielzahl strukurierter Anfragen durch robuste und intuitive Snytax, ist aber Syntaxfehlern gegnüber wenig tolerant.
- "DisMax Query Parser"
  Der DisMax Query Parser soll möglichst selten Fehler werfen. Verarbeitet werden hierbei einfache Phrasen ohne komplexe Syntax, sinnvoll insbesondere für direkten Userinput.
- "Extended DisMax (eDisMax) Query Parser"
  Dieser Parser ist eine verbesserte Version des DisMax Query Parsers mit zusätzlichen Parametern und Operatoren.

### 3.1.3

Der Schema Designer ist unter `/solr/#/~schema-designer`, im vorliegenden Beispiel also <http://localhost:8983/solr/#/~schema-designer> zu finden.
