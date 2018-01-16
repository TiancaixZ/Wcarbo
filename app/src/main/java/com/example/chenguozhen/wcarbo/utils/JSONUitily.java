package com.example.chenguozhen.wcarbo.utils;

import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo;
import com.example.chenguozhen.wcarbo.Bean.JSON.Collectionweibo_Pic_urls;
import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;
import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo;
import com.example.chenguozhen.wcarbo.Bean.JSON.Hotweibo_Pic_urls;
import com.example.chenguozhen.wcarbo.Bean.JSON.Users;
import com.example.chenguozhen.wcarbo.Bean.Status;
import com.google.gson.Gson;
import com.lzy.ninegrid.ImageInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguozhen on 2018/1/3.
 */

public class JSONUitily {

    /**
     * 解析frinds json数据, 将解析的frindes的结果存储到Frindes数据库.
     */
    public static void paerseJSON_Frindes(String jsondata) {
        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            JSONArray users = jsonBody.getJSONArray("users");
            List<Frindes> frindesList = new ArrayList<Frindes>();
            for (int i = 0; i < users.length(); i++) {
                JSONObject jsonObject = users.getJSONObject(i);
                Frindes frindes = new Frindes();
                int id = jsonObject.getInt("id");
                frindes.setId(id);
                String idstr = jsonObject.getString("idstr");
                frindes.setIdstr(idstr);
                String screen_name = jsonObject.getString("screen_name");
                frindes.setScreen_name(screen_name);
                String name = jsonObject.getString("name");
                frindes.setName(name);
                int province = jsonObject.getInt("province");
                frindes.setProvince(province);
                int city = jsonObject.getInt("city");
                frindes.setCity(city);
                String location = jsonObject.getString("location");
                frindes.setLocation(location);
                if (jsonObject.isNull("description")) {
                    jsonObject.put("description", 0);
                    frindes.setDescription(0 + "");
                } else {
                    String description = jsonObject.getString("description");
                    frindes.setDescription(description);
                }
                String profile_image_url = jsonObject.getString("profile_image_url");
                frindes.setProfile_image_url(profile_image_url);
                String avatar_large = jsonObject.getString("avatar_large");
                frindes.setAvatar_large(avatar_large);
                if (jsonObject.isNull("cover_image_phone")) {
                    jsonObject.put("cover_image_phone", 0 + "");
                    frindes.setCover_image_phone(jsonObject.getString("cover_image_phone"));
                } else {
                    String cover_image_phone = jsonObject.getString("cover_image_phone");
                    frindes.setCover_image_phone(cover_image_phone);
                }
                String gender = jsonObject.getString("gender");
                frindes.setGender(gender);
                int followers_count = jsonObject.getInt("followers_count");
                frindes.setFollowers_count(followers_count);
                int friends_count = jsonObject.getInt("friends_count");
                frindes.setFrindes_count(friends_count);
                int statuses_count = jsonObject.getInt("statuses_count");
                frindes.setStatuses_count(statuses_count);
                String created_at = jsonObject.getString("created_at");
                frindes.setCreated_at(created_at);
                boolean allow_all_act_msg = jsonObject.getBoolean("allow_all_act_msg");
                frindes.setAllow_all_act_msg(allow_all_act_msg);
                boolean verified = jsonObject.getBoolean("verified");
                frindes.setVerified(verified);
                if (jsonObject.isNull("verified_reason")) {
                    jsonObject.put("description", 0);
                    frindes.setVerified_reason(0 + "");
                } else {
                    String verified_reason = jsonObject.getString("verified_reason");
                    frindes.setVerified_reason(verified_reason);
                }
                boolean follow_me = jsonObject.getBoolean("follow_me");
                frindes.setFoolow_me(follow_me);
                int online_status = jsonObject.getInt("online_status");
                frindes.setOnline_status(online_status);
                String lang = jsonObject.getString("lang");
                frindes.setLang(lang);
                frindesList.add(frindes);
            }
            DataSupport.deleteAll(Frindes.class);
            DataSupport.saveAll(frindesList);
            int total_number = jsonBody.getInt("total_number");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析User json数据, 将解析的User的结果存储到User数据库.
     *
     * @param jsondata
     */
    public static void paerseJSON_USEPAGER(String jsondata) {
        try {
            Users user = new Users();
            JSONObject jsonBody = new JSONObject(jsondata);
            int id = jsonBody.getInt("id");
            user.setId(id);
            String idstr = jsonBody.getString("idstr");
            user.setIdstr(idstr);
            String screen_name = jsonBody.getString("screen_name");
            user.setScreen_name(screen_name);
            String location = jsonBody.getString("location");
            user.setLocation(location);
            String description = jsonBody.getString("description");
            user.setDescription(description);
            String url = jsonBody.getString("url");
            user.setUrl(url);
            String gender = jsonBody.getString("gender");
            user.setGender(gender);
            String avatar_hd = jsonBody.getString("avatar_hd");
            user.setAvatar_hd(avatar_hd);
            int foolowers_count = jsonBody.getInt("followers_count");
            user.setFoolowers_count(foolowers_count);
            int friends_count = jsonBody.getInt("friends_count");
            user.setFrindes_count(friends_count);
            int statuses_count = jsonBody.getInt("statuses_count");
            user.setStatuses_count(statuses_count);
            int favourites_count = jsonBody.getInt("favourites_count");
            user.setFavourites_count(favourites_count);
            String created_at = jsonBody.getString("created_at");
            user.setCreated_at(created_at);
            boolean verified = jsonBody.getBoolean("verified");
            user.setVerified(verified);
            if (jsonBody.isNull("verified_reason")) {
                user.setVerified_reason(0 + "");
            } else {
                String verified_reason = jsonBody.getString("verified_reason");
                user.setVerified_reason(verified_reason);
            }
            DataSupport.deleteAll(Users.class, "idstr = ?", idstr);
            user.save();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jsondata
     */
    public static void paerseJSON_Hotweibo(String jsondata) {
        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            JSONArray statuses = jsonBody.getJSONArray("statuses");
            List<Hotweibo> hotweiboList = new ArrayList<Hotweibo>();
            for (int i = 0; i < statuses.length(); i++) {
                JSONObject jsonObject = statuses.getJSONObject(i);
                Hotweibo hotweibo = new Hotweibo();
                String idstr = jsonObject.getString("idstr");
                List<Hotweibo> hotweibos = DataSupport.where("idstr = ?", idstr).find(Hotweibo.class);
                if (hotweibos.size() <= 0) {
                    hotweibo.setIdstr(idstr);
                    String created_at = jsonObject.getString("created_at");
                    hotweibo.setCreated_at(created_at);
                    String text = jsonObject.getString("text");
                    hotweibo.setText(text);
                    String source = jsonObject.getString("source");
                    hotweibo.setSource(source);
                    Boolean favorited = jsonObject.getBoolean("favorited");
                    hotweibo.setFavorited(favorited);
                    Boolean truncated = jsonObject.getBoolean("truncated");
                    hotweibo.setTruncated(truncated);
                    if (jsonObject.isNull("pic_urls")) {
                        hotweibo.setPic_urls_count(0);
                    } else {
                        JSONArray pic_urls = jsonObject.getJSONArray("pic_urls");
                        List<Hotweibo_Pic_urls> hotweibo_pic_urlsList = new ArrayList<Hotweibo_Pic_urls>();
                        hotweibo.setPic_urls_count(pic_urls.length());
                        for (int j = 0; j < pic_urls.length(); j++) {
                            Hotweibo_Pic_urls hotweibo_pic_urls = new Hotweibo_Pic_urls();
                            JSONObject jsonObject1 = pic_urls.getJSONObject(j);
                            String thumbnail_pic = jsonObject1.getString("thumbnail_pic");
                            hotweibo_pic_urls.setThumbnail_pic(thumbnail_pic);
                            hotweibo_pic_urls.setHotweibo_idstr(idstr);
                            hotweibo.getPic_urlsList().add(hotweibo_pic_urls);
                            hotweibo_pic_urlsList.add(hotweibo_pic_urls);
                        }
                        DataSupport.saveAll(hotweibo_pic_urlsList);
                    }
                    JSONObject user = jsonObject.getJSONObject("user");
                    String User_idstr = user.getString("idstr");
                    hotweibo.setUsers_idstr(User_idstr);
                    String screen_name = user.getString("screen_name");
                    hotweibo.setScreen_name(screen_name);
                    String location = user.getString("location");
                    hotweibo.setLocation(location);
                    String description = user.getString("description");
                    hotweibo.setDescription(description);
                    String url = user.getString("url");
                    hotweibo.setUrl(url);
                    String gender = user.getString("gender");
                    hotweibo.setGender(gender);
                    String avatar_hd = user.getString("avatar_hd");
                    hotweibo.setAvatar_hd(avatar_hd);
                    int foolowers_count = user.getInt("followers_count");
                    hotweibo.setFoolowers_count(foolowers_count);
                    int friends_count = user.getInt("friends_count");
                    hotweibo.setFrindes_count(friends_count);
                    int statuses_count = user.getInt("statuses_count");
                    hotweibo.setStatuses_count(statuses_count);
                    int favourites_count = user.getInt("favourites_count");
                    hotweibo.setFavourites_count(favourites_count);
                    String Users_created_at = user.getString("created_at");
                    hotweibo.setUsers_created_at(Users_created_at);
                    boolean verified = user.getBoolean("verified");
                    hotweibo.setVerified(verified);
                    if (user.isNull("verified_reason")) {
                        hotweibo.setVerified_reason(0 + "");
                    } else {
                        String verified_reason = user.getString("verified_reason");
                        hotweibo.setVerified_reason(verified_reason);
                    }
                    hotweiboList.add(hotweibo);
                }
                DataSupport.saveAll(hotweiboList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jsondata
     */
    public static void paerseJSON_Favourite(String jsondata) {
        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            JSONArray statuses = jsonBody.getJSONArray("favorites");
            List<Collectionweibo> collectionweiboList = new ArrayList<Collectionweibo>();
            for (int i = 0; i < statuses.length(); i++) {
                JSONObject count = statuses.getJSONObject(i);
                JSONObject jsonObject = count.getJSONObject("status");
                String idstr = jsonObject.getString("idstr");
                List<Collectionweibo> collectionweibos = DataSupport.where
                        ("idstr == ?", idstr).find(Collectionweibo.class);


                if (collectionweibos.size() <= 0) {
                    Collectionweibo collectionweibo = new Collectionweibo();
                    collectionweibo.setIdstr(idstr);
                    String created_at = jsonObject.getString("created_at");
                    collectionweibo.setCreated_at(created_at);
                    String text = jsonObject.getString("text");
                    collectionweibo.setText(text);
                    String source = jsonObject.getString("source");
                    collectionweibo.setSource(source);
                    Boolean favorited = jsonObject.getBoolean("favorited");
                    collectionweibo.setFavorited(favorited);
                    Boolean truncated = jsonObject.getBoolean("truncated");
                    collectionweibo.setTruncated(truncated);
                    int reposts_count = jsonObject.getInt("reposts_count");
                    int comments_count = jsonObject.getInt("comments_count");
                    int attitudes_count = jsonObject.getInt("attitudes_count");
                    collectionweibo.setReposts_count(reposts_count);
                    collectionweibo.setComments_count(comments_count);
                    collectionweibo.setAttitudes_count(attitudes_count);

                    if (jsonObject.isNull("pic_urls")) {
                        collectionweibo.setPic_urls_count(0);
                    } else {
                        JSONArray pic_urls = jsonObject.getJSONArray("pic_urls");
                        List<Collectionweibo_Pic_urls> collectionweibo_pic_urls = new ArrayList<Collectionweibo_Pic_urls>();
                        collectionweibo.setPic_urls_count(pic_urls.length());
                        for (int j = 0; j < pic_urls.length(); j++) {
                            Collectionweibo_Pic_urls collectionweiboPicUrls = new Collectionweibo_Pic_urls();
                            JSONObject jsonObject1 = pic_urls.getJSONObject(j);
                            String thumbnail_pic = jsonObject1.getString("thumbnail_pic");
                            collectionweiboPicUrls.setThumbnail_pic(thumbnail_pic);
                            collectionweiboPicUrls.setCollectionweibo_idstr(idstr);
                            collectionweibo.getPic_urlsList().add(collectionweiboPicUrls);
                            collectionweibo_pic_urls.add(collectionweiboPicUrls);
                        }
                        DataSupport.saveAllAsync(collectionweibo_pic_urls).listen(new SaveCallback() {
                            @Override
                            public void onFinish(boolean success) {

                            }
                        });
                    }

                    JSONObject user = jsonObject.getJSONObject("user");
                    String user_idstr = user.getString("idstr");
                    collectionweibo.setUsers_idstr(user_idstr);
                    String screen_name = user.getString("screen_name");
                    collectionweibo.setScreen_name(screen_name);
                    String location = user.getString("location");
                    collectionweibo.setLocation(location);
                    String description = user.getString("description");
                    collectionweibo.setDescription(description);
                    String url = user.getString("url");
                    collectionweibo.setUrl(url);
                    String gender = user.getString("gender");
                    collectionweibo.setGender(gender);
                    String avatar_hd = user.getString("avatar_hd");
                    collectionweibo.setAvatar_hd(avatar_hd);
                    int foolowers_count = user.getInt("followers_count");
                    collectionweibo.setFoolowers_count(foolowers_count);
                    int friends_count = user.getInt("friends_count");
                    collectionweibo.setFrindes_count(friends_count);
                    int statuses_count = user.getInt("statuses_count");
                    collectionweibo.setStatuses_count(statuses_count);
                    int favourites_count = user.getInt("favourites_count");
                    collectionweibo.setFavourites_count(favourites_count);
                    String Users_created_at = user.getString("created_at");
                    collectionweibo.setUsers_created_at(Users_created_at);
                    boolean verified = user.getBoolean("verified");
                    collectionweibo.setVerified(verified);
                    if (!(user.isNull("verified_reason"))) {
                        String verified_reason = user.getString("verified_reason");
                        collectionweibo.setVerified_reason(verified_reason);
                    }

                    if (jsonObject.isNull("retweeted_status")) {
                        collectionweibo.setRetweeted(false);
                    } else {
                        collectionweibo.setRetweeted(true);
                        JSONObject retweeted_status = jsonObject.getJSONObject("retweeted_status");
                        String retweeted_idstr = retweeted_status.getString("idstr");
                        collectionweibo.setRetweeted_idstr(retweeted_idstr);
                        String retweeted_created_at = retweeted_status.getString("created_at");
                        collectionweibo.setRetweeted_created_at(retweeted_created_at);
                        String retweeted_text = retweeted_status.getString("text");
                        collectionweibo.setRetweeted_text(retweeted_text);
                        //String retweeted_source = retweeted_status.getString("source");
                        //collectionweibo.setRetweeted_source(retweeted_source);
                        Boolean retweeted_favorited = retweeted_status.getBoolean("favorited");
                        collectionweibo.setRetweeted_favorited(retweeted_favorited);
                        Boolean retweeted_truncated = retweeted_status.getBoolean("truncated");
                        collectionweibo.setRetweeted_truncated(retweeted_truncated);
                        int retweeted_reposts_count = retweeted_status.getInt("reposts_count");
                        int retweeted_comments_count = retweeted_status.getInt("comments_count");
                        int retweeted_attitudes_count = retweeted_status.getInt("attitudes_count");


                        if (jsonObject.isNull("pic_urls")) {
                            collectionweibo.setRetweeted_Pic_urls_count(0);
                        } else {
                            JSONArray retweeted_pic_urls = retweeted_status.getJSONArray("pic_urls");
                            collectionweibo.setRetweeted_Pic_urls_count(retweeted_pic_urls.length());
                            List<Collectionweibo_Pic_urls> collectionweiboPicUrls = new ArrayList<Collectionweibo_Pic_urls>();
                            for (int j = 0; j < retweeted_pic_urls.length(); j++) {
                                Collectionweibo_Pic_urls collectionweibo_pic_urls = new Collectionweibo_Pic_urls();
                                JSONObject jsonObject1 = retweeted_pic_urls.getJSONObject(j);
                                String retweeted_pic = jsonObject1.getString("thumbnail_pic");
                                collectionweibo_pic_urls.setRetweedted_pic(retweeted_pic);
                                collectionweibo_pic_urls.setCollectionweibo_idstr(retweeted_idstr);
                                collectionweibo.getPic_urlsList().add(collectionweibo_pic_urls);
                            }
                            DataSupport.saveAllAsync(collectionweiboPicUrls).listen(new SaveCallback() {
                                @Override
                                public void onFinish(boolean success) {

                                }
                            });
                        }

                        JSONObject retweeted_user = retweeted_status.getJSONObject("user");
                        String retweeted_user_idstr = retweeted_user.getString("idstr");
                        collectionweibo.setRetweeted_Users_idstr(retweeted_user_idstr);
                        String retweeted_user_screen_name = retweeted_user.getString("screen_name");
                        collectionweibo.setRetweeted_screen_name(retweeted_user_screen_name);
                        String retweeted_user_location = retweeted_user.getString("location");
                        collectionweibo.setRetweeted_location(retweeted_user_location);
                        String retweeted_user_description = retweeted_user.getString("description");
                        collectionweibo.setRetweeted_description(retweeted_user_description);
                        String retweeted_user_url = retweeted_user.getString("url");
                        collectionweibo.setRetweeted_url(retweeted_user_url);
                        String retweeted_user_gender = retweeted_user.getString("gender");
                        collectionweibo.setRetweeted_gender(retweeted_user_gender);
                        String retweeted_user_avatar_hd = retweeted_user.getString("avatar_hd");
                        collectionweibo.setRetweeted_avatar_hd(retweeted_user_avatar_hd);
                        int retweeted_user_foolowers_count = retweeted_user.getInt("followers_count");
                        collectionweibo.setRetweeted_foolowers_count(retweeted_user_foolowers_count);
                        int retweeted_user_friends_count = retweeted_user.getInt("friends_count");
                        collectionweibo.setRetweeted_frindes_count(retweeted_user_friends_count);
                        int retweeted_user_statuses_count = retweeted_user.getInt("statuses_count");
                        collectionweibo.setRetweeted_statuses_count(retweeted_user_statuses_count);
                        int retweeted_user_favourites_count = retweeted_user.getInt("favourites_count");
                        collectionweibo.setRetweeted_favourites_count(retweeted_user_favourites_count);
                        String retweeted_user_Users_created_at = retweeted_user.getString("created_at");
                        collectionweibo.setRetweeted_created_at(retweeted_user_Users_created_at);
                        boolean retweeted_user_verified = retweeted_user.getBoolean("verified");
                        collectionweibo.setRetweeted_verified(retweeted_user_verified);
                        if (!(retweeted_user.isNull("verified_reason"))) {
                            String retweeted_user_verified_reason = retweeted_user.getString("verified_reason");
                            collectionweibo.setRetweeted_verified_reason(retweeted_user_verified_reason);
                        }
                    }
                    collectionweiboList.add(collectionweibo);
                }
            }
            DataSupport.saveAllAsync(collectionweiboList).listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jsondata
     * @return
     */
    public static List<Collectionweibo> collectionweiboList(String jsondata) {
        List<Collectionweibo> collectionweiboList = new ArrayList<Collectionweibo>();
        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            JSONArray favorites = jsonBody.getJSONArray("favorites");
            for (int i = 0; i < favorites.length(); i++) {
                Collectionweibo collectionweibo = new Collectionweibo();
                JSONObject count = favorites.getJSONObject(i);
                //Status status = parseJSON_Status(count);
                JSONObject jsonObject = count.getJSONObject("status");
                String idstr = jsonObject.getString("idstr");
                collectionweibo.setIdstr(idstr);
                String created_at = jsonObject.getString("created_at");
                collectionweibo.setCreated_at(created_at);
                String text = jsonObject.getString("text");
                collectionweibo.setText(text);
                if (jsonObject.isNull("deleted")) {
                    String source = jsonObject.getString("source");
                    collectionweibo.setSource(source);
                    Boolean favorited = jsonObject.getBoolean("favorited");
                    collectionweibo.setFavorited(favorited);
                    Boolean truncated = jsonObject.getBoolean("truncated");
                    collectionweibo.setTruncated(truncated);
                    int reposts_count = jsonObject.getInt("reposts_count");
                    int comments_count = jsonObject.getInt("comments_count");
                    int attitudes_count = jsonObject.getInt("attitudes_count");
                    collectionweibo.setReposts_count(reposts_count);
                    collectionweibo.setComments_count(comments_count);
                    collectionweibo.setAttitudes_count(attitudes_count);

                    if (jsonObject.isNull("pic_urls")) {
                        collectionweibo.setPic_urls_count(0);
                    } else {
                        JSONArray pic_urls = jsonObject.getJSONArray("pic_urls");
                        collectionweibo.setPic_urls_count(pic_urls.length());
                    }

                    JSONObject user = jsonObject.getJSONObject("user");
                    String user_idstr = user.getString("idstr");
                    collectionweibo.setUsers_idstr(user_idstr);
                    String screen_name = user.getString("screen_name");
                    collectionweibo.setScreen_name(screen_name);
                    String location = user.getString("location");
                    collectionweibo.setLocation(location);
                    String description = user.getString("description");
                    collectionweibo.setDescription(description);
                    String url = user.getString("url");
                    collectionweibo.setUrl(url);
                    String gender = user.getString("gender");
                    collectionweibo.setGender(gender);
                    String avatar_hd = user.getString("avatar_hd");
                    collectionweibo.setAvatar_hd(avatar_hd);
                    int foolowers_count = user.getInt("followers_count");
                    collectionweibo.setFoolowers_count(foolowers_count);
                    int friends_count = user.getInt("friends_count");
                    collectionweibo.setFrindes_count(friends_count);
                    int statuses_count = user.getInt("statuses_count");
                    collectionweibo.setStatuses_count(statuses_count);
                    int favourites_count = user.getInt("favourites_count");
                    collectionweibo.setFavourites_count(favourites_count);
                    String Users_created_at = user.getString("created_at");
                    collectionweibo.setUsers_created_at(Users_created_at);
                    boolean verified = user.getBoolean("verified");
                    collectionweibo.setVerified(verified);
                    if (!(user.isNull("verified_reason"))) {
                        String verified_reason = user.getString("verified_reason");
                        collectionweibo.setVerified_reason(verified_reason);
                    }

                    if (jsonObject.isNull("retweeted_status")) {
                        collectionweibo.setRetweeted(false);
                    } else {
                        collectionweibo.setRetweeted(true);
                        JSONObject retweeted_status = jsonObject.getJSONObject("retweeted_status");
                        String retweeted_idstr = retweeted_status.getString("idstr");
                        collectionweibo.setRetweeted_idstr(retweeted_idstr);
                        String retweeted_created_at = retweeted_status.getString("created_at");
                        collectionweibo.setRetweeted_created_at(retweeted_created_at);
                        String retweeted_text = retweeted_status.getString("text");
                        collectionweibo.setRetweeted_text(retweeted_text);
                        if (!(jsonObject.isNull("deleted"))) {
                            String retweeted_source = retweeted_status.getString("source");
                            collectionweibo.setRetweeted_source(retweeted_source);
                            Boolean retweeted_favorited = retweeted_status.getBoolean("favorited");
                            collectionweibo.setRetweeted_favorited(retweeted_favorited);
                            Boolean retweeted_truncated = retweeted_status.getBoolean("truncated");
                            collectionweibo.setRetweeted_truncated(retweeted_truncated);
                            int retweeted_reposts_count = retweeted_status.getInt("reposts_count");
                            int retweeted_comments_count = retweeted_status.getInt("comments_count");
                            int retweeted_attitudes_count = retweeted_status.getInt("attitudes_count");
                            collectionweibo.setRetweeted_reposts_count(retweeted_reposts_count);
                            collectionweibo.setRetweeted_comments_count(retweeted_comments_count);
                            collectionweibo.setRetweeted_attitudes_count(retweeted_attitudes_count);

                            if (jsonObject.isNull("pic_urls")) {
                                collectionweibo.setRetweeted_Pic_urls_count(0);
                            } else {
                                JSONArray retweeted_pic_urls = retweeted_status.getJSONArray("pic_urls");
                                collectionweibo.setRetweeted_Pic_urls_count(retweeted_pic_urls.length());
                            }

                            JSONObject retweeted_user = retweeted_status.getJSONObject("user");
                            String retweeted_user_idstr = retweeted_user.getString("idstr");
                            collectionweibo.setRetweeted_Users_idstr(retweeted_user_idstr);
                            String retweeted_user_screen_name = retweeted_user.getString("screen_name");
                            collectionweibo.setRetweeted_screen_name(retweeted_user_screen_name);
                            String retweeted_user_location = retweeted_user.getString("location");
                            collectionweibo.setRetweeted_location(retweeted_user_location);
                            String retweeted_user_description = retweeted_user.getString("description");
                            collectionweibo.setRetweeted_description(retweeted_user_description);
                            String retweeted_user_url = retweeted_user.getString("url");
                            collectionweibo.setRetweeted_url(retweeted_user_url);
                            String retweeted_user_gender = retweeted_user.getString("gender");
                            collectionweibo.setRetweeted_gender(retweeted_user_gender);
                            String retweeted_user_avatar_hd = retweeted_user.getString("avatar_hd");
                            collectionweibo.setRetweeted_avatar_hd(retweeted_user_avatar_hd);
                            int retweeted_user_foolowers_count = retweeted_user.getInt("followers_count");
                            collectionweibo.setRetweeted_foolowers_count(retweeted_user_foolowers_count);
                            int retweeted_user_friends_count = retweeted_user.getInt("friends_count");
                            collectionweibo.setRetweeted_frindes_count(retweeted_user_friends_count);
                            int retweeted_user_statuses_count = retweeted_user.getInt("statuses_count");
                            collectionweibo.setRetweeted_statuses_count(retweeted_user_statuses_count);
                            int retweeted_user_favourites_count = retweeted_user.getInt("favourites_count");
                            collectionweibo.setRetweeted_favourites_count(retweeted_user_favourites_count);
                            String retweeted_user_Users_created_at = retweeted_user.getString("created_at");
                            collectionweibo.setRetweeted_created_at(retweeted_user_Users_created_at);
                            boolean retweeted_user_verified = retweeted_user.getBoolean("verified");
                            collectionweibo.setRetweeted_verified(retweeted_user_verified);
                            if (!(retweeted_user.isNull("verified_reason"))) {
                                String retweeted_user_verified_reason = retweeted_user.getString("verified_reason");
                                collectionweibo.setRetweeted_verified_reason(retweeted_user_verified_reason);
                            }
                        }
                    }
                }
                collectionweiboList.add(collectionweibo);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return collectionweiboList;
    }

    /**
     * @param jsondata
     * @return
     */
    public static List<Collectionweibo_Pic_urls> collectionweibo_pic_urlsList(String jsondata) {
        List<Collectionweibo_Pic_urls> collectionweiboPicUrls = new ArrayList<Collectionweibo_Pic_urls>();
        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            JSONArray favorites = jsonBody.getJSONArray("favorites");
            for (int i = 0; i < favorites.length(); i++) {
                JSONObject count = favorites.getJSONObject(i);
                JSONObject jsonObject = count.getJSONObject("status");
                String idstr = jsonObject.getString("idstr");
                if (jsonObject.isNull("pic_urls")) {
                } else {
                    JSONArray pic_urls = jsonObject.getJSONArray("pic_urls");
                    for (int j = 0; j < pic_urls.length(); j++) {
                        Collectionweibo_Pic_urls collectionweibo_pic_url = new Collectionweibo_Pic_urls();
                        JSONObject pic_count = pic_urls.getJSONObject(j);
                        String thumbnail_pic = pic_count.getString("thumbnail_pic");
                        collectionweibo_pic_url.setThumbnail_pic(thumbnail_pic);
                        collectionweibo_pic_url.setCollectionweibo_idstr(idstr);
                        Collectionweibo collectionweibo = collectionweiboList(jsondata).get(i);
                        collectionweibo.getPic_urlsList().add(collectionweibo_pic_url);
                        collectionweiboPicUrls.add(collectionweibo_pic_url);
                    }
                }
                if (jsonObject.isNull("retweeted_status")) {
                } else {
                    JSONObject retweeted_status = jsonObject.getJSONObject("retweeted_status");
                    if (!(jsonObject.isNull("pic_urls"))) {
                        JSONArray retweeted_pic_urls = retweeted_status.getJSONArray("pic_urls");
                        if (retweeted_pic_urls.length() > 0) {
                            for (int j = 0; j < retweeted_pic_urls.length(); j++) {
                                Collectionweibo_Pic_urls collectionweibo_pic_url = new Collectionweibo_Pic_urls();
                                JSONObject retweeted_pic_count = retweeted_pic_urls.getJSONObject(j);
                                String retweeted_pic = retweeted_pic_count.getString("thumbnail_pic");
                                collectionweibo_pic_url.setRetweedted_pic(retweeted_pic);
                                collectionweibo_pic_url.setCollectionweibo_idstr(idstr);
                                collectionweiboPicUrls.add(collectionweibo_pic_url);
                            }
                        }

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return collectionweiboPicUrls;
    }

    /**
     *
     * @param jsondata
     * @return
     */
    public static List<Frindes> frindesList(String jsondata) {
        List<Frindes> frindesList = new ArrayList<Frindes>();
        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            JSONArray users = jsonBody.getJSONArray("users");
            for (int i = 0; i < users.length(); i++) {
                JSONObject jsonObject = users.getJSONObject(i);
                Frindes frindes = new Frindes();
                int id = jsonObject.getInt("id");
                frindes.setId(id);
                String idstr = jsonObject.getString("idstr");
                frindes.setIdstr(idstr);
                String screen_name = jsonObject.getString("screen_name");
                frindes.setScreen_name(screen_name);
                String name = jsonObject.getString("name");
                frindes.setName(name);
                int province = jsonObject.getInt("province");
                frindes.setProvince(province);
                int city = jsonObject.getInt("city");
                frindes.setCity(city);
                String location = jsonObject.getString("location");
                frindes.setLocation(location);
                if (jsonObject.isNull("description")) {
                    jsonObject.put("description", 0);
                    frindes.setDescription(0 + "");
                } else {
                    String description = jsonObject.getString("description");
                    frindes.setDescription(description);
                }
                String profile_image_url = jsonObject.getString("profile_image_url");
                frindes.setProfile_image_url(profile_image_url);
                String avatar_large = jsonObject.getString("avatar_large");
                frindes.setAvatar_large(avatar_large);
                if (jsonObject.isNull("cover_image_phone")) {
                    jsonObject.put("cover_image_phone", 0 + "");
                    frindes.setCover_image_phone(jsonObject.getString("cover_image_phone"));
                } else {
                    String cover_image_phone = jsonObject.getString("cover_image_phone");
                    frindes.setCover_image_phone(cover_image_phone);
                }
                String gender = jsonObject.getString("gender");
                frindes.setGender(gender);
                int followers_count = jsonObject.getInt("followers_count");
                frindes.setFollowers_count(followers_count);
                int friends_count = jsonObject.getInt("friends_count");
                frindes.setFrindes_count(friends_count);
                int statuses_count = jsonObject.getInt("statuses_count");
                frindes.setStatuses_count(statuses_count);
                String created_at = jsonObject.getString("created_at");
                frindes.setCreated_at(created_at);
                boolean allow_all_act_msg = jsonObject.getBoolean("allow_all_act_msg");
                frindes.setAllow_all_act_msg(allow_all_act_msg);
                boolean verified = jsonObject.getBoolean("verified");
                frindes.setVerified(verified);
                if (jsonObject.isNull("verified_reason")) {
                    jsonObject.put("description", 0);
                    frindes.setVerified_reason(0 + "");
                } else {
                    String verified_reason = jsonObject.getString("verified_reason");
                    frindes.setVerified_reason(verified_reason);
                }
                boolean follow_me = jsonObject.getBoolean("follow_me");
                frindes.setFoolow_me(follow_me);
                int online_status = jsonObject.getInt("online_status");
                frindes.setOnline_status(online_status);
                String lang = jsonObject.getString("lang");
                frindes.setLang(lang);
                frindesList.add(frindes);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return frindesList;
    }

    /**
     *
     * @param object
     * @return
     */
    public static Status parseJSON_Status(JSONObject object){
        Status status = new Status();
        try {
            JSONObject jsonObject = object.getJSONObject("status");
            String idstr = jsonObject.getString("idstr");
            status.setIdstr(idstr);
            String created_at = jsonObject.getString("created_at");
            status.setCreated_at(created_at);
            String text = jsonObject.getString("text");
            status.setText(text);
            if (jsonObject.isNull("deleted")) {
                String source = jsonObject.getString("source");
                status.setSource(source);
                Boolean favorited = jsonObject.getBoolean("favorited");
                status.setFavorited(favorited);
                Boolean truncated = jsonObject.getBoolean("truncated");
                status.setTruncated(truncated);
                int reposts_count = jsonObject.getInt("reposts_count");
                int comments_count = jsonObject.getInt("comments_count");
                int attitudes_count = jsonObject.getInt("attitudes_count");
                status.setReposts_count(reposts_count);
                status.setComments_count(comments_count);
                status.setAttitudes_count(attitudes_count);

                if (jsonObject.isNull("pic_urls")) {
                    //
                } else {
                    JSONArray pic_urls = jsonObject.getJSONArray("pic_urls");
                    List<ImageInfo> pic_urlList = new ArrayList<ImageInfo>();
                    for (int i = 0; i < pic_urls.length(); i++) {
                        ImageInfo imageInfo = new ImageInfo();
                        JSONObject pic_count = pic_urls.getJSONObject(i);
                        String thumbnail_pic = pic_count.getString("thumbnail_pic");
                        imageInfo.setThumbnailUrl(thumbnail_pic);
                        imageInfo.setBigImageUrl(thumbnail_pic);
                        pic_urlList.add(imageInfo);
                    }
                    status.setPic_urls(pic_urlList);
                }

                JSONObject user = jsonObject.getJSONObject("user");
                Gson gson = new Gson();
                UsersBean usersBean = gson.fromJson(user.toString(),UsersBean.class);
                status.setUsersBean(usersBean);

                if (jsonObject.isNull("retweeted_status")) {
                } else {
                    Status re_status = new Status();
                    JSONObject retweeted_status = jsonObject.getJSONObject("retweeted_status");
                    String retweeted_idstr = retweeted_status.getString("idstr");
                    re_status.setIdstr(retweeted_idstr);
                    String retweeted_created_at = retweeted_status.getString("created_at");
                    re_status.setCreated_at(retweeted_created_at);
                    String retweeted_text = retweeted_status.getString("text");
                    re_status.setText(retweeted_text);
                    if (!(jsonObject.isNull("deleted"))) {
                        String retweeted_source = retweeted_status.getString("source");
                        re_status.setSource(retweeted_source);
                        Boolean retweeted_favorited = retweeted_status.getBoolean("favorited");
                        re_status.setFavorited(retweeted_favorited);
                        Boolean retweeted_truncated = retweeted_status.getBoolean("truncated");
                        re_status.setTruncated(retweeted_truncated);
                        int retweeted_reposts_count = retweeted_status.getInt("reposts_count");
                        int retweeted_comments_count = retweeted_status.getInt("comments_count");
                        int retweeted_attitudes_count = retweeted_status.getInt("attitudes_count");
                        re_status.setReposts_count(retweeted_reposts_count);
                        re_status.setComments_count(retweeted_comments_count);
                        re_status.setAttitudes_count(retweeted_attitudes_count);

                        if (jsonObject.isNull("pic_urls")) {

                        } else {
                            JSONArray retweeted_pic_urls = retweeted_status.getJSONArray("pic_urls");
                            List<ImageInfo> pic_urlList = new ArrayList<ImageInfo>();
                            for (int i = 0; i < retweeted_pic_urls.length(); i++) {
                                ImageInfo imageInfo = new ImageInfo();
                                JSONObject pic_count = retweeted_pic_urls.getJSONObject(i);
                                String thumbnail_pic = pic_count.getString("thumbnail_pic");
                                imageInfo.setThumbnailUrl(thumbnail_pic);
                                imageInfo.setBigImageUrl(thumbnail_pic);
                                pic_urlList.add(imageInfo);
                            }
                            status.setPic_urls(pic_urlList);
                        }

                        JSONObject retweeted_user = retweeted_status.getJSONObject("user");
                        UsersBean re_usersBean = gson.fromJson(retweeted_user.toString(),UsersBean.class);
                        re_status.setUsersBean(re_usersBean);

                        status.setRetweeted_status(re_status);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     *
     * @param jsondata
     * @return
     */
    public static Favorites parseJSON_favorites(String jsondata){
        Favorites favorites = new Favorites();

        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            List<Favorites.FavoritesBean> favoritesBeans = new ArrayList<Favorites.FavoritesBean>();
            JSONArray favoritearrays = jsonBody.getJSONArray("favorites");
            for (int i = 0; i < favoritearrays.length(); i++) {
                JSONObject count = favoritearrays.getJSONObject(i);
                Favorites.FavoritesBean favoritesBean  = new Favorites.FavoritesBean();
                Status status = parseJSON_Status(count);
                favoritesBean.setStatus(status);
                String favorited_time  = count.getString("favorited_time");
                favoritesBean.setFavorited_time(favorited_time);
                favoritesBeans.add(favoritesBean);
            }

            favorites.setFavorites(favoritesBeans);
            int total_number = jsonBody.getInt("total_number");
            favorites.setTotal_number(total_number);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return favorites;
    }

}
