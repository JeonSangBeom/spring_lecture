package com.jjang051.controller;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	
	@ResponseBody
	@RequestMapping("/Corona.do")
	public String apiCall(String date,HttpServletResponse response) throws Exception {// 데이터를 뿌려 날짜가 뿌려지는 것
		//response.setContentType("application/json");
		//response.setCharacterEncoding("UTF-8");
		//data.go.kr에서 복붙
		Map<String,Object> hashMap = new HashMap<String,Object>();		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=T3%2B1U0I7Z%2B2nVRxRwzOmxA44Uz%2B%2FaTKPWfPSoCZccL5%2F664TNO9WEiduqD7QWGS1bsaVKsh1oUxgzfFwt8%2FIPA%3D%3D"); /*Service Key 마이 페이지 -일반 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*검색할 생성일 범위의 종료 date 변수 사용*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*기본이 XML인데 _type을 붙이면 JSON리턴*/
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        String json = null;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            json = StreamUtils.copyToString(conn.getInputStream(),StandardCharsets.UTF_8);//StreamUtils.copyToString - 이렇게 사용하면 stream으로 받아 올 수 있다
        } else {
            json = StreamUtils.copyToString(conn.getErrorStream(),StandardCharsets.UTF_8);
        }
        conn.disconnect();// 연결 끊어 주기
        hashMap.put("aa", json);// aa를 json으로 던져주기
		return json;
	}
}


