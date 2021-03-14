package com.home.samples.old;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class ReadFileBenchMark {

    @Param({
            "C:\\Users\\kgromov\\Desktop\\Credentials.txt",
            "C:\\eula.1031.txt",
            "C:\\Windows\\WinSxS\\x86_microsoft-windows-servicingstack_31bf3856ad364e35_10.0.17134.165_none_8ed574c0651001db\\GlobalInstallOrder.xml",
          /*  "C:\\Users\\kgromov\\Desktop\\nds-compilier-stuff\\Conditions_in_Routing.docx",
            "C:\\Users\\kgromov\\Desktop\\nds-compilier-stuff\\RDF Reference Manual v2018.Q1.pdf",*/

    })
    private String filePath;

    @Benchmark
    public void bufferedFileReader_JDK_1_7(Blackhole bh) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                bh.consume(strCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void scannerAndFile_JDK_1_7_8(Blackhole bh) {
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                bh.consume(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    @Benchmark
    public void dataInputStream_JDK_1_7_8(Blackhole bh) {
        try (DataInputStream reader = new DataInputStream(new FileInputStream(filePath));) {
            String result = reader.readUTF();
            bh.consume(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Benchmark
    public void filesBufferedReader_JDK_1_8(Blackhole bh) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))){
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                bh.consume(strCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void filesReadAll_JDK_1_8(Blackhole bh) {
        try {
            List<String> allLines =  Files.readAllLines(Paths.get(filePath));
            bh.consume(allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void filesReadChannel_JDK_1_8(Blackhole bh) {
        try (RandomAccessFile reader = new RandomAccessFile(filePath, "r");
             FileChannel channel = reader.getChannel();)
        {
            ByteBuffer buff = ByteBuffer.allocate((int) channel.size());
            channel.read(buff);
            buff.flip();
            String value = new String(buff.array());
            bh.consume(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (DataInputStream reader = new DataInputStream(new FileInputStream( "C:\\Users\\kgromov\\Desktop\\Credentials.txt"))) {
            String result = reader.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
