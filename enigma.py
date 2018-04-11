# -*- coding: utf-8 -*-
from copy import copy #从copy中引入copy函数
reflector=[18, 16, 12, 15, 19, 13, 23, 20, 9, 8, 21, 14, 2, 5, 11, 3, 1, 22, 0, 4, 7, 10, 17, 6, 25, 24]  
raw_cog=[[6, 10, 23, 9, 19, 2, 21, 1, 7, 24, 0, 17, 15, 3, 8, 4, 14, 12, 16, 25, 11, 18, 22, 20, 13, 5]  
,[0, 7, 1, 10, 19, 5, 9, 18, 4, 23, 2, 20, 15, 24, 22, 8, 12, 25, 6, 13, 3, 11, 21, 17, 16, 14]  
,[23, 2, 4, 13, 14, 21, 12, 0, 9, 16, 5, 17, 20, 22, 10, 24, 7, 3, 25, 8, 15, 6, 19, 18, 11, 1]]  
def shift(List,n):
	return List[n:]+List[:n]
class Cog:
	def __init__(self,list):
		self.list=copy(list)
	def rotate(self):#逆时针旋转
		self.list=shift(self.list,1)
	def printList(self):#????
		for i in self.list:
			print(i,)
	def nextNum(self,num):# 正向加密
		return self.list[num]
	def lastNum(self,num):# 反射回来后的加密方式
		return self.list.index(num)
class Enigma:
	def __init__(self):
		global reflector
		self.reflector=copy(reflector)
		self.lenCipher=0#这个变量在转动转子的时候会用到
		self.cogList=[]
		for i in range(3):
			self.cogList.append(Cog(raw_cog[i]))#转子和反射器都会用到copy函数
	def printEnigma(self):
		print('print reflector')
		for i in self.reflector:
			print(i,)
		print('print cogs')
		for i in range(3):
			self.cogList[i].printList()
			print 
	def deChar(self,theChar):
		num=ord(theChar)-ord('a')
		for i in range(3):
			num=self.cogList[i].nextNum(num)
		num=self.reflector[num]
		for i in range(2,-1,-1):
			num=self.cogList[i].lastNum(num)
		return chr(ord('a')+num)
	def encode(self,theString):
		str=theString.lower()
		cipher=""
		for i in str:
			if i.isalpha():
				cipher+=self.deChar(i)
				self.lenCipher+=1
				for j in range(3):
					if self.lenCipher>=26**j and self.lenCipher%(26**j)==0:
						self.cogList[j].rotate()
		return cipher
	
def main():
	plain=input('Enter the string that you want to entryption:')
	test=Enigma()
	tmp=test.encode(plain)
	print(tmp)
	
if __name__=='__main__':
	main()