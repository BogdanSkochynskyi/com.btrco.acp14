package weekend1.taskLoader.model;

public class Task
{

    /**
     * Task id
     */
    private int id;

    /**
     * Task title
     */
    private String title;

    /**
     * Task description
     */
    private String description;

    /**
     * Path to java file with task template
     */
    private String javaFilePath;

    /**
     * Score that user get after resolving this task
     */
    private int taskScore; //TODO: if there is only resolve or not resolve state, change by boolean

    public Task(int id, String title, String description, String javaFilePath)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.javaFilePath = javaFilePath;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getJavaFilePath()
    {
        return javaFilePath;
    }

    public void setJavaFilePath(String javaFilePath)
    {
        this.javaFilePath = javaFilePath;
    }

    public int getTaskScore()
    {
        return taskScore;
    }

    public void setTaskScore(int taskScore)
    {
        this.taskScore = taskScore;
    }
}
