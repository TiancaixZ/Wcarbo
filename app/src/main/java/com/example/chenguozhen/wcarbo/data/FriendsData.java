package com.example.chenguozhen.wcarbo.data;

import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.Iterator;
import java.util.List;

/**
 * Created by chenguozhen on 2018/1/11.
 */

public class FriendsData implements Database<Frindes>{

    @Override
    public void onLoad(List<Frindes> frindes) {
        List<Frindes> load_list = frindes;
        List<Frindes> database_list = DataSupport.findAll(Frindes.class);
        Iterator<Frindes> it = load_list.iterator();
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
    public void update(List<Frindes> frindes) {
    }

    @Override
    public List<Frindes> query() {
        return null;
    }

}
