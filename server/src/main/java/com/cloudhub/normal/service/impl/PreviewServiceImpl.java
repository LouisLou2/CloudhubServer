package com.cloudhub.normal.service.impl;

import cn.hutool.core.codec.Base64;
import com.cloudhub.normal.service.PreviewService;
import com.cloudhub.normal.utils.URLTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
@Controller
@RequestMapping("/storage")
public class PreviewServiceImpl implements PreviewService {
    @GetMapping("/onlinepreview")
    @Override
    public RedirectView OnlineFilePreview(@RequestParam(value = "id", required = true) String id, RedirectAttributes attributes) {
        //此函数不需要发回json，只需要重定向到在线预览页面,若文件不存在则重定向到notfound页面
        String tempURL= URLTools.makeTempObsURL(Long.parseLong(id));
        RedirectView redirectView=null;
        if(tempURL==null){
            redirectView = new RedirectView("/notfoundPage");
            return redirectView;
        }
        //对URL进行Base64编码
        String encodedURL= Base64.encode(tempURL);
        //将参数添加到重定向URL
        attributes.addAttribute("url", encodedURL);
        //创建RedirectView并设置重定向地址
        redirectView = new RedirectView("/onlinePreview");
        return redirectView;
    }
    @GetMapping("/notfoundPage")
    @Override
    public String notFoundPage() {
        return "notfoundPage.ftl";
    }

    @Override
    @GetMapping("/onlinepreview_deprecated")
    public String OnlineFilePreview(String id) {
        //String tempURL="https://cloudhub.obs.cn-south-1.myhuaweicloud.com/afile.jpg";
        //String encodedURL= cn.hutool.core.codec.Base64.encode(tempURL);
        ////String targetUrl = "192.168.146.121:8082/onlinePreview?url="+encodedURL;
        //String targetUrl="https://www.bilibili.com/";
        //// 发送GET请求
        //ResponseEntity<String> response = restTemplate.getForEntity(targetUrl, String.class);
        //
        //// 获取响应数据，您可以根据需要对响应进行处理
        //String responseData = response.getBody();
        return "notfoundPage.ftl";
    }
}
