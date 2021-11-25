import os

n = int(input("Number of dir: "))
path = input("New dir path: ")
comm = "mkdir " + path
i = 0
nameList = []

while i < n:
	nome = input("Name: ")
	nameList.append(nome)
	i = i + 1

i = 0
while i < n:
	os.system(comm+"\\"+nameList[i])
	i = i + 1