package modularmachines.helpers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import modularmachines.api.misc.Bug;
import wasliecore.helpers.FileHelper;
import wasliecore.main.WaslieCore;

public class BugHelper {
	public static void createBugFolder(){
		File file;
		try{
			file = new File(WaslieCore.configLocation  + FileHelper.getSlash() + "Bugs" + FileHelper.getSlash());
			if(!file.exists())
				file.mkdir();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void createBug(String fileName, Bug bug){
		File file;
		try {
			file = new File(WaslieCore.configLocation + FileHelper.getSlash() + "Bugs" + FileHelper.getSlash() + fileName + ".txt");
			file.createNewFile();
			PrintWriter writer = new PrintWriter(WaslieCore.configLocation + FileHelper.getSlash() + "Bugs" + FileHelper.getSlash() + fileName + ".txt", "UTF-8");
			
			writer.println("Mod Name: " + bug.modName);
			writer.println("Minecraft Version: " + bug.mcVersion);
			
			writer.println("Mod Version: " + bug.modVersion);
			writer.println(bug.bug);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}