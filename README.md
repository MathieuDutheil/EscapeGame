# EscapeGame
## Comment compiler et exécuter ce programme Java en ligne de commande ?
<li><p>Vous pouvez compiler et exécuter ce programme écrit en langage Java depuis votre IDE favori (dans ce cas pensez à télécharger la librairie log4j au lien ci-dessous et de l'importer dans votre IDE.)</p>
<p><a href="http://mirrors.standaloneinstaller.com/apache/logging/log4j/1.2.17/log4j-1.2.17.zip">Log4j</a></p>
<a href="https://github.com/MathieuDutheil/EscapeGame.git">Lien GitHub pour cloner le projet depuis votre IDE.</a></li>

<li>
<p>Vous pouvez tout aussi bien le compiler et l'exécuter depuis la console de commande de votre système.</p>
<p>Merci de suivre les étapes suivantes :</p>
</li>

<ol>
<li>Téléchargez le code source du programme. Mémorisez le nom du dossier où vous enregistrez le programme.
<p><a href="https://github.com/MathieuDutheil/EscapeGame/archive/master.zip">Lien GitHub pour télécharger.</a></p>
</li>

<li>Ouvrez une console de commande.
<p>Sous Windows : pressez la touche Démarrer puis saisissez cmd dans la barre d'exécution qui est affichée. Pressez ensuite la touche Entrée pour ouvrir la console de commande.</p>
<p>Sous Mac : Faites Pomme + R et rechercher Terminal. Pressez ensuite la touche Entrée pour ouvrir le Terminal</p>
</li>

<li>Vérifiez si Java est installé sur votre ordinateur.
<p>Saisissez java -version puis pressez la touche Entrée dans votre console de commande.</p>
<p>Si l'environnement Java est installé, vous verrez s'afficher un message vous indiquant quelle est sa version active.</p>


</li>
</ol>




javac -cp log4j-1.2.17.jar *.java
export CLASSPATH=$CLASSPATH:/HD/Users/Mathieu/Desktop/EscapeGameApp/EscapeGame-master/src/log4j-1.2.17.jar
javac -cp log4j-1.2.17.jar *.java
http://mirrors.standaloneinstaller.com/apache/logging/log4j/1.2.17/log4j-1.2.17.zip
