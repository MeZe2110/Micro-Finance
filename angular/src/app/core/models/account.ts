import { TypeAccount } from "./type-account";
import { Transaction } from "./transaction";
import { UserShow } from "./user-show";
export class Account {
    
    idAccount!: number;
    numAccount!: number;
    rib!: number;
    balance!: number;
    date!: Date;
    typeA!: TypeAccount;
    transactions?: Transaction[]; 
    user?: UserShow; 

    
}


