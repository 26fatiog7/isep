# -*- coding: utf-8 -*-
"""
Created on Wed May 20 18:33:03 2020
enonce : 
    Soit un signal échantillonné x(nT).
    On réalise la détection de la présence d’un signal utile
    dans ce signal à partir de l’estimation de la puissance
    p(nT) sur des fenêtres glissantes.
    On note xd(nT) le signal résultant de la détection.
    Lors de la phase de calibration du système, 
    on a mesuré un niveau de bruit égal à 7.10−4W 
    et un rapport signal à bruit de l’ordre de 20dB. 
    La méthode de détection est formalisée par l'équation:
    xd(nT)=1 si p(nT)≥A dBm, 0 sinon.
    Quelle est un bon choix pour A ?
@author: 26fatiog7
"""
from math import log,exp

snr = 20            #rapport sign/bruit
pb = 7*10**-4           #puissance bruit
coeff = 10                #coeff_devant_le_log
base = 10           #base du logarithme


gauche = (snr * log(base))/coeff
exp_gauche = exp(gauche)
res = exp_gauche * pb
print(res)
print("ton resultatest en Watt, mets le en dbm si necessaire :")
print("https://www.rapidtables.com/convert/power/Watt_to_dBm.html")