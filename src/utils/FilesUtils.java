package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thk on 6/20/19.
 */
public class FilesUtils {

    public static String GetResourcesFile(String fileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileName);
        try {
            return readAllLines(inputStream);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    public static String readAllLines(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        List<String> data = new ArrayList<>();
        String result="";
        for (String line; (line = br.readLine()) != null; ) {
            result+=line;
        }
        return result;
    }
}
