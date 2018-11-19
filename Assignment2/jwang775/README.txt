CS4641 Assignment 2
Jingbo Wang
GTID: jwang775

Modification based on ABAGAIL library:
- Extended test and training data split functionality in wineTest.java
- Implemented random restarts functionality for randomized hill climbing in wineTest.java

Code:
- The code written for this assignment is in the directory “jwang775/ABAGAIL-master/src/assignment2”
- two .txt data files containing training data and test data
- “wineTest.java” is for the neural network weights optimization problem
- “TravelingSalesmanTest.java” and “FlipFlopTest.java” are for the other two optimization problems

Instructions for running:
(Make sure Java 8 and ant is properly installed)
- go in the “ABAGAIL-master” directory (“cd ABAGAIL-master”)
- run “ant -v” to build the project
- run “java -cp ABAGAIL.jar assignment2.wineTest” to run the neural network weights optimization problem
  - In the source code in file “wineTest.java”, the lines following the comment “param tuning” have all the parameters that I tried to tune during this process
  - Feel free to change the parameter values before running the file, but every time the file is modified, the project needs to be rebuilt by running “ant -v”
- run “java -cp ABAGAIL.jar assignment2.TravelingSalesmanTest” to run the traveling salesman problem
- run “java -cp ABAGAIL.jar assignment2.FlipFlopTest” to run the flip flop problem

 

