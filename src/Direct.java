import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Direct {

    File direct;

    public Direct() {
    }

    public File getDirest() { return direct; }

    public void setDirest(File direct) {
        this.direct = direct;
    }

    public List<File> takeDirect(){
        return new ArrayList<>(Arrays.asList(direct.listFiles()));
    }

}
