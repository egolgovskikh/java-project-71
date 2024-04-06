package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import lombok.Setter;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        version = "1.0",
        description = """
                Compares two configuration files and shows a difference.
                      filepath1         path to first file
                      filepath2         path to second file"""
)
@Setter
public final class App implements Callable<Object> {

    @Option(
            names = {"-h", "--help"},
            description = "Show this help message and exit.",
            usageHelp = true
    )
    private boolean help;

    @Option(
            names = {"-V", "--version"},
            description = "Print version information and exit.",
            versionHelp = true
    )
    private boolean version;

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format"
    )
    private String format = "stylish";

    @Parameters(index = "0")
    private String filepath1;
    @Parameters(index = "1")
    private String filepath2;

    public static void main(String[] args) {
        App app = new App();
        int exitCode = new CommandLine(app).execute(args);
        System.exit(exitCode);
    }

    @Override
    public String call() throws IOException {
        try {
            String result = Differ.generate(
                    filepath1,
                    filepath2,
                    format
            );
            System.out.println(result);
            return result;
        } catch (IOException e) {
            throw new IOException("Error entering filepath: " + e.getMessage());
        }
    }
}
