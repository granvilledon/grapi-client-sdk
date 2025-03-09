package com.granvilledon.grapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.granvilledon.grapiclientsdk.model.User;
import com.granvilledon.grapiclientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 */
public class GrapiClient {
    private String accessKey;
    private String secretKey;

    public GrapiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //GET请求
        String result = HttpUtil.get("http://localhost:8123/api/name/?name="+name);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        String result = HttpUtil.post("http://localhost:8123/api/name/", "name="+name);
        System.out.println(result);
        return result;
    }

    private Map<String,String> getHeaderMap(String body) {
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("accessKey",accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp",String.valueOf(System.currentTimeMillis()));
        hashMap.put("sign", SignUtils.getSign(body,secretKey));
        return hashMap;
    }

    public String getUsernameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post("http://localhost:8123/api/name/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
}
