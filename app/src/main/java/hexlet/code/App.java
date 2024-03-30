package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "gendiff",
        version = "1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {

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

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

    }
}