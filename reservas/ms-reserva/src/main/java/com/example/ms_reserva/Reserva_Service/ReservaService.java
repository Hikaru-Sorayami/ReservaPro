@Service
public class ReservaService {

    @Autowired
    private RestTemplate restTemplate;

    public boolean validarUsuario(Long usuarioId) {
        String url = "http://localhost:8081/usuarios/" + usuarioId;

        try {
            restTemplate.getForObject(url, Object.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}