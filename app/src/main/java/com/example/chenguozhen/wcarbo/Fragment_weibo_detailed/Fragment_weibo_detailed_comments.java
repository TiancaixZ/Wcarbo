package com.example.chenguozhen.wcarbo.Fragment_weibo_detailed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.module.base.BaseListFragment;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.chenguozhen.wcarbo.utils.Utility.Pagecount;

/**
 * Created by chenguozhen on 2018/1/18.
 */

public class Fragment_weibo_detailed_comments extends BaseListFragment{

    private String token;
    private String url = "https://api.weibo.com/2/comments/show.json?source=3867086258";
    private List<Comment> commentsList = new ArrayList<Comment>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = ((wcarbo)getActivity().getApplication()).getToken();
    }

    @Override
    protected void SwipeRefresh_Refresh() {
        super.SwipeRefresh_Refresh();
    }

    public class weiboAsyncTask extends AsyncTask<String,Integer,Boolean> {

        private List<Favorites.FavoritesBean> favoritesBeanList = new ArrayList<Favorites.FavoritesBean>();

        @Override
        protected Boolean doInBackground(String... strings) {
            final String url = strings[0];
            final OkHttpClient client = new OkHttpClient();
            HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
            builder.addQueryParameter(Oauth2AccessToken.KEY_ACCESS_TOKEN,token);
            builder.addQueryParameter("count",String.valueOf(50));
            
            Request request = new Request.Builder()
                    .url(builder.build())
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String responseData  = response.body().string();
                Favorites favorites = JSONUitily.favorites(responseData);
                List<Favorites.FavoritesBean> favoritesBeans = favorites.getFavorites();
                favoritesBeanList.addAll(favoritesBeans);
                final int count  = Pagecount(responseData);
                if (count >= 2){
                    for (int i = 2; i <= count; i++) {
                        Response response1 = client.newCall(Utility.builder(i,url,token)).execute();
                        String responseData1 = response1.body().string();
                        Favorites favorites1 = JSONUitily.favorites(responseData1);
                        List<Favorites.FavoritesBean> favoritesBeans1 = favorites1.getFavorites();
                        favoritesBeanList.addAll(favoritesBeans1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean == true){
                favoritesBeans.addAll(favoritesBeanList);
                collectionListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected RecyclerView.Adapter adapter() {

        return super.adapter();
    }
}
