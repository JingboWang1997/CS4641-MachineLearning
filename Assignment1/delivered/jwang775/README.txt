CS 4641 Assignment 1
Jingbo Wang
GTID: 903242036

Instructions for running the 5 algorithms in Weka:

================ Decision Tree ===================
—————Train—————
1.Open Weka
2.Click on the ‘Explorer’ button on the righthand side
3.Click on the ‘open file…’ button on the top left of the GUI
4.Go into the ‘datasets’ folder in the project directory
5.Go into the ‘cars’ or the ‘wines’ folder for either one of the classification problems
6.Select either ‘car-training.arff’ or ‘winequality-binary-training.arff’ (or the ones with ‘0.1’-‘0.9’ attached. They are different sizes of training data extracted from the full training set)
7.After selecting the training file, click the ‘open’ button 
8.Click the ‘Classify’ button on the top of the GUI
9.Click the ‘Choose’ button on the top region of the GUI
10.Go into ‘trees’ folder and select ‘J48’
11.Click on the white text box on the right side of the ‘Choose’ button
12.Change the hyper-parameters (‘confidenceFactor’) to preferred values
13.When finished, click the ‘OK’ button in the bottom
14.On the left side of the GUI, Select ‘Use training set’ to train without cross validation or select ‘Cross-validation’ to train with cross validation
15.If training with cross-validation, the number in the ‘Folds’ text box can be edited
16.Click the ‘Start’ button to start
17.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center of the GUI) shows all the information including accuracy/ error rate, time taken, confusion matrix, etc.
—————Test—————
18.On the left side of the GUI, select ‘Supplied test set’
19.Click the ‘Set…’ button
20.Click the ‘Open file…’ button
21.Select the ‘car-test.arff’ or ‘winequality-binary-test.arff’ file and click the ‘Open’ button
22.Click the ‘Close’ button
23.Click the ‘Start’ button
24.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center and right side of the GUI) shows all the information regarding this test (accuracy/ error rate, time taken, confusion matrix, etc.)


================ Neural Networks ===================
—————Train—————
1.Open Weka
2.Click on the ‘Explorer’ button on the righthand side
3.Click on the ‘open file…’ button on the top left of the GUI
4.Go into the ‘datasets’ folder in the project directory
5.Go into the ‘cars’ or the ‘wines’ folder for either one of the classification problems
6.Select either ‘car-training.arff’ or ‘winequality-binary-training.arff’ (or the ones with ‘0.1’-‘0.9’ attached. They are different sizes of training data extracted from the full training set)
7.After selecting the training file, click the ‘open’ button 
8.Click the ‘Classify’ button on the top of the GUI
9.Click the ‘Choose’ button on the top region of the GUI
10.Go into ‘functions’ folder and select ‘MultilayerPerceptron’
11.Click on the white text box on the right side of the ‘Choose’ button
12.Change the hyper-parameters (‘hiddenLayers’(a = number of attributes/2 and the layers are comma separated), ‘learningRate’, ‘momentum’ and ’trainingTime’) to preferred values
13.When finished, click the ‘OK’ button in the bottom
14.On the left side of the GUI, Select ‘Use training set’ to train without cross validation or select ‘Cross-validation’ to train with cross validation
15.If training with cross-validation, the number in the ‘Folds’ text box can be edited. (Change to 3 so it doesn’t take a long time)
16.Click the ‘Start’ button to start
17.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center of the GUI) shows all the information including accuracy/ error rate, time taken, confusion matrix, etc.
—————Test—————
18.On the left side of the GUI, select ‘Supplied test set’
19.Click the ‘Set…’ button
20.Click the ‘Open file…’ button
21.Select the ‘car-test.arff’ or ‘winequality-binary-test.arff’ file and click the ‘Open’ button
22.Click the ‘Close’ button
23.Click the ‘Start’ button
24.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center and right side of the GUI) shows all the information regarding this test (accuracy/ error rate, time taken, confusion matrix, etc.)


