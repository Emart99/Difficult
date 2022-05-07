package aplicacion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["aplicacion"])
open class DifficultApp

fun main(args: Array<String>) {
    runApplication<DifficultApp>(*args)
}

object Keys {
    private var keyItemCarrito: Long = 0L

    fun plusItemCarrito(): Long {
        keyItemCarrito++
        return keyItemCarrito
    }
}