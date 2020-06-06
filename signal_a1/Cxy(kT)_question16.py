# -*- coding: utf-8 -*-
"""
Created on Tue May 19 21:25:05 2020

@author: 26fatiog7
"""
import sys
from math import cos,sin

#valeur pour x(nT)
A = 2.5
Fe = 16000
T = 1/Fe
f0 = 250

#valeur pour y(nT)
A1 = 5.3
v = 3.1415926/4


# on veut NT >> 1/f0 => N >> 1/(f0*T) 
N = 10 * (1/(f0 *T)) 
print(N)
k = 7
a = []
#def de x
for n in range (0, 3000000):        #•x(nT)
    x =  A*cos(2*3.1415926*f0*n*T)
    a.append(x)
    
aa = []
#def de y
for n in range (0, 3000000):        #y(n+k * T)
    y =  A1*cos(2*3.1415926*f0*(n + k)*T + v)
    aa.append(y)

b = []
bb = []
for n in range (0, int(N)):
    if len(b) == 0 :
        res = a[n] * aa[n + k]
        b.append(res)
        bb.append(res)
       
    else:
        res = b[0] + a[n] * aa[n + k]
        b.clear()
        b.append(res)
        bb.append(res)

    print (b[0]/N)
    

#rep 6.52
















