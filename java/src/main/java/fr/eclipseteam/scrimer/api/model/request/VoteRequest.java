package fr.eclipseteam.scrimer.api.model.request;

public class VoteRequest {
    private String postId;
    private boolean like;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public VoteRequest(String userId, String postId, boolean like){
        this.postId = postId;
        this.userId = userId;
        this.like = like;
    }
}
