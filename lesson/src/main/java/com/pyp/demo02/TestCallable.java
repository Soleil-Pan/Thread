package com.pyp.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//创建线程方式三：实现callable接口
public class TestCallable implements Callable<Boolean> {

    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        webDownloader webDownloader = new webDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://img.alicdn.com/imgextra/i2/O1CN01curTvL1YaOYqtESds_!!6000000003075-2-tps-520-280.png","1.png");
        TestCallable t2 = new TestCallable("https://img.alicdn.com/imgextra/i3/O1CN01WLtq2P1mMZEBfaMa9_!!6000000004940-2-tps-255-204.png","2.png");
        TestCallable t3 = new TestCallable("https://img.alicdn.com/imgextra/i3/O1CN017fCad823g7bcbTSkK_!!6000000007284-2-tps-300-240.png","3.png");

        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = service.submit(t1);
        Future<Boolean> r2 = service.submit(t2);
        Future<Boolean> r3 = service.submit(t3);
        //获取结果

        Boolean rs1 = r1.get();
        Boolean rs2 = r2.get();
        Boolean rs3 = r3.get();

        //关闭服务
        service.shutdownNow();
    }

}



//下载器
class webDownloader{
    //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
