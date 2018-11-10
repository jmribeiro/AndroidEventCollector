package pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.setupstrategy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import pt.ribeiro.util.FileSystem;
import pt.ribeiro.util.ShellCommandExecuter;
import pt.ribeiro.util.Time;
import pt.ribeiro.util.userinterface.UserInterface;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.AndroidEventCollector;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.R;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FatalException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.FolderDoesntExistException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.MoreThanOneUSBDeviceConnectedException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.MoreThanOneVirtualDeviceRunningException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NoDevicesConnectedException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NoImagesInstalledException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NoPreviousExtractionsException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.NoVirtualDevicesRunningException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.exception.RequiredToolsNotInstalledException;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.module.Module;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.EmptyExtractionPhase;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.ExtractionPhase;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.DeviceExtractionStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.ExtractionStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.FolderExtractionStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.USBDeviceExtractionStrategy;
import pt.ulisboa.tecnico.csf.grupo1.androideventcollector.phase.phasestrategy.extractionstrategy.VirtualDeviceExtractionStrategy;

public class UserConfigurationStrategy extends SetupStrategy{
	
	public UserConfigurationStrategy(UserInterface userInterface) {
		super(userInterface);
	}

	public ExtractionPhase configureExtractionPhase() throws FatalException{
		
		_userInterface.displayLine("\nWhat Do You Wish To Do?");
		List<String> options = new ArrayList<String>();
		options.add("Analyze A Device");
		options.add("Analyze An Image Folder");
		options.add("Analyze USB Connected Device (Requires Only One USB Connected Device)");
		options.add("Analyze Virtual Device (Requires Only One Virtual Device)");
		options.add("Load A Previous Extraction");
		options.add("Exit");
		
		ExtractionStrategy extractionStrategy = null;
		
		while(true){
			int command = _userInterface.getMenuItem(options);
			try{
				extractionStrategy = parseStrategyCommand(command);
				return new ExtractionPhase(_userInterface, extractionStrategy, configureModules(), R.DEFAULT_EXTRACTIONS_FOLDER);
			} catch (LoadingPreviousExtractionException e){
				String previousExtractionFolder = e.getExtractionFolder();
				return new EmptyExtractionPhase(_userInterface, previousExtractionFolder);
			} catch (NoImagesInstalledException | NoPreviousExtractionsException | NoDevicesConnectedException | NoVirtualDevicesRunningException | FolderDoesntExistException | MoreThanOneUSBDeviceConnectedException | MoreThanOneVirtualDeviceRunningException | NumberFormatException  e) {
				_userInterface.displayLine("\n>>> "+e.getMessage()+"\n");
			}
		}

	}

	public List<String> configureModules(){
		List<String> availableModules = getAvailableModules();
		displayAvailableModules(availableModules);
		return requestModules(availableModules);
	}
	
	
	private ExtractionStrategy parseStrategyCommand(int command) throws RequiredToolsNotInstalledException, NoDevicesConnectedException, FolderDoesntExistException, MoreThanOneUSBDeviceConnectedException, NoVirtualDevicesRunningException, MoreThanOneVirtualDeviceRunningException, LoadingPreviousExtractionException, NoImagesInstalledException, NoPreviousExtractionsException{
		switch(command){
			case 1:
				String deviceId = loadDevice();
				return new DeviceExtractionStrategy(deviceId);
			case 2:
				String imagePath = loadDeviceImage();
				return new FolderExtractionStrategy(imagePath);
			case 3:
				checkUSBDeviceConnection();
				return new USBDeviceExtractionStrategy();
			case 4:
				checkVirtualDevice();
				return new VirtualDeviceExtractionStrategy();
			case 5:
				String previousExtractionFolder = loadPreviousExtractionFolder();
				throw new LoadingPreviousExtractionException(previousExtractionFolder);
			case 0:
				AndroidEventCollector.getInstance().terminate();
			default:
				throw new NumberFormatException();
		}

	}
	
