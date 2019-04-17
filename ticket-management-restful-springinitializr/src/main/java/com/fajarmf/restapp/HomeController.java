package com.fajarmf.restapp;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/")
public class HomeController {
	@ResponseBody
	@RequestMapping("/")
	public Map<String, Object> test(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("result", "Gresik");
		return map;
	}
}
