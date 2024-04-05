import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agents")
public class RecommendationController {

    @GetMapping("/{id}/recommendations")
    public List<Recommendation> getRecommendations(@PathVariable String id) {
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation("1", "Add agents to Queue 1, there are too many clients. "));
        recommendations.add(new Recommendation("2", "Add agents to Queue 2, there are too many clients. "));
        recommendations.add(new Recommendation("3", "Add agents to Queue 3, there are too many clients. "));
        recommendations.add(new Recommendation("4", "Add agents to Queue 4, there are too many clients. "));
        recommendations.add(new Recommendation("5", "Add agents to Queue 5, there are too many clients. "));


        return recommendations;
    }

    // Clase interna para representar una recomendación
    public static class Recommendation {
        private String ID;
        private String Description;

        public Recommendation(String ID, String description) {
            this.ID = ID;
            this.Description = description;
        }

        // Getters y setters (pueden ser generados automáticamente por tu IDE)
        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }
    }
}
