package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.setupstrategy;

import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.ExtractionPhase;

public class CommandLineArgumentsSetupStrategy extends SetupStrategy {
	
	public CommandLineArgumentsSetupStrategy(UserInterface userInterface) {
		super(userInterface);
	}

	@Override
	public ExtractionPhase configureExtractionPhase() throws FatalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasTimeframe() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getEndDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unused")
	private Option[] parseArguments(String[] args)  {
		
		//EXAMPLE OF USE
		Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;
        
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar aec.jar", options);

            System.exit(1);
            return null;
        }
        
        
        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");

        System.out.println(inputFilePath);
        System.out.println(outputFilePath);
        
        return cmd.getOptions();
        
	}
	
}
