import os, shutil
import os
import pathlib

def menu():
	print("1. Numbered")
	print("2. For each file here")
	print("3. Help")
	print("Any other key to exit")

	return int(input())

def numbered(myPath : str):
	nDir = int(input("\nHow many dir? "))
	n = 1

	while n <= nDir:
		os.makedirs(myPath + "\\" + str(n))
		n = n + 1
	
	return True

def filed(myPath : str):
	#input del tipo di file che cerco
	typeOfFile = str(input("\nWhich type of file? "))

	#prendo una lista dei file con l'estensione scelta
	arr = os.listdir(myPath)

	myFile = []

	#prendo tutti i nomi dei file che hanno l'estensione inserita
	for file in arr:
		if file.endswith(typeOfFile):
			myFile.append(file)

	#creo le cartelle con i nomi dei file
	numero = 0
	while numero < len(myFile):
		os.makedirs(myPath + "\\" + myFile[numero][:-4])
		numero = numero + 1

	#sposto ogni file nella sua omonima cartella
	numero = 0
	while numero < len(myFile):
		shutil.move(myPath + "\\" + myFile[numero], myPath + "\\" + myFile[numero][:-4])
		numero = numero + 1

	return True

def takePath():
	myPath = str(input("Input path:"))
	if myPath == "this":
		myPath = os.path.dirname(os.path.realpath(__file__))
	return myPath

def main():
	myPath = takePath()
	exit = False
	while exit == False:
		ris = menu()
		if ris == 1:
			exit = numbered(myPath)
		elif ris == 2:
			exit = filed(myPath)
		elif ris == 3:
			print("\nSe premi 1, puoi creare N cartelle numerate,\naltrimenti, se premi 2, puoi creare N cartella da una specifica estensione dei file, copiando il suo nome e spostandoli al suo interno\n")
		else:
			exit()

	input("\nAll good. Press Enter to continue...")

main()