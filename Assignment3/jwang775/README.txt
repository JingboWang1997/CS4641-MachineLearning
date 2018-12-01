CS4641 Assignment 3
Jingbo Wang
GTID: jwang775

===Data Files===
test and training files for cars and wine
post PCA datafilee for cars and wine 
(0.99 variance for cars, 0.95 variance for wine)

===Running Instruction===
Open Weka
Open Explorer
Under the ‘Preprocess’ tab, click ‘Open file…’ to load in .arff data files

——— Clustering ———
Go to the ‘Cluster’ tab
Click ‘Choose’
- Select ‘SimpleKMeans’ for K-means
- Select ‘EM’ for GMM-EM
Click on the text box on the right of the ‘Choose’ button
Modify the parameters here
After parameters are selected, Click ‘OK’
Select the radio buttons on the left for 
- training (‘Use training set’)
- test (‘Supplied test set’)
 - Click ’Set…’ button to set the test file
- training with class assigned to clusterings
Click the ‘Ignore attributes’ button to ignore the label
Click the ‘Start’ button to start the training or test.
Right click on the new entry in the ‘Result list’ space
Select ‘Visualize cluster assignments’ to visualize the clusterings

——— PCA ———
Go to the ‘Select attributes’ tab
Click ‘Choose’
Select ‘PrincipalComponents’
(Select ‘Yes’ if there is an alert popup)
Click on the text box on the right of the ‘Choose’ button
Modify the parameters here
After parameters are selected, Click ‘OK’
Click the ‘Start’ button to start the training or test. 
Right click on the new entry in the ‘Result list’ space
Select ‘Visualize transformed data’ to visualize the transformed space
Select ‘Save transformed data…’ to save the new transformed dataset

——— Neural Net after preprocessing ———
Go to the ‘Classify’ tab
Click ‘Choose’
Select ‘FilteredClassifier’ under ‘meta’ folder
Click on the text box on the right of the ‘Choose’ button
Modify the parameters here
- Click the ‘Choose’ button on the right of the ‘classifier’ text
  - Select ‘MultilayerPerceptron’ under ‘functions’ folder
  - Modify neural net parameters in the same way
- Click the ‘Choose’ button on the right of the ‘filter’ text
  - Select ‘ClusterMembership’ under ‘unsupervised/attribute’ folder for clusterings
    — Choose the specific clustering algorithms by modifying the parameters in the ClusterMembership
    — Choose the ‘EM’ under ‘MakeDensityBasedCluster’ for attributes with probabilities
  — Select ‘PrincipalComponents’ under the same folder for PCA  
After parameters are selected, Click ‘OK’
Select the radio buttons on the left for 
- training (‘Use training set’)
- test (‘Supplied test set’)
 - Click ’Set…’ button to set the test file
Click the ‘Start’ button to start the training or test. 


 

