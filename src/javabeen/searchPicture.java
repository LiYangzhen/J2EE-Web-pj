package javabeen;

public class searchPicture {

    private int ImageId;
    private String title;
    private String Description;
    private String PATH;

    public searchPicture(){
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

}
