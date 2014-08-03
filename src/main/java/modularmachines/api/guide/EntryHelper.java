/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.guide;

import java.util.ArrayList;
import java.util.HashMap;

public class EntryHelper {
	public static void addEntry(String title, IEntry[] entry, int page){
		if(page > maxPages)
			maxPages = page;
			
		titles.add(new Title(title, page));
		entries.put(title, entry);
	}
	
	public static int maxPages = 0;
	public static ArrayList<Title> titles = new ArrayList<Title>();
	public static HashMap<String, IEntry[]> entries = new HashMap<String, IEntry[]>();
}