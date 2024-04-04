package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

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
public class App implements Callable<Object> {

    @Option(
            names = {"-h", "--help"},
            description = "Show this help message and exit.",
            usageHelp = true
    )
    boolean help;

    @Option(
            names = {"-V", "--version"},
            description = "Print version information and exit.",
            versionHelp = true
    )
    boolean version;

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format"
    )
    String format;

    @Parameters(index = "0")
    String filepath1;
    @Parameters(index = "1")
    String filepath2;

    public static void main(String[] args) {
        App app = new App();
        int exitCode = new CommandLine(app).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Object call() {
        try {
            String result = Differ.generate(
                    FileUtils.readFile(filepath1),
                    FileUtils.readFile(filepath2)
            );
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Ошибка ввода пути к файлу");
        }
        return null;
    }
}