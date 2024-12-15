# Variables
SRC_DIR = src
MODEL_DIR = $(SRC_DIR)/com/schottenTotten/model
BIN_DIR = bin
MAIN_CLASS = Main

# Sources
SRC_FILES = \
	$(SRC_DIR)/Borne.java \
	$(SRC_DIR)/Hand.java \
	$(SRC_DIR)/ConsoleView.java \
	$(SRC_DIR)/MenuPrincipal.java \
	$(SRC_DIR)/GestionPartie.java \
	$(MODEL_DIR)/Carte.java \
	$(MODEL_DIR)/Hand.java \
	$(MODEL_DIR)/Joueur.java \
	$(MODEL_DIR)/Pioche.java \
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

# Règle pour les fichiers dans src
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@$(MKDIR) $(dir $@)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $<

# Règle pour les fichiers dans src/com/schottenTotten/model
$(BIN_DIR)/com/schottenTotten/model/%.class: $(MODEL_DIR)/%.java
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

