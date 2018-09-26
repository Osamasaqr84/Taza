package com.osamaomar.akhbarak.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PreferenceHelper {

	private SharedPreferences app_prefs;
	private static String app_ref = "userdetails";
	private static String photo = "uri";
	private static String Token = "token";
	private static String UserId = "userid";
	private static String Language = "language";
	private Context context;


	public PreferenceHelper(Context context) {
		this.context = context;
		try {
			app_prefs = context.getSharedPreferences(app_ref,
					Context.MODE_PRIVATE);
		}
		catch (NullPointerException e)
		{
		}
	}



	public String getToken() {
		return app_prefs.getString(Token,null);
	}

	public void setToken(String API_TOKEN) {
		Editor edit = app_prefs.edit();
		edit.putString(Token, API_TOKEN);
		edit.commit();
	}

	public String getUserId() {
		return app_prefs.getString(UserId,null);
	}

	public void setLanguage(String language) {
		Editor edit = app_prefs.edit();
		edit.putString(Language, language);
		edit.commit();
	}

	public String getLanguage() {
		return app_prefs.getString(Language,null);
	}

	public String getphoto() {
		return app_prefs.getString(photo,null);
	}

	public void setUserId(String API_TOKEN) {
		Editor edit = app_prefs.edit();
		edit.putString(UserId, API_TOKEN);
		edit.apply();
	}

	public void setphoto(String uri){
	Editor edit = app_prefs.edit();
	edit.putString(photo, uri);
	edit.apply();
}


    public void Logout(){
		setToken(null);
		setUserId(null);
		setphoto(null);

	}

	public void clearRequestData() {

 	}

}
