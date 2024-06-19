using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Test : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }

    public void CallAuth()
    {
        HetIbUsbSDK.GetInstance().SendHex("A50201000004000300000075");
    }
}
