# -*- coding: utf-8 -*-
"""
Created on Mon Jan 11 18:38:43 2021

@author: gusta
"""
import tkinter
import hashlib
import pyodbc
import time
import os
#pyinstaller --onefile -w aa.py
#pyinstaller --onefile -w formation_2021_test_1.py
 
#####################################
    #ADD USER FORMATION#
#####################################
def add_formation_user():
    global page_add_formation_user
    page_add_formation_user = tkinter.Tk()
    page_add_formation_user.geometry('900x900')
    global canvas_add_formation_user
    canvas_add_formation_user = tkinter.Canvas(page_add_formation_user,width=taille_x, height=taille_y, bg='red4')
    canvas_add_formation_user.pack()
    
    #TITRE
    mess_titre_recherche = "recherche de votre collaborateur"
    label_tire_recherche = tkinter.Label(page_add_formation_user, text=mess_titre_recherche, bg='black', fg='white', font=("Courier", 10))
    canvas_add_formation_user.create_window(WIDTH/3, 10, anchor=tkinter.NW, window=label_tire_recherche)    
    
    #CHAMPS POUR RECUP LE COLLABORATEUR
    recherche_contrat="entrez le type de contrat du collaborateur"
    label_zone_recherche_contrat = tkinter.Label(page_add_formation_user, text=recherche_contrat, bg='grey' , font=("Courier", 10))
    canvas_add_formation_user.create_window(WIDTH/3 -taille_mess, 70, anchor=tkinter.NW, window=label_zone_recherche_contrat)
    
    OptionListContrat = ["CDI", "CTT","STAGIAIRE"]
    global variable_search_contrat
    variable_search_contrat = tkinter.StringVar(page_add_formation_user)
    variable_search_contrat.set(OptionListContrat[0])

    global opt1
    opt1 = tkinter.OptionMenu(page_add_formation_user, variable_search_contrat, *OptionListContrat)
    enter_opt1 = canvas_add_formation_user.create_window(WIDTH/3 -taille_mess, 95, anchor=tkinter.NW, window=opt1)

    #BOUTON LANCE RECHERCHE
    label_bouton_recherche_user = tkinter.Button(page_add_formation_user, text="recherche des collaborateur", bg='white', command=sql_recherche_user)
    canvas_add_formation_user.create_window(WIDTH/3 -taille_mess, 130, anchor=tkinter.NW, window=label_bouton_recherche_user)


def sql_recherche_user():
    conn = pyodbc.connect(r'Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=%s ;' %(path))
    cursor = conn.cursor()
    cursor.execute(''' SELECT col_id, col_nom, col_prenom FROM collaborateur WHERE col_contrat = '%s' order by col_nom ASC'''%(variable_search_contrat.get()))
    tab = []
    Lb = tkinter.Listbox(page_add_formation_user, bg='white', fg='black', font=("Arial", 12))
    for x in cursor:
        x = str(x)
        print (x)
        x = x.replace("'","").replace(" ","   ").replace("(","").replace(")","").replace(",","")
        tab.append(x)
    for i in range (len(tab)):
        Lb.insert(i, tab[i]+'  ')
        canvas_add_formation_user.create_window(10,150,width=400, anchor=tkinter.NW, window=Lb)
   
    cursor.execute(''' SELECT col_id FROM collaborateur WHERE col_contrat = '%s' '''%(variable_search_contrat.get()))
    global list_id_sql_recherche_user
    list_id_sql_recherche_user = [] 
    for x in cursor:
        x = str(x)
        x = x.replace("(","").replace(")","").replace(",","").replace(" ","")
        list_id_sql_recherche_user.append(x)
    print (list_id_sql_recherche_user)
    sql_select_user()
    
