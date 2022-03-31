package main.java.backend.engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import main.java.utils.Utils;

public class BitValue
{
//=======================================
// Attributes
//=======================================
	public static final String URL_BITCOIN_PRICE		= "https://api.coinbase.com/v2/prices/spot?currency=USD";

	private double	value;
	private String	currency;


//=======================================
// Constructor
//=======================================
	public BitValue(double value, String currency)
	{
		assert (value >= 0);
		this.value		= value;
		this.currency	= new String(currency);
	}

	public BitValue(BitValue bv)
	{
		this(bv.value, bv.currency);
	}


//=======================================
// Accesses
//=======================================
	public double	getValue	()	{return this.value;}
	public String	toString	()	{return Utils.genericToString(this.getClass().getDeclaredFields(), this);}


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

            JSONObject jsonObject = new JSONObject(sb.toString());
			double	value	= jsonObject.getJSONObject("data").getDouble("amount");
			String	currency= jsonObject.getJSONObject("data").getString("currency");

			return new BitValue(value, currency);
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
