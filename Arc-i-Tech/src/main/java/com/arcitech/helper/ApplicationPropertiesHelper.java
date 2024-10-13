package com.arcitech.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Ajay G.
 */
public class ApplicationPropertiesHelper {
	private static ApplicationPropertiesHelper instance;
	private String appPropsFileName = "./src/main/resources/application.properties";

	private Properties properties;
	private final Scanner sc;

	private ApplicationPropertiesHelper() {
		this.sc = new Scanner(System.in);
	}

	public ApplicationPropertiesHelper(Scanner sc) {
		this.sc = sc;
	}

	public static void start(Scanner sc) throws Exception {
		int choice = 0;
		ApplicationPropertiesHelper setup = new ApplicationPropertiesHelper(sc);
		do {
			System.out.println();
			System.out.println("1. Base setup");
			System.out.println("2. Update all properties");
			System.out.println("3. Update with common/ default properties");
			System.out.println("4. Add local/ new properties");
			System.out.println("5. Create backup");
			System.out.println("6. Restore defualt properties");
			System.out.println("7. Create Properties file");
			System.out.println("8. Exit");
			System.out.print(" Enter your choice: ");
			choice = Integer.parseInt(setup.sc.nextLine());
			switch (choice) {
			case 1:
				System.out.println("\nDoing Base Setup");
				setup.baseSetup();
				break;
			case 2:
				System.out.println("\nUpdate all properties");
				setup.updateAllProperties();
				break;
			case 3:
				System.out.println("\nUpdating common properties");
				setup.updateWithCommonProperties();
				break;
			case 4:
				System.out.println("\nAdd local properties");
				setup.addLocalProperties();
				break;
			case 5:
				System.out.println("\nCreating Backup");
				setup.createBackup();
				break;
			case 6:
				System.out.println("\nRestoring Default Properties");
				setup.restoreDefaultProperties();
				break;
			case 7:
				System.out.println("\nCreating Properties File");
				setup.restoreDefaultProperties();
				break;
			case 8:
				System.out.println("\nThank You!");
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println("Try Again!");
				break;
			}

		} while (choice != 8);
	}

	/**
	 * @return
	 */
	private Map<String, String> defaultCommonProperties() {

		Set<Entry<String, String>> entries = new LinkedHashSet<Map.Entry<String, String>>();

		entries.add(PropertyParser.getEntry(PropType.COMMENT.getValue(), "Spring Boot server configuration"));
		entries.add(PropertyParser.getEntry("server.address", "0.0.0.0"));
		entries.add(PropertyParser.getEntry("server.port", "8000"));
		entries.add(PropertyParser.getEntry(PropType.EMPTYLINE.getValue(), ""));

		entries.add(PropertyParser.getEntry(PropType.COMMENT.getValue(), "MySQL configuration"));
		entries.add(PropertyParser.getEntry("spring.datasource.url", "jdbc:mysql://localhost:3306/arcitech_db"));
		entries.add(PropertyParser.getEntry("spring.datasource.username", "root"));
		entries.add(PropertyParser.getEntry("spring.datasource.password", "root"));
		entries.add(PropertyParser.getEntry("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver"));
		entries.add(PropertyParser.getEntry("spring.jpa.database-platform", "org.hibernate.dialect.MySQL5Dialect"));
		entries.add(PropertyParser.getEntry("spring.jpa.hibernate.ddl-auto", "update"));

		Map<String, String> commonProps = new LinkedHashMap<String, String>();
		for (Entry<String, String> entry : entries) {
			commonProps.put(entry.getKey(), entry.getValue());
		}

		return commonProps;
	}