	private void checkVirtualDevice() throws NoVirtualDevicesRunningException, MoreThanOneVirtualDeviceRunningException {
		String output = ShellCommandExecuter.exec("adb -e pull /init.rc "+R.TEMPORARY_FOLDER);
		if(output.contains("no emulators found")) throw new NoVirtualDevicesRunningException();
		if(output.contains("more than one emulator")) throw new MoreThanOneVirtualDeviceRunningException();
	}

	private void checkUSBDeviceConnection() throws NoDevicesConnectedException, MoreThanOneUSBDeviceConnectedException {
		String output = ShellCommandExecuter.exec("adb -d pull /init.rc "+R.TEMPORARY_FOLDER);
		if(output.contains("no devices found")) throw new NoDevicesConnectedException();
		if(output.contains("more than one device")) throw new MoreThanOneUSBDeviceConnectedException();
	}

	private String loadDevice() throws RequiredToolsNotInstalledException, NoDevicesConnectedException {
		List<String> availableDevices = getAvailableDevices();
		displayAvailableDevices(availableDevices);
		return requestDeviceId(availableDevices);
	}
	
	private String loadDeviceImage() throws FolderDoesntExistException, NoImagesInstalledException{
		
		List<String> images = Arrays.asList(FileSystem.getDirectoryNamesFromFolder(R.DEFAULT_IMAGES_FOLDER));
		if(images.size()==0) throw new NoImagesInstalledException();
		
		_userInterface.displayLine("Open an image");
		
		for (String image : images){
			_userInterface.displayLine(image);
		}
		
		String answer = _userInterface.getUserInput("Type in the name of the image");
		while(!images.contains(answer)){
			answer = _userInterface.getUserInput("Type the complete folder name [example image#1]");
		}
		
		return R.DEFAULT_IMAGES_FOLDER+"/"+answer;
	}
	
	private String loadPreviousExtractionFolder() throws FolderDoesntExistException, NoPreviousExtractionsException{

		List<String> extractions = Arrays.asList(FileSystem.getDirectoryNamesFromFolder(R.DEFAULT_EXTRACTIONS_FOLDER));
		if(extractions.size()==0) throw new NoPreviousExtractionsException();
		_userInterface.displayLine("Open previous extractions");
		
		for (String extraction : extractions){
			_userInterface.displayLine(extraction);
		}
		
		String answer = _userInterface.getUserInput("Type in the name of the extraction");
		while(!extractions.contains(answer)){
			answer = _userInterface.getUserInput("Type the complete folder name, not just the number [example extraction#1]");
		}
		
		return R.DEFAULT_EXTRACTIONS_FOLDER+"/"+answer;
	}

	private void displayAvailableDevices(List<String> availableDevices) throws RequiredToolsNotInstalledException, NoDevicesConnectedException {
		
		_userInterface.displayLine("\nAvailable Devices:");
		
		for(int deviceNumber = 0; deviceNumber < availableDevices.size(); deviceNumber++){
			String currentDevice = availableDevices.get(deviceNumber);
			_userInterface.displayLine(deviceNumber+" | "+currentDevice);
		}
	}
	
	private List<String> getAvailableDevices() throws RequiredToolsNotInstalledException, NoDevicesConnectedException {
		
		String output = ShellCommandExecuter.exec("adb devices");
		List<String> lines = Arrays.asList(output.split("\n"));
		
		//Remove Empty Lines
		lines.remove("");
		
		return readDevicesFromADBOutput(lines);
	}

	private List<String> readDevicesFromADBOutput(List<String> lines)
		throws RequiredToolsNotInstalledException, NoDevicesConnectedException
	{
		List<String> devices = new ArrayList<String>();
		
		if(!lines.get(0).equals("List of devices attached")){
			throw new RequiredToolsNotInstalledException("ADB (Android Debug Bridge)");
		}
		
		for(String line : lines){
			if(line.endsWith("device")){
				String[] lineParts = line.split("\t");
				String deviceId = lineParts[0];
				
				devices.add(deviceId);
			}
		}
		
		if(devices.size() == 0){
			throw new NoDevicesConnectedException();
		}else{
			return devices;
		}
		
	}

