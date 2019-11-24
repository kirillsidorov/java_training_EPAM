package ua.nure.sidorovk.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

public class Part5 implements Callable<Object> {

    private static final Object MONITOR = new Object();
    private static final int THREADS_NUMBER = 10;
    private static final int COLUMNS = 20;
    private static final int EOL_LENGTH = System.lineSeparator().length();
    private static final String CHARSET_NAME = "UTF-8";

    private RandomAccessFile out;
    private int position;
    private String characters;

    public Part5(RandomAccessFile out, int position, String charToFill) {
        this.out = out;
        this.position = position;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < COLUMNS; i++) {
            stringBuilder.append(charToFill);
        }
        stringBuilder.append(System.lineSeparator());
        this.characters = stringBuilder.toString();
    }

    @Override
    public Object call() throws IOException, InterruptedException {
        RandomAccessFile raf = getRAF();
        synchronized (MONITOR) {
            raf.seek(position);
            raf.write(characters.getBytes(CHARSET_NAME));
            sleep(100);
        }
        return null;
    }

    private RandomAccessFile getRAF() {
        return out;
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String fileName = "part5.txt";
        Files.deleteIfExists(new File(fileName).toPath());
        File file = new File(fileName);
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {

            ExecutorService exe = Executors.newFixedThreadPool(THREADS_NUMBER);

            ArrayList<Future<Object>> futures = new ArrayList<>();

            int position = 0;
            for (int currentNumber = 0; currentNumber < THREADS_NUMBER; currentNumber++) {
                String charToFill = String.valueOf(currentNumber);
                int charsLength = COLUMNS * charToFill.length();
                int stringLength = charsLength + EOL_LENGTH;

                Future<Object> f = exe.submit(new Part5(raf, position, charToFill));
                futures.add(f);

                position += stringLength;
            }

            for (Future<Object> future : futures) {
                future.get();
            }

            exe.shutdown();
        }

        System.out.println(new String(Files.readAllBytes(file.toPath()), CHARSET_NAME));
    }
}