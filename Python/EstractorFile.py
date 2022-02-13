import os, shutil, pathlib

inputPath = str(input("Input path:"))
dirList = os.listdir(inputPath)

def estrai(dirPath, typeOfFile):
	arr = os.listdir(dirPath)
	print(arr)

	for file in arr:
		if file.endswith(typeOfFile):
			shutil.move(dirPath + "\\" + file, inputPath)
	print()
def main():

	typeOfFile = str(input("Estensione dei file: "))
	for directory in dirList:
		if directory == "desktop.ini" or directory.endswith(".mp4") or directory.endswith(".mkv"):
			continue

		# questo è il path completo della cartella
		dirPath = inputPath + "\\" + directory

		#per ogni cartella estraggo il file mp4
		estrai(dirPath, typeOfFile)
	#C:\Users\pamui\Downloads

main()