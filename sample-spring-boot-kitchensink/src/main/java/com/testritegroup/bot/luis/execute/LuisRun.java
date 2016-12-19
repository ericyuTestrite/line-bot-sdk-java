package com.testritegroup.bot.luis.execute;

import org.json.JSONArray;
import org.json.JSONObject;

import com.testritegroup.bot.luis.util.CallLuisClient;

public class LuisRun {

	public static String doRun(String strQuery) throws Exception {
		String retDoNotUnderstand = "我不太懂你的意思,可否再輸入一次嗎";
		CallLuisClient http = new CallLuisClient();
		System.out.println("Testing 1 - Send Http GET request");
		String jRetSon = http.sendGet(strQuery);
		JSONObject obj = new JSONObject(jRetSon);
		double scoreMax = 0;
		String sAns = ""; 
		boolean isUseEntity=false;
		JSONArray entities = obj.getJSONArray("entities");
		if (entities!=null){
			for (int i = 0; i < entities.length(); i++) {
				scoreMax = entities.getJSONObject(i).getDouble("score");
				System.out.println("doRun  scoreMax= " + scoreMax);
				String entity2 = entities.getJSONObject(i).getString("entity");
				sAns = entity2;
				isUseEntity=true;
			}
		}

		JSONArray intents = obj.getJSONArray("intents");
		for (int i = 0; i < intents.length(); i++) {
			String intent = intents.getJSONObject(i).getString("intent");
			double score = intents.getJSONObject(i).getDouble("score");
			if (scoreMax < score) {
				System.out.println("doRun  score= " + score);
				sAns = intent;
				scoreMax=score;
				isUseEntity=false;
			}
			JSONArray actions = null;
			try {
				actions = intents.getJSONObject(i).getJSONArray("actions");
				JSONArray parameters = actions.getJSONObject(0).getJSONArray("parameters");
				JSONArray value = parameters.getJSONObject(0).getJSONArray("value");
				double score2 = value.getJSONObject(0).getDouble("score");
				String entity2 = value.getJSONObject(0).getString("entity");
				if (scoreMax < score2) {
					System.out.println("doRun  score2= " + score2);
					sAns = entity2;
					scoreMax=score;
					isUseEntity=true;
				}
			} catch (Exception e) {
			}
		}

		if (sAns != null && sAns.length() > 0  && !"None".equals(sAns)) {
			String ret= "您是不是想要" + sAns + "";
			if(isUseEntity){
				ret += ", 有關";
			}
			return ret;

		} else {
			return retDoNotUnderstand;
		}
	}

//	public static void main(String[] args)  {
//		// LuisRun luisRun=new LuisRun();
//		try {
//			String strQuery = "今天天氣好嗎";
//			String ret = LuisRun.doRun(strQuery);
//			System.out.println("main  args1= " + ret);
//
//			strQuery = "今天是星期幾?";
//			ret = LuisRun.doRun(strQuery);
//			System.out.println("main  arg2s= " + ret);
//			
//			strQuery = "今天氣溫如何";
//
//			ret = LuisRun.doRun(strQuery);
//			System.out.println("main  arg3s= " + ret);
//			strQuery = "那裏有在賣燈泡";
//
//			ret = LuisRun.doRun(strQuery);
//			System.out.println("main  arg3s= " + ret);
//			
//			
//			strQuery = "任天堂的首席製作人";
//
//			ret = LuisRun.doRun(strQuery);
//			System.out.println("main  arg3s= " + ret);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
