import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

public class FileSelect 
{
	private static Component parentComponent = null;
	private static File file = null;
	private static final JFileChooser fileChoosed = new JFileChooser();
	
	public FileSelect (Component parent) 
	{
		setParentComponent(parent);
	}
	public FileSelect () 
	{
		setParentComponent(null);
	}
	
	public Component getParentComponent() 
	{
		return parentComponent;
	}
	public void setParentComponent(Component parent) 
	{
		this.parentComponent = parent;
	}

	//This function returns the file reference, after testing if a file was selected. Returns null otherwise. 
	public static File getFile() throws FileNotFoundException
	{
		if (openDialog())
		{
			return file;
		}
		
		else
		{
			System.out.println("File was not selected");
			throw new FileNotFoundException();
		}
	}
	
	/* This function displays the Open File dialog box and return the file reference 
	 * if selected by the user, or null if dismissed or error
	 */
	private static boolean openDialog()
	{
		//Opens the Open File dialog box, and return either true if file was selected or false if error or dismissed
		int fileChoosedStatus = fileChoosed.showOpenDialog(parentComponent);
				
		if (fileChoosedStatus == JFileChooser.APPROVE_OPTION) 
		{
			file = fileChoosed.getSelectedFile();
			return true;
        } 
		
		else 
		{
            return false;
        }
	}
}
