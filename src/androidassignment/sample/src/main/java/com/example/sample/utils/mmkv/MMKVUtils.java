package com.example.sample.utils.mmkv;

import com.example.sample.MyApplication;
import com.example.sample.data.MsgBean;
import com.example.sample.data.SessionBean;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MMKVUtils {


    public static final String SESSION_STORE_LIST_KEY = "sessions_list_key";


    /**
     * 获取对话列表
     *
     * @return
     */
    public static List<SessionBean> getSessionList() {
        String s = MyApplication.mmkv.decodeString(SESSION_STORE_LIST_KEY, "");
        List<SessionBean> list;
        try {
            TypeToken<List<SessionBean>> typeToken = new TypeToken<List<SessionBean>>() {
            };
            list = new Gson().fromJson(s, typeToken.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * 获取聊天记录
     *
     * @return
     */
    public static List<MsgBean> getSessionRecordList(String sessionname) {
        String s = MyApplication.mmkv.decodeString(sessionname, "");
        List<MsgBean> list;
        try {
            TypeToken<List<MsgBean>> typeToken = new TypeToken<List<MsgBean>>() {
            };
            list = new Gson().fromJson(s, typeToken.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * 更新单个对话聊天记录
     *
     * @return
     */
    public static void updateRecordList(String sessionname, List<MsgBean> list) {
        String json = new Gson().toJson(list);
        MyApplication.mmkv.encode(sessionname, json);
    }


    /**
     * 更新聊天列表
     *
     * @return
     */
    public static void updateSessionList(List<SessionBean> list) {
        list.sort(new Comparator<SessionBean>() {
            @Override
            public int compare(SessionBean sessionBean, SessionBean t1) {
                return (int) (sessionBean.getUpdateTime() - t1.getUpdateTime());
            }
        });
        String json = new Gson().toJson(list);
        MyApplication.mmkv.encode(SESSION_STORE_LIST_KEY, json);
    }

}
