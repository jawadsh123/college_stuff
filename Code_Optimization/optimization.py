
ip = input()
flag = 0
variable_dict = dict()
op_str = ''

while ip != "}":
	temp_str = ''
	if flag == 1:

		dec = ip.split(' ')
		if dec[0] == "int" or dec[0] == "float":
			declaration = dec[1].split(",")
			temp_str += dec[0] + ' '
			for x in declaration:
				var = x.split("=")
				value = ''
				if len(var) > 1:
					variable_dict[var[0]] = var[1]
				else:
					# variable_dict[var[0]] = var[0]
					temp_str += var[0] + ","
			temp_str += " ;\n"
		elif dec[0][1] == "=":
			assignment = ip.split("=")
			exp = assignment[1]
			if len(exp) == 3:
				variable_dict[assignment[0]] = exp[0]
			else:
				temp_str += assignment[0] + "="
				for j in range(len(exp)):
					if exp[j] in variable_dict:
						temp_str += variable_dict[exp[j]]
					else:
						temp_str += exp[j]
				temp_str += "\n"
		else :
			temp_str += ip + '\n'
	elif ip == "{":
		temp_str += "{\n"
		flag = 1
	else:
		temp_str += ip + "\n"
	# print(variable_dict)
	ip = input()
	if ip == "}":
		temp_str += ip

	op_str += temp_str


print("\n\n----- Optimized Code -----\n\n")
print(op_str)
