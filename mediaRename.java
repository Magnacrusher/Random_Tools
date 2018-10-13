import java.io.*;
import javax.swing.JFileChooser;
import java.io.FilenameFilter;

public class mediaRename
{
	public static void main(String[] args) throws IOException
	{
		bulkRename(true); //True to apply changes, false to only display changes that would be made
		
		//Some tests of the renamer
//        getUpdatedName("blah.blah.2010.1080p.mp4");
//        getUpdatedName("The two part file (Part 1 of 2).avi");
//        getUpdatedName("The two part file (Part 2 of 2).avi");
//        getUpdatedName("Oh no weird brackets [2014] extra garbage lolol.avi");
//        getUpdatedName("these conventions are horribad.2014.a-b_c.avi");
//        getUpdatedName("Y not zoidberg (2013) zoiddyzoid.net");
//        getUpdatedName("Lemon pledge.avi");
		
	}
	
	public static void bulkRename(boolean applyChange) throws IOException
	{
		// Allow user to select file to process
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(1);
		int returnVal = chooser.showOpenDialog(null);
		
		// Output the selection
		System.out.println(returnVal);
		String fullPath=chooser.getCurrentDirectory()+"\\"+chooser.getSelectedFile().getName();
		System.out.println("File Path: "+fullPath);
		
		// Print all files in that folder
		File folder = new File(fullPath);
		
		// Only grab the videos in the directory
		FilenameFilter extensionFilter = new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
            {
				String[] allowed = new String[]{".mkv",".avi",".mp4"};
                for(String format : allowed)
                {
                	if(name.endsWith(format))
                		return true;
                }
                return false;
            }
		};
		
		File[] files = folder.listFiles(extensionFilter);
		
		for(File currentFile : files)
		{
			String rawPath=currentFile.toPath().toString().replaceAll("[^\\\\](?!.*\\\\.*)", "");
			String newName=getUpdatedName(currentFile.getName());
			File newFile = new File(rawPath+newName);
			if(newName.compareTo(currentFile.getName())!=0)
			{
				if(newFile.exists())
				{
					System.out.println("Error, file: "+newName+" already exists.");
					throw new java.io.IOException("file exists");
				}
				else if(applyChange)
				{
					currentFile.renameTo(newFile);
				}
				System.out.println("Name Updated: ["+currentFile.getName()+"] to ["+newName+"]");
			}
			else
			{
				System.out.println("No Change Made: "+currentFile.getName());
			}
		}
	}
	
	// Name fixer
	public static String getUpdatedName(String name)
	{
		name=name.replaceAll("\\.(?!...$)", " "); //Replace all periods not followed by ***<EOL>
		name=name.replaceAll("\\d{4}.*(?=\\.)", ""); //deletes all years -> extension
		name=name.replaceAll("[\\[\\(]*(?=\\.)", ""); //deletes bracketty characters that may remain
		name=name.replaceAll(" *(?=\\.)", ""); //deletes ending spaces that may remain
		return name;
	}
}
