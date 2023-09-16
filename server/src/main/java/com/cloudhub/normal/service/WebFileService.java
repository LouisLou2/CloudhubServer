package com.cloudhub.normal.service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public interface WebFileService {

    String getShareWeb(@RequestParam(name = "id", required = true) String id,Model model,HttpSession session);

    String checkSharePassword(@RequestParam(name = "psw") String psw,Model model,HttpSession session);

    //String showSharedFile(Model model);
    String ReserveFile(Model model,HttpSession session);
}
