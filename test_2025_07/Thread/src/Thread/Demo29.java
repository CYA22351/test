    package Thread;

    import java.util.concurrent.Callable;
    import java.util.concurrent.ExecutionException;
    import java.util.concurrent.FutureTask;

    /**
     * @author ：陈奕安（3048279304@qq.com）
     * @date ：Created in 2025/7/13 11:34
     * @description：
     * @modified By：
     * @version:
     */
    public class Demo29 {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            //此时callable不能执行，需要借助Thread
            Callable<Integer> callable=new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int result=0;
                    for (int i=1;i<=100;i++){
                        result+=i;
                    }
                    return result;
                }
            };
            FutureTask<Integer> futureTask=new FutureTask<>(callable);
            Thread t=new Thread(futureTask);
            t.start();
            //get操作就是获取FutureTask的返回值，这个返回值就是来自于Callable的call方法
            //get可能会阻塞，如果当前线程执行完毕，get会拿到结果
            //如果当前线程还没执行完毕，get会一直阻塞
            System.out.println(futureTask.get());
        }
    }