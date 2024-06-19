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


    public void CallMode()
    {
        HetIBSDK.GetInstance().StartMotorByMode(300, 299);
    }

    public void CallTargetMotor()
    {
        HetIBSDK.GetInstance().StartTargetMotor("[{\"duty\":5,\"frequency\":50,\"index\":1},{\"duty\":5,\"frequency\":50,\"index\":2},{\"duty\":5,\"frequency\":50,\"index\":3}]");
    }
}
