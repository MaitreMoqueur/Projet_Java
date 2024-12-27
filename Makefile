# Variables
SRC_DIR = src
MODEL_DIR = $(SRC_DIR)/main/java/com/schottenTotten/model
CONTROLLER_DIR = $(SRC_DIR)/main/java/com/schottenTotten/controller
VIEW_DIR = $(SRC_DIR)/main/java/com/schottenTotten/view
AI_DIR = $(SRC_DIR)/main/java/com/schottenTotten/ai
BIN_DIR = bin
TEST_DIR = src/test/java/com/schottenTotten
MAIN_CLASS = $(SRC_DIR)/main/java/com/schottenTotten/Main.java

# Sources et classes
SRC_FILES = \
	$(MODEL_DIR)/Carte.java \
	$(MODEL_DIR)/Joueur.java \
	$(MODEL_DIR)/Borne.java \
        $(MODEL_DIR)/Hand.java \
	$(MODEL_DIR)/Pioche.java \
	$(CONTROLLER_DIR)/GestionPartie.java \
	$(VIEW_DIR)/ConsoleView.java \
	$(VIEW_DIR)/MenuPrincipal.java \
	$(VIEW_DIR)/InputHandler.java \
	$(MAIN_CLASS)\
	$(AI_DIR)/IA.java

TEST_FILES = \
	$(TEST_DIR)/model/JoueurTest.java \
	$(TEST_DIR)/model/CarteTest.java \
        $(TEST_DIR)/model/PiocheTest.java \
        $(TEST_DIR)/model/HandTest.java \
        $(TEST_DIR)/model/BorneTest.java \
        $(TEST_DIR)/controller/GestionPartieTest.java \
        $(TEST_DIR)/ai/IATest.java

CLASS_FILES = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SRC_FILES))
TEST_CLASS_FILES = $(patsubst $(TEST_DIR)/%.java,$(BIN_DIR)/test/%.class,$(TEST_FILES))

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

$(BIN_DIR)/test/%.class: $(TEST_DIR)/%.java
	@$(MKDIR) $(dir $@)
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR):$(TEST_DIR) -cp ./junit-jupiter-api-5.11.4.jar:./junit-jupiter-engine-5.11.4.jar:./apiguardian-api-1.1.2.jar:./junit-platform-commons-1.11.4.jar:./opentest4j-1.3.0.jar:./junit-platform-console-standalone-1.11.4.jar:./mockito-core-5.14.2.jar $<

run: all
	java -cp bin main.java.com.schottenTotten.Main

clean:
	$(RM) $(BIN_DIR)

code:
	$(CODE) $(SRC_FILES)

compil_tests: $(TEST_CLASS_FILES)

tests: compil_tests
	$(JAVA) -cp $(BIN_DIR):./junit-platform-console-standalone-1.11.4.jar \
		org.junit.platform.console.ConsoleLauncher --scan-classpath


# Fichiers supplémentaires (README et PDF inclus pour information)
README.txt: 
	@echo "README.txt est présent."

schottentotten_regles.pdf:
	@echo "schottentotten_regles.pdf est présent."

PG22O_Projet_1.pdf:
	@echo "PG22O_Projet_1.pdf est présent."
