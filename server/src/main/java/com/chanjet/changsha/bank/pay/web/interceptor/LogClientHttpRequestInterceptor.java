package com.chanjet.changsha.bank.pay.web.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@Slf4j(topic = "outgoing")
public class LogClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

   @Override
   public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

       StopWatch stopWatch = new StopWatch();
       stopWatch.start();
       ClientHttpResponse response = execution.execute(request, body);

       stopWatch.stop();
       StringBuilder resBody = new StringBuilder();
       try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(),
               StandardCharsets.UTF_8))) {
           String line = bufferedReader.readLine();
           while (line != null) {
               resBody.append(line);
               line = bufferedReader.readLine();
           }
       }
        //当然图片、文件一类的就可以省了，打出日志没啥用处，此处的业务逻辑随意撸了，比如header头信息类似于  Accept 、Accept-Encoding 、Accept-Language、Connection 等等
       // 坑货，这么写单纯是为了过findbugs检查
       if (request != null){
           if (request.getHeaders() != null){
               if (request.getHeaders().getContentType() != null){
                   if (request.getHeaders().getContentType().includes(MediaType.MULTIPART_FORM_DATA)){
                       body = new byte[]{};
                   }
               }
           }
       }

       log.info(JSON.toJSONString(RestLog.builder().costTime(stopWatch.getLastTaskTimeMillis()).headers(request.getHeaders()).method(request.getMethodValue())
               .reqUrl(request.getURI().toString()).reqBody(new String(body, StandardCharsets.UTF_8)).resBody(resBody.toString()).resStatus(response.getRawStatusCode()).build()));
       return response;
   }

   @Data
   @Builder
   private static class RestLog {
       private String reqUrl;
       private String method;
       private HttpHeaders headers;
       private String reqBody;
       private String resBody;
       private long costTime;
       private int resStatus;
   }
}
