import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { TrainingInscription } from '../../core/models/training-inscription';

@Injectable({
  providedIn: 'root'
})
export class TrainingInscriptionService {

  constructor(private http: HttpClient) { }

  addTrainingInscription (trainingInscription: TrainingInscription, idUser:number, idTraining: number) {
    return this.http.post(`${environment.baseUrl}/trainingInscription/add/${idUser}/${idTraining}`, trainingInscription);
  }

  annulerTrainingInscription (idTrainingInscription: number) {
    return this.http.put(`${environment.baseUrl}/trainingInscription/cancel/${idTrainingInscription}`, {});
  }  

  getTrainingInscriptionsByTrainingId (idTraining: number) {
    return this.http.get<TrainingInscription[]>(`${environment.baseUrl}/trainingInscription/byTraining/${idTraining}`);
  }

  getTrainingInscriptionsByUserId (idUser: number) {
    return this.http.get<TrainingInscription[]>(`${environment.baseUrl}/trainingInscription/byUser/${idUser}`);
  }

  getTrainingInscriptionById(idTrainingInscription: number) {
    return this.http.get<TrainingInscription>(`${environment.baseUrl}/trainingInscription/${idTrainingInscription}`);
  }
}
