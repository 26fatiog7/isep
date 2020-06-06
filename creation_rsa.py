# -*- coding: utf-8 -*-
"""
Created on Sun May 02 15:29:33 2020
@author:26fatiog7
chiffrement rsa avec des valeurs pas trop grandes car capacités de calculs limités (valeurs max testés environ 100 milliard max pour p et q car pas trouvé de nombres plus grand sur google)
Si la taille du message une fois chffré est supérieur à n alors écrire un plus petit message ou choisir p et q plus grand capacité tres tres limite
Code à modifier pour le rendre plus performant
"""
import sys
from Crypto.Util.number import inverse

def nb_premier():
    nb_p = int(input ("entrez votre nombre premier p : "))
    est_premier(nb_p)
    print ("nombre p choisi :", nb_p)
    nb_q = int(input("entrez votre nombre premier q : "))
    est_premier(nb_q)
    est_different(nb_p , nb_q)
    global n
    n = nb_q * nb_p
    print ("votre nombre n est :", n)
    global len_n                                # à partir d'ici affiche taille mess max à entrer
    len_n = len(str(n))
    if (len_n % 2) == 0:
        print(" ")
    else:
        len_n = len_n +1
   # print (len_n)
    global lim_mess
    lim_mess = (len_n//2) - 2
    print("taille mess max = ", lim_mess)
    print (" ")
    calcul_phi(nb_p, nb_q, n)
    
    
def est_premier(a):
    i = 2
    while i*i <= a and a%i != 0:
        i = i+1
    if i*i > a and a > 1:
        print ("OK, c'est un nombre premier")
    else:
        print("ERREUR, ce n'est pas un nombre premier")
        sys.exit("vous avez causé une erreur") #on doit relancer le programme

def est_different(b,r):
    if b == r:
        print ("ERREUR vos deux nombres sont identiques")
        sys.exit("vous avez causé une erreur")
    else:
        print ("OK, nombre accepté")
        print ("  ")

def calcul_phi(p, q, n):
    phi = (p-1) * (q-1)
    print("votre nombre phi est : ", phi)
    print ("  ")
  #  for e in range (phi//2, phi):           si on veut pas  prendre e=65537, permet d'en afficher mais prend du temps
    #    if counter == 100                   il faut donc réduire l'intervalle (phi//2, phi) en fonction de la taille de p et q
     #       break                           
      #  else:
       #     if pgcd(e, phi, n) == 1:
        #        print ("les nombres e possibles sont : ", e)
         #       counter += 1
          #  else :
           #     e = e+1
                
    
    ###############################
    global nb_e
    nb_e = 65537
   # nb_e = int(input("entrez votre nombre e parmi les propositions qui vous ont été faites : "))
    print("clé publique = :", "(", n, "," , nb_e, ")")
    print ("")
    print("veuillez patienter......")
    print ("  ")
    calcul_d(nb_e, phi, n)
    
def pgcd(a,b, n):
    if a % b == 0:  return b
    else: return pgcd(b, a % b, n)
    print ("Le PGCD de ",a," et de ",b," est ",pgcd(a,b))

def calcul_d(nb_e, phi, n):
    d = inverse (nb_e, phi)
    print ("votre nombre d est : ", d)
    print("votre clé privée est = " ,"(", n, ", ",  d , ")")
    
def cryptage(mess, nb_e, n):
    c = pow(mess, nb_e, n)
    print("ciphertext = ",c) 
   
###########################################################################
###########################################################################
def choix2():
    global ciphertext
    global n
    global d
    ciphertext = int(input ("entrez votre nombre ciphertext : "))
    #print ("message à déchiffrer :", ciphertext)
    n = int(input("entrez votre nombre n : "))
    print("nombre n = ", n)
    d = int(input("entrez votre nombre d : "))
    print("nombre d = " , d)
    mess_dec = pow(ciphertext, d, n)
    print('mess en decimal = ', mess_dec )
    mess_hex = hex(mess_dec)
    mess_hex = mess_hex[2:]
    print('mess en hex = ', mess_hex)
    
    while True : 
        try :
            mess_fin  = bytearray.fromhex(mess_hex).decode()
            print('message final = ', mess_fin)
            break
        except ValueError :
            sys.exit("vous avez causé une erreur")
            print("Oops ! es-tu sûr de tes valeurs ?")
           
 
def choix3():
    nb_n = int(input("entrez votre nombre n: "))
    print("votre nombre n est : ", nb_n)
    nb_e = int(input("entrez votre nombre e : "))
    print("votre clé privée est donc : (",nb_n,",", nb_e,")" )
    message_code = input("Entrez un message à coder :  ")
    print("votre message à envoyer est : ", message_code)
    message_code = message_code.encode("utf-8").hex()
    print("message en hex",message_code)
    mess_code =int (message_code, 16)
    print("message en decimal",mess_code)
    if (mess_code > nb_n) : 
        print("vous avez du faire une erreur car les dimensions sont incorrectes")
        sys.exit("vous avez causé une erreur")
    else : 
        cryptage(mess_code,nb_e, nb_n)
    
question = ""
while question != "1" and question != "2" and question != "3":
    question = input("Voulez-vous creer une clé RSA pour coder un message ou décoder un message à l'aide d'une clé ou coder avec une clé déjà existante ? Rentrez 1 ou 2 ou 3 \n")
    question = question.lower()
if (question == "1" ) :
    nb = nb_premier()
    message = input("Entrez un message à coder :  ")
    print("message en clair : ", message)
    message = message.encode("utf-8").hex()
    print("message en hex",message)
    mess =int (message, 16)
    print (len(str(mess)))
    print("message en decimal",mess)
    cryptage(mess, nb_e, n)
    if (mess < n):
        print("message bien chiffré")
    else:
        print("message trop long")
    
if (question == "2") :
    c2 = choix2()
    
if (question == "3") :
    c3 = choix3()
    