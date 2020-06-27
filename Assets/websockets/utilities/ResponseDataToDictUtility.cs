using System.Collections;
using System.Collections.Generic;
using Newtonsoft.Json;
using UnityEngine;

public class ResponseDataToDictUtility
{
    public Dictionary<string, string> ResponseDataToDict(byte[] data)
    {
        string jsonString = System.Text.Encoding.Default.GetString(data);
        Dictionary<string, string> responseDict = JsonConvert.DeserializeObject<Dictionary<string, string>>(jsonString);
        return responseDict;
    }
}