def sql_select_user():
    phrase_select_user = "entrez le numero de votre collaborateur"
    label_phrase_select_user = tkinter.Label(page_add_formation_user, text=phrase_select_user, font=("Courier", 10), bg="grey", fg="black")
    canvas_add_formation_user.create_window(520 , 20, anchor=tkinter.NW, window=label_phrase_select_user)

    variable_select_id = tkinter.StringVar(page_add_formation_user)
    variable_select_id.set(list_id_sql_recherche_user[0])

    global opt2
    opt2 = tkinter.OptionMenu(page_add_formation_user, variable_select_id, *list_id_sql_recherche_user)
    enter_opt2 = canvas_add_formation_user.create_window(520, 50, anchor=tkinter.NW, window=opt2)

    phrase_select_formation = "entrez la formation a effectuer"
    label_phrase_select_formation = tkinter.Label(page_add_formation_user, text=phrase_select_formation, font=("Courier", 10), bg="grey", fg="black")
    canvas_add_formation_user.create_window(520 , 100, anchor=tkinter.NW, window=label_phrase_select_formation)

    variable_select_formation = tkinter.StringVar(page_add_formation_user)
    variable_select_formation.set(list_formation[0])

    global opt3
    opt3 = tkinter.OptionMenu(page_add_formation_user, variable_select_formation, *list_formation)
    canvas_add_formation_user.create_window(520, 140, anchor=tkinter.NW, window=opt3)


#####################################
    #/ADD USER  FORMATION#
#####################################


#####################################
    #ADD USER#
#####################################
def sql_add_user():  
    global page_add_user
    page_add_user = tkinter.Tk()
    page_add_user.geometry('500x400')
    page_add_user.positionfrom=("user")
    global canvas_add_user
    canvas_add_user = tkinter.Canvas(page_add_user,width=taille_x, height=taille_y, bg='red4')
    canvas_add_user.pack()

    #ZONE DE TEXTE ECRIRE NOM PRENOM DATE NAISSANCE EMPLOYE
    zone_nom = "entrez le nom du nouvel employé."
    zone_prenom = "entrez le prenom du nouvel employé."
    zone_date = "selectionnez le type de contrat."
    
    
    OptionListContrat = ["CDI", "CTT","STAGIAIRE"] 
    global variable_add_contrat
    variable_add_contrat = tkinter.StringVar(page_add_user)
    variable_add_contrat.set(OptionListContrat[0])

    global opt
    opt = tkinter.OptionMenu(page_add_user, variable_add_contrat, *OptionListContrat)
    enter_opt = canvas_add_user.create_window(WIDTH/3 -taille_mess, 155, anchor=tkinter.NW, window=opt)
    
    label_zone_nom = tkinter.Label(page_add_user, text=zone_nom, bg='grey' , font=("Courier", 10))
    label_nom = canvas_add_user.create_window(WIDTH/3 -taille_mess, 10, anchor=tkinter.NW, window=label_zone_nom)
    var_nom= ""
    global nom_entry
    nom_entry = tkinter.Entry(page_add_user,textvariable=var_nom, width=25, bg='grey')
    enter_nom = canvas_add_user.create_window(WIDTH/3 -taille_mess, 35, anchor=tkinter.NW, window=nom_entry)
    
    label_zone_prenom = tkinter.Label(page_add_user, text=zone_prenom, bg='grey' , font=("Courier", 10))
    label_prenom = canvas_add_user.create_window(WIDTH/3 -taille_mess, 70, anchor=tkinter.NW, window=label_zone_prenom)
    var_prenom= ""
    global prenom_entry
    prenom_entry = tkinter.Entry(page_add_user,textvariable=var_prenom, width=25, bg='grey')
    enter_prenom = canvas_add_user.create_window(WIDTH/3 -taille_mess, 95, anchor=tkinter.NW, window=prenom_entry)

    #label_zone_date = tkinter.Label(page_add_user, text=zone_date, bg='grey' , font=("Courier", 10))
    #label_date = canvas.create_window(WIDTH/3 -taille_mess, 130, anchor=tkinter.NW, window=label_zone_date)
    #var_date= "" 
    #global date_entry
    #date_entry = tkinter.Entry(page_add_user,textvariable=var_date, width=25, bg='grey')
    #enter_date = canvas.create_window(WIDTH/3 -taille_mess, 155, anchor=tkinter.NW, window=date_entry)
    
    bouton1 = tkinter.Button(page_add_user, text="ajoutter le collaborateur", bg='white', command=sql_add_user_button)
    canvas_add_user.create_window(WIDTH/3, 200, anchor=tkinter.NW, window=bouton1)
    
