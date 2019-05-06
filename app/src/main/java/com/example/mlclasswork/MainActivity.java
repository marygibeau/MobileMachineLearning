package com.example.mlclasswork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.opencsv.CSVReader;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream in = getResources().openRawResource(R.raw.iris_training);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, Charset.forName("UTF-8")));
        String line = "";
        Dataset d = new DefaultDataset();
        try {
            while((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                double[] temp = new double[] {Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])};
                Instance tempi = new DenseInstance(temp, tokens[4]);
                d.add(tempi);
                Log.v("mine","index 1: " + tokens[0] + " index 1: " + tokens[1] + " index 2: " + tokens[2] + " index 3: " + tokens[3] + " index 4: " + tokens[4]);
            }
        } catch (IOException e) {
            Log.v("mine", "somethin happened");
        }

        Classifier c = new KNearestNeighbors(3);
        c.buildClassifier(d);

        InputStream in1 = getResources().openRawResource(R.raw.iris_test);
        BufferedReader reader1 = new BufferedReader(
                new InputStreamReader(in1, Charset.forName("UTF-8")));
        String line1 = "";
        try {
            while((line1 = reader1.readLine()) != null) {
                String[] tokens = line1.split(",");
                double[] temp = new double[] {Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3])};
                Instance tempi = new DenseInstance(temp);
                Log.v("mine","that's a : " + c.classify(tempi));
            }
        } catch (IOException e) {
            Log.v("mine", "somethin happened");
        }

    }
}
