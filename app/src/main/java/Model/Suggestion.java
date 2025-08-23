package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "suggestions",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "user_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = @Index("user_id")
)
public class Suggestion {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "user_id")
    private Long userId;

    @ColumnInfo(name = "suggested_title")
    private String suggestedTitle;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = "time_stamp")
    private long timestamp;

    public Suggestion() { }

    @Ignore
    public Suggestion(Long id, User user, String suggestedTitle, String comment, long timestamp) {
        this.id = id;
        this.userId = user != null ? user.getId() : null;
        this.suggestedTitle = suggestedTitle;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    @Ignore
    public Suggestion(Long id, Long userId, String suggestedTitle, String comment, long timestamp) {
        this.id = id;
        this.userId = userId;
        this.suggestedTitle = suggestedTitle;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getSuggestedTitle() { return suggestedTitle; }
    public void setSuggestedTitle(String suggestedTitle) { this.suggestedTitle = suggestedTitle; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
