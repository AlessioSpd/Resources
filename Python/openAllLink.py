#!/usr/bin/python
import sys
import os
import time

path = str(input("Path: "))
mode = str(input("Mode (a for anonymous, n for normal): ")).lower()
print (mode)
if mode != 'a' and mode != 'n':
	print("Modalità di apertura non corretta, ripetere")
	exit()

f = open(path, "r")

cmd = "start msedge.exe"

links = []

for line in f:
	cmd = cmd + f' "{str(line)}"'
	cmd = cmd.replace('\n', "")

if 'a' == mode:
	cmd += f' -inprivate'

# print(cmd)
os.system(cmd)