def sql_add_user_button():
    if nom_entry.get() != "" and prenom_entry.get() != "":
        print ("if")
        print ("azertytrezer", path)
        #conn = pyodbc.connect(r'Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=\formation_2021_test1.accdb;')
        
        conn = pyodbc.connect(r'Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=%s ;' %(path))
        cursor = conn.cursor()
        cursor.execute(''' insert into collaborateur (col_prenom, col_nom, col_contrat) values ('%s', '%s', '%s') '''%(prenom_entry.get(), nom_entry.get(), variable_add_contrat.get()))#.format(name_user,firstname_user,date_user)
        conn.commit()
        quitt_page_add_user()
        print ("ajoutté okkk")
    else:
        print ("else")
        champ_manquant = "Un champ est manquant"
        label_champ_manquant = tkinter.Label(page_add_user, text=champ_manquant, bg='black', fg='white', font=("Courier", 10))
        label_champ = canvas_add_user.create_window(WIDTH/3 +80, 100, anchor=tkinter.NW, window=label_champ_manquant)


def quitt_page_add_user():
    page_add_user.destroy()
#####################################
    #/ADD USER#
#####################################

def new_page_admin():
    #COCAcolaBDD connection_bdd() 
    admin_page = tkinter.Tk()

    
    #admin_page.geometry('{}x{}'.format(taille_x/4, taille_y/3))
    admin_page.geometry('500x400')
    
    #creation des boutons onlglets
    canvas_admin = tkinter.Canvas(admin_page, width=taille_x, height=taille_y, bg='red4')
    canvas_admin.pack()
    
    bouton1 = tkinter.Button(admin_page, command=sql_add_user ,text="ajoutter collaborateur", bg='white') #OK
    canvas_admin.create_window(10, 10, anchor=tkinter.NW, window=bouton1)
    
    bouton1_1 = tkinter.Button(admin_page ,text="commencer form col", bg='white', command=add_formation_user) #OK
    canvas_admin.create_window(10, 150, anchor=tkinter.NW, window=bouton1_1)
    
    bouton2 = tkinter.Button(admin_page, text="voir les informations d'un salarié", bg='white')
    canvas_admin.create_window(200, 10, anchor=tkinter.NW, window=bouton2)
    
    bouton3 = tkinter.Button(admin_page, text="voir graphe salarié", bg='white')
    canvas_admin.create_window(10, 50, anchor=tkinter.NW, window=bouton3)
    
    bouton4 = tkinter.Button(admin_page, text="supprimer un salarié", bg='white')
    canvas_admin.create_window(200, 50, anchor=tkinter.NW, window=bouton4)
    
    bouton5 = tkinter.Button(admin_page, text="liste des salariés", bg='white')
    canvas_admin.create_window(10, 90, anchor=tkinter.NW, window=bouton5)  

#événement bouton accueil admin 
def onclick1():
    print ("button admin true")
    if (check_password(password_entry.get()) == True):
        print ("ok")
        mess_pass_bon = "*  bon mot de passe   *"
        label_mess_pass1 = tkinter.Label(root, text=mess_pass_bon, bg='green', font=("Courier", 10))
        label_bon_pass = canvas.create_window(WIDTH/3 +80, 100, anchor=tkinter.NW, window=label_mess_pass1)
        quitt_root()
        time.sleep(0.6)
        new_page_admin()
        
    else: 
        mess_pass_faux = "mauvais mot de passe"
        label_mess_pass = tkinter.Label(root, text=mess_pass_faux, bg='black', fg='white', font=("Courier", 10))
        label_faux_pass = canvas.create_window(WIDTH/3 +80, 100, anchor=tkinter.NW, window=label_mess_pass)