	/**
	 * 
	 */
	public void baseSetup() {

		LinkedHashMap<String, String> appProps = this.readProperties();

		String port = appProps.getOrDefault("server.port", "8000");

		System.out.println("Default SpringBoot server port: " + port);
		System.out.print("Do you want to change spingboot server port (Y/N): ");
		String ch = this.sc.nextLine();
		if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {
			System.out.print("Enter SpringBoot server port: ");
			port = this.sc.nextLine();
		}
		appProps.put("server.address", "0.0.0.0");
		appProps.put("server.port", port);

		port = appProps.containsKey("spring.datasource.url") ? appProps.get("spring.datasource.url").substring(
				appProps.get("spring.datasource.url").lastIndexOf(':') + 1,
				appProps.get("spring.datasource.url").lastIndexOf('/')) : "3306";
		System.out.println("Default MySQL server port: " + port);
		System.out.print("Do you want to change MySQL server port (Y/N): ");
		ch = this.sc.nextLine();
		if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {
			System.out.print("Enter MySQL server port: ");
			port = this.sc.nextLine();
		}
		appProps.put("spring.datasource.url", "jdbc:mysql://localhost:" + port + "/arcitech_db");

		String username = appProps.get("spring.datasource.username");
		String password = appProps.get("spring.datasource.password");
		System.out.println("Default MySQL username: '" + username + "' and password: '" + password + "'");
		System.out.print("Do you want to change MySQL Username (Y/N): ");
		ch = this.sc.nextLine();
		if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {
			System.out.print("Enter MySQL Username: ");
			username = this.sc.nextLine();
		}
		System.out.print("Do you want to change MySQL Password (Y/N): ");
		ch = this.sc.nextLine();
		if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {
			System.out.print("Enter MySQL Password: ");
			password = this.sc.nextLine();
		}
		appProps.put("spring.datasource.username", username);
		appProps.put("spring.datasource.password", password);

		System.out.println(appProps.toString());
		this.writeProperties(appProps);
	}

	public void updateAllProperties() {
		LinkedHashMap<String, String> appProps = this.readProperties();
		int entryCount = 1;
		for (Entry<String, String> entry : appProps.entrySet()) {
			if (entry.getKey().startsWith(PropType.EMPTYLINE.getValue())) {
				continue;
			}

			System.out.println("ENTRY: " + entryCount++);
			boolean isComment = false;
			if (entry.getKey().startsWith(PropType.COMMENT.getValue())) {
				isComment = true;
				System.out.println("# " + entry.getValue());
			} else {
				System.out.println(entry);
			}
			System.out.print("Do you want to modify above entry(Y/N): ");
			String ch = this.sc.nextLine();
			if (ch.charAt(0) != 'Y' && ch.charAt(0) != 'y') {
				System.out.println();
				continue;
			}
			if (isComment) {
				System.out.print("Enter updated comment for '" + entry.getValue() + "': ");
			} else {
				System.out.print("Enter value for property '" + entry.getKey() + "':");
			}
			String newVal = this.sc.nextLine();
			appProps.put(entry.getKey(), newVal);
			System.out.println();
		}
		this.writeProperties(appProps);

	}

	public void restoreDefaultProperties() {
		this.writeProperties(this.defaultCommonProperties());
	}

	public void addLocalProperties() {
		Map<String, String> localProps = new LinkedHashMap<String, String>();

		String comment = "Local Properties - " + LocalDateTime.now();
		System.out.print("Do you want to add description/ comment for local properties(y/n): ");
		String ch = this.sc.nextLine();
		if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {
			System.out.print("Enter comment: ");
			comment = this.sc.nextLine();
		}
		Entry<String, String> emptyEntry = PropertyParser.getEntry(PropType.EMPTYLINE.getValue(), "");
		Entry<String, String> commentEntry = PropertyParser.getEntry(PropType.COMMENT.getValue(), "");
		localProps.put(emptyEntry.getKey(), emptyEntry.getValue());
		localProps.put(commentEntry.getKey(), comment);

		System.out.print("How many local properties you wanted to add: ");
		int n = Integer.parseInt(this.sc.nextLine());
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.println("Local Property-" + (i + 1));
			System.out.print("Enter property key: ");
			String key = this.sc.nextLine();
			System.out.print("Enter propery value for'" + key + "': ");
			String value = this.sc.nextLine();
			localProps.put(key, value);
		}

		LinkedHashMap<String, String> appProps = this.readProperties();
		appProps.putAll(localProps);

