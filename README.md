# Gracz.pl backend

![spring badge](https://img.shields.io/badge/Spring%20framework-5.2.8-brightgreen)

## Project Description
Backend of Gracz.pl online shop.
[Link](https://github.com/kraleppa/gracz-pl-web) to frontend

## Authentication
Gracz.pl has implemented authentication by JSON Web Token.

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

## User API
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

## Basket API
### Add game to basket

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



## Contributors :hamburger:
<table>
  <tr>
    <td align="center"><a href="https://github.com/kraleppa"><img src="https://avatars1.githubusercontent.com/u/56135216?s=460&u=359e017d16c70a31d3bdb086172308cc6f045acf&v=4" width="100px;" alt=""/><br /><sub><b>Krzysztof Nalepa</b></sub></a><br /></td>
    </td>
  </tr>
</table>  
