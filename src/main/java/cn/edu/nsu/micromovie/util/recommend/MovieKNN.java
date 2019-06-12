package cn.edu.nsu.micromovie.util.recommend;

import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.data.DataModel;
import net.librec.data.model.TextDataModel;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.rating.RMSEEvaluator;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.UserKNNRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

import java.util.ArrayList;
import java.util.List;

public class MovieKNN {
    private static  List<RecommendedItem> resultList;
    static {
        init();
    }
    public static List<Integer> getRecommend(Integer userId,Integer size){
        List<Integer> movieIdList = new ArrayList<>(size);
        for(RecommendedItem r : resultList){
            if (r.getUserId().equals(userId.toString()) && r.getValue()>3.5D){
                movieIdList.add(Integer.valueOf(r.getItemId()));
            }
        }
        return movieIdList;
    }

    private static void init(){
        Configuration configuration = new Configuration();
        configuration.set("data.input.path","u.data");
        configuration.set("dfs.data.dir", "F:\\毕业设计\\librectest\\src\\main\\resources\\data\\");
        configuration.set("data.column.format", "UIRT");
        //创建datamodel
        DataModel dataModel = new TextDataModel(configuration);
        try {
            dataModel.buildDataModel();
        } catch (LibrecException e) {
            e.printStackTrace();
        }

        //创建context
        RecommenderContext context = new RecommenderContext(configuration,dataModel);

        //定义相似的算法
        configuration.set("rec.recommender.similarity.key" ,"item");
        RecommenderSimilarity similarity = new PCCSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);

        //定义推荐算法
        configuration.set("rec.neighbors.knn.number", "5");
        Recommender recommender = new UserKNNRecommender();
        recommender.setContext(context);
        RecommenderEvaluator evaluator = new RMSEEvaluator();


        //推荐并且得到resultList
        try {
            recommender.recommend(context);
        } catch (LibrecException e) {
            e.printStackTrace();
        }
        try {
            recommender.evaluate(evaluator);
        } catch (LibrecException e) {
            e.printStackTrace();
        }
        resultList = recommender.getRecommendedList();
    }


}
