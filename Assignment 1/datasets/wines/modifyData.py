import csv
from collections import Counter
from random import shuffle 

# load in data
f = open("original-dataset/winequality-white.csv")
original_file = f.read()
rowsplit_data = original_file.splitlines()
examples = [rows.split(',') for rows in rowsplit_data] # [[],[],[],...,[]]
attributes = [attribute.split(';') for attribute in examples.pop(0)] # [['""', '""', '""',...,'""']]

# reformat attributes, remove ""
for i in range(len(attributes[0])):
	attribute = attributes[0][i]
	attributes[0][i] = attributes[0][i][1:len(attribute)-1]
# attributes = [['','','',...,'']]

reformat = [example[0].split(';') for example in examples] # [['','',...,''],[],[],...,[]]
labels = []
# rewrite quality class to binary
for row in reformat:
	quality = row[-1]
	if (int(quality) < 6):
		qualityString = "not good"
	else:
		qualityString = "good"
	labels.append(qualityString)
	row[-1] = qualityString

# analyze labels
count = Counter(labels)
good_count = count['good']
notgood_count = count['not good']

# construct training_list(80%) and test_list(20%)
# distribution of the two labels should stay consistent in these lists 
training_list = []
test_list = []
good_test_count = int(good_count * 0.2)
notgood_test_count = int(notgood_count * 0.2)
good_current_count = 0
notgood_current_count = 0
shuffle(reformat)
for row in reformat:
	if row[-1] == 'good' and good_current_count < good_test_count:
		test_list.append(row)
		good_current_count += 1
	elif row[-1] == 'not good' and notgood_current_count < notgood_test_count:
		test_list.append(row)
		notgood_current_count += 1
	else:
		training_list.append(row)

# write to a new files with new labels
# with open('winequality-binary-training.csv', mode='w') as file:
#     writer = csv.writer(file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
#     writer.writerow(attributes[0])
#     for row in training_list:
#     	writer.writerow(row)
# with open('winequality-binary-test.csv', mode='w') as file:
#     writer = csv.writer(file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
#     writer.writerow(attributes[0])
#     for row in test_list:
#     	writer.writerow(row)

remaining_list = training_list
current_list = []
for i in range(10):
	temp_list = []
	print "looping", i
	size = float(i + 1)/float(10)
	good_need = int(good_count * 0.8 * 0.1)
	notgood_need = int(notgood_count * 0.8 * 0.1)
	good_current_count = 0
	notgood_current_count = 0
	print len(remaining_list)
	if (i < 9):
		for row in remaining_list:
			if row[-1] == 'good' and good_current_count < good_need:
				current_list.append(row)
				good_current_count += 1
			elif row[-1] == 'not good' and notgood_current_count < notgood_need:
				current_list.append(row)
				notgood_current_count += 1
			else:
				temp_list.append(row)
	else:
		current_list += remaining_list
	# temp list has the unused, current_list should be written to a file and be appended on the next iteration
	remaining_list = temp_list
	with open('winequality-binary-training-' + str(size) + '.csv', mode='w') as file:
	    writer = csv.writer(file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
	    writer.writerow(attributes[0])
	    for row in current_list:
	    	writer.writerow(row)


