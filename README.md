# Reward App

### How to run
```
./mvnw spring-boot:run
```
Required Java 17

### API
#### To create transaction: POST api/transactions

Example body:
```
{
    "userId" : 1,
    "amount": 90
}
```

Example response:
```
{
    "id": 1
}
```

Statuses: 201, 400(if amount is not positive value or userId is not positive value or 0)

#### To update transaction: PUT api/transactions/{transaction_id}

Example body:
```
{
    "userId" : 1,
    "amount": 90
}
```

Statuses: 204, 400(if amount is not positive value or userId is not positive value or 0), 404(if transaction not found)


#### To get 3 months reports started from now: GET api/users/{user_id}/rewards/report

Example response:

```
{
    "rewards": {
        "FEBRUARY" : 150
    },
    "total": 150
}
```

Statuses: 200