#verification mot de passe administrateur
def check_password(password):
    boolean = False
    result = hashlib.md5(password.encode()).hexdigest()
    if (result == "c1a91a5ae86faec0aab0e27b38ea72a3"):
        boolean = True
        return boolean
        print (path)
    else:
        boolean = True
        return boolean
        print (path)
    
def quitt_root():
    root.destroy()

#####################
# LIST FORMATIONS
global list_formation
list_formation = ["BTE Depal", "BTE Sout", "BTE BE3", "BTE BE4", "BTE Packa", "BTE Pal", "BTE PartieBasse", "L5 ZoneProcess", "L5 Packa", "L5 Pal", "L6 Souf", "L6 Manch", "L6 Sout", "L6 Packa", "L6 Pal", "L7 Process", "L7 Packa", "L7 Depal", "L7 Pal", "Sirop_C Prep", "Sirop_C NEP", "Sirop_C TDE", "Sirop_A Prep", "Sirop_A NEP", "Sirop_A Dep", "Sirop_A Basc", "OZQ Base", "OZQ Complete"]


#MAIN      
#IMAGE_PATH = 'img0.png'

nom_fichier = '\\COCA11.accdb'
global path

path = os.getcwd()
path = path + nom_fichier
print (type(path))
print (path)
    
WIDTH, HEIGTH = 600, 350
root = tkinter.Tk()
root.geometry('{}x{}'.format(WIDTH, HEIGTH))
global taille_x
global taille_y
taille_x = int(root.winfo_screenwidth()) # 1536
taille_y = int(root.winfo_screenheight()) # 864
root.positionfrom=("user")
canvas =canvas = tkinter.Canvas(root,width=taille_x, height=taille_y, bg='red4')
canvas.pack()

#comprends pas on peut pas compiler avec des images de fond, a voir pour plus tard. date 11/12/2020
#img = ImageTk.PhotoImage(Image.open(IMAGE_PATH).resize((WIDTH, HEIGTH), Image.ANTIALIAS))
#canvas.background = img  # Keep a reference in case this code is put in a function.
#bg = canvas.create_image(0, 0, anchor=tkinter.NW, image=img)

#message bienvenue
message_bienvenue = "Bienvenue dans votre logiciel Coca-Cola !"
taille_mess = len(message_bienvenue)*2
label_text = tkinter.Label(root, text=message_bienvenue, font=("Courier", 10), bg="red", fg="black")
label_bienvenue = canvas.create_window(WIDTH/3 -taille_mess, 10, anchor=tkinter.NW, window=label_text)

#PANEL ADMIN
#mess si admin
mess_admin = "Si vous êtes administrateur, entrez votre mot de passe"
label_mess_admin = tkinter.Label(root, text=mess_admin, bg='grey' , font=("Courier", 10))
label_admin = canvas.create_window(WIDTH/3 -taille_mess, 80, anchor=tkinter.NW, window=label_mess_admin)

#input password
var_password= ""
password_entry = tkinter.Entry(root, show="*",textvariable=var_password, width=25, bg='grey')
enter_password = canvas.create_window(WIDTH/3 -taille_mess, 100, anchor=tkinter.NW, window=password_entry)

#boutton admin
label_button = tkinter.Button(root, text="valider", bg='white', command=onclick1, font=("Courier", 10))
button_admin = canvas.create_window(WIDTH/3 -taille_mess, 120, anchor=tkinter.NW, window=label_button)

#PANEL UTILISATEUR
#message utilisateur lambda
mess_user = "Si vous êtes utilisateur, cliquez ci-dessous"
label_mess_user= tkinter.Label(root, text=mess_user, bg='grey', font=("Courier", 10))
label_user = canvas.create_window(WIDTH/3 -taille_mess, 190, anchor=tkinter.NW, window=label_mess_user)

#boutton connexion basique
label_bouton2 = tkinter.Button(root, text="Connexion utilisateur", bg='white')
button_user = canvas.create_window(WIDTH/3 -taille_mess, 210, anchor=tkinter.NW, window=label_bouton2)

root.mainloop()