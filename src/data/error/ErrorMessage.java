package data.error;

public class ErrorMessage {

    public static String getErrorMessage(ErrorCode err){
        String message = "";
        switch (err) {
            case DUPLICATED_NICK:
                message = "Your nickname is already used";
                break;

        }

        return message;
    }

}
