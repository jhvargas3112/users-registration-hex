User Management
========================

Currently in the company, we need to implement a service for customer registration. From business they have given us the following use cases:

# Use cases

## Add User

We need to be able to add user to our system. We need to retrieve those information of the user:
* username
* password
* birthdate
* height
* weight

The user domain invariants are:
* username: at least 8 nonblank alphanumeric and unique
* age: greater or equal 18 years
* password: nonblank
* heigh: in m, between 1,00 and 2,30
* weight: in kg, between 40 and 150

## Update user
We need be able to modify user data:
* height
* weight

## Retrieve users
We need to be able to retrieve the registered user list ordered by birthdate.

## Create Statistics
For statistics purpose, we need to maintain a relationship between the year of birth and the average of the users BMI (Body Mass Index):

```math
BMI = weight / (height)^2
```

So, on every register / update we need to calculate and store the BMI of the year.

## Retrieve Statistics
Wee need to be able to retrieve the statistics orderer by BMI


# Details
Our system will be used with different clients, CLI and REST.
