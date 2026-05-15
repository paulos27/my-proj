import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main extends Application {

    private TextArea textArea;
    private File currentFile;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        textArea = new TextArea();

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");
        newFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));

        MenuItem open = new MenuItem("Open...");
        open.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));

        MenuItem save = new MenuItem("Save");
        save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        MenuItem saveAs = new MenuItem("Save As...");

        MenuItem exit = new MenuItem("Exit");

        fileMenu.getItems().addAll(newFile, open, save, saveAs, new SeparatorMenuItem(), exit);

        Menu editMenu = new Menu("Edit");

        MenuItem cut = new MenuItem("Cut");
        cut.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        MenuItem copy = new MenuItem("Copy");
        copy.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));

        MenuItem paste = new MenuItem("Paste");
        paste.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));

        MenuItem selectAll = new MenuItem("Select All");
        selectAll.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));

        editMenu.getItems().addAll(cut, copy, paste, new SeparatorMenuItem(), selectAll);

        Menu formatMenu = new Menu("Format");
        CheckMenuItem wordWrap = new CheckMenuItem("Word Wrap");
        MenuItem clear = new MenuItem("Clear");
        formatMenu.getItems().addAll(wordWrap, clear);

        Menu viewMenu = new Menu("View");
        CheckMenuItem darkMode = new CheckMenuItem("Dark Mode");
        viewMenu.getItems().add(darkMode);

        Menu helpMenu = new Menu("Help");
        MenuItem about = new MenuItem("About");
        helpMenu.getItems().add(about);

        menuBar.getMenus().addAll(fileMenu, editMenu, formatMenu, viewMenu, helpMenu);

        newFile.setOnAction(e -> {
            textArea.clear();
            currentFile = null;
        });

        open.setOnAction(e -> openFile(stage));
        save.setOnAction(e -> saveFile(stage));
        saveAs.setOnAction(e -> saveAsFile(stage));
        exit.setOnAction(e -> stage.close());

        cut.setOnAction(e -> textArea.cut());
        copy.setOnAction(e -> textArea.copy());
        paste.setOnAction(e -> textArea.paste());
        selectAll.setOnAction(e -> textArea.selectAll());

        wordWrap.setOnAction(e -> textArea.setWrapText(wordWrap.isSelected()));
        clear.setOnAction(e -> textArea.clear());

        darkMode.setOnAction(e -> {
            if (darkMode.isSelected()) {
                textArea.setStyle("-fx-control-inner-background: black; -fx-text-fill: white;");
            } else {
                textArea.setStyle("");
            }
        });

        about.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Simple Notepad");
            alert.setContentText("Made with JavaFX");
            alert.showAndWait();
        });

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(textArea);

        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("Notepad");
        stage.setScene(scene);
        stage.show();
    }

    private void openFile(Stage stage) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(stage);

        if (file != null) {
            try {
                textArea.setText(Files.readString(file.toPath()));
                currentFile = file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Stage stage) {
        try {
            if (currentFile == null) {
                saveAsFile(stage);
            } else {
                Files.writeString(currentFile.toPath(), textArea.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAsFile(Stage stage) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(stage);

        if (file != null) {
            try {
                Files.writeString(file.toPath(), textArea.getText());
                currentFile = file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
