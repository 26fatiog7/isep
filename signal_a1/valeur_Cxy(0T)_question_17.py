# -*- coding: utf-8 -*-
"""
Created on Tue May 19 21:25:05 2020

@author: 26fatiog7
"""
import sys
from math import cos,sin

#valeur pour x(nT)
A = 2
Fe = 44000
T = 1/Fe
f0 = 1250

#valeur pour y(nT)
A1 = 5
v = -3.1415926/6


# on veut NT >> 1/f0 => N >> 1/(f0*T) 
N = 200*1/(f0 *T) 
print(N) 

k = 0

a2 = []
a = []
#def de x
for n in range (0, 1000000):        #•x(nT)
    x =  A*cos(2*3.1415926*f0*n*T)
    a.append(x)
    
aa = []
aa2 = []
#def de y
for n in range (0, 1000000):        #y(n+k * T)
    y =  A1*cos(2*3.1415926*f0*(n + k)*T + v)
    aa.append(y)

b = []
bb =[]
for nbr in range(0,  int(N)):
    # ("Cxy(",nbr ,"T)= ",(1/N)*b[0])
    
    if len(b) == 0:
        res = a[nbr] * aa[nbr + k]
        b.append(res)
        bb.append(res*1/N)
        
    else:
        res = b[0] + a[nbr] * aa[nbr + k]
        b.clear()
        b.append(res)
        bb.append(res*1/N)
    print ("Cxy(",k,"T)= ",(1/N)*b[0])
    
print ("resultat final Cxy(",k,"T)= ",(1/N)*b[0])
print(max(bb))