import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Common {

    private MainScreen mainScreen;
    private Direct direct;
    private Root root;
    private String selectedRoot;

    public Common() {
        this.mainScreen = new MainScreen();
        root = new Root();
        this.direct = new Direct();
        this.mainScreen.setSelectRoot(true);
        this.mainScreen.setRoot("");
        mainScreen.setEnableButtonCreateFile(false);
        mainScreen.setEnableButtonDeleteFile(false);
        mainScreen.setEnableButtonOpenFile(false);
        mainScreen.setEnableButtonComeBackFile(false);
    }
    // Начальная загрузка приложения и выбор диска с которым работаем
    public void selectRoot(){
        if (mainScreen.isSelectRoot()){
            selectedRoot = mainScreen.getRoot();
            if (selectedRoot.equals("")){
                mainScreen.setjTextArea("");
                List<String> listRoot = root.getRoot();
                for (String file : listRoot) {
                    mainScreen.appendjTextArea(file);
                }
                mainScreen.setEnableButtonCreateFile(false);
                mainScreen.setEnableButtonDeleteFile(false);
                mainScreen.setEnableButtonOpenFile(false);
                mainScreen.setEnableButtonComeBackFile(false);
            } else {
                File root = new File(selectedRoot);
                direct.setDirest(root);
                mainScreen.setUpdate(true);
                showDirect();
                mainScreen.setSelectRoot(true);
                mainScreen.setEnableButtonCreateFile(true);
                mainScreen.setEnableButtonDeleteFile(true);
                mainScreen.setEnableButtonOpenFile(true);
            }
            mainScreen.setSelectRoot(false);
            mainScreen.setInputRootFile("");
        }
    }

    // Возвращает список файлов и папок в каталоге
    private void showDirect(){
        if (mainScreen.isUpdate()){
            mainScreen.setjTextArea("");
            List<File> fileList = direct.takeDirect();
            for (File file : fileList) {
                mainScreen.appendjTextArea(file.getName());
            }
            mainScreen.setUpdate(false);
        }
    }

    // Создает файл или папку в каталоге
    public void createNewFile(){
        if (mainScreen.isCreatedFile()) {
            boolean createdFile = false;
            mainScreen.setCurrentFilePath(direct.getDirest());
            String nameNewObject = direct.getDirest().getName();
            if (nameNewObject.contains(".txt") ) {
                File newfile = new File(mainScreen.getNewFilePart());
                try {
                    createdFile = newfile.createNewFile();
                } catch (IOException ex) {
                    System.out.println("Error created file");
                }
                if(createdFile)
                    System.out.println("File has been created");
            } else {
                File dir = new File(mainScreen.getNewFilePart());
                createdFile = dir.mkdir();
                if(createdFile)
                    System.out.println("Folder has been created");
            }

            mainScreen.setCreatedFile(false);
            mainScreen.setUpdate(true);
            showDirect();
            mainScreen.setInputCreateFile("");
        }
    }
    public void deleteFile(){
        if (mainScreen.isDeleteFile() ){
            mainScreen.setCurrentFilePath(direct.getDirest());
            File deleteFile = new File(mainScreen.getDeleteFilePart());
            boolean statusDelete = deleteFile.delete();
            if(statusDelete && deleteFile.isFile()) {
                System.out.println("File has been deleted");
            } else {
                System.out.println("Folder has been deleted");
            }
            mainScreen.setDeleteFile(false);
            mainScreen.setUpdate(true);
            showDirect();
            mainScreen.setInputDeleteFile("");
        }
    }
    public void openFile(){
        mainScreen.setCurrentFilePath(direct.getDirest());
        if (mainScreen.isOpenFile()){
            File openFile = new File(mainScreen.getOpenNewFile());
            // проверяем что это файл, иначе это папка и открываем её
            if (openFile.isFile()){
                // Инициализация desktop для открытия файла
                Desktop desktop = null;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                // в блоке try...catch открываем файл
                try {
                    desktop.open(openFile);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }else {
                direct.setDirest(openFile);
                mainScreen.setUpdate(true);
                showDirect();
            }

            mainScreen.setOpenFile(false);
            mainScreen.setInputOpenFile("");
            mainScreen.setEnableButtonComeBackFile(true);
        }
    }
    public void comeBackFile(){
        if (mainScreen.isComeBackFile()){
            mainScreen.setCurrentFilePath(direct.getDirest());
            File openComeBackFile = new File(mainScreen.getOpenComeBackFile());
            if (selectedRoot.equals(openComeBackFile.getAbsolutePath())) {
                mainScreen.setEnableButtonComeBackFile(false);
            } else {
                mainScreen.setEnableButtonComeBackFile(true);
            }
            direct.setDirest(openComeBackFile);
            mainScreen.setUpdate(true);
            showDirect();
            mainScreen.setComeBackFile(false);
        }
    }
    public void help(){
        if (mainScreen.isSelectHelp()){
            File helpFile = new File("help/help.docx");
            // Инициализация desktop для открытия файла
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }
            // в блоке try...catch открываем файл
            try {
                desktop.open(helpFile);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            mainScreen.setSelectHelp(false);
        }
    }
}
