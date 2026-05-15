# JavaFX Notepad

A simple **Notepad application** built with **JavaFX**.  
This project demonstrates how to create a text editor with menus, keyboard shortcuts, and basic file operations.

---

## ✨ Features

- **File Menu**
  - New file (`Ctrl+N`)
  - Open file (`Ctrl+O`)
  - Save file (`Ctrl+S`)
  - Save As...
  - Exit application

- **Edit Menu**
  - Cut (`Ctrl+X`)
  - Copy (`Ctrl+C`)
  - Paste (`Ctrl+V`)
  - Select All (`Ctrl+A`)

- **Format Menu**
  - Word Wrap toggle
  - Clear text area

- **View Menu**
  - Dark Mode toggle

- **Help Menu**
  - About dialog with app info

---

## 🛠️ Technologies Used

- **JavaFX** for GUI components
- **Java NIO Files API** for file handling
- **FileChooser** for opening and saving files
- **Scene & Stage** for window management

---

## 📂 Project Structure


- `Main` class extends `Application`
- `start(Stage stage)` initializes UI
- `TextArea` for text editing
- `MenuBar` with multiple menus
- Helper methods:
  - `openFile(Stage stage)`
  - `saveFile(Stage stage)`
  - `saveAsFile(Stage stage)`

---

## 🚀 How to Run

1. Install **Java 11+** and **JavaFX SDK**.
2. Compile the project:
   ```bash
   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls Main.java
