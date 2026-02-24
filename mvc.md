### Problem med ursprungligt MVC
* Det finns ingen entydig modell, istället ändrar controller 
  på modellen genom ``actionPerfomred()`` i ``TimerListener``, och ``gas()``
* ``DrawPanel`` gör viewarbete med metoden ``paintComponent()``
* Statiska knappar som fungerar som input ska egentligen vara i controller, inte i view
  * Knapparnas funktionalitet måste i alla fall flyttas till controller
* Fönstrets storlek bör finnas i model, p.g.a kollisionsberäkningar
* Initieringen bör finnas i en applikation

### Del 3 MVC
##### Lösta problem
* Vi skapade en ``Main`` class som fungerar som en applikation
* Vi har mer tydlig separation mellan modell och controller
  * ``CarView`` har råkat bli både view och controller, medan
  ``CarController`` har blivit modellen

##### Återstående problem
* Fortfarande ingen entydig modell, eftersom ``DrawableData`` finns i ``DrawPanel``.
  Resten av modellen är i ``CarController``
* ``DrawPanel`` gör fortfarande viewarbete som innan


### Designmönster
* Vi använder observer pattern så att view can rita bilarna utan att
  model beror på view
* State pattern används för bilarnas riktning
* Vi hade kunnat använda composite pattern så att en ``IStorage`` räknas som en ``Storable``.
  Vi hade dock inget behov av detta, och det finns ingen anledning att en storage måste vara storable
* Vi ska använda en factory method pattern för att skapa olika varianter av bilar, så att
  ``Main`` inte behöver bero på alla varianter
