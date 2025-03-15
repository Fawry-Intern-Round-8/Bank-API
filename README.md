# Bank-API

## Use Case
![UseCaseDiagram](https://github.com/user-attachments/assets/604a2081-d4cd-4875-9a9a-ae0573727f94)

## Entity-Relationship Diagram (ERD)

```mermaid
erDiagram
    ACCOUNT {
        UUID id
        STRING card_number
        STRING name
        STRING email
        STRING password_hash
        FLOAT balance
        BOOLEAN is_active
    }
    
    TRANSACTION {
        UUID id
        UUID account_id
        STRING type
        FLOAT amount
        STRING notes
        DATE timestamp
    }
    
    TRANSACTION_LOG {
        UUID id
        UUID transaction_id
        STRING status
        STRING message
        DATE timestamp
    }
    
    ACCOUNT ||--o{ TRANSACTION : has
    TRANSACTION ||--|| TRANSACTION_LOG : logs
```
## Class Diagram
![ClassDiagram](https://github.com/user-attachments/assets/76fdf576-2de5-469f-bd4e-51c19cc239b5)


