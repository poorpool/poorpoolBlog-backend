package net.yxchen.poorpoolblog.service;

import net.yxchen.poorpoolblog.bean.*;
import net.yxchen.poorpoolblog.dao.ArticleLabelMapper;
import net.yxchen.poorpoolblog.dao.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {
    @Autowired
    LabelMapper labelMapper;

    @Autowired
    ArticleLabelMapper articleLabelMapper;

    public List<Label> getLabels() {
        return labelMapper.selectByExample(null);
    }

    public boolean addLabel(Label label) {
        LabelExample labelExample = new LabelExample();
        LabelExample.Criteria criteria = labelExample.createCriteria();
        criteria.andLabelNameEqualTo(label.getLabelName());
        if (labelMapper.countByExample(labelExample) > 0) {
            return false;
        }
        return labelMapper.insertSelective(label) > 0;
    }

    public List<Label> getLabelsByArticleId(Long articleId) {
        ArticleLabelExample articleLabelExample = new ArticleLabelExample();
        ArticleLabelExample.Criteria criteria = articleLabelExample.createCriteria();
        criteria.andArticleIdEqualTo(articleId);
        List<ArticleLabel> articleLabels = articleLabelMapper.selectByExample(articleLabelExample);
        List<Long> labelIdList = new ArrayList<>();
        for (ArticleLabel articleLabel : articleLabels) {
            labelIdList.add(articleLabel.getLabelId());
        }
        LabelExample labelExample = new LabelExample();
        labelExample.createCriteria().andLabelIdIn(labelIdList);
        return labelMapper.selectByExample(labelExample);
    }
}
