using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using NativeWebSocket;
using Newtonsoft.Json;
public class GetId 
{
    private bool _isRequesting = false;
    private string _websocketId = "";

    public bool IsRequesting()
    {
        return _isRequesting;
    }

    public string WebSocketId()
    {
        return _websocketId;
    }

    public bool CanRequest(WebSocket webSocket)
    {
        if(webSocket.State == WebSocketState.Open && IsRequesting() == false)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void HandleResponse(Dictionary<string, string> responseDict)
    {
        if(responseDict["type"] == "getId")
        {
            _websocketId = responseDict["websocketId"];
            _isRequesting = false;
        }
    }

    public void DoRequest(WebSocket webSocket)
    {
        _isRequesting = true;
        Dictionary<string, string> getIdRequest = new Dictionary<string, string>();
        getIdRequest.Add("type", "getId");
        webSocket.SendText(JsonConvert.SerializeObject(getIdRequest));
    }
}
