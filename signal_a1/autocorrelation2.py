# -*- coding: utf-8 -*-
"""
Created on Tue May 19 21:25:05 2020

On calcule l'intercorrélation Cxy(kT) . 
Quelle est la première valeur positive de k 
qui donne un maximum de corrélation ?

@author: 26fatiog7
"""
import sys
from math import cos,sin
#VALEUR A CHANGER A PARTIR D ICI
#VALEURS POUR x(n)
A=1.25
f=250
Fe=16000
T = 1/Fe
#VALEURS POUR y(n)
A1 = 1.5
v=3.1415926/4
#FIN DES VALEURS A CHANGER

a2 = []
a = []
for n in range (0, 1000):
    x =  A*cos(2*3.1415926*f*n*T)
    a.append(x)
    a2.append(x)

aa = []
aa2 = []
  
for n in range (0, 1000):
    x =  A*cos(2*3.1415926*f*n*T + v)
    aa.append(x)
    aa2.append(x)

autoco = 0
for k in range(1, len(a)):
   b = []
   nbr = 0
   kk = k
   print ("interco= ", autoco, "k= ", k-1)
   while nbr < len(a)//2-1 & k+kk < len(aa):
       res = a[nbr]
       #print("res =", res)
       #a.pop(nbr)
       #print("k=", k)
       res2 = aa[nbr + k]
       #print("res =", res, "res2 =", res2)
       #a.pop(nbr)
       nbr = nbr +1
       if len(b) == 0:
           autoco = res*res2
           b.append(autoco)
       else:
           autoco = b[0] + res*res2
           b.clear()
           b.append(autoco)
      # print(autoco)
           
   #print ("autoco= ", autoco, "k= ", k)

            

              
            
    
    
        
