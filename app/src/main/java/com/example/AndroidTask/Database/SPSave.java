package com.example.AndroidTask.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;

public class SPSave {
    public static boolean saveUserInfo(Context context,String account,String password){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit= sp.edit();
        edit.putString("account",account);
        edit.putString("password",password);
        edit.commit();
        return true;
    }
    public static Map<String,String> getUserInfo(Context context){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        String account= sp.getString("account",null);
        String password= sp.getString("password",null);
        Map<String,String> userMap=new HashMap<String,String>();
        userMap.put(account,"account");
        userMap.put(password,"password");
        return userMap;
    }
    public static void deleteAll(Context context){
        SharedPreferences sp=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit= sp.edit();
        edit.clear();
        edit.commit();
    }

}
