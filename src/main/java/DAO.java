import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DAO {
    private final String File;

    public DAO(String File) {
        this.File = File;
    }

    public Stream fileReader() throws IOException {
        return Files.lines(Paths.get(File));
    }
}
