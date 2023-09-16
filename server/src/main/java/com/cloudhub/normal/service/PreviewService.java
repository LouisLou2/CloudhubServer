package com.cloudhub.normal.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

public interface PreviewService {
    RedirectView OnlineFilePreview(@RequestParam(value = "id", required = true) String id, RedirectAttributes attributes);

    @GetMapping("/notfoundPage")
    String notFoundPage();

    String OnlineFilePreview(@RequestParam(value="id",required = true)String id);
}
