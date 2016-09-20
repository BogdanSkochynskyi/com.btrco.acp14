package weekend3.multithreading.join;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadJoin {

    public static void main(String[] args) {

        File folder = new File("E:\\IdeaProjects\\com.btrco.acp14");

        File[] files = folder.listFiles();

        List<Thread> threads = new ArrayList<>();
        List<AsynchFileSearchTask> tasks  = new ArrayList<>();
        List<File> resultFiles  = new ArrayList<>();

        for (File file : files) {
            if (file.isDirectory())
            {
                AsynchFileSearchTask task = new AsynchFileSearchTask(file, "Test");
                Thread thread = new Thread(task);
                threads.add(thread);
                tasks.add(task);

                thread.start();
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join(); //мейн заснет пока поток не закончит свою работу. слип с условием. Блокирующий метод.
                // Если передать в метод время, то это означает, что если метод не закончится до этого времени,
                // то мейн после этого времени все равно проснется thread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (AsynchFileSearchTask task : tasks) {
            List<File> resFiles1 = task.getResult();
            resultFiles.addAll(resFiles1);
        }

        resultFiles.forEach(System.out::println);


    }

}

class AsynchFileSearchTask implements Runnable
{
    private File root;

    private String keyWord;

    private List<File> result;

    public AsynchFileSearchTask(File root, String keyWord) {
        this.root = root;
        this.keyWord = keyWord;
        result = new ArrayList<>();
    }

    @Override
    public void run() {
        recSearch(root);
    }

    private void recSearch(File dir) {
        if (dir == null)
        {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0)
        {
            return;
        }

        Arrays.stream(files)
                .forEach((file) -> {
                    if (file.isDirectory())
                    {
                        recSearch(file);
                    }
                    else if (file.getName().contains(keyWord))
                    {
                        result.add(file);
                    }
                });
    }

    public List<File> getResult()
    {
        return result;
    }
}