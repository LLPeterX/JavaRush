package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Графический интерфейс будет представлять собой окно, в котором будет меню и панель с двумя вкладками.
//На первой вкладке будет располагаться текстовая панель, которая будет отрисовывать html страницу.
// На ней можно будет форматировать и редактировать текст страницы.
//На второй вкладке будет редактор, который будет отображать код html страницы, в нем будут видны все используемые html теги.
// В нем также можно будет менять текст страницы, добавлять и удалять различные теги.
public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(); //панель с двумя вкладками.
    private JTextPane htmlTextPane = new JTextPane(); // панель для визуального редактирования html
    private JEditorPane plainTextPane = new JEditorPane(); // панель для редактировани текста html
    private FrameListener listener;
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    // конструктор
    // 9.2. Добавь конструктор класса View. Он должен устанавливать внешний вид и поведение (look and feel)
    // нашего приложения такими же, как это определено в системе.
    //Конструктор не должен кидать исключений, только логировать их с помощью ExceptionHandler.
    // Подсказа: для реализации задания используй класс UIManager.
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    // инициализщация меню
    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // 9.1.2. С помощью MenuHelper инициализировать меню в следующем порядке: Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь
        MenuHelper.initFileMenu(this,menuBar);
        MenuHelper.initEditMenu(this,menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this,menuBar);
        MenuHelper.initColorMenu(this,menuBar);
        MenuHelper.initFontMenu(this,menuBar);
        MenuHelper.initHelpMenu(this,menuBar);
        // 9.1.3. Добавлять в верхнюю часть панели контента текущего фрейма нашу панель меню, аналогично тому, как это мы делали с панелью вкладок.
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
//        JScrollPane scrollPaneHTML = new JScrollPane();
//        tabbedPane.addTab("HTML",new scrollPaneHTML);
        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));
        // вкладка для текста
//        JScrollPane scrollPaneTXT = new JScrollPane();
//        tabbedPane.addTab("Текст",scrollPaneTXT);
        tabbedPane.addTab("Текст", new JScrollPane(plainTextPane));
        // 6.6. Устанавливать предпочтительный размер панели tabbedPane.
        tabbedPane.setPreferredSize(new Dimension(600,600));
        // 6.7. Создавать объект класса TabbedPaneChangeListener и устанавливать его в качестве слушателя изменений в tabbedPane.
        //TabbedPaneChangeListener changeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        // 6.8. Добавлять по центру панели контента текущего фрейма нашу панель с вкладками.
        // Получить панель контента текущего фрейма можно с помощью метода getContentPane(), его реализация унаследовалась от JFrame.
        //tabbedPane.setAlignmentX(Component.CENTER_ALIGNMENT); // неверно!!!
        getContentPane().add(tabbedPane,BorderLayout.CENTER);


    }
    // initGui() будет инициализировать графический интерфейс.
    public void initGui() {
        initMenuBar(); // меню
        initEditor(); // панель с двумя вкладками
        pack(); // унаслед. от JFrame - уст.размер компонентов так, чтобы они все уместились в окне. Должен вызываться ПОСЛЕ добавления всех эл-тов
    }


    public void exit() {
        controller.exit();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }
    public  boolean canRedo() {
        return undoManager.canRedo();
    }
    // 11.5.5. Добавь и реализуй метод void resetUndo(), который должен сбрасывать все правки в менеджере undoManager.
    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

    }

    public void init() {
        // 4.3.1. Вызывать инициализацию графического интерфейса initGui().
        initGui();
        // 4.3.2. Добавлять слушателя событий нашего окна. - ???
        // В качестве подписчика создай и используй объект класса FrameListener.
        // В качестве метода для добавления подписчика используй подходящий метод из класса Window от которого наследуется
        // и наш класс через классы JFrame и Frame.
        listener = new FrameListener(this); // подписчик
        addWindowListener(listener);
        this.setVisible(true);

    }
    //  true, если выбрана вкладка, отображающая html в панели вкладок (подсказка: ее индекс 0)
    public boolean isHtmlTabSelected() {
        //return tabbedPane.getSelectedComponent() == tabbedPane.getComponents()[0];
        return tabbedPane.getSelectedIndex()==0;
    }

    // 14.1.1. Выбирать html вкладку (переключаться на нее).
    //14.1.2. Сбрасывать все правки с помощью метода, который ты реализовал ранее.
    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    //update(), который должен получать документ у контроллера и устанавливать его в панель редактирования htmlTextPane.
    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    // показывать диалоговое окно с информацией о программе.
    // Информацию придумай сам, а вот тип сообщения должен быть JOptionPane.INFORMATION_MESSAGE.
    public void showAbout() {
        JOptionPane.showMessageDialog(null,
                "Тестовый редактор HTML",
                "Редактор",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // 18.1. Метод должен проверить, какая вкладка сейчас оказалась выбранной.
    //18.2. Если выбрана вкладка с индексом 0 (html вкладка), значит нам нужно получить текст из plainTextPane
    // и установить его в контроллер с помощью метода setPlainText.
    //18.3. Если выбрана вкладка с индексом 1 (вкладка с html текстом), то необходимо получить текст у контроллера
    // с помощью метода getPlainText() и установить его в панель plainTextPane.
    //18.4. Сбросить правки (вызвать метод resetUndo представления).
    public void selectedTabChanged() {
        if(tabbedPane.getSelectedIndex()==0) { // выбрана вкладка HTML
            //текст из plainTextPane
            String text = plainTextPane.getText();
            controller.setPlainText(text);
        } else if(tabbedPane.getSelectedIndex()==1){ //  вкладка TEXT
            String text = controller.getPlainText();
            plainTextPane.setText(text);
        }
        resetUndo();
    }
//Реализуем метод actionPerformed(ActionEvent actionEvent) у представления,
// этот метод наследуется от интерфейса ActionListener и будет вызваться при выборе пунктов меню,
// у которых наше представление указано в виде слушателя событий.
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        } // case
    }

}
