package com.learn.springbootkubernetes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class Controller {
    @RequestMapping("/")
    public String index() {
        String shang = "今天台风";
		String result = "";
		System.out.println(shang);
		try {
			result = URLEncoder.encode(shang, "GBK");
			System.out.println("GBK:" + result);
			
		    result = URLEncoder.encode(shang, "UTF-8");
			System.out.println("UTF8: " + result);
			
			result = URLEncoder.encode(shang, "UTF-16");
			System.out.println("UTF16:" + result);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}

        return shang;
    }
}
