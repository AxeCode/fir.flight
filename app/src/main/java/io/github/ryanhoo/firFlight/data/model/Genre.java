package io.github.ryanhoo.firFlight.data.model;

/**
 * Created by arthas on 09/01/2018.
 */

public class Genre {
    /**
     * deletedAt : null
     * deleted : false
     * createdAt : 2017-12-27T02:23:46.919Z
     * updatedAt : 2017-12-28T01:44:34.021Z
     * name : 文化
     * logo : http://imgs.wanmen.org/6ab0c731fcc53d5777f9a7738ec676ca.png
     * hide : false
     * genre : 586d234a5f071276741397fc
     * id : 5a4304328c7e4d6f4ef7966e
     * remainingDays : -1
     * isPaid : false
     * actions : {"watch":false,"download":false}
     */

    private Object deletedAt;
    private boolean deleted;
    private String createdAt;
    private String updatedAt;
    private String name;
    private String logo;
    private boolean hide;
    private String genre;
    private String id;
    private int remainingDays;
    private boolean isPaid;
    private ActionsBean actions;
    //课程英文简称
    private String subject;

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public ActionsBean getActions() {
        return actions;
    }

    public void setActions(ActionsBean actions) {
        this.actions = actions;
    }

    public static class ActionsBean {
        /**
         * watch : false
         * download : false
         */

        private boolean watch;
        private boolean download;

        public boolean isWatch() {
            return watch;
        }

        public void setWatch(boolean watch) {
            this.watch = watch;
        }

        public boolean isDownload() {
            return download;
        }

        public void setDownload(boolean download) {
            this.download = download;
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
