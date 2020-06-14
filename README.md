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
<p>S'il n'est pas installé sur votre ordinateur, vous pourrez télécharger gratuitement l'environnement de développement et d'exécution (Java JDK) <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">depuis le site web d'Oracle</a> et l'installer.</p>
<p>Pensez à <a href="https://www.java.com/fr/download/help/path.xml">configurer le "path"</a></p>
</li>

<li>Placez-vous dans le dossier où est enregistré votre code source.
<p>Utilisez la commande cd suivie de l'arborescence et du nom du répertoire où vous avez enregistré votre code source.</p>
<p>Si vous êtes positionné dans le répertoire C:\Users et que vous voulez aller vous placer dans C:\Users\votre_nom\Projet\Titan, vous devrez entrer cd\Users\votre_nom\Projet\Titan puis presser la touche Entrée. Pensez à remplacer votre_nom par votre nom réel de connexion.</p>
<p>Vous pourrez afficher le contenu du répertoire où vous vous trouvez en tapant la commande dir(sous Windows) ou ls(sous Mac) suivie d'une pression sur la touche Entrée.</p>
</li>

<li>Télécharger <a href="http://mirrors.standaloneinstaller.com/apache/logging/log4j/1.2.17/log4j-1.2.17.zip">Log4j</a> et placer log4j-1.2.17.jar (contenu dans le zip) dans EscapeGame-master/src </li>
<li>Taper la commande javac -cp log4j-1.2.17.jar *.java 
<p>De nouveaux fichiers .class devrait apparaître dans le dossier.</p></li>
<li>Taper la commande java -cp log4j-1.2.17.jar EscapeGameApp</li>
<p>Le jeu  se lance. Bonne partie.</p>
</ol>


<p>Merci à <a href="https://fr.wikihow.com/compiler-et-exécuter-un-programme-Java-en-ligne-de-commande">WikiHow</a> pour la trame de ce tutoriel.</p>