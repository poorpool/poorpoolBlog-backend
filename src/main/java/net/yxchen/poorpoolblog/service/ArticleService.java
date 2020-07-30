package net.yxchen.poorpoolblog.service;

import net.yxchen.poorpoolblog.bean.*;
import net.yxchen.poorpoolblog.dao.ArticleLabelMapper;
import net.yxchen.poorpoolblog.dao.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleLabelMapper articleLabelMapper;

    public boolean saveArticle(Article article, List<Integer> labels) {
        article.setArticleDate(new Date());
        article.setArticleCommentCount(0);
        article.setArticleLikeCount((long) 0);
        boolean flag = articleMapper.insertSelective(article) > 0;
        for (Integer labelId : labels) {
            articleLabelMapper.insertSelective(new ArticleLabel(null, article.getArticleId(), (long) labelId));
        }
        return flag;
    }

    public boolean updateArticle(Article article, List<Integer> labels) {
        boolean flag = articleMapper.updateByPrimaryKeySelective(article) > 0;
        ArticleLabelExample articleLabelExample = new ArticleLabelExample();
        articleLabelExample.createCriteria().andArticleIdEqualTo(article.getArticleId());
        articleLabelMapper.deleteByExample(articleLabelExample);
        for (Integer labelId : labels) {
            articleLabelMapper.insertSelective(new ArticleLabel(null, article.getArticleId(), (long) labelId));
        }
        return flag;
    }

    public boolean deleteArticle(Long articleId) {
        return articleMapper.deleteByPrimaryKey(articleId) > 0;
    }

    public Article getArticle(Long articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }

    public List<Article> getAllArticleWithoutContentReverse() {
        List<Article> articles = articleMapper.selectWithoutContentByExample(new ArticleExample());
        Collections.reverse(articles);
        return articles;
    }

    public List<Article> getArticleByLabelIdWithoutContentReverse(List<Long> labelIds) {
        List<ArticleLabel> articleLabels = null;
        ArticleLabelExample articleLabelExample = new ArticleLabelExample();
        if (labelIds != null && !labelIds.isEmpty()) {
            ArticleLabelExample.Criteria criteria = articleLabelExample.createCriteria();
            criteria.andLabelIdIn(labelIds);
        }
        articleLabels = articleLabelMapper.selectByExample(articleLabelExample);
        List<Long> articleIdList = new ArrayList<>();
        for (ArticleLabel articleLabel : articleLabels) {
            articleIdList.add(articleLabel.getArticleId());
        }
        ArticleExample articleExample = new ArticleExample();
        if (!articleIdList.isEmpty()) {
            articleExample.createCriteria().andArticleIdIn(articleIdList);
            List<Article> articles = articleMapper.selectWithoutContentByExample(articleExample);
            Collections.reverse(articles);
            return articles;
        } else {
            return new ArrayList<>();
        }

    }
}
