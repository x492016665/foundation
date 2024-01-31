package com.pf.controller.mp;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.pf.dao.entity.WxUser;
import com.pf.pojo.dto.notice.WxNoticeDTO;
import com.pf.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("${system.prefix.namespace3}/notice")
@ApiIgnore
public class MpNoticedController {

    @Autowired
    private WxMpService wxMpService;

    //明文
    private final String RAW = "raw";

    //aes密文
    private final String AES = "aes";

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    @Qualifier("subRouter")
    private WxMpMessageRouter subRouter;

    @Autowired
    private ThreadPoolExecutor executor;

    @RequestMapping
    public String notice(WxNoticeDTO notice, HttpServletRequest request) throws IOException {
        log.info("收到微信的事件推送:{}", notice);
        boolean checkSignature = wxMpService.checkSignature(notice.getTimestamp(), notice.getNonce(), notice.getSignature());
        if (!checkSignature) {
            log.info("非法请求:{}", notice);
            return "非法请求";
        }
        if (StrUtil.isNotEmpty(notice.getEchostr())) {
            return notice.getEchostr();
        }
        WxMpXmlMessage inMessage = parseWxMessage(notice, request);
        if (inMessage == null) {
            log.info("解析回传消息为空:{}", notice);
            return "解析回传消息为空";
        }
        //新增微信用户
        bindWxUser(inMessage);
        String encryptType = StrUtil.isBlank(notice.getEncrypt_type()) ? RAW : AES;
        WxMpXmlOutMessage outMessage = subRouter.route(inMessage);
        log.info("解析通知用户消息:{}", outMessage);
        if (outMessage == null) {
            return "";
        }
        if (RAW.equals(encryptType)) {
            return outMessage.toXml();
        } else if (AES.equals(encryptType)) {
            return outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        }
        return "";
    }

    public void bindWxUser(final WxMpXmlMessage inMessage) {
        executor.execute(() -> {
            try {
                log.info("inMessage:{}", inMessage);
                WxMpUser wxMpUser = wxMpService.getUserService().userInfo(inMessage.getFromUser());
                WxUser wxUser = createWxUser(wxMpUser);
                log.info("[{}]公众号创建用户[{}]", inMessage.getEvent(), wxMpUser);
                wxUserService.bindWxUser(wxUser);
            } catch (WxErrorException ignore) {
                log.info("解析微信信息异常[{}]", inMessage, ignore);
            } catch (Exception ignore) {
                log.error("关注公众号创建用户失败[{}]", inMessage, ignore);
            }
        });
    }

    private String toTagIds(Long[] tagIds) {
        if (tagIds == null) {
            return null;
        }
        return Arrays.stream(tagIds).map(x -> Convert.toStr(x))
                .collect(Collectors.joining(","));
    }

    private WxUser createWxUser(WxMpUser wxMpUser) {
        WxUser wxUser = new WxUser();
        wxUser.setUnionId(wxMpUser.getUnionId());
        wxUser.setMpOpenId(wxMpUser.getOpenId());
        wxUser.setSubscribe(wxMpUser.getSubscribe());
        wxUser.setLanguage(wxMpUser.getLanguage());
        wxUser.setSubscribeTime(wxMpUser.getSubscribeTime());
        wxUser.setRemark(wxMpUser.getRemark());
        wxUser.setGroupid(wxMpUser.getGroupId());
        wxUser.setTagIds(toTagIds(wxMpUser.getTagIds()));
        wxUser.setSubscribeScene(wxMpUser.getSubscribeScene());
        wxUser.setQrScene(wxMpUser.getQrScene());
        wxUser.setQrSceneStr(wxMpUser.getQrSceneStr());
        wxUser.setSubscribeStatusTime(new Date());
        return wxUser;
    }

    private WxMpXmlMessage parseWxMessage(WxNoticeDTO notice, HttpServletRequest request) throws IOException {
        WxMpXmlMessage inMessage = null;
        String encryptType = StrUtil.isBlank(notice.getEncrypt_type()) ? RAW : AES;
        String timestamp = notice.getTimestamp();
        String nonce = notice.getNonce();
        if (RAW.equals(encryptType)) {
            // 明文传输的消息
            inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
        } else if (AES.equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = notice.getMsg_signature();
            inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
        }
        log.info("解析回传消息[{}]", inMessage);
        return inMessage;
    }
}