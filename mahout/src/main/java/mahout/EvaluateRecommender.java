package mahout;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: BCE8959
 * Date: 12/11/14
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class EvaluateRecommender implements RecommenderBuilder {

    /**
     * Now we have to create the code for the test. We'll check how much the recommender misses the real interaction strength on average.
     * We employ an AverageAbsoluteDifferenceRecommenderEvaluator for this. The following code shows how to put the
     * pieces together and run a hold-out test
     * @throws IOException
     * @throws TasteException
     */
    public void holdOutTest() throws IOException, TasteException {
        DataModel model = new FileDataModel(new File(AppConstants.TEST_FILE));
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder builder = new EvaluateRecommender();
        double result;
        for(int i = 0; i < 5; i++){
            result = evaluator.evaluate(builder, null, model, 0.9, 1.0);
            System.out.println(result);
        }
    }

    /**
     * One way to check whether the recommender returns good results is by doing a hold-out test.
     * We partition our dataset into two sets: a trainingset consisting of 90% of the data and a testset consisting of 10%.
     * Then we train our recommender using the training set and look how well it predicts the unknown interactions in the testset.
     * To test our recommender, we create a class called EvaluateRecommender with a main method and add an class called
     * EvaluateRecommender that implements the RecommenderBuilder interface. We implement the buildRecommender method and make it
     * setup our user-based recommender
     * @param dataModel
     * @return
     * @throws TasteException
     */
    @Override
    public Recommender buildRecommender(DataModel dataModel) throws TasteException {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
            return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
    }
}

