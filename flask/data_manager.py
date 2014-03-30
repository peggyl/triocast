FILENAME = 'data.txt'

def read():
	people = []
	with open(FILENAME, 'r') as f:
		for line in f:
			if line[0] == '#':
				continue
			name, number, sms, call = line.split(",")
			obj = {
				'name': name,
				'number': number,
				'sms': sms,
				'call': call
			}
			people.append(obj)
	return people


def add(name, number, sms, call):
	print 'adding to file'
	with open(FILENAME, 'a') as f:
		s = ','.join([name, number, sms, call]) + '\n'
		f.write(s)
		return True