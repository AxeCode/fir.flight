package io.github.ryanhoo.firFlight.data.model;

/**
 * Date: 01/01/2018
 *
 * @author lvhanqing
 */

public class Classes {

    private String name;
    private String hls;
    private boolean isChapter;
    private int chapterNum;
    private boolean isSelect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    public boolean isChapter() {
        return isChapter;
    }

    public void setChapter(boolean chapter) {
        isChapter = chapter;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum) {
        this.chapterNum = chapterNum;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
