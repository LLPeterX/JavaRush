package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view; // отвечает за проедстваление
    private HTMLDocument document; // содержимое html (из библиотеки swing)
    private File currentFile; // файл, который открыт в нашем редакторе

    // конструктор
    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void exit() {
        System.exit(0);
    }

    public void init() {
        createNewDocument();
    }
    // Добавь в контроллер метод resetDocument(), который будет сбрасывать текущий документ. Он должен:
    //15.1. Удалять у текущего документа document слушателя правок которые можно отменить/вернуть
    // (найди подходящий для этого метод, унаследованный от AbstractDocument).
    //Слушателя нужно запросить у представления (метод getUndoListener()).
    //Не забудь проверить, что текущий документ существует (не null).
    //15.2. Создавать новый документ по умолчанию и присваивать его полю document.
    public void resetDocument() {
        if(document!=null)
            document.removeUndoableEditListener(view.getUndoListener());
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    // Добавь метод setPlainText(String text) в контроллер.
    // Он будет записывать переданный текст с html тегами в документ document. При его реализации:
    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        try {
            new HTMLEditorKit().read(reader, document,0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter writer = new StringWriter();
        try {
            new HTMLEditorKit().write(writer,document,0,document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    // 20.1. Реализуй метод создания нового документа createNewDocument() в контроллере. Он должен:
    //20.1.1. Выбирать html вкладку у представления.
    //20.1.2. Сбрасывать текущий документ.
    //20.1.3. Устанавливать новый заголовок окна, например: "HTML редактор". Воспользуйся методом setTitle(),
    // который унаследован в нашем представлении.
    //20.1.4. Сбрасывать правки в Undo менеджере. Используй метод resetUndo представления.
    //20.1.5. Обнулить переменную currentFile.
    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        // аналогично SaveDocumentAs(), только вызов showOpen
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int res = fileChooser.showOpenDialog(view);
        if(res == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileReader reader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(reader, document, 0);
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
            resetDocument();
        }

    }

    public void saveDocument() {
        if(currentFile==null)
            saveDocumentAs();
        else { // файл непустой
            view.selectHtmlTab();
            // дублируем код из saveAs(), пропуская fileChooser
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                //Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText()
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        } //else
    } // saveDocument()

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int res = fileChooser.showSaveDialog(view);
        if(res == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                //Переписывать данные из документа document в объекта FileWriter-а аналогично тому, как мы это делали в методе getPlainText()
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }

        }

    }

    public static void main(String[] args) {
        View mainView = new View();
        Controller mainController = new Controller(mainView);
        mainView.setController(mainController);
        mainView.init();
        mainController.init();

    }
}
