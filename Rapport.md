## Test jMeter

Pour tester via jMeter, nous avons créé un script qui lance 5 threads en même temps qui crée un badge, un pointScale, une rule et qui boucle trois fois pour créer un évènement :
![](IMG/jMetter2.jpg)
![](IMG/jMetter.jpg)

Cela nous a donné comme résultats:

![](IMG/jMetter3.jpg)

et en base de données, cela donne : 

![](IMG/BaseDeDonneesUser.jpg)
![](IMG/BaseDeDonneesEvent.jpg)

Donc l'on remarque clairement qu'il y a un problème de gestion de la concurrence lors de la création d'évènement, il en résulte la création de trois users identiques alors qu'il ne devrait n'y en avoir qu'un seul.

### Solution
Il faudrait mettre en place une gestion de la concurrence pessimiste ou optimiste.
La distinction entre les deux est qu'en pessimiste, la gestion se fait en bloquant l'accès à la ressource si celle-ci est déjà en cours d'utilisation mais cela peut conduire à des deadlocks. En ce qui concerne la gestion de la concurrence optimiste, il y a la possibilité d'utiliser un ETag dans les requêtes de réponse si HTTP/1.1 est utilisée. Le ETag est un hash de la valeur d'une ressources, du temps de la dernière modification ou encore juste un numéro de révision. Le service qui utilise l'API REST doit donc ajouter dans son header un champs If-Match. Cela rend la requête conditionnelle et permet de vérifier si la ressource n'a pas été modifiée. Si la condition est vrai, pas de modification, alors elle est acceptée sinon on recevrait une réponse 412 (Precondition failed).
Sinon une autre manière de procédé est d'utilisé l'annotation JPA @Version qui permettra la création d'un champ de révision directement dans la base de données et donc c'est JPA directement qui détectera un conflit dans les updates.