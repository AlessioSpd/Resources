using UnityEngina;
using System.Collection;

[RequireComponent(typeof(CharacterController))]
[AddComponentMenu("Control Script/FPS Input")]
public class FPSInput : MonoBeahviour {
    [SerializedField] private float speed;
    private float gravity = -9.8f;
    private CharacterController _characterController;

    void Start() {
        _characterController = GetComponent<CharacterController>();
    }

    void Update() {
        float horizontalMove = Input.GetAxis("Horizontal") * speed;
        float VerticalMove = Input.GetAxis("Vertical") * speed;

        Vector3 movement = new Vector3(horizontalMove, 0, VerticalMove); //la posizione delle variabili cambia a tua scelta
        movement = Vector3.ClampMagnitude(movement, speed);

        movement.y *= gravity;

        movement *= Time.deltaTime;
        movement = transform.TransformDirection(movement);
        _characterController.Move(movement);
    }
}