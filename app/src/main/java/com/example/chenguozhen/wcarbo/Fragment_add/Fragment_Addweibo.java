package com.example.chenguozhen.wcarbo.Fragment_add;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chenguozhen.wcarbo.Bean.Comments;
import com.example.chenguozhen.wcarbo.Interface.HttpListener;
import com.example.chenguozhen.wcarbo.R;
import com.example.chenguozhen.wcarbo.utils.HttpUtil;
import com.example.chenguozhen.wcarbo.utils.JSONUitily;
import com.example.chenguozhen.wcarbo.utils.StringUtil;
import com.example.chenguozhen.wcarbo.utils.Utility;
import com.example.chenguozhen.wcarbo.wcarbo;
import com.example.chenguozhen.wcarbo.widget.CommentChoiceFragment;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * Created by chenguozhen on 2018/1/26.
 */

public class Fragment_Addweibo extends Fragment implements HttpListener{

    @BindView(R.id.edittext) EditText editText;
    @BindView(R.id.photo) ImageButton photo;
    @BindView(R.id.aite) ImageButton aite;
    @BindView(R.id.mention) ImageButton mention;
    @BindView(R.id.send) ImageButton send;

    public static final int Addweibo = 100;
    public static final int ADDReposts = 101;
    public static final int AddReply = 102;

    private final String crespost_url = "https://api.weibo.com/2/comments/create.json?source=3867086258";
    private final String reply_url = "https://api.weibo.com/2/comments/reply.json?source=3867086258";
    private Request result;
    private String idstr = null;
    private String cid = null;
    private int type;

    public static Fragment_Addweibo  newInstance(String idstr,String cid,int type){
        Bundle args = new Bundle();
        args.putString("detail",idstr);
        args.putString("cid",cid);
        args.putInt("type",type);

        Fragment_Addweibo addweibo= new Fragment_Addweibo();
        addweibo.setArguments(args);
        return addweibo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idstr = getArguments().getString("detail");
        cid = getArguments().getString("cid");
        type = getArguments().getInt("type");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_addweibo,container,false);
        ButterKnife.bind(this, view);

        editText.addTextChangedListener(StringUtil.textNumberListener(editText,getContext()));
        return view;
    }

    @OnClick({R.id.photo,R.id.aite,R.id.mention,R.id.send})
    public void ButtonClick(View v) {
        switch (v.getId()) {
            case R.id.photo:
                break;
            case R.id.aite:
                break;
            case R.id.mention:
                break;
            case R.id.send:
                clicksend(idstr,cid);
                break;
            default:
                break;
        }
    }

    private void clicksend(String idstr, String cid) {
        Oauth2AccessToken tk = AccessTokenKeeper.readAccessToken(wcarbo.getContext());
        String comment = null;
        String tmp = editText.getText().toString();
        try {
            comment = URLDecoder.decode(tmp,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        switch (type){
            case AddReply:
                result = Utility.reply_builder(reply_url,tk.getToken(),idstr,cid,comment);
                send(result);
                break;
            case ADDReposts:
                result = Utility.create_builder(crespost_url,tk.getToken(),idstr,comment);
                send(result);
                break;
            default:
                break;
        }


    }

    @Override
    public void success(String responseData) {
        final Comments comment = JSONUitily.bytomentions(responseData);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (comment.getComments() != null){
                    Toast.makeText(getContext(),"发送成功",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "发送失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void failed() {
        Toast.makeText(getContext(), "发送失败", Toast.LENGTH_SHORT).show();
    }

    private void send(Request request){
        HttpUtil httpUtil = new HttpUtil(this);
        httpUtil.Data_update(request);
    }
}
