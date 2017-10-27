package com.healthy.cbelly.util;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by chan1park on 17.06.22
 *
 * EventBus Manager
 */
public class EventBusManager {
  private volatile static EventBusManager manager;
  private Map<String, Object> map;
  private static final String TAG = EventBusManager.class.getName();

  /**
   * @return SingleTon pattern
   */
  public static EventBusManager getInstance() {
    synchronized (EventBusManager.class) {
      if (manager == null) {
        manager = new EventBusManager();
      }
    }
    return manager;
  }

  /**
   * default hashMap 초기화 메서드
   */
  public void init() {
    if (map == null) {
      map = new HashMap<String, Object>();
    } else {
      map.clear();
    }
  }

  /**
   * default put method intent 를 대체하기 위해 간편하게 사용할수 있게금 만든 메서드
   *
   * @param key key  를 스트링형태로 지정하여
   * @param value value 를 오브젝트형으로 아무거나 담을 수 있다
   */
  public void put(String key, Object value) {
    map.put(key, value);
  }

  /**
   * default cast -  hashMap 을 원하는 곳에 전달하기 위한 method
   */
  public void cast() {
    if (map != null) EventBus.getDefault().postSticky(map);
  }

  /**
   * @param obj 원하는 자료형을 담아 전달하기 위한 method
   */
  public void cast(Object obj) {
    LogUtil.d(TAG, "cast obj >> " + obj);
    if (obj != null) EventBus.getDefault().postSticky(obj);
  }

  /**
   * @param eventType 원하는 타입을 가져올수 있다
   * (HashMap(String,Object))EventBusHandler.getInstance().accept(HashMap.class);
   * or
   * (String)EventBusHandler.getInstance().accept(String.class);
   */
  public Object accept(Class<?> eventType) {
    return EventBus.getDefault().getStickyEvent(eventType);
  }

  /**
   *  sticky 를 remove # 초기화 해야 하는 상황에서 사용한다.
   * @param eventType
   * @return
   */
  public Object clearAccept(Class<?> eventType) {
    return EventBus.getDefault().removeStickyEvent(eventType);
  }
}
