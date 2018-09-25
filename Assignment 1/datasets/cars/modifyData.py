import csv
from collections import Counter
from random import shuffle 

# load in data
f = open("original-dataset/car.csv")
original_file = f.read()
rowsplit_data = original_file.splitlines()
examples = [rows.split(',') for rows in rowsplit_data] # [[],[],[],...,[]]
attributes = ['buying', 'maint', 'doors', 'persons', 'lug_boot', 'safety', 'quality']

# analyze labels
labels = []
for row in examples:
	quality = row[-1]
	labels.append(quality)
count = Counter(labels)
unacc_count = count['unacc']
acc_count = count['acc']
good_count = count['good']
vgood_count = count['vgood']

# construct training_list(80%) and test_list(20%)
# distribution of the two labels should stay consistent in these lists 
training_list = []
test_list = []
unacc_test_count = int(unacc_count * 0.2)
acc_test_count = int(acc_count * 0.2)
good_test_count = int(good_count * 0.2)
vgood_test_count = int(vgood_count * 0.2)

unacc_current_count = 0
acc_current_count = 0
good_current_count = 0
vgood_current_count = 0
shuffle(examples)
for row in examples:
	if row[-1] == 'unacc' and unacc_current_count < unacc_test_count:
		test_list.append(row)
		unacc_current_count += 1
	elif row[-1] == 'acc' and acc_current_count < acc_test_count:
		test_list.append(row)
		acc_current_count += 1
	elif row[-1] == 'good' and good_current_count < good_test_count:
		test_list.append(row)
		good_current_count += 1
	elif row[-1] == 'vgood' and vgood_current_count < vgood_test_count:
		test_list.append(row)
		vgood_current_count += 1
	else:
		training_list.append(row)

# write to a new files with new labels
# with open('car-training.csv', mode='w') as file:
#     writer = csv.writer(file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
#     writer.writerow(attributes)
#     for row in training_list:
#     	writer.writerow(row)
# with open('car-test.csv', mode='w') as file:
#     writer = csv.writer(file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
#     writer.writerow(attributes)
#     for row in test_list:
#     	writer.writerow(row)

remaining_list = training_list
current_list = []
for i in range(10):
	temp_list = []
	print "looping", i
	size = float(i + 1)/float(10)
	unacc_need = int(unacc_count * 0.8 * 0.1)
	acc_need = int(acc_count * 0.8 * 0.1)
	good_need = int(good_count * 0.8 * 0.1)
	vgood_need = int(vgood_count * 0.8 * 0.1)
	unacc_current_count = 0
	acc_current_count = 0
	good_current_count = 0
	vgood_current_count = 0
	print len(remaining_list)
	if (i < 9):
		for row in remaining_list:
			if row[-1] == 'unacc' and unacc_current_count < unacc_need:
				current_list.append(row)
				unacc_current_count += 1
			elif row[-1] == 'acc' and acc_current_count < acc_need:
				current_list.append(row)
				acc_current_count += 1
			elif row[-1] == 'good' and good_current_count < good_need:
				current_list.append(row)
				good_current_count += 1
			elif row[-1] == 'vgood' and vgood_current_count < vgood_need:
				current_list.append(row)
				vgood_current_count += 1
			else:
				temp_list.append(row)
	else:
		current_list += remaining_list
	# temp list has the unused, current_list should be written to a file and be appended on the next iteration
	remaining_list = temp_list
	with open('car-training-' + str(size) + '.csv', mode='w') as file:
	    writer = csv.writer(file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
	    writer.writerow(attributes)
	    for row in current_list:
	    	writer.writerow(row)
