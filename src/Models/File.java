package Models;

public class File {

    private String name;
    private Integer size;
    private Status status;
    private String date;
    private String Address;
    private long downloaded = 0;
    private String suffix;

    public File(String name, int size, Status status, String date, String address, String suffix) {
        this.setName(name);
        this.setSize(size);
        this.setStatus(status);
        this.setDate(date);
        setAddress(address);
        this.setDownloaded(downloaded);
        this.setSuffix(suffix);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public long getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(long downloaded) {
        this.downloaded = downloaded;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }


}
