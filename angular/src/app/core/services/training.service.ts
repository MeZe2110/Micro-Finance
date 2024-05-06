import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Training } from '../../core/models/training';

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  constructor(private http: HttpClient) { }

  addTraining(training: Training) {
    return this.http.post(`${environment.baseUrl}/training/add`, training);
  }

  updateTraining(training: Training) {
    return this.http.put(`${environment.baseUrl}/training/update`, training);
  }

  getAllTrainings() {
    return this.http.get<Training[]>(`${environment.baseUrl}/training/all`);
  }

  getTrainingById(idTraining: number) {
    return this.http.get<Training>(`${environment.baseUrl}/training/${idTraining}`);
  }

  getTrainingByIdUser(idUser: number) {
    return this.http.get<Training>(`${environment.baseUrl}/training/byUser/${idUser}`);
  }

  deleteTraining(idTraining: number) {
    return this.http.delete(`${environment.baseUrl}/training/${idTraining}`);
  }
}
