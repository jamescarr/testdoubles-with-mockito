package org.jamescarr.eg;

import java.io.OutputStream;

public interface Marshaller<T> {
	public void marshall(T object, OutputStream out);
}
