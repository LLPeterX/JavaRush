package com.javarush.task.task32.task3212;

        import com.javarush.task.task32.task3212.contex.InitialContext;
        import com.javarush.task.task32.task3212.service.Service;

        import java.util.Collections;

// локатор управляет всей фигней - при необходимсоти иниц.кеш (а он статик и иниц.при сорздании объекта)

/*
 Поиск сервиса на удаленном узле - дорогая процедура. Поэтому юаем кеш.
 При первос вызове службы Локатор помещает службу в кеш (список List<Service>  сервсов)
   - сервис: то, что на самом деле обраб.запрос
   - контекст: ссылка на сервис, кот. используется для поиска
   - локатор: получает перечень сервисов из кеша
   - кеш: список сервисов в виде списка

 */

 public class ServiceLocator {
    private static Cache cache;

    // инициализауия кеша при создании объекта класса
    static {
        cache = new Cache();
    }

    /**
     * First, check for a service object in the cache
     * If a service object is not in the cache, perform a lookup using
     * the JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of the service object in the context
     * @return Object mapped to the name in context
     */
    // проверяем - если объект в кеше, сразу его возвращаем.
    // если нет - создаем новый обхект кеша
    public static Service getService(String jndiName) {
        // ищем сервис с заданным именем
        Service svc = cache.getService(jndiName);
        // Если нужный сервис находится в кэше, метод getService(String jndiName) должен возвращать сервис из кэша.
        if(svc != null)  return svc;
        // 3. Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен создавать контекст.
        //4. Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен искать нужный сервис в контексте.
        //5. Если нужный сервис НЕ находится в кэше, метод getService(String jndiName) должен добавлять в кэш сервис,
        //    найденный в контексте и возвращать его.
        InitialContext context = new InitialContext();
        svc = (Service)context.lookup(jndiName);
        cache.addService(svc); // добавляем в любом случае, даже ели svc=null
        return svc;
    }
}
