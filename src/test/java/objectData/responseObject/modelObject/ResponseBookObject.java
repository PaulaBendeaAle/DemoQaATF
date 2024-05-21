package objectData.responseObject.modelObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import objectData.responseObject.ResponseNotNull;
import org.testng.Assert;

@Getter
public class ResponseBookObject implements ResponseNotNull {

    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("author")
    private String author;
    @JsonProperty("publishDate")
    private String publish_date;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("pages")
    private Integer pages;
    @JsonProperty("description")
    private String description;
    @JsonProperty("website")
    private String website;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(isbn);
        if (title != null) {
            Assert.assertNotNull(title);
            Assert.assertNotNull(subTitle);
            Assert.assertNotNull(author);
            Assert.assertNotNull(publish_date);
            Assert.assertNotNull(publisher);
            Assert.assertNotNull(pages);
            Assert.assertNotNull(description);
            Assert.assertNotNull(website);
        }
    }
}
