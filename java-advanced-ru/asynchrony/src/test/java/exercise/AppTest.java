package exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {
    private String destPath;

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    @BeforeEach
    void beforeEach() throws Exception {
        destPath = Files.createTempFile("test", "tmp").toString();
    }

    @Test
    void testUnion() throws Exception {
        CompletableFuture<String> result = App.unionFiles(
            "src/test/resources/file1.txt",
            "src/test/resources/file2.txt",
            destPath
        );
        result.get();

        String actual = Files.readString(getFullPath(destPath));
        assertThat(actual).contains("Test", "Message");
    }

    @Test
    void testUnionWithNonExistedFile() throws Exception {

        String result = tapSystemOut(() -> {
            App.unionFiles("nonExistingFile", "file", destPath).get();
        });

        assertThat(result.trim()).contains("NoSuchFileException");
    }

    // BEGIN
    @Test
    void testDirectorySize() throws Exception {
        Long resourcesSize = 90L;
        Long dirSize = 77L;
        Long nestedSize = 51L;

        String resourcesPath = "src/test/resources";
        String dirPath = "src/test/resources/dir";
        String nestedPath = "src/test/resources/dir/nested";

        assertThat(App.getDirectorySize(resourcesPath).get()).isEqualTo(resourcesSize);
        assertThat(App.getDirectorySize(dirPath).get()).isEqualTo(dirSize);
        assertThat(App.getDirectorySize(nestedPath).get()).isEqualTo(nestedSize);

    }
    // END
}
