
ip = ''
in_macro = 0

set_MNT = False 
pass_1 = []
pass_2 = []
MDT = []
MNT = {}
ALA_1 = []
ALA_2 = []

def enterMDT(ip):

	if ip != "MEND":
		m_name, m_arg = ip.split(' ')
		arg_list = m_arg.split(',')
		table_entry = m_name + " "

		for i, arg in enumerate(arg_list):
			if arg[0] == '&':
				idx = enter_ALA_1(arg)
				if i == 0:
					table_entry += "#" + str(idx)	
				else:
					table_entry += ",#" + str(idx)
			else:
				if i == 0:
					table_entry += arg
				else:
					table_entry += "," + arg
	else:
		table_entry = "MEND"
	MDT.append(table_entry)


def enterMNT(ip):
	m_name, m_arg = ip.split(" ")
	MNT[m_name] = len(MDT)-1

def enter_ALA_1(arg):
	
	for i in range(len(ALA_1)):
		if ALA_1[i] == arg:
			return i
	ALA_1.append(arg)
	return len(ALA_1)-1

def expandMacro(idx, m_arg):

	# updating ALA 2
	mdt_val = MDT[idx]
	mdt_args = mdt_val.split(" ")[1]
	mdt_args = mdt_args.split(",")
	m_arg = m_arg.split(",")

	for i in range(len(mdt_args)):

		ala_2_idx = int(mdt_args[i][1])
		ALA_2[ala_2_idx] = m_arg[i]

	idx += 1
	while MDT[idx] != "MEND":
		temp_entry = ""
		# print(MDT[idx])
		entry_name, entry_args = MDT[idx].split(" ")
		entry_args = entry_args.split(",")
		temp_entry += entry_name + " "
		for i, x in enumerate(entry_args):
			if x[0] == "#":
				if i == 0:
					temp_entry += ALA_2[int(x[1])]
				else:
					temp_entry += "," + ALA_2[int(x[1])]
			else:
				if i == 0:
					temp_entry += x
				else:
					temp_entry += "," + x
		pass_2.append(temp_entry)
		idx += 1


while ip != "END":
	ip = input()
	if ip == "MACRO":
		in_macro = 1
		set_MNT = True
		continue

	if in_macro == 0:
		pass_1.append(ip)
	else:
		if ip != "MEND":
			enterMDT(ip)
			if set_MNT:
				enterMNT(ip)
				set_MNT = False
		else:
			in_macro = 0
			enterMDT(ip)

# making ALA 2
ALA_2 = ALA_1


for x in pass_1:
	try:
		if x != "START" and x != "END":
			m_name, m_arg = x.split(" ")
			if m_name in MNT:
				idx = MNT[m_name]
				expandMacro(idx, m_arg)
			else:
				pass_2.append(x)
		else:
			pass_2.append(x)
	except:
		pass_2.append(x)



print("\nMDT Table:")
for i, x in enumerate(MDT):
	print(i, x)

print("\nMNT Table:")
for i, x in enumerate(MNT):
	print(i, x, MNT[x])
print("\nALA_1 Table:")
for i, x in enumerate(ALA_1):
	print(i, x)
print("\nPass 1:")
for x in pass_1:
	print(x)
print("\nPass 2:")
for x in pass_2:
	print(x)

