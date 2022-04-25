import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ApplicationConfig;

import static utils.ApplicationUtils.log;

public class tmp {
    public static void main(String[] args) throws JSONException {
        String req = "{\"id\":1,\"method\":\"eth_submitLogin\",\"params\":[\"VLAD\",\"x\"],\"worker\":\"PC.VLAD\"}";
        JSONObject jsonObject = new JSONObject(req);
        if(jsonObject.getString("method").equals("eth_submitLogin")){
            JSONArray jsonArray = jsonObject.getJSONArray("params");
            jsonArray.put(0, ApplicationConfig.getInstance().getPoolAddress());
        }
        log(jsonObject.toString());

    }
}
