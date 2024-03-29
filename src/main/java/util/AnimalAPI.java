package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import data.animal.AnimalItem;
import data.animal.AnimalResponse;
import data.animal.AnimalResponseResult;

public class AnimalAPI {
	
	private static Map<String, AnimalItem> cache;
	static {
		cache = new HashMap<>();
	}
	
	// OPEN API 연동해서 데이터 받아와서 파싱해서 컨트롤러에 연결
	public static AnimalResponse getAnimals(String upkind, String uprCd, String pageNo, String bgnde, String endde) {
		try {
			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";
			
			Map<String, String> params = new LinkedHashMap<>();
			params.put("serviceKey", "tn2QYKN7gYKzFTZslBMJQMYem10LBYlxadGI%2BmhOWQz9W6mZl4BvvgN8mi7LoY7Dkw5gcYV%2FRx8afw%2F6p%2BlyIg%3D%3D");
			params.put("_type", "json");
			params.put("numOfRows", "12");
			
			params.put("upkind", upkind == null ? "" : upkind);
			params.put("upr_cd", uprCd == null ? "" : uprCd);
			params.put("pageNo", pageNo == null ? "" : pageNo);
			params.put("bgnde", bgnde == null ? "" : bgnde);
			params.put("endde", endde == null ? "" : endde);

			String queryString = QueryStringBuilder.build(params);
			
			URI uri = new URI(target+"?"+queryString);
			
			// HttpClient 객체를 활용하는 방식
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			AnimalResponseResult responseResult = gson.fromJson(response.body(), AnimalResponseResult.class);
			
			for(AnimalItem one : responseResult.getResponse().getBody().getItems().getItem()) {
				cache.put(one.getDesertionNo(), one);
			}
			// System.out.println("[서버] cache size : " + cache.size());
			
			return responseResult.getResponse();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static AnimalItem findByDesertionNo(String no) {
		return cache.get(no);
	}
}
