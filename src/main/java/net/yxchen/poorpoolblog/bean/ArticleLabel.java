package net.yxchen.poorpoolblog.bean;

public class ArticleLabel {
    private Long id;

    private Long articleId;

    private Long labelId;

    public ArticleLabel() {
    }

    public ArticleLabel(Long id, Long articleId, Long labelId) {
        this.id = id;
        this.articleId = articleId;
        this.labelId = labelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return "ArticleLabel{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", labelId=" + labelId +
                '}';
    }
}