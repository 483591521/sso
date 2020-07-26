package cn.tedu.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

// zuul环境中启动它作为一个断路器
@Component
public class HelloFallback implements ZuulFallbackProvider{

	@Override
	public String getRoute() {
		return "*"; // 路由
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		
		return new ClientHttpResponse() {
			
			@Override // 响应头信息
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return headers;
			}
			
			@Override // 返回内容
			public InputStream getBody() throws IOException {
				String msg = "tonyZuul"; // 可以构造对象JSON串
				return new ByteArrayInputStream(msg.getBytes());
			}
			
			@Override // 状态文字描述
			public String getStatusText() throws IOException {
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}
			
			@Override // 状态码
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST;
			}
			
			@Override // 状态码的值
			public int getRawStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST.value();
			}
			
			@Override // 关闭
			public void close() {
				
			}
		};
	}

}
