### Beroenden
* ``DrawPanel`` har ett starkt beroende av ``Vehicle`` och ``Workshop``
* ``CarController`` har ett starkt beroende av ``Vehicle`` och ``Workshop``
* ``Vehicle`` har ett starkt beroende av ``IStorage``
* ``CarController`` har beroende till en grannes granne (``CarView``)
* ``CarController`` beror på swing biblioteket

### Single Responsibility Principle och Separation of Concern
* ``Vehicle`` har många ansvarsområden: ex storable, movable, allt som definierar en bil. Kan "lösas" med komposition.
* ``DrawPanel`` ritar skärmen ``CarView`` hanterar vad som ska ritas. All den funktionalitet bör hamna i ``CarView``
* ``CarController`` bör inte använda ``repaint()`` från ``DrawPanel`` eftersom ansvaret att rita upp skärmen inte ligger på ``CarController``
  * Kan ändra ``CarView`` så att det har sin egen klocka, därmed behöver inte ``CarController`` ens veta om att ``CarView`` existerar (multithreading)
* ``CarController`` skulle kunna dekompositioneras.
  * Main funktion
  * Gas, Stop, etc kan flyttas ut till en ``CarHandler`` annars blir den beroende av exakta implementationer av en bil.