		this.writeProperties(appProps);

	}

	public void updateWithCommonProperties() {
		Map<String, String> commonProps = this.defaultCommonProperties();
		LinkedHashMap<String, String> appProps = this.readProperties();

		Entry<String, String> emptyEntry = PropertyParser.getEntry(PropType.EMPTYLINE.getValue(), "");
		Entry<String, String> commentEntry = PropertyParser.getEntry(PropType.COMMENT.getValue(),
				"New Common Properties");

		appProps.put(emptyEntry.getKey(), emptyEntry.getValue());
		appProps.put(commentEntry.getKey(), commentEntry.getValue());

		for (Entry<String, String> entry : commonProps.entrySet()) {
			if (entry.getKey().startsWith(PropType.COMMENT.getValue())
					|| entry.getKey().startsWith(PropType.EMPTYLINE.getValue())) {
				continue;
			}
			appProps.put(entry.getKey(), entry.getValue());
		}

		this.writeProperties(appProps);
	}

	public void createBackup() {
		LinkedHashMap<String, String> appProps = this.readProperties();
		StringBuilder sb = new StringBuilder(this.appPropsFileName);

		int i = sb.lastIndexOf(".");
		sb.replace(i, i + 1, "_");
		sb.insert(this.appPropsFileName.lastIndexOf('/') + 1, LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
				+ "_" + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME).toString().replace(":", ".") + "_");
		sb.append(".backup");

		this.writeProperties(sb.toString(), appProps);
	}

	/**
	 * @return
	 */
	private LinkedHashMap<String, String> readProperties() {

		LinkedHashMap<String, String> defaultProps = new LinkedHashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(this.appPropsFileName));) {

			reader.lines().forEach(new PropertyParser(defaultProps));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		properties = new Properties();
		properties.putAll(defaultProps);
		return defaultProps;
	}

	private void writeProperties(String newFileName, Map<String, String> props) {
		String actualFileName = this.appPropsFileName;
		this.appPropsFileName = newFileName;
		this.writeProperties(props);
		this.appPropsFileName = actualFileName;
	}

	/**
	 * @param props
	 */
	private void writeProperties(Map<String, String> props) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.appPropsFileName));) {
			for (Entry<String, String> entry : props.entrySet()) {
				if (entry.getKey().startsWith(PropType.COMMENT.getValue())) {
					writer.write("# " + entry.getValue());
				} else if (entry.getKey().startsWith(PropType.EMPTYLINE.getValue())) {
					writer.newLine();
					continue;
				} else {
					writer.write(entry.getKey() + "=" + entry.getValue());
				}
				writer.newLine();
			}
			System.out.println("Properties file updated successfully with comments retained!");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return (String) this.properties.get(key);
	}

	public static ApplicationPropertiesHelper getInstance() {
		if (ApplicationPropertiesHelper.instance == null) {
			ApplicationPropertiesHelper.instance = new ApplicationPropertiesHelper();
			ApplicationPropertiesHelper.instance.properties = new Properties();
			ApplicationPropertiesHelper.instance.properties
					.putAll(ApplicationPropertiesHelper.instance.readProperties());
		}
		return ApplicationPropertiesHelper.instance;
	}
}

/**
 * @author Ajay G.
 */
enum PropType {
	EMPTYLINE("EMPTYLINE"), COMMENT("COMMENT");

	private String value;

	PropType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}

/**
 * @author Ajay G.
 */
class PropertyParser implements Consumer<String> {
	final Map<String, String> map;
	private static int newLineCount = 0;
	private static int commentCount = 0;

	public PropertyParser(Map<String, String> map) {
		if (map != null) {
			this.map = map;
		} else {
			this.map = new LinkedHashMap<String, String>();
		}
	}

	@Override
	public void accept(String line) {
		if (line == null) {
			System.out.println("NULL line.");
			return;
		}
		if (line.trim().isEmpty()) {
			map.put(PropType.EMPTYLINE.getValue() + newLineCount++, "");
		} else if (line.startsWith("#")) {
			map.put(PropType.COMMENT.getValue() + commentCount++, line.substring(1).trim());
		} else if (line.contains("=")) {
			String[] pair = line.split("=");
			map.put(pair[0].trim(), pair[1].trim());
		} else {
			System.out.println("Invalid line!");
		}
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public static Entry<String, String> getEntry(String key, String value) {
		if (key == null || key.trim().isEmpty()) {
			System.out.println("NULL or EMPTY key.");
			return null;
		}

		if (key.equalsIgnoreCase(PropType.EMPTYLINE.getValue())) {
			return new AbstractMap.SimpleEntry<String, String>(PropType.EMPTYLINE.getValue() + newLineCount++, "");
		}
		if (key.equalsIgnoreCase(PropType.COMMENT.getValue())) {
			return new AbstractMap.SimpleEntry<String, String>(PropType.COMMENT.getValue() + commentCount++,
					value == null ? "" : value.trim());
		}
		return new AbstractMap.SimpleEntry<String, String>(key.trim(), value.trim());
	}
}