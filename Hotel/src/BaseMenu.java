import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Map;

abstract class BaseMenu
{
    String header = "";
    Map<Integer, Method> menuActions;
    Map<Integer, String> menuDescriptions;
    long currentUserID = -1;

    public Method getAction(int id)
    {
        if (menuActions.get(id) != null)
        {
            return menuActions.get(id);
        }

        return null;
    }

    public String getHeader()
    {
        return header;
    }

    public Map<Integer, Method> getMenuActions()
    {
        return menuActions;
    }

    public Map<Integer, String> getActionDescriptions()
    {
        return menuDescriptions;
    }

    public static LocalDate dateInput(String userInput)
    {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date;

        try
        {
            date = LocalDate.parse(userInput, dateFormat);
        }
        catch (DateTimeParseException e)
        {
            return null;
        }

        return date;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        String logo = "\n" +
                "       _   _       _       _   _____          \n" +
                "      | | | |     | |     | | |_   _|         \n" +
                "      | |_| | ___ | |_ ___| |   | |_   _ _ __ \n" +
                "      |  _  |/ _ \\| __/ _ \\ |   | | | | | '__|\n" +
                "      | | | | (_) | ||  __/ |   | | |_| | |   \n" +
                "      \\_| |_/\\___/ \\__\\___|_|   \\_/\\__,_|_|   \n" +
                "                                version 1.0\n" +
                "                                        \n";
        String separator = "+" + String.join("", Collections.nCopies(50, "-")) + "+\n";

        builder.append(logo);
        builder.append(separator);
        builder.append("|");

        int cat_spacer = 25 - (int)Math.ceil((float)header.length()/2);

        builder.append(String.join("", Collections.nCopies(cat_spacer, " ")));
        builder.append(header);
        builder.append(String.join("", Collections.nCopies(cat_spacer, " ")));

        if ((header.length() % 2) == 1)
        {
            builder.append(" |\n");
        }
        else
        {
            builder.append("|\n");
        }

        builder.append("|" + String.join("", Collections.nCopies(50, " ")) + "|\n");

        for (Map.Entry<Integer, String> entry : menuDescriptions.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            builder.append("| ");
            String action = "[" + key + "] " + value;
            builder.append(action);
            builder.append(String.join("", Collections.nCopies(49 - action.length(), " ")));
            builder.append("|\n");
        }

        builder.append(separator);

        return builder.toString();
    }
}
