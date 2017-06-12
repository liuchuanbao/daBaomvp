package cn.efarm360.com.dabaomvp.activity.picture;

/**
 * Created by pubinfo on 2017/6/12.
 */

public class Archive {
    private String filename;
    private int filezize;
    private Byte[] data;
    private String fileid;
    private String url;
    private int sort;
    private String shdid;

    public Archive() {
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getFilezize() {
        return this.filezize;
    }

    public void setFilezize(int filezize) {
        this.filezize = filezize;
    }

    public Byte[] getData() {
        return this.data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    public String getFileid() {
        return this.fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShdid() {
        return this.shdid;
    }

    public void setShdid(String shdid) {
        this.shdid = shdid;
    }
}
