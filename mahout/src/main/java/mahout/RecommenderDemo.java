package mahout;


import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BCE8959
 * Date: 12/11/14
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecommenderDemo {


    public void recommendItem() throws IOException, TasteException {
        /**
         * The first thing we have to do is load the data from the file. Mahout's recommenders use an interface called DataModel to handle interaction data. You can load our made up interactions like this
         */
        DataModel model = new FileDataModel(new File(AppConstants.TEST_FILE));
        /*
        In this example, we want to create a user-based recommender. The idea behind this approach is that when we want to compute recommendations for a particular users, we look for other users with a
         similar taste and pick the recommendations from their items. For finding similar users, we have to compare their interactions. There are several methods for doing this. One popular method is to
         compute the correlation coefficient between their interactions. In Mahout, you use this method as follows:
         */
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        /*
        The next thing we have to do is to define which similar users we want to leverage for the recommender.
        For the sake of simplicity, we'll use all that have a similarity greater than 0.1. This is implemented via a ThresholdUserNeighborhood:
         */
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        //Now we have all the pieces to create our recommender
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        /*
        We can easily ask the recommender for recommendations now. If we wanted to get three items recommended for the user with userID 2, we would do it like this
         */
        List<RecommendedItem>  recommendations = recommender.recommend(2, 3);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }



    }



}
