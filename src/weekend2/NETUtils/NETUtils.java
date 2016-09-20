package weekend2.NETUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class NETUtils {

    public static void save(String url, String destination) throws URISyntaxException, IOException {
        InputStream stream = new URI(url).toURL().openStream();
        OutputStream outputStream = new FileOutputStream(destination);

        byte[] bytes = new byte[1028];
        int counter = 0;

        while ((counter = stream.read(bytes)) != -1)
        {
            outputStream.write(bytes, 0, counter);
            outputStream.flush();
        }

        stream.close();
        outputStream.close();
    }

}
