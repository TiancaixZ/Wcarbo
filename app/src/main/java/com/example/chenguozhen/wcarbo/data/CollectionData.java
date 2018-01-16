package com.example.chenguozhen.wcarbo.data;

import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenguozhen on 2018/1/9.
 */

public class CollectionData implements Database<Collectionweibo>{

    @Override
    public void onLoad(List<Collectionweibo> collectionweibos) {
        List<Collectionweibo> load_list = collectionweibos;
        List<Collectionweibo> database_list = DataSupport.findAll(Collectionweibo.class);
        Iterator<Collectionweibo> it = load_list.iterator();
        while(it.hasNext())
        {
            String load_idstr = it.next().getIdstr();
            for (int i = 0; i < database_list.size(); i++) {
                if (load_idstr.equals(database_list.get(i).getIdstr())){
                    it.remove();
                }
            }
        }
        DataSupport.saveAllAsync(load_list).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {

            }
        });

    }

    @Override
    public void update(List<Collectionweibo> collectionweibos) {
        List<Collectionweibo> load_list = collectionweibos;
        List<Collectionweibo> database_list = DataSupport.findAll(Collectionweibo.class);
        for (int i = 0; i < database_list.size(); i++) {
            if (database_list.get(i).getReposts_count() != load_list.get(i).getReposts_count()
                    && database_list.get(i).getComments_count() != load_list.get(i).getComments_count()
                    && database_list.get(i).getComments_count() != load_list.get(i).getComments_count()
                    && database_list.get(i).getRetweeted_reposts_count() != load_list.get(i).getRetweeted_reposts_count()
                    && database_list.get(i).getRetweeted_comments_count() != load_list.get(i).getRetweeted_comments_count()
                    && database_list.get(i).getRetweeted_attitudes_count() != load_list.get(i).getRetweeted_attitudes_count()){
                Collectionweibo update_repost = new Collectionweibo();
                update_repost.setReposts_count(load_list.get(i).getReposts_count());
                update_repost.setComments_count(load_list.get(i).getComments_count());
                update_repost.setAttitudes_count(load_list.get(i).getAttitudes_count());
                update_repost.setRetweeted_reposts_count(load_list.get(i).getRetweeted_reposts_count());
                update_repost.setRetweeted_comments_count(load_list.get(i).getRetweeted_comments_count());
                update_repost.setRetweeted_attitudes_count(load_list.get(i).getRetweeted_attitudes_count());
                update_repost.update(i);
            }
        }
    }

    @Override
    public void query(List<Collectionweibo> collectionweibos) {

    }
}
