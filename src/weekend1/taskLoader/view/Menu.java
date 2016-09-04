package weekend1.taskLoader.view;

import weekend1.taskLoader.model.Task;

import java.security.PublicKey;

public class Menu
{

    public static void showInitialMenu()
    {
        System.out.println("~~~~~~~~~Welcome to the new code learning program!~~~~~~~~~");
        System.out.println("Choose your destiny:");
        System.out.println("1. Add new task.");
        System.out.println("2. Find task by id.");
        System.out.println("3. Perform task.");
        System.out.println("4. Show my statistic.");
        System.out.println("-1. Exit.");
    }

    public static void showPerformTaskMenu()
    {
        System.out.println("What do you want?");
        System.out.println("1. Resolve a task.");
        System.out.println("2. Check a task.");
        System.out.println("-1. Return to main menu.");
    }

    public static void showTaskResolveMenu()
    {
        System.out.println("Please, write task id that you want to resolve:");
    }

    public static void showTaskCheckMenu()
    {
        System.out.println("Please, write task path to verify your resolving:");
    }

    public static void showTaskDescription(Task task)
    {
        System.out.println("This is task that you want to resolve:");
        System.out.println("Task title: " + task.getTitle());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Do you want to resolve this task?");
        System.out.println("1. YES.");
        System.out.println("2. NO.");
    }

    public static void showCopyTaskToResolveMenu()
    {
        System.out.println("Please, write path where task will be copied");
    }


    public static void showNewTaskAddingMenu()
    {
        System.out.println("Great! You want to create new task!");
        System.out.println("Please write next points of a task divided by enter:");
        System.out.println("1. Task title.");
        System.out.println("2. Task description.");
        System.out.println("3. Task file path.");
        System.out.println("And then press enter.");
    }

    public static void showFindTaskByIdMenu()
    {
        System.out.println("Please, write task id that you want to find:");
    }

}
