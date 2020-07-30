package net.yxchen.poorpoolblog.controller;

import net.yxchen.poorpoolblog.annotation.UserLoginToken;
import net.yxchen.poorpoolblog.bean.Article;
import net.yxchen.poorpoolblog.bean.ReturnMessage;
import net.yxchen.poorpoolblog.service.ArticleService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @UserLoginToken
    @PostMapping("/article")
    public ReturnMessage addNewArticle(@RequestBody Map data) {
        Article article = new Article();
        try {
            BeanUtils.populate(article, (Map<String, ?>) data.get("article"));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.fail().setMessage("发生内部错误");
        }
        ArrayList<Integer> labels = (ArrayList<Integer>) data.get("labels");
        if (articleService.saveArticle(article, labels)) {
            return ReturnMessage.success().setMessage("保存成功");
        } else {
            return ReturnMessage.fail().setMessage("保存失败");
        }
    }

    @UserLoginToken
    @PostMapping("/article/{articleId}")
    public ReturnMessage editArticle(@PathVariable("articleId") Long articleId, @RequestBody Map data) {
        Article article = new Article();
        try {
            BeanUtils.populate(article, (Map<String, ?>) data.get("article"));
            article.setArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMessage.fail().setMessage("发生内部错误");
        }
        ArrayList<Integer> labels = (ArrayList<Integer>) data.get("labels");
        if (articleService.updateArticle(article, labels)) {
            return ReturnMessage.success().setMessage("保存成功");
        } else {
            return ReturnMessage.fail().setMessage("保存失败");
        }
    }

    @UserLoginToken
    @DeleteMapping("/article/{articleId}")
    public ReturnMessage deleteArticle(@PathVariable("articleId") Long articleId) {
        if (articleService.deleteArticle(articleId)) {
            return ReturnMessage.success().setMessage("删除成功");
        } else {
            return ReturnMessage.fail().setMessage("删除失败");
        }
    }

    @GetMapping("/article/{articleId}")
    public ReturnMessage getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleService.getArticle(articleId);
        if (article == null) {
            return ReturnMessage.fail().setMessage("保存失败");
        } else {
            return ReturnMessage.success().setParam("article", article);
        }
    }

    @GetMapping("/article")
    public ReturnMessage getAllArticleWithoutContentReverse() {
        List<Article> articles = articleService.getAllArticleWithoutContentReverse();
        return ReturnMessage.success().setParam("articles", articles);
    }

    @GetMapping("/article/label")
    public ReturnMessage getArticleByLabelId(@RequestParam(value = "labelId", required = false) List<Long> labelIds) {
        List<Article> articles = articleService.getArticleByLabelIdWithoutContentReverse(labelIds);
        return ReturnMessage.success().setParam("articles", articles);
    }
}
