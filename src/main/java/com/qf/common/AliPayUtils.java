package com.qf.common;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AliPayUtils {
    @Value("${alipay.appid}")
    private String appid;
    @Value("${alipay.apk}")
    private String apk;
    @Value("${alipay.format}")
    private String format;
    @Value("${alipay.charset}")
    private String charset;
    @Value("${alipay.apuk}")
    private String apuk;
    @Value("${alipay.singtype}")
    private String singtype;
    
    public   String   pay (HttpServletRequest httpRequest,
                           HttpServletResponse httpResponse)   throws ServletException, IOException {
        AlipayClient alipayClient =  new DefaultAlipayClient( "https://openapi.alipaydev.com/gateway.do" , appid, apk, format, charset, apuk, singtype);  //获得初始化的AlipayClient 
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request 
      /*  alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
        alipayRequest.setNotifyUrl( "http://domain.com/CallBack/notify_url.jsp" ); //在公共参数中设置回跳和通知地址 */
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\"201503200101010011231232131123\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":8888.88,"  +
                "    \"subject\":\"Iphone12 128G\","  +
                "    \"body\":\"Iphone6 16G\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数 
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset="  + charset);
       /* httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面 
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();*/
        return form;
    }

}
