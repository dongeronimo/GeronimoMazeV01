using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using NativeWebSocket;

public class NetworkManager : MonoBehaviour
{
    public string websocketId;
    public bool isConnected;
    public string ServerUrl;
    public string ClientId="";

    private WebSocket webSocket;
    private GetId getIdHandler = new GetId();
    // Start is called before the first frame update
    void Start()
    {
    
    }

    void Update()
    {
        if (webSocket == null)
        {
            CreateWebsocket();
        }
        else
        {
            RequestIdIfItHasNoId();
            //Seta estados
            isConnected = webSocket.State == WebSocketState.Open;
            ClientId = getIdHandler.WebSocketId();
        }
    }
    private void RequestIdIfItHasNoId()
    {
        if (string.IsNullOrEmpty(ClientId) && getIdHandler.CanRequest(webSocket) == true)
        {
            getIdHandler.DoRequest(webSocket);
        }
    }
    private void CreateWebsocket()
    {
        webSocket = new WebSocket(ServerUrl);
        webSocket.OnMessage += OnMessage;
        webSocket.Connect().GetAwaiter().OnCompleted(() => Debug.Log("Completed connection?"));
    }
    private void OnMessage(byte[] data)
    {
        Dictionary<string, string> responseDict = new ResponseDataToDictUtility().ResponseDataToDict(data);
        getIdHandler.HandleResponse(responseDict);
    }
}
