package com.github.yandoroshenko.demo

import zio.*

object Main extends ZIOAppDefault {
  case class A()
  case class B(a: A)

  val bLayer: ZLayer[A, Throwable, B] = ZLayer {
    ZIO.service[A].mapAttempt(B.apply)
  }

  val aLayer: ZLayer[Any, Throwable, A] = ZLayer.succeed(A())

  val x: ZIO[B, Throwable, String] = for {
    b <- ZIO.service[B]
  } yield "ab"

  override def run =
    for {
      str <- x.provideLayer(aLayer >>> bLayer)
      _ <- ZIO.debug(str)
    } yield ()
}
