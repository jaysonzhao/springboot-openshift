package com.learn.springbootkubernetes;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
public class Controller {
	@Autowired
    private Tracer tracer;

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
		Span span = tracer.buildSpan("say-hello").start();
		try {
            String response = shang;
			
            Map<String, String> fields = new LinkedHashMap<>();
            fields.put("event", shang);
            fields.put("message", "this is a log message for name " + shang);
            span.log(fields);
            span.setTag("response", response);
           
        } finally {
            span.finish();
        }

        return shang;
    }
}
