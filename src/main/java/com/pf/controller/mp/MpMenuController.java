package com.pf.controller.mp;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("${system.prefix.namespace3}/menu")
@ApiIgnore
public class MpMenuController {

    @Autowired
    private WxMpService wxMpService;

    @RequestMapping("/addWxMenu")
    public void addWxMenu(@RequestBody WxMenu wxMenu) throws WxErrorException {
        WxMpMenuService menuService = wxMpService.getMenuService();
        menuService.menuCreate(wxMenu);
    }

    @RequestMapping("/delWxMenu")
    public void delWxMenu() throws WxErrorException {
        WxMpMenuService menuService = wxMpService.getMenuService();
        menuService.menuDelete();
    }
}