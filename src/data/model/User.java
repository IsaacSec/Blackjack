package data.model;

public class User {

    private String nickname;
    private String token;
    private String roomName;

    public User(String userId, String token, String roomName) {
        this.nickname = userId;
        this.token = token;
        this.roomName = roomName;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
