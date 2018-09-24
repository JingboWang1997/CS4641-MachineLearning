There are 1728 instances in total. 80% of the total instances are split into the training set and the remaining 20% are split into the test set, which makes 1384 instances in the training set and 344 instances in the test set. The class distributions in the training and test sets are about the same. However, each class is not evenly distributed in a set: (diagram for class distribution is on page 3)

Class distribution in the training set:   
Class       #instances         %
---------------------------------------
unacc           968         (69.942%) 
acc               308         (22.254%) 
good             56           (4.046%) 
v-good          52           (3.757%)

Class distribution in the test set:
Class       #instances         %
---------------------------------------
unacc           242         (70.349%) 
acc               76           (22.093%) 
good             13           (3.779%) 
v-good          13           (3.779%)


There were originally two datasets with one about red wines and the other one about white wines. These two datasets are related to red and white variants of the Portuguese "Vinho Verde" wine. For the purpose of this project, I am only using the dataset for the white wines because the white wine dataset is about 3 times larger than the red wine dataset. Therefore I would expect more accurate and reliable results from the white wine dataset than the red wine dataset.

There are 4898 instances in total. 80% of the total instances are split into the training set and the remaining 20% are split into the test set, which makes 3919 instances in the training set and 979 instances in the test set. The class distributions in the training and test sets are about the same. However, each class is not evenly distributed in a set: (diagram for class distribution is on page 3)

Class distribution in the training set:   
Class         #instances         %
---------------------------------------
good             2607         (66.522%)
not good      1312          (33.478%)

Class distribution in the test set:
Class           #instances         %
---------------------------------------
good                651           (66.496%)
not good          328           (33.5%)

The reason I converted the classes from scores of 0 to 10 (inclusive) to the binary classes of ‘good’ and ‘not good’ is that there is 0 instance with quality score of 0, 1, 2 and 10. There is a huge number of instances at the quality score of 6, which is around 44.875%, and there are barely any instances on the lower or higher end of the quality spectrum (5 instances at quality score 9 and 20 instances at score 3). To fix the extremely unbalanced data and 4 unnecessary classes/labels, I split the instances with the quality score 6. With this modification, the class distribution is way more balanced than before, and it removed a lot of unnecessary details in this dataset.