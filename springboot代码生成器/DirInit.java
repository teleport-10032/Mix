package teleport;

import java.io.File;

public class DirInit {
    public DirInit(String workspace, String workspace2)
    {
        File wsDir = new File(workspace);
        File ws2Dir = new File(workspace+"/"+workspace2);
        if(!wsDir.exists()) {
            wsDir.mkdir();
        }
        if(!ws2Dir.exists())
        {
            ws2Dir.mkdir();
        }
    }
}
