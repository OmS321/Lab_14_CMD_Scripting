import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector
{
    public static void main(String []args) throws IOException
    {
        JFileChooser chooser = new JFileChooser();

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        String rec;
        Scanner inFile = new Scanner(System.in);

        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("test.txt");
        // set the chooser to the current directory
        chooser.setCurrentDirectory(target.toFile());

        ArrayList<String> lines = new ArrayList<>();

        try
        {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                target = chooser.getSelectedFile().toPath();

                //inFile = new Scanner(target);
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(target, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                System.out.println("Reading file: " + target.toAbsolutePath() + "...");

                while(reader.ready())
                {
                    rec = reader.readLine();
                    lines.add(rec);
                    lineCount++;
                    charCount += rec.length();
                    wordCount += rec.split("\\s+").length; // split by whitespace
                    // echo to screen
                    System.out.printf("\nLine %4d %-60s ", lineCount, rec);
                }

                System.out.println("\n\nLine Count: " + lineCount);
                System.out.println("Word Count: " + wordCount);
                System.out.println("Character Count: " + charCount);

                inFile.close();
            }
            else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file! Terminating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
