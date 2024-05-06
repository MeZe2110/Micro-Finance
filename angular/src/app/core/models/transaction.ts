import { Account } from "./account";
import { TypeTransaction } from "./type-transaction";

export class Transaction {
    idTransaction!: number;
    amount!: number;
    date!: Date;
    typeT!: string; // Assuming TypeTransaction is represented as a string in TypeScript
    sender!: Account; // Assuming Account model is defined
    receiver!: Account; // Assuming Account model is defined
    //loan?: any; // Assuming Loan model is defined
    //investment?: any; // Assuming Investment model is defined
}
