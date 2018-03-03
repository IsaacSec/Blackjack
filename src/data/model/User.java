package data.model;

public class User {

    private String nickname;
    private String token;
    private String roomId;

    public User(String userId, String token, String roomId) {
        this.nickname = userId;
        this.token = token;
        this.roomId = roomId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
