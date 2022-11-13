package cz.mateusz.saltech;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ApplicationManager {

    public static List<String> getOpenApplications(List<String> commands) {
        LinkedList<String> linkedList = new LinkedList<>();

        for(String command : commands) {
            Optional<String> application = identifyApplicationInCommand(command);
            if(application.isPresent()) {
                linkedList.add(application.get());
                continue;
            }

            if(isClearCommand(command)) {
                linkedList.clear();
                continue;
            }

            int closedAppsCounter = countApplicationsToBeClosed(command);
            if(closedAppsCounter == 0) continue;
            if(closedAppsCounter == 1 ) linkedList.removeLast();
            if(closedAppsCounter > 1) {
                while(linkedList.size() > 0 && closedAppsCounter > 0) {
                    linkedList.removeLast();
                    closedAppsCounter--;
                }
            }
        }
        return linkedList;
    }

    public static boolean isClearCommand(String command) {
        return command.equals("clear");
    }

    public static int countApplicationsToBeClosed(String command) {
        if(!command.startsWith("close")) return 0;
        int count = Integer.valueOf(command.substring(command.indexOf(" ") + 1));
        return count;
    }

    public static Optional<String> identifyApplicationInCommand(String command) {
        String application = null;
        if(isOpenApplicationCommand(command)) {
            String[] commandParts = command.split(" ");
            application = commandParts[commandParts.length - 1];
        }
        return Optional.ofNullable(application);
    }

    public static boolean isOpenApplicationCommand(String command) {
        if(command.startsWith("open")) return true;
        return false;
    }
}
