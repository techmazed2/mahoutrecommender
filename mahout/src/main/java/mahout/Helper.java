package mahout;

import java.io.File;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: BCE8959
 * Date: 12/11/14
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Helper {

    public static File readFile(){
        URL url = Helper.class.getClassLoader().getResource("dataset.csv");
        return new File(url.getPath());
    }
}
