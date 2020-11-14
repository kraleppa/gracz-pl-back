# Gracz.pl backend

![spring badge](https://img.shields.io/badge/Spring%20framework-5.2.8-brightgreen)

## Project Description
Backend of Gracz.pl online shop.
[Link](https://github.com/kraleppa/gracz-pl-web) to frontend

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

Path variables:
| Param | Value |
| :--- | :--- |
| `gameId`  | `long` |

Headers:
| Key | Value | Description |
| :--- | :--- | :--- |
| `Authorization` | `string` | **Required** JWT |
| `Content-Type` | `application/json` | **Required**|


## Contributors :hamburger:
<table>
  <tr>
    <td align="center"><a href="https://github.com/kraleppa"><img src="https://avatars1.githubusercontent.com/u/56135216?s=460&u=359e017d16c70a31d3bdb086172308cc6f045acf&v=4" width="100px;" alt=""/><br /><sub><b>Krzysztof Nalepa</b></sub></a><br /></td>
    </td>
  </tr>
</table>  
