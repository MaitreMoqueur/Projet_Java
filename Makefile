# Variables
SRC_DIR = src
MODEL_DIR = $(SRC_DIR)/main/java/com/schottenTotten/model
CONTROLLER_DIR = $(SRC_DIR)/main/java/com/schottenTotten/controller
VIEW_DIR = $(SRC_DIR)/main/java/com/schottenTotten/view
AI_DIR = $(SRC_DIR)/main/java/com/schottenTotten/ai
BIN_DIR = bin
MAIN_CLASS = $(SRC_DIR)/main/java/com/schottenTotten/Main.java

# Sources et classes
SRC_FILES = \
	$(MODEL_DIR)/Carte.java \
	$(MODEL_DIR)/Joueur.java \
	$(MODEL_DIR)/Borne.java \
        $(MODEL_DIR)/Hand.java \
	$(MODEL_DIR)/Pioche.java \
	$(MODEL_DIR)/Variante.java \
	$(MODEL_DIR)/CarteTactique.java \
	$(CONTROLLER_DIR)/GestionPartie.java \
	$(VIEW_DIR)/ConsoleView.java \
	$(VIEW_DIR)/MenuPrincipal.java \
	$(VIEW_DIR)/InputHandler.java \
	$(MAIN_CLASS)\
	$(AI_DIR)/IA.java

CLASS_FILES = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SRC_FILES))

# Commandes
JAVAC = javac
JAVA = java
MKDIR = mkdir -p
RM = rm -rf
CODE = code


# Règles
.PHONY: all clean run

all: $(CLASS_FILES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@$(MKDIR) $(dir $@)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<

run: all
	java -cp bin com.schottenTotten.Main

clean:
	$(RM) $(BIN_DIR)

code:
	$(CODE) $(SRC_FILES)
# Fichiers supplémentaires (README et PDF inclus pour information)
README.txt: 
	@echo "README.txt est présent."

schottentotten_regles.pdf:
	@echo "schottentotten_regles.pdf est présent."

PG22O_Projet_1.pdf:
	@echo "PG22O_Projet_1.pdf est présent."
