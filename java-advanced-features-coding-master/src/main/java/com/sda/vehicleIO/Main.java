package com.sda.vehicleIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sda.vehicleIO.model.Car;
import com.sda.vehicleIO.model.CarShape;
import com.sda.vehicleIO.model.Motorcycle;
import com.sda.vehicleIO.model.MotorcycleShape;
import com.sda.vehicleIO.model.Tractor;
import com.sda.vehicleIO.model.Transmision;
import com.sda.vehicleIO.model.Vehicle;

public class Main {

	// The PATH to the vehicles.txt file. Please update to your location
	private static final String FILE_PATH = "/Users/Desktop/";
	private static final String FILE_NAME = "vehicles.txt";

	//We wil store all vehicles in this list
	private static List<Vehicle> vehicleList = new ArrayList<>();
	//We will store only cars in this list
	private static List<Car> carList = new ArrayList<>();
	private static List<Motorcycle> motorcycleList = new ArrayList<>();
	private static List<Tractor> tractorList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		readFileAndConvertToObjects(FILE_PATH + FILE_NAME);

		countVehiclesForType();

		countVehiclesPerBrand();

		sortCarsByPrice();

		sortChoppersByTopSpeed();

		displayVehiclesInDifferentFiles();

	}

	/**
	 * Method will read the provided filename and convert each line to a java object 
	 * It will then store the objects into the vehicleList so we can process them more easily
	 * 
	 * https://www.journaldev.com/709/java-read-file-line-by-line
	 */
	public static void readFileAndConvertToObjects(String filename) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();

			// reading line by line until we reach end of file
			while (line != null) {

				// converting each line to an object of type Vehicle
				Vehicle vehicle = convertLineToVehicle(line);

				// storing vehicle in list
				if (vehicle != null) {
					vehicleList.add(vehicle);
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method converts each line into a coresponding java object A line looks like
	 * "Car, Mercedes, C180, 20000, 220, Automatic, Sedan"
	 * 
	 * We split the line by ", " so we get an array 
	 * array[0] = 'Car' 
	 * array[1] = 'Mercedes' 
	 * array[2] = 'C180' 
	 * ....
	 * 
	 */
	public static Vehicle convertLineToVehicle(String line) {
		String[] lineProperties = line.split(", ");
		Vehicle vehicle = null;

		// Depending on the value of lineProperties[0] we know which kind of vehicle we
		// want: car, motorcycle or tractor
		switch (lineProperties[0]) {
		case "Car":
			vehicle = convertLineToCar(lineProperties);
			break;
		case "Motorcycle":
			vehicle = convertLineToMotorcycle(lineProperties);
			break;
		case "Tractor":
			vehicle = convertLineToTractor(lineProperties);
			break;
		}

		return vehicle;
	}

	/**
	 * Setting the different parts of the line as object properties
	 */
	public static Car convertLineToCar(String[] lineProperties) {
		Car car = new Car();

		car.setBrand(lineProperties[1]);
		car.setModel(lineProperties[2]);
		car.setPrice(Integer.valueOf(lineProperties[3]));
		car.setTopSpeed(Integer.valueOf(lineProperties[4]));
		car.setTransmission(Transmision.valueOf(lineProperties[5]));
		car.setShape(CarShape.valueOf(lineProperties[6]));
		return car;
	}

	public static Motorcycle convertLineToMotorcycle(String[] lineProperties) {
		Motorcycle motorcycle = new Motorcycle();

		motorcycle.setBrand(lineProperties[1]);
		motorcycle.setModel(lineProperties[2]);
		motorcycle.setPrice(Integer.valueOf(lineProperties[3]));
		motorcycle.setTopSpeed(Integer.valueOf(lineProperties[4]));
		motorcycle.setShape(MotorcycleShape.valueOf(lineProperties[5]));
		return motorcycle;
	}

	public static Tractor convertLineToTractor(String[] lineProperties) {
		Tractor tractor = new Tractor();

		tractor.setBrand(lineProperties[1]);
		tractor.setModel(lineProperties[2]);
		try {
			tractor.setPrice(Integer.valueOf(lineProperties[3]));
		} catch (Exception e) {
			System.out.println(e);
		}
		tractor.setMaxPulledWeight(Integer.valueOf(lineProperties[4]));
		return tractor;
	}

	/**
	 * Method will split the large Vehicle list into smaller list for cars, motorcycles and tractors
	 */
	public static void countVehiclesForType() {
		// We iterate the whole vehicleList
		// Since we can check what type of object each one is, we store them in their appropriate lists
		for (Vehicle v : vehicleList) {
			if (v instanceof Car) {
				carList.add((Car) v);
			}
			if (v instanceof Motorcycle) {
				motorcycleList.add((Motorcycle) v);
			}
			if (v instanceof Tractor) {
				tractorList.add((Tractor) v);
			}
		}

		System.out.println("Cars: " + carList.size() + " Motorcycles: " + motorcycleList.size() + " Tractors: "
				+ tractorList.size());
	}

	/**
	 * Method will categorize vehicles by brand
	 * 
	 * We can use a map structure where the brand is the key and the list of vehicles of that particular brand is the value
	 */
	public static void countVehiclesPerBrand() {
		Map<String, Integer> vehicleCountPerBrand = new HashMap<String, Integer>();
		
		// We iterate through all the vehicles
		for (Vehicle v : vehicleList) {
			
			// If the brand of the current vehicle already exists in the map, we add the vehicle to the list of vehicles of the same brand
			if (vehicleCountPerBrand.containsKey(v.getBrand())) {
				Integer brandCount = vehicleCountPerBrand.get(v.getBrand());
				brandCount++;
				vehicleCountPerBrand.put(v.getBrand(), brandCount);
			// If the brand doesn't exist we add it to the map
			} else {
				vehicleCountPerBrand.put(v.getBrand(), 1);
			}
		}

		System.out.println(vehicleCountPerBrand);
	}

	/**
	 * Method sorts the carsList by price
	 */
	private static void sortCarsByPrice() {
		
		// We are using a custom Comparator to sort the list
		// https://www.baeldung.com/java-comparator-comparable
		carList.sort(new Comparator<Car>() {

			@Override
			public int compare(Car c1, Car c2) {
				return c1.getPrice() - c2.getPrice();
			}
		});

		for(Car car : carList) {
			System.out.println(car);
		}
	}

	/**
	 * Method sorts choppers by top speed
	 */
	private static void sortChoppersByTopSpeed() {
		// Since we don't want to sort all motorcycles, only the choppers, we create a smaller list in which we only store choppers
		List<Motorcycle> chopperList = new ArrayList<>();

		for (Motorcycle m : motorcycleList) {
			if (m.getShape().equals(MotorcycleShape.CHOPPER)) {
				chopperList.add(m);
			}
		}

		// We sort the chopper list with a comparator
		chopperList.sort(new Comparator<Motorcycle>() {

			@Override
			public int compare(Motorcycle m1, Motorcycle m2) {
				return m1.getTopSpeed() - m2.getTopSpeed();
			}
		});

		for(Motorcycle chopper : chopperList) {
			System.out.println(chopper);
		}
	}

	/**
	 * Method writes the different types of vehicles to different files
	 */
	private static void displayVehiclesInDifferentFiles() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "cars.txt"));

		for (Car c : carList) {
			writer.write(c.toString());
			writer.newLine();
		}

		writer.close();

		writer = new BufferedWriter(new FileWriter(FILE_PATH + "motorcycles.txt"));

		for (Motorcycle m : motorcycleList) {
			writer.write(m.toString());
			writer.newLine();
		}

		writer.close();

		writer = new BufferedWriter(new FileWriter(FILE_PATH + "tractors.txt"));

		for (Tractor t : tractorList) {
			writer.write(t.toString());
			writer.newLine();
		}

		writer.close();
	}

}