	private String requestDeviceId(List<String> availableDevices) {
		boolean hasValidDevice = false;
		
		int deviceNumber = 0;
		while(!hasValidDevice){
			_userInterface.displayLine("Which Device Do You Wish To Use?");
			String userInputLine = _userInterface.getUserInput();
			deviceNumber = Integer.parseInt(userInputLine);
			hasValidDevice = deviceNumber >= 0 && deviceNumber < availableDevices.size();
		}
		
		return availableDevices.get(deviceNumber);
	}
	
	private List<String> getAvailableModules() {
		return Module.installedModules();
	}
	
	private void displayAvailableModules(List<String> availableModules) {
		_userInterface.displayLine("\nAvailable Modules:");
		
		for(int moduleNumber = 0; moduleNumber < availableModules.size(); moduleNumber++){
			String currentModule = availableModules.get(moduleNumber);
			_userInterface.displayLine(moduleNumber+" | "+currentModule);
		}
		
	}
	
	private List<String> requestModules(List<String> availableModules) {
		

		List<String> selectedModules = new ArrayList<String>();
		List<Integer> selectedModuleNumbers = new ArrayList<Integer>();
		
		boolean hasAllModules = false;

		while(!hasAllModules){
			
			if(selectedModules.size() == availableModules.size()){
				//We have all
				break;
			}
			
			displaySelectedModules(selectedModules);
			
			_userInterface.displayLine("Which Modules Do You Wish To Use?");
			_userInterface.displayLine("Type 'all' To Select All Modules");
			_userInterface.displayLine("Type 'continue' To Exit Module Selection");
			
			String userInputLine = _userInterface.getUserInput();
			
			if(userInputLine.equals("continue")){
				break;
			}else if(userInputLine.equals("all")){
				return availableModules;
			}else{
				Integer currentNumber = Integer.parseInt(userInputLine);
				
				boolean isValidModuleNumber = currentNumber >= 0 && currentNumber < availableModules.size() && !selectedModuleNumbers.contains(currentNumber);
				
				if(isValidModuleNumber){
					selectedModuleNumbers.add(currentNumber);
					selectedModules.add(availableModules.get(currentNumber));
				}
			}
		
		}
		
		displaySelectedModules(selectedModules);
		return selectedModules;
	}
	
	private void displaySelectedModules(List<String> selectedModules){
		String modules = "[";
		for(String selectedModule : selectedModules){
			modules+=selectedModule+" ";
		}
		modules+="]";
		_userInterface.display("Selected Modules: ");
		_userInterface.displayLine(modules);
	}

	@Override
	public boolean hasTimeframe() {
		String answer = _userInterface.getUserInput("Do you wish to analyse within a timeframe? (Y/N)");
		while ( ! ((answer.equals("Y")) || (answer.equals("y")) || (answer.equals("N")) || (answer.equals("n")))){
			answer = _userInterface.getUserInput("Please type only Y or N");
		}
		return answer.equals("Y") || answer.equals("y");
	}

	@Override
	public Date getStartDate() {
		
		Date startDate;
		String answer = _userInterface.getUserInput("Enter start date (dd/mm/aaaa):");
		while(true){
			try{
				startDate = Time.createDate(answer);
				break;
			}catch(ParseException e){
				answer = _userInterface.getUserInput("Please enter a date in format (dd/mm/aaaa) [example 1/1/2017]:");
			}
		}
		return startDate;

	}

	@Override
	public Date getEndDate() {
		Date startDate;
		String answer = _userInterface.getUserInput("Enter end date (dd/mm/aaaa):");
		while(true){
			try{
				startDate = Time.createDate(answer);
				break;
			}catch(ParseException e){
				answer = _userInterface.getUserInput("Please enter a date in format (dd/mm/aaaa) [example 01/01/2017 (zeros matter)]:");
			}
		}
		return startDate;
	}
}