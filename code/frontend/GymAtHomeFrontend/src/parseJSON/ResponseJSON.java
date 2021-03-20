package parseJSON;

import com.google.gson.JsonElement;

public class ResponseJSON {
    public String status;
    public int code;
    public String msg;
    public JsonElement data;

    @Override
    public String toString() {
        return "ResponseJSON{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
