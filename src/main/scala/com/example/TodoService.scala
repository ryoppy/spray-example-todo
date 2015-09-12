package com.example

import com.example.entity.{SearchParam, Todo}

import scala.collection.concurrent.TrieMap

class TodoService {

  var store: TrieMap[Int, Todo] = TrieMap()

  /**
   * find by id from store.
   * @param id todo id
   * @return stored todo object
   */
  def findById(id: Int): Option[Todo] = store.get(id)

  /**
   * search from store.
   * @param param search parameters
   * @return find todo objects
   */
  def search(param: SearchParam): Seq[Todo] = {
    store
      .filter { case (_, todo) => param.content.forall(todo.content.contains) }
      .filter { case (_, todo) => param.isDone.forall(todo.isDone == _) }
      .values.toSeq
  }

  /**
   * add store.
   * @param content todo content
   * @return stored todo object.
   */
  def create(content: String): Option[Todo] = {
    val newId = getNewId
    store.put(newId, Todo(newId, content, isDone = false))
    store.get(newId)
  }

  /**
   * ah...just update! yo!
   */
  def update(id: Int, content: Option[String], isDone: Option[Boolean]): Option[Todo] = {
    store.get(id).map((_, content, isDone)).map {
      case (todo, Some(c), Some(d)) =>
        Todo(id, c, d)

      case (todo, Some(c), None) =>
        todo.copy(content = c)

      case (todo, None, Some(d)) =>
        todo.copy(isDone = d)

      case (todo, _, _) =>
        todo
    }
    .map { t =>
      store.put(id, t)
      t
    }
  }

  /**
   * delete from store
   * @param id todo id
   * @return deleted todo object
   */
  def delete(id: Int): Option[Todo] = store.remove(id)

  private def getNewId = store.lastOption.map { case (id, _) => id + 1 }.getOrElse(1)
}
