[![Travis CI](https://travis-ci.org/ryoppy/spray-example-todo.svg?branch=master)](https://travis-ci.org/ryoppy/spray-example-todo)

# spray example todo application.

シンプルなTODOアプリです。

just simple implementation.

you should separate route class. if many routes.

test case is not exists. i write soon. maybe.

## usage

POST /todos

- content=foo

response

```
{
  "id": 1,
  "content": "foo",
  "isDone": false
}
```

GET /todos/1

```
{
  "id": 1,
  "content": "foo",
  "isDone": false
}
```

PUT /todos/1

- isDone=true

```
{
  "id": 1,
  "content": "foo",
  "isDone": true
}
```

GET /todos?content=f

```
{
  "id": 1,
  "content": "foo",
  "isDone": true
}
```

DELETE /todos/1

```
{
  "id": 1,
  "content": "foo",
  "isDone": true
}
```
