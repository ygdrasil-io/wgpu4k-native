package publication.smoke;

import io.ygdrasil.kffi.Callback;
import io.ygdrasil.wgpu.WGPULogCallback;

public final class PublicationSmoke {
    private PublicationSmoke() {}

    public static Class<?>[] publishedApi() {
        return new Class<?>[] { Callback.class, WGPULogCallback.class };
    }
}
