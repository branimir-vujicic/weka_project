package ra.ac.su.vts.bvujicic.datamin;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

//import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;

import weka.core.converters.ConverterUtils.DataSource;
public class LoadSaveData{
    public static void main(String args[]) throws Exception{
        DataSource source = new DataSource("Result_9.csv");
        Instances dataset = source.getDataSet();

        System.out.println(dataset.toSummaryString());

        ArffSaver saver = new ArffSaver();
        saver.setInstances(dataset);
        saver.setFile(new File("Result_9.arff"));
        saver.writeBatch();
    }
}
