NewPasswords.class: NewPasswords.java #new project
	javac -g NewPasswords.java

Passwords.class: Passwords.java #old project
	javac -g Passwords.java

old: Passwords.class

run:
	java NewPasswords

runold:
	java Passwords

all: NewPasswords.class run #for new project

.PHONY: all run

clean:
	rm *.class