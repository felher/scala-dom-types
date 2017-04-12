package com.raquo.dombuilder.domapi

/** Scala DOM Builder only interacts with the real DOM via this trait,
  * If you want to render to something other than a browser's DOM, implement this trait.
  *
  * This could potentially be used for an HTML renderer, React Native, virtual DOM, etc.
  *
  * Note: there are still a bunch of TODO-s to actually make this possible.
  */
trait DomApi[JsFun1[_, _], DomEvent, DomHtmlElement, DomElement, DomText, DomComment, DomNode] {

  @inline def parentNode(node: DomNode): Option[DomNode]

  @inline def createElement(tagName: String): DomElement

  @inline def createTextNode(text: String): DomText

  @inline def createComment(text: String): DomComment

  @inline def insertBefore(
    parentNode: DomElement,
    newNode: DomNode,
    referenceNode: DomNode
  ): Unit

  // @TODO[Performance] Add removeAllChildren and implement it as .textContent = "" or something?

  @inline def removeChild(parentNode: DomNode, child: DomNode): Unit

  @inline def appendChild(parentNode: DomNode, child: DomNode): Unit

  @inline def setTextContent(node: DomNode, text: String)

  @inline def setAttribute[V](element: DomElement, attrName: String, value: V): Unit

  @inline def removeAttribute(element: DomElement, attrName: String): Unit

  @inline def addEventListener[E <: DomEvent](
    element: DomNode,
    eventName: String,
    eventHandler: JsFun1[E, Unit],
    useCapture: Boolean = false
  ): Unit

  @inline def removeEventListener[E <: DomEvent](
    element: DomNode,
    eventName: String,
    eventHandler: JsFun1[E, Unit],
    useCapture: Boolean = false
  ): Unit

  @inline def setProperty[V](element: DomNode, propName: String, value: V): Unit

  @inline def setStyle[V](element: DomHtmlElement, propName: String, value: V): Unit
}
