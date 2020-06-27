using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace HelloWorld
{
    public class RotateCube : MonoBehaviour
    {
        // Start is called before the first frame update
        void Start()
        {

        }

        // Update is called once per frame
        void Update()
        {
            transform.Rotate(Vector3.up, 20 * Time.deltaTime);
            transform.Rotate(Vector3.left, 10 * Time.deltaTime);
        }
    }
}
