INPOST task

In order to build docker image, you have to build project with maven first (jdk17) so there is jar in target folder.

To create image run in folder with Dockerfile (gk/inpost is example, can be anything).
docker build -t gk/inpost .
To run image use:
docker run -p 8080:8080 gk/inpost


It was not specified so I decided to not create any other endpoints.
If you want to change/add product simply change/add code in ProductRepository.
For repeatable requests one UUID is constant - e6b577db-7102-4384-813c-bb0b5252ea43.

Discount polices are configurable with below properties - unit is percentage:
percentage.based.policy=50
count.based.policy=10,20,50
Percentage works intuitively. No matter how many products final price is calculated by formula:
product price multiply by amount and then provide percentage discount
eg. 10  * 10 products * 50% discount = 50 
Count based is array split by comma, calculator uses it if request has more than 1 amount of product
eg 10,20,50 - means, if you buy 2 products 10 % discount, if 3 products 20 % discount, if 4 and more products then 50% discount

Example request - if discountPolicy is specified then it is applied, if not normal calculation happens
POST http://localhost:8080/products/e6b577db-7102-4384-813c-bb0b5252ea43/calculate

{
//"discountPolicy": "COUNT_BASED" //optional values: COUNT_BASED, PERCENTAGE_BASED
"amount" : 100 //required
}


This would be it, it took some time, I think it is good for now.
If I had mote time I would also write more tests:
-testing strategies (one is just an example)
-contract validation
-more e2e test in ProductResourceTest