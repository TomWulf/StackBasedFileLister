import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class Main
{
    public static void main(String[] args)
    {

            File selectedFile;
            JFileChooser chooser = new JFileChooser();

            Stack<File> stack = new Stack<>();

            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                stack.push(selectedFile);

                while(!stack.isEmpty())
                {
                    File f = stack.pop();

                    File [] dList = f.listFiles();

                    if(dList != null) // Hack not sure why but it returns hidden directories/files fails without this
                        for (File sf : dList)
                        {
                            if (sf.isFile() )
                            {
                                System.out.println("Found a File: " + sf.getAbsolutePath());
                            }
                            else if (sf.isDirectory() && !sf.isHidden())
                            {
                                System.out.println("Found a Directory: " + sf.getAbsolutePath());
                                stack.push(sf);
                            }
                        }
                }
            }
            else
            {
                System.out.println("You did not choose a directory to scan!");
                System.exit(0);
            }



    }
}