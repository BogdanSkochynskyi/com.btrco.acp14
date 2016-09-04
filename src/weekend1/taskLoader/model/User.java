package weekend1.taskLoader.model;

import java.util.ArrayList;
import java.util.List;

//TODO: use when Data Base will be ready
public class User
{

    /**
     * Users name
     */
    private String name;

    /**
     * List of tasks that this user created
     */
    private List<Task> tasksCreatedByUser;

    /**
     * List of tasks that this user resolved
     */
    private List<Task> tasksResolvedByUser;

    public User(String name)
    {
        this.name = name;
        this.tasksCreatedByUser = new ArrayList<>();
        this.tasksResolvedByUser = new ArrayList<>();
    }

    /**
     * Add new resolved by user task
     * @param resolvedTask
     */
    public void addResolvedTask(Task resolvedTask)
    {
        for (int i = 0; i < this.tasksResolvedByUser.size(); i++) {
            if (this.tasksResolvedByUser.get(i).getId() == resolvedTask.getId()
                    && this.tasksResolvedByUser.get(i).getTaskScore() >= resolvedTask.getTaskScore())
            {
                System.out.println("Sorry, but this task was resolved by you with bigger or the same score!"); //TODO: we can create our own exception for this situation
            }
            else
            {
                this.getTasksResolvedByUser().add(resolvedTask);
            }
        }
    }

    /**
     * Add new created by user task
     * @param createdTask
     */
    public void addCreatedTask(Task createdTask)
    {
        this.tasksCreatedByUser.add(createdTask);
    }

    /**
     * Return resolved Task by id if it was resolved by user
     * @param id
     * @return
     */
    public Task getResolvedTaskById(int id)
    {
        Task resolvedTask = null;

        for (int i = 0; i < this.tasksResolvedByUser.size(); i++) {
            if (this.tasksResolvedByUser.get(i).getId() == id)
            {
                resolvedTask = this.tasksResolvedByUser.get(i);
                break;
            }
        }

        if (null == resolvedTask)
        {
            System.out.println("Sorry, but you didn't resolve this task yet!");
        }

        return resolvedTask;
    }

    /**
     * Return created Task by id if it was created by user
     * @param id
     * @return
     */
    public Task getCreatedTaskById(int id)
    {
        Task createdTask = null;

        for (int i = 0; i < this.tasksCreatedByUser.size(); i++) {
            if (this.tasksCreatedByUser.get(i).getId() == id)
            {
                createdTask = this.tasksCreatedByUser.get(i);
                break;
            }
        }

        if (null == createdTask)
        {
            System.out.println("Sorry, but this task was created by somebody else!");
        }

        return createdTask;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Task> getTasksCreatedByUser()
    {
        return tasksCreatedByUser;
    }

    public void setTasksCreatedByUser(List<Task> tasksCreatedByUser)
    {
        this.tasksCreatedByUser = tasksCreatedByUser;
    }

    public List<Task> getTasksResolvedByUser()
    {
        return tasksResolvedByUser;
    }

    public void setTasksResolvedByUser(List<Task> tasksResolvedByUser)
    {
        this.tasksResolvedByUser = tasksResolvedByUser;
    }

}
