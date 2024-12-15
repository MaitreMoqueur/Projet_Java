# Variables
SRC_DIR = src
MODEL_DIR = $(SRC_DIR)/com/schottenTotten/model
CONTROLLER_DIR = $(SRC_DIR)/com/schottenTotten/controller
VIEW_DIR = $(SRC_DIR)/com/schottenTotten/view
AI_DIR = $(SRC_DIR)/com/schottenTotten/ai
BIN_DIR = bin
MAIN_CLASS = com.schottenTotten.Main

# Sources et classes
SRC_FILES = \
	$(MODEL_DIR)/Carte.java \
	$(MODEL_DIR)/Joueur.java \
	$(MODEL_DIR)/Borne.java \
	$(MODEL_DIR)/Pioche.java \
	$(CONTROLLER_DIR)/GestionPartie.java \
	$(VIEW_DIR)/ConsoleView.java \
	$(SRC_DIR)/MenuPrincipal.java \
	$(SRC_DIR)/Main.java

CLASS_FILES = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SRC_FILES))

# Commandes
JAVAC = javac
JAVA = java
MKDIR = mkdir -p
RM = rm -rf

# Règles
.PHONY: all clean run

all: $(CLASS_FILES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@$(MKDIR) $(dir $@)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<

run: all
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

clean:
	$(RM) $(BIN_DIR)

# Fichiers supplémentaires (README et PDF inclus pour information)
README.txt:
	@echo "README.txt est présent."

schottentotten_regles.pdf:
	@echo "schottentotten_regles.pdf est présent."

PG22O_Projet_1.pdf:
	@echo "PG22O_Projet_1.pdf est présent."
