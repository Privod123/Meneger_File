import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Root {

    private File[] list;
    private List<String> listRoot;

    public Root() {
        this.list = File.listRoots();
        this.listRoot = new ArrayList<>();
        for (File file : list) {
            listRoot.add(file.getAbsolutePath());
        }
    }

    public List<String> getRoot(){
        return listRoot;
    }

}
