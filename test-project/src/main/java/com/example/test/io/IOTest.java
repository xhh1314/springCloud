package com.example.test.io;

import java.io.*;

public class IOTest {

    public static void main(String[] args) throws IOException {

       // test1();
        test2();

    }

    /**
     * 测试字节流
     *
     * @throws IOException
     */
    private static void test1() throws IOException {
        String path = IOTest.class.getClassLoader().getResource("").getPath();
        File file = new File(path + "ThreadLocalTest.txt");
        FileInputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] bytes = new byte[128];
        while (inputStream.read(bytes) > 0) {
            //把数据读取到一个byteArray中
            outputStream.write(bytes);
            byte[] bytes1 = outputStream.toByteArray();
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int read1 = bufferedInputStream.read();

    }

    private static void test2() throws IOException {
        String path = IOTest.class.getClassLoader().getResource("").getPath();
        File file = new File(path + "ThreadLocalTest.txt");
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        int read = inputStreamReader.read();
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
    }
}
