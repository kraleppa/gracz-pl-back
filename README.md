# Gracz.pl backend

![spring badge](https://img.shields.io/badge/Spring%20framework-5.2.8-brightgreen)

## Project Description
Backend of Gracz.pl online shop.
[Link](https://github.com/kraleppa/gracz-pl-web) to frontend


## Authentication
Gracz.pl has implemented authentication by JSON Web Token.
There are three types of users (roles):
* Anonymus user - not logged in user
* User - logged in user
* Admin - logged in admin

### Authenticate
```http
POST http://localhost:8080/authenticate
```

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Content-Type` | `application/json` | **Required**|


Example body
```javascript
{
    "username": "kraleppa",
    "password": "password"
}
```

Return message 
```javascript
{
    "jwt": "fancy-jwt"
}
```

## Games API
### Get all games
This endpoint has an option to filter games and sort them, also pagination is implemented.

```http
GET http://localhost:8080/api/v1/games
```

Params:
| Param | Description |
| :--- | :--- |
| `page`  | Page number |
| `console` | Get only games on given platform |
| `genre` | Get only games with given genre|
| `name` | Get only games which matches given search name |
| `sortBy` | Sort by parameter |
| `ascending` | Ascending or decending sorting |

Example:
```http
GET http://localhost:8080/api/v1/games?page=0&size=5&console=xbox_one&genre=SHOOTING
```

```javascript
{
    "content": [
        {
            "gameId": 2,
            "name": "Halo",
            "price": 199.99,
            "genre": "SHOOTING",
            "console": "XBOX_ONE",
            "description": "Świetna gra!",
            "imageUrl": "https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "paged": true,
        "unpaged": false
    },
    "totalElements": 1,
    "totalPages": 1,
    "last": true,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "size": 5,
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```

### Get specific game

```http
GET http://localhost:8080/api/v1/games/gameId
```


Path variables:
| Param | Value |
| :--- | :--- |
| `gameId`  | `long` |

Example
```http
GET http://localhost:8080/api/v1/games/2
```

```javascript
{
    "gameId": 2,
    "name": "Halo",
    "price": 199.99,
    "genre": "SHOOTING",
    "console": "XBOX_ONE",
    "description": "Świetna gra!",
    "imageUrl": "https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg"
}
```

### Add game 
`Admin only`

```http
POST http://localhost:8080/api/v1/games
```

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |
| `Content-Type` | `application/json` | **Required**|

Example body
```javascript
{
    "name": "Halo 2",
    "price": 79.99,
    "genre": "shooting",
    "console": "xbox_one",
    "description": "Great game!",
    "imageUrl": "https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg"
}
```

Return message
```javascript
{
    "gameId": 17,
    "name": "Halo 2",
    "price": 79.99,
    "genre": "SHOOTING",
    "console": "XBOX_ONE",
    "description": "Great game!",
    "imageUrl": "https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg"
}
```
### Edit game
`Admin only`

```http
PUT http://localhost:8080/api/v1/games/gameId
```

Path variables:
| Param | Value |
| :--- | :--- |
| `gameId`  | `long` |

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |
| `Content-Type` | `application/json` | **Required**|

Example
```http
PUT http://localhost:8080/api/v1/games/2
```


Example body
```javascript
{
    "name": "Halo 6",
    "price": 19.99,
    "genre": "RACING",
    "console": "XBOX_ONE",
    "description": "Całkiem dobra gra!",
    "imageUrl": "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
}
```

Return message
```javascript
{
    "gameId": 2,
    "name": "Halo 6",
    "price": 19.99,
    "genre": "RACING",
    "console": "XBOX_ONE",
    "description": "Całkiem dobra gra!",
    "imageUrl": "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
}
```

### Delete game
`Admin only`

```http
DELETE http://localhost:8080/api/v1/games/gameId
```

Example
```http
DELETE http://localhost:8080/api/v1/games/2
```

Path variables:
| Param | Value |
| :--- | :--- |
| `gameId`  | `long` |

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |
| `Content-Type` | `application/json` | **Required**|

## Users API
### Registration

```http
POST http://localhost:8080/api/v1/users
```


Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Content-Type` | `application/json` | **Required**|


Example body
```javascript
{
	"username": "testUser",             //unique
	"password": "testPassword",
	"email": "test@email.com",          //unique
	"name": "testName",
	"surname": "testSurname",
	"address": "street 15/2",
	"city": "testCity",
	"zip": "33-092"
}
```

Return message
```javascript
{
    "id": 18,
    "username": "testUser",
    "role": "ROLE_USER",
    "email": "test@email.com",
    "name": "testName",
    "surname": "testSurname",
    "address": "street 15/2",
    "city": "testCity",
    "zip": "33-092"
}
```

### Get user details
`User only`

```http
GET http://localhost:8080/api/v1/users
```

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |

Returns information about user, based on JWT.

Return body
```javascript
{
    "id": 18,
    "username": "testUser",
    "role": "ROLE_USER",
    "email": "test@email.com",
    "name": "testName",
    "surname": "testSurname",
    "address": "street 15/2",
    "city": "testCity",
    "zip": "33-092"
}
```

### Edit user
`User only`

```http
PATCH http://localhost:8080/api/v1/users
```

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |
| `Content-Type` | `application/json` | **Required**|

Example body (you can add here any field which you want to change in user)
```javascript
{
	"username": "newName"
}
```


Return message
```javascript
{
    "id": 18,
    "username": "newName",
    "role": "ROLE_USER",
    "email": "test@email.com",
    "name": "testName",
    "surname": "testSurname",
    "address": "street 15/2",
    "city": "testCity",
    "zip": "33-092"
}
```

## Baskets API
### Add game to basket
`User only`

```http
POST http://localhost:8080/api/v1/baskets
```

Params:
| Param | Value |
| :--- | :--- |
| `gameId`  | `long` |

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |

Example
```http
POST http://localhost:8080/api/v1/baskets?gameId=5
```

Return message
```javascript
{
    "gameList": [
        {
            "gameId": 5,
            "name": "Forza 6",
            "price": 19.99,
            "genre": "RACING",
            "console": "XBOX_ONE",
            "description": "Całkiem dobra gra!",
            "imageUrl": "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
        }
    ],
    "totalPrice": 19.99
}
```

### Get user's basket
`User only`

```http
GET http://localhost:8080/api/v1/baskets
```


Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |

It returns user's basket, based on JWT.


Return message
```javascript
{
    "gameList": [
        {
            "gameId": 5,
            "name": "Forza 6",
            "price": 19.99,
            "genre": "RACING",
            "console": "XBOX_ONE",
            "description": "Całkiem dobra gra!",
            "imageUrl": "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
        },
        {
            "gameId": 17,
            "name": "Hajlo 2",
            "price": 79.99,
            "genre": "SHOOTING",
            "console": "XBOX_ONE",
            "description": "Great game!",
            "imageUrl": "https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg"
        }
    ],
    "totalPrice": 99.98
}
```

### Delete game from basket
`User only`

```http
DELETE http://localhost:8080/api/v1/baskets
```

Params:
| Param | Value |
| :--- | :--- |
| `gameId`  | `long` |

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |

Example
```http
DELETE http://localhost:8080/api/v1/baskets?gameId=17
```

Return message
```javascript
{
    "gameList": [
        {
            "gameId": 5,
            "name": "Forza 6",
            "price": 19.99,
            "genre": "RACING",
            "console": "XBOX_ONE",
            "description": "Całkiem dobra gra!",
            "imageUrl": "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
        }
    ],
    "totalPrice": 19.99
}
```

## Orders API
### Get user's orders
`User only`

```http
GET http://localhost:8080/api/v1/orders
```

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |

Returns orders of user, based on JWT

Return message
```javascript
[
    {
        "orderId": 9,
        "orderPrice": 320.98,
        "shippingPrice": 19.99,
        "shipping": "GLS",
        "paymentOption": "Pobranie",
        "creationDate": "2020-11-14T19:15:32.805936",
        "orderState": "SENT",
        "orderElements": [
            {
                "elementId": 7,
                "name": "Halo",
                "price": 199.99,
                "imageUrl": "https://s3.gaming-cdn.com/images/products/734/orig/halo-the-master-chief-collection-xbox-one-cover.jpg"
            },
            {
                "elementId": 8,
                "name": "The last of us II",
                "price": 120.99,
                "imageUrl": "https://a.allegroimg.com/s512/037b18/ced7818d4a9c914abafbd856762a/THE-LAST-OF-US-PART-2-II-PS4-PLAYSTATION-PL-DUBB"
            }
        ]
    }
]
```
### Add order
`User only`
```http
POST http://localhost:8080/api/v1/orders
```

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |
| `Content-Type` | `application/json` | **Required**|

This endpoint takes every item from user's basket and create a new order with theese items.

Example body
```javascript
{
	"shippingPrice": 19.99,
	"shipping": "UPS",
	"paymentOption": "PayPal"
}
```

Return message
```
{
    "orderId": 8,
    "orderPrice": 19.99,
    "shippingPrice": 19.99,
    "shipping": "UPS",
    "paymentOption": "PayPal",
    "creationDate": "2020-11-14T21:34:43.014896",
    "orderState": "NEW",
    "orderElements": [
        {
            "elementId": 7,
            "name": "Forza 6",
            "price": 19.99,
            "imageUrl": "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
        }
    ]
}
```

### Get all orders
`Admin only`
```http
GET http://localhost:8080/api/v1/orders/all
```

Params:
| Param | Value |
| :--- | :--- |
| `inProgress`  | `boolean` |

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |


Returns every order in system. You can filter orders using inProgress param.

Example return message
```javascript
[
    {
        "orderId": 8,
        "orderPrice": 19.99,
        "shippingPrice": 19.99,
        "shipping": "UPS",
        "paymentOption": "PayPal",
        "creationDate": "2020-11-14T21:34:43.014896",
        "orderState": "NEW",
        "orderElements": [
            {
                "elementId": 7,
                "name": "Forza 6",
                "price": 19.99,
                "imageUrl": "https://www.mobygames.com/images/covers/l/315940-forza-motorsport-6-xbox-one-front-cover.png"
            }
        ]
    },
    {
        "orderId": 12,
        "orderPrice": 164.32,
        "shippingPrice": 19.99,
        "shipping": "GLS",
        "paymentOption": "Przelew",
        "creationDate": "2020-11-14T21:38:20.131302",
        "orderState": "NEW",
        "orderElements": [
            {
                "elementId": 10,
                "name": "The last of us II",
                "price": 120.99,
                "imageUrl": "https://a.allegroimg.com/s512/037b18/ced7818d4a9c914abafbd856762a/THE-LAST-OF-US-PART-2-II-PS4-PLAYSTATION-PL-DUBB"
            },
            {
                "elementId": 11,
                "name": "Mario Kart 8",
                "price": 43.33,
                "imageUrl": "https://i0.wp.com/www.semperludo.com/wp-content/uploads/2017/04/Mario-Kart-8-Deluxe-Switch-cover.jpg?fit=456%2C738&ssl=1"
            }
        ]
    }
]
```

### Change order state
`Admin only`
```http
PATCH http://localhost:8080/api/v1/orders
```

Params:
| Param | Value |
| :--- | :--- |
| `orderState`  | `state` |
| `orderId`  | `long` |


Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |

You can change state of existing order

Example
```http
http://localhost:8080/api/v1/orders?orderState=SENT&orderId=12
```

Return message
```javascript
{
    "orderId": 12,
    "orderPrice": 164.32,
    "shippingPrice": 19.99,
    "shipping": "GLS",
    "paymentOption": "Przelew",
    "creationDate": "2020-11-14T21:38:20.131302",
    "orderState": "SENT",
    "orderElements": [
        {
            "elementId": 10,
            "name": "The last of us II",
            "price": 120.99,
            "imageUrl": "https://a.allegroimg.com/s512/037b18/ced7818d4a9c914abafbd856762a/THE-LAST-OF-US-PART-2-II-PS4-PLAYSTATION-PL-DUBB"
        },
        {
            "elementId": 11,
            "name": "Mario Kart 8",
            "price": 43.33,
            "imageUrl": "https://i0.wp.com/www.semperludo.com/wp-content/uploads/2017/04/Mario-Kart-8-Deluxe-Switch-cover.jpg?fit=456%2C738&ssl=1"
        }
    ]
}
```

### Get details of order's owner 
`Admin only`
```http
GET http://localhost:8080/api/v1/orders/credentials
```
Params:
| Param | Value |
| :--- | :--- |
| `orderId`  | `long` |


Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |

Example
```http
GET http://localhost:8080/api/v1/orders/credentials?orderId=8
```

Return message
```javascript
{
    "email": "email@costam.pl",
    "name": "Krzysztof",
    "surname": "Nalepa",
    "address": "Krakowska 1",
    "city": "Bibice",
    "zip": "32-087"
}
```




## Contributors :hamburger:
<table>
  <tr>
    <td align="center"><a href="https://github.com/kraleppa"><img src="https://avatars1.githubusercontent.com/u/56135216?s=460&u=359e017d16c70a31d3bdb086172308cc6f045acf&v=4" width="100px;" alt=""/><br /><sub><b>Krzysztof Nalepa</b></sub></a><br /></td>
    </td>
  </tr>
</table>  
