package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TeleOp
public class PerformanceTester extends LinearOpMode {
    ExecutorService service;
    int threadCount;
    int maxThreads;
    ElapsedTime t;
    Queue<Double> times;
    int tests;
    int testNum;

    double ratio;
    double percentage;
    int steps;
    String bar = "";
    double time;

    String data = "";
    File file;
    volatile int testCount;

    synchronized void add() {
        testCount++;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Hello? Is this thing on?");
        t = new ElapsedTime();
        times = new LinkedList();
        threadCount = -1;
        maxThreads = 75;
        tests = 10;
        testNum = 1;


        waitForStart();

        while (opModeIsActive() && (threadCount < maxThreads || testNum <= tests)) {
            if (threadCount > maxThreads || threadCount < 0) {
                threadCount = 0;
                testNum++;
                createFile();
            }
            threadCount++;
            runTest();
            printData();
            saveToFile();
            telemetry.addData("thread number: ", threadCount);
            telemetry.update();
        }


    }

    public void runTest() {
        telemetry.addLine("Starting test number: " + threadCount);
        service = Executors.newFixedThreadPool(threadCount);
        telemetry.addLine("Created Executor");
        t.reset();
        for (int i = 0; i < maxThreads; i++) {
            service.execute(() -> {
                add();
            });
        }
        time = t.milliseconds();
        times.add(time);
        telemetry.addData(threadCount + " finished processes in ", time + " milliseconds");
    }


    public void printData() {
        telemetry.addData("Testing addition on ", threadCount + " threads out of " + maxThreads);
        ratio = (double) threadCount / maxThreads * 10.0;
        percentage = ratio * 10.0;
        steps = (int) ratio;


        bar = "|";
        for (int i = 0; i <= 10; i++) {
            if (i <= steps) {
                bar += "=";
            } else {
                bar += "-";
            }
        }
        bar += "|  ";
        bar += percentage + "%";

        telemetry.addData("Progress: ", bar);
    }

    public void createFile() {
        String filename = "PerformanceTest_" + new Date().getTime() + ".csv";
        file = AppUtil.getInstance().getSettingsFile(filename);
    }

    public void saveToFile() {
        data += threadCount + ", " + times.poll() + "\n";


        ReadWriteFile.writeFile(file, data);
    }
}
