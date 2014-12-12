package mahout;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: BCE8959
 * Date: 12/11/14
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecommenderDemoTest {

    @Test
    public void testRecommendItem() throws Exception {
        RecommenderDemo sut = new RecommenderDemo();

        sut.recommendItem();
    }


}
