package net.yxchen.poorpoolblog.dao;

import java.util.List;
import net.yxchen.poorpoolblog.bean.ArticleLabel;
import net.yxchen.poorpoolblog.bean.ArticleLabelExample;
import org.apache.ibatis.annotations.Param;

public interface ArticleLabelMapper {
    long countByExample(ArticleLabelExample example);

    int deleteByExample(ArticleLabelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticleLabel record);

    int insertSelective(ArticleLabel record);

    List<ArticleLabel> selectByExample(ArticleLabelExample example);

    ArticleLabel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ArticleLabel record, @Param("example") ArticleLabelExample example);

    int updateByExample(@Param("record") ArticleLabel record, @Param("example") ArticleLabelExample example);

    int updateByPrimaryKeySelective(ArticleLabel record);

    int updateByPrimaryKey(ArticleLabel record);
}