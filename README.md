# BasicBanking_RestfullService_Api

You can use provided project template as a start
The template project (gradle Java) is available under the src folder.  We recommend that you use Quarkus or Spring(boot), Junit, JPA as the primary choices for your implementation.

Task 1: Implement and test the model
These transaction objects will be used both to make financial requests of a BankAccount and to keep a record of those requests. The following Unit test segment indicates how transactions will be used on the service side:

BankAccount account = new BankAccount("Jim", 12345);
account.post(new DepositTransaction(1000));
account.post(new WithdrawalTransaction(200));
account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));
assertEquals(account.getBalance(), 703.50, 0.0001)



BONUS Task 1: Find a better implementation alternative
The bank account post method must do something special for each Transaction type. e.g. post(DepositTransaction) and post(WithdrawalTransaction. This solution will work but creating families of overloaded methods is discouraged as it causes problems with maintenance. Consider, if we added more Transaction subclasses we would need to keep changing the BankAccount class, overloading even more post methods. It is considered bad form in OO  to write case statements based on the type of objects. It also has the same maintenance problems as the first solution. Adding more Transaction subclasses would require changes. Find a solution to delegate the operation using polymorphism so that the Bank account is never changed by introducing new transaction types. At a öinimum you shoudl make the provided uni test to run:

Task 2:  Provide a REST API using Spring Rest Controllers and TEST
Provide a REST API to the banking system as follows. The following code demonstrates how BankAccounts might be used.  Use services and repositories to persist your model above into a Database using JPA.  Please provide tests (MOCK or othrewise) for your code:
To deposit money into an account, one would use:

curl --location --request POST 'http://localhost:8080/account/v1/credit/669-7788' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '    {
        "amount": 1000.0
    }'

response would be (200):
{
    "status": "OK",
    "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
}


To withdraw money:

curl --location --request POST 'http://localhost:8080/account/v1/debit/669-7788' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '    {
        "amount": 50.0
    }'

response would be (200):
{
    "status": "OK",
    "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
}


To get the current account data, one would use:

curl --location --request GET 'http://localhost:8080/account/v1/669-7788'

response would be:

{
    "accountNumber": "669-7788",
    "owner": "Kerem Karaca",
    "balance": 950.0,
    "createDate": "2020-03-26T06:15:50.550+0000",
    "transactions": [
        {
            "date": "2020-03-26T06:16:03.563+0000",
            "amount": 1000.0,
            "type": "DepositTransaction",
            "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
        },
        {
            "date": "2020-03-26T06:16:35.047+0000",
            "amount": 50.0,
            "type": "WithdrawalTransaction",
            "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
        }
    ]
}
