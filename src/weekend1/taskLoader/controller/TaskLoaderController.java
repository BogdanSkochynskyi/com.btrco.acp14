package weekend1.taskLoader.controller;

import weekend1.taskLoader.model.Task;
import weekend1.taskLoader.view.Menu;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class TaskLoaderController {

    private static final int EXIT_CODE = -1;
    private static final int ADD_NEW_TASK_CODE = 1;
    private static final int FIND_TASK_BY_ID_CODE = 2;
    private static final int PERFORM_TASK_CODE = 3;
    private static final int SHOW_STATISTIC_CODE = 4;

    /**
     * Scanner for user inputs reading
     */
    private Scanner scanner;

    /**
     * Test tasks
     */
    private Task[] tasks;

    public TaskLoaderController() {
        this.scanner = new Scanner(System.in);
        this.createTestData(new Random().nextInt(10) + 1);
    }

    /**
     * I don't want to use serialization except of DataBase, so I use this test data
     *
     * @param amountOfTestTAsks
     */
    private void createTestData(int amountOfTestTAsks) {

        System.out.println(amountOfTestTAsks);
        this.tasks = new Task[amountOfTestTAsks];
        for (int i = 0; i < amountOfTestTAsks; i++) {
            this.tasks[i] = new Task(i + 1, "Test title " + (i * 2), "Test description " + (i * 3414), "/Users/mac/IdeaProjects/com.btrco.acp14/src/weekend1/tasks/Task" + (i + 1) + ".java");
        }

    }

    /**
     * Handling of main menu select
     */
    public void mainMenuSelectResult() {
        int usersChoose = 0;
        while (usersChoose == 0) {
            if (scanner.hasNextInt()) {
                usersChoose = scanner.nextInt();
                if (usersChoose > 4 || usersChoose < -1) {
                    this.showWrongNumberError();
                    usersChoose = 0;
                    scanner.nextLine();
                }
            } else {
                this.showWrongNumberError();
                scanner.nextLine();
            }

        }

        this.chooseOperation(usersChoose);
    }

    /**
     * Show error if user tryed to select wrong number or if it's not a number
     */
    private void showWrongNumberError() {
        System.out.println("You are selected wrong menu number, please select \ncorrect menu number or -1 to exit program ");
    }

    /**
     * Choose main menu operation to execute
     *
     * @param usersChoose
     */
    private void chooseOperation(int usersChoose) {

        switch (usersChoose) {
            case EXIT_CODE: {
                System.out.println("Bye bye!");
                break;
            }
            case ADD_NEW_TASK_CODE: {
                Menu.showNewTaskAddingMenu();
                this.scanner.nextLine();
                this.newTaskMenuSelectResult();
                break;
            }
            case FIND_TASK_BY_ID_CODE: {
                Menu.showFindTaskByIdMenu();
                this.scanner.nextLine();
                this.printTaskInfo(this.findTaskById());
                break;
            }
            case PERFORM_TASK_CODE: {
                Menu.showPerformTaskMenu();
                this.performTaskSelectResult();
                break;
            }
            case SHOW_STATISTIC_CODE: {
                break;
            }
        }

    }

    /**
     * Print information about task
     * @param taskById
     */
    private void printTaskInfo(Task taskById) {

        System.out.println("Task id: " + taskById.getId());
        System.out.println("Task title: " + taskById.getTitle());
        System.out.println("Task description: " + taskById.getDescription());
        System.out.println("Task path: " + taskById.getJavaFilePath());
    }

    /**
     * Handling of new task menu select
     */
    private void newTaskMenuSelectResult() {
        String taskPoints[] = new String[3];
        for (int i = 0; i < 3; i++) {
            taskPoints[i] = this.scanner.nextLine();
        }
        Task newTask = new Task(0, taskPoints[0], taskPoints[1], taskPoints[2]);//TODO: add dynamic id.

        System.out.println("This is your added task, is all ok?");
        System.out.println("Task id: " + newTask.getId());
        System.out.println("Task title: " + newTask.getTitle());
        System.out.println("Task description: " + newTask.getDescription());
        System.out.println("Task path: " + newTask.getJavaFilePath());
        //TODO: add verifying by user and YES\NO menu, then if YES - add task to DB, if NO - provide possibility to fix task points or remove task
    }

    /**
     * Handling perform task menu select
     */
    private void performTaskSelectResult() {
        int usersChoose = 0;
        while (usersChoose == 0) {
            if (scanner.hasNextInt()) {
                usersChoose = scanner.nextInt();
                if (usersChoose > 2 || usersChoose < -1) {
                    this.showWrongNumberError();
                    usersChoose = 0;
                    scanner.nextLine();
                }
            } else {
                this.showWrongNumberError();
                scanner.nextLine();
            }

            switch (usersChoose) {
                case 1: {
                    Menu.showTaskResolveMenu();
                    this.scanner.nextLine();
                    this.taskResolveMenu();
                    break;
                }
                case 2: {
                    Menu.showTaskCheckMenu();
                    this.scanner.nextLine();
                    this.checkTheTask();
                    break;
                }
                case -1: {
                    System.out.println("Bye bye!");
                    break;
                }
            }

        }
    }

    /**
     * Handling yes-no task resolve menu
     */
    private void taskResolveMenu() {

        Task foundTask = findTaskById();
        Menu.showTaskDescription(foundTask);
        this.scanner.nextLine();

        int userChoose = 0;
        while (userChoose == 0) {
            if (this.scanner.hasNextInt()) {
                userChoose = this.scanner.nextInt();
                if (userChoose > 2 || userChoose < 1) {
                    this.showWrongNumberError();
                    userChoose = 0;
                    scanner.nextLine();
                } else {
                    Menu.showCopyTaskToResolveMenu();
                    this.scanner.nextLine();
                    String path = this.scanner.nextLine();
                    this.copyTaskToResolve(foundTask, path);
                }
            }

        }

    }

    /**
     * Handling find task by id menu
     */
    private Task findTaskById()
    {
        Task foundTask = null;
        while (null == foundTask) {
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                for (int i = 0; i < this.tasks.length; i++) {
                    if (this.tasks[i].getId() == id) {
                        foundTask = this.tasks[i];
                    }
                }
            } else {
                this.showWrongNumberError();
            }

        }
        return foundTask;
    }

    /**
     * Copying initial task file to path that user set.
     * If it is another package - it will changed in the new file;
     * If it is another file name - it will be changed in the new file;
     *
     * @param taskToCopy
     * @param path
     */
    private void copyTaskToResolve(Task taskToCopy, String path) {

        try {
            System.out.println(taskToCopy.getJavaFilePath());
            File initialTaskFile = new File(taskToCopy.getJavaFilePath());
            File newTaskFile = new File(path);

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(initialTaskFile)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newTaskFile)));
            String line;

            while ((line = br.readLine()) != null) {
                //As it is java file, the package in code should be correct
                if (line.contains("package")) {
                    line = line.replace(taskToCopy.getJavaFilePath().split("/")[7], path.split("/")[7]);
                }
                //As it is java file, the Class name in code should be the same as the name of file
                if (line.contains(taskToCopy.getJavaFilePath().split("/")[8].split("\\.")[0])) {
                    line = line.replace(taskToCopy.getJavaFilePath().split("/")[8].split("\\.")[0], path.split("/")[8].split("\\.")[0]);
                }
                bw.write(line + System.getProperty("line.separator"));
                bw.flush();

            }

            System.out.println("File is copied successful!");
            System.out.println("Now you can open task file in your favorite redactor and resolve the task.");
            System.out.println("After resolving you can run this program again and check the result.");
            System.out.println("You can find your task file by next path:\n" + path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check is the task file compiled without errors.
     */
    private void checkTheTask() {
        String path = null;
        while (null == path) {
            path = this.scanner.nextLine();
        }

        Process p = null;
        StringBuffer sb = new StringBuffer();
        String error = "";
        try {
            p = Runtime.getRuntime().exec("javac " + path);
            p.waitFor();
            error = readAll(p.getErrorStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == error || error.equals("")) {
            System.out.println("You did it! Your task compiled without errors. But I don't now is result is correct =) " +
                    "\nThis functionality will be in the next version of this program.\nSee you later!");
            System.out.println(sb.toString());
        } else {
            System.out.println("Something wrong:");
            System.out.println(error);
        }


    }

    /**
     * Read error stream
     *
     * @param is
     * @return
     */
    private String readAll(InputStream is) {
        StringBuffer output = new StringBuffer();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}