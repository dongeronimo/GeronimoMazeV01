using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class LabelIsConnected : MonoBehaviour
{
    public NetworkManager manager;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        if (manager.isConnected)
        {
            GetComponent<Text>().text = "Connected";
        }
        else
        {
            GetComponent<Text>().text = "Not connected";
        }
    }
}
