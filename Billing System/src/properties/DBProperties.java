package properties;

import gui.dialog.MessageDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class DBProperties
{
	public DBProperties()
	{
		OutputStream output = null;
		// Path path = Paths.get("config.properties");
		try
		{
			Boolean exists = new File("config.properties").isFile();
			// if (Files.notExists(path))
			if (!exists)
			{
				Properties prop = new Properties();

				output = new FileOutputStream("config.properties");

				// set the properties value
				prop.setProperty("database", "localhost");
				prop.setProperty("dbname", "citycabletvnetwork");
				prop.setProperty("url", "jdbc:mysql://localhost:3306/");
				prop.setProperty("user", "root");
				prop.setProperty("password", "root");

				// save properties to project root folder
				prop.store(output, null);
			}
		}
		catch (IOException io)
		{
			new MessageDialog("Error", io.getMessage());
			io.printStackTrace();
		}
		finally
		{
			if (output != null)
			{
				try
				{
					output.close();
				}
				catch (IOException e)
				{
					new MessageDialog("Error", e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return Properties
	 * */
	public Properties getPropFile()
	{
		Properties prop = new Properties();

		FileInputStream input = null;

		try
		{
			String filename = "config.properties";
			// String workingDir = System.getProperty("user.dir");
			// ClassLoader loader =
			// Thread.currentThread().getContextClassLoader();
			// input = loader.getResourceAsStream(filename);
			input = new FileInputStream(filename);
			// if (input == null)
			// {
			// new MessageDialog("Error",
			// "Sorry, unable to find properties file = " + filename);
			// return null;
			// }
			//
			// load a properties file from class path, inside static method
			prop.load(input);
		}
		catch (IOException ex)
		{
			new MessageDialog("Error", "Sorry, unable to find/load properties file");
			prop = null;
			ex.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					new MessageDialog("Error", "Sorry, unable to find/load properties file");
					prop = null;
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}