import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainScreen {

    private JTextArea jTextArea;            // Поле вывода каталога
    private File currentFilePath;           // Текущий путь к каталогу
    private boolean createdFile;            // метка о изменении в каталоге
    private boolean deleteFile;             // метка о удаление из каталога
    private boolean openFile ;              // метка о открытие нового каталога
    private boolean comeBackFile ;          // метка о возврате в каталог на шаг назад
    private boolean selectRoot ;            // метка о выборе диска
    private boolean update ;                // метка о обновлении каталога
    private boolean selectHelp ;            // метка о вызове справки - Помощь
    private String newFilePart;             // путь к новому созданному файлу или папку
    private String deleteFilePart;          // путь на одну папку назад
    private String openNewFile;             // путь к новому файлу(каталогу),который надо открыть
    private String openComeBackFile;        // путь к каталогу на шаг назад
    private String root;                    // выбранный диск
    private JTextField inputCreateFile;     // поле ввода имени создаваемого файла/папки
    private JTextField inputDeleteFile;     // поле ввода имени удаляемого файла/папки
    private JTextField inputOpenFile;       // поле ввода файла/папки которую надо открыть
    private JTextField inputRootFile;       // поле ввода диска, который просматриваешь
    private JButton buttonCreateFile;       // кнопка "Создать файл или папку"
    private JButton buttonDeleteFile;       // кнопка "Удалить файл или кнопку"
    private JButton buttonOpenFile;         // кнопка "Открыть файл или кнопку"
    private JButton buttonComeBackFile;     // кнопка "Вернуться назад"


    public void appendjTextArea(String text) { jTextArea.append(text + "\n"); }
    public void setjTextArea(String text) { jTextArea.setText(text);}

    public boolean isCreatedFile() { return createdFile; }
    public boolean isDeleteFile() { return deleteFile; }
    public boolean isOpenFile() { return openFile; }
    public boolean isComeBackFile() { return comeBackFile; }
    public boolean isSelectRoot() { return selectRoot; }
    public boolean isUpdate() { return update; }
    public boolean isSelectHelp() { return selectHelp; }
    public String getOpenNewFile() { return openNewFile; }
    public String getOpenComeBackFile() { return openComeBackFile; }
    public String getRoot() { return root; }
    public JTextField getInputCreateFile() { return inputCreateFile; }
    public String getNewFilePart() { return newFilePart; }
    public String getDeleteFilePart() { return deleteFilePart; }

    public void setCreatedFile(boolean createdFile) { this.createdFile = createdFile; }
    public void setDeleteFile(boolean deleteFile) { this.deleteFile = deleteFile; }
    public void setOpenFile(boolean openFile) { this.openFile = openFile; }
    public void setComeBackFile(boolean comeBackFile) { this.comeBackFile = comeBackFile; }
    public void setSelectRoot(boolean selectRoot) { this.selectRoot = selectRoot; }
    public void setUpdate(boolean update) { this.update = update; }
    public void setRoot(String root) { this.root = root; }
    public void setSelectHelp(boolean selectHelp) { this.selectHelp = selectHelp; }
    public void setInputCreateFile(String text) { this.inputCreateFile.setText(text);}
    public void setInputDeleteFile(String text) { this.inputDeleteFile.setText(text);}
    public void setInputOpenFile(String text) { this.inputOpenFile.setText(text);}
    public void setInputRootFile(String text) { this.inputRootFile.setText(text);}
    public void setCurrentFilePath(File currentFilePath) { this.currentFilePath = currentFilePath; }
    public void setEnableButtonCreateFile(boolean status) {  buttonCreateFile.setEnabled(status); }
    public void setEnableButtonDeleteFile(boolean status) {  buttonDeleteFile.setEnabled(status); }
    public void setEnableButtonOpenFile(boolean status) {  buttonOpenFile.setEnabled(status); }
    public void setEnableButtonComeBackFile(boolean status) {  buttonComeBackFile.setEnabled(status); }
    public void setNewFilePart(String newFilePart) { this.newFilePart = newFilePart; }

    public MainScreen() {

        JFrame jMainScreen = new JFrame("Manager File");
        Toolkit toolkit = Toolkit.getDefaultToolkit();  // определяет размер экрана
        Dimension dimension = toolkit.getScreenSize();  // возвращает обьект где есть размеры экрана
        jMainScreen.setBounds(dimension.width/2 -300 , dimension.height/2 - 300 , 600, 480); // устанавливаем положение jMainScreen на экране
        jMainScreen.setLayout(new GridLayout(1,2));
        jMainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Панель отображения содержания каталога */
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(290,470));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanel1.setLayout(new FlowLayout());
        jPanel1.setOpaque(true);

        /* Поле c экраном */
        jTextArea = new JTextArea();
        jTextArea.setEditable(false); // (false) запрещает редактирование поля jTextArea
        jTextArea.setEnabled(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(280,420));
        jPanel1.add(jScrollPane);

        /* Поле c кнопками упраления */
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(290,470));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanel2.setLayout(new GridLayout(5,1));
        jPanel2.setOpaque(true);

        /* панель управления "Создать новый файл или папку" */
        JPanel jPanelCreated = new JPanel();
        jPanelCreated.setPreferredSize(new Dimension(290,100));
        jPanelCreated.setLayout(new GridLayout(1,2));
        jPanelCreated.setOpaque(true);

        JPanel jPanelCreatedLabel = new JPanel();
        jPanelCreatedLabel.setPreferredSize(new Dimension(140,100));
        jPanelCreatedLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelCreatedLabel.setLayout(new GridLayout(2,1));
        jPanelCreatedLabel.setOpaque(true);


        /* Кнопка "Создать новый файл или папку" */
        JLabel labelCreateFile = new JLabel(" Write a name ");
        labelCreateFile.setHorizontalTextPosition(SwingConstants.CENTER);
        inputCreateFile = new JTextField(10);
        buttonCreateFile = new JButton("Create File/Folder");
        buttonCreateFile.addActionListener((e) -> {
            createdFile = true;
            newFilePart = currentFilePath  + "\\" + inputCreateFile.getText();
        });

        /* панель управления "Удалить файл или папку" */
        JPanel jPanelDelete = new JPanel();
        jPanelDelete.setPreferredSize(new Dimension(290,100));
        jPanelDelete.setLayout(new GridLayout(1,2));
        jPanelDelete.setOpaque(true);

        JPanel jPanelDeleteLabel = new JPanel();
        jPanelDeleteLabel.setPreferredSize(new Dimension(140,100));
        jPanelDeleteLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelDeleteLabel.setLayout(new GridLayout(2,1));
        jPanelDeleteLabel.setOpaque(true);

        /* Кнопка "Удалить файл или папку" */
        JLabel labelDeleteFile = new JLabel(" Write a name ");
        inputDeleteFile = new JTextField(10);
        buttonDeleteFile = new JButton("Delete File/Folder");
        buttonDeleteFile.addActionListener((e) -> {
            deleteFile = true;
            deleteFilePart = currentFilePath  + "\\" + inputDeleteFile.getText();
        });

        /* панель управления "Открыть файл или папку" */
        JPanel jPanelOpen = new JPanel();
        jPanelOpen.setPreferredSize(new Dimension(290,100));
        jPanelOpen.setLayout(new GridLayout(1,2));
        jPanelOpen.setOpaque(true);

        JPanel jPanelOpenLabel = new JPanel();
        jPanelOpenLabel.setPreferredSize(new Dimension(140,100));
        jPanelOpenLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelOpenLabel.setLayout(new GridLayout(2,1));
        jPanelOpenLabel.setOpaque(true);

        /* Кнопка "Открыть файл или папку" */
        JLabel labelOpenFile = new JLabel(" Write a name ");
        inputOpenFile = new JTextField(10);
        buttonOpenFile = new JButton("Open File/Folder");
        buttonOpenFile.addActionListener((e) -> {
            openFile = true;
            openNewFile = currentFilePath  + "\\" + inputOpenFile.getText() + "\\";
        });

        /* панель управления "Вернуться назад в файл или папку" */
        JPanel jPanelComeBack = new JPanel();
        jPanelComeBack.setPreferredSize(new Dimension(290,100));
        jPanelComeBack.setLayout(new GridLayout(1,2));
        jPanelComeBack.setOpaque(true);

        JPanel jPanelComeBackLabel = new JPanel();
        jPanelComeBackLabel.setPreferredSize(new Dimension(140,100));
        jPanelComeBackLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelComeBackLabel.setLayout(new GridLayout(2,1));
        jPanelComeBackLabel.setOpaque(true);

        /* Кнопка " Вернуться назад" */
        buttonComeBackFile = new JButton("Come Back");
        buttonComeBackFile.addActionListener((e) -> {
            comeBackFile = true;
            String currentWay = currentFilePath.getAbsolutePath();
            int lastIndexSeparator = currentWay.lastIndexOf("\\");
            openComeBackFile = currentWay.substring(0,lastIndexSeparator) + "\\";
        });

        /* панель управления "Выбор диска" */
        JPanel jPanelRoot = new JPanel();
        jPanelRoot.setPreferredSize(new Dimension(290,100));
        jPanelRoot.setLayout(new GridLayout(1,2));
        jPanelRoot.setOpaque(true);

        JPanel jPanelRootLabel = new JPanel();
        jPanelRootLabel.setPreferredSize(new Dimension(140,100));
        jPanelRootLabel.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelRootLabel.setLayout(new GridLayout(2,1));
        jPanelRootLabel.setOpaque(true);

        /* Кнопка "Выбрать диск" */
        JLabel labelRootFile = new JLabel(" Select root ");
        inputRootFile = new JTextField(10);
        JButton buttonRootFile = new JButton("Select Root");
        buttonRootFile.addActionListener((e) -> {
            selectRoot = true;
            root = inputRootFile.getText();
        });

        /* панель управления "Выбор диска" */

        JPanel jPanelHelp = new JPanel();
        jPanelHelp.setPreferredSize(new Dimension(140,100));
        jPanelHelp.setBorder(BorderFactory.createLineBorder(Color.black, 3)); // рамка
        jPanelHelp.setLayout(new GridLayout(2,1));
        jPanelHelp.setOpaque(true);

        /* Кнопка " Help" */
        JButton buttonHelpFile = new JButton("Help");
        buttonHelpFile.addActionListener((e) -> {
            selectHelp = true;
        });

        // Добавление панелей в панель управления Выбора диска
        jPanelRootLabel.add(labelRootFile);
        jPanelRootLabel.add(inputRootFile);
        jPanelRoot.add(jPanelRootLabel);
        jPanelRoot.add(buttonRootFile);

        // Добавление панелей в панель управления кнопкой Создать
        jPanelCreatedLabel.add(labelCreateFile);
        jPanelCreatedLabel.add(inputCreateFile);
        jPanelCreated.add(jPanelCreatedLabel);
        jPanelCreated.add(buttonCreateFile);

        // Добавление панелей в панель управления кнопкой Удалить
        jPanelDeleteLabel.add(labelDeleteFile);
        jPanelDeleteLabel.add(inputDeleteFile);
        jPanelDelete.add(jPanelDeleteLabel);
        jPanelDelete.add(buttonDeleteFile);

        // Добавление панелей в панель управления кнопкой Открыть
        jPanelOpenLabel.add(labelOpenFile);
        jPanelOpenLabel.add(inputOpenFile);
        jPanelOpen.add(jPanelOpenLabel);
        jPanelOpen.add(buttonOpenFile);

        // Добавление панелей в панель управления кнопкой Возврат назад
        jPanelComeBack.add(buttonHelpFile);
        jPanelComeBack.add(buttonComeBackFile);

        /* Добавление панелей c кнопками упраления на главную панель управления */
        jPanel2.add(jPanelRoot);
        jPanel2.add(jPanelCreated);
        jPanel2.add(jPanelDelete);
        jPanel2.add(jPanelOpen);
        jPanel2.add(jPanelComeBack);

        /* Добавление главной панели управления и панели отображения содержания каталога на Frame */
        jMainScreen.add(jPanel1);
        jMainScreen.add(jPanel2);

        jMainScreen.setVisible(true);
    }
}
