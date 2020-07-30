package net.yxchen.poorpoolblog.controller;

import net.yxchen.poorpoolblog.annotation.UserLoginToken;
import net.yxchen.poorpoolblog.bean.Label;
import net.yxchen.poorpoolblog.bean.ReturnMessage;
import net.yxchen.poorpoolblog.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabelController {
    @Autowired
    LabelService labelService;

    @GetMapping("/label")
    public ReturnMessage getLabels() {
        List<Label> labels = labelService.getLabels();
        return ReturnMessage.success().setParam("labels", labels);
    }

    @UserLoginToken
    @PostMapping("/label")
    public ReturnMessage addLabel(@RequestBody Label label) {
        if (labelService.addLabel(label)) {
            return ReturnMessage.success();
        } else {
            return ReturnMessage.fail().setMessage("添加失败");
        }
    }

    @GetMapping("/label/article/{articleId}")
    public ReturnMessage getLabelsByArticleId(@PathVariable("articleId") Long articleId) {
        List<Label> labels = labelService.getLabelsByArticleId(articleId);
        return ReturnMessage.success().setParam("labels", labels);
    }
}
