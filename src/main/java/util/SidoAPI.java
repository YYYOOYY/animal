package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;

import data.sido.SidoResponse;
import data.sido.SidoResponseResult;

public class SidoAPI {
	public static SidoResponse getSidos() {
		try {
			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sido";
			Map<String, String> params = new LinkedHashMap<>();
			params.put("serviceKey", "tn2QYKN7gYKzFTZslBMJQMYem10LBYlxadGI%2BmhOWQz9W6mZl4BvvgN8mi7LoY7Dkw5gcYV%2FRx8afw%2F6p%2BlyIg%3D%3D");
			params.put("_type", "json");
			params.put("numOfRows", "17");
			
			String queryString = QueryStringBuilder.build(params);
			
			URI uri = new URI(target+"?"+queryString);
			
			// HttpClient 객체를 활용하는 방식
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			Gson gson = new Gson();
			SidoResponseResult responseResult = gson.fromJson(response.body(), SidoResponseResult.class);
			
			return responseResult.getResponse();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
