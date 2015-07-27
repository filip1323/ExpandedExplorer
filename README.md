# ExpandedExplorer

ExpandedExplorer is a simple Java program that allows user to explore directories and open them or run executables in Windows OS.
It was written to improve skills in Object-Oriented programming, as also to design first, implement later.
All UML diagrams are saved in ExpandedExplorerBluePrint.png and ExpandedExplorerBluePrint.uxf (UMLet file extension).

Main feature is path auto-completion. If there is only one matching file in current directory, then program assumes it is the wanted one. It works recursively, so if you are in main dir, like `C:/maindir` and there is only one file 5 levels down:`C:/maindir/f/o/o/b/a/r.exe`, then it changes path to it automatically.

App uses mgarin/weblaf gui library.
http://mvnrepository.com/artifact/de.sciss/weblaf/1.27
