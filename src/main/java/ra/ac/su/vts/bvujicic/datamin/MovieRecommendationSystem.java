package ra.ac.su.vts.bvujicic.datamin;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.neighboursearch.LinearNNSearch;
import weka.core.neighboursearch.NearestNeighbourSearch;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemovePercentage;

import java.util.*;

public class MovieRecommendationSystem {

    public static void main(String[] args) {
        try {
            // Load the dataset
            Instances data = loadData("Result_9.arff");

            // Split the data into training and testing sets
            Instances trainingData = splitData(data, 80);
            Instances testingData = splitData(data, 20);

            // Configure the collaborative filtering algorithm
            NearestNeighbourSearch collaborativeFilter = new LinearNNSearch();
            collaborativeFilter.setDistanceFunction(new EuclideanDistance());
            collaborativeFilter.setInstances(trainingData);

            // Generate recommendations for a user
            int userId = 10; // Example: Recommend movies for User 10
            Instances recommendations = generateRecomendationInstances(collaborativeFilter, trainingData, userId);

            // Print distinct movie recommendations
            System.out.println("Movie Recommendations:");

            for (Instance movie : recommendations) {
                System.out.print(movie.value(recommendations.attribute("rating")) + " ");
                System.out.println(movie.stringValue(recommendations.attribute("title")));
            }

            // Evaluate the recommendation system
            Evaluation evaluation = evaluateRecommendationSystem(trainingData, testingData, recommendations);
            System.out.println(evaluation.toSummaryString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load the dataset
    private static Instances loadData(String filePath) throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(filePath);
        return source.getDataSet();
    }

    // Split the data into training and testing sets
    private static Instances splitData(Instances data, int percentage) throws Exception {
        RemovePercentage splitFilter = new RemovePercentage();
        splitFilter.setPercentage(percentage);
        splitFilter.setInvertSelection(true);
        splitFilter.setInputFormat(data);
        return Filter.useFilter(data, splitFilter);
    }


    // Generate movie recommendations for a user
    private static Instances generateRecomendationInstances(NearestNeighbourSearch collaborativeFilter, Instances trainingData, int userId) throws Exception {
        Instance user = trainingData.get(userId - 1); // User IDs start from 1
        Instances recommendations = collaborativeFilter.kNearestNeighbours(user, 50); // Generate 200 recommendations

        System.err.println(recommendations.size());

        List<String> distinctRecommendations = new ArrayList<>();
        for (int i = 0; i < recommendations.numInstances(); i++) {
            String movieTitle = recommendations.instance(i).stringValue(trainingData.attribute("title"));
            if (!distinctRecommendations.contains(movieTitle)) {
                distinctRecommendations.add(movieTitle);
            }
        }
        Instances distinctRecomendationInstances = new Instances(recommendations);
        int capacity = distinctRecomendationInstances.size();
        Set<String> set = new HashSet<>(capacity);
        distinctRecomendationInstances.removeIf(r -> !set.add(r.stringValue(recommendations.attribute("title"))));

        distinctRecomendationInstances.sort(Comparator.comparing(o -> o.value(recommendations.attribute("rating"))));
        Collections.reverse(distinctRecomendationInstances);
        if (distinctRecomendationInstances.size() > 10) {
            while (distinctRecomendationInstances.size() > 10) {
                distinctRecomendationInstances.remove(10);
            }
        }
        return distinctRecomendationInstances;
    }


    // Evaluate the recommendation system
    private static Evaluation evaluateRecommendationSystem(Instances trainingData, Instances testingData, Instances recommendations) throws Exception {
        // Merge the training data and recommendations
        Instances mergedData = new Instances(trainingData);

        for (int i = 0; i < recommendations.numInstances(); i++) {
            mergedData.add(recommendations.instance(i));
        }

        // Build and evaluate a classifier on the merged data
        mergedData.setClassIndex(mergedData.numAttributes() - 1);
        trainingData.setClassIndex(mergedData.numAttributes() - 1);
        testingData.setClassIndex(mergedData.numAttributes() - 1);

        Classifier classifier = new NaiveBayes();
        classifier.buildClassifier(mergedData);

        //print out capabilities
        System.out.println(classifier.getCapabilities().toString());

        Evaluation evaluation = new Evaluation(trainingData);
        evaluation.evaluateModel(classifier, testingData);

        return evaluation;
    }
}