================ Boosted Decision Tree ===================
—————Train—————
1.Open Weka
2.Click on the ‘Explorer’ button on the righthand side
3.Click on the ‘open file…’ button on the top left of the GUI
4.Go into the ‘datasets’ folder in the project directory
5.Go into the ‘cars’ or the ‘wines’ folder for either one of the classification problems
6.Select either ‘car-training.arff’ or ‘winequality-binary-training.arff’ (or the ones with ‘0.1’-‘0.9’ attached. They are different sizes of training data extracted from the full training set)
7.After selecting the training file, click the ‘open’ button 
8.Click the ‘Classify’ button on the top of the GUI
9.Click the ‘Choose’ button on the top region of the GUI
10.Go into ‘meta’ folder and select ‘AdaBoostM1’
11.Click on the white text box on the right side of the ‘Choose’ button
12.Click the ‘Choose’ button next to the text ‘classifier’
13.Select ‘J48’ from ‘trees’ folder
14.Click on the white text box on the right side of the ‘Choose’ button for ‘J48’
15.Change the hyper-parameters of the J48 decision tree (‘confidenceFactor’) to preferred values
16.When finished, click the ‘OK’ button in the bottom
17.Change the hyper-parameters of the AdaBoostM1 boosted decision tree (‘numIterations’) to preferred values
18.When finished, click the ‘OK’ button in the bottom
19.On the left side of the GUI, Select ‘Use training set’ to train without cross validation or select ‘Cross-validation’ to train with cross validation
20.If training with cross-validation, the number in the ‘Folds’ text box can be edited.
21.Click the ‘Start’ button to start
22.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center of the GUI) shows all the information including accuracy/ error rate, time taken, confusion matrix, etc.
—————Test—————
23.On the left side of the GUI, select ‘Supplied test set’
24.Click the ‘Set…’ button
25.Click the ‘Open file…’ button
26.Select the ‘car-test.arff’ or ‘winequality-binary-test.arff’ file and click the ‘Open’ button
27.Click the ‘Close’ button
28.Click the ‘Start’ button
29.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center and right side of the GUI) shows all the information regarding this test (accuracy/ error rate, time taken, confusion matrix, etc.)


================ Support Vector Machine ===================
—————Train—————
1.Open Weka
2.Click on the ‘Explorer’ button on the righthand side
3.Click on the ‘open file…’ button on the top left of the GUI
4.Go into the ‘datasets’ folder in the project directory
5.Go into the ‘cars’ or the ‘wines’ folder for either one of the classification problems
6.Select either ‘car-training.arff’ or ‘winequality-binary-training.arff’ (or the ones with ‘0.1’-‘0.9’ attached. They are different sizes of training data extracted from the full training set)
7.After selecting the training file, click the ‘open’ button 
8.Click the ‘Classify’ button on the top of the GUI
9.Click the ‘Choose’ button on the top region of the GUI
10.Go into ‘functions’ folder and select ‘SMO’
11.Click on the white text box on the right side of the ‘Choose’ button
13.Click on the white text box on the right side of the ‘Choose’ button for ‘PolyKernel’
14.Change the hyper-parameters of the polynomial kernel function (‘exponent’) to preferred values
15.When finished, click the ‘OK’ button in the bottom
17.Click the ‘OK’ button in the bottom
18.On the left side of the GUI, Select ‘Use training set’ to train without cross validation or select ‘Cross-validation’ to train with cross validation
19.If training with cross-validation, the number in the ‘Folds’ text box can be edited
20.Click the ‘Start’ button to start
21.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center of the GUI) shows all the information including accuracy/ error rate, time taken, confusion matrix, etc.
—————Test—————
22.On the left side of the GUI, select ‘Supplied test set’
23.Click the ‘Set…’ button
24.Click the ‘Open file…’ button
25.Select the ‘car-test.arff’ or ‘winequality-binary-test.arff’ file and click the ‘Open’ button
26.Click the ‘Close’ button
27.Click the ‘Start’ button
28.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center and right side of the GUI) shows all the information regarding this test (accuracy/ error rate, time taken, confusion matrix, etc.)


================ K-Nearest Neighbors ===================
—————Train—————
1.Open Weka
2.Click on the ‘Explorer’ button on the righthand side
3.Click on the ‘open file…’ button on the top left of the GUI
4.Go into the ‘datasets’ folder in the project directory
5.Go into the ‘cars’ or the ‘wines’ folder for either one of the classification problems
6.Select either ‘car-training.arff’ or ‘winequality-binary-training.arff’ (or the ones with ‘0.1’-‘0.9’ attached. They are different sizes of training data extracted from the full training set)
7.After selecting the training file, click the ‘open’ button 
8.Click the ‘Classify’ button on the top of the GUI
9.Click the ‘Choose’ button on the top region of the GUI
10.Go into ‘lazy’ folder and select ‘IBk’
11.Click on the white text box on the right side of the ‘Choose’ button
12.Change the hyper-parameters (‘KNN’) to preferred values
13.When finished, click the ‘OK’ button in the bottom
14.On the left side of the GUI, Select ‘Use training set’ to train without cross validation or select ‘Cross-validation’ to train with cross validation
15.If training with cross-validation, the number in the ‘Folds’ text box can be edited
16.Click the ‘Start’ button to start
17.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center of the GUI) shows all the information including accuracy/ error rate, time taken, confusion matrix, etc.
—————Test—————
18.On the left side of the GUI, select ‘Supplied test set’
19.Click the ‘Set…’ button
20.Click the ‘Open file…’ button
21.Select the ‘car-test.arff’ or ‘winequality-binary-test.arff’ file and click the ‘Open’ button
22.Click the ‘Close’ button
23.Click the ‘Start’ button
24.After the ‘Status’ in the bottom shows ‘OK’, the ‘Classifier output’ screen (the center and right side of the GUI) shows all the information regarding this test (accuracy/ error rate, time taken, confusion matrix, etc.)