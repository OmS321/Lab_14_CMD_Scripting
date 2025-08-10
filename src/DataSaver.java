import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner (System.in);
        ArrayList<String> data = new ArrayList<>();
        PrintWriter writer;

        do
        {
            data.add(SafeInput.getNonZeroLenString(in, "Enter your first name"));

            data.add(SafeInput.getNonZeroLenString(in, "Enter your last name"));

            data.add(String.valueOf(SafeInput.getRangedInt(in, "Enter your 6-digit ID number", 100000, 999999)));
            in.nextLine();

            data.add(SafeInput.getRegExString(in, "Enter your email address", "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"));

            data.add(String.valueOf(SafeInput.getRangedInt(in, "Enter your birth year", 1900, 2025)));
            in.nextLine();
        }
        while (SafeInput.getYNConfirm(in, "Do you want to enter another record?"));

        System.out.println(data);

        File directory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(directory.getPath() + "\\src\\data.csv");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(new FileOutputStream(file.toFile()));
            writer = new PrintWriter(new OutputStreamWriter(out));

            for (int i = 0; i < data.size(); i += 5)
            {
                String[] row = new String[5];
                row[0] = data.get(i); // first name
                row[1] = data.get(i + 1); // last name
                row[2] = data.get(i + 2); // ID number
                row[3] = data.get(i + 3); // email address
                row[4] = data.get(i + 4); // birth year

                for (int j = 0; j < row.length; j++)
                {
                    writer.write(row[j], 0, row[j].length());
                    if (j < row.length - 1) writer.print(", ");
                }
                writer.println();
            }
            writer.close();
            System.out.println("Data file has been written to: " + file.toAbsolutePath());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}
