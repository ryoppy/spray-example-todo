package com.example

import com.example.entity.{SearchParam, Todo}
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.routing.HttpServiceActor

class TodoRoute extends HttpServiceActor with SprayJsonSupport with DefaultJsonProtocol {

  implicit val todoJson = jsonFormat3(Todo)

  val service = new TodoService

  def receive = runRoute {
    pathPrefix("todos") {
      get {
        // GET /todos/:id
        path(IntNumber) { id =>
          complete(service.findById(id))
        } ~
        // GET /todos?isDone=...&content=...
        parameters(('isDone.as[Boolean].?, 'content.?)).as(SearchParam) { sp =>
          complete(service.search(sp))
        }
      } ~
      // POST /todos
      post {
        formFields('content) { content =>
          complete(service.create(content))
        }
      } ~
      // PUT /todos/:id
      put {
        path(IntNumber) { id =>
          formFields(('content.?, 'isDone.as[Boolean].?)) { (content, isDone) =>
            complete(service.update(id, content, isDone))
          }
        }
      } ~
      // DELETE /todos/:id
      delete {
        path(IntNumber) { id =>
          complete(service.delete(id))
        }
      }
    }
  }
}
