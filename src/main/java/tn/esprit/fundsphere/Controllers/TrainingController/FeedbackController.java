package tn.esprit.fundsphere.Controllers.TrainingController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.TrainigManagment.Feedback;
import tn.esprit.fundsphere.Services.TrainingService.IFeedbackServices;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final IFeedbackServices feedbackServices;

    @PostMapping("/add/{idUser}/{idTraining}")
    public Feedback addFeedback(
            @RequestBody @Valid Feedback feedback,
            @PathVariable int idUser,
            @PathVariable int idTraining) {

        return feedbackServices.addFeedback(feedback, idUser, idTraining);
    }

    @PutMapping("/update")
    public Feedback updateFeedback(@RequestBody @Valid Feedback feedback) {
        return feedbackServices.updateFeedback(feedback);
    }

    @GetMapping("/feedbacks/{idTraining}")
    public List<Feedback> findFeedbacksByTrainingId(@PathVariable int idTraining) {
        return feedbackServices.findFeedbacksByTrainingId(idTraining);
    }

    @GetMapping("/{idTraining}/{idUser}")
    public Feedback getFeedbacksByUserIdAndTrainingId(@PathVariable int idTraining, @PathVariable int idUser) {
        return feedbackServices.getFeedbacksByUserIdAndTrainingId(idTraining, idUser);
    }

    @DeleteMapping("/delete/{idFeedback}")
    public void deleteFeedback(@PathVariable int idFeedback) {
        feedbackServices.deleteFeedback(idFeedback);
    }
}
