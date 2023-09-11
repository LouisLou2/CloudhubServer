package cn.keking.normal.model;

public class RemoteFile {
    private String name;
    private String path;
    private String type;
    private String size;
    private String lastModified;
    private String url;

    public RemoteFile(String name, String path, String type, String size, String lastModified, String url) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
        this.lastModified = lastModified;
        this.url = url;
    }

    public RemoteFile(String name, String path, String type, String size, String lastModified) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
        this.lastModified = lastModified;
    }

    public RemoteFile(String name, String path, String type, String size) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
    }

    public RemoteFile(String name, String path, String type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public RemoteFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public RemoteFile(String name) {
        this.name = name;
    }

    public RemoteFile() {
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }
}
