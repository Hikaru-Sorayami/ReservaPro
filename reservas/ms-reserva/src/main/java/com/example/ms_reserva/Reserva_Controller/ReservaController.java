@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public String crearReserva(@RequestBody Reserva reserva) {

        boolean usuarioExiste = reservaService.validarUsuario(reserva.getUsuarioId());

        if (!usuarioExiste) {
            return "El usuario no existe";
        }

        return "Reserva creada correctamente";
    }
}