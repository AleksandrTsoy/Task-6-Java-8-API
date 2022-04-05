import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FileReader {

    public List<String> fileReader(String fileName) throws IOException, URISyntaxException {
        Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI());
        return Files.lines(path).collect(toList());
    }
}
