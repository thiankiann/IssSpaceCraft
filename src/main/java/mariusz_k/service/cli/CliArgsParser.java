package mariusz_k.service.cli;

import mariusz_k.config.AppConfig;
import mariusz_k.config.CliUsageConfig;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.util.Optional;


import static mariusz_k.config.CliUsageConfig.*;

public class CliArgsParser {
    private final CommandLineParser cmdParser = new DefaultParser();

    public AppConfig parseAppConfig(String... args) throws ParseException {
        final var cmd = cmdParser.parse(CliUsageConfig.CLI_OPTIONS, args);
        return new AppConfig(
                cmd.hasOption(DEBUG),
                Optional.ofNullable(cmd.getOptionValue(DB_USER)).orElseThrow(() -> cmdArgMissing(DB_USER)),
                Optional.ofNullable(cmd.getOptionValue(DB_PASS)).orElseThrow(() -> cmdArgMissing(DB_PASS)),
                Optional.ofNullable(cmd.getOptionValue(DB_NAME)).orElseThrow(() -> cmdArgMissing(DB_NAME)),
                Optional.ofNullable(cmd.getOptionValue(DB_HOST)).orElseThrow(() -> cmdArgMissing(DB_HOST))
        );
    }

    private ParseException cmdArgMissing(String cmdArg) {
        return new ParseException("Cmd argument '" + cmdArg + "' is missing.\n");
    }
}
