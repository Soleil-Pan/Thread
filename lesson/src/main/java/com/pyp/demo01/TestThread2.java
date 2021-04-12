package com.pyp.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，实现多线程同步下载图片
public class TestThread2 extends Thread {

    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        webDownloader webDownloader = new webDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件" + name);
    }

    public static void main(String[] args) {
        TestThread2 testThread1 = new TestThread2("https://img.alicdn.com/imgextra/i2/O1CN01curTvL1YaOYqtESds_!!6000000003075-2-tps-520-280.png","1.png");
        TestThread2 testThread2 = new TestThread2("https://img.alicdn.com/imgextra/i3/O1CN01WLtq2P1mMZEBfaMa9_!!6000000004940-2-tps-255-204.png","2.png");
        TestThread2 testThread3 = new TestThread2("https://img.alicdn.com/imgextra/i3/O1CN017fCad823g7bcbTSkK_!!6000000007284-2-tps-300-240.png","3.png");

        testThread1.start();
        testThread2.start();
        testThread3.start();
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
