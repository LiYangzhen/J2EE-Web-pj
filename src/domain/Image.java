package domain;

public class Image {
    private int ImageId;
    private String Title;
    private String Description;
    private String PATH;
    private String AsciiName;
    private String CountryName;
    private int CollectedNum;
    private String Content;
    private int UID;
    private boolean Collected;

    public Image() {
    }

    public Image(int imageId, String title, String description, String path, String asciiName,
                 String countryName, int collectedNum, String content, int UID, boolean collected) {
        this.ImageId = imageId;
        this.Title = title;
        this.Description = description;
        this.PATH = path;
        this.AsciiName = asciiName;
        this.CollectedNum = collectedNum;
        this.CountryName = countryName;
        this.Content = content;
        this.UID = UID;
        this.Collected = collected;
    }

    public boolean isCollected() {
        return Collected;
    }

    public void setCollected(boolean collected) {
        Collected = collected;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getDescription() {
        return Description;
    }

    public String getPATH() {
        return PATH;
    }

    public String getTitle() {
        return Title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setImageId(int imageId) {
        this.ImageId = imageId;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public int getCollectedNum() {
        return CollectedNum;
    }

    public String getAsciiName() {
        return AsciiName;
    }

    public String getContent() {
        return Content;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setAsciiName(String asciiName) {
        AsciiName = asciiName;
    }

    public void setCollectedNum(int collectedNum) {
        CollectedNum = collectedNum;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    @Override
    public String toString() {
        return "Image{" +
                "ImageId=" + ImageId +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", PATH='" + PATH + '\'' +
                '}';
    }
}
