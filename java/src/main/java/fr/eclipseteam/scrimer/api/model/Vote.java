package fr.eclipseteam.scrimer.api.model;

public class Vote {

    private String postId;
    private boolean like;

    public Vote(String postId, boolean like){
        this.postId = postId;
        this.like = like;
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


}
