package org.graphiks.kffi.engine.upcallfixture

import java.util.concurrent.CopyOnWriteArrayList

/**
 * Dispatcher d'upcall de test, dans un package distinct de [JvmUpcallEngine].
 *
 * L'objet est privé (classe package-private au niveau JVM) : l'accès croisé
 * au package depuis le moteur n'est possible que via privateLookupIn — un
 * retour à `MethodHandles.lookup().findStatic` ferait échouer le test
 * (IllegalAccessException). C'est la forme réelle générée par kextract en
 * M4.2 (`private object` + méthode statique de dispatch).
 *
 * [TestUpcallDispatchersBridge] expose au test la classe du dispatcher et la
 * file de capture sans compromettre la visibilité JVM de l'objet.
 */
private object TestUpcallDispatchers {
    val captured: MutableList<Triple<Int, Int, Long>> = CopyOnWriteArrayList()

    @JvmStatic
    fun captureStatusValueUserdata(status: Int, value: Int, userdata: Long) {
        captured += Triple(status, value, userdata)
    }

    @JvmStatic
    fun captureReturningInt(status: Int, userdata: Long): Int {
        captured += Triple(status, -1, userdata)
        return status + 1
    }
}

object TestUpcallDispatchersBridge {
    val dispatcherClass: Class<*> = TestUpcallDispatchers::class.java

    val captured: MutableList<Triple<Int, Int, Long>>
        get() = TestUpcallDispatchers.captured
}
