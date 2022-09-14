package Helpers;

import java.util.List;

public interface ObjectCreator<T> {

    T CreateObject();

    List<T> CreateObjects(int i);
}
