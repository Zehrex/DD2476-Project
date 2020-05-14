12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/event/client/ProcessCurrentQueueRefreshEvent.java
package ru.bgcrm.event.client;

/**
 * Сообщение о необходимости перейти в текущую выбранную очередь процессов
 * (если открыты очереди процессов) и обновить её.
 */
public class ProcessCurrentQueueRefreshEvent
	extends ClientEvent
{}