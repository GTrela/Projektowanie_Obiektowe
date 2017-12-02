import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

abstract class BaseMenu
{
    String header = "";
    Map<Integer, Method> menuActions;
    Map<Integer, String> menuDescriptions;

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

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        String separator = "+" + String.join("", Collections.nCopies(50, "-")) + "+\n";

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
