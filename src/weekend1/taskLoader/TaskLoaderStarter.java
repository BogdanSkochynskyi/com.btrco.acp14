package weekend1.taskLoader;

import weekend1.taskLoader.controller.TaskLoaderController;
import weekend1.taskLoader.view.Menu;

public class TaskLoaderStarter
{

    public static void main(String[] args)
    {
        Menu.showInitialMenu();
        TaskLoaderController controller = new TaskLoaderController();
        controller.mainMenuSelectResult();
    }

}
