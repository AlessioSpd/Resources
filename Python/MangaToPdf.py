from PIL import Image
import os

myPath = os.path.dirname(os.path.realpath(__file__))			#acquisisco il mio path

capList = os.listdir(myPath)									            #acquisisco la lista dei capitoli
capList.pop()												                      #elimino l' ultimo (sarebbe lo script, provvisorio)


image = []

for capName in capList:
	capPath = myPath + "\\" + capName							          #...\Manga\CapitoloX
	imgListName = os.listdir(capPath)							          #...001.jpg, 002.jpg, ...

	for imgName in imgListName:
		image.append(Image.open(capPath + '\\' + imgName))		# Image.open(r'D:\Manga\CapitoloX\001.jpg')....

toSave = []

for img in image:
	toSave.append(img.convert('RGB'))

im1 = toSave[0]
del toSave[0]

im1.save(r'C:\Users\pamui\OneDrive\Desktop\test.pdf',save_all=True, append_images=toSave)
