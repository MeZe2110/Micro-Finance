import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Feedback } from '../../core/models/feedback';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private http: HttpClient) { }

  addFeedback(feedback: Feedback, idUser:number, idTraining: number) {
    return this.http.post(`${environment.baseUrl}/feedback/add/${idUser}/${idTraining}`, feedback);
  }

  updateFeedback(feedback: Feedback) {
    return this.http.put(`${environment.baseUrl}/feedback/update`, feedback);
  }

  findFeedbacksByTrainingId(idTraining: number) {
    return this.http.get<Feedback[]>(`${environment.baseUrl}/feedback/feedbacks/${idTraining}`);
  }

  getFeedbacksByUserIdAndTrainingId(idTraining: number, idUser:number) {
    return this.http.get<Feedback>(`${environment.baseUrl}/feedback/${idTraining}/${idUser}`);
  }

  deleteFeedback(idFeedback: number) {
    return this.http.delete(`${environment.baseUrl}/feedback/delete/${idFeedback}`);
  }
}
