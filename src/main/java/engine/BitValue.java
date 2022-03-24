package main.java.engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import main.java.utils.Logger;

public class BitValue
{
//=======================================
// Attributes
//=======================================
	private final static String URL_BITCOIN_PRICE = "https://api.coinbase.com/v2/prices/spot?currency=USD";
	private double	value;

	public BitValue(double value)
	{
		this.value = value;
	}


//=======================================
// Accesses
//=======================================
	public double getValue()	{return this.value;}


//=======================================
// Local methods
//=======================================
	public static BitValue fetchNewValue()
	{
		HttpsURLConnection con = null;
        try
        {
            URL u = new URL(URL_BITCOIN_PRICE);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();


            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            Logger.log(sb.toString());

			double value = new JSONObject(sb.toString()).getJSONObject("data").getDouble("amount");
			return new BitValue(value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (con != null) {
            try
            {
                con.disconnect();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        return null;
	}
}
