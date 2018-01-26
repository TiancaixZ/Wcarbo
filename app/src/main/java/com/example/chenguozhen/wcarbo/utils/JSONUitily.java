package com.example.chenguozhen.wcarbo.utils;

import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Bean.Favorites;
import com.example.chenguozhen.wcarbo.Bean.Gson.UsersBean;
import com.example.chenguozhen.wcarbo.Bean.Gson.error;
import com.example.chenguozhen.wcarbo.Bean.JSON.Comment;
import com.example.chenguozhen.wcarbo.Bean.JSON.Frindes;
import com.example.chenguozhen.wcarbo.Bean.JSON.Users;
import com.example.chenguozhen.wcarbo.Bean.JSON.Status;
import com.example.chenguozhen.wcarbo.Bean.Public;
import com.example.chenguozhen.wcarbo.Bean.Repost;
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
     * @param jsonObject
     * @return
     */
    public static Status parseJSON_Status(JSONObject jsonObject){
        Status status = new Status();
        try {
            String idstr = jsonObject.getString("idstr");
            status.setIdstr(idstr);
            String created_at = jsonObject.getString("created_at");
            status.setCreated_at(created_at);
            String text = jsonObject.getString("text");
            status.setText(text);
            if (jsonObject.isNull("deleted")) {
                status.setDeleted(false);
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
                    status.setPhoto(false);
                } else {
                    status.setPhoto(true);
                    JSONArray pic_urls = jsonObject.getJSONArray("pic_urls");
                    List<ImageInfo> pic_urlList = new ArrayList<ImageInfo>();
                    for (int i = 0; i < pic_urls.length(); i++) {
                        ImageInfo imageInfo = new ImageInfo();
                        JSONObject pic_count = pic_urls.getJSONObject(i);
                        String thumbnail_pic = pic_count.getString("thumbnail_pic");
                        imageInfo.setThumbnailUrl(thumbnail_pic);
                        imageInfo.setBigImageUrl(StringUtil.getBigImageUrl(thumbnail_pic));
                        pic_urlList.add(imageInfo);
                    }
                    status.setPic_urls(pic_urlList);
                }

                JSONObject user = jsonObject.getJSONObject("user");
                Gson gson = new Gson();
                UsersBean usersBean = gson.fromJson(user.toString(),UsersBean.class);
                status.setUsersBean(usersBean);

                if (jsonObject.isNull("retweeted_status")) {
                    status.setRetweeted_status(null);
                } else {
                    Status re_status = new Status();
                    JSONObject retweeted_status = jsonObject.getJSONObject("retweeted_status");
                    String retweeted_idstr = retweeted_status.getString("idstr");
                    re_status.setIdstr(retweeted_idstr);
                    String retweeted_created_at = retweeted_status.getString("created_at");
                    re_status.setCreated_at(retweeted_created_at);
                    String retweeted_text = retweeted_status.getString("text");
                    re_status.setText(retweeted_text);
                    if (jsonObject.isNull("deleted")) {
                        re_status.setDeleted(false);
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
                            re_status.setPhoto(false);
                        } else {
                            re_status.setPhoto(true);
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
                            re_status.setPic_urls(pic_urlList);
                        }

                        JSONObject retweeted_user = retweeted_status.getJSONObject("user");
                        UsersBean re_usersBean = gson.fromJson(retweeted_user.toString(),UsersBean.class);
                        re_status.setUsersBean(re_usersBean);

                        status.setRetweeted_status(re_status);
                    } else {
                        re_status.setDeleted(true);
                        status.setRetweeted_status(re_status);
                    }
                }
            } else {
                status.setDeleted(true);
                return status;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     *
     * @param jsonObject
     * @return
     */
    public static Comment parseJSON_Comment(JSONObject jsonObject){
        Gson gson = new Gson();
        Comment comment = gson.fromJson(jsonObject.toString(),Comment.class);
        try {
            JSONObject userObject = jsonObject.getJSONObject("user");
            UsersBean usersBean = gson.fromJson(userObject.toString(),UsersBean.class);
            comment.setUsersBean(usersBean);
            if (jsonObject.isNull("reply_comment")){
                comment.setReply(false);
            } else {
                comment.setReply(true);
                JSONObject reObject = jsonObject.getJSONObject("reply_comment");
                Comment re_comment = gson.fromJson(reObject.toString(),Comment.class);
                comment.setReply_comment(re_comment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return comment;
    }

    /**
     *
     * @param jsondata
     * @return
     */
    public static Favorites favorites(String jsondata){
        Favorites favorites = new Favorites();

        try {
            Gson gson = new Gson();
            error error = gson.fromJson(jsondata, com.example.chenguozhen.wcarbo.Bean.Gson.error.class);
            favorites.setError(error);
            JSONObject jsonBody = new JSONObject(jsondata);
            List<Favorites.FavoritesBean> favoritesBeans = new ArrayList<Favorites.FavoritesBean>();
            JSONArray favoritearrays = jsonBody.getJSONArray("favorites");
            for (int i = 0; i < favoritearrays.length(); i++) {
                JSONObject count = favoritearrays.getJSONObject(i);
                Favorites.FavoritesBean favoritesBean  = new Favorites.FavoritesBean();
                JSONObject jsonObject = count.getJSONObject("status");
                Status status = parseJSON_Status(jsonObject);
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

    /**
     *
     * @param jsondata
     * @return
     */
    public static Public publics(String jsondata){
        Public publics = new Public();

            try {
                Gson gson = new Gson();
                error error = gson.fromJson(jsondata, com.example.chenguozhen.wcarbo.Bean.Gson.error.class);
                publics.setError(error);
                ArrayList<Status> statusArrayList = new ArrayList<Status>();
                JSONObject jsonBody = new JSONObject(jsondata);
                JSONArray statuses = jsonBody.getJSONArray("statuses");
                for (int i = 0; i < statuses.length(); i++) {
                    JSONObject count = statuses.getJSONObject(i);
                    Status status = parseJSON_Status(count);
                    statusArrayList.add(status);
                }
                publics.setStatuses(statusArrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return publics;
    }

    /**
     *
     * @param jsondata
     * @return
     */
    public static Comments comments(String jsondata){
        Comments comments = new Comments();
        try {
            Gson gson = new Gson();
            error error = gson.fromJson(jsondata, com.example.chenguozhen.wcarbo.Bean.Gson.error.class);
            comments.setError(error);
            JSONObject jsonBody = new JSONObject(jsondata);
            long max_id = jsonBody.getLong("max_id");
            comments.setMax_id(max_id);
            long since_id = jsonBody.getLong("since_id");
            comments.setSince_id(since_id);
            int total_number = jsonBody.getInt("total_number");
            comments.setTotal_number(total_number);
            long previous_cursor = jsonBody.getLong("previous_cursor");
            comments.setPrevious_cursor(previous_cursor);
            long next_cursor = jsonBody.getLong("next_cursor");
            comments.setNext_cursor(next_cursor);

            ArrayList<Comment> commentList = new ArrayList<>();
            JSONArray commentArray = jsonBody.getJSONArray("comments");
            for (int i = 0; i < commentArray.length(); i++) {
                JSONObject count = commentArray.getJSONObject(i);
                Comment comment = parseJSON_Comment(count);
                commentList.add(comment);
            }
            comments.setComments(commentList);
            JSONObject statusObject = jsonBody.getJSONObject("status");
            Status status = parseJSON_Status(statusObject);
            comments.setStatus(status);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return comments;
    }

    /**
     *
     * @param jsondata
     * @return
     */
    public static Repost repost(String jsondata){
        Repost repost = new Repost();
        try {
            Gson gson = new Gson();
            error error = gson.fromJson(jsondata, com.example.chenguozhen.wcarbo.Bean.Gson.error.class);
            repost.setError(error);
            JSONObject jsonBody = new JSONObject(jsondata);
            int total_number = jsonBody.getInt("total_number");
            repost.setTotal_number(total_number);
            long previous_cursor = jsonBody.getLong("previous_cursor");
            repost.setPrevious_cursor(previous_cursor);
            long next_cursor = jsonBody.getLong("next_cursor");
            repost.setNext_cursor(next_cursor);

            ArrayList<Status> repostList = new ArrayList<Status>();
            JSONArray repostsArray = jsonBody.getJSONArray("reposts");
            for (int i = 0; i < repostsArray.length(); i++) {
                JSONObject count = repostsArray.getJSONObject(i);
                Status status = parseJSON_Status(count);
                repostList.add(status);
            }
            repost.setReposts(repostList);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return repost;
    }

    /**
     *
     * @param jsondata
     * @return
     */
    public static Comments bytomentions(String jsondata){
        Comments comments = new Comments();
        try {
            JSONObject jsonBody = new JSONObject(jsondata);
            int total_number = jsonBody.getInt("total_number");
            comments.setTotal_number(total_number);
            long previous_cursor = jsonBody.getLong("previous_cursor");
            comments.setPrevious_cursor(previous_cursor);
            long next_cursor = jsonBody.getLong("next_cursor");
            comments.setNext_cursor(next_cursor);

            ArrayList<Comment> commentList = new ArrayList<Comment>();
            JSONArray commentArray = jsonBody.getJSONArray("comments");
            for (int i = 0; i < commentArray.length(); i++) {
                JSONObject count = commentArray.getJSONObject(i);
                Comment comment = parseJSON_Comment(count);
                JSONObject statusobject = count.getJSONObject("status");
                Status status = parseJSON_Status(statusobject);
                comments.setStatus(status);
                commentList.add(comment);
            }
            comments.setComments(commentList);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return comments;
    }



}
