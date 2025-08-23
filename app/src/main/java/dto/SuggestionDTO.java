package dto;

import Model.Suggestion;

public class SuggestionDTO {
    public Long id;
    public Long userId;
    public String suggestedTitle;
    public String comment;
    public long timestamp;

    public static SuggestionDTO fromEntity(Suggestion e) {
        SuggestionDTO d = new SuggestionDTO();
        d.id = e.getId();
        d.userId = e.getUserId();
        d.suggestedTitle = e.getSuggestedTitle();
        d.comment = e.getComment();
        d.timestamp = e.getTimestamp();
        return d;
    }

    public Suggestion toEntity() {
        return new Suggestion(id, userId, suggestedTitle, comment, timestamp);
    }
}
