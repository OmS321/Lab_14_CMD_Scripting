import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile = new Scanner(System.in);
        Path target;
        String rec;


        if (args.length > 0)
        {
            target = Path.of(System.getProperty("user.dir"), args[0]);

            try
            {
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(target, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                System.out.println("Reading file: " + target.toAbsolutePath() + "...");

                while(reader.ready())
                {
                    rec = reader.readLine();
                    // echo to screen
                    System.out.println(rec);
                }

                inFile.close();
            }
            catch (IOException e)
            {
                System.out.println("Uh Oh! Something went wrong while reading the file.");
                throw new RuntimeException(e);
            }

        }
        else
        {
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
                        // echo to screen
                        System.out.println(rec);
                    }

                    inFile.close();
                }
                else   // User did not pick a file, closed the chooser
                {
                    System.out.println("Sorry, you must select a file.");
                    System.out.println("Run the program again!");
                    System.exit(0);
                }
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

    }